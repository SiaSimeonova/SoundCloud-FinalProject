<%@page import="java.util.ArrayList"%>
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
<%@ page errorPage="error.jsp" %>
<html>
<style>
#title{
position: relative;
top: 80px;
left: 50%;}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
			if (session.getAttribute("user") == null) {
				response.sendRedirect("index.jsp");
				return;
			}
		%>
<%@include file="./header.jsp" %>
<h1 id="title" style="color:white;" >Uploads</h1>
<%

List<Integer> uploads=((User)session.getAttribute("user")).getUploadsIDs();


for(Integer id: uploads){
	AudioFile song=new AudioFileDAO().getSongById(id);
	request.setAttribute("name", song.getName());
	request.setAttribute("author", song.getAutor());
	request.setAttribute("ganre", song.getCategory());
	request.setAttribute("likes", song.getLikes());
	request.setAttribute("id", song.getId());
	System.err.println(song.getId());

	request.setAttribute("picId", song.getId());
	//System.out.println(request.getAttribute("picId"));
	%>
	<%@include file="./template.jsp" %>
<%} %>


</body>
</html>