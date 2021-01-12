<%-- 
    Document   : homePage
    Created on : 12 Jan, 2021, 5:24:49 PM
    Author     : Mohak Chavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-color: white;">
        
        <jsp:include page="../genericPages/header.jsp" flush="true">
             <jsp:param name="userName" value="<%=request.getParameter(\"user\")%>"/>
        </jsp:include>
         
        <div id="homeContent" style="margin-top: 15%;">
        </div>

    </body>
</html>
