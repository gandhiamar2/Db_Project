package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	PrintWriter pw = response.getWriter();
	HttpSession session=request.getSession(false); 
	if(session!=null)
	{
	session.removeAttribute("id");
    session.invalidate();    
  System.out.println("You are successfully logged out!"); 
    response.sendRedirect("home_page.html");
	}
  
      
	else{
	System.out.println("there is no active session to logout");
      response.sendRedirect("home_page.html");
	}
	System.out.println("atleat servelt called");
    pw.close();  
	
	}

		
	

}
