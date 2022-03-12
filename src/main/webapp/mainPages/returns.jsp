<%-- 
    Document   : returns
    Created on : 27 Jan, 2021, 11:05:20 AM
    Author     : Mohak Chavan
--%>

<%@page import="com.mohakchavan.pustakniparab_web.Helpers.SessionHelper"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Calendar"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.mohakchavan.pustakniparab_web.Models.Names"%>
<%@page import="com.mohakchavan.pustakniparab_web.Models.Issues"%>
<%@page import="java.util.List"%>
<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Returns | Pustak Ni Parab</title>
	<link rel="stylesheet" href="./css/returns.css"/>
	<script type="text/javascript" src="./javascripts/returns.js"></script>
    </head>

    <%
	Map sessionMap = new SessionHelper(request).checkSessionAndGetCurrentUser();
	if (!sessionMap.containsKey(Constants.ATTRIBUTE_KEY_NAMES.IS_SESSION_VALID) || !((Boolean) sessionMap.get(Constants.ATTRIBUTE_KEY_NAMES.IS_SESSION_VALID))) {
	    request.getRequestDispatcher(Constants.PATHS.JSP.LOGIN).forward(request, response);
	}
	
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

    <body onload='setIssues(<%=getAllIssuesToString(issuesList)%>)'>

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
				       onkeyup="bookInputChanged(value)"
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
				<select name="<%=Constants.IDS.SEL_NAME%>" id="<%=Constants.IDS.SEL_NAME%>"
					onchange="nameIdChanged()"
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
				       onkeyup="personInputChanged(value)"
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

	    <%!
		String getAllIssuesToString(List<Issues> issues) {
		    if (!issues.isEmpty()) {
			JSONArray jIssueList = new JSONArray();
			for (Issues issue : issues) {
			    JSONObject jIssue = new JSONObject();
			    jIssue.put("issue_id", String.valueOf(issue.getIssueNo()));
			    jIssue.put("book_name", issue.getBookName());
			    jIssue.put("name_id", issue.getIssuerId());
			    jIssue.put("person_name", issue.getIssuerName());
			    jIssue.put("issue_date", issue.getIssueDate());
			    jIssueList.add(jIssue);
			    //				 toReturn += "[\"" + String.valueOf(issue.getIssueNo()) + "\",\"" + issue.getBookName() + "\",\""
			    //					 + issue.getIssuerId() + "\",\"" + issue.getIssuerName() + "\",\""
			    //					 + "\"],";
			}
			//			     toReturn = toReturn.substring(0, toReturn.length() - 1);
			return jIssueList.toString();
		    }
		    return null;
		}
	    %>

	    <div id="returnResults">
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
		     </jsp:include>

		     <%
			     }
			 }
		     %>

		     </div>

		    <input type="button" value="Submit" id="returnsSubmitButton" hidden class="submitButton" onclick="submitReturnIssues();"/>
		</div>

		<div class="loadReturnDateModal" id="loadReturnDateModal" style="display: none;">
		    <table class="returnDateModalContent" id="returnDateModalContent" align="center">
			<tr>
			    <td colspan="2" class="returnDateHeader" align="center">Please Select the Return Date</td>
			</tr>
			<tr><td colspan="2"><hr/></td></tr>
			<tr>
			    <td class="returnDateLabel boldFont" style="width: 30%; margin-right: 1%;" align="right">Select Date</td>
			    <td style="padding: 1%;">
				<select name="issue_day" id="issue_day" class="issr" onchange="setDate()">
				    <option value="0_0">Date</option>

				    <%!				    String getTwoDigit(int number) {
					    return String.valueOf(number).length() < 2 ? "0" + String.valueOf(number) : String.valueOf(number);
					}
				    %>

				    <%
					Calendar today = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIME_ZONE));
					for (int i = 1; i <= 31; i++) {
					    out.print("<option value=\"" + getTwoDigit(i) + "\"");
					    if (i == today.get(Calendar.DAY_OF_MONTH)) {
						out.print(" selected ");
					    }
					    out.println(">" + getTwoDigit(i) + "</option>");
					}
				    %>
				</select>

				<select name="issue_month" id="issue_month" class="issr" onchange="setDate()">
				    <option value="0_0">Month</option>
				    <%
					for (int i = 0; i < Constants.ARRAYS.MONTHS.length; i++) {
					    out.print("<option value=\"" + Constants.ARRAYS.MONTHS[i] + "\"");
					    if (i == today.get(Calendar.MONTH)) {
						out.print(" selected ");
					    }
					    out.println(">" + Constants.ARRAYS.MONTHS[i] + "</option>");
					}
				    %>
				</select>

				<select name="issue_year" id="issue_year" class="issr" onchange="setDate()">
				    <option value="0_0">Year</option>
				    <%
					for (int i = 2000; i <= 2100; i++) {
					    out.print("<option value=\"" + i + "\"");
					    if (i == today.get(Calendar.YEAR)) {
						out.print(" selected ");
					    }
					    out.println(">" + i + "</option>");
					}
				    %>
				</select>
			    </td>
			</tr>
			<tr>
			    <td align="center" colspan="2" style="color: red; font-size: large; font-weight: bolder; word-wrap: break-word;">
				Please select date which is after from
			    </td>
			</tr>
			<tr>
			    <td align="center" colspan="2" style="color: red; font-size: large; font-weight: bolder; word-wrap: break-word;">
				<p id="afterReturnDate" class="" style="margin: 1% auto;"></p>
			    </td>
			</tr>
			<tr>
			    <td colspan="2">
				<input type="button" value="Submit" id="returnDateSubmitBtn" class="returnDateBtn"
				       onclick="submitClicked()"/>

				<input type="button" value="Cancel" id="returnDateCancelBtn" class="returnDateBtn"
				       onclick="cancelClicked()"/>
			    </td>
			</tr>
		    </table>
		</div>

		<div style="display: none;">
		    <form id="hidData" action="ReturnIssue" method="post">
			<input type="text" hidden id="<%=Constants.ATTRIBUTE_KEY_NAMES.IS_REQUEST_TO_RETURN_ISSUE%>"
			       name="<%=Constants.ATTRIBUTE_KEY_NAMES.IS_REQUEST_TO_RETURN_ISSUE%>" 
			       value="<%=Constants.YES%>"/>
			<input type="hidden" name="<%=Constants.IDS.RETURN_DATE%>" id="<%=Constants.IDS.RETURN_DATE%>" required />
			<input type="text" hidden id="issuesToReturn" name="issuesToReturn"/>
		    </form>
		</div>

		<%
		    String returnedIssues = "";
		    try {
			returnedIssues = request.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_TRANSACTION_SUCCESS).toString().trim();
			if (returnedIssues != null) {
		%>
		<div id="returnedResult">
		    <jsp:include page="./genericContent/returnResultModal.jsp" flush="true">
			<jsp:param name="result_type" value="<%=Constants.VALUES.returns%>"/>
			<jsp:param name="returned_issues" value='<%=returnedIssues.split(";")[0]%>'/>
			<jsp:param name="return_date" value='<%=returnedIssues.split(";")[1]%>'/>
		    </jsp:include>
		</div>
		<%
			    //		    request.setAttribute(Constants.IDS.RESULT_TYPE, Constants.VALUES.RETURNS);
			    //		    request.setAttribute(Constants.IDS.RETURNED_ISSUES, returnedIssues.split(";")[0]);
			    //		    request.setAttribute(Constants.IDS.RETURN_DATE, returnedIssues.split(";")[1]);
			    //		    request.getRequestDispatcher("./genericContent/returnResultModal.jsp").include(request, response);

			}
		    } catch (Exception ex) {
		    }
		%>

		</body>
		</html>
