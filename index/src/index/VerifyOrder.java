package index;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class VerifyOrder extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		System.out.println("Inside VerifyOrder.java");
		HttpSession session = request.getSession(true);
		String sku = session.getAttribute("sku").toString();
		int qty_req = Integer.parseInt(session.getAttribute("qty").toString());
		
		try {
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			DB db = mongoClient.getDB( "shopit" );
			DBCollection coll = db.getCollection("inventory");
			BasicDBObject query = new BasicDBObject("sku", session.getAttribute("sku"));
			BasicDBObject keys = new BasicDBObject();
			keys.put("_id", 0);
			keys.put("title", 1);
			keys.put("img", 1);
			keys.put("retailp", 1);
			keys.put("description", 1);
			keys.put("startdate", 1);
			keys.put("enddate", 1);
			DBCursor cursor = coll.find(query, keys);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				while (cursor.hasNext()) {
					DBObject dbo = cursor.next();
					String price = dbo.get("retailp").toString();
					String desc = dbo.get("description").toString();
					String name = dbo.get("title").toString();
					String img = dbo.get("img").toString();
					String str_enddate = dbo.get("enddate").toString();
					
					Date startdate = sdf.parse(dbo.get("startdate").toString());
					Date enddate = sdf.parse(dbo.get("enddate").toString());
					Date currentdate = sdf.parse( sdf.format(new Date()));
					
					session.setAttribute("price", price);
					session.setAttribute("description", desc);
					session.setAttribute("product_name", name);
					session.setAttribute("img", img);
					
					if (str_enddate.isEmpty() || str_enddate.equals("")) {
						session.setAttribute("date", "valid");
					}
					else if (startdate.compareTo(currentdate) <= 0 && currentdate.compareTo(enddate) <= 0) {
						session.setAttribute("date", "valid");
		        	} else {
		        		session.setAttribute("date", "invalid");
		        	}
					
					DatabaseOps db_mysql = new DatabaseOps("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/shopit", "root", "");
				    String query2 = "select quantity from inventory where sku = '" + sku + "'";
				    ResultSet rs = db_mysql.dbselect(query2);
				    if (rs.next()) {
				    	int qty_avail = rs.getInt("quantity");
				    	if (qty_avail < qty_req) {
							session.setAttribute("stock", "no");
			        	} else {
			        		session.setAttribute("stock", "yes");
			        	}
				    } else {
				    	db_mysql.dbclose();
				    	System.out.println("No output for query: " + query2);
				    	request.getRequestDispatcher("/error.jsp").forward(request, response);
				    }
				    db_mysql.dbclose();
				    float amount = qty_req * Float.parseFloat(price);
				    session.setAttribute("amount", amount);
					request.getRequestDispatcher("/order.jsp").forward(request, response);
				}
			} finally {
				cursor.close();
				mongoClient.close();
			}
		}
		catch (Exception e) {
			System.err.println("ERROR:");
		    System.err.println(e.getMessage());
		} 
	}
}
