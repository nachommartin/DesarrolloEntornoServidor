package clases;

import java.util.Objects;

public class Producto {
 
private int id;
private String concepto;
private double importe;

public Producto(int id, String concepto, double importe) {
super();
this.id = id;
this.concepto = concepto;
this.importe = importe;
}


public int getId() {
return id;
}
public void setId(int id) {
this.id = id;
}
public String getConcepto() {
return concepto;
}
public void setConcepto(String concepto) {
this.concepto = concepto;
}
public double getImporte() {
return importe;
}
public void setImporte(double importe) {
this.importe = importe;
}


@Override
public int hashCode() {
	return Objects.hash(id);
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
	return id == other.id;
}



 
}