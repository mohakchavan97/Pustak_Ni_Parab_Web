<%-- 
    Document   : loader
    Created on : 6 Feb, 2021, 6:21:25 PM
    Author     : Mohak Chavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
	    #container{
		--size: 30px;
		width: var(--size);
		height: var(--size);
		position: relative;
	    }

	    #bottom, #top{
		--size: 90%;
		width: var(--size);
		height: var(--size);
		position: absolute;
		top: 0;
		left: 0;
	    }

	    #top{
		z-index: 10;
	    }

	    .bottomLoader, .topLoader{
		border-width: 2px;
		border-style: solid;
		border-radius: 50%;
	    }

	    .bottomLoader{
		border-color: #00857740;
		border-top-color: #008577;
		-webkit-animation: spin 3s linear infinite;
		animation: spin 3s linear infinite;
	    }

	    .topLoader{
		border-color: #00857700;
		border-top-color: #008577;
		border-bottom-color: #008577;
		-webkit-animation: antispin 2s linear infinite;
		animation: antispin 2s linear infinite;
	    }

	    @-webkit-keyframes spin{
		0%{-webkit-transform: rotate(0deg);}
		100%{-webkit-transform: rotate(360deg);}
	    }

	    @keyframes spin{
		0%{-webkit-transform: rotate(0deg);}
		100%{-webkit-transform: rotate(360deg);}
	    }

	    @-webkit-keyframes antispin{
		0%{-webkit-transform: rotate(0deg);}
		100%{-webkit-transform: rotate(-360deg);}
	    }

	    @keyframes antispin{
		0%{-webkit-transform: rotate(0deg);}
		100%{-webkit-transform: rotate(-360deg);}
	    }

	    .loadModal{
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

	    .modalContent{
		background-color: #fefefe;
		padding: 20px;
		border: 2px solid #D81B60;
		position: absolute;
		top: 45%;
		left: 50%;
		transform: translate(-50%,-50%);
		-ms-transform: translate(-50%,-50%);
	    }
	</style>
    </head>
    <body>

	<div id="loadModal" class="loadModal">
	    <table class="modalContent" align="center">
		<tr>
		    <td id="container">
			<div id="bottom" class="bottomLoader"></div>
			<div id="top" class="topLoader"></div>
		    </td>
		    <td style="padding-left: 20px;">Loading...</td>
		</tr>
	    </table>
	</div>
    </body>
</html>
