package com.example.demo.model;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Pedido {
	private String referencia;
	private Usuario usuario;
	private String direccion;
	private HashMap<Producto,Integer> productos; 
	private double coste;
	
	
	
	public Pedido(String referencia, Usuario usuario, String direccion) {
		super();
		this.referencia = referencia;
		this.usuario = usuario;
		this.direccion = direccion;
		this.coste = this.calcularCosteTotal();
		this.productos= new HashMap<Producto,Integer>();
	} 
	
	
	
	public String getReferencia() {
		return referencia;
	}



	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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



	public void setProductos(HashMap<Producto,Integer> productos) {
		this.productos = productos;
	}



	public double getCoste() {
		return coste;
	}



	public void setCoste(double coste) {
		this.coste = coste;
	}



	
	
	public double calcularCosteTotal(){
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
		return contador;
	}

}
