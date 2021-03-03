<%-- 
    Document   : issues
    Created on : 12 Jan, 2021, 11:18:58 PM
    Author     : Mohak Chavan
--%>

<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page import="com.mohakchavan.pustakniparab_web.Models.CurrentUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Issues</title>
	<script type="text/javascript" src="./javascripts/issues.js"></script>
	<link rel="stylesheet" href="./css/issues.css"/>
    </head>
    <body style="background-color: white;">

	<div id="header">
	    <%--<jsp:include page="./genericContent/header.jsp" flush="true">--%>
	    <%--<jsp:param name="userGivenName" value="<%=CurrentUser.getDisplayName()%>"/>--%>
	    <%--<jsp:param name="userPhoto" value="<%=CurrentUser.getPhotoUrl()%>"/>--%>
	    <%--</jsp:include>--%>
	</div>


	<%
	    //Boolean isVerified = (Boolean) session.getAttribute(Constants.SESSION_KEY_NAMES.IS_CURRENT_USER_VERIFIED);
	    Boolean isVerified = true;
	%>

	<div id="issueContent" align="center" style="margin-top: 15%;">
	    <form id="issue" method="post" action="AddIssue" onsubmit="return validation();">
		<table style="margin-top: 1%;" cellpadding="3%">
		    <tr>
			<th rowspan="3" class="spanClass">Book Details</th>
			<th align="right" class="td-th-large">Name:</th>
			<td align="left" style="margin-left: 2%;">
			    <input type="text" name="book_name" id="book_name"
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
                            <input type="number" name="price" id="price"
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
                            <input type="text" name="auth_pub" id="auth_pub"
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
                            <select name="sel_name" onchange="getname()" id="sel_name"
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

                                %>
                                <option value="-2"
                                        <% /*if (id == -2) {
                                                out.print(" selected");
                                            }*/
                                        %>
                                        >Other</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Name:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="text" name="issuer_name"
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
                                   required />
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Address:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="text" name="issuer_add"
                                   <% /*if (getid) {
                                           out.print(" value=\"" + rs.getString(4) + ", " + rs.getString(5) + ", " + rs.getString(6) + "\"");
                                       }*/
				       if (!isVerified) {
					   out.print(" disabled ");
				       }
                                   %>
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Contact No:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="number" name="issuer_cont" id="issuer_cont"
                                   <% /*if (getid) {
                                           out.print(" value=\"" + rs.getString(7) + "\"");
                                       }*/
				       if (!isVerified) {
					   out.print(" disabled ");
				       }
                                   %>
                                   />
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
                            <input type="hidden" name="issue_date" id="issue_date" required />
			    <select name="issue_day" id="issue_day" class="issr" onchange="setDate()">
				<option value="0_0">Date</option>
				
				<%!
				    String getTwoDigit(int number) {
					return String.valueOf(number).length() < 2 ? "0" + String.valueOf(number) : String.valueOf(number);
				    }
				%>
				
				<%
				    Calendar today = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"));
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
				    String months[] = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
				    for (int i = 0; i < months.length; i++) {
					out.print("<option value=\"" + months[i] + "\"");
					if (i == today.get(Calendar.MONTH)) {
					    out.print(" selected ");
					}
					out.println(">" + months[i] + "</option>");
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

    </body>
</html>
