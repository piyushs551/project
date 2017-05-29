<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<style>
	body{
	background-image : url("/images/background.jpg");
	background-repeat : no-repeat;
	background-size : 100% 100%;
	color : white;
	}
	
	#main{
		opacity: 1;
	}
	#form{
		opacity: 1;
		font-weight: bold;
	}
	</style>
</head>
<body>

	<nav class="navbar navbar-inverse">
  	<div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="">home</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="user/register.htm">Register a new Seller</a></li>
      <li><a href="user/signupCustomer.htm">Register a new Buyer</a></li>
      
    	
    </ul>
    
  	</div>
	</nav>
	<div class="container-fluid">
	
	<div class="container-fluid" id="main"><center>
<br><br><br><br>
    <h1 style="font-weight:bold">Login</h1>
    <form action="user/login" method="post">
    
        <div class="form-group well-sm" id="form">
    <label for="email">Username:</label>
             <input type="text" name="username" class="form-control" style="width: 300px;"  />
             </div>
            <div class="form-group well-sm" id="form">
    <label for="email">Password:</label>
             <input type="password" class="form-control" name="password" style="width: 300px;" />
                    </div>
                    
                    <button type="submit" class="btn btn-success" id="form"">LOGIN</button>                                                               
                    <br/><br><br><br><br>

    </form></center></div>
    
	</div>
</body>
</html>                                                  