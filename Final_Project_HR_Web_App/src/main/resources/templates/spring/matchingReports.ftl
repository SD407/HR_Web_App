<#ftl>
<#import "/spring.ftl" as spring/>
<#assign head]

</#assign>
<#escape x as x?html>

<#if user??>
	<#if user != "anonymousUser">
<!DOCTYPE html>
<html>
	<head>
		<title>${title}</title>
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
		            <li class="#"><a href="/spring/home">Home</a></li>
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
			            <li class="#"><a href="/spring/details">Account Details</a></li>
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
		</div>
		<!-- Menu end -->
		
		<div class="container">
			<div>
				<div class="panel panel-default" style="margin-top:55px;">
					<div class="panel-heading"><b>MATCHING RESULTS</b>
						<span style="float:left; margin-right:50px;" class="glyphicon glyphicon-list-alt" aria-hidden="true"/>
					</div>
				
				<#if role == "[ROLE_COMPANY]">  	
				<!-- Default tables -->
				<table class="table table-hover">
					<tr>
						<th>Username </th>
						<th>Location </th>
						<th>Year Of Experience </th>
						<th>Current Job Title </th>
						<th>Personal Skills </th>
						<th>Professional Skills </th>
					</tr>
					<#if candidateMatches??>
						<#list candidateMatches as matchCandidate>
								<tr>
									<td>  ${matchCandidate.userName!''} </td>
									<td>  ${matchCandidate.location!''} </td>
									<td>  ${matchCandidate.yearOfExperience!''} </td>
									<td>  ${matchCandidate.currentJobTitle!''} </td>
									<td>  ${matchCandidate.personalSkills!''} </td>
									<td>  ${matchCandidate.professionalSkills!''} </td>
								</tr>
						</#list>
					</#if>
				</table>
				<#elseif role == "[ROLE_CANDIDATE]">
				<table class="table table-hover">
					<tr>
						<th>Username </th>
						<th>Location </th>
						<th>Year Of Experience </th>
						<th>Current Job Title </th>
						<th>Personal Skills </th>
						<th>Professional Skills </th>
					</tr>
					<#if companyMatches??>
						<#list companyMatches as matchCompany>
								<tr>
									<td>  ${matchCompany.userName!''} </td>
									<td>  ${matchCompany.location!''} </td>
									<td>  ${matchCompany.yearOfExperience!''} </td>
									<td>  ${matchCompany.currentJobTitle!''} </td>
									<td>  ${matchCompany.personalSkills!''} </td>
									<td>  ${matchCompany.professionalSkills!''} </td>
								</tr>
						</#list>
					</#if>
				</table>
				</#if>
			</div>
	</body>
</html>
	</#if>
</#if>
</#escape>