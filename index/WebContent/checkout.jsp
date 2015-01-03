<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>ShopIT: Checkout</title>
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
        
        <div class="cleaner"></div>
    </div> <!-- END of templatemo_header -->
    
    <div id="templatemo_menu">
    	<div id="top_nav" class="ddsmoothmenu">
	            <ul>
	                <li><a href="http://localhost:3000">Home</a></li>
	            </ul>
	            <br style="clear: left" />
	        </div> <!-- end of ddsmoothmenu -->
    </div> <!-- END of templatemo_menu -->
    
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
        	<h2>Checkout</h2>
            <h5><strong>BILLING DETAILS</strong></h5>
            <form action="checkout" method="post">
	            
	            <div class="content_half float_l checkout">
					Enter your full name as it is on the credit card:                				
	                <input type="text" name="shipping_name"  style="width:300px;"  />
	                Shipping Address:
	                <input type="text" name="shipping_address" style="width:300px;"  />
	                Email:
					<input type="text" name="shipping_email" style="width:300px;"  />
	                Phone:<br />
					<span style="font-size:10px">Please, specify your reachable phone number. YOU MAY BE GIVEN A CALL TO VERIFY AND COMPLETE THE ORDER.</span>
	                <input type="text" name="shipping_phone" style="width:300px;"  />
	            </div>
            
	            <div class="content_half float_r checkout">
	            	Credit/Debit Card Number:
	                <input type="text" name="cardnumber" style="width:300px;"  />
	                Credit/Debit Card CVV2:
	                <input type="text" name="cvv" style="width:300px;"  />
	            </div>
	            
	            <div class="cleaner h50"></div>
	            <h3>Shopping Cart</h3>
	            <h4>TOTAL: <strong><%= session.getAttribute("amount") %></strong></h4>
	            <input type="submit" value="Pay Now" class="btn btn-info">
			</form>
		</div>
        <div class="cleaner"></div>
    </div> <!-- END of templatemo_main -->    
</div> <!-- END of templatemo_wrapper -->

</body>
</html>