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
	
	
	<form:form action="${contextPath}/post/reply" method = "get">
	

	

	<table class = "table table-striped" style="width: 50%">
		<tr>
			<td><b>real estate type</b></td>
			<td><b>description</b></td>
			<td><b>address</b></td>
			<td><b>price</b></td>
			<td><b>posted by</b></td>
			<td><b>picture</b></td>
			<td><b>Offer</b></td>
		</tr>
		<c:forEach var="pst" items="${post}">
			<tr>
				<td>${pst.type}</td>
				<td>${pst.description}</td>
				<td>${pst.address}</td>
				<td>${pst.price}</td>
				<td>${pst.user.firstName}</td>
				<td> <img height="150" width="150" src="${pst.filename}" /></td>
				<td><button class="btn btn-primary" type=submit name="reply" value="${pst.id}">send</button></td>
				
			</tr>
		</c:forEach>
	</table>
	</form:form>
</body>
</html>