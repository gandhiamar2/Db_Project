package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTML;

public class CarrierDetails_Update extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter pw = response.getWriter();
		

		try {
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
				int fi_id = (int) session.getAttribute("autoid");
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_project", "root", "agilefant");
				PreparedStatement ps = con.prepareStatement("call update_carrier_entry (?,?,?,?,?,?,?,?,?,?,?,?,? )");
				ps.setInt(1, fi_id);
				ps.setString(2, carrier_first_name);
				ps.setString(3, carrier_last_name);
				ps.setString(4, carrier_phone);
				ps.setString(5, carrier_email);
				ps.setString(6, flight_name);
				ps.setString(7, flight_number);
				ps.setString(8, flight_source);
				ps.setString(9, flight_dest);
				ps.setString(10, flight_dept_time);
				ps.setString(11, flight_arr_time);
				ps.setString(12, carrier_luggage_vacancy);
				ps.setString(13, carrier_description);
				
				int check = ps.executeUpdate();
				session.removeAttribute("autoid");
				
				if(check != 0)
				{
					System.out.println("update sucessful");
					response.sendRedirect("user_selection.html");
					
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.sendRedirect("user_selection.html");
			System.out.println("error not updated try again");
		}



	}



}
