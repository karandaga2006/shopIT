<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>ShopIT: Order Summary</title>
	<link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />
	<link href="css/ddsmoothmenu.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="templatemo_wrapper">
		<div id="templatemo_header">
			<div id="header_right">
				<form action="accountdetails" method="post">
					<input type="submit" value="My Account" class="link">
				</form>
			</div>
	    	<div id="site_title">
	        	<h1>ShopIT: Order Summary</h1>
	        </div>
	        <div class="cleaner"></div>
	    </div> <!-- END of templatemo_header -->
	    
	    <div id="templatemo_menu">
	    
	    	<div id="top_nav" class="ddsmoothmenu">
	            <ul>
	                <li><a href="http://localhost:3000">Home</a></li>
	            </ul>
	            <br style="clear: left" />
	        </div> <!-- end of ddsmoothmenu -->
	    
	    <div id="templatemo_main">
	   		<div id="sidebar" class="float_l">
	        	<div class="sidebar_box"><span class="bottom"></span>
	            	<h3>Categories</h3>   
	                <div class="content"> 
	                	<ul class="sidebar_list">
	                    	<li class="first"><a href="http://localhost:3000/laptops">Laptops</a></li>
	                        <li><a href="http://localhost:3000/tablets">Tablets</a></li>
	                        <li><a href="http://localhost:3000/headphones">Headphones</a></li>
	                        <li class="last"><a href="http://localhost:3000/consoles">Gaming Consoles</a></li>
	                    </ul>
	                </div>
	            </div>
	        </div>
	        
	        <div id="content" class="float_r">
	            <h1>Your order has been placed successfully</h1>
	            <div class="content_half float_l">
	        	<a  rel="lightbox[portfolio]" ><img src="<%= session.getAttribute("img") %>"/></a>
	            </div>
	            <div class="content_half float_r">
					<table>
						<tr>
	                        <td height="30" width="160">Product:</td>
	                        <td><%= session.getAttribute("product_name") %></td>
	                    </tr>
	                    <tr>
	                        <td height="30" width="160">Price:</td>
	                        <td><%= session.getAttribute("price") %></td>
	                    </tr>
	                    <tr>
	                        <td height="30" width="160">Quantity:</td>
	                        <td><%= session.getAttribute("qty") %></td>
	                    </tr>
	                </table>
	                <div class="cleaner h20"></div>
				</div>
	            <div class="cleaner h30"></div>
	            
	            <div class="cleaner h50"></div>
	            
	        </div> 
	        
	        <div class="cleaner"></div>
	    
	    </div> <!-- END of templatemo_main -->
	    
	</div> <!-- END of templatemo_wrapper -->
	
	</body>
</html>