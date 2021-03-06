<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>SoundBlast</title>

    <meta name="description" content="Source code generated using layoutit.com">
    <meta name="author" content="LayoutIt!">

    <link href="css/bootstrap.min.css" rel="stylesheet">
   <!--   <link href="css/style.css" rel="stylesheet">-->
	<style>
 
#search_form {
    position: relative;
    bottom: -500px;
    left: 35%;
    
   
}
img {
    position: absolute;
	
    left: 210px;
    top: 0px;
    z-index: -1;
	width:1500px; /* you can use % */
    height: auto;
}
#keyword{

	border: 1px solid #848484;
	-webkit-border-radius: 30px;
	-moz-border-radius: 30px;
	border-radius: 30px;
	outline: 0;
	
	padding-left: 10px;
	padding-right: 10px;

	height:35px;
	font-size:20px;
	width: 400px;
}
#subscribe_submit{
	height:35px;
	font-family: 'Pacifico', cursive;
	font-size: 20px;
	color: #FFF;
	text-decoration: none;
		
}
.neon {
    color: #D0F8FF;
	font-size: 75px;
    text-shadow: 0 0 5px #A5F1FF, 0 0 10px #A5F1FF,
             0 0 20px #A5F1FF, 0 0 30px #A5F1FF,
             0 0 40px #A5F1FF;
			 
}



#title {
    position: absolute;
    top: 400px;
   left: 800px;
	
   
}
.btn-group {
    position: absolute;
    top: 0px;
   left: 220px;
	
   
}
/* Basic background image styling. */
.background-image {
	background-position: center center;
	background-repeat: no-repeat;
	background-size: cover;
}

/* Styles for the alternating / transition effect. */
.toggle-image {
 	position: absolute;
	width: 1500px;
	top: 0;
	left: 210px;
 	height: 800px;
	transition: opacity 1s ease-in-out;
}

/* Styles for the specific images. */
.first-image {
	background-image: url('background.png');	
	z-index: -6;
	opacity: 0;
}

.second-image {
	background-image: url('background1.png');
	z-index: -5;
	opacity: 0;
}

.third-image {
	background-image: url('background2.png');
	z-index: -4;
	opacity: 0;
}
.fourth-image {
	background-image: url('background3.png');	
	z-index: -3;
	opacity: 0;
}

.fifth-image {
	background-image: url('background4.png');
	z-index: -2;
	opacity: 0;
}

.sixth-image {
	background-image: url('background5.png');
	z-index: -1;
	opacity: 0;
}

.first-image.show {
	opacity: 1;
}

.second-image.show {
	opacity: 1;
}

.third-image.show {
	opacity: 1;
}
.fourth-image.show {
	opacity: 1;
}

.fifth-image.show {
	opacity: 1;
}

.sixth-image.show {
	opacity: 1;
}


 
	
</style>

  </head>
 
  <body>

    <div class="container-fluid">
	<div class="row">
		
		<div class="btn-group" role="group" aria-label="First group">
			
			<a href="register.jsp" type="button" class="btn btn-danger" >Create Account</a>
			<a href="./signIn.jsp" type="button" class="btn btn-default">Login</a>
			</div>
			<div class="container" id="searchBar">
			<div class="neon" id="title" >
			<div>SOUND<span>BLAST</span></div>
		</div>
			<form id="search_form" action="subscribe" method="post">
                
                <input class="text" placeholder="Search for your favorite tracks "id="keyword" type="text" name="searchWord"  />
                <input class="btn btn-danger" id="subscribe_submit" type="submit" value="Search" />
            </form>
			</div>
			

			<div class="background-image toggle-image first-image show"></div>
			<div class="background-image toggle-image second-image " ></div>
			<div class="background-image toggle-image third-image "></div>
			<div class="background-image toggle-image fourth-image "></div>
			<div class="background-image toggle-image fifth-image "></div>
			<div class="background-image toggle-image sixth-image "></div>
		</div>
	</div>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>   
	<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
  </body>
</html>