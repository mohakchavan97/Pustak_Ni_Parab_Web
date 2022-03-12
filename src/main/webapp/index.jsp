<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!--<meta name="google-signin-client_id" content="172709641516-h64u23dq9sqmjtnue6stlgibir2i19h6.apps.googleusercontent.com">-->        
        <meta name="google-signin-client_id" content="172709641516-rdv19n8cbqpb1u4p3d0tfhp3fqht9dbk.apps.googleusercontent.com">
        <title>JSP Page</title>
        <!--<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>-->
        <!--<script type="text/javascript" src="index.js"></script>-->
    </head>
    <body>
        <div id="res">Hello World!2</div>
        <!--<div class="g-signin2" id="mySignIn" data-onsuccess="onSignIn" data-longtitle="true"></div>-->
        <script>
//	    window.onload = function () {
//		document.location.href = "Home";
//	    };

	    var isSet = false;
	    var onlyDigit = /^[0-9]+$/;
	    var temp2 = document.getElementById("temp2");
	    function onSignIn(googleUser) {
		document.getElementById("idToken").value = googleUser.getAuthResponse().id_token;
		document.getElementById("accessToken").value = googleUser.getAuthResponse(true).access_token;
		document.getElementById("userForm").submit();
	    }
	    function renderButton() {
		gapi.signin2.render('mySignIn',
			{
			    'width': 250,
			    'height': 50,
			    'theme': 'dark',
			});
	    }
	    function charAdded(txxx) {


		var text = document.getElementById("temp");
		var val;
		if (isNaN(txxx) || txxx === undefined) {
		    val = text.value;
		} else {
		    val = text.value + txxx;
		}
		if (onlyDigit.test(val)) {
		    if (!isSet) {
			setTimeout(clicked, 2000);
			isSet = true;
		    }
		} else {
		    var sliced = val.slice(0, -1);
		    text.value = sliced;
		}

//		if (!isSet) {
//		    isSet = true;
//		    setTimeout(clicked, 3000);
//		}
	    }
	    function clicked() {
		var text = document.getElementById("temp").value;
		if (onlyDigit.test(text)) {
		    document.getElementById("temp2").value = document.getElementById("temp").value;
		}
		isSet = false;
	    }
	    function checkVal(e) {
		var codedKey;
		if (window.event) {
		    codedKey = e.keyCode;
		} else if (e.which) {
		    codedKey = e.which;
		}
		charAdded(String.fromCharCode(codedKey));
	    }

	    function issueChecked(issue_id) {
		if (document.getElementById("isIssueChecked_" + issue_id).checked) {
		    console.log(issue_id + " is checked");
		} else {
		    console.log(issue_id + " is unchecked");
		}
	    }

	    function copyToClipboard() {
	    <%
		JSONObject jso = new JSONObject();
		jso.put("key1", "value1");
		jso.put("key2", "value2");
		out.print("var str=" + jso.toString());
	    %>
		navigator.clipboard.writeText(JSON.stringify(str));
		alert("copied");
	    }
        </script>
        <div>
            <form action="Home" method="post" id="userForm">
                <input type="hidden" id="idToken" name="idToken"/>
                <input type="hidden" id="accessToken" name="accessToken"/>
		<input type="text" id="temp" onkeypress="checkVal(event)" onkeyup="charAdded()"/>
		<input readonly type="text" id="temp2"/>
            </form>
        </div>

	<div>
	    <input type="button" value="text" onclick="copyToClipboard()"/>
	    <input type="text"/>
	</div>

	<div>
	    <%--<jsp:include page="./genericContent/issueCard.jsp" flush="true">
		<jsp:param name="issue_id" value="3312"/>
		<jsp:param name="book_name" value="Book Name"/>
		<jsp:param name="name_id" value="23"/>
		<jsp:param name="issuer_name" value="mohak chavan"/>
		<jsp:param name="issue_date" value="01 APR 2020"/>
	    </jsp:include>

	    <jsp:include page="./genericContent/issueCard.jsp" flush="true">
		<jsp:param name="issue_id" value="3313"/>
		<jsp:param name="book_name" value="Book Name"/>
		<jsp:param name="name_id" value="23"/>
		<jsp:param name="issuer_name" value="mohak chavan"/>
		<jsp:param name="issue_date" value="01 APR 2020"/>
	    </jsp:include>--%>
	</div>
    </body>
</html>
