package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.ApiError;
import com.example.demo.error.CantidadException;
import com.example.demo.error.LPNotFoundException;
import com.example.demo.error.ListNotFoundException;
import com.example.demo.error.PedidoNotFoundException;
import com.example.demo.error.ProductoNotFoundException;
import com.example.demo.error.UsuarioNotFoundException;
import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.services.PedidoService;
import com.example.demo.services.ProductoService;
import com.example.demo.services.UsuarioService;

/**
 * Controlador de la Api Rest
 * @author Nacho
 *
 */
@RestController
public class MainController {
	
	@Autowired
	private UsuarioService servicioUser;
	
	@Autowired 
	private ProductoService servicioProducto; 
	
	@Autowired
	private PedidoService servicioPedido;
	
	/**
	 * Método para obtener un usuario
	 * @param user
	 * @return
	 */
	@GetMapping("/usuario/{user}")
	public Usuario findByUser(@PathVariable String user) {
		Usuario resultado = servicioUser.getByNick(user);
		if (resultado == null) {
			throw new UsuarioNotFoundException(user);
		} else {
			return resultado;
		}
	}
	
	/**
	 * Método para obtener el listado del producto
	 * @return
	 */
	@GetMapping("/producto")
	public List<Producto> findAll(){
		return this.servicioProducto.mostrarProductos();
	}
	
	/**
	 * Método para obtener un pedido
	 * @param ref
	 * @return
	 */
	@GetMapping("/pedido/{ref}")
	public Pedido findByRef(@PathVariable long ref) {
		Pedido resultado = servicioPedido.getByRef(ref);
		if (resultado == null) {
			throw new PedidoNotFoundException(ref);
		} else {
			return resultado;
		}
	}
	
	/**
	 * Método para obtener la colección de pedidos de un usuario
	 * @param nick
	 * @return
	 */
	@GetMapping("/pedido/usuario/{nick}")
	public List<Pedido> listByUser(@PathVariable String nick) {
		Usuario user = servicioUser.getByNick(nick);
		if (user == null) {
			throw new UsuarioNotFoundException(nick);
		}
		else if (user.getListaPedidos().isEmpty()) {
			throw new ListNotFoundException(nick);
		} 
		else {
			List<Pedido> resultado = servicioUser.verPedidos(nick);
			return resultado;
		}
	}
	/**
	 * Método para mostar el carrito de un pedido
	 * @param ref
	 * @return
	 */
	@GetMapping("/lineapedido/{ref}")
	public List<LineaPedido> mostrarCarrito(@PathVariable long ref){
		Pedido ped =servicioPedido.getByRef(ref);
		if (ped == null) {
			throw new PedidoNotFoundException(ref);
		}
		else if (ped.getLineaPedido().isEmpty()) {
			throw new LPNotFoundException(ref);
		}
		return ped.getLineaPedido();
	}
	
	/**
	 * Método para añadir un pedido a un usuario
	 * @param user
	 * @return
	 */
	@PostMapping("/pedido/{user}")
	public Pedido add(@PathVariable String user) {
		Pedido ped= new Pedido();
		Usuario resultado = servicioUser.getByNick(user);
		if (resultado == null) {
			throw new UsuarioNotFoundException(user);
		}

		return this.servicioUser.addPedido(ped,user);
	}
	/**
	 * Método para añadir productos al carrito de un pedido
	 * @param producto
	 * @param cantidad
	 * @param ref
	 * @return
	 */
	@PostMapping("/lineapedido/{cantidad}/{ref}")
	public LineaPedido addLP(@RequestBody Producto producto, @PathVariable int cantidad, @PathVariable long ref){
		Pedido ped =servicioPedido.getByRef(ref);
			if (ped == null) {
				throw new PedidoNotFoundException(ref);
			}
			else if(cantidad <0 || cantidad >10) {
				throw new CantidadException();
				
			}
			else if (servicioProducto.getByRef(producto.getReferenciaProducto())==null){
				throw new ProductoNotFoundException(producto.getReferenciaProducto());
			}
			LineaPedido lp= servicioPedido.addProductos(ped, producto, cantidad);
			return lp;
			
	}
	
	/**
	 * Método para editar un producto
	 * @param nick
	 * @param ref
	 * @param ped
	 * @return
	 */
	@PutMapping("/pedido/{nick}/{ref}")
	public Pedido editarPedido(@PathVariable String nick, @PathVariable long ref, @RequestBody Pedido ped) {
		Pedido resultado = servicioPedido.getByRef(ref);
		Usuario user = servicioUser.getByNick(nick);
		if (resultado == null) {
			throw new PedidoNotFoundException(ref);
		}
		else if (user == null) {
			throw new UsuarioNotFoundException(nick);
		}
		else {
			resultado.setDireccion(ped.getDireccion());
			resultado.setGastosEnvio(ped.getGastosEnvio());
			resultado.setFecha(ped.getFecha());
			servicioUser.addPedido(resultado, nick);
			return resultado;
		}
	}
	
	/**
	 * Método para quitar un producto del carrito de un pedido
	 * @param producto
	 * @param cantidad
	 * @param ref
	 * @return
	 */
	@DeleteMapping("/lineapedido/{cantidad}/{ref}")
	public String removeLP(@RequestBody Producto producto, @PathVariable int cantidad, @PathVariable long ref){
		Pedido ped =servicioPedido.getByRef(ref);
			if (ped == null) {
				throw new PedidoNotFoundException(ref);
			}
			else if(cantidad <0 || cantidad >10) {
				throw new CantidadException();
				
			}
			else if (servicioProducto.getByRef(producto.getReferenciaProducto())==null){
				throw new ProductoNotFoundException(producto.getReferenciaProducto());
			}
			String lp= servicioPedido.quitarProductos(ped, producto, cantidad);
			return lp;
			
	}
	
	/**
	 * Borrar un pedido de la colección de un usuario
	 * @param user
	 * @param ref
	 * @return
	 */
	@DeleteMapping("/pedido/{user}/{ref}")
	public Pedido borrar(@PathVariable String user, @PathVariable long ref) {
		Pedido ped= servicioPedido.getByRef(ref);
		Usuario resultado = servicioUser.getByNick(user);
		if (ped == null) {
			throw new PedidoNotFoundException(ref);
		}
		if (resultado == null) {
			throw new UsuarioNotFoundException(user);
		}

		return this.servicioUser.borrarPedido(ped,user);
	}
	

	/**
	 * Gestor de la excepción de no existencia del pedido
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(PedidoNotFoundException.class)
	public ResponseEntity<ApiError> handlePedidoNoEncontrado(PedidoNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	/**
	 * Gestor de la excepción de no existencia del usuario
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<ApiError> handleUsuarioNoEncontrado(UsuarioNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	/**
	 * Gestor de la excepción de no existencia del producto
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ProductoNotFoundException.class)
	public ResponseEntity<ApiError> handleProductoNoEncontrado(UsuarioNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	/**
	 * Gestor de la excepción de que un usuario no tenga pedidos
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ListNotFoundException.class)
	public ResponseEntity<ApiError> handleListaPedidosVacia(ListNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

	/**
	 * Gestor de la excepción de que un pedido no tenga productos
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(LPNotFoundException.class)
	public ResponseEntity<ApiError> handleCarritoVacio(LPNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	/**
	 * Gestor de la excepción de la cantidad no correcta
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(CantidadException.class)
	public ResponseEntity<ApiError> badCantidad(CantidadException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.BAD_REQUEST);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	
}
	
	


