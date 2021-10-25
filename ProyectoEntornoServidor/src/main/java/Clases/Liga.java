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

/* Como es un bean, el constructor lo hacemos sin parámetros pero indicando que 
 * se cree la colección necesaria para la aplicación*/

public void anadirEquipo(Equipo e1) throws LigaException{
	if (equipos.size()>4) {
		throw new LigaException("Demasiados equipos");}
	else {
		equipos.add(e1);
	}
}

/*Para simplificar la aplicación sólo vamos a permitir que se añadan 4 equipos
 * en la colección de la liga */

public String clasificacion() throws LigaException {
	Collections.sort(equipos);
	StringBuilder cadena= new StringBuilder();
	int contador=0; 
	Iterator<Equipo>eq =equipos.iterator();
	while(eq.hasNext()) {
		Equipo aux=eq.next();
        cadena.append((contador+1) + "º " + aux.toString()+" || ");
        contador++; 
	}
	if (contador<4) {
		System.out.println(cadena.toString());
		throw new LigaException("No hay suficientes equipos");
		}
	return cadena.toString();
	}

/* Las ligas tienen que ser exclusivamente de 4 equipos por lo que controlaremos
 * el tamaño de la colección. A pesar de que un HashSet sería más útil para evitar
 * repetidos, al final usaremos un ArrayList por su facilidad de ordenar ese
 * tipo de colección. En este proyecto prima la sencillez y aprender los elementos
 * de un JSP para saber usarlos en un futuro */

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public void vaciarLiga() {
	equipos.clear(); 
}

}