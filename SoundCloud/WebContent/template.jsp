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
    <script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js"></script>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>
	<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
    
<style>
.btn-circle {
  width: 40px;
  height: 40px;
  line-height: 40px; /* adjust line height to align vertically*/
  padding:0;
  border-radius: 50%;
}
#content{
position: relative;
top: 100px;}
body { 
	width: 100%;
	height:100%;
	font-family: 'Open Sans', sans-serif;
	background: #092756;
	background: -moz-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%),-moz-linear-gradient(top,  rgba(57,173,219,.25) 0%, rgba(42,60,87,.4) 100%), -moz-linear-gradient(-45deg,  #670d10 0%, #092756 100%);
	background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -webkit-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -webkit-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
	background: -o-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -o-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -o-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
	background: -ms-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -ms-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -ms-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
	background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), linear-gradient(to bottom,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), linear-gradient(135deg,  #670d10 0%,#092756 100%);
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#3E1D6D', endColorstr='#092756',GradientType=1 );
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
    <div id="content"class="container-fluid">
	<div class="row">
		<div class="col-md-3">
			 
			<img alt="Bootstrap Image Preview" src="./imageServlet/<%=request.getAttribute("picId")%>" width="300px" height="300px" class="img-thumbnail" >
			<div class="btn-group btn-group-lg btn-group-vertical">
			<audio id="audio_play<%=request.getAttribute("picId")%>">
			<source src="./audioServlet/<%=request.getAttribute("picId")%>" type="audio/mpeg" />
			
			</audio>
			<button onClick="document.getElementById('audio_play<%=request.getAttribute("picId")%>').play(); return false;" type="button" class="btn btn-circle btn-success">
				
				<span class="glyphicon glyphicon-play"></span>
				     
			</button>
			<button onClick="document.getElementById('audio_play<%=request.getAttribute("picId")%>').pause(); return false;" type="button" class="btn btn-circle btn-danger">
				
				<span class="glyphicon glyphicon-pause"></span>
				
				
			</button>		
						
							
						
							
					
							
			</div>
		</div>
		<div class="col-md-4">
			<h1 style="color:white;" >
				Name:  <%=request.getAttribute("name") %>
			</h1>
			<h2 style="color:white;">
				<%=request.getAttribute("author") %>
			</h2>
			<h3 style="color:white;">
			<%=request.getAttribute("ganre") %>
			</h3>
			
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