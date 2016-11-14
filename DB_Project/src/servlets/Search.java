package servlets;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search
 */

public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// To change body of generated methods, choose Tools | Templates.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String dept_time = request.getParameter("departure_time");
		String vacancy = request.getParameter("vacancy");
		HttpSession session = request.getSession(false);
		try{
		
		System.out.println("insearch"+session.getAttribute("id").toString());
		}
		catch (Exception e2) {
			System.out.println("there is no active session ....pleaselogin");
			response.sendRedirect("home_page.html");
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_project", "root", "agilefant");
			PreparedStatement ps = con.prepareStatement("CALL db_project.search_carrier(?,?,?,?)");
			ps.setString(1, from);
			ps.setString(2, to);
			ps.setString(3, dept_time);
			ps.setString(4, vacancy);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			pw.print("<!DOCTYPE html><html><head><meta charset='ISO-8859-1'><title>amar</title><style type='text/css'>.header{background-color: #ECF0F1;overflow: hidden;}a{text-decoration: none;text-align: center;}a:HOVER{box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0		rgba(0, 0, 0, 0.19);	color: #000000;	background-color: #FFFFFF;}#reference{margin: 0px 90px 0px 212px;width: 200px;height: 70px;float: left;}#reference1{margin: 0px 90px 0px 212px;width: 200px;height: 70px;float: right;}</style></head><body bgcolor='orange' style='margin: 0px;'><div class=header><a id='reference' href='home_page.html'>HOME</a><a id='reference1' href='user_selection.html'>SEARCH</a></div>");
			pw.print("<table width=50% border=1>");
			pw.print("<caption>Result:</caption>");

			int total = rsmd.getColumnCount();
			pw.print("<tr>");
			for (int i = 1; i <= total; i++) {
				pw.print("<th>" + rsmd.getColumnName(i) + "</th>");
			}

			pw.print("</tr>");
			if (rs != null) {
				while (rs.next()) {
					pw.print(
							"<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3)
									+ "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(5) + "</td><td>"
									+ rs.getString(6) + "</td><td>" + rs.getString(7) + "</td><td>" + rs.getString(8)
									+ "</td><td>" + rs.getString(9) + "</td><td>" + rs.getString(10) + "</td></tr>");
				}
			}

			pw.print("</body></n></html>");
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		pw.close();
	}

}
