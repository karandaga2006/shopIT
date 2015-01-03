<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<title>ShopIT: Login </title>
		<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="css/templatemo_style_form.css" rel="stylesheet" type="text/css">	
	</head>
	<body class="templatemo-bg-gray">
		<div class="container">
			<div class="col-md-12">
				<h1 class="margin-bottom-15">Login</h1>
				<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" role="form" action="validatelogin" method="post">				
			        <div class="form-group">
			          <div class="col-xs-12">		            
			            <div class="control-wrapper">
			            	<label for="username" class="control-label fa-label"><i class="fa fa-user fa-medium"></i></label>
			            	<input type="text" class="form-control" id="username" name="username" placeholder="Username">
			            </div>		            	            
			          </div>              
			        </div>
			        <div class="form-group">
			          <div class="col-md-12">
			          	<div class="control-wrapper">
			            	<label for="password" class="control-label fa-label"><i class="fa fa-lock fa-medium"></i></label>
			            	<input type="password" class="form-control" id="password" name="password" placeholder="Password">
			            </div>
			          </div>
			        </div>
			        <div class="form-group">
			          <div class="col-md-12">
		             	<div class="checkbox control-wrapper">
		                	<label>
		                  		<input type="checkbox"> Remember me>
	                		</label>
		              	</div>
			          </div>
			        </div>
			        <div class="form-group">
			          <div class="col-md-12">
			          	<div class="control-wrapper">
			          		<input type="submit" value="Log in" class="btn btn-info">
			          		<a href="#" class="text-right pull-right">Forgot password?</a>
			          	</div>
			          </div>
			        </div>
			        <hr>
			        <div class="form-group">
			        	<div class="col-md-12">
			        		<label>Login with: </label>
			        		<div class="inline-block">
			        			<a href="#"><i class="fa fa-facebook-square login-with"></i></a>
				        		<a href="#"><i class="fa fa-twitter-square login-with"></i></a>
				        		<a href="#"><i class="fa fa-google-plus-square login-with"></i></a>
				        		<a href="#"><i class="fa fa-tumblr-square login-with"></i></a>
				        		<a href="#"><i class="fa fa-github-square login-with"></i></a>
			        		</div>		        		
			        	</div>
			        </div>
			      </form>
			      <div class="text-center">
			      	<a href="newuser.jsp" class="templatemo-create-new">Not Registered? <i class="fa fa-arrow-circle-o-right"></i></a>
			      	<br><a href="http://localhost:3000" class="templatemo-create-new">Home <i class="fa fa-home"></i></a>
			      </div>
			</div>
		</div>
	</body>
</html>