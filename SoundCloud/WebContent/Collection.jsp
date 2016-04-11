<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="POJO.User"%>
<%@page import="java.util.List"%>
<%@page import="DAO.AudioFileDAO"%>
<%@page import="POJO.AudioFile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Collections</title>
</head>
<body>
<%

List<AudioFile> uploads=new AudioFileDAO().getUploads(((User)session.getAttribute("user")).getUserName());
for(AudioFile song: uploads){
	request.setAttribute("name", song.getName());
	request.setAttribute("author", song.getAutor());
	request.setAttribute("ganre", song.getCategory());
	
	%>
	<%@include file="./template.jsp" %>
<%} %>


</body>
</html>