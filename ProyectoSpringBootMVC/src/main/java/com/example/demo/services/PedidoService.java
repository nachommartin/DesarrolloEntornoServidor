package com.example.demo.services;




import org.springframework.stereotype.Service;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;




@Service
public class PedidoService {
	
	
	
	
	public Pedido getByRef(Usuario user, long ref) {
		Pedido resul= new Pedido();
		Pedido aux = new Pedido();
		aux.setReferencia(ref);
		if (user.getListaPedidos().contains(aux)) {
		      for (Pedido pedido : user.getListaPedidos()) {
		        if (pedido.equals(aux)) 
		          resul= pedido;
			      } 
			   }
		else {
			resul= new Pedido();
		}
		return resul;
	}
	
	public void addProductos(Pedido aux, Producto producto, int cant) {
		if(aux.getProductos().containsKey(producto)) {
			int cantidad= aux.getProductos().get(producto)+cant;
			aux.getProductos().put(producto,cantidad);
		}
		else {
			aux.getProductos().put(producto, cant);
		}
	}
	
	
	public String quitarProductos(Pedido aux, Producto producto, int cant) {
		String cadena="";
		if(aux.getProductos().containsKey(producto)) {
			int cantidad= aux.getProductos().get(producto)-cant;
			if (cantidad<0) {
				cadena="Has quitado m�s cantidad de ese producto del que ten�as en el carrito";
			}
			else {
				aux.getProductos().put(producto,cantidad);
				cadena="Has quitado correctamente el producto";
				if(aux.getProductos().get(producto)==0) {
					aux.getProductos().remove(producto);
				}
			}
		}
		else {
			cadena="Ese producto no est� en tu carrito";
		}
		return cadena;
	}
	
	public Double controladorGastosEnvio(Double d, Pedido ped) {
		Double resul=ped.getGastosEnvio()-d;
		return resul;
	}

}
