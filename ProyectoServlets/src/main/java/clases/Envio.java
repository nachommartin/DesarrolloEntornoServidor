package clases;

import java.util.Objects;

public class Envio {
 
private String concepto;
private double precio;


public Envio(String concepto, double precio) {
	super();
	this.concepto = concepto;
	this.precio = precio;
}


public String getConcepto() {
	return concepto;
}


public void setConcepto(String concepto) {
	this.concepto = concepto;
}


public double getPrecio() {
	return precio;
}


public void setPrecio(double precio) {
	this.precio = precio;
}


@Override
public int hashCode() {
	return Objects.hash(concepto);
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Envio other = (Envio) obj;
	return Objects.equals(concepto, other.concepto);
}



}


