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
      <a class="navbar-brand" href="${contextPath}">home</a>
    </div>
    
    <a href="${contextPath}"><button class="btn btn-success navbar-btn navbar-right">Login</button></a>
  	</div>
	</nav>
	
	
	<br/>

	<h2>Register a New User</h2>

	<form:form action="${contextPath}/user/register" commandName="user"
		method="post">

		<table class="table" style="width: 50%">
			<tr>
				<td>First Name:</td>
				<td><form:input class="form-control" path="firstName" size="30" />
					<font color="red"><form:errors path="firstName" /></font></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input class="form-control" path="lastName" size="30" />
					<font color="red"><form:errors path="lastName" /></font></td>
			</tr>


			<tr>
				<td>User Name:</td>
				<td><form:input class="form-control" path="userName" size="30" />
					<font color="red"><form:errors path="userName" /></font></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password class="form-control" path="password" size="30"
						/> <font color="red"><form:errors
							path="password" /></font></td>
			</tr>

			<tr>
				<td>Email Id:</td>
				<td><form:input class="form-control" path="email" size="30"
						type="email" /> <font color="red"><form:errors
							path="email" /></font></td>
			</tr>
			
			<tr>
				<td>Phone Number:</td>
				<td><form:input class="form-control" path="phoneNumber" size="30"
						/> <font color="red"><form:errors
							path="phoneNumber" /></font></td>
			</tr>
			<tr>
				<td>Status:</td>
				<td><form:input class="form-control" path="status" value="seller" readonly="true"/>
					
					</td>
			</tr>
			<tr>
				<td colspan="2"><input class="btn btn-primary" type="submit" value="Register User" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>