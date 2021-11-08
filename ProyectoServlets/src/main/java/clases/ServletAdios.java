package clases; 

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servletadios")
public class ServletAdios extends HttpServlet
{
	public void doGet (HttpServletRequest request,
					   HttpServletResponse response)
					throws ServletException, IOException{
				HttpSession sesion = request.getSession(true);
		        response.setContentType("text/html");
				PrintWriter pw= response.getWriter();
				
				sesion.invalidate(); 
			     
			        	 
			
	        	response.sendRedirect("HTML/login.html");

	       }
	    // Metodo para POST
	 
	    public void doPost(HttpServletRequest request,
	                       HttpServletResponse response)
	                    throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	 
	   
	    
}