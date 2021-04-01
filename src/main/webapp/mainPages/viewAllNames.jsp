<%-- 
    Document   : viewAllNames
    Created on : 26 Jan, 2021, 12:16:57 PM
    Author     : Mohak Chavan
--%>

<%@page import="com.mohakchavan.pustakniparab_web.Models.CurrentUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View ALL Names | Pustak Ni Parab</title>
	<link rel="stylesheet" href="./css/viewAllNames.css"/>
    </head>
    <body style="background-color: #f5f5f5;">
	
	<jsp:include page="./genericContent/header.jsp" flush="true">
            <jsp:param name="userGivenName" value="<%=CurrentUser.getDisplayName()%>"/>
	    <jsp:param name="userPhoto" value="<%=CurrentUser.getPhotoUrl()%>"/>
        </jsp:include>
	
        <div id="allNameCards" align="center" style="margin-top: 10%;">
	    <jsp:include page="../genericContent/nameCard.jsp" flush="true"></jsp:include>
	    
	    <jsp:include page="../genericContent/nameCard.jsp" flush="true"></jsp:include>
	    
	    <jsp:include page="../genericContent/nameCard.jsp" flush="true"></jsp:include>
	    
	    <jsp:include page="../genericContent/nameCard.jsp" flush="true"></jsp:include>
	    
	    <jsp:include page="../genericContent/nameCard.jsp" flush="true"></jsp:include>
	    
	    <jsp:include page="../genericContent/nameCard.jsp" flush="true"></jsp:include>
	    
	    <jsp:include page="../genericContent/nameCard.jsp" flush="true"></jsp:include>
	    
	    <jsp:include page="../genericContent/nameCard.jsp" flush="true"></jsp:include>
	    
	    <jsp:include page="../genericContent/nameCard.jsp" flush="true"></jsp:include>
	    
	    <jsp:include page="../genericContent/nameCard.jsp" flush="true"></jsp:include>
	</div>
    </body>
</html>
