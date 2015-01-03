<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>ShopIT: Account Details</title>
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
        	<h1>Order History</h1>
        	<form action="cancelorder" method="post">
        	<table width="680px" cellspacing="0" cellpadding="5">
           	  	<tr bgcolor="#ddd">
           	  		<th width="60" align="left">Order# </th> 
                	<th width="180" align="left">Product </th> 
               	  	<th width="100" align="center">Quantity </th> 
                	<th width="60" align="right">Amount </th> 
                	<th width="60" align="right">Date </th> 
                	<th width="90"> </th>
              	</tr>
				<c:forEach var="orderid" items="${al_orderid}" varStatus="status">
               	<tr>
               		<td align="center"><c:out value="${orderid}"/></td>
                   	<td><c:out value="${al_product[status.index]}"/></td>  
                    <td align="center"><c:out value="${al_quantity[status.index]}"/></td> 
                    <td align="right"><c:out value="${al_amount[status.index]}"/></td>  
                    <td align="right"><c:out value="${al_date[status.index]}"/></td>
                    <c:if test="${al_status[status.index] != 'Processed'}">
                    	<td align="center"> <input type="submit" value="Cancel Order" class="link grey">
                    	<input type="hidden" name="orderid_cancel" value="${orderid}" >                    	
                    </c:if>
				</tr>
				</c:forEach>
			</table>
			</form>                             
        </div> 
        <div class="cleaner"></div>
    </div> <!-- END of templatemo_main -->
</div> <!-- END of templatemo_wrapper -->

</body>
</html>