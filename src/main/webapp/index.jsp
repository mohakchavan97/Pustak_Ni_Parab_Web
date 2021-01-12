<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!--<meta name="google-signin-client_id" content="172709641516-h64u23dq9sqmjtnue6stlgibir2i19h6.apps.googleusercontent.com">-->        
        <meta name="google-signin-client_id" content="172709641516-rdv19n8cbqpb1u4p3d0tfhp3fqht9dbk.apps.googleusercontent.com">        
        <title>JSP Page</title>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <!--<script type="text/javascript" src="index.js"></script>-->
    </head>
    <body>
        <div id="res">Hello World!2</div>
        <div class="g-signin2" data-onsuccess="onSignIn" data-longtitle="true"></div>
        <script>
            function onSignIn(googleUser) {
                document.getElementById("idToken").value = googleUser.getAuthResponse().id_token;
                document.getElementById("accessToken").value = googleUser.getAuthResponse(true).access_token;
                document.getElementById("userForm").submit();
            }
        </script>
        <div>
            <form action="UserSignedIn" method="post" id="userForm">
                <input type="hidden" id="idToken" name="idToken"/>
                <input type="hidden" id="accessToken" name="accessToken"/>
            </form>
        </div>
    </body>
</html>
