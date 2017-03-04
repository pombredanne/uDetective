/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package as.it.ubc.ca.udetective;

import as.it.ubc.ca.udetective.model.AbstractModel;
import as.it.ubc.ca.udetective.service.MerchantService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author strider
 */
public class AdminHelper {

    private HttpServletRequest request;
    private ServletContext context;
    private HttpServletResponse response;    
    
    private static final Logger LOG = LogManager.getLogger(AdminHelper.class);
    
    private static final String PAGE_404                 = "/WEB-INF/view/404.jsp";        
    
    public AdminHelper(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        this.request = request;
        this.context = context;
        this.response = response;
    }
    
    public boolean isAdmin(String cwlName) throws ServletException, IOException {
        
        try {
            AbstractModel admin = new MerchantService().findAdminByCwlName(cwlName);
            if (admin == null) {
                LOG.error("User " + cwlName + " has no administrative privileges");
                RequestDispatcher rd = context.getRequestDispatcher(PAGE_404);
                rd.forward(request, response);  
                return false;
            }
        } catch (MMSException ae) {
            LOG.error(ae.toString());
            RequestDispatcher rd = context.getRequestDispatcher(PAGE_404);
            rd.forward(request, response);     
        }               
        return true;
    }
    
    
    
}
