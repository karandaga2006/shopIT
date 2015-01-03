package index;

import index.DatabaseOps;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class CreateUser extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		System.out.println("Inside CreateUser.java");
		String fname = request.getParameter("fname");    
	    String lname = request.getParameter("lname");
	    String email = request.getParameter("email");
	    String user = request.getParameter("username");
	    String pwd = request.getParameter("password");
	    
	    DatabaseOps db = new DatabaseOps("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/shopit", "root", "");
	    
    	String query = "insert into user_acc (fname, lname, email, uname, pwd)"
            + " values ('" + fname +"','" + lname + "','" + email + "','" + user + "',sha1('" + pwd + "'))";
	    System.out.println("Query: " + query);
    	int i = db.dbinsert(query);
	    if (i>0) {
	    	HttpSession session = request.getSession(true);
			session.setAttribute("userid", user);
			session.setAttribute("login", "yes");
	    } else {
	    	db.dbclose();
	    	System.out.println("Failed to add the new user <" + fname + ">");
	    	request.getRequestDispatcher("/error.jsp").forward(request, response);
	    }
	    
	    db.dbclose();
	    request.getRequestDispatcher("/verifyorder").forward(request, response);
	}
}