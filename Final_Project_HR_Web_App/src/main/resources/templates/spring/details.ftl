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
							<li class="dropdown active">
				              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">User Account <span class="caret"></span></a>
				              <ul class="dropdown-menu">
				                <li><a href="/spring/details">Account Details</a></li>
				                <li><a href="/spring/matching">Perfect Match</a></li>
				                <li><a href="/spring/reports">Reports</a></li>
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
		</div>
		<div>
			<div class="panel panel-default" style="margin-top:50px;">
				  <!-- Default panel contents -->
					<div class="panel-heading"><b>${user} account details</b>
						<span style="float:left; margin-right:50px;" class="glyphicon glyphicon-user" aria-hidden="true"/>
					</div>
				  <div class="panel-body" style="padding-top: 0px;padding-bottom: 0px;">
				   		
				  </div>
			<div>
			<table class="table table-hover">
					<tr>
					<#if role == "[ROLE_CANDIDATE]">
						<th>First Name </th>
						<th>Last Name </th>
						<th>Phone Number </th>
						<th>Email </th>
					<#elseif role == "[ROLE_COMPANY]">
						<th>Company Name </th>
						<th>Phone Number </th>
						<th>Email </th>
					</#if>
						<th> </th>
				</tr>
		
				<tr>
				<#if role == "[ROLE_CANDIDATE]">
					<#if candidates??>
						<#list candidates as candidate>
							<#if user == candidate.userName>
							<tr>
								<td>  ${candidate.firstName!''} </td>
								<td>  ${candidate.lastName!''} </td>
								<td>  ${candidate.phoneNumber!''} </td>
								<td>  ${candidate.email!''} </td>
							</tr>
							</#if>
						</#list>
					</#if>
				<#elseif role == "[ROLE_COMPANY]">
					<#if companies??>
						<#list companies as company>
							<#if user == company.userName>
							<tr>
								<td>  ${company.companyName!''} </td>
								<td>  ${company.phoneNumber!''} </td>
								<td>  ${company.email!''} </td>
							</tr>
							</#if>
						</#list>
					</#if>
				</#if>
				</table>
				</div>
			</div>
	</body>

</html>
	</#if>
</#if>

</#escape>