package clases; 

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servletfactura")
public class ServletFactura extends HttpServlet
{	
	@Override
	public void doGet (HttpServletRequest request,
					   HttpServletResponse response)
					throws ServletException, IOException{
				HttpSession sesion = request.getSession(true);
		        response.setContentType("text/html");
				PrintWriter pw= response.getWriter();
				
				
			        	 
			
	        String opcion = request.getParameter("option");
 

	        TipoEnvios envios = new TipoEnvios();
	        
	        if (opcion== null && sesion.getAttribute("aPagar")== null && sesion.getAttribute("userSaved")==null) {
	        	pw.println("<html>");
	        	pw.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/ProyectoServlets/CSS/error.css'>");
	        	pw.println("<body>");
	        	pw.println("<div class='header'>");
	        	pw.println("<h3>Delicias turcas de Trebisonda</h3>");
	        	pw.println("</div>");
	        	pw.println("<img src='https://www.iconpacks.net/icons/1/free-error-icon-905-thumb.png'>");
		        pw.println("Para poder comprar debes acceder con tu cuenta");
		        pw.println("<br>");
	            pw.println("<form action='http://localhost:8080/ProyectoServlets/HTML/login.html' method='post'>"); 
	            pw.println("<input type='submit' id='boton' value='Volver a inicio'>");
	            pw.println("</form>"); 
	            //Control de acceso sin haberse logueado
	        	
	        }
	        
	        else if (opcion==null) {
	        	 pw.println ("<HTML>");
		         pw.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/ProyectoServlets/CSS/error.css'>");	    	   
	        	 pw.println ("<BODY>");
		         pw.println("<div class='header'>");
		         pw.println("<h3>Delicias turcas de Trebisonda</h3>");
		         pw.println("</div>");
		    	 pw.println("<img src='https://img.icons8.com/dotty/480/facepalm.png'>");
	        	 pw.println("¡Atención "+ sesion.getAttribute("userSaved")+"!"); 
			     pw.println("<br>");
			     pw.println("No has seleccionado ningún tipo de envío");
			     pw.println("<br>");
		         pw.println("<form action='/ProyectoServlets/servletenvio' method='post'>"); 
		         pw.println("<input type='submit' id='boton' value='Volver a seleccionar el tipo de envío'>");
		         pw.println("</form>"); 	
		         //Si se ha accedido sin haber seleccionado ningún tipo de envío
	        	
	        }
	        else  {
				 String totalParaParsear= sesion.getAttribute("aPagar").toString();
			     double total= Double.parseDouble(totalParaParsear); 
	        	 pw.println ("<HTML>");
		         pw.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/ProyectoServlets/CSS/factura.css'>");	    	   
	        	 pw.println ("<BODY>");
		         pw.println("<div class='header'>");
		         pw.println("<h3>Delicias turcas de Trebisonda</h3>");
		         pw.println("</div>");
		    	 pw.println("<img src='https://i.pinimg.com/originals/f3/ba/61/f3ba618c9b657a86e6dd6764752747d2.png'>");
	             pw.println ("<h4>Has seleccionado el envío "+ opcion +"</h4>");
	             double precioEnvio= recuperadorValores(envios, opcion); 
	             double costeTotal = Math.round((total+precioEnvio)*100.0)/100.0;
	        	 pw.println ("El coste total es de "+ costeTotal+" euros: "+ total + " euros por las delicias y " +precioEnvio+ " euros por el envío" );
	        	 pw.println("<br>");
	        	 pw.println("<form action='/ProyectoServlets/servletinicial' method='post'>"); 
		         pw.println("<input type='submit' id='boton' value='Deshacer el pedido'>");
		         pw.println("</form>");
		         pw.println("<form action='/ProyectoServlets/servletenvio' method='post'>"); 
		         pw.println("<input type='submit' id='boton' value='Volver a seleccionar el tipo de envío'>");
		         pw.println("</form>");
		         pw.println("<form action='/ProyectoServlets/servletfinal' method='post'>"); 
		         pw.println("<input type='submit' id='boton' value='Realizar el pago'>");
		         pw.println("</form>");
	        	 sesion.setAttribute("toSend", opcion);

		         // Si se ha seleccionado el envío, lo guarda en servidor, te da las opciones de volver a elegirlo, borrar el carrito o pagar
	        } 
	        

	       }
	    // Metodo para POST
		@Override
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
	    // Método para recuperar los valores de nuestro HashSet
	   
	    
}