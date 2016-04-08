<%@page import="POJO.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
</style>
</head>
  <body>
	<div>
		<img  id="background" src="images/background.png" >
	</div>
    <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-2">
					<img alt="Bootstrap Image Preview" src="images/profilna.png" class="img-circle" width="240px" height="240px">
				</div>
				<div class="col-md-6">
				<div id="name">
				<% String firstName=((User)session.getAttribute("user")).getFirstName();
							if(firstName==null){
									firstName="";
							}
						
						%>
					<h3>
						<%=firstName %>
					</h3>
					<h3>
						Last Name
					</h3>
					</div>
				</div>
				<div class="col-md-4">
					 
					<button  id="editButton"type="button" class="btn btn-danger">
						Edit Profile
					</button>
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
						<div class="col-md-4">
							<div class="thumbnail">
								<img alt="Bootstrap Thumbnail First" src="http://lorempixel.com/output/people-q-c-600-200-1.jpg">
								<div class="caption">
									<h3>
										Thumbnail label
									</h3>
									<p>
										Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
									</p>
									<p>
										<a class="btn btn-primary" href="#">Action</a> <a class="btn" href="#">Action</a>
									</p>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="thumbnail">
								<img alt="Bootstrap Thumbnail Second" src="http://lorempixel.com/output/city-q-c-600-200-1.jpg">
								<div class="caption">
									<h3>
										Thumbnail label
									</h3>
									<p>
										Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
									</p>
									<p>
										<a class="btn btn-primary" href="#">Action</a> <a class="btn" href="#">Action</a>
									</p>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="thumbnail">
								<img alt="Bootstrap Thumbnail Third" src="http://lorempixel.com/output/sports-q-c-600-200-1.jpg">
								<div class="caption">
									<h3>
										Thumbnail label
									</h3>
									<p>
										Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
									</p>
									<p>
										<a class="btn btn-primary" href="#">Action</a> <a class="btn" href="#">Action</a>
									</p>
								</div>
							</div>
						</div>
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