<%-- 
    Document   : newBooks
    Created on : 25 Jan, 2021, 4:22:03 PM
    Author     : Mohak Chavan
--%>

<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page import="com.mohakchavan.pustakniparab_web.Models.CurrentUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Books</title>
	<script type="text/javascript" src="../javascripts/newBooks.js"></script>
	<link rel="stylesheet" href="../css/newBooks.css"/>
    </head>
    <body style="background-color: white;">

        <%--<jsp:include page="../genericContent/header.jsp" flush="true">--%>
            <%--<jsp:param name="userGivenName" value="<%=CurrentUser.getDisplayName()%>"/>--%>
	    <%--<jsp:param name="userPhoto" value="<%=CurrentUser.getPhotoUrl()%>"/>--%>
        <%--</jsp:include>--%>

	<div id="newBooksContent" align="center" style="margin-top: 15%;">
	    <p style="padding: 5%;">
		<%
		    out.println(session.getId()+"<br/>"+session.getAttribute(Constants.SESSION_KEY_NAMES.IS_CURRENT_USER_VERIFIED));
		    out.println("<br/>"+CurrentUser.getDisplayName());
		%>
	    </p>
            <form id="issue" method="post" >
                <table style="margin-top: 1%;" cellpadding="3%">
                    <tr>
                        <th align="right" class="td-th-large">Person Name:</th>
                        <td align="left" style="margin-left: 2%;">
                            <input type="text" name="book_name" id="book_name" autofocus required />
                        </td>
                    </tr>
		    <tr>
                        <th align="right" class="td-th-large">Total Books:</th>
                        <td align="left" style="margin-left: 2%;">
                            <input type="number" name="book_name" id="book_name" required />
                        </td>
                    </tr>
		    <tr>
                        <th align="right" class="td-th-large" style="padding-top: 10%;">Select Language:</th>
                        <td align="left" style="margin-left: 2%; padding-top: 4%; padding-bottom: 2%;">
                            <select name="book_name" id="book_name">
				<option value="0_0" selected>Select Language</option>
				<option value="Gujarati">Gujarati</option>
				<option value="English">English</option>
				<option value="Hindi">Hindi</option>
				<option value="-2">Other</option>
			    </select>
                        </td>
                    </tr>
		    <tr id="specLang">
                        <th align="right" class="td-th-large">Specify Language:</th>
                        <td align="left" style="margin-left: 2%;">
                            <input type="text" name="book_name" id="book_name" />
                        </td>
                    </tr>
		    <tr>
			<th colspan="2"><hr></th>
		    </tr>
		    <tr>
                        <th align="right" class="td-th-large">Issue Date:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="text" onfocus="ins_date()" name="issue_date" id="issue_date" required />
                        </td>
                    </tr>
		    <tr>
                        <td colspan="2" align="center" style="height: 40px">
                            <input type="submit" value="Submit" style="margin-top: 5%; font-weight: bold;font-variant: all-petite-caps;padding: 2.5% 5%;font-size: large;"/>
                        </td>
                    </tr>
                    <tr>
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
