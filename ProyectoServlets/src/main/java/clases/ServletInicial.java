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
	        
	        Catalogo cat = new Catalogo();
	        
	        if (listaUsuarios.comprobadorTotal(usuario, contrasena)==1) {
	        	 pw.println ("<HTML>");
	        	 pw.println ("<BODY>");
	        	 pw.println ("<H1>Delicias turcas de Trebisonda</H1>");
	        	 pw.println ("<BR>");
	             pw.println ("Bienvenido " + usuario);
	             pw.println ("<BR>");
	             pw.println("<form action='/ProyectoServlets/servletcatalogo' method='post'>"); 
	             pw.println("<h5>Productos:</h5>"); 
	             pw.println ("<BR>");
	             pw.println(cat.toString()); 
	            	             
	        	
	        }
	        else if (listaUsuarios.comprobadorTotal(usuario, contrasena)==0) {
	        	pw.println ("<HTML>");
	        	 pw.println ("<BODY>");
	        	 pw.println ("<H1>Vaya</H1>");
	        	 pw.println ("<BR>");
	             pw.println (usuario + " te has equivocado de contraseña");
	             pw.println ("<BR>");
	        	 pw.println ("<A HREF='http://localhost:8080/ProyectoServlets/HTML/login.html'>Vuelve a intentarlo</A>");
	        }
	        else if (listaUsuarios.comprobadorTotal(usuario, contrasena)==-1) {
	        		pw.println ("<HTML>");
	        		pw.println ("<BODY>");
	        		pw.println ("<H1>Lo sentimos</H1>");
	        		pw.println ("<BR>");
	        		pw.println ("El usuario o la contraseña son incorrect@s");
	        		pw.println ("<BR>");
	        		pw.println ("<A HREF='http://localhost:8080/ProyectoServlets/HTML/login.html'>Vuelve a intentarlo</A>");	        
	        }
	        else {
	        	pw.println ("<HTML>");
	        	pw.println ("<BODY>");
	        	pw.println ("<H1>Lo sentimos</H1>");
	        	pw.println ("<BR>");
	        	pw.println ("Error desconocido");
	        	pw.println ("<BR>");
	        	pw.println ("<A HREF='http://localhost:8080/ProyectoServlets/HTML/login.html'>Volver a inicio</A>");
	        }
	}
	    // Metodo para POST
	 
	    public void doPost(HttpServletRequest request,
	                       HttpServletResponse response)
	                    throws ServletException, IOException {
	        doGet(request, response);
	    }
}

