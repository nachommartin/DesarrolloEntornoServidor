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
	
	


}
