<%--
    Document   : homePage
    Created on : 12 Jan, 2021, 5:24:49 PM
    Author     : Mohak Chavan
--%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
<%@page import="com.mohakchavan.pustakniparab_web.Models.DashBoard.DashBoard"%>
<%@page import="java.util.List"%>
<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.SessionHelper"%>
<%@page import="java.util.Map"%>
<%@page import="com.mohakchavan.pustakniparab_web.Models.SessionUser"%>
<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | Pustak Ni Parab</title>
	<link rel="stylesheet" href="./css/homePage.css"/>
	<script type="text/javascript">
	    function hideLoader() {
		document.getElementById("loader").style.display = "none";
	    }
	</script>
    </head>
    <body style="background-color: white;" onload="hideLoader();">

	<%
	    try {
		SessionUser currentUser;
		Map map = SessionHelper.checkSessionAndGetCurrentUser(request);
		if (map.containsKey(Constants.ATTRIBUTE_KEY_NAMES.IS_SESSION_VALID) && ((Boolean) map.get(Constants.ATTRIBUTE_KEY_NAMES.IS_SESSION_VALID))) {
		    currentUser = (SessionUser) map.get(Constants.ATTRIBUTE_KEY_NAMES.CURRENT_USER);
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
	    <img src=<%out.print("\"./charts/demoChart.jpeg\"");%> alt="alt" style="width: 30%; height: auto;" align="center"/>
	    <br/>
	    <p style="padding: 5%;">
		<%
		    out.println(session.getId() + "<br/>" + session.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_CURRENT_USER_VERIFIED));
		%>
		<br/><br/></p>

	    <div id="dashboardImages" class="dashboardImages">
		<!--Use this div for showing all the dashboard items-->

		<%--<jsp:include page="./genericContent/dashboardCard.jsp" flush="true"/>--%>
		<%--<jsp:include page="./genericContent/dashboardCard.jsp" flush="true"/>--%>

		<%
		    File dashDataFile = new File(getServletContext().getRealPath(Constants.PATHS.DASHBOARD_DATA_PATH));
		    if (dashDataFile.exists()) {
			BufferedReader reader = new BufferedReader(new FileReader(dashDataFile));
			String lines = "", str;
			while ((str = reader.readLine()) != null) {
			    lines += str;
			}
			reader.close();
			JSONParser parser = new JSONParser();
			try {
			    JSONObject jsonDashData = (JSONObject) parser.parse(lines);

//			    if (dashboardList != null && dashboardList.size() > 0) {
			    if (jsonDashData != null && jsonDashData.size() > 0) {
				for (int i = 0; i < jsonDashData.size(); i++) {
				    DashBoard dashBoard = new DashBoard(jsonDashData.get("" + i).toString());
				    if (dashBoard.isImage()) {
//				    if ((Boolean) json.get("isImage")) {

		%>

		<div class="no-float-items"><!--Use this div inside of previous div for showing one item only in a row-->
		    <jsp:include page="./genericContent/dashboardCard.jsp" flush="true">
			<jsp:param name="is_image_data" value="true"/>
			<jsp:param name="image_link" value="<%=dashBoard.getImageHREF()%>"/>
		    </jsp:include>
		</div>

		<%			    }
				}
			    }
			} catch (Exception ex) {
			    System.out.println(ex.getMessage());
			    out.println(Constants.ERRORS.SOME_ERROR_FULL);
			}
		    }
		%>

<!--		<img class="dashbrdImages" src=<%out.print("\"./charts/demoChart.jpeg\"");%> alt="alt" style="width: 30%; height: auto;" align="center"/>
<img class="dashbrdImages" src=<%out.print("\"./charts/demoChart.jpeg\"");%> alt="alt" style="width: 30%; height: auto;" align="center"/>
<img class="dashbrdImages" src=<%out.print("\"./charts/demoChart.jpeg\"");%> alt="alt" style="width: 30%; height: auto;" align="center"/>
<img class="dashbrdImages" src=<%out.print("\"./charts/demoChart.jpeg\"");%> alt="alt" style="width: 30%; height: auto;" align="center"/>
<img class="dashbrdImages" src=<%out.print("\"./charts/demoChart.jpeg\"");%> alt="alt" style="width: 30%; height: auto;" align="center"/>
<img class="dashbrdImages" src=<%out.print("\"./charts/demoChart.jpeg\"");%> alt="alt" style="width: 30%; height: auto;" align="center"/>
<img class="dashbrdImages" src=<%out.print("\"./charts/demoChart.jpeg\"");%> alt="alt" style="width: 30%; height: auto;" align="center"/>
<img class="dashbrdImages" src=<%out.print("\"./charts/demoChart.jpeg\"");%> alt="alt" style="width: 30%; height: auto;" align="center"/>-->
	    </div>


	    <br/>
	    <div style="display: none;">
		<a href="newBooks">New Books</a>
		<a href="issues">Issues</a>
		<a href="logout">Logout</a>
		<input type="button" onclick="document.getElementById('loader').style.display = 'block';">
	    </div>
	</div>

	<%
		} else {
		    request.getRequestDispatcher(Constants.PATHS.JSP.LOGIN).forward(request, response);
		}
	    } catch (Exception ex) {
		request.getRequestDispatcher(Constants.PATHS.JSP.LOGIN).forward(request, response);
	    }
	%>

    </body>
</html>
