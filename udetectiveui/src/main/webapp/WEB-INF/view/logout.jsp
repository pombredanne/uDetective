<%
// Try to prevent browsers from caching page!!
   response.setHeader("Pragma","no-cache"); // HTTP 1.0
   response.setDateHeader("Expires",0); // prevents caching at proxy server
   response.setHeader("Cache-Control", "no-cache"); // no-cache parameter does not seem to work; no-store parameter used instead
   response.setHeader("Cache-Control", "no-store"); // HTTP 1.1
%>

<%@ page language="java" import="java.util.*"%>
<%@ page import="javax.servlet.http.HttpServlet" %>
<%@ page import=" javax.servlet.http.HttpSession" %>
<%@ page import=" javax.servlet.http.HttpServletRequest" %>
<%@ page import=" javax.servlet.http.HttpServletResponse" %>
<%@ page import=" javax.servlet.ServletException" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <body>

        <%  
            out.println("Session working : " + (String)session.getAttribute("description") + "<br/>");            
                                           
            Cookie[] cookies = request.getCookies();
            if(cookies!=null) {
                 for (int i = 0; i < cookies.length; i++) {
                         Cookie c = request.getCookies()[i];
                         c.setMaxAge(-1);
                         response.addCookie(c);
                  }
            }
        %> 
                                
                                
        <% 
           session.invalidate(); 
           request.getSession().invalidate();
           response.sendRedirect("https://<some_url>/abc/logout");
        %>
        <h1>Logout</h1>
        <p>You have logged out.</p>
    </body>
</html>
