package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;



public class Carrier_update extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);	
		PrintWriter pw = response.getWriter();
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_project", "root", "agilefant");
			int a = Integer.parseInt(request.getParameter("radio_button"));
			String email = session.getAttribute("id").toString();
			session.setAttribute("autoid",a);
			//System.out.println(email);
			PreparedStatement ps = con.prepareStatement("CALL Db_project.selected_log_carrier(?,?)");
			ps.setString(1,email);
			ps.setInt(2, a);

			ResultSet rs = ps.executeQuery();
			
			String s[] = new String[13];
			if(rs!=null)
			{
				while(rs.next()){
				for (int i = 1; i < 12; i++) {
					s[i] = rs.getString(i);
				}
				}
			}
//System.out.println(s[1]);
			pw.print("<!DOCTYPE html><html><head><meta charset='ISO-8859-1'><title>amar</title></head><body>");

			pw.print("<H1> select the entry to edit </H1>");	
			pw.print("<div class='carrier_details_form'>");
			pw.print("<form id='form' action='CarrierDetails_Update' method = 'get'>");
			pw.print("<ul>");
			pw.print("<li><label class='form_label'>FIRST NAME :</label></br> <input ");
			pw.print("class='form_input' type='text' name='first_name'");
			pw.print("value="+s[1]+"></li>");
			pw.print("<li><label class='form_label'>LAST NAME :</label></br> <input ");
			pw.print("class='form_input' type='text' name='last_name'");
			pw.print("value="+s[2]+"></li>");
			pw.print("<li><label class='form_label'>CONTACT NUMBER :</label></br> <input ");
			pw.print("class='form_input' type='text' name='contact_number'");
			pw.print("value="+s[3]+"></li>");
			pw.print("<li><label class='form_label'>FLIGHT NAME :</label></br> <input ");
			pw.print("class='form_input' type='text' name='flight_name'");
			pw.print("value="+s[4]+"></li>");
			pw.print("<li><label class='form_label'>FLIGHT NUMBER :</label></br> <input ");
			pw.print("class='form_input' type='text' name='flight_number'");
			pw.print("value="+s[5]+ "></li>");
			pw.print("<li><label class='form_label'>Departing from :</label></br> <input ");
			pw.print("class='form_input' type='text' name='from'");
			pw.print("value="+s[6]+"></li>");
			pw.print("<li><label class='form_label'>Flying To :</label></br> <input ");
			pw.print("class='form_input' type='text' name='to'");
			pw.print("value="+s[7]+ "></li>");
			pw.print("<li><label class='form_label'>Departure Time :</label></br> <input ");
			pw.print("class='form_input' type='datetime-local' name='departure_time'");
			pw.print("value="+s[8]+"></li>");
			pw.print("<li><label class='form_label'>Arrival Time :</label></br> <input ");
			pw.print("class='form_input' type='datetime-local' name='arrival_time'");
			pw.print("value="+s[9]+"></li>");
			pw.print("<li><label class='form_label'>Luggage Weight :</label></br> <input ");
			pw.print("class='form_input' type='text' name='vacancy'");
			pw.print("value="+s[10]+"></li>");
			pw.print("<li><label class='form_label'>Description :</label></br> <textarea ");
			pw.print("rows='8' cols='15' class='form_input' name='description'>"+s[11]+" </textarea></li>");
			pw.print("<li><input type='submit' value='UPDATE'></li>");
			pw.print("</ul>");
			pw.print("</form>");
			pw.print("</div></body>");
		} 
		catch (NullPointerException e)
		{
			System.out.println("there is no active session ///////carrier_update");
			response.sendRedirect("home_page.html");

		}	

		catch (Exception e) {

			e.printStackTrace();
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
