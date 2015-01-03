package index;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class ValidateLogin extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		System.out.println("Inside ValidateLogin.java");
		
		HttpSession session = request.getSession(true);
		String user = request.getParameter("username");
	    String pwd = request.getParameter("password");
	    
	    DatabaseOps db = new DatabaseOps("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/shopit", "root", "");
	    String query = "select * from user_acc where uname = '" + user + "' and pwd = sha1('" + pwd + "')";
	    ResultSet rs = db.dbselect(query);
	    try {
			if (rs.next()) {
				session.setAttribute("userid", user);
				db.dbclose();
				request.getRequestDispatcher("/verifyorder").forward(request, response);
			} else {
				System.out.println("Invalid login for user <" + user + ">");
				db.dbclose();
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    db.dbclose();
	}
}
