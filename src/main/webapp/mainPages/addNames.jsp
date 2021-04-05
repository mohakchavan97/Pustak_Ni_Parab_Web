<%-- 
    Document   : addNames
    Created on : 25 Jan, 2021, 12:27:06 PM
    Author     : Mohak Chavan
--%>

<%@page import="com.mohakchavan.pustakniparab_web.Models.Names"%>
<%@page import="com.mohakchavan.pustakniparab_web.StaticClasses.Constants"%>
<%@page import="com.mohakchavan.pustakniparab_web.Models.CurrentUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Name | Pustak Ni Parab</title>
	<script type="text/javascript" src="./javascripts/addNames.js"></script>
	<link rel="stylesheet" href="./css/addNames.css"/>
    </head>
    <body style="background-color: white;" onload="pageLoaded();">

	<div id="header">
	    <jsp:include page="./genericContent/header.jsp" flush="true">
		<jsp:param name="userGivenName" value="<%=CurrentUser.getDisplayName()%>"/>
		<jsp:param name="userPhoto" value="<%=CurrentUser.getPhotoUrl()%>"/>
	    </jsp:include>
	</div>

	<div id="loader">
	    <jsp:include page="./genericContent/loader.jsp" flush="true"/>
	</div>

	<div id="errorDiv" 
	     <%
		 String errorData = "";
		 boolean hasError = false;
		 try {
		     errorData = request.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.HAS_ERROR_WITH_DATA).toString().trim();
		     hasError = true;
		 } catch (Exception ex) {
		     hasError = false;
		 }
		 try {
		     errorData += " " + request.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.HAS_TRNSCTN_ERROR_WITH_DATA).toString().trim();
		     hasError = true;
		 } catch (Exception ex) {
		     if (!hasError) {
			 out.print(" hidden ");
		     }
		 }
	     %>
	     align="center" style="margin-top: 13%; margin-bottom: -14.8%; color: red;
	     font-size: x-large; font-weight: bolder; word-wrap: break-word;"><%=errorData.toString()%></div>

	<div id="add_name" align="center" style="margin-top: 15%;">
            <form id="name" method="post" action="NewName" onsubmit="return validation();">
                <table style="margin-top: 1%;" cellpadding="3%">
		    <!--                    <tr>
					    <th align="right" style="margin-right: 2%;">Serial No:</th>
					    <td align="left" style="margin-left: 2%;">
						<input type="text" style="background-color: #cccccc; padding: 2%; text-align: center; font-size: medium;" readonly name="serial_no" value="<%//=(ser_no + 1)%>"/>
					    </td>
					</tr>
					<tr>
					    <th colspan="2"><hr></th>
					</tr>-->
                    <tr>
			<th rowspan="2" class="spanClass">Full Name</th>
                        <th align="right" class="td-th-large">First Name:</th>
                        <td align="left" style="margin-left: 2%;">
                            <input type="text" name="<%=Constants.IDS.ISSUER_FNAME%>" id="<%=Constants.IDS.ISSUER_FNAME%>" required/>
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Last Name:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="text" name="<%=Constants.IDS.ISSUER_LNAME%>" id="<%=Constants.IDS.ISSUER_LNAME%>"/>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3"><hr></th>
                    </tr>
                    <tr>
			<th rowspan="3" class="spanClass">Address</th>
                        <th align="right" class="td-th-large">Block/Flat No.:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="text" name="<%=Constants.IDS.BLK_FLT%>" id="<%=Constants.IDS.BLK_FLT%>" />
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Street Name:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="text" name="<%=Constants.IDS.STRT_NAME%>" id="<%=Constants.IDS.STRT_NAME%>" required/>
                        </td>
                    </tr>
                    <tr>
                        <th align="right" class="td-th-large">Locality/Area:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="text" name="<%=Constants.IDS.LOCAL_AREA%>" id="<%=Constants.IDS.LOCAL_AREA%>" value="Isanpur" required/>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3"><hr></th>
                    </tr>
                    <tr>
			<td></td>
                        <th align="right" class="td-th-large">Contact No.:</th>
                        <td align="left" style="margin-left: 2%">
                            <input type="number" name="<%=Constants.IDS.ISSUER_CONTACT%>" id="<%=Constants.IDS.ISSUER_CONTACT%>" required />
                        </td>
                    </tr>
                    <tr>
			<td></td>
                        <td colspan="2" align="center" style="height: 40px">
                            <input type="submit" value="Submit" style="margin-top: 5%; font-weight: bold; 
				   font-variant: all-petite-caps; padding: 2.5% 5%; font-size: large;"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

	<%
	    Names addedName = null;
	    try {
		addedName = (Names) request.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_TRANSACTION_SUCCESS);
		if (addedName != null) {
	%>
	<div id="returnedResult">
	    <jsp:include page="./genericContent/returnResultModal.jsp" flush="true">
		<jsp:param name="result_type" value="<%=Constants.VALUES.name%>"/>
		<jsp:param name="name_id" value="<%=String.valueOf(addedName.getSer_no())%>"/>
		<jsp:param name="issuer_name" value="<%=addedName.returnFullName()%>"/>
		<jsp:param name="issuer_address" value="<%=addedName.returnFullAddress()%>"/>
		<jsp:param name="issuer_contact" value="<%=addedName.getContact()%>"/>
	    </jsp:include>
	</div>
	<%
		}
	    } catch (Exception ex) {
	    }
	%>

    </body>
</html>
