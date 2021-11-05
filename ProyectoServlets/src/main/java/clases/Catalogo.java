package clases;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Collection;



public class Catalogo {
	private HashMap<String,Double> productos;


	public Catalogo() {
		super();
		productos = new HashMap<String,Double>();
		
		productos.put("Pistacho", 2.99);   
		productos.put("Avellana", 1.99);   
		productos.put("Nuez", 1.99);   
		productos.put("Almendra", 3.99);
		productos.put("Canela", 0.99);
	}


	public HashMap<String, Double> getProductos() {
		return productos;
	}
	
	

	public double sumaAvellanaNuez(int cantidad) {
		double resul= cantidad * 1.99; 
		return resul;
	}

	public double sumaPistacho(int cantidad) {
		double resul= cantidad * 2.99; 
		return resul;
	}
	
	public double sumaAlmendra(int cantidad) {
		double resul= cantidad * 3.99; 
		return resul;
	}
	
	public double sumaCanela(int cantidad) {
		double resul= cantidad * 0.99; 
		return resul;
	}
	
	public double sumaTotal(int c1, int c2, int c3, int c4, int c5) {
		double resul= (c1+c2+c3+c4+c5);
		return resul; 
	}

	@Override
	public String toString() {
		StringBuilder cadena= new StringBuilder();
		Collection<String> keys = productos.keySet();
		Collection<Double> precios =productos.values();
			Iterator<Double> pit = precios.iterator();
        	double[] lista = new double[precios.size()];
        	int cont=0; 
	        while(pit.hasNext()) {
	        	lista[cont]=pit.next(); 
	        	cont++;
	        }
			Iterator<String> it = keys.iterator();
			int i=0; 
	        while(it.hasNext()) {
	        	cadena.append("Delicia turca de ");
	            cadena.append(it.next());
	            cadena.append(" con precio ");
	            cadena.append(lista[i]);
	            cadena.append("<br>");
	            cadena.append("<input type='number' min='0' name='caja'"+i+">");
	            cadena.append("<br>");
	            i++;
			}
			return cadena.toString();
		}

}
	
	
	