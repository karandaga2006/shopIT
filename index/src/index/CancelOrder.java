package index;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class CancelOrder extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		System.out.println("Inside CancelOrder.java");
		
		HttpSession session = request.getSession(true);
		
		int orderid_cancel = Integer.parseInt(request.getParameter("orderid_cancel"));
		int quantity_cancel = 0;
		String sku_update = null;
		
		ArrayList<String> al_orderid = (ArrayList<String>) session.getAttribute("al_orderid");
		ArrayList<String> al_sku = (ArrayList<String>) session.getAttribute("al_sku");
		ArrayList<String> al_product = (ArrayList<String>) session.getAttribute("al_product");
		ArrayList<String> al_quantity = (ArrayList<String>) session.getAttribute("al_quantity");
		ArrayList<String> al_amount = (ArrayList<String>) session.getAttribute("al_amount");
		ArrayList<String> al_date = (ArrayList<String>) session.getAttribute("al_date");
		ArrayList<String> al_status = (ArrayList<String>) session.getAttribute("al_status");
		
		for (int i = 0; i < al_orderid.size(); i++){
	        int orderid = Integer.parseInt(al_orderid.get(i));
	        if (orderid == orderid_cancel){
	        	al_orderid.remove(i);
	        	sku_update = al_sku.get(i);
	        	al_sku.remove(i);
	        	al_product.remove(i);
	        	quantity_cancel = Integer.parseInt(al_quantity.get(i));
	        	al_quantity.remove(i);
	        	al_amount.remove(i);
	        	al_date.remove(i);
	        	al_status.remove(i);
	        }
	    }
		
		session.setAttribute("al_orderid", al_orderid);
		session.setAttribute("al_sku", al_sku);
		session.setAttribute("al_product", al_product);
		session.setAttribute("al_quantity", al_quantity);
		session.setAttribute("al_amount", al_amount);
		session.setAttribute("al_date", al_date);
		session.setAttribute("al_status", al_status);
		
		DatabaseOps db = new DatabaseOps("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/shopit", "root", "");
		String query = "delete from order_details where orderid = " + orderid_cancel;
		int i = db.dbinsert(query);
		if (i>0) {
	    	query = "update inventory set quantity = quantity + " + quantity_cancel + " where sku = '" + sku_update + "'";
	    	i = db.dbinsert(query);
	    	if (i<=0) {
	    		db.dbclose();
	    		System.out.println("Failed to update inventory for sku: " + sku_update + " by quantity: " + quantity_cancel);
	    		request.getRequestDispatcher("/error.jsp").forward(request, response);
	    	}
	    } else {
	    	db.dbclose();
	    	System.out.println("Failed to delete order details for order id: " + orderid_cancel);
	    	request.getRequestDispatcher("/error.jsp").forward(request, response);
	    }
		
		db.dbclose();
		request.getRequestDispatcher("/accountdetails.jsp").forward(request, response);
	}
}
