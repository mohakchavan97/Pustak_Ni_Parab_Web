<%-- 
    Document   : viewAllIssues
    Created on : 12 Mar, 2022, 11:27:03 PM
    Author     : Mohak Chavan
--%>

<%@page import="com.mohakchavan.pustakniparab_web.Models.Issues"%>
<%@page import="java.util.List"%>
<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page import="com.mohakchavan.pustakniparab_web.Helpers.SessionHelper"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View ALL Issues | Pustak Ni Parab</title>
	<link rel="stylesheet" href="./css/base.css"/>
	<script>
	    function pageLoaded() {
		document.getElementById("loader").style.display = "none";
	    }
	</script>
    </head>

    <%
	Map sessionMap = new SessionHelper(request).checkSessionAndGetCurrentUser();
	if (!sessionMap.containsKey(Constants.ATTRIBUTE_KEY_NAMES.IS_SESSION_VALID) || !((Boolean) sessionMap.get(Constants.ATTRIBUTE_KEY_NAMES.IS_SESSION_VALID))) {
	    request.getRequestDispatcher(Constants.PATHS.JSP.LOGIN).forward(request, response);
	}

	List<Issues> issuesList = null;
	try {
	    issuesList = (List<Issues>) request.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.ALL_ISSUES_FOR_HTML);
	} catch (Exception ex) {
	}
    %>

    <body style="background-color: #f5f5f5;" onload="pageLoaded();">

	<jsp:include page="./genericContent/header.jsp" flush="true"/>

	<div id="loader">
	    <jsp:include page="./genericContent/loader.jsp" flush="true"/>
	</div>

	<div id="errorDiv" 
	     <%
		 String errorData = "";
		 boolean hasError = false;
		 try {
		     errorData = request.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.HAS_ERROR_WITH_DATA).toString().trim();
		     hasError = true;
		 } catch (Exception ex) {
		     hasError = false;
		 }
		 try {
		     errorData += " " + request.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.HAS_TRNSCTN_ERROR_WITH_DATA).toString().trim();
		     hasError = true;
		 } catch (Exception ex) {
		     if (!hasError) {
			 out.print(" hidden ");
		     }
		 }
	     %>
	     align="center" style="margin-top: 13%; margin-bottom: -14.8%; color: red;
	     font-size: x-large; font-weight: bolder; word-wrap: break-word;"><%=errorData.toString()%></div>

	<div id="viewAllIssues" style="margin-top: 15%;">
	    <div id="noIssuesDiv" style="margin-top: 1%; margin-bottom: 5%; color: red; font-size: x-large; font-weight: bolder;
		     word-wrap: break-word; float: left; left: 50%; position: relative; transform: translateX(-50%);"

		     <%
			 if (issuesList == null || issuesList.isEmpty()) {
			     out.println(">" + Constants.ERRORS.NO_ISSUES + "</div>");
			 } else {
			     out.println(" hidden>" + Constants.ERRORS.NO_ISSUES + "</div>");
			     for (Issues issue : issuesList) {
		     %>

		     <jsp:include page="./genericContent/issueCard.jsp" flush="true">
			 <jsp:param name="issue_id" value="<%=String.valueOf(issue.getIssueNo())%>"/>
			 <jsp:param name="book_name" value="<%=issue.getBookName()%>"/>
			 <jsp:param name="name_id" value="<%=issue.getIssuerId()%>"/>
			 <jsp:param name="issuer_name" value="<%=issue.getIssuerName()%>"/>
			 <jsp:param name="issue_date" value="<%=issue.getIssueDate()%>"/>
			 <jsp:param name="isReturned" value="<%=issue.getIsReturned()%>"/>
		     </jsp:include>

		     <%
			     }
			 }
		     %>
	</div>

    </body>
</html>
