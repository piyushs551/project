<%-- 
    Document   : result
    Created on : Aug 17, 2017, 11:29:20 AM
    Author     : piyush
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Result</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    <!-- Custom fonts for this template -->
    

</head>

<body id="page-top">

    <!-- Navigation -->
    <nav class="navbar navbar-inverse">
        <a class="navbar-brand" href="index.html">SEARCHER</a>
        
        
    </nav>

    <!-- Intro Header -->
    

    <!-- About Section -->
    <section id="about" class="content-section ">

        <div class="container">
        <c:forEach items="${sessionScope.printList}" var="bean">
        <h3>FileName: <c:out value="${bean.title}"/></h3>
        
        <hr>

        <h4>Ranking Score: <c:out value="${bean.rankSc}"/></h4>
        <a href="file:///${bean.path}.getAbsolutePath()"><h4>Path: <c:out value="${bean.path}"/></h4></a>
        <h4>Snippet: <c:out value="${bean.content}"/></h4>
        <hr>

        </c:forEach>
            
        </div>
    </section>







    <!-- Download Section -->
    

   

    <!-- Map Section -->
    

    <!-- Footer -->
    <footer>
        <div class="container text-center">
            <p>Copyright &copy; 2017</p>
        </div>
    </footer>

    
   

</body>

</html>