<#ftl>
<#import "/spring.ftl" as spring/>
<#assign head]

</#assign>
<#escape x as x?html>

<!DOCTYPE html>
<html>
	<head>
		<title>Add Details</title>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	    <title>Bootstrap 101 Template</title>
	
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<@spring.url '/js/bootstrap.min.js'/>"></script>
	    
	    <!-- Bootstrap -->
	    <link href="<@spring.url '/css/bootstrap.min.css'/>" rel="stylesheet">
	
	    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    
		<!-- Google Fonts -->
		<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
		
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
		            <li class="active"><a href="/spring/home">Home</a></li>
		          </ul>
		          <ul class="nav navbar-nav">
		            <li class="#"><a href="/spring/about">About</a></li>
		          </ul>
		          <ul class="nav navbar-nav">
		            <li class="#"><a href="/spring/contact">Contact</a></li>
		          </ul>
	      	 	  <#if user??>
	          		<#if user != "anonymousUser">
	         			 <ul class="nav navbar-nav">
							<li class="dropdown">
					              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">User Account <span class="caret"></span></a>
					              <ul class="dropdown-menu">
					                <li><a href="/spring/details">Account Details</a></li>
					                <li><a href="/spring/matchingReports">Matching Reports</a></li>
					              </ul>
				            </li>
	         			 </ul>
	         		</#if>
				  </#if>
		          <ul class="nav navbar-nav navbar-right">
			         	 <#if user??>
			          		<#if user != "anonymousUser">
				           	 	<li><a href="<@spring.url '/logout'/>">Logout</a></li>
				           	<#else>
				           		<li><a href="/spring/login">Login</a></li>
			         		</#if>
						</#if>
		          </ul>
		        </div><!--/.nav-collapse -->
		      </div>
		    </nav>
		<!-- Menu end -->
		
		
		<!-- Add candidate experience & skills -->
		<div class="panel panel-default" style="margin-top:55px;">
				<div class="panel-heading"><b>ADD DETAILS </b>
					<span style="float:left; margin-right:50px;" class="glyphicon glyphicon-list-alt" aria-hidden="true"/>
				</div>
		  	<div class="panel-body" style="padding-top: 0px;padding-bottom: 0px;">
		  	</div>
				<form method="post" action="/spring/saveCompanyJobDescription">
					
					<#if errors??>
					    <div>
					        <ul style="text-align: center; padding-left: 0px;">
					            <#list errors as error>
					                <b style="color:red">
					                <#if error.field??>${error.field}: </#if>${error.defaultMessage}
					                </b>
					            </#list>
					        </ul>
					    </div>
					</#if>
				
					<div class="form-group" style="margin-top:15px;margin-left:10px;">
		        		<label style="margin: 0px 0px 0px;">Location:</label>
						<br>
						<input type="radio" name="location" id="location" value="CLUJ-NAPOCA">&nbsp;CLUJ-NAPOCA&nbsp;</input>
						<input type="radio" name="location" id="location" value="BUCURESTI">&nbsp;BUCURESTI&nbsp;</input>
		            </div>
						<hr style="margin-left: 0px;margin-right: 0px;margin-top: 10px;margin-bottom: 10px;width: 100%;">
					<div class="form-group" style="margin-top:15px;margin-left:10px;">
		        		<label style="margin: 0px 0px 0px;">Years of Experience:</label>
						<br>
						<input type="radio" name="yearOfExperience" id="yearOfExperience" value="0-1">&nbsp;0-1&nbsp;</input>
						<input type="radio" name="yearOfExperience" id="yearOfExperience" value="1-3">&nbsp;1-3&nbsp;</input>
						<input type="radio" name="yearOfExperience" id="yearOfExperience" value="3-5">&nbsp;3-5&nbsp;</input>
						<input type="radio" name="yearOfExperience" id="yearOfExperience" value="5-8">&nbsp;5-8&nbsp;</input>
		            </div>
		            	<hr style="margin-left: 0px;margin-right: 0px;margin-top: 10px;margin-bottom: 10px;width: 100%;">
		            <div class="form-group" style="margin-top:15px;margin-left:10px;">
		        		<label style="margin: 0px 0px 0px;">Current Job:</label>
						<br>
						<input type="radio" name="currentJobTitle" id="currentJobTitle" value="PM">&nbsp;PM&nbsp;</input>
						<input type="radio" name="currentJobTitle" id="currentJobTitle" value="BA">&nbsp;BA&nbsp;</input>
						<input type="radio" name="currentJobTitle" id="currentJobTitle" value="QA">&nbsp;QA&nbsp;</input>
						<input type="radio" name="currentJobTitle" id="currentJobTitle" value="JAVA">&nbsp;JAVA&nbsp;</input>
		            </div>
		            	<hr style="margin-left: 0px;margin-right: 0px;margin-top: 10px;margin-bottom: 10px;width: 100%;">
		            <div class="form-group" style="margin-top:15px;margin-left:10px;">
		        		<label style="margin: 0px 0px 0px;">Personal Skills:</label>
						<br>
						<input type="checkbox" name="personalSkills" id="personalSkills" value="TEAM PLAYER">&nbsp;TEAM PLAYER&nbsp;</input>
						<input type="checkbox" name="personalSkills" id="personalSkills" value="GOOD COMMUNICATOR">&nbsp;GOOD COMMUNICATOR&nbsp;</input>
						<input type="checkbox" name="personalSkills" id="personalSkills" value="HARD WORKING">&nbsp;HARD WORKING&nbsp;</input>
		            </div>
		            	<hr style="margin-left: 0px;margin-right: 0px;margin-top: 10px;margin-bottom: 10px;width: 100%;">
		            <div class="form-group" style="margin-top:15px;margin-left:10px;">
		        		<label style="margin: 0px 0px 0px;">Professional Skills:</label>
						<br>
						<input type="checkbox" name="professionalSkills" id="professionalSkills" value="JAVA">&nbsp;JAVA&nbsp;</input>
						<input type="checkbox" name="professionalSkills" id="professionalSkills" value="SPRING">&nbsp;SPRING&nbsp;</input>
						<input type="checkbox" name="professionalSkills" id="professionalSkills" value="JDBC">&nbsp;JDBC&nbsp;</input>
		            </div>
		            	<hr style="margin-left: 0px;margin-right: 0px;margin-top: 10px;margin-bottom: 10px;width: 100%;">
					<div class="form-group main-center" style="padding-top:0px;padding-bottom:10px;">
						<button type="submit" class="btn btn-primary btn-lg btn-block login-button">Save skills</button>
					</div>
					<div style="margin-bottom:10px;" class="login-register">
				            <a href="/spring/details">Cancel</a>
				    </div>
					<input type="hidden" name="id" value="<#if companyJobDescription.id??>${companyJobDescription.id?c}</#if>">
					<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
				</form>
			</div>
		<!-- End candidate experience & skills -->
		
	</body>
</html>
</#escape>