package com.example.demo.services;




import org.springframework.stereotype.Service;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;




@Service
public class PedidoService {
	
	
	
	/**
	 * Método para extraer el pedido indicando su referencia
	 * @param user
	 * @param ref
	 * @return
	 */
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
	
	/**
	 * Añadir productos al pedido. Si el producto ya está en el pedido, se 
	 * aumenta de cantidad 
	 * @param aux
	 * @param producto
	 * @param cant
	 */
	public void addProductos(Pedido aux, Producto producto, int cant) {
		if(aux.getProductos().containsKey(producto)) {
			int cantidad= aux.getProductos().get(producto)+cant;
			aux.getProductos().put(producto,cantidad);
		}
		else {
			aux.getProductos().put(producto, cant);
		}
	}
	
	/**
	 * Se quita un producto del pedido. Se controla que el pedido tenga que estar en el pedido
	 * o que no se quite más cantidad de la que se tiene en el pedido
	 * @param aux
	 * @param producto
	 * @param cant
	 * @return
	 */
	public String quitarProductos(Pedido aux, Producto producto, int cant) {
		String cadena="";
		if(aux.getProductos().containsKey(producto)) {
			int cantidad= aux.getProductos().get(producto)-cant;
			if (cantidad<0) {
				cadena="Has quitado más cantidad de ese producto del que tenías en el carrito";
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
			cadena="Ese producto no está en tu carrito";
		}
		return cadena;
	}
	
	/**
	 * Método para recuperar los gastos de envio originales de un pedido que se va a editar
	 * pero el proceso de edición no ha terminado porque el usuario ha roto la aplicación poniendo
	 * una ruta a la que no debería acceder
	 * @param d
	 * @param ped
	 * @return
	 */
	public Double controladorGastosEnvio(Double d, Pedido ped) {
		Double resul=ped.getGastosEnvio()-d;
		return resul;
	}

}
