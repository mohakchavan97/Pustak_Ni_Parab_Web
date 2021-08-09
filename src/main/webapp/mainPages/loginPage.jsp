<%-- 
    Document   : loginPage
    Created on : 3 Feb, 2021, 12:04:49 PM
    Author     : Mohak Chavan
--%>

<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%--<%=Constants.GOOGLE_CLIENT_ID%> use this after final--%>
	<!--<meta name="google-signin-client_id" content="172709641516-rdv19n8cbqpb1u4p3d0tfhp3fqht9dbk.apps.googleusercontent.com">-->
	<meta name="google-signin-client_id" content="<%=Constants.GOOGLE_CLIENT_ID%>">
	<meta name="google-signin-cookiepolicy" content="single_host_origin">
	<meta name="google-signin-scope" content="profile email">
        <title>Login | Pustak Ni Parab</title>
	<script src="https://apis.google.com/js/platform.js" async defer></script>

	<!-- The core Firebase JS SDK is always required and must be listed first -->
	<script src="https://www.gstatic.com/firebasejs/8.8.1/firebase-app.js"></script>
	<script src="https://www.gstatic.com/firebasejs/8.8.1/firebase-auth.js"></script>

	<script type="text/javascript" src="./javascripts/loginPage.js"></script>
    </head>
    <body onload="loaded();">

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

	<div id="loader">
	    <jsp:include page="./genericContent/loader.jsp" flush="true"></jsp:include>
	</div>

	<div id="loginContent" style="margin-top: 15%;" align="center">

	    <div style="padding-bottom: 1%; font-size: x-large;">To use the Pustak Ni Parab, please sign in with Google by clicking the below button.</div>

	    <div class="g-signin2" data-onsuccess="onSignIn" data-longtitle="true" data-theme="dark" onclick="signInClicked();"></div>
	    
	    <div>
		<form action="LogUserIn" method="post" id="userForm">
		    <input type="hidden" id="idToken" name="idToken"/>
		    <input type="hidden" id="accessToken" name="accessToken"/>
		    <input type="hidden" id="firebaseAuthId" name="firebaseAuthId"/>
		</form>
	    </div>
	</div>

    </body>
</html>
