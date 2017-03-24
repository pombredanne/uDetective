<%@page import="java.util.Random"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.TreeMap"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="javax.servlet.http.HttpServlet" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="javax.servlet.ServletException" %>

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp">
    <jsp:param name="homeLinkClass" value="active"/>
</jsp:include>

    <p>Use the following form to find IP addresses by ... code, or <a href="/udetective/Search">click here to list all IP addresses</a>.</p>
    <form id="searchForm" method="GET" action="Search">
        <div class="error">${errors.validation}</div>
        <div class="hint">Hint: For wildcard search please use '%'</div>
        <label for="ipAddress">IP Address:</label>
        <div class="field">
            <input type="text" id="ipAddress" name="ipAddress" size="10" value="" />
            <input type="submit" value="Submit" />
        </div>
        <div class="clear"></div>
    </form>

    <br/>
    <br/> 
    
    <jsp:include page="/WEB-INF/view/Admin_Contact.jsp" />
    
<jsp:include page="/WEB-INF/view/footer.jsp" />