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
.btn-circle {
  width: 40px;
  height: 40px;
  line-height: 40px; /* adjust line height to align vertically*/
  padding:0;
  border-radius: 50%;
}
</style>
  </head>
  <body>

    <div class="container-fluid">
	<div class="row">
		<div class="col-md-2">
			 
			<img alt="Bootstrap Image Preview" src="http://lorempixel.com/140/140/" class="img-thumbnail">
			<div class="btn-group btn-group-lg btn-group-vertical">
			<button type="button" class="btn btn-circle btn-success">
				
				<span class="glyphicon glyphicon-play"></span>
				
			</button>
			<button type="button" class="btn btn-circle btn-danger">
				
				<span class="glyphicon glyphicon-pause"></span>
				
			</button>
			</div>
		</div>
		<div class="col-md-4">
			<h3>
				<%=request.getAttribute("name") %>
			</h3>
			<h4>
				<%=request.getAttribute("author") %>
			</h4>
			<p>
			<%=request.getAttribute("ganre") %>
			</p>
		</div>
		<div class="col-md-4">
		</div>
	</div>
	<hr>
</div>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>