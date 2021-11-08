package clases; 
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servletcatalogo")
public class ServletCatalogo extends HttpServlet
{
	public void doGet (HttpServletRequest request,
					   HttpServletResponse response)
					throws ServletException, IOException{
		        
	}
	    // Metodo para POST
	 
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


	       
	       if (precioCanela>0) {
		        pw.println("Has seleccionado "+ canela +" delicia(s) de canela por un valor de "+ precioCanela +" euros");
		        pw.println("<br>");

	       } 
	       
	       if(precioAlmendra>0) {
		        pw.println("Has seleccionado "+ almendra +" delicia(s) de almendra por un valor de "+ precioAlmendra+" euros");
		        pw.println("<br>");
	       }
	       
	       if(precioNuez>0) {
		        pw.println("Has seleccionado "+ nuez +" delicia(s) de nuez por un valor de "+ precioNuez+" euros");
		        pw.println("<br>");
	       }
	       
	       if(precioPistacho>0) {
		        pw.println("Has seleccionado "+ pistacho +" delicia(s) de pistacho por un valor de "+ precioPistacho+" euros");
		        pw.println("<br>");
	       }

	       if(precioAvellana>0) {
		        pw.println("Has seleccionado "+ avellana +" delicia(s) de avellana por un valor de "+ precioAvellana+" euros");
		        pw.println("<br>");
	       }

	       double total= cat.sumaTotal(precioCanela, precioAlmendra, precioNuez, precioPistacho, precioAvellana);

	       if(total<=0) {
	    	   	pw.println("�Atenci�n "+ sesion.getAttribute("userSaved")+"!"); 
		        pw.println("<br>");
		        pw.println("No has seleccionado ning�n producto");
		        pw.println("<br>");
	            pw.println("<form action='/ProyectoServlets/servletinicial' method='post'>"); 
	            pw.println("<input type='submit' value='Volver al cat�logo'>");
	            pw.println("</form>"); 
	       }
	       else {
	    	   pw.println("El precio total es "+total +" euros");
		       pw.println("<br>");
	    	   pw.println("<form action='/ProyectoServlets/servletinicial' method='post'>"); 
	           pw.println("<input type='submit' value='Deshacer el pedido'>");
	           pw.println("</form>"); 
	           pw.println("<form action='/ProyectoServlets/servletenvio' method='post'>"); 
	           pw.println("<input type='submit' value='Realizar el pedido'>");
	           pw.println("</form>");
	           sesion.setAttribute("aPagar", total);
	       }
	       
	      



	    }
	    
}