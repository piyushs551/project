<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Advertisement List</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<nav class="navbar navbar-inverse">
  	<div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="${contextPath}/user/customerHome">home</a>
    </div>
    
    <a href="${contextPath}/user/logout"><button class="btn btn-danger navbar-btn navbar-right">logout</button></a>
  	</div>
	</nav>
	
	<form action="${contextPath}/post/reply" method="post">
	
	

	<a href="${contextPath}/user/">Home</a><br/>

	<table class="table table-stripped">
		<tr>
			<td><b>real estate type</b></td> 
			<td>${post.type}</td>
			</tr>
			<tr>
			<td><b>description</b></td>
			<td>${post.description}</td>
			</tr>
			
			<tr>
			<td><b>address</b></td>
			<td>${post.address}</td>
			</tr>
			
			<tr>
			<td><b>price</b></td>
			<td>${post.price}</td>
			</tr>
			
			<tr>
			<td><b>posted by</b></td>
			<td>${post.user.firstName}</td>
			</tr>
			
			<tr>
			<td><b>Message</b></td>
			<td><input class="form-control" type = "text" name = "message"/></td>
		</tr>
		<tr>
				<td><button class="btn btn-primary" type=submit name="send" value="${post.id}">send</button></td>
				
			</tr>
		
	</table>
	</form>
</body>
</html>