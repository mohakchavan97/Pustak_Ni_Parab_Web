<%-- 
    Document   : nameCard
    Created on : 25 Jan, 2021, 5:49:38 PM
    Author     : Mohak Chavan
--%>

<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/nameCard.css"/>
    </head>
    <body>
	<div id="nameCard" style="float: left;">
	    <table class="cardTable">
		<tr>
		    <th rowspan="3" class="spanClass"><%=request.getParameter(Constants.IDS.NAME_ID)%></th>
		    <td align="left" class="td-content"><%=request.getParameter(Constants.IDS.ISSUER_NAME)%></td>
		</tr>
		<tr>
		    <td align="left" class="td-content"><%=request.getParameter(Constants.IDS.ISSUER_ADDRESS)%></td>
		</tr>
		<tr>
		    <td align="left" class="td-content" style="border-bottom: 0px;"><%=request.getParameter(Constants.IDS.ISSUER_CONTACT).length() > 0 ? request.getParameter(Constants.IDS.ISSUER_CONTACT) : "-"%></td>
		</tr>
	    </table>
	</div>

    </body>
</html>
