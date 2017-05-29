<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>



	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<nav class="navbar navbar-inverse">
  	<div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="${contextPath}/user/userHome">home</a>
    </div>
    
    <a href="${contextPath}/user/logout"><button class="btn btn-danger navbar-btn navbar-right">logout</button></a>
  	</div>
	</nav>


	<h1>Hi, ${user.firstName}</h1>
	
	
	
	<a href="${contextPath}/post/add" >Add Post</a> 
	<br/>
	<a href="${contextPath}/post/specificList" >View my posts</a>
	<br/>
	<a href="${contextPath}/post/viewMessage" >View Messages</a> 
	<br/>
</body>
</html>