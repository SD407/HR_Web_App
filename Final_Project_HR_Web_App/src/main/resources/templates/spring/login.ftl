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
	    
	    <!-- Website Font style -->
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
	
	</head>
	
	<body>
		<!-- Menu -->
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
        		  <a class="navbar-brand" style="padding-top: 0px;" href="/spring/about"><img src="<@spring.url '/images/logo.png'/>" style="width:50px;height:50px;"></a>
        		</div>
        		<div id="navbar" class="navbar-collapse collapse">
         			 <ul class="nav navbar-nav">
			            <li><a href="/spring/home">Home</a></li>
			            <li><a href="/spring/about">About</a></li>
			            <li><a href="/spring/contact">Contact</a></li>
         			 </ul>
		        <div id="navbar" class="navbar-collapse collapse">
		          <ul class="nav navbar-nav navbar-right">
		            <li class="active"><a href="/spring/registrationPage">Register</a></li>
		          </ul>
		        </div><!--/.nav-collapse -->
		      </div>
		    </nav>
		</div>
		<!-- Menu end -->
		
		<!-- Content -->
		<div class="container">
			<div style="margin: 230px auto 0px; max-width: 330px;" class="row main">
				<div class="panel-heading">
	            </div> 
				<div class="main-login main-center">
					<form class="form-horizontal" method="POST" action="/spring/login">
							<#if RequestParameters.error??>
							    <div>
							        <ul style="text-align: center; padding-left: 0px;">
						                <b style="color:red">
						                	Invalid credentials provided.
						                </b>
							        </ul>
							    </div>
							</#if>

							<#if RequestParameters.logout??>
							    <div>
							        <ul style="text-align: center; padding-left: 0px;">
						                <b style="color:green">
						                	You have been logged out.
						                </b>
							        </ul>
							    </div>
							</#if>
				
							<div class="form-group">
								<label for="username" class="cols-sm-2 control-label">Username</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
										<input type="text" class="form-control" name="userName" id="userName" placeholder="Enter your Username"/>
									</div>
								</div>
							</div>
	
							<div class="form-group">
								<label for="password" class="cols-sm-2 control-label">Password</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
										<input type="password" class="form-control" name="password" id="password" placeholder="Enter your Password"/>
									</div>
								</div>
							</div>
						
						<div class="form-group ">
							<button type="submit" class="btn btn-primary btn-lg btn-block login-button">Login</button>
						</div>
						
				        <#if RequestParameters.error??>
					        <div style="text-align: center; margin-top: 10px;" class="form-control">
					            <a href="#">Forgot password?</a>
					        </div>
				        </#if>
						
						<div style="margin-top: 10px;" class="login-register">
				            <a href="/spring/registrationPage">Register</a>
				        </div>
						 <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
					</form>
				</div>
			</div>
		</div>
		<!-- Content end -->
		
	</body>
</html>
</#escape>