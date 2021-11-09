package clases; 

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servletadios")
public class ServletAdios extends HttpServlet
{
	@Override
	public void doGet (HttpServletRequest request,
					   HttpServletResponse response)
					throws ServletException, IOException{
				HttpSession sesion = request.getSession(true);
		        response.setContentType("text/html");
				
				sesion.invalidate(); 
			     
			        	 
			
	        	response.sendRedirect("HTML/login.html");
	        	
	        	//Te borra la sesión y te redirige al inicio

	       }
	    // Metodo para POST
		@Override
	    public void doPost(HttpServletRequest request,
	                       HttpServletResponse response)
	                    throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	 
	   
	    
}