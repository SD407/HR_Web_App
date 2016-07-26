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
		            <li class="active"><a href="/spring/contact">Contact</a></li>
		          </ul>
		           <#if user??>
	          		<#if user != "anonymousUser">
			          <ul class="nav navbar-nav">
			            <li class="#"><a href="/spring/details">Account Details</a></li>
			          </ul>
	         		</#if>
				  </#if>
		          <ul class="nav navbar-nav navbar-right">
	          		<#if user != "anonymousUser">
		           	 	<li><a href="<@spring.url '/logout'/>">Logout</a></li>
		           	<#else>
		           		<li><a href="/spring/login">Login</a></li>
	         		</#if>
		          </ul>
		        </div><!--/.nav-collapse -->
		      </div>
		    </nav>
		</div>
		<!-- Menu end -->
		
		<!-- Content -->
		<div class="container">
			<div class="panel panel-default" style="margin-top:55px;">
				<div class="panel-heading"><b>Scoala Informala de IT</b>
					<span style="float:left; margin-right:10px;" class="glyphicon glyphicon-globe" aria-hidden="true"/>
				</div>
				<div style="width:100%;overflow:hidden;height:400px;color:red;">
				<div id="gmap_display" style="height:100%; max-width:100%;">
					<iframe style="height:100%;width:100%;border:0;" frameborder="0" src="https://www.google.com/maps/embed/v1/place?q=Cluj-Napoca,+Cluj+County,+Romania+henry+barbuse+scoala+informala+de+it&key=AIzaSyAN0om9mFmy1QN6Wf54tXAowK4eT0ZUPrU"></iframe>
				</div>
				<a class="embedded-map-html" href="https://www.hostingreviews.website/inmotion-hosting-review" id="enable-map-data">inmotion hosting reviews</a>
				<style>#gmap_display img{max-width:none!important;background:none!important;}</style>
				</div>
				<script src="https://www.hostingreviews.website/google-maps-authorization.js?id=caff92dc-78f9-94bc-617b-6621d1d810c9&c=embedded-map-html&u=1467291397" defer="defer" async="async"></script>
			<div>
		
			<div class="panel panel-default" style="margin-top:0px; margin-bottom:0px; text-align:left;">
				<div class="panel-heading"><b>Contact Details</b>
					<span style="float:left; margin-right:10px;" class="glyphicon glyphicon-globe" aria-hidden="true"/>
				</div>
				<div style="margin-left:40px;">
				<p style="margin-top:10px;"><b>Ne puteți contacta la numerele de telefon:</b></p>
				   <p> Cluj-Napoca: 0744-679.530</p>
				   <p>București: 0744-679.530</p>
				   <p>Iași: 0722-523.102</p>
				   <p> Oradea: 0723-777.826</p>
				   <p> Timișoara:  0758-522.061</p>
				<p><b>pe email la:</b></p>
				  <p>  cluj@scoalainformala.ro</p>
				  <p>  bucuresti@scoalainformala.ro</p>
				  <p>  iasi@scoalainformala.ro</p>
				  <p>  oradea@scoalainformala.ro</p>
				  <p> timisoara@scoalainformala.ro</p>
				</div>
			<hr style="margin-left: 0px;margin-right: 0px;margin-top: 10px;margin-bottom: 10px;width: 100%;">
			<div class="container">
		    <!----------- Footer ------------>
		    <footer class="footer-bs">
		        <div class="row">
		        	<div class="col-md-4 footer-brand animated fadeInLeft">
		            	<h2 style="margin-top: 10px;"><img src="<@spring.url '/images/logo.png'/>" style="width:80px;height:80px;"></h2>
		                <p>© 2016 PerfectMatch, All rights reserved</p>
		            </div>
		        	<div class="col-md-4 footer-nav animated fadeInUp"  style="width:270px;">
		            	<h4>Menu</h4>
		            	<div class="col-md-10" style="padding-left: 0px;">
		                    <ul class="list">
		                        <li><a href="/spring/about">About Us</a></li>
		                        <li><a href="/spring/contact">Contact</a></li>
		                        <li><a href="#">Terms & Condition</a></li>
		                        <li><a href="#">Privacy Policy</a></li>
		                    </ul>
		                </div>
		            </div>
		        	<div class="col-md-3 footer-social animated fadeInDown">
		            	<h4>Follow Us</h4>
		            	<ul>
		                	<li><a href="#">Facebook</a></li>
		                	<li><a href="#">Twitter</a></li>
		                	<li><a href="#">Instagram</a></li>
		                	<li><a href="#">RSS</a></li>
		                </ul>
		            </div>
		        	<div class="col-md-2 footer-ns animated fadeInRight">
		            	<h4>Email us
		                <p>
		                    <div class="input-group">
		                      <a href="mailto:dragan.sorinvladut@gmail.com?Subject=Hello%20again" target="_top"><span class="glyphicon glyphicon-envelope"></span></a>
		                    </div>
		                    <!-- /input-group -->
		                 </p>
		                 </h4>
		            </div>
		        </div>
		    </footer>
		
		</div>
		<!-- Content end -->
	</body>
</html>

</#escape>