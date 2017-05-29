<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Request List</title>
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

<form:form action="${contextPath}/post/mail" method = "get">
<table class="table table-striped">
        <tr>
            <td><b>Post Id</b></td>
            <td><b>Customer</b>
            <td><b>Message</b></td>
            <td><b>Mail</b></td>
        </tr>
        <c:forEach var="messages" items="${messages}">
            <tr>
                <td>${messages.post.id}</td>
                <td>${messages.customer.personId}</td>
                <td>${messages.message}</td>
                <td><button class="btn btn-primary" type=submit name="mail" value="${messages.customer.email}">send mail</button></td>
            </tr>
        </c:forEach>
    </table>
    </form:form>
</body>
</html>