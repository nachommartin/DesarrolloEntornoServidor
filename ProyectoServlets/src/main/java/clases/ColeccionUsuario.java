package clases;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Collection;



public class ColeccionUsuario {
	private HashMap<String,String> usuarios;


	public ColeccionUsuario() {
		super();
		usuarios = new HashMap<String,String>();
		
		 usuarios.put("Nacho", "akira");   
	     usuarios.put("Hugo", "kirby");   
	     usuarios.put("Fernan", "carlton");   
	     usuarios.put("Esther", "moron");
	}


	public HashMap<String, String> getUsuarios() {
		return usuarios;
	}
	
	public boolean comprobarUsuario(String usuario){
		Collection<String> keys = usuarios.keySet();
		boolean resul=false; 
		Iterator<String> it = keys.iterator();
        while(it.hasNext() && !resul) {
        	String cadena= it.next();
        	System.out.println(cadena);
        	if (usuario.equals(cadena)) {
				resul= true; 				
			}
			else {
				resul= false;
			}
		}
		return resul;
	}

	public boolean comprobarContrasena(String contrasena) {
		Collection<String> keys = usuarios.values();
		boolean resul=false; 
		Iterator<String> it = keys.iterator();
        while(it.hasNext() && !resul) {
        	String cadena= it.next();
        	System.out.println(cadena);
        	if (contrasena.equals(cadena)) {
				resul= true; 				
			}
			else {
				resul= false;
			}
		}
		return resul;
	}

	
	public int comprobadorTotal(String usuario, String contrasena) {
		int num; 
		if (this.comprobarUsuario(usuario) && this.comprobarContrasena(contrasena)==true) {
			num= 1; 				
		}
		else if (this.comprobarUsuario(usuario)==true && this.comprobarContrasena(contrasena)==false){
			num=0; 
		}
		else {
			num=-1; 
		}
		return num; 
	}

	@Override
	public String toString() {
		return "ColeccionUsuario [=" + usuarios + "]";
	}
	
	
	
}
