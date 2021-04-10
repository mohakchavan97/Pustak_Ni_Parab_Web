<%--
    Document   : homePage
    Created on : 12 Jan, 2021, 5:24:49 PM
    Author     : Mohak Chavan
--%>

<%@page import="com.mohakchavan.pustakniparab_web.Models.CurrentUser2"%>
<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | Pustak Ni Parab</title>
	<script type="text/javascript">
	    function hideLoader() {
		document.getElementById("loader").style.display = "none";
	    }
	</script>
    </head>
    <body style="background-color: white;" onload="hideLoader();">

	<%
	    try {
		session = request.getSession(false);
		if (session == null) {
		    request.getRequestDispatcher("login").forward(request, response);
		} else if (session != null) {
		    CurrentUser2 currentUser = (CurrentUser2) session.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.USER_SESSION_DATA);
	%>

        <jsp:include page="./genericContent/header.jsp" flush="true"/>

	<div id="loader">
	    <jsp:include page="./genericContent/loader.jsp" flush="true"/>
	</div>

	<div id="homeContent" style="margin-top: 15%;" align="center">
	    <h1>Pustak Ni Parab Home Page</h1>
	    <p><%
		out.print(currentUser.getuId());
		out.print("<br/>");
		out.print(currentUser.getProvider());
		%></p>
	    <br/>
	    <p style="padding: 5%;">
		<%
		    out.println(session.getId() + "<br/>" + session.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_CURRENT_USER_VERIFIED));
		%>
		<br/><br/>
		<img src=<%out.print("\"./charts/demoChart.jpeg\"");%> alt="alt" style="width: 30%; height: auto;"/>
	    </p>
	    <a href="newBooks">New Books</a>
	    <a href="issues">Issues</a>
	    <a href="logout">Logout</a>
	    <input type="button" onclick="document.getElementById('loader').style.display = 'block';">
        </div>

	<%
		}
	    } catch (Exception ex) {
		request.getRequestDispatcher("login").forward(request, response);
	    }
	%>

    </body>
</html>
