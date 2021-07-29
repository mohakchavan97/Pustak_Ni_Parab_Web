<%-- 
    Document   : dashboardCard
    Created on : 6 Jun, 2021, 3:56:30 PM
    Author     : Mohak Chavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/dashboardCard.css"/>
	<script type="text/javascript">
	    function changeImg(){
		document.getElementById(<%%>).style.padding="5% 5% 0% 5%";
		    
	    }
	</script>
    </head>
    <body onload="changeImg()">

	<!--<div id="dashboardCard" class="dashCard" style="float: left;" align="center">
	    <p class="bold-white cardHeader" align="center">88</p>
	    <p class="bold-white cardFooter" style="margin: 20% 10% 2% 10%;" align="center">September</p>
	    <p class="bold-white cardFooter" style="margin: 0% 10% 5% 10%;" align="center">Aavak</p>
	</div>-->
	
	<div id="dashboardCard" class="dashCard" style="float: left; width: fit-content !important;" align="center">
	    <img id="img1" style="/*margin: 10%;*//*padding: 5% 5% 0% 5%;*/" align="center" src="<%=request.getParameter("image_link")%>" alt="alt"/>
	</div>

    </body>
</html>
