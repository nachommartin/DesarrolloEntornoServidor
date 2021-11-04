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
	
	public boolean comprobarUsuario(String usuario){
		Set<String> keys = usuarios.keySet();
		boolean resul=false; 
		for (int i=0; i< keys.toArray().length || resul==true; i++ ) {
			if (usuario.equals(keys.toArray()[i])) {
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
		for (int i=0; i< keys.toArray().length || resul==true; i++ ) {
			if (contrasena.equals(keys.toArray()[i])) {
				resul= true; 
			}
			else {
				resul= false;
			}
		}
		return resul;
	}


	@Override
	public String toString() {
		return "ColeccionUsuario [=" + usuarios + "]";
	}
	
	
	
}