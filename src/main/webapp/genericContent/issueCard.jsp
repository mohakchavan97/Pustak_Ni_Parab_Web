<%-- 
    Document   : issueCard
    Created on : 26 Jan, 2021, 4:50:41 PM
    Author     : Mohak Chavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/issueCard.css"/>
    </head>
    <body>

	<div id="issueCard" class="issueCard" style="float: left;">
	    <table class="issueTable">
		<tr>
		    <th class="td-th-large" colspan="2" align="left" style="width: 80%;">Issue ID</th>
		    <td rowspan="2" class="td-content-1">
			<label class="container"><input type="checkbox" name="isIssueChecked" id="isIssueChecked">&nbsp;
			    <span class="checkMark"></span></label>
		    </td>
		</tr>
		<tr>
		    <td colspan="2" class="td-content">
			Name of the Book which can be long or short more than this also</td>
		</tr>
		<tr>
		    <td class="td-content" style="font-weight: bold;">22</td>
		    <td class="td-content">First LastName</td>
		    <td class="td-content">23 FEB 2020</td>
		</tr>
	    </table>
	</div>

    </body>
</html>
