package Clases;

import java.util.Objects;

public  class Equipo implements Comparable<Equipo> {
	private int puntos;
	private String nombre;
	
	
	public Equipo() {
		super();	
	}


	public int getPuntos() {
		return puntos;
	}


	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return Objects.equals(nombre, other.nombre);
	}


	@Override
	public String toString() {
		return nombre + " " + puntos + " puntos";
	}


	@Override
	public int compareTo(Equipo eq) {
		// TODO Auto-generated method stub
		int resul;
		if ( this.getPuntos() == eq.getPuntos()) {
			resul=0; 
		}
		else if (this.getPuntos()>eq.getPuntos()) {
			resul=1;
		}
		else {
			resul=-1;
		}
		return resul;
	}


	
	
	
	
}