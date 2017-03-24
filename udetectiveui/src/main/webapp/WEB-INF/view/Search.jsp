<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="javax.servlet.http.HttpServlet" %>
<%@ page import=" javax.servlet.http.HttpSession" %>
<%@ page import=" javax.servlet.http.HttpServletRequest" %>
<%@ page import=" javax.servlet.http.HttpServletResponse" %>
<%@ page import=" javax.servlet.ServletException" %>

<jsp:include page="/WEB-INF/view/header.jsp" />
<jsp:include page="menu.jsp">
    <jsp:param name="searchLinkClass" value="active"/>
</jsp:include>

    <blockquote>
        <h4>Search by IP address</h4>
    </blockquote>

    <p>Use this form to search ...
    Once the search criteria is defined click SUBMIT.</p>

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

<jsp:include page="/WEB-INF/view/footer.jsp" />