<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page import ="java.sql.*" %> 
<html>
	<head>
		<title>ShopIT: Create Account</title>
		<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="css/templatemo_style_form.css" rel="stylesheet" type="text/css">	
	</head>
	<body class="templatemo-bg-gray">
		<h1 class="margin-bottom-15">Create Account</h1>
		<div class="container">
			<div class="col-md-12">			
				<form class="form-horizontal templatemo-create-account templatemo-container" action="createuser" method="post">
					<div class="form-inner">
						<div class="form-group">
				          <div class="col-md-6">		          	
				            <label for="first_name" class="control-label">First Name</label>
				            <input type="text" class="form-control" id="fname" name="fname" placeholder="">		            		            		            
				          </div>  
				          <div class="col-md-6">		          	
				            <label for="last_name" class="control-label">Last Name</label>
				            <input type="text" class="form-control" id="lname" name="lname" placeholder="">		            		            		            
				          </div>             
				        </div>
				        <div class="form-group">
				          <div class="col-md-12">		          	
				            <label for="username" class="control-label">Email</label>
				            <input type="email" class="form-control" id="email" name="email" placeholder="">		            		            		            
				          </div>              
				        </div>			
				        <div class="form-group">
				          <div class="col-md-6">		          	
				            <label for="username" class="control-label">Username</label>
				            <input type="text" class="form-control" id="username" name="username" placeholder="">		            		            		            
				          </div>
				        </div>
				        <div class="form-group">
				          <div class="col-md-6">
				            <label for="password" class="control-label">Password</label>
				            <input type="password" class="form-control" id="password" name="password" placeholder="">
				          </div>
				          <div class="col-md-6">
				            <label for="password" class="control-label">Confirm Password</label>
				            <input type="password" class="form-control" id="password_confirm" name="password_confirm" placeholder="">
				          </div>
				        </div>
				        <div class="form-group">
				          <div class="col-md-12">
				            <input type="submit" value="Create account" class="btn btn-info">
							<a href="login.jsp" class="pull-right">Registered User?</a>
				            <br><a href="http://localhost:3000" class="pull-right">Home <i class="fa fa-home"></i></a>
				          </div>
				        </div>	
					</div>				    	
			      </form>		      
			</div>
		</div>
		
		<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
	</body>
</html>