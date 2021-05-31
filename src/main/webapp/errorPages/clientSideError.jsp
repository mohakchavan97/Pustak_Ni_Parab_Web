<%-- 
    Document   : clientSideError
    Created on : 31 May, 2021, 6:04:22 PM
    Author     : Mohak Chavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>

	<div id="header" style="position: fixed; top: 0%; left: 0.5%; right: 0.5%; background-color: white;
	     box-shadow: 0px 2px 5px 4px grey; z-index: 10;">
            <table style="width: 100%; background-color: #008577; margin-top: 0.1%;" border="0" align="center">
                <tr>
                    <th style="width: 90%">
                        <h1 align="center" style="color: white;">Pustak Ni Parab</h1>
                    </th>
                </tr>
            </table>
        </div>

	<div id="loader" style="display: none;">
	    <jsp:include page="/genericContent/loader.jsp" flush="true"></jsp:include>
	</div>

	<div align="center" style="margin-top: 13%; color: red;
	     font-size: xxx-large; font-weight: bolder; word-wrap: break-word; padding: 0% 4%;">
	    404. Page Not Found.
	</div>
	
	<div align="center" style="margin-top: 3%; color: red;
	     font-size: x-large; font-weight: bolder; word-wrap: break-word; padding: 0% 4%;">
	    The page you requested was not found. Please check and enter correct URL. If the problem persists, please contact developer.
	</div>
	
    </body>
</html>
