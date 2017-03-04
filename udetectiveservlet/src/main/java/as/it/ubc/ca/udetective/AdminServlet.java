package as.it.ubc.ca.udetective;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final String PARAM_CWL_LOGIN_NAME     = "cwlLoginName";
    
    private static final Logger log = LogManager.getLogger(AdminServlet.class);

   /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processGetRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //AdminHelper helper = new AdminHelper(request, response, getServletContext());

        // Check if the user has administrative privileges
        // CAS returns value of attribute 'cwlLoginName'
        Object cwlLoginName = request.getSession().getAttribute(PARAM_CWL_LOGIN_NAME);
        log.debug("CWL login name is " + cwlLoginName);
        
        AdminHelper helper = new AdminHelper(request, response, getServletContext());
        if (helper.isAdmin(cwlLoginName.toString())) {
            log.debug("The administrator " + cwlLoginName + " is logging in");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/view/Admin.jsp");
            rd.forward(request, response);        
            return;
        }
    }    
    
    
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processGetRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Merchant Management System - Administration";
    }
}