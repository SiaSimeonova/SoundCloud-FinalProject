<%@page import="POJO.*"%>
<%@page import="DAO.*"%>
<%@page import="javax.websocket.Session"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page errorPage="error.jsp"%>
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
<%
			if (session.getAttribute("user") == null) {
				response.sendRedirect("index.jsp");
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
	<div>
		<h1>Result</h1>
		<p>

			<%
			String searchKey = request.getParameter("searchKey");
				List<AudioFile> uploads = new AudioFileDAO().getSearchedTracks(searchKey);
				if (uploads.size() > 0) {
					for (AudioFile song : uploads) {
						request.setAttribute("name", song.getName());
						request.setAttribute("author", song.getAutor());
						request.setAttribute("ganre", song.getCategory());
						request.setAttribute("owner", song.getOwnersName());
						request.setAttribute("picId", new AudioFileDAO().getSongId(song));
			%>

			<%@include file="./template.jsp"%>
			<%
				}
				} else {
					response.sendRedirect("./searchNoResult.jsp");
				}
			%>
		</p>
	</div>

</body>
</html>