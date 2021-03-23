<%-- 
    Document   : issueCard
    Created on : 26 Jan, 2021, 4:50:41 PM
    Author     : Mohak Chavan
--%>

<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/issueCard.css"/>
    </head>
    <body>

	<div id="issueCard_<%=request.getParameter(Constants.IDS.ISSUE_ID)%>" class="issueCard" style="float: left;">
	    <table class="issueTable">
		<tr>
		    <th class="td-th-large" colspan="2" align="left" style="width: 80%;"><%=request.getParameter(Constants.IDS.ISSUE_ID)%></th>
		    <td rowspan="2" class="td-content-1">
			<label class="container"><input type="checkbox" name="isIssueChecked" id="isIssueChecked">&nbsp;
			    <span class="checkMark"></span></label>
		    </td>
		</tr>
		<tr>
		    <td colspan="2" class="td-content">
			<%=request.getParameter(Constants.IDS.BOOK_NAME)%></td>
		</tr>
		<tr>
		    <td class="td-content" style="font-weight: bold;"><%=request.getParameter(Constants.IDS.NAME_ID)%></td>
		    <td class="td-content"><%=request.getParameter(Constants.IDS.ISSUER_NAME)%></td>
		    <td class="td-content"><%=request.getParameter(Constants.IDS.ISSUE_DATE)%></td>
		</tr>
	    </table>
	</div>

    </body>
</html>
