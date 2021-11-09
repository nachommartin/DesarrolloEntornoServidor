package clases; 

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servletfinal")
public class ServletFinal extends HttpServlet
{	
	@Override
	public void doGet (HttpServletRequest request,
					   HttpServletResponse response)
					throws ServletException, IOException{
				HttpSession sesion = request.getSession(true);
		        response.setContentType("text/html");
				PrintWriter pw= response.getWriter();
				
				if (sesion.getAttribute("userSaved")==null) {
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
		            //Control de si se ha accedido sin haberse logueado
		        	
				}
				
				else if (sesion.getAttribute("aPagar")==null){
					pw.println("<html>");
		        	pw.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/ProyectoServlets/CSS/error.css'>");	    	   
		        	pw.println("<body>");
		        	pw.println("<div class='header'>");
		        	pw.println("<h3>Delicias turcas de Trebisonda</h3>");
		        	pw.println("</div>");
		    	   	pw.println("<img src='https://img.icons8.com/dotty/480/facepalm.png'>");
			        pw.println(sesion.getAttribute("userSaved") + " tu carrito está vacío");
			        pw.println("<br>");
		            pw.println("<form action='http://localhost:8080/ProyectoServlets/servletinicial' method='post'>"); 
		            pw.println("<input type='submit' id='boton' value='Ve al catálogo'>");
		            pw.println("</form>"); 
		            // Control de si se ha accedido con sesión pero sin carrito creado o sin envío seleccionado
		        	
				}
				
				else if (sesion.getAttribute("toSend")==null){
					pw.println("<html>");
		        	pw.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/ProyectoServlets/CSS/error.css'>");	    	   
		        	pw.println("<body>");
		        	pw.println("<div class='header'>");
		        	pw.println("<h3>Delicias turcas de Trebisonda</h3>");
		        	pw.println("</div>");
		    	   	pw.println("<img src='https://img.icons8.com/dotty/480/facepalm.png'>");
			        pw.println(sesion.getAttribute("userSaved") + " no seleccionaste ningún tipo de envío");
			        pw.println("<br>");
		            pw.println("<form action='http://localhost:8080/ProyectoServlets/servletinicial' method='post'>"); 
		            pw.println("<input type='submit' id='boton' value='Ve al catálogo'>");
		            pw.println("</form>"); 
		            // Control de si se ha accedido con sesión pero sin envío seleccionado
		        	
				}
			        	 
				else {
				
				 sesion.removeAttribute("aPagar");
				 sesion.removeAttribute("toSend");
	        	 pw.println ("<HTML>");
	        	 pw.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/ProyectoServlets/CSS/galata.css'>");	    	   
	        	 pw.println ("<BODY>");
		         pw.println("<div class='header'>");
		         pw.println("<h3>Delicias turcas de Trebisonda</h3>");
		         pw.println("</div>");
		    	 pw.println("<img src='https://upload.wikimedia.org/wikipedia/commons/0/05/Galata_Bridge_-_Istanbul%2C_Turkey_-_panoramio.jpg'>");
	        	 pw.println("<h4>¡Gracias "+ sesion.getAttribute("userSaved")+" por tu compra!</h4>"); 
			     pw.println("<br>");
		         pw.println("<form action='/ProyectoServlets/servletinicial' method='post'>"); 
		         pw.println("<input type='submit' id='boton' value='Volver a comprar'>");
		         pw.println("</form>"); 	
		         pw.println("<form action='/ProyectoServlets/servletadios' method='post'>"); 
		         pw.println("<input type='submit' id='boton' value='Cerrar sesión y salir'>");
		         pw.println("</form>"); 	
		         // Confirmación de que se ha pagado, te da la opción de volver a comprar y se borra el carrito y el envío de la sesión o salir con otro servlet
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