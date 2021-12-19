package com.example.demo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Aunque la cantidad es un atributo de producto y con una List sería suficiente para guardar los
 * productos se ha optado por el HashMap por su eficiencia para evitar valores repetidos y su manera 
 * de calcular el precio al iterar al mismo tiempo producto(key) y cantidad(value). Hay tres banderas
 * para controlar que si el proceso de edición o creación de un pedido no se tramita correctamente, los
 * cambios no persistan en el usuario
 * @author humat
 *
 */
public class Pedido implements Comparable<Pedido> {
	private long referencia=0;
	private String direccion;
	private HashMap<Producto,Integer> productos; 
	private double coste;
	private LocalDateTime fecha; 
	private double gastosEnvio;
	private boolean tramitado;
	private boolean editado;
	private int editadoTramitado;
	
	
	public Pedido(long referencia, String direccion, LocalDateTime fecha) {
		super();
		this.referencia= referencia;
		this.direccion = direccion;
		this.coste = 0;
		this.fecha= fecha;
		this.gastosEnvio=0;
		this.tramitado=false;
		this.editado=false;
		this.editadoTramitado=0;
		this.productos= new HashMap<Producto,Integer>();
	} 
	
	
	
	public Pedido() {
		super();
		this.referencia++;
		this.coste = 0;
		this.fecha = LocalDateTime.now();
		this.gastosEnvio=0;
		this.tramitado=false;
		this.editado=false;
		this.editadoTramitado=0;
		this.productos= new HashMap<Producto,Integer>();
	} 
	
	public Pedido(long referencia) {
		super();
		this.referencia= referencia;
		this.fecha = LocalDateTime.now();
		this.tramitado=false;
		this.editado=false;
		this.editadoTramitado=0;
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



	public void setFecha(LocalDateTime fecha) {
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

	
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	
	/**
	 * El set de la fecha es actualizarla al momento actual para el proceso de edición
	 */
	public void actualizarFecha() {
		this.fecha=LocalDateTime.now();
	}
	
	
	

	public int getEditadoTramitado() {
		return editadoTramitado;
	}



	public void setEditadoTramitado(int editadoTramitado) {
		this.editadoTramitado = editadoTramitado;
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
		if (this == obj) {
			return true;}
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (referencia != other.referencia)
			return false;
		return true;
	}

	/**
	 * Método para calcular el coste del carrito del pedido (precio producto * cantidad)
	 */
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
	/**
	 * Es parecido al To String pero si este último se utiliza para mostrar el pedido en el historial, éste se usa 
	 * para mostrar el carrito durante el proceso de creación/edición del pedido
	 * @return
	 */
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



	/**
	 * Comparador mediante la fecha
	 */
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