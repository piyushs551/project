<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Advertisement List</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
	<form:form action="${contextPath}/post/delete" commandName="apply"
		method="post">
	
	

	<a href="${contextPath}/user/">Home</a><br/>

	<table class="table">
		<tr>
			<td><b>real estate type</b></td>
			<td><b>description</b></td>
			<td><b>address</b></td>
			<td><b>price</b></td>
			<td><b>posted by</b></td>
			<td><b>picture</b></td>
		</tr>
		<c:forEach var="pst" items="${post}">
			<tr>
				<td>${pst.type}</td>
				<td>${pst.description}</td>
				<td>${pst.address}</td>
				<td>${pst.price}</td>
				<td>${pst.user.firstName}</td>
				<td> <img height="150" width="150" src="${pst.filename}" /></td>
				<td><button class="form-control" type=submit name="delete" value="post.id">delete</button></td>
			</tr>
		</c:forEach>
	</table>
	</form:form>
</body>
</html>