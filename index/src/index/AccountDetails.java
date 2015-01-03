package index;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class AccountDetails extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		System.out.println("Inside AccountDetails.java");
		
		HttpSession session = request.getSession(true);
		String uname = (String) session.getAttribute("userid");
		
		ArrayList<String> al_orderid = new ArrayList<String>();
		ArrayList<String> al_sku = new ArrayList<String>();
		ArrayList<String> al_product = new ArrayList<String>();
		ArrayList<String> al_quantity = new ArrayList<String>();
		ArrayList<String> al_amount = new ArrayList<String>();
		ArrayList<String> al_date = new ArrayList<String>();
		ArrayList<String> al_status = new ArrayList<String>();
		
		Date orderdate, currentdate;
	    Calendar order_date = Calendar.getInstance(), current_date = Calendar.getInstance();
	    java.sql.Date sqlDate;
	    long startTime, endTime, diffTime, diffDays;
	    
	    DatabaseOps db = new DatabaseOps("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/shopit", "root", "");
		
	    String query = "select orderid, sku, pdt_name, quantity, amount, date, status from order_details "
	    		+ "where uname = '" + uname + "'";
	    
	    ResultSet rs = db.dbselect(query);
	    
	    try {
			while (rs.next()) {
				al_orderid.add(rs.getString("orderid"));
				al_sku.add(rs.getString("sku"));
				al_product.add(rs.getString("pdt_name"));
				al_quantity.add(rs.getString("quantity"));
				al_amount.add(rs.getString("amount"));
				al_date.add(rs.getString("date"));
				
				if (rs.getString("status").compareTo("Processed") != 0) {
					sqlDate = rs.getDate("date");
					orderdate =  new java.util.Date(sqlDate.getTime());
					currentdate = new java.util.Date();
			    	order_date.setTime(orderdate);
			    	current_date.setTime(currentdate);
			    	orderdate = order_date.getTime();
			    	currentdate = current_date.getTime();
			    	startTime = orderdate.getTime();
			    	endTime = currentdate.getTime();
			    	diffTime = endTime - startTime;
			    	diffDays = diffTime / (1000 * 60 * 60 * 24);
					
			    	if (diffDays > 3) {
			    		al_status.add("Processed");
			    		query = "update order_details set status = 'Processed' where orderid = '" + rs.getInt("orderid") + "'";
			    		int i = db.dbinsert(query);
					    if (i<=0) {
					    	db.dbclose();
					    	System.out.println("Failed to update status for OrderID: " + rs.getInt("orderid") + " to <Processed>");
					    	request.getRequestDispatcher("/error.jsp").forward(request, response);
					    }
			    	} else {
			    		al_status.add("Processing");
			    	}
				} else {
					al_status.add("Processed");
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
	    
	    db.dbclose();
	    
		session.setAttribute("al_orderid", al_orderid);
		session.setAttribute("al_sku", al_sku);
		session.setAttribute("al_product", al_product);
		session.setAttribute("al_quantity", al_quantity);
		session.setAttribute("al_amount", al_amount);
		session.setAttribute("al_date", al_date);
		session.setAttribute("al_status", al_status);
		request.getRequestDispatcher("/accountdetails.jsp").forward(request, response);
	}
}
