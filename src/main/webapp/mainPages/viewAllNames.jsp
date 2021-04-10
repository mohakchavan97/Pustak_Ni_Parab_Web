<%-- 
    Document   : viewAllNames
    Created on : 26 Jan, 2021, 12:16:57 PM
    Author     : Mohak Chavan
--%>

<%@page import="com.mohakchavan.pustakniparab_web.Models.Names"%>
<%@page import="java.util.List"%>
<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View ALL Names | Pustak Ni Parab</title>
	<link rel="stylesheet" href="./css/viewAllNames.css"/>
	<script>
	    function pageLoaded() {
		document.getElementById("loader").style.display = "none";
	    }
	</script>
    </head>
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

        <div id="allNameCards" align="center" style="margin-top: 10%;">
	    <div id="noNamesDiv" style="margin-top: 1%; margin-bottom: 5%; color: red; font-size: x-large; font-weight: bolder;
		 word-wrap: break-word; float: left; left: 50%; position: relative; transform: translateX(-50%);"

		 <%List<Names> namesList = null;
		     try {
			 namesList = (List<Names>) request.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.ALL_NAMES_FOR_HTML);
		     } catch (Exception ex) {
		     }
		     if (namesList == null || namesList.isEmpty()) {
			 out.println(">" + Constants.ERRORS.NO_NAMES + "</div>");
		     } else {
			 out.println(" hidden>" + Constants.ERRORS.NO_NAMES + "</div>");
			 for (Names name : namesList) {
		 %>

		 <jsp:include page="./genericContent/nameCard.jsp" flush="true">
		     <jsp:param name="name_id" value="<%=String.valueOf(name.getSer_no())%>"/>
		     <jsp:param name="issuer_name" value="<%=name.returnFullName()%>"/>
		     <jsp:param name="issuer_address" value="<%=name.returnFullAddress()%>"/>
		     <jsp:param name="issuer_contact" value="<%=name.getContact()%>"/>
		 </jsp:include>

		 <%
			 }
		     }
		 %>
		 <%--<jsp:include page="./genericContent/nameCard.jsp" flush="true"></jsp:include>--%>

		 </div>
		</body>
		</html>
