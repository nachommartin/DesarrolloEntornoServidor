package clases; 
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servletinicial")
public class ServletInicial extends HttpServlet
{
	public void doGet (HttpServletRequest request,
					   HttpServletResponse response)
					throws ServletException, IOException{
				HttpSession sesion = request.getSession(true);
		        response.setContentType("text/html");
				PrintWriter pw= response.getWriter();
				
			ColeccionUsuario listaUsuarios = new ColeccionUsuario(); 
	 
	      
	        String usuario = request.getParameter("user");
	        String contrasena= request.getParameter("pass"); 
	        
	        
	        System.out.println(listaUsuarios.toString());
	        
	        if (listaUsuarios.comprobarUsuario(usuario)==true) {
	        	 pw.println ("<HTML>");
	        	 pw.println ("<BODY>");
	        	 pw.println ("<H1>Delicias turcas de Trebisonda</H1>");
	        	 pw.println ("<BR>");
	             pw.println ("Bienvenido " + usuario);
	             pw.println ("<BR>");
	        	 pw.println ("<A HREF='catalogo.html'>Acceder al catálogo</A>");
	             
	        	
	        }
	        else { 
	        	   	pw.println("<H1>Hola</H1>");
	        }
	    
	        
	        
	        
	 
	}
	    // Metodo para POST
	 
	    public void doPost(HttpServletRequest request,
	                       HttpServletResponse response)
	                    throws ServletException, IOException {
	        doGet(request, response);
	    }
}

