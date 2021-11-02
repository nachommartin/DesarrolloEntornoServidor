package clases; 
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class ServletInicial extends HttpServlet
{
	public void doGet (HttpServletRequest request,
					   HttpServletResponse response)
					throws ServletException, IOException{
				HttpSession sesion = request.getSession(true);
				PrintWriter pw= response.getWriter();
				
			ColeccionUsuario listaUsuarios = new ColeccionUsuario(); 
	 
	      
	        String Usuario =
	            request.getParameter("texto1");
	        
	 
	}
	    // Metodo para POST
	 
	    public void doPost(HttpServletRequest request,
	                       HttpServletResponse response)
	                    throws ServletException, IOException {
	        doGet(request, response);
	    }
}

