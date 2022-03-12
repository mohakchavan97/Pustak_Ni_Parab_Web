<%-- 
    Document   : header
    Created on : 12 Jan, 2021, 6:58:00 PM
    Author     : Mohak Chavan
--%>

<%@page import="com.mohakchavan.pustakniparab_web.Models.SessionUser"%>
<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
	    function toggleDrawer() {
		var isStyle = document.getElementById("navigationDrawer").style;
		if (isStyle.display === "block")
		    isStyle.display = "none";
		else
		    isStyle.display = "block";
	    }

	    /*function toggleDrawer() {
	     var isStyle = document.getElementById("navigationDrawer").style;
	     var loadModal = document.getElementById("loadDrawerModal");
	     var drawerModal = document.getElementById("drawerModalContent");
	     if (isStyle.display === "block") {
	     if (loadModal.classList.contains("openEffect"))
	     loadModal.classList.remove("openEffect");
	     if (loadModal.classList.contains("loadModalAnime"))
	     loadModal.classList.remove("loadModalAnime");
	     loadModal.classList.add("closeEffect");
	     loadModal.classList.add("loadModalAnime");
	     if (drawerModal.classList.contains("openEffect"))
	     drawerModal.classList.remove("openEffect");
	     if (drawerModal.classList.contains("drawerModalAnime"))
	     drawerModal.classList.remove("drawerModalAnime");
	     drawerModal.classList.add("closeEffect");
	     drawerModal.classList.add("drawerModalAnime");
	     setTimeout(closeDrawer(), 1000);
	     //		    loadModal.addEventListener("animationend", closeDrawer());
	     //		    loadModal.addEventListener("webkitAnimationEnd", closeDrawer());
	     } else {
	     isStyle.display = "block";
	     if (loadModal.classList.contains("closeEffect"))
	     loadModal.classList.remove("closeEffect");
	     if (loadModal.classList.contains("loadModalAnime"))
	     loadModal.classList.remove("loadModalAnime");
	     loadModal.classList.add("openEffect");
	     loadModal.classList.add("loadModalAnime");
	     if (drawerModal.classList.contains("closeEffect"))
	     drawerModal.classList.remove("closeEffect");
	     if (drawerModal.classList.contains("drawerModalAnime"))
	     drawerModal.classList.remove("drawerModalAnime");
	     drawerModal.classList.add("openEffect");
	     drawerModal.classList.add("drawerModalAnime");
	     }
	     }*/

	    function closeDrawer() {
		document.getElementById("navigationDrawer").style.display = "none";
	    }
	</script>
    </head>
    <body>

        <div id="header" style="position: fixed; top: 0%; left: 0.5%; right: 0.5%; background-color: white;
	     box-shadow: 0px 2px 5px 4px grey; z-index: 10;">
            <table style="width: 100%; background-color: #008577; margin-top: 0.1%;" border="0" align="center">
                <tr>
		    <th style="width: 5%;">
			<img src="./icons/ic_menu_24px.svg" alt="ic_menu" style="--size: 50%; width: var(--size); height: var(--size); cursor: pointer;" onclick="toggleDrawer();"/>
		    </th>
                    <th style="width: 85%">
                        <h1 align="center" style="color: white;">Pustak Ni Parab</h1>
                    </th>
		    <%
			session = request.getSession(false);
			if (session != null) {
			    SessionUser currentUser = (SessionUser) session.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.USER_SESSION_DATA);
			    if (currentUser != null && !currentUser.getDisplayName().isEmpty() && currentUser.getDisplayName() != null) {
		    %>
                    <th style="width: 6%;">
                        <h4 align="right" style="color: white;"><%=currentUser.getDisplayName()%></h4>
                    </th>
                    <td style="width: 4%; padding-right: 2%;">
                        <div style="border: 2px solid #ffffff; border-radius: 50%; width: 50px; height: 50px;
			     background-color: white; margin-left: 5%; overflow: hidden;">
                            <!--src="../icons/ic_user.svg"-->
			    <img style="width: 100%; height: 100%; object-fit: cover;"
				 src="<%=currentUser.getPhotoUrl()%>"
				 alt="User"/>
                        </div>
                    </td>
		    <%
			}
		    %>
                </tr>
            </table>
        </div>

	<div id="navigationDrawer" style="display: none;">
	    <jsp:include page="navigationDrawer.jsp" flush="true">
		<jsp:param name="toShowToggle" value="<%=String.valueOf(currentUser.isDeveloper())%>"/>
	</jsp:include>
    </div>

    <%
	if (session.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_CURRENT_USER_VERIFIED) != null
		&& !((Boolean) session.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_CURRENT_USER_VERIFIED))) {
    %>
    <div id="notVerifiedUser" align="center" style="margin-top: 10%; color: red; font-size: x-large; font-weight: bolder; margin-bottom: -11.8%;">
	You are not a verified user. Please verify yourself.
    </div>
    <%
	    }
	}
    %>
</body>
</html>
