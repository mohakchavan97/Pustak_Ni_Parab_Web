<%-- 
    Document   : addNames
    Created on : 25 Jan, 2021, 12:27:06 PM
    Author     : Mohak Chavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Names</title>
	<script type="text/javascript" src="../javascripts/addNames.js"></script>
	<link rel="stylesheet" href="../css/addNames.css"/>
    </head>
    <body style="background-color: white;">

        <jsp:include page="../genericPages/header.jsp" flush="true">
            <jsp:param name="userName" value="<%=request.getParameter(\"user\")%>"/>
        </jsp:include>

	<div id="add_name" align="center" style="margin-top: 15%;">

            <form id="name" method="post">
                <table style="margin-top: 1%;" cellpadding="3%">
<!--                    <tr>
                        <th align="right" style="margin-right: 2%;">Serial No:</th>
                        <td align="left" style="margin-left: 2%;">
                            <input type="text" style="background-color: #cccccc; padding: 2%; text-align: center; font-size: medium;" readonly name="serial_no" value="<%//=(ser_no + 1)%>"/>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2"><hr></th>
                    </tr>-->
                    <tr>
			<th rowspan="2" class="spanClass">Full Name</th>
                        <th align="right" class="td-th-large">First Name:</th>
                        <td align="left" style="margin-left: 2%;">
                            <input type="text" name="first_name" autofocus required/>
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Last Name:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="text" name="last_name"/>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3"><hr></th>
                    </tr>
                    <tr>
			<th rowspan="3" class="spanClass">Address</th>
                        <th align="right" class="td-th-large">Block/Flat No.:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="text" name="blk_flat" />
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Street Name:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="text" name="strt_name" required/>
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Locality/Area:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="text" name="local_area" value="Isanpur" required/>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3"><hr></th>
                    </tr>
                    <tr>
			<td></td>
                        <th align="right" class="td-th-large">Contact No.:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="number" name="cont_no" id="cont_no" required />
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
