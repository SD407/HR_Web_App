<#ftl>
<#import "/spring.ftl" as spring/>
<#assign head>

</#assign>
<#escape x as x?html>

<!DOCTYPE html>
<html>
	<head>
		<title>${title}</title>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	    <title>Bootstrap 101 Template</title>
	
	    <!-- Bootstrap -->
	    <link href="<@spring.url '/css/bootstrap.min.css'/>" rel="stylesheet">
	
	    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    
	   	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<@spring.url '/js/bootstrap.min.js'/>"></script>
		
		<!-- Google Fonts -->
		<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
		
		<!-- Register CSS -->
	    <link href="<@spring.url '/css/register.css'/>" rel="stylesheet">
	
	</head>

	<body>
		<div class="container">
			<!-- Fixed navbar -->
		    <nav class="navbar navbar-default navbar-fixed-top">
		      <div class="container">
		        <div class="navbar-header">
		          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		            <span class="sr-only">Toggle navigation</span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		          </button>
        		  <a class="navbar-brand" href="/spring/about">HR Web App</a>
        		</div>
        		<div id="navbar" class="navbar-collapse collapse">
         			 <ul class="nav navbar-nav">
			            <li><a href="/spring/home">Home</a></li>
			            <li><a href="/spring/about">About</a></li>
			            <li><a href="/spring/contact">Contact</a></li>
         			 </ul>
		        <div id="navbar" class="navbar-collapse collapse">
		          <ul class="nav navbar-nav navbar-right">
		            <li class="active"><a href="/spring/login">Login</a></li>
		          </ul>
		        </div><!--/.nav-collapse -->
		      </div>
		    </nav>
		</div>
		
		<div style="margin: 300px auto 0px; max-width: 330px;">
			<div class="main-login main-center">
				<form class="form-horizontal" method="post" action="#">
					<div class="form-group" style="margin-top: 15px;">
						<button type="button"
						onclick="window.location.href='/spring/registerCandidate'" 
						class="btn btn-primary btn-lg btn-block login-button">Register Candidate
						</button>
					</div>
					<div class="form-group " style="margin-top: 15px;">
						<button type="button" 
						onclick="window.location.href='/spring/registerCompany'" 
						class="btn btn-primary btn-lg btn-block login-button">Register Company
						</button>
					</div>
				</form>
			</div>
		</div>
		
	</body>
	</html>
</#escape>