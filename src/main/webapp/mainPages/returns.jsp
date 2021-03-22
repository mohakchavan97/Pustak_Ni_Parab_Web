<%-- 
    Document   : returns
    Created on : 27 Jan, 2021, 11:05:20 AM
    Author     : Mohak Chavan
--%>

<%@page import="com.mohakchavan.pustakniparab_web.Models.Names"%>
<%@page import="com.mohakchavan.pustakniparab_web.Models.Issues"%>
<%@page import="java.util.List"%>
<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page import="com.mohakchavan.pustakniparab_web.Models.CurrentUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Returns</title>
	<link rel="stylesheet" href="./css/returns.css"/>
	<script type="text/javascript" src="./javascripts/returns.js"></script>
    </head>
    <body onload="assignVar()">

	<jsp:include page="./genericContent/header.jsp" flush="true">
            <jsp:param name="userGivenName" value="<%=CurrentUser.getDisplayName()%>"/>
	    <jsp:param name="userPhoto" value="<%=CurrentUser.getPhotoUrl()%>"/>
        </jsp:include>

	<%
	    Boolean isVerified = (Boolean) session.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_CURRENT_USER_VERIFIED);

	    List<Issues> issuesList = null;
	    try {
		issuesList = (List<Issues>) request.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.ALL_ISSUES_FOR_HTML);
	    } catch (Exception ex) {
	    }
	    List<Names> namesList = null;
	    try {
		namesList = (List<Names>) request.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.ALL_NAMES_FOR_HTML);
	    } catch (Exception ex) {
	    }
	%>


	<div id="loader" style="display: none;">
	    <jsp:include page="./genericContent/loader.jsp" flush="true"/>
	</div>

	<div id="errorDiv" 
	     <%
		 String errorData = "";
		 try {
		     errorData = request.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.HAS_ERROR_WITH_DATA).toString().trim();
		 } catch (Exception ex) {
		     out.print(" hidden ");
		 }
	     %>
	     align="center" style="margin-top: 13%; margin-bottom: -14.8%; color: red;
	     font-size: x-large; font-weight: bolder; word-wrap: break-word;"><%=errorData.toString()%></div>

	<div id="returns" style="margin-top: 15%;">

	    <div id="returnContent" align="center" style=" width: 100%;">
		<div class="returnTable" align="right">
		    <table style="margin-top: 1%; padding-right: 4%; border-right: 2px solid #767676;" cellpadding="3%">
			<tr>
			    <th align="right" class="td-th-large">Issue ID:</th>
			    <td align="left" style="margin-left: 2%;">
				<input type="text" name="<%=Constants.IDS.ISSUE_ID%>" id="<%=Constants.IDS.ISSUE_ID%>"
				       onkeypress="checkPressedCharForIssID(event)" onkeyup="checkWholeValueForIssID()"
				       <%
					   if (!isVerified) {
					       out.print(" disabled ");
					   }
				       %>
				        />
			    </td>
			</tr>
			<tr>
			    <th colspan="2" class="spanClass">OR</th>
			</tr>
			<tr>
			    <th align="right" class="td-th-large">Book Name:</th>
			    <td align="left" style="margin-left: 2%;">
				<input type="text" name="<%=Constants.IDS.BOOK_NAME%>" id="<%=Constants.IDS.BOOK_NAME%>"
				       onkeyup="callBookAPI(value)"
				       <%
					   if (!isVerified) {
					       out.print(" disabled ");
					   }
				       %>
				       />
			    </td>
			</tr>
		    </table>
		</div>
		<div class="returnTable spanClass" style="height: 100%; width: 10%;" align="center">OR</div>
		<div class="returnTable" align="left">
		    <table style="margin-top: 1%; border-left: 2px solid #767676;" cellpadding="3%">
			<tr>
			    <th align="right" class="td-th-large">Select ID:</th>
			    <td align="left" style="margin-left: 2%;">
				<!--onchange='getname([<%=getAllNamesToString(namesList)%>])'-->
				<select name="<%=Constants.IDS.SEL_NAME%>" id="<%=Constants.IDS.SEL_NAME%>"
					onchange="callNameIdAPI()"
					<%
					    if (!isVerified) {
						out.print(" disabled ");
					    }
					%>
					>
				    <%!
					String getAllNamesToString(List<Names> list) {
					    if (!list.isEmpty()) {
						String toReturn = "";
						for (Names names : list) {
						    toReturn += "[\"" + names.getSer_no() + "\",\"" + names.getFirstName() + "\",\""
							    + names.getLastName() + "\",\"" + names.getBlkOrFltNo() + "\",\""
							    + names.getStreetName() + "\",\"" + names.getLocalityOrArea() + "\",\""
							    + names.getContact() + "\"],";
						}
						toReturn = toReturn.substring(0, toReturn.length() - 1);
						return toReturn;
					    }
					    return null;
					}
				    %>
				    <option value="0_0" selected>Select Name ID</option>
				    <%
					for (Names names : namesList) {
					    out.println("<option value=\"" + names.getSer_no() + "\""
						    + ">" + names.getSer_no() + "</option>");
					}
				    %>
				</select>
			    </td>
			</tr>
			<tr>
			    <th colspan="2" class="spanClass">OR</th>
			</tr>
			<tr>
			    <th align="right" class="td-th-large">Issuer Name:</th>
			    <td align="left" style="margin-left: 2%;">
				<input type="text" name="<%=Constants.IDS.ISSUER_NAME%>" id="<%=Constants.IDS.ISSUER_NAME%>"
				       onkeyup="callPersonAPI(value)"
				       <%
					   if (!isVerified) {
					       out.print(" disabled ");
					   }
				       %>
				       />
			    </td>
			</tr>
		    </table>
		</div>
	    </div>

	    <div style="width: 100%; float: right;"><hr/></div>

	    <div id="returnResults">

		<%
		    if (issuesList != null && !issuesList.isEmpty()) {
			for (Issues issue : issuesList) {
		%>

		<jsp:include page="./genericContent/issueCard.jsp" flush="true">
		    <jsp:param name="issue_id" value="<%=String.valueOf(issue.getIssueNo())%>"/>
		    <jsp:param name="book_name" value="<%=issue.getBookName()%>"/>
		    <jsp:param name="name_id" value="<%=issue.getIssuerId()%>"/>
		    <jsp:param name="issuer_name" value="<%=issue.getIssuerName()%>"/>
		    <jsp:param name="issue_date" value="<%=issue.getIssueDate()%>"/>
		</jsp:include>

		<%
			}
		    } else {
			out.println("<div id=\"errorDiv\" align=\"center\" style=\"margin-top: 13%; margin-bottom: -14.8%; color: red;"
				+ "font-size: x-large; font-weight: bolder; word-wrap: break-word;\">" + Constants.ERRORS.NO_ISSUES + "</div>");
		    }
		%>

	    </div>

	    <input type="button" value="Submit" class="submitButton"/>
	</div>
    </body>
</html>
