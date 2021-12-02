package com.example.demo.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Pedido {
	private String referencia;
	private Usuario usuario;
	private String direccion;
	private ArrayList<Producto> productos; 
	private double coste;
	
	
	
	public Pedido(String referencia, Usuario usuario, String direccion) {
		super();
		this.referencia = referencia;
		this.usuario = usuario;
		this.direccion = direccion;
		this.coste = this.costeTotal();
		this.productos= new ArrayList<Producto>();
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



	public ArrayList<Producto> getProductos() {
		return productos;
	}



	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}



	public double getCoste() {
		return coste;
	}



	public void setCoste(double coste) {
		this.coste = coste;
	}



	public double costeTotal() {
		double contador=0; 
		Iterator<Producto>pr =productos.iterator();
		while(pr.hasNext()) {
			Producto aux=pr.next();
	        contador+= aux.getPrecio(); 
		}
		return contador;
	}
	

}
