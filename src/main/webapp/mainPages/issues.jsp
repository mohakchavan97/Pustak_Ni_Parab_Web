<%-- 
    Document   : issues
    Created on : 12 Jan, 2021, 11:18:58 PM
    Author     : Mohak Chavan
--%>

<%@page import="com.mohakchavan.pustakniparab_web.Models.Issues"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mohakchavan.pustakniparab_web.Models.Names"%>
<%@page import="java.util.List"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page import="com.mohakchavan.pustakniparab_web.Models.CurrentUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Issue | Pustak Ni Parab</title>
	<script type="text/javascript" src="./javascripts/issues.js"></script>
	<link rel="stylesheet" href="./css/issues.css"/>
    </head>
    <body style="background-color: white;" onload='issuesPageLoaded();'>

	<%
	    Boolean isVerified = (Boolean) session.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_CURRENT_USER_VERIFIED);

	    List<Names> namesList = null;
	    try {
		namesList = (List<Names>) request.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.ALL_NAMES_FOR_HTML);
	    } catch (Exception ex) {
	    }
	%>

	<div id="header">
	    <jsp:include page="./genericContent/header.jsp" flush="true">
		<jsp:param name="userGivenName" value="<%=CurrentUser.getDisplayName()%>"/>
		<jsp:param name="userPhoto" value="<%=CurrentUser.getPhotoUrl()%>"/>
	    </jsp:include>
	</div>

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

	<div id="issueContent" align="center" style="margin-top: 15%;">
	    <form id="issue" method="post" action="AddIssue" onsubmit="return validation();">
		<table style="margin-top: 1%;" cellpadding="3%">
		    <tr>
			<th rowspan="3" class="spanClass">Book Details</th>
			<th align="right" class="td-th-large">Name:</th>
			<td align="left" style="margin-left: 2%;">
			    <input type="text" name="<%=Constants.IDS.BOOK_NAME%>" id="<%=Constants.IDS.BOOK_NAME%>"
				   <%				       /*if (!(bk_nm.isEmpty())) {
					   out.print(" value=\"" + bk_nm.trim().toString() + "\"");
				       }*/
				       if (!isVerified) {
					   out.print(" disabled ");
				       }
				   %>
				   autofocus required />
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Price:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="number" name="<%=Constants.IDS.PRICE%>" id="<%=Constants.IDS.PRICE%>"
                                   <% /* if (!(pr.isEmpty())) {
                                           out.print(" value=\"" + pr.trim().toString() + "\"");
                                       }*/
				       if (!isVerified) {
					   out.print(" disabled ");
				       }
                                   %>
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Author/Publication:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="text" name="<%=Constants.IDS.AUTHOR_PUBLISHER%>" id="<%=Constants.IDS.AUTHOR_PUBLISHER%>"
                                   <% /*if (!(ap.isEmpty())) {
                                           out.print(" value=\"" + ap.trim().toString() + "\"");
                                       }*/
				       if (!isVerified) {
					   out.print(" disabled ");
				       }
                                   %>
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3"><hr></th>
                    </tr>
                    <tr>
			<th rowspan="4" class="spanClass">Issuer Details</th>
                        <th align="right" class="td-th-large">Select ID:</th>
                        <td align="left" style="margin-left: 2%">
                            <select name="<%=Constants.IDS.SEL_NAME%>" onchange='getname([<%=getAllNamesToString(namesList)%>])'
				    id="<%=Constants.IDS.SEL_NAME%>"
				    <%
					if (!isVerified) {
					    out.print(" disabled ");
					}
				    %>
				    >
                                <option value="0_0"
                                        <% /*if (id == 0) {
						out.print(" selected");
                                            }*/
                                        %>
                                        >Select Name</option>
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
                                <% /*try {
                                        Class.forName("com.mysql.jdbc.Driver");
                                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pustak_ni_parab", "root", "");
                                        Statement st = con.createStatement();

                                        String sql = "SELECT * FROM `names`";
                                        ResultSet rs = st.executeQuery(sql);
                                        if (rs.next()) {
                                            do {
                                                out.print("<option value=\"" + rs.getInt(1) + "\"");
                                                if (id == rs.getInt(1)) {
                                                    out.print(" selected");
                                                    getid = true;
                                                }
                                                out.println(">" + rs.getInt(1) + "</option>");
                                            } while (rs.next());
                                        }*/
				    for (Names names : namesList) {
					out.println("<option value=\"" + names.getSer_no() + "\""
						+ ">" + names.getSer_no() + "</option>");
				    }
                                %>
                                <!--  <option value="-2" -->
				<% /*if (id == -2) {
					out.print(" selected");
				    }*/
				%>
				<!-- Other</option> -->
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Name:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="text" name="<%=Constants.IDS.ISSUER_NAME%>" id="<%=Constants.IDS.ISSUER_NAME%>"
                                   <% /*sql = "SELECT * FROM `names` WHERE `Serial_No` = " + id;
                                       rs = st.executeQuery(sql);
                                       rs.next();

                                       if (getid) {
                                           out.print(" value=\"" + rs.getString(2) + " " + rs.getString(3) + "\"");
                                       }*/
				       if (!isVerified) {
					   out.print(" disabled ");
				       }
                                   %>
                                   required readonly/>
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Address:</th>
                        <td align="left" style="margin-left: 2%">
                            <textarea type="text" name="<%=Constants.IDS.ISSUER_ADDRESS%>" id="<%=Constants.IDS.ISSUER_ADDRESS%>"
				      <% /*if (getid) {
					      out.print(" value=\"" + rs.getString(4) + ", " + rs.getString(5) + ", " + rs.getString(6) + "\"");
					  }*/
					  if (!isVerified) {
					      out.print(" disabled ");
					  }
				      %>
				      readonly></textarea>
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Contact No:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="number" name="<%=Constants.IDS.ISSUER_CONTACT%>" id="<%=Constants.IDS.ISSUER_CONTACT%>"
                                   <% /*if (getid) {
                                           out.print(" value=\"" + rs.getString(7) + "\"");
                                       }*/
				       if (!isVerified) {
					   out.print(" disabled ");
				       }
                                   %>
				   readonly/>
                            <% /*} catch (Exception ex) {
                                    out.println(ex.toString());
                                }*/
                            %>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3"><hr></th>
                    </tr>
                    <tr>
			<td></td>
                        <th align="right" class="td-th-large">Issue Date:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="hidden" name="<%=Constants.IDS.ISSUE_DATE%>" id="<%=Constants.IDS.ISSUE_DATE%>" required />
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
			<td></td>
                        <td colspan="2" align="center" style="height: 40px">
                            <input type="submit" value="Submit" style="margin-top: 5%; font-weight: bold;font-variant: all-petite-caps;padding: 2.5% 5%;font-size: large;"
				   <%				       if (!isVerified) {
					   out.print(" disabled ");
				       }
				   %>
				   />
                        </td>
                    </tr>
                    <tr>
			<td></td>
                        <td colspan="2" align="center" style="color: red;">
                            <% /*String data = null;
                                try {
                                    data = (request.getAttribute("data")).toString();
                                } catch (Exception ex) {
                                }
                                if (data != null) {
                                    out.println(data);
                                }*/
                            %>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

	<%	Issues addedIssue = null;
	    try {
		addedIssue = (Issues) request.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_TRANSACTION_SUCCESS);
		if (addedIssue != null) {
	%>
	<div id="returnedResult">
	    <jsp:include page="./genericContent/returnResultModal.jsp" flush="true">
		<jsp:param name="result_type" value="<%= Constants.VALUES.issue%>"/>
		<jsp:param name="issue_id" value="<%= String.valueOf(addedIssue.getIssueNo())%>"/>
		<jsp:param name="book_name"  value="<%=addedIssue.getBookName()%>"/>
		<jsp:param name="issuer_name" value="<%= addedIssue.getIssuerName()%>"/>
		<jsp:param name="issue_date" value="<%=addedIssue.getIssueDate()%>"/>
	    </jsp:include>
	</div>
	<%
		}
	    } catch (Exception ex) {
	    }
	%>

    </body>
</html>
