<%@page import="javax.websocket.Session"%>
<%@page import="java.sql.*"%>
<%@page import="POJO.*"%>
<%@page import="DAO.*"%>
<%@page import="java.util.*"%>
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
#search_form {
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

	<div class="outer-wrapper">
		<div style="overflow: auto;">
			<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">

					<img src="images/logo.jpg" class="img-rounded" alt="Cinque Terre"
						width="50" height="50"> <a class="navbar-brand" href="#">SoundBlast</a>
				</div>

				<%
					if (session.getAttribute("user") == null) {
						response.sendRedirect("index.jsp");
						return;
					}
				%>

				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="./Collection.jsp">Collection</a></li>

					<form id="search_form" action="./Search" method="post">

						<input class="text" placeholder="Search for your favorite tracks "
							id="keyword" type="text" name="searchKey" /> <input
							class="btn btn-danger" id="subscribe_submit" type="submit"
							value="Search" />
					</form>



				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="upload.jsp"> Upload </a></li>
					<li><a href="#"><span class="glyphicon glyphicon-user"></span>
							Hello <%=((User) session.getAttribute("user")).getUserName()%></a></li>
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
		</div>
		<div class="inner-wrapper" style="height: 45px;"></div>
		<div class="inner-wrapper"
			style="clear: both; border 1px solid black; background-color: #F4F6F9">
			<ul class="w3-ul w3-card-4">
				<%
					List<AudioFile> files = new AudioFileDAO().searchAudio(request.getParameter("searchKey"));
					for (AudioFile file : files) {
				%>
				<li class="w3-padding-16"><img src="img/img1.jpg"
					class="w3-left w3-circle" style="width: 60px"> <span
					class="w3-xlarge" style="margin-left: 10px;"> <a
						href=<%out.print("name=" + file.getName());%>> <%
 	out.print(file.getName());
 %>
					</a>
				</span> <br> <span>Name: <%
 	out.print(file.getName());
 %></span> <br> <span>Email: <%
 	out.print(file.getName());
 %></span></li>
				<%
					}
				%>
			</ul>
		</div>

	</div>

</body>
</html>