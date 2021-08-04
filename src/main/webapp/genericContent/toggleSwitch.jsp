<%-- 
    Document   : toggleSwitch
    Created on : 4 Aug, 2021, 11:55:53 PM
    Author     : Mohak Chavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
	    /* The switch - the box around the slider */
	    .switch {
		position: relative;
		display: inline-block;
		width: 36px;
		height: 16px;
	    }

	    /* Hide default HTML checkbox */
	    .switch input {
		opacity: 0;
		width: 0;
		height: 0;
	    }

	    /* The slider */
	    .slider {
		position: absolute;
		cursor: pointer;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		background-color: #cccccc; /*Switch off back colour*/
		-webkit-transition: .4s;
		transition: .4s;
	    }

	    .slider:before {
		position: absolute;
		content: "";
		height: 20px;
		width: 20px;
		left: 0px;
		bottom: -2px;
		background-color: white; /*toggle switch off colour*/
		-webkit-transition: .4s;
		transition: .4s;
		box-shadow: 0px 1px 2px grey;
	    }

	    input:checked + .slider {
		background-color: #ff8cb6; /*Switch on back colour*/
	    }

	    input:focus + .slider {
		/*box-shadow: 0 0 1px #2196F3; Switch on back colour*/
	    }

	    input:checked + .slider:before {
		-webkit-transform: translateX(16px);
		-ms-transform: translateX(16px);
		transform: translateX(16px);
		background-color: #d81b60; /*toggle switch on colour*/
	    }

	    /* Rounded sliders */
	    .slider.round {
		border-radius: 34px;
	    }

	    .slider.round:before {
		border-radius: 50%;
	    }
	</style>
    </head>
    <body>
        <div>
	    <%=request.getParameter("text")%>
	    <!-- Rounded switch div -->
	    <label class="switch" style="margin-left: 5%;">
		<input type="checkbox">
		<span class="slider round"></span>
	    </label>
	</div>
    </body>
</html>
