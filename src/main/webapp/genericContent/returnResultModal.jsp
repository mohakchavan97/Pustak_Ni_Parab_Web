<%-- 
    Document   : returnResultModal
    Created on : 16 Mar, 2021, 6:03:36 PM
    Author     : Mohak Chavan
--%>

<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
	    function closeResultModal() {
		document.getElementById("loadResultModal").style.display = "none";
	    }
	</script>
        <style type="text/css">
	    @import "../css/base.css";

	    .loadResultModal{
		display: block;
		position: fixed;
		z-index: 20;
		padding-top: 100px;
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		overflow: visible;
		background-color: rgba(0,0,0,0.4);
	    }

	    .resultModalContent{
		background-color: #fefefe;
		padding: 20px;
		border: 2px solid #D81B60;
		position: absolute;
		top: 45%;
		left: 50%;
		width: 30%;
		transform: translate(-50%,-50%);
		-ms-transform: translate(-50%,-50%);
	    }

	    .resultHeader{
		font-weight: bold;
		font-size: larger;
		padding-top: 5%;
		padding-bottom: 2%;
	    }

	    .resultLabel{
		font-weight: bold;
		font-size: medium;
		padding: 2% 1%;
		vertical-align: top;
	    }
	</style>
    </head>
    <body>

	<div class="loadResultModal" id="loadResultModal">
	    <table class="resultModalContent" id="resultModalContent" align="center">
		<tr>
		    <th colspan="2" class="resultHeader">Issue was added successfully.</th>
		</tr>
		<tr><td colspan="2"><hr/></td></tr>
		<tr>
		    <td class="resultLabel" align="right" style="width:35%;">Issue ID:</td>
		    <td class="resultLabel" align="left">20202</td>
		    <!--<td class="resultLabel" align="left"><% //request.getParameter(Constants.IDS.ISSUE_ID)%></td>-->
		</tr>
		<tr>
		    <td class="resultLabel" align="right" style="width:35%;">Book Name:</td>
		    <td class="resultLabel" align="left">physics department of intitute</td>
		    <!--<td class="resultLabel" align="left"><% //request.getParameter(Constants.IDS.BOOK_NAME)%></td>-->
		</tr>
		<tr>
		    <td class="resultLabel" align="right" style="width:35%;">Person Name:</td>
		    <td class="resultLabel" align="left">mohak yoigta chavan</td>
		    <!--<td class="resultLabel" align="left"><% //request.getParameter(Constants.IDS.ISSUER_NAME)%></td>-->
		</tr>
		<tr>
		    <td class="resultLabel" align="right" style="width:35%;">Issue Date:</td>
		    <td class="resultLabel" align="left">16 Mar 2021</td>
		    <!--<td class="resultLabel" align="left"><% //request.getParameter(Constants.IDS.ISSUE_DATE)%></td>-->
		</tr>
		<tr>
		    <td colspan="2" align="center" style="padding-top: 5%;">
			<input type="button" value="OK" onclick="closeResultModal();" style="padding: 1% 10%;"/>
		    </td>
		</tr>
	    </table>
	</div>

    </body>
</html>
