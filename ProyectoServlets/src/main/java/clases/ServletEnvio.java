package clases; 
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servletenvio")
public class ServletEnvio extends HttpServlet
{	
	@Override
	public void doGet (HttpServletRequest request,
					   HttpServletResponse response)
					throws ServletException, IOException{
				HttpSession sesion = request.getSession(true);
		        response.setContentType("text/html");
				PrintWriter pw= response.getWriter();
				
			TipoEnvios envios = new TipoEnvios(); 
	 
	    			
		
	        String totalParaParsear= sesion.getAttribute("aPagar").toString();
	        double total= Double.parseDouble(totalParaParsear); 
	        
	        
	        if (total>0) {
	        	 pw.println ("<HTML>");
		         pw.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/ProyectoServlets/CSS/carrito.css'>");	    	   
	        	 pw.println ("<BODY>");
	             pw.println ("Ahora selecciona tu envío");
	             pw.println ("<BR>");
	             pw.println("<form action='/ProyectoServlets/servletfactura' method='post'>"); 
	             pw.println(envios.toString()); 
	             pw.println("<input type='submit' value='Tramitar pedido'>");
	             pw.println("</form>"); 
	        	
	        }
	        else {
	        	pw.println ("<HTML>");
	        	pw.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/ProyectoServlets/CSS/error.css'>");	    	   
	        	pw.println ("<BODY>");
	    	   	pw.println("¡Atención "+ sesion.getAttribute("userSaved")+"!"); 
		        pw.println("<br>");
		        pw.println("Tu carrito está vacío");
		        pw.println("<br>");
	            pw.println("<form action='/ProyectoServlets/servletinicial' method='post'>"); 
	            pw.println("<input type='submit' value='Volver al catálogo'>");
	            pw.println("</form>"); 
	       }
	}
	    // Metodo para POST
		@Override
	    public void doPost(HttpServletRequest request,
	                       HttpServletResponse response)
	                    throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	   
}
