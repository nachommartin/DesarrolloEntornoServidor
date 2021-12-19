package com.example.demo.model;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Como se tiene que chequar con el Validation la cantidad, esta es un atributo
 * del producto y tiene las anotaciones de que debe estar entre 1 y 10, ambos inclusive
 * y no ser nulo
 * @author humat
 *
 */
public class Producto {
	
	private String referenciaProducto;
	private String titulo;
	private String plataforma;
	private double precio;
	@NotNull
	@Min(1)
	@Max(10)
	private int cantidad;
	
	
	
	public Producto(String referenciaProducto, String titulo, String plataforma, double precio) {
		super();
		this.referenciaProducto = referenciaProducto;
		this.titulo = titulo;
		this.plataforma= plataforma;
		this.precio = precio;
		this.cantidad=0;
	}

	
	public Producto() {
		super();
	}
	



	public String getReferenciaProducto() {
		return referenciaProducto;
	}



	public String getTitulo() {
		return titulo;
	}



	public double getPrecio() {
		return precio;
	}
	
	
	public String getPlataforma() {
		return plataforma;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public void setReferenciaProducto(String referenciaProducto) {
		this.referenciaProducto = referenciaProducto;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	@Override
	public String toString() {
		return "Producto [referenciaProducto=" + referenciaProducto + ", titulo=" + titulo + ", plataforma="
				+ plataforma + ", precio=" + precio + ", cantidad=" + cantidad + "]";
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(referenciaProducto);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(referenciaProducto, other.referenciaProducto);
	}

	

}
