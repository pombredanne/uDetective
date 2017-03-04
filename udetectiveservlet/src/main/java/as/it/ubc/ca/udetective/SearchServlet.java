package as.it.ubc.ca.udetective;

/**
 * @author UBC IT
 */

import as.it.ubc.ca.udetective.model.ServiceNowTicket;
import as.it.ubc.ca.udetective.service.MerchantService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SearchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final String PARAM_MERCHANT_CODE  = "merchantCode";
    private static final String PAGE_404             = "/WEB-INF/view/404.jsp";    
    private static final String PARAM_CWL_LOGIN_NAME = "cwlLoginName";        
    
    private static final Logger log = LogManager.getLogger(SearchServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        Object cwlLoginName = request.getSession().getAttribute(PARAM_CWL_LOGIN_NAME);
        log.debug("CWL login name is " + cwlLoginName);
        
        AdminHelper helper = new AdminHelper(request, response, getServletContext());
        if (!helper.isAdmin(cwlLoginName.toString())) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_404);
            rd.forward(request, response);              
            return;
        }
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/view/Search.jsp");
        rd.forward(request, response);  
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        
        Object cwlLoginName = request.getSession().getAttribute(PARAM_CWL_LOGIN_NAME);
        log.debug("CWL login name is " + cwlLoginName);
        
        AdminHelper helper = new AdminHelper(request, response, getServletContext());
        if (!helper.isAdmin(cwlLoginName.toString())) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_404);
            rd.forward(request, response);              
            return;
        }
        
        Map<String, String> errors = new HashMap<>();
        
        String merchantCode = request.getParameter(PARAM_MERCHANT_CODE);
                
        log.debug("--- Data submitted for search");
        log.debug("Merchant code " + merchantCode);
        
        request.setAttribute("merchantCode", merchantCode);
        request.setAttribute("search_merchantCode", merchantCode);
        
        if (errors.isEmpty()) {
            MerchantService merchantService = new MerchantService();
            List<ServiceNowTicket> merchantList = new ArrayList<>();

//            try {
//                merchantList = merchantService.findMerchants(merchantCode);
//            } catch (MMSException ae) {
//                RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_404);
//                rd.forward(request, response);     
//                return;
//            }

            log.debug("Merchants found " + merchantList.size());
            if (merchantList == null || merchantList.isEmpty()) {
                errors.put("validation", "No merchants are found.");
                log.warn(errors.toString());
                request.setAttribute("errors", errors);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/view/Search.jsp");   
                rd.forward(request, response);
                return;
            } else {
                request.setAttribute("applicationList", merchantList);
                // Save search criteria in a special attributes
                HttpSession session = request.getSession();
                session.setAttribute("search_merchantCode", merchantCode);
                
                request.setAttribute("merchantCode", merchantCode);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/view/Search_Result.jsp");
                rd.forward(request, response);         
                return;
            }
        } else {
            log.warn(errors.toString());
            request.setAttribute("errors", errors);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/view/Search.jsp");
            rd.forward(request, response);        
            return;
        }
    }
}