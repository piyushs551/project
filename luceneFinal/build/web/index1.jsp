

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Search engine</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
 

    <!-- Custom fonts for this template -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Cabin:700' rel='stylesheet' type='text/css'>

    <!-- Custom styles for this template -->
    <link href="css/grayscale.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
        <a class="navbar-brand" href="#page-top"></a>
        
        
    </nav>

    <!-- Intro Header -->
    <header class="masthead">
        <div class="intro-body">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 mx-auto">

                        <form name='myForm' method="get" action="lucene">

                        <h1 class="brand-heading">SearcheR</h1>
                        <p class="intro-text"><input type="text" name="query1" class="form-control" placeholder="FIRST QUERY" required="required"/></p>
                        <p class="intro-text"><input type="text" name="query2" class="form-control" placeholder="SECOND QUERY" required="required"/></p>


                        <span class="Form-title"><label></label></span>
                        <label class="Form-label--tick">
                            <input type="radio" value="conjunction" name="radioBtn" class="Form-label-radio" checked>
                            <span class="Form-label-text">Conjuction</span>
                        </label>
                        <label class="Form-label--tick">
                            <input type="radio" value="disjunction" name="radioBtn" class="Form-label-radio">
                            <span class="Form-label-text">Disjunction</span>
                        </label>
                        <label class="Form-label--tick">
                            <input type="radio" value="negation" name="radioBtn" class="Form-label-radio">
                            <span class="Form-label-text">negation</span>
                        </label>
                        <br>

                        <button type="submit" class="btn btn-default btn-lg">Search</button>

                        </form>
                        
                    </div>
                </div>
            </div>
        </div>
    </header>

    
    

    <!-- Footer -->
    <footer>
        <div class="container text-center">
            <p>Copyright &copy; 2017</p>
        </div>
    </footer>

    

</body>

    
</html>

