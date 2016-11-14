package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
/**
 * @author pruthvi u.v.s.s
 *
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String error="LOGIN FAILED ! TRY AGAIN";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_project", "root", "agilefant");
			PreparedStatement ps = con
					.prepareStatement("select * from user where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			boolean status = false;
			status = rs.next();

			if (status) {
				HttpSession session=request.getSession();  
		        session.setAttribute("id",email); 
		       
		        System.out.println(session.getAttribute("id").toString());        
				response.sendRedirect("user_selection.html");
			}else {
		response.sendRedirect("home_page.html");
		System.out.println(error);		
				
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

}
