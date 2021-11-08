package clases; 
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servletcatalogo")
public class ServletCatalogo extends HttpServlet
{
	@Override	
	public void doGet (HttpServletRequest request,
					   HttpServletResponse response)
					throws ServletException, IOException{
// Como el doPost recupera el doGet he probado a hacerlo todo con el doPost y dejo esto vacío para ver si funciona, y sí
	}
	    // Metodo para POST
		@Override
	    public void doPost(HttpServletRequest request,
	                       HttpServletResponse response)
	                    throws ServletException, IOException {
			HttpSession sesion = request.getSession(true);


	    	response.setContentType("text/html");
			PrintWriter pw= response.getWriter();
	
			
			String canela = request.getParameter("cajacanela");
	        String almendra = request.getParameter("cajaalmendra");
	        String nuez = request.getParameter("cajanuez");
	        String pistacho = request.getParameter("cajapistacho");
	        String avellana = request.getParameter("cajaavellana");

	        Catalogo cat = new Catalogo(); 
	        
	        if (canela.equals("")){
	        	canela="0"; 
	        }
	        
	        if (almendra.equals("")){
	        	almendra="0"; 
	        }
	        
	        if (nuez.equals("")){
	        	nuez="0"; 
	        }
	        
	        if (pistacho.equals("")){
	        	pistacho="0"; 
	        }
	        
	        if (avellana.equals("")){
	        	avellana="0"; 
	        }
	        
	       double precioCanela = cat.sumaCanela(Integer.parseInt(canela));
	       double precioAlmendra = cat.sumaAlmendra(Integer.parseInt(almendra));
	       double precioNuez = cat.sumaAvellanaNuez(Integer.parseInt(nuez));
	       double precioPistacho = cat.sumaPistacho(Integer.parseInt(pistacho));
	       double precioAvellana = cat.sumaAvellanaNuez(Integer.parseInt(avellana));

    	   StringBuilder listaCompra= new StringBuilder();

	       
	       if (precioCanela>0) {
		        listaCompra.append("Has seleccionado "+ canela +" delicia(s) de canela por un valor de "+ precioCanela +" euros");
		        listaCompra.append("<br>");

	       } 
	       
	       if(precioAlmendra>0) {
	    	   listaCompra.append("Has seleccionado "+ almendra +" delicia(s) de almendra por un valor de "+ precioAlmendra+" euros");
	    	   listaCompra.append("<br>");
	       }
	       
	       if(precioNuez>0) {
	    	   listaCompra.append("Has seleccionado "+ nuez +" delicia(s) de nuez por un valor de "+ precioNuez+" euros");
	    	   listaCompra.append("<br>");
	       }
	       
	       if(precioPistacho>0) {
	    	   listaCompra.append("Has seleccionado "+ pistacho +" delicia(s) de pistacho por un valor de "+ precioPistacho+" euros");
	    	   listaCompra.append("<br>");
	       }

	       if(precioAvellana>0) {
	    	   listaCompra.append("Has seleccionado "+ avellana +" delicia(s) de avellana por un valor de "+ precioAvellana+" euros");
	    	   listaCompra.append("<br>");
	       }

	       double total= cat.sumaTotal(precioCanela, precioAlmendra, precioNuez, precioPistacho, precioAvellana);

	       if(total<=0) {
	    	    pw.println("<html>");
	        	pw.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/ProyectoServlets/CSS/error.css'>");	    	   
	    	    pw.println("<body>");
	        	pw.println("<div class='header'>");
	        	pw.println("<h3>Delicias turcas de Trebisonda</h3>");
	        	pw.println("</div>");
	    	   	pw.println("<img src='https://img.icons8.com/dotty/480/facepalm.png'>");
	    	   	pw.println("¡Atención "+ sesion.getAttribute("userSaved")+"!"); 
		        pw.println("<br>");
		        pw.println("No has seleccionado ningún producto");
		        pw.println("<br>");
	            pw.println("<form action='/ProyectoServlets/servletinicial' method='post'>"); 
	            pw.println("<input type='submit' id='boton' value='Volver al catálogo'>");
	            pw.println("</form>"); 
	       }
	       else {
	    	   pw.println("<html>");
	    	   pw.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/ProyectoServlets/CSS/error.css'>");	    	   
	    	   pw.println("<body>");
	    	   pw.println("<div class='header'>");
	    	   pw.println("<h3>Delicias turcas de Trebisonda</h3>");
	    	   pw.println("</div>");
	    	   pw.println("<img src='https://www.pngall.com/wp-content/uploads/5/Shopping-Cart-PNG-Free-Image.png'>");
	    	   pw.println(listaCompra.toString());
	    	   pw.println("<h4>El precio total es "+total +" euros</h4>");
		       pw.println("<br>");
	    	   pw.println("<form action='/ProyectoServlets/servletinicial' method='post'>"); 
	           pw.println("<input type='submit' id='boton' value='Deshacer el pedido'>");
	           pw.println("</form>"); 
	           pw.println("<form action='/ProyectoServlets/servletenvio' method='post'>"); 
	           pw.println("<input type='submit' id='boton' value='Realizar el pedido'>");
	           pw.println("</form>");
	           sesion.setAttribute("aPagar", total);
	       }
	       
	      



	    }
	    
}