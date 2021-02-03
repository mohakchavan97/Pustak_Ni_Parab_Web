<%-- 
    Document   : homePage
    Created on : 12 Jan, 2021, 5:24:49 PM
    Author     : Mohak Chavan
--%>

<%@page import="com.mohakchavan.pustakniparab_web.Models.CurrentUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | Pustak Ni Parab</title>
    </head>
    <body style="background-color: white;">

        <jsp:include page="../genericContent/header.jsp" flush="true">
	    <jsp:param name="userGivenName" value="<%=request.getAttribute(\"userGivenName\")%>"/>
	    <jsp:param name="userPhoto" value="<%=request.getAttribute(\"userPhoto\")%>"/>
        </jsp:include>

        <div id="homeContent" style="margin-top: 15%;">
	    <h1>Pustak Ni Parab Home Page</h1>
	    <p><%
		out.print(CurrentUser.getuId());
		out.print("<br/>");
		out.print(CurrentUser.getProvider());
		%></p>
        </div>

    </body>
</html>
