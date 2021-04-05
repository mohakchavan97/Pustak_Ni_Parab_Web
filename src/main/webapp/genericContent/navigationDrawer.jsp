<%-- 
    Document   : navigationDrawer
    Created on : 22 Feb, 2021, 11:43:54 AM
    Author     : Mohak Chavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
	    :root{
		--animTime: 0.5s;
	    }

	    .loadDrawerModal{
		display: block;
		position: fixed;
		z-index: 20;
		margin-top: 6%;
		/*padding-left: 0.5%;*/
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
	    }

	    .loadModalAnime{
		animation: drawerAlpha var(--animTime) ease-in-out 1;
		-webkit-animation: drawerAlpha var(--animTime) ease-in-out 1;
		animation-fill-mode: forwards;
		-webkit-animation-fill-mode: forwards;
	    }

	    .drawerModalContent{
		background-color: #fefefe;
		padding: 20px;
		border: 2px solid #D81B60;
		position: absolute;
		overflow: scroll;
	    }

	    .drawerModalAnime{
		animation: drawerOpen var(--animTime) ease-in-out 1;
		-webkit-animation: drawerOpen var(--animTime) ease-in-out 1;
		animation-fill-mode: forwards;
		-webkit-animation-fill-mode: forwards;
	    }

	    .openEffect{
		animation-direction: normal;
		-webkit-animation-direction: normal;
	    }

	    .closeEffect{
		animation-direction: reverse;
		-webkit-animation-direction: reverse;
	    }

	    @-webkit-keyframes drawerAlpha{
		0%{background-color: rgba(0,0,0,0.0);}
		25%{background-color: rgba(0,0,0,0.1);}
		50%{background-color: rgba(0,0,0,0.2);}
		75%{background-color: rgba(0,0,0,0.3);}
		100%{background-color: rgba(0,0,0,0.4);}
	    }

	    @keyframes drawerAlpha{
		0%{background-color: rgba(0,0,0,0.0);}
		25%{background-color: rgba(0,0,0,0.1);}
		50%{background-color: rgba(0,0,0,0.2);}
		75%{background-color: rgba(0,0,0,0.3);}
		100%{background-color: rgba(0,0,0,0.4);}
	    }

	    @-webkit-keyframes drawerOpen{
		0%{left: -200px;}
		75%{left: 20px;}
		100%{left: 0; margin-left: 0.5%;}
	    }

	    @keyframes drawerOpen{
		0%{left: -200px;}
		75%{left: 20px;}
		100%{left: 0; margin-left: 0.5%;}
	    }

	    .drawerHeader{
		font-weight: bold;
		font-size: larger;
		padding-top: 5%;
	    }

	    .drawerImg{
		padding: 2% 2% 2% 15%;
	    }

	    .drawerLabel{
		font-weight: bold;
		font-size: medium;
		padding-left: 2%;
		vertical-align: middle;
		white-space: nowrap;
	    }
	</style>
    </head>
    <body>

	<div class="loadDrawerModal openEffect loadModalAnime" id="loadDrawerModal">
	    <table class="drawerModalContent openEffect drawerModalAnime" id="drawerModalContent">
		<tr>
		    <td class="drawerHeader">Issues</td>
		</tr>
		<tr><td colspan="2"><hr/></td></tr>
		<tr style="cursor: pointer;" onclick="location.href = './AddIssue';">
		    <td class="drawerImg"><img src="./icons/ic_issues.svg" alt="ic_issues" align="center" style="vertical-align: middle;"/></td>
		    <td class="drawerLabel">Add New Issue</td>
		</tr>
		<tr style="cursor: pointer;" onclick="location.href = './ReturnIssue';">
		    <td class="drawerImg"><img src="./icons/ic_returns.svg" alt="ic_returns" align="center" style="vertical-align: middle;"/></td>
		    <td class="drawerLabel">Returns</td>
		</tr>
		<tr><td colspan="2"><hr/></td></tr>
		<tr>
		    <td class="drawerHeader">Names</td>
		</tr>
		<tr><td colspan="2"><hr/></td></tr>
		<tr style="cursor: pointer;" onclick="location.href = './addName';">
		    <td class="drawerImg"><img src="./icons/ic_add_name.svg" alt="ic_add_name" align="center" style="vertical-align: middle;"/></td>
		    <td class="drawerLabel">Add New Person</td>
		</tr>
		<tr style="cursor: pointer;" onclick="location.href = './AllNames';">
		    <td class="drawerImg"><img src="./icons/ic_all_names.svg" alt="ic_viewAll" align="center" style="vertical-align: middle;"/></td>
		    <td class="drawerLabel">View All Names</td>
		</tr>
		<tr><td colspan="2"><hr/></td></tr>
		<tr style="cursor: pointer;" onclick="location.href = './newBooks';">
		    <td class="drawerImg"><img src="./icons/ic_add_book_24px.svg" alt="ic_add_book" align="center" style="vertical-align: middle;"/></td>
		    <td class="drawerLabel">Add New Books</td>
		</tr>
	    </table>
	</div>

    </body>
</html>
