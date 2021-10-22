package Clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Liga {
private String nombre= null;
private ArrayList<Equipo> equipos;


public Liga() {
	super();
	equipos = new ArrayList<Equipo>();

}

public void anadirEquipo(Equipo e1) throws LigaException{
	if (equipos.size()< 5) {
		equipos.add(e1);}
	else {
		throw new LigaException("Demasiados equipos");
	}
}

public String clasificacion() throws LigaException {
	Collections.sort(equipos);
	StringBuilder cadena= new StringBuilder();
	int contador=0; 
	Iterator<Equipo>eq =equipos.iterator();
	while(eq.hasNext()) {
		Equipo aux=eq.next();
        cadena.append((contador+1) + aux.toString()+"\n");
        contador++; 
	}
	if (contador<4) {
		throw new LigaException("No hay suficientes equipos");
		}
	return cadena.toString();
	}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}



}