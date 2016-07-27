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
			            <li class="active"><a href="/spring/details">Account Details</a></li>
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
		
		<!-- Content candidate details-->
		<div class="container">
			<div>
				<div class="panel panel-default" style="margin-top:55px;">
					<!-- Admin panel contents -->
				  	<#if role == "[ROLE_ADMIN]">
					  	<div class="panel-heading"><b>${role?keep_after("_")?keep_before("]")} TABLES</b>
							<span style="float:left; margin-right:50px;" class="glyphicon glyphicon-list-alt" aria-hidden="true"/>
						</div>
					<#elseif role != "[ROLE_ADMIN]">
				 	<!-- Default panel contents -->
						<div class="panel-heading"><b>${user?upper_case} ${role?keep_after("_")?keep_before("]")} DETAILS</b>
							<span style="float:left; margin-right:50px;" class="glyphicon glyphicon-list-alt" aria-hidden="true"/>
						</div>
					</#if>
				  	<div class="panel-body" style="padding-top: 0px;padding-bottom: 0px;">
				  	</div>
				  	
				<!-- Default tables -->
				<#if role == "[ROLE_CANDIDATE]" || role == "[ROLE_COMPANY]">
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
					</tr>
			
					<#if role == "[ROLE_CANDIDATE]">
						<#if candidates??>
							<#list candidates as candidate>
								<#if user == candidate.userName>
								<tr>
									<td>  ${candidate.firstName!''} </td>
									<td>  ${candidate.lastName!''} </td>
									<td>  ${candidate.phoneNumber!''} </td>
									<td>  ${candidate.email!''} </td>
									<td><a style="float:right;" href="/spring/editCandidate?id=${candidate.id?c}">
										<button type="button" class="btn btn-primary btn-xs">EDIT </button></a></td>
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
									<td><a style="float:right;" href="/spring/editCompany?id=${company.id?c}">
										<button type="button" class="btn btn-primary btn-xs">EDIT </button></a></td>
								</tr>
								</#if>
							</#list>
						</#if>
					</#if>
				</table>
					<!-- Default tables -->
					<!-- ADMIN tables -->
					<#elseif role == "[ROLE_ADMIN]">
						<table class="table table-hover">
							<tr>
								<th>First Name </th>
								<th>Last Name </th>
								<th>Phone Number </th>
								<th>Email </th>
								<th> </th>
								<th> </th>
							</tr>
						<#if candidates??>
							<#list candidates as candidate>
								<#if user != candidate.userName>
								<tr>
									<td>  ${candidate.firstName!''} </td>
									<td>  ${candidate.lastName!''} </td>
									<td>  ${candidate.phoneNumber!''} </td>
									<td>  ${candidate.email!''} </td>
									<td><a href="/spring/editCandidate?id=${candidate.id?c}">
										<button type="button" class="btn btn-primary btn-xs">EDIT </button></a></td>
									<td><a href="/spring/deleteCandidate?id=${candidate.id?c}">
										<button type="button" class="pull-right btn btn-danger btn-xs">DELETE</button></a></td>
									</tr>
								</#if>
							</#list>
						</#if>
						</table>
						<table class="table table-hover">
							<tr>
								<th>Company Name </th>
								<th>Phone Number </th>
								<th>Email </th>
								<th> </th>
								<th> </th>
							</tr>
						<#if companies??>
							<#list companies as company>
								<#if user != company.userName>
								<tr>
									<td>  ${company.companyName!''} </td>
									<td>  ${company.phoneNumber!''} </td>
									<td>  ${company.email!''} </td>
									<td><a href="/spring/editCompany?id=${company.id?c}">
										<button type="button" class="pull-right btn btn-primary btn-xs">EDIT </button></a></td>
									<td><a href="/spring/deleteCompany?id=${company.id?c}">
										<button type="button" class="pull-right btn btn-danger btn-xs">DELETE</button></a></td>
								</tr>
								</#if>
							</#list>
						</#if>
						</table>
				</#if>
				<!-- Default tables -->
				</div>
			</div>
		<!-- Content candidate details end -->
		
		<!-- Add experience & skills -->
		<div class="panel panel-default" style="margin-top:5px;">
			<#if role == "[ROLE_CANDIDATE]">
				<div class="panel-heading"><b>ADD ${role?keep_after("_")?keep_before("]")} EXPERIENCE</b>
				<#if candidateJobDescriptions??>
					<#list candidateJobDescriptions as candidateJobDescription>
					<#if user == candidateJobDescription.userName>
					<th><a href="/spring/matchingReports?id=${candidateJobDescription.id?c}">
						<button type="button" class="pull-right btn btn-success btn-xs">FIND MATCHES </button></a></td>
					<span style="float:left; margin-right:50px;" class="glyphicon glyphicon-list-alt" aria-hidden="true"/>
					</#if>
					</#list>
				</#if>
				</div>
			<#elseif role == "[ROLE_COMPANY]">
				<div class="panel-heading"><b>ADD ${role?keep_after("_")?keep_before("]")} REQUIREMENTS</b>
				<#if companyJobDescriptions??>
					<#list companyJobDescriptions as companyJobDescription>
					<#if user == companyJobDescription.userName>
					<th><a href="/spring/matchingReports?id=${companyJobDescription.id?c}">
						<button type="button" class="pull-right btn btn-success btn-xs">FIND MATCHES </button></a></td>
					<span style="float:left; margin-right:50px;" class="glyphicon glyphicon-list-alt" aria-hidden="true"/>
					</#if>
					</#list>
				</#if>
				</div>
			</#if>
		  	<div class="panel-body" style="padding-top: 0px;padding-bottom: 0px;">
		  	</div>
			<#if role == "[ROLE_CANDIDATE]">
				<table class="table table-hover">
					<#if candidateJobDescriptions??>
						<#list candidateJobDescriptions as candidateJobDescription>
							<#if user == candidateJobDescription.userName>
								<tr>
									<th>Location </th>
									<th>Year Of Experience </th>
									<th>Current Job Title </th>
									<th>Personal Skills </th>
									<th>Professional Skills </th>
									<#if candidateJobDescription.id?c?? && candidateJobDescription.location??>
										<th></th>
									<#else>
										<th><a style ="float:right;" 
										href="/spring/addCandidateJobDescription?id=${candidateJobDescription.id?c}">
											<button type="button" class="btn btn-primary btn-xs">ADD </button></a></td>
									</#if>
								</tr>
							</#if>
						</#list>
					</#if>
					<#if candidateJobDescriptions??>
						<#list candidateJobDescriptions as candidateJobDescription>
							<#if user == candidateJobDescription.userName>
								<tr>
									<td>  ${candidateJobDescription.location!''} </td>
									<td>  ${candidateJobDescription.yearOfExperience!''} </td>
									<td>  ${candidateJobDescription.currentJobTitle!''} </td>
									<td>  ${candidateJobDescription.personalSkills!''} </td>
									<td>  ${candidateJobDescription.professionalSkills!''} </td>
									<#if candidateJobDescription.id?c?? && candidateJobDescription.location??>
										<td><a style="float:right;" 
										href="/spring/editCandidateJobDescription?id=${candidateJobDescription.id?c}">	
											<button type="button" class="btn btn-primary btn-xs">EDIT </button></a></td>
									<#else>
										<th></th>
									</#if>
								</tr>
							</#if>
						</#list>
					</#if>
				</table>
			<#elseif role == "[ROLE_COMPANY]">
				<table class="table table-hover">
					<#if companyJobDescriptions??>
						<#list companyJobDescriptions as companyJobDescription>
							<#if user == companyJobDescription.userName>
								<tr>
									<th>Location </th>
									<th>Year Of Experience </th>
									<th>Current Job Title </th>
									<th>Personal Skills </th>
									<th>Professional Skills </th>
									<#if companyJobDescription.id?c?? && companyJobDescription.location??>
										<th></th>
									<#else>
										<th><a style ="float:right;" 
										href="/spring/addCompanyJobDescription?id=${companyJobDescription.id?c}">
											<button type="button" class="btn btn-primary btn-xs">ADD </button></a></td>
									</#if>
								</tr>
							</#if>
						</#list>
					</#if>
					<#if companyJobDescriptions??>
						<#list companyJobDescriptions as companyJobDescription>
							<#if user == companyJobDescription.userName>
								<tr>
									<td>   ${companyJobDescription.location!''} </td>
									<td>   ${companyJobDescription.yearOfExperience!''} </td>
									<td>   ${companyJobDescription.currentJobTitle!''} </td>
									<td>  ${companyJobDescription.personalSkills!''} </td>
									<td>  ${companyJobDescription.professionalSkills!''} </td>
									<#if companyJobDescription.id?c?? && companyJobDescription.location??>
										<td><a style="float:right;" 
										href="/spring/editCompanyJobDescription?id=${companyJobDescription.id?c}">
											<button type="button" class="btn btn-primary btn-xs">EDIT </button></a></td>
									<#else>
										<th></th>
									</#if>
								</tr>
							</#if>
						</#list>
					</#if>
				</table>
			</#if>
		</div>
		<!-- End xperience & skills -->
		
	</body>
</html>
	</#if>
</#if>

</#escape>