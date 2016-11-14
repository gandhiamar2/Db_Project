package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Carrier_log extends HttpServlet {
	private static final long serialVersionUID = 1L;






	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/plain");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession(false);



		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_project", "root", "agilefant");
			String email = session.getAttribute("id").toString();
			PreparedStatement ps = con.prepareStatement("CALL Db_Project.log_carrier(?)");	
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rms = rs.getMetaData();
			pw.print("<H1> select the entry to edit </H1>");
			pw.print("<button id = 'mytest'>clickmeforlert</button>");

			pw.print("<table width=50% border=1>");
			int total = rms.getColumnCount();
			pw.print("<tr><th>slector</th>");

			for (int i = 2; i <= total; i++) {

				pw.print("<th>" + rms.getColumnName(i) + "</th>");
			}

			pw.print("</tr>");

			pw.print("<form action = 'Carrier_update' id='carrier_update_form' method = 'get'>");

			//extracting flight_id to be value for radio button
			if (rs != null) {

				while (rs.next()) {

					int a = Integer.parseInt(rs.getString(1));					
					String logvalue = "<td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4)+ "</td><td>" + rs.getString(5) + "</td><td>" + rs.getString(6)  + "</td></tr>";
					//String logvalue2 = rs.getString(2) + rs.getString(3) + rs.getString(4) +rs.getString(5)+rs.getString(6);
					pw.print("<tr><td><input type = 'radio' name = 'radio_button' form='carrier_update_form' value ="+a+"></td>"+logvalue );

				}
			}
			pw.print("</br><input type = 'submit' id = 'carrier_log_submit' form='carrier_update_form' value = 'Edit'></form>");

		} 
		catch (NullPointerException e)
		{

			System.out.println("no active session please login/carrier_login");
			response.sendRedirect("home_page.html");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

}
