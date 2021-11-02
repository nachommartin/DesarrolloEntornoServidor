package clases;

import java.util.HashMap;
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
	
	public boolean comprobarUsuario(String usuario) {
		Set<String> keys = usuarios.keySet();
		boolean resul=false; 
		for ( String key : keys ) {
			if (usuario.equals(key)) {
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
		for ( String key : keys ) {
			if (contrasena.equals(key)) {
				resul= true; 
			}
			else {
				resul= false;
			}
		}
		return resul;
	}
	
}