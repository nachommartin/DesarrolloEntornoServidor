package com.example.demo.model;

public class Producto {
	private String referenciaProducto;
	private String descripcion;
	private double precio;
	
	
	
	public Producto(String referenciaProducto, String descripcion, double precio) {
		super();
		this.referenciaProducto = referenciaProducto;
		this.descripcion = descripcion;
		this.precio = precio;
	}



	public String getReferenciaProducto() {
		return referenciaProducto;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public double getPrecio() {
		return precio;
	}
	
	
	
	
	

}


