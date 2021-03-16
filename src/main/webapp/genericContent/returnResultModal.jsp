<%-- 
    Document   : returnResultModal
    Created on : 16 Mar, 2021, 6:03:36 PM
    Author     : Mohak Chavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
	    .loadDrawerModal{
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

	    .drawerModalContent{
		background-color: #fefefe;
		padding: 20px;
		border: 2px solid #D81B60;
		position: absolute;
		top: 45%;
		left: 50%;
		transform: translate(-50%,-50%);
		-ms-transform: translate(-50%,-50%);
	    }

	    .drawerHeader{
		font-weight: bold;
		font-size: larger;
		padding-top: 5%;
		padding-bottom: 2%;
	    }

	    .drawerLabel{
		font-weight: bold;
		font-size: medium;
		padding: 2% 1%;
		vertical-align: middle;
		white-space: nowrap;
	    }
	</style>
    </head>
    <body>

	<div class="loadDrawerModal" id="loadDrawerModal">
	    <table class="drawerModalContent" id="drawerModalContent" align="center">
		<tr>
		    <th colspan="2" class="drawerHeader">Issue was added successfully.</th>
		</tr>
		<tr><td colspan="2"><hr/></td></tr>
		<tr>
		    <td class="drawerLabel" align="right">Issue ID:</td>
		    <td class="drawerLabel" align="left">20202</td>
		</tr>
		<tr>
		    <td class="drawerLabel" align="right">Book Name:</td>
		    <td class="drawerLabel" align="left">physics department of intitute</td>
		</tr>
		<tr>
		    <td class="drawerLabel" align="right">Person Name:</td>
		    <td class="drawerLabel" align="left">mohak yoigta chavan</td>
		</tr>
		<tr>
		    <td class="drawerLabel" align="right">Issue Date:</td>
		    <td class="drawerLabel" align="left">16 Mar 2021</td>
		</tr>
	    </table>
	</div>

    </body>
</html>
