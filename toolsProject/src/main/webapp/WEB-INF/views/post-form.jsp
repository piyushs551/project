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
 	
<title>Add User Form</title>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<nav class="navbar navbar-inverse">
  	<div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="${contextPath}/user/userHome">home</a>
    </div>
    
    <a href="${contextPath}/user/logout"><button class="btn btn-danger navbar-btn navbar-right">logout</button></a>
  	</div>
	</nav>
	<h2>Add a POST</h2>

	<form:form action="${contextPath}/post/add" commandName="post"
		method="post" enctype="multipart/form-data">

		<table class="table table-striped">
			
			<tr>
				<td>Posted By</td>
				<td>${sessionScope.user.userName}</td>
				<td><form:hidden path="postedBy"
						value="${sessionScope.user.personId}" /></td>
			</tr>
		
			<tr>
				<td>type:</td>
				<td><form:input class="form-control" path="type" size="30" required="required" />
					<font color="red"><form:errors path="type" /></font></td>
			</tr>

			<tr>
				<td>description:</td>
				<td><form:input class="form-control" path="description" size="30" required="required" />
					<font color="red"><form:errors path="description" /></font></td>
			</tr>


			<tr>
				<td>address:</td>
				<td><form:input class="form-control" path="address" size="30" required="required" />
					<font color="red"><form:errors path="address" /></font></td>
			</tr>

			<tr>
				<td>price:</td>
				<td><form:input class="form-control" path="price" size="30"
						required="required" /> <font color="red"><form:errors
							path="price" /></font></td>
			</tr>
			
			<tr>
			
			<td>Create Album:</td>
			<td><input class="form-control" type="text" name="filename" /><br/></td>
			</tr>
			<tr>
			
			<td>Select photo:</td>
			<td><form:input path="photo" type="file" name="photo"/>
			<font color="red"><form:errors
							path="photo" /></font></td>
			</tr>
			
			
			<tr>
				<td colspan="2"><input type="submit" value="postAdd" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>