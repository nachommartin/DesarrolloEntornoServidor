package com.example.demo.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Pedido implements Comparable<Pedido> {
	private long referencia=325;
	private String direccion;
	private HashMap<Producto,Integer> productos; 
	private double coste;
	private LocalDate fecha; 
	private double gastosEnvio;
	private boolean tramitado;
	private boolean editado;
	
	
	public Pedido(long referencia, String direccion, LocalDate fecha) {
		super();
		this.referencia= referencia;
		this.direccion = direccion;
		this.coste = 0;
		this.fecha= fecha;
		this.gastosEnvio=0;
		this.tramitado=false;
		this.editado=false;
		this.productos= new HashMap<Producto,Integer>();
	} 
	
	
	
	public Pedido(String direccion) {
		super();
		referencia++;
		this.direccion = direccion;
		this.coste = 0;
		this.fecha = LocalDate.now();
		this.gastosEnvio=0;
		this.tramitado=false;
		this.editado=false;
		this.productos= new HashMap<Producto,Integer>();
	} 
	
	public Pedido() {
		super();
		referencia++;
		this.fecha = LocalDate.now();
		this.tramitado=false;
		this.editado=false;
		this.productos= new HashMap<Producto,Integer>();

	}
	
	
	
		
	public long getReferencia() {
		return referencia;
	}



	public void setReferencia(long referencia) {
		this.referencia = referencia;
	}



	
	public double getGastosEnvio() {
		return gastosEnvio;
	}



	public void setGastosEnvio(double gastosEnvio) {
		this.gastosEnvio = gastosEnvio;
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






	public boolean isEditado() {
		return editado;
	}



	public void setEditado(boolean editado) {
		this.editado = editado;
	}



	public double getCoste() {
		return coste;
	}





	public void setProductos(HashMap<Producto, Integer> productos) {
		this.productos = productos;
	}



	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}



	public boolean isTramitado() {
		return tramitado;
	}



	public void setTramitado(boolean tramitado) {
		this.tramitado = tramitado;
	}



	public void setCoste(double coste) {
		this.coste = coste;
	}

	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void actualizarFecha() {
		this.fecha=LocalDate.now();
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
		String auxFecha;
		StringBuilder cadena= new StringBuilder();
		Collection<Producto> keys = productos.keySet();
		Collection<Integer> valores = productos.values();
		Iterator<Producto> pr = keys.iterator();
		Iterator<Integer> vl = valores.iterator();
	    while(pr.hasNext()) {
	       	Producto aux= pr.next();
	       	Integer auxVal= vl.next();
	        cadena.append(auxVal + " unidad(es) de " + aux.getTitulo()+" a "+ aux.getPrecio() +" euros ");
			}
	    	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    auxFecha = this.fecha.format(formato);
	    	resul = cadena.toString();	  
		return "Pedido: "+ this.referencia + " Fecha: " + auxFecha +" Precio: " + this.coste+ " euros Productos: " + resul;
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




	@Override
	public int compareTo(Pedido ped) {
		// TODO Auto-generated method stub
		int resul; 
		if (ped.getFecha().isEqual(this.fecha)) {
			resul=0;
		}
		else if (ped.getFecha().isAfter(this.fecha)) {
			resul=1;
		}
		else {
			resul=-1;
		}
		return resul;
	}
	


}