package com.example.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Pedido {
	private long referencia=325;
	private String direccion;
	private HashMap<Producto,Integer> productos; 
	private double coste;
	
	
	public Pedido(long referencia, String direccion) {
		super();
		this.referencia= referencia;
		this.direccion = direccion;
		this.coste = 0;
		this.productos= new HashMap<Producto,Integer>();
	} 
	
	
	
	public Pedido(String direccion) {
		super();
		referencia++;
		this.direccion = direccion;
		this.coste = 0;
		this.productos= new HashMap<Producto,Integer>();
	} 
	
	public Pedido() {
		super();
		referencia++;
		this.productos= new HashMap<Producto,Integer>();

	}
	
		
	public long getReferencia() {
		return referencia;
	}



	public void setReferencia(long referencia) {
		this.referencia = referencia;
	}



	



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public HashMap<Producto,Integer> getProductos() {
		return productos;
	}






	public double getCoste() {
		return coste;
	}





	public void setCoste(double coste) {
		this.coste = coste;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (referencia ^ (referencia >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (referencia != other.referencia)
			return false;
		return true;
	}

	public void calcularCosteTotal(){
		double contador =0;
		Collection<Producto> keys = productos.keySet();
		Collection<Integer> valores = productos.values();
		Iterator<Producto> pr = keys.iterator();
		Iterator<Integer> vl = valores.iterator();
        while(pr.hasNext()) {
        	Producto aux= pr.next();
        	Integer auxVal= vl.next();
	        contador+= (aux.getPrecio()*auxVal); 			
			}
		this.coste= Math.round(contador * 100d) / 100d;
	}
	
	@Override
	public String toString() {
		String resul; 
		StringBuilder cadena= new StringBuilder();
		int contador=0; 
		int precioTotal=0;
		Collection<Producto> keys = productos.keySet();
		Collection<Integer> valores = productos.values();
		Iterator<Producto> pr = keys.iterator();
		Iterator<Integer> vl = valores.iterator();
	    while(pr.hasNext()) {
	       	Producto aux= pr.next();
	       	Integer auxVal= vl.next();
	        cadena.append(auxVal + " unidad(es) de " + aux.getTitulo()+" de "+aux.getPlataforma()+" a "+ aux.getPrecio() +" euros ");
		    precioTotal+= (aux.getPrecio()*auxVal); 
	        contador++; 
			}
	    if (contador<=0) {
			resul = "No tienes pedidos en tu historial";
			}
	    else {
	    	resul = cadena.toString();
	    }
		return "Pedido "+ this.referencia + " con precio " + precioTotal+ " euros y estos productos: " + resul;
	}
	
	public ArrayList<String> verCarrito() {
		ArrayList<String> listado= new ArrayList<String>();
		String resul; 
		Collection<Producto> keys = productos.keySet();
		Collection<Integer> valores = productos.values();
		Iterator<Producto> pr = keys.iterator();
		Iterator<Integer> vl = valores.iterator();
	    while(pr.hasNext()) {
	       	Producto aux= pr.next();
	       	Integer auxVal= vl.next();
	        resul= auxVal + " unidad(es) de " + aux.getTitulo()+" de "+aux.getPlataforma()+" a "+ aux.getPrecio() +" euros ";
	        listado.add(resul);
			}
		return listado;
	}


}
