<%@page import="ca.ubc.it.as.udetective.ui.service.UDetectiveServiceException"%>
<%@page import="ca.ubc.it.as.udetective.ui.service.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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

    <%
        String udetectiveCode = session.getAttribute("search_udetectiveCode").toString();

        List udetectiveList = new ArrayList();
        try {
            udetectiveList = new AdminService().find(udetectiveCode);
        } catch (Exception ae) {
            out.println(ae.toString());
        }
        session.setAttribute("udetectiveList", udetectiveList);
    %>
    
    <form method="GET" action="Search" style="float:right;margin-top:10px">
        <input type="text" id="udetectiveCode" name="udetectiveCode" size="10" value="<c:out value="${udetectiveCode}"/>">
        <input type="submit" value="Submit" />
    </form>
    <blockquote>
        <h4>IP Address Search - <c:out value="${udetectiveCode}"/></h4>
    </blockquote>
    <br clear="both" />

    <a href="Export">Export search results to Excel</a>
    <div class="search-results">
        <div class="header-row">
            <div class="column udetective-id">ID</div>
            <div class="column udetective-name">Name</div>
            <div class="column udetective-code">Code</div>
            <div class="column gl-code">GL Code</div>
            <div class="column operational-contact">Operational Contact</div>
            <div class="column auth-server-id">Auth Server ID</div>
            <div class="column udetective-active">Active</div>
            <div class="clear-row"></div>
        </div>
        <c:forEach items="<%= udetectiveList %>" var="app" varStatus="loopStatus">
            <a class="data-row" href="Edit?udetectiveId=<c:out value="${app.udetectiveId}"/>">
                <span class="column udetective-id"><c:out value="${app.udetectiveId}" />&nbsp;</span>
                <span class="column udetective-name"><c:out value="${app.udetectiveName}" />&nbsp;</span>
                <span class="column udetective-code"><c:out value="${app.srcTypeCd}" />&nbsp;</span>
                <span class="column gl-code"><c:out value="${app.glCode}" />&nbsp;</span>
                <span class="column operational-contact"><c:out value="${app.operationalContactEmail}" />&nbsp;</span>
                <span class="column auth-server-id"><c:out value="${app.authServerId}" />&nbsp;</span>
                <span class="column udetective-active">
                    <c:choose>
                        <c:when test="${app.active=='Y'}">
                            Yes
                        </c:when>
                        <c:otherwise>
                            No
                        </c:otherwise>
                    </c:choose>
                </span>
                <span class="clear-row"></span>
            </a>
        </c:forEach>
    </div>

    <br clear="both" />
    <a href="Export">Export search results to Excel</a>
    <br /><br />

<jsp:include page="/WEB-INF/view/footer.jsp" />