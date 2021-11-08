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
		
		productos.put("pistacho", 2.99);   
		productos.put("avellana", 1.99);   
		productos.put("nuez", 1.99);   
		productos.put("almendra", 3.99);
		productos.put("canela", 0.99);
	}


	public HashMap<String, Double> getProductos() {
		return productos;
	}
	
	

	public double sumaAvellanaNuez(int cantidad) {
		double resul= cantidad * 1.99; 
		return Math.round(resul*100.0)/100.0;
	}

	public double sumaPistacho(int cantidad) {
		double resul= cantidad * 2.99; 
		return Math.round(resul*100.0)/100.0;
	}
	
	public double sumaAlmendra(int cantidad) {
		double resul= cantidad * 3.99; 
		return Math.round(resul*100.0)/100.0;
	}
	
	public double sumaCanela(int cantidad) {
		double resul= cantidad * 0.99; 
		return Math.round(resul*100.0)/100.0;
	}
	
	public double sumaTotal(double c1, double c2, double c3, double c4, double c5) {
		double resul= (c1+c2+c3+c4+c5);
		return Math.round(resul*100.0)/100.0; 
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
	        	String concepto= it.next().toString();
	            cadena.append(concepto);
	            cadena.append(" con precio de ");
	            cadena.append(lista[i]);
	            cadena.append(" euros por unidad");
	            cadena.append("<input type='number' min='0' name='caja"+concepto+"'>");
	            cadena.append("<br>");
	            i++;
			}
			return cadena.toString();
		}

}