/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.ubc.it.as.udetective.ui;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author strider
 */
public class AdminHelper {

    //final private HttpServletRequest request;
    //final private ServletContext context;
    //final private HttpServletResponse response;    
    
    //final private static Logger LOG = getLogger(AdminHelper.class);
    
    //private static final String PAGE_404                 = "/WEB-INF/view/404.jsp";        
    
    public AdminHelper(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        //this.request = request;
        //this.context = context;
        //this.response = response;
    }
    
    public boolean isAdmin(String cwlName) throws ServletException, IOException {
        
//        try {
//            Admin admin = new UDetectivrService().findAdminByCwlName(cwlName);
//            if (admin == null) {
//                LOG.error("User " + cwlName + " has no administrative privileges");
//                RequestDispatcher rd = context.getRequestDispatcher(PAGE_404);
//                rd.forward(request, response);  
//                return false;
//            }
//        } catch (UDetectiveException ae) {
//            LOG.error(ae.toString());
//            RequestDispatcher rd = context.getRequestDispatcher(PAGE_404);
//            rd.forward(request, response);     
//        }               
        return true;
    }
    
    
    
}
