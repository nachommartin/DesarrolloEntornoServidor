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
	 
	    			
		
			double total= convertidor(sesion); 
	        
	        if (total<0) {
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
	            //Control de acceso sin loguearse
	        	
	        }
	        
	        else if (total>0) {
	        	 pw.println ("<HTML>");
                 pw.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/ProyectoServlets/CSS/envio.css'>");
	        	 pw.println ("<BODY>");
	        	 pw.println("<div class='header'>");
		    	 pw.println("<h3>Delicias turcas de Trebisonda</h3>");
		    	 pw.println("</div>");
		    	 pw.println("<img src='https://i.pinimg.com/originals/c5/c4/b7/c5c4b7a1b5831e52f33ac59d333c9b8f.png'>");
	             pw.println ("<h4>Ahora selecciona tu envío</h4>");
	             pw.println ("<BR>");
	             pw.println("<form action='/ProyectoServlets/servletfactura' method='post'>"); 
	             pw.println(envios.toString()); 
	             pw.println("<input type='submit' id='boton' value='Tramitar pedido'>");
	             pw.println("</form>"); 
	             //Si el carrito está lleno que seleccione el envío 
	        	
	        }
	        else {
	        	pw.println("<html>");
		        pw.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/ProyectoServlets/CSS/error.css'>");	    	   
	        	pw.println("<body>");
	        	pw.println("<div class='header'>");
		        pw.println("<h3>Delicias turcas de Trebisonda</h3>");
		        pw.println("</div>");
		    	pw.println("<img src='https://img.icons8.com/dotty/480/facepalm.png'>");
	    	   	pw.println("¡Atención "+ sesion.getAttribute("userSaved")+"!"); 
		        pw.println("<br>");
		        pw.println("Tu carrito está vacío");
		        pw.println("<br>");
	            pw.println("<form action='/ProyectoServlets/servletinicial' method='post'>"); 
	            pw.println("<input type='submit' id='boton' value='Volver al catálogo'>");
	            pw.println("</form>"); 
	            //Si está logueado pero se ha saltado un paso y tiene el carrito vacío
	       }
	}
	    // Metodo para POST
		@Override
	    public void doPost(HttpServletRequest request,
	                       HttpServletResponse response)
	                    throws ServletException, IOException {
	        doGet(request, response);
	    }
		
	    
	    public double convertidor(HttpSession sesion) {
	    	double total; 
	    	if (sesion.getAttribute("aPagar")!=null){
	    		String cantidad=sesion.getAttribute("aPagar").toString();
		        total= Double.parseDouble(cantidad); 
		        // Si el total está en la sesión se recupera y se pasa a double
			}
	    	else if(sesion.getAttribute("aPagar")==null && sesion.getAttribute("userSaved")==null) {
	    		total = -1; 
	    		//Para controlar que se ha accedido sin haberse logueado
	    		
	    	}
	    	else {
	    		total=0; 
	    		// Para controlar que se ha logueado pero se ha saltado un paso sin haber llenado su carrito
	    	}
	    	return total; 			
	    }
	    
	   
}
