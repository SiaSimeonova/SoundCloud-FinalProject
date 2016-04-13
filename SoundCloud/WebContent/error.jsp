<%@ page isErrorPage="true"%>
<html>
<head>
<title>Show Error Page</title>
</head>
<body>
	<h1>Opps...</h1>

	<p>Sorry, an error occurred.</p>
	<img src="./images/err.jpg" class="img-responsive center-block"
		alt="error img">
	<a href="./index.jsp">Try again!</a>
	<pre>
<%
	exception.getMessage();
%>
</pre>
</body>
</html>