package clases; 

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servletfinal")
public class ServletFinal extends HttpServlet
{
	public void doGet (HttpServletRequest request,
					   HttpServletResponse response)
					throws ServletException, IOException{
				HttpSession sesion = request.getSession(true);
		        response.setContentType("text/html");
				PrintWriter pw= response.getWriter();
				
				sesion.removeAttribute("aPagar");
			     
			        	 
			
	        	 pw.println ("<HTML>");
	        	 pw.println ("<BODY>");
	        	 pw.println("�Gracias "+ sesion.getAttribute("userSaved")+" por tu compra!"); 
			     pw.println("<br>");
			     pw.println("<br>");
		         pw.println("<form action='/ProyectoServlets/servletinicial' method='post'>"); 
		         pw.println("<input type='submit' value='Volver a comprar'>");
		         pw.println("</form>"); 	
		         pw.println("<form action='/ProyectoServlets/servletadios' method='post'>"); 
		         pw.println("<input type='submit' value='Cerrar sesi�n y salir'>");
		         pw.println("</form>"); 	            	             


	       }
	    // Metodo para POST
	 
	    public void doPost(HttpServletRequest request,
	                       HttpServletResponse response)
	                    throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	 
	   
	    
}