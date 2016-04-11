<%@page import="POJO.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page errorPage="error.jsp" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Bootstrap 3, from LayoutIt!</title>

    <meta name="description" content="Source code generated using layoutit.com">
    <meta name="author" content="LayoutIt!">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
<style>
#editButton{
position: relative;
left: 450px;
top:200px;
}
#background{
    position: absolute;
	
    top: 0px;
    z-index: -1;
    height: auto;
}
#name{
color: white;
}
#profile{
position: relative;
top:80px;
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
	<div>
		<img  id="background" src="images/background.png" >
	</div>
	<%@include file="header.jsp" %>
    <div class="container-fluid" id="profile">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-2">
				<%String picPath=((User)(session.getAttribute("user"))).getPicPath();
			
				if(picPath==null){
					picPath="defProfile.jpg";
				}
				else{
					picPath="./profilePicServlet";
				}
				%>
					<img  src="./profilePicServlet" class="img-circle" width="240px" height="240px">
				</div>
				<div class="col-md-6">
				<div id="name">
				<% String firstName=((User)session.getAttribute("user")).getFirstName();
							if(firstName.equals("empty")){
									firstName="";
							}
						
						%>
					<h3>
						<%=firstName %>
					</h3>
					<% String lastName=((User)session.getAttribute("user")).getSurname();
							if(lastName.equals("empty")){
									lastName="";
							}
						
						%>
					<h3>
						<%=lastName %>
					</h3>
					</div>
				</div>
				<div class="col-md-4">
					 <a href="editprofile.jsp" id="editButton" class="btn btn-danger role="button">Edit Profile</a>
					
				</div>
			</div>
			<div class="row">
				<div class="col-md-8">
					<ul class="nav nav-tabs">
						<li class="active">
							<a href="#">All</a>
						</li>
						<li>
							<a href="#">Tracks</a>
						</li>
						<li >
							<a href="#">Playlists</a>
						</li>
						
					</ul>
				</div>
				<div class="col-md-4">
					<div class="row">
						
					
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>