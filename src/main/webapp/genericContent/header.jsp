<%-- 
    Document   : header
    Created on : 12 Jan, 2021, 6:58:00 PM
    Author     : Mohak Chavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!--    <head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>JSP Page</title>
	</head>-->
    <body>

        <div id="header" style="position: fixed; top: 0%; left: 0.5%; right: 0.5%; background-color: white;
	     box-shadow: 0px 2px 5px 4px grey; z-index: 10;">
            <table style="width: 100%; background-color: #008577; margin-top: 0.1%;" border="0" align="center">
                <tr>
                    <th style="width: 90%">
                        <h1 align="center" style="color: white;">Pustak Ni Parab</h1>
                    </th>
                    <th style="width: 6%;">
                        <h4 align="right" style="color: white;"><%=request.getParameter("userName")%></h4>
                    </th>
                    <td style="width: 4%; padding-right: 2%;">
                        <div style="border: 2px solid #ffffff; border-radius: 50%;
                             width: 40px; height: 40px; padding: 3px; background-color: white; margin-left: 5%;">
                            <img width="18" height="18" src="../icons/ic_user.svg" alt="User"/>
                        </div>
                    </td>
                </tr>
            </table>
        </div>

    </body>
</html>
