<%@page import="POJO.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type">
<title>Welcome</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
#search_form{

position: relative;
 top: -40px;
 left: 70%;
}

#keyword {
	border: 1px solid #848484;
	-webkit-border-radius: 30px;
	-moz-border-radius: 30px;
	border-radius: 30px;
	outline: 0;
	height: 25px;
	width: 275px;
	padding-left: 10px;
	padding-right: 10px;
}
</style>
</head>
<body>
	<%
		if (session.getAttribute("user") == null) {

			out.print("Ne hitruvai be mishkoo..");
			return;
		}
	%>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">

			<img src="images/logo.jpg" class="img-rounded" alt="Cinque Terre"
				width="50" height="50"> <a class="navbar-brand" href="#">SoundBlast</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">Home</a></li>
			<li><a href="#">Collection</a></li>

			<form id="search_form" action="subscribe" method="post">

				<input class="text" placeholder="Search for your favorite tracks "
					id="keyword" type="text" name="searchWord" /> <input
					class="btn btn-danger" id="subscribe_submit" type="submit"
					value="Search" />
			</form>



		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#">
					Upload </a></li>
			<li><a href="#"><span class="glyphicon glyphicon-user"></span>
					Hello <%=((User)session.getAttribute("user")).getUserName()%></a></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Options <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="./profile.jsp">Profile</a></li>
					
				</ul></li>


			<li><a href="Logout"><span 
					class="glyphicon glyphicon-log-in"></span> Logout </a></li>
		</ul>

	</div>

	</nav>
</body>
</html>