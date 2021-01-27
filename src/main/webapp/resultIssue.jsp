<%-- 
    Document   : resultIssue
    Created on : 5 Nov, 2020, 3:40:02 PM
    Author     : Mohak Chavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            String data = null;
            try {
                data = (request.getAttribute("data")).toString();
            } catch (Exception e) {
            }
            if (data != null) {
                out.println("<br/><h1>" + data + "</h1>");
            }
        %>
        
    </body>
</html>
