<%-- 
    Document   : returns
    Created on : 27 Jan, 2021, 11:05:20 AM
    Author     : Mohak Chavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Returns</title>
	<link rel="stylesheet" href="../css/returns.css"/>
    </head>
    <body>

	<jsp:include page="../genericContent/header.jsp" flush="true">
            <jsp:param name="userName" value="<%=request.getParameter(\"user\")%>"/>
        </jsp:include>

	<div id="returns" style="margin-top: 15%;">

	    <div id="returnContent" align="center" style=" width: 100%;">
		<div class="returnTable" align="right">
		    <table style="margin-top: 1%; padding-right: 4%; border-right: 2px solid #767676;" cellpadding="3%">
			<tr>
			    <th align="right" class="td-th-large">Issue ID:</th>
			    <td align="left" style="margin-left: 2%;">
				<input type="text" name="issue_id" id="issue_id" autofocus />
			    </td>
			</tr>
			<tr>
			    <th colspan="2" class="spanClass">OR</th>
			</tr>
			<tr>
			    <th align="right" class="td-th-large">Book Name:</th>
			    <td align="left" style="margin-left: 2%;">
				<input type="text" name="issue_id" id="issue_id" />
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
				<select name="name_id" id="name_id">
				    <option value="0_0" selected>Select Name ID</option>
				</select>
			    </td>
			</tr>
			<tr>
			    <th colspan="2" class="spanClass">OR</th>
			</tr>
			<tr>
			    <th align="right" class="td-th-large">Issuer Name:</th>
			    <td align="left" style="margin-left: 2%;">
				<input type="text" name="issue_id" id="issue_id" />
			    </td>
			</tr>
		    </table>
		</div>
	    </div>

	    <div style="width: 100%; float: right;"><hr/></div>

	    <div id="returnResults">
		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>
		
		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>
		
		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>

		<jsp:include page="../genericContent/issueCard.jsp" flush="true"></jsp:include>
	    </div>

	    <input type="button" value="Submit" class="submitButton"/>
	</div>
    </body>
</html>
