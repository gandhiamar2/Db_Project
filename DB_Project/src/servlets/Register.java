package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		String passport_number = request.getParameter("passport_number");
		String email = request.getParameter("email");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_project", "root", "agilefant");
			PreparedStatement ps = con.prepareStatement("insert into user values(?,?,?,?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, passport_number);
			int i = ps.executeUpdate();
			if (i > 0)
				System.out.println("Registered successfully : " + i);

			response.sendRedirect("home_page.html");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		pw.close();
	}

}
