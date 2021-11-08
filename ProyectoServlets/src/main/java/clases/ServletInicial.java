package clases; 
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servletinicial")
public class ServletInicial extends HttpServlet
{
	@Override
	public void doGet (HttpServletRequest request,
					   HttpServletResponse response)
					throws ServletException, IOException{
				HttpSession sesion = request.getSession(true);
		        response.setContentType("text/html");
				PrintWriter pw= response.getWriter();
				
			ColeccionUsuario listaUsuarios = new ColeccionUsuario(); 
	 
			
	        String user = request.getParameter("user");
	        String pass= request.getParameter("pass"); 
	        
	    	
		
	        String usuario= controlUsuario(user, sesion);
	        String contrasena= controlContrasena(pass, sesion);
	        
	        Catalogo cat = new Catalogo();

	        	       
	        
	        
	        if (listaUsuarios.comprobadorTotal(usuario, contrasena)==1) {
	        	 pw.println ("<HTML>");
	        	 pw.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/ProyectoServlets/CSS/catalogo.css'>");
	        	 pw.println ("<BODY>");
	        	 pw.println("<div class='header'>");
	        	 pw.println("<h3>Delicias turcas de Trebisonda</h3>");
	        	 pw.println("</div>");
	        	 pw.println ("<H1>!Auténticas delicias turcas desde el Mar Negro!</H1>");
	        	 pw.println ("<BR>");
	        	 pw.println("<img src='https://www.grandbazaarist.com/wp-content/uploads/2020/01/Turkish-delights-colorful.jpg'>");
	             pw.println ("<h3>Hola " + usuario+"</h3>");
	             pw.println("<form action='/ProyectoServlets/servletcatalogo' method='post'>"); 
	             pw.println("<h4>¿Qué te apetece?</h5>"); 
	             pw.println(cat.toString()); 
	             pw.println("<input type='submit' id='boton' value='Hacer pedido'>");
	             pw.println("</form>"); 

	            	             
	        	
	        }
	        else if (listaUsuarios.comprobadorTotal(usuario, contrasena)==0) {
	        	 pw.println ("<HTML>");
	        	 pw.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/ProyectoServlets/CSS/error.css'>");
	        	 pw.println ("<BODY>");
	        	 pw.println("<div class='header'>");
	        	 pw.println("<h3>Delicias turcas de Trebisonda</h3>");
	        	 pw.println("</div>");
	        	 pw.println("<img src='https://www.iconpacks.net/icons/1/free-error-icon-905-thumb.png'>");
	        	 pw.println ("<BR>");
	        	 pw.println ("<H1>¡Vaya!</H1>");
	        	 pw.println ("<BR>");
	             pw.println (usuario + " te has equivocado de contraseña");
	             pw.println ("<BR>");
	        	 pw.println ("<A HREF='http://localhost:8080/ProyectoServlets/HTML/login.html'>Vuelve a intentarlo</A>");
	        }
	        else if (listaUsuarios.comprobadorTotal(usuario, contrasena)==-1 && !usuario.equals("Tramposo") && !contrasena.equals("Tramposo")) {
	        		pw.println ("<HTML>");
		        	pw.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/ProyectoServlets/CSS/error.css'>");
	        		pw.println ("<BODY>");
	        		pw.println("<div class='header'>");
		        	pw.println("<h3>Delicias turcas de Trebisonda</h3>");
		        	pw.println("</div>");
		        	pw.println("<img src='https://www.iconpacks.net/icons/1/free-error-icon-905-thumb.png'>");
	        		pw.println ("<H1>Lo sentimos</H1>");
	        		pw.println ("<BR>");
	        		pw.println ("El usuario o la contraseña son incorrect@s");
	        		pw.println ("<BR>");
	        		pw.println ("<A HREF='http://localhost:8080/ProyectoServlets/HTML/login.html'>Vuelve a intentarlo</A>");	        
	        }
	        else {
	        	pw.println ("<HTML>");
	        	 pw.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/ProyectoServlets/CSS/error.css'>");
	        	 pw.println ("<BODY>");
	        	 pw.println("<div class='header'>");
	        	 pw.println("<h3>Delicias turcas de Trebisonda</h3>");
	        	 pw.println("</div>");
	        	 pw.println("<img src='https://www.iconpacks.net/icons/1/free-error-icon-905-thumb.png'>");
	        	 pw.println ("<BR>");
	        	 pw.println ("<H1>¡Vaya!</H1>");
	        	 pw.println ("<BR>");
	             pw.println ("Tienes que loguearte para acceder al catálogo");
	             pw.println ("<BR>");
	        	 pw.println ("<A HREF='http://localhost:8080/ProyectoServlets/HTML/login.html'>Vuelve a inicio</A>");
	        }
	}
	    // Metodo para POST
	 	@Override
	    public void doPost(HttpServletRequest request,
	                       HttpServletResponse response)
	                    throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    public String controlUsuario(String usuario, HttpSession sesion) {
	    	if (usuario!=null){
				sesion.setAttribute("userSaved", usuario);
			}
	    	else if(usuario==null && sesion.getAttribute("userSaved")==null) {
	    		usuario="Tramposo"; 
	    		
	    	}
	    	else {
				usuario=sesion.getAttribute("userSaved").toString();
	    	}
	    	return usuario; 			
	    }
	    
	    public String controlContrasena(String contrasena, HttpSession sesion) {
			if (contrasena!=null){
				sesion.setAttribute("passSaved", contrasena);
			}
			
			else if(contrasena==null && sesion.getAttribute("passSaved")==null) {
	    		contrasena="Tramposo"; 
	    		
	    	}
			else {
				contrasena=sesion.getAttribute("passSaved").toString();
			}
	    	return contrasena; 			

	    }
}