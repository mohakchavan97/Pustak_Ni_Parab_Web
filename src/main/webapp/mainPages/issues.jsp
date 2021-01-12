<%-- 
    Document   : issues
    Created on : 12 Jan, 2021, 11:18:58 PM
    Author     : Mohak Chavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Issues</title>
	<script type="text/javascript" src="../javascripts/issues.js"></script>
	<link rel="stylesheet" href="../css/issues.css"/>
    </head>
    <body style="background-color: white;">

        <jsp:include page="../genericPages/header.jsp" flush="true">
            <jsp:param name="userName" value="<%=request.getParameter(\"user\")%>"/>
        </jsp:include>

        <div id="issueContent" align="center" style="margin-top: 15%;">
            <form id="issue" method="post" >
                <table style="margin-top: 1%;" cellpadding="3%">
                    <tr>
			<th rowspan="3" class="spanClass">Book Details</th>
                        <th align="right" class="td-th-large">Name:</th>
                        <td align="left" style="margin-left: 2%;">
                            <input type="text" name="book_name" id="book_name"
                                   <%
                                       /*if (!(bk_nm.isEmpty())) {
                                           out.print(" value=\"" + bk_nm.trim().toString() + "\"");
                                       }*/
                                   %>
                                   autofocus required />
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Price:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="number" name="price" id="price"
                                   <%
                                       /* if (!(pr.isEmpty())) {
                                           out.print(" value=\"" + pr.trim().toString() + "\"");
                                       }*/
                                   %>
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Author/Publication:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="text" name="auth_pub" id="auth_pub"
                                   <%
                                       /*if (!(ap.isEmpty())) {
                                           out.print(" value=\"" + ap.trim().toString() + "\"");
                                       }*/
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
                            <select name="sel_name" onchange="getname()" id="sel_name">
                                <option value="0_0"
                                        <%
					    /*if (id == 0) {
						out.print(" selected");
                                            }*/
                                        %>
                                        >Select Name</option>
                                <%
                                    /*try {
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
                                        <%
					    /*if (id == -2) {
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
                                   <%
                                       /*sql = "SELECT * FROM `names` WHERE `Serial_No` = " + id;
                                       rs = st.executeQuery(sql);
                                       rs.next();

                                       if (getid) {
                                           out.print(" value=\"" + rs.getString(2) + " " + rs.getString(3) + "\"");
                                       }*/
                                   %>
                                   required />
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Address:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="text" name="issuer_add"
                                   <%
                                       /*if (getid) {
                                           out.print(" value=\"" + rs.getString(4) + ", " + rs.getString(5) + ", " + rs.getString(6) + "\"");
                                       }*/
                                   %>
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Contact No:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="number" name="issuer_cont" id="issuer_cont"
                                   <%
                                       /*if (getid) {
                                           out.print(" value=\"" + rs.getString(7) + "\"");
                                       }*/
                                   %>
                                   />
                            <%
                                /*} catch (Exception ex) {
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
                            <input type="text" onfocus="ins_date()" name="issue_date" id="issue_date" required />
                        </td>
                    </tr>
                    <tr>
			<td></td>
                        <td colspan="2" align="center" style="height: 40px">
                            <input type="submit" value="Submit" style="margin-top: 5%; font-weight: bold;font-variant: all-petite-caps;padding: 2.5% 5%;font-size: large;"/>
                        </td>
                    </tr>
                    <tr>
			<td></td>
                        <td colspan="2" align="center" style="color: red;">
                            <%
				/*String data = null;
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
