//Inserts transaction in order_details and updates the inventory

package index;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class Checkout extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inside Checkout.java");
		
		HttpSession session = request.getSession(true);
		
		String sku = (String) session.getAttribute("sku");
		String product_name = (String) session.getAttribute("product_name");
		int qty = Integer.parseInt(session.getAttribute("qty").toString());
		float amount = Float.parseFloat(session.getAttribute("amount").toString());
		String uname = (String) session.getAttribute("userid");
		
		String name = request.getParameter("shipping_name");
		String address = request.getParameter("shipping_address");
		String email = request.getParameter("shipping_email");
		String phone = request.getParameter("shipping_phone");
		
		java.util.Date order_date = new java.util.Date();
	    java.sql.Date sql_order_date = new java.sql.Date(order_date.getTime());
		String status = "Processing";
		
		DatabaseOps db = new DatabaseOps("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/shopit", "root", "");
		
		String query = "insert into order_details (sku, pdt_name, quantity, amount, uname, "
	    		+ "shipping_name, shipping_add, shipping_email, shipping_phone, date, status)"
				+ " values ('" + sku + "','" + product_name + "'," + qty + "," + amount + ",'" + uname + "','"
	    		+ name + "','" + address + "','" + email + "','" + phone + "','" + sql_order_date + "','" + status + "')";
	    
		int i = db.dbinsert(query);
		
		if (i>0) {
	    	query = "update inventory set quantity = quantity - " + qty + " where sku = '" + sku + "'";
		    i = db.dbinsert(query);
		    if (i<=0) {
		    	db.dbclose();
		    	System.out.println("Inventory update failed");
		    	request.getRequestDispatcher("/error.jsp").forward(request, response);
		    }
	    } else {
	    	db.dbclose();
	    	System.out.println("Order Detail insert failed for query: " + query);
	    	request.getRequestDispatcher("/error.jsp").forward(request, response);
	    }
		
		db.dbclose();
		request.getRequestDispatcher("/ordersummary.jsp").forward(request, response);
	}	
}