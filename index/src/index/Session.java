package index;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class Session extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inside Session.java");
		
		HttpSession session = request.getSession(true);
		session.setAttribute("sku", request.getParameter("sku"));
		session.setAttribute("qty", request.getParameter("qty"));
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}