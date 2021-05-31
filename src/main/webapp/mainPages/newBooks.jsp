<%-- 
    Document   : newBooks
    Created on : 25 Jan, 2021, 4:22:03 PM
    Author     : Mohak Chavan
--%>

<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.SessionHelper"%>
<%@page import="java.util.Map"%>
<%@page import="com.mohakchavan.pustakniparab_web.Models.NewBooks"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Books | Pustak Ni Parab</title>
	<script type="text/javascript" src="./javascripts/newBooks.js"></script>
	<link rel="stylesheet" href="./css/newBooks.css"/>
    </head>

    <%
	Map sessionMap = SessionHelper.checkSessionAndGetCurrentUser(request);
	if (!sessionMap.containsKey(Constants.ATTRIBUTE_KEY_NAMES.IS_SESSION_VALID) || !((Boolean) sessionMap.get(Constants.ATTRIBUTE_KEY_NAMES.IS_SESSION_VALID))) {
	    request.getRequestDispatcher(Constants.PATHS.JSP.LOGIN).forward(request, response);
	}
    %>

    <body style="background-color: white;" onload="pageLoaded();">

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

	<div id="newBooksContent" align="center" style="margin-top: 15%;">
            <form id="newBooks" method="post" onsubmit="return validation();" action="AddBooks">
                <table style="margin-top: 1%;" cellpadding="3%">
                    <tr>
                        <th align="right" class="td-th-large">Person Name:</th>
                        <td align="left" style="margin-left: 2%;">
                            <input type="text" name="<%=Constants.IDS.DONOR_NAME%>" id="<%=Constants.IDS.DONOR_NAME%>" required />
                        </td>
                    </tr>
		    <tr>
                        <th align="right" class="td-th-large">Total Books:</th>
                        <td align="left" style="margin-left: 2%;">
                            <input type="number" name="<%=Constants.IDS.TOTAL_BOOKS%>" id="<%=Constants.IDS.TOTAL_BOOKS%>" required />
                        </td>
                    </tr>
		    <tr>
                        <th align="right" class="td-th-large" style="padding-top: 10%;">Select Language:</th>
                        <td align="left" style="margin-left: 2%; padding-top: 4%; padding-bottom: 2%;">
                            <select name="sel_lang" id="sel_lang" onchange="langChanged();">
				<option value="0_0" selected>Select Language</option>
				<option value="Gujarati">Gujarati</option>
				<option value="English">English</option>
				<option value="Hindi">Hindi</option>
				<option value="-2">Other</option>
			    </select>
                        </td>
                    </tr>
		    <tr id="specLang" hidden>
                        <th align="right" class="td-th-large">Specify Language:</th>
                        <td align="left" style="margin-left: 2%;">
                            <input type="text" name="<%=Constants.IDS.BOOKS_LANGUAGE%>" id="<%=Constants.IDS.BOOKS_LANGUAGE%>" required />
                        </td>
                    </tr>
		    <tr>
			<th colspan="2"><hr></th>
		    </tr>
		    <tr>
                        <th align="right" class="td-th-large">Donation Date:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="hidden" name="<%=Constants.IDS.NEW_BOOK_DATE%>" id="<%=Constants.IDS.NEW_BOOK_DATE%>" required />
			    <select name="newBook_day" id="newBook_day" class="issr" onchange="setDate()">
				<option value="0_0">Date</option>

				<%!
				    String getTwoDigit(int number) {
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

			    <select name="newBook_month" id="newBook_month" class="issr" onchange="setDate()">
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

			    <select name="newBook_year" id="newBook_year" class="issr" onchange="setDate()">
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

	<%	    NewBooks addedBooks = null;
	    try {
		addedBooks = (NewBooks) request.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_TRANSACTION_SUCCESS);
		if (addedBooks != null) {
	%>
	<div id="returnedResult">
	    <jsp:include page="./genericContent/returnResultModal.jsp" flush="true">
		<jsp:param name="result_type" value="<%= Constants.VALUES.newBooks%>"/>
		<jsp:param name="new_book_id" value="<%= String.valueOf(addedBooks.getNewBookId())%>"/>
		<jsp:param name="donor_name"  value="<%=addedBooks.getPersonName()%>"/>
		<jsp:param name="total_books" value="<%= addedBooks.getTotalBooks()%>"/>
		<jsp:param name="books_language" value="<%=addedBooks.getLanguage()%>"/>
	    </jsp:include>
	</div>
	<%
		}
	    } catch (Exception ex) {
	    }
	%>

    </body>
</html>
