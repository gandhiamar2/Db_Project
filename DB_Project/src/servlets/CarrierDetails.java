package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class CarrierDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		try{
		
		System.out.println("Incarrierdetails "+session.getAttribute("id").toString());		
	    String carrier_first_name = request.getParameter("first_name");
		String carrier_last_name = request.getParameter("last_name");
		String carrier_phone = request.getParameter("contact_number");
		String flight_name = request.getParameter("flight_name");
		String flight_number = request.getParameter("flight_number");
		String flight_dest = request.getParameter("to");
		String flight_source = request.getParameter("from");
		String flight_dept_time = request.getParameter("departure_time");
		String flight_arr_time = request.getParameter("arrival_time");
		String carrier_luggage_vacancy = request.getParameter("vacancy");
		String carrier_description = request.getParameter("description");
		String carrier_email = session.getAttribute("id").toString();
		
		
		
		
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_project", "root", "agilefant");
			PreparedStatement ps = con.prepareStatement(
					"CALL db_project.insertCarrier (?,?,?,?,?,?,?,?,?,?,?,?)");
ps.setString(1, carrier_first_name);
ps.setString(2, carrier_last_name);
ps.setString(3, carrier_phone);
ps.setString(4, carrier_email);
ps.setString(5, flight_name);
ps.setString(6, flight_number);
ps.setString(7, flight_source);
ps.setString(8, flight_dest);
ps.setString(9, flight_dept_time);
ps.setString(10, flight_arr_time);
ps.setString(11, carrier_luggage_vacancy);
ps.setString(12, carrier_description);


			int i = ps.executeUpdate();
			if (i > 0)
				System.out.println("Registered successfully : " + i);

			response.sendRedirect("sample.html");

		} 
		catch (NullPointerException e) {
						System.out.println("there is no active session ....pleaselogin");
			response.sendRedirect("home_page.html");
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.close();
		}

}
