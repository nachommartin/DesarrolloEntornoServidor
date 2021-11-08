package clases; 

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servletfactura")
public class ServletFactura extends HttpServlet
{
	public void doGet (HttpServletRequest request,
					   HttpServletResponse response)
					throws ServletException, IOException{
				HttpSession sesion = request.getSession(true);
		        response.setContentType("text/html");
				PrintWriter pw= response.getWriter();
				
				String totalParaParsear= sesion.getAttribute("aPagar").toString();
			    double total= Double.parseDouble(totalParaParsear); 
			        	 
			
	        String opcion = request.getParameter("option");
 

	    			
	        TipoEnvios envios = new TipoEnvios();
	        
	        if (opcion==null) {
	        	 pw.println ("<HTML>");
	        	 pw.println ("<BODY>");
	        	 pw.println("�Atenci�n "+ sesion.getAttribute("userSaved")+"!"); 
			        pw.println("<br>");
			        pw.println("No has seleccionado ning�n tipo de env�o");
			        pw.println("<br>");
		            pw.println("<form action='/ProyectoServlets/servletenvio' method='post'>"); 
		            pw.println("<input type='submit' value='Volver a seleccionar el tipo de env�o'>");
		            pw.println("</form>"); 	            	             
	        	
	        }
	        else if (opcion!=null) {
	        	 pw.println ("<HTML>");
	        	 pw.println ("<BODY>");
	             pw.println ("Has seleccionado el env�o "+ opcion);
	             pw.println ("<BR>");
	             double precioEnvio= recuperadorValores(envios, opcion); 
	             double costeTotal = Math.round((total+precioEnvio)*100.0)/100.0;
	        	 pw.println ("El coste total es de "+ costeTotal+" euros: "+ total + " euros por las delicias y " +precioEnvio+ " euros por el env�o" );
	        	 pw.println("<form action='/ProyectoServlets/servletinicial' method='post'>"); 
		         pw.println("<input type='submit' value='Deshacer el pedido'>");
		         pw.println("</form>");
		         pw.println("<form action='/ProyectoServlets/servletenvio' method='post'>"); 
		         pw.println("<input type='submit' value='Volver a seleccionar el tipo de env�o'>");
		         pw.println("</form>");
		         pw.println("<form action='/ProyectoServlets/servletfinal' method='post'>"); 
		         pw.println("<input type='submit' value='Realizar el pago'>");
		         pw.println("</form>");
	        } 
	        

	       }
	    // Metodo para POST
	 
	    public void doPost(HttpServletRequest request,
	                       HttpServletResponse response)
	                    throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    public double recuperadorValores(TipoEnvios te, String cadena) {
	    	double resul=0;
	        if (te.getEnvios().containsKey(cadena)) {
	        	resul= te.getEnvios().get(cadena);
	        }

	    	return resul; 			
	    }
	    
	   
	    
}