package clases; 

import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;



public class TipoEnvios {
	private HashMap<String,Double> envios;


	public TipoEnvios() {
		super();
		envios = new HashMap<String,Double>();
		
		envios.put("ordinario", 3.49);   
		envios.put("a punto de entrega", 1.99);   
		envios.put("urgente", 4.99);   
	}


	public HashMap<String, Double> getEnvios() {
		return envios;
	}
	
	

	

	@Override
	public String toString() {
		StringBuilder cadena= new StringBuilder();
		Collection<String> keys = envios.keySet();
		Collection<Double> precios =envios.values();
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
	        	cadena.append("Envío ");
	        	String concepto= it.next();
	        	cadena.append(concepto);
	            cadena.append(" con precio de ");
	            cadena.append(lista[i]);
	            cadena.append(" euros");
	            cadena.append("<br>");
	            cadena.append("<input type='radio' name='option' value='"+concepto+"'>");
	            cadena.append("<br>");
	            i++;
			}
			return cadena.toString();
		}
	
}