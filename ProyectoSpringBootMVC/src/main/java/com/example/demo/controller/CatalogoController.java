package com.example.demo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.services.PedidoService;
import com.example.demo.services.ProductoService;
import com.example.demo.services.UsuarioService;

@Controller
public class CatalogoController {
	
	@Autowired
	private HttpSession sesion;
	
	@Autowired
	private UsuarioService servicioUser;
	
	@Autowired
	private PedidoService servicioPed;
	
	@Autowired
	private ProductoService servicioPro;

	
	@GetMapping({"/inicioApp"})
	public String inicio(Model model) {
		Usuario aux = servicioUser.getByNick(sesion.getAttribute("userSaved").toString());
		model.addAttribute("usuario", aux);
		return "inicioApp";
	}
	
	@GetMapping({"/catalogo"})
	public String catalogo(Model model) {
		Usuario aux = servicioUser.getByNick(sesion.getAttribute("userSaved").toString());
		if(servicioUser.ultimoPedido(aux.getNick()).isTramitado()==false) {
			Pedido ped = servicioUser.ultimoPedido(aux.getNick());
			servicioUser.borrarPedido(ped, aux.getNick());
		}		
		Producto producto= new Producto();
		model.addAttribute("usuario", aux);
		model.addAttribute("productos", servicioPro.mostrarProductos());
		model.addAttribute("productoGenerado", producto);
		return "catalogo";
	}
	
	@PostMapping(value="/catalogo", params={"anadir"} )
	public String submitCatalogo(Model model, @Valid @ModelAttribute("productoGenerado") Producto producto,
			BindingResult bindingResult) {
		Usuario aux = servicioUser.getByNick(sesion.getAttribute("userSaved").toString());
		model.addAttribute("usuario", aux);
		model.addAttribute("productos", servicioPro.mostrarProductos());
		model.addAttribute("productoGenerado", producto);
		if (bindingResult.hasErrors()) {
			String cadenaError = bindingResult.getFieldError().getDefaultMessage();
			model.addAttribute("mensajeError", cadenaError);
			return "catalogo";
		} 
		else {
			Pedido ped= new Pedido();
			if(servicioUser.recuperadorPedido(ped, aux.getListaPedidos())==null) {
				servicioPed.addProductos(ped, producto, producto.getCantidad());
				servicioUser.addPedido(ped, aux.getNick());
			}
			else {
				ped = servicioUser.recuperadorPedido(ped, aux.getListaPedidos());
				servicioPed.addProductos(ped, producto, producto.getCantidad());
				servicioUser.addPedido(ped, aux.getNick());				
			}
			ArrayList<String> lista= ped.verCarrito();
			model.addAttribute("carrito", lista);
			ped.calcularCosteTotal();
			String total= ""+(double)Math.round(ped.getCoste() * 100d) / 100d;
			model.addAttribute("sumaCarrito",total);
			return "catalogo";
		}
	}
	
	@PostMapping(value="/catalogo", params={"borrar"} )
	public String submitCatalogoBorrar(Model model, @Valid @ModelAttribute("productoGenerado") Producto producto,
			BindingResult bindingResult) {
		Usuario aux = servicioUser.getByNick(sesion.getAttribute("userSaved").toString());
		model.addAttribute("usuario", aux);
		model.addAttribute("productos", servicioPro.mostrarProductos());
		model.addAttribute("productoGenerado", producto);
		if (bindingResult.hasErrors()) {
			String cadenaError = bindingResult.getFieldError().getDefaultMessage();
			model.addAttribute("mensajeError", cadenaError);
			return "catalogo";
		} 
		else {
			Pedido ped= new Pedido();
			if(servicioUser.recuperadorPedido(ped, aux.getListaPedidos())==null) {
				String auxCadena= "Tu carrito esta vacio";
				model.addAttribute("carritoNo",auxCadena);

			}
			else {
				ped = servicioUser.recuperadorPedido(ped, aux.getListaPedidos());
				String auxCadena= servicioPed.quitarProductos(ped, producto, producto.getCantidad());
				model.addAttribute("carritoNo",auxCadena);	
				servicioUser.addPedido(ped, aux.getNick());	
				if(ped.getProductos().isEmpty()==false) {
					ArrayList<String> lista= ped.verCarrito();
					model.addAttribute("carrito", lista);
					ped.calcularCosteTotal();
					String total= ""+ped.getCoste();
					model.addAttribute("sumaCarrito",total);
				}
			}
			return "catalogo";
		}
	}
	
	@GetMapping({"/envio"})
	public String envio(Model model) {
	Usuario aux = servicioUser.getByNick(sesion.getAttribute("userSaved").toString());
	model.addAttribute("usuario",aux);
		return "envio";
	}
	
	@PostMapping({"/envio"})
	public String envioSubmit(Model model, @RequestParam(value ="precio", required = false) Double precioASumar,
			@RequestParam(value ="direccion", required = false) String direccion) {
	Usuario aux = servicioUser.getByNick(sesion.getAttribute("userSaved").toString());
	Pedido ped = servicioUser.ultimoPedido(aux.getNick());
	model.addAttribute("usuario",aux);
	model.addAttribute("precio",precioASumar);
	if(precioASumar!=null && !direccion.equals("")) {
		ped.setGastosEnvio(precioASumar);
		ped.setCoste((double)Math.round((ped.getCoste()+ped.getGastosEnvio()) * 100d) / 100d);
		ped.setDireccion(direccion);
		servicioUser.addPedido(ped, aux.getNick());
		return "redirect:/factura";
	}
	else if(precioASumar==null && !direccion.equals("")) {
		precioASumar=0.25;
		model.addAttribute("precio",precioASumar);
		return "envio";
	}
	else {
		model.addAttribute("direccion",direccion);
		return "envio";
	}
	
	}
	
	@GetMapping({"/factura"})
	public String facturar(Model model) {
	Usuario aux = servicioUser.getByNick(sesion.getAttribute("userSaved").toString());
	Pedido ped = servicioUser.ultimoPedido(aux.getNick());
	Double gastosEnvio = ped.getGastosEnvio(); 
	model.addAttribute("usuario",aux);
	model.addAttribute("envio",gastosEnvio);
	String direccion = ped.getDireccion();
	model.addAttribute("dire",direccion);
	ArrayList<String> lista= ped.verCarrito();
	model.addAttribute("carrito", lista);
	String total= ""+ped.getCoste();
	model.addAttribute("sumaCarrito", total);
	return "factura";
	}
	
	@GetMapping({"/historial"})
		public String historial(Model model) {
		Usuario aux = servicioUser.getByNick(sesion.getAttribute("userSaved").toString());
		Pedido ped = servicioUser.ultimoPedido(aux.getNick());
		ped.setTramitado(true);
		model.addAttribute("usuario", aux);
		model.addAttribute("pedidos", servicioUser.verPedidos(aux.getNick()));
		return "historial";
	}

	
	@PostMapping({"/historial"})
	public String historialSubmit(Model model,@RequestParam(name ="idBorrado") long refPedido) {
		Usuario aux = servicioUser.getByNick(sesion.getAttribute("userSaved").toString());
		Pedido ped = servicioPed.getByRef(aux, refPedido);
		servicioUser.borrarPedido(ped, aux.getNick());
		model.addAttribute("usuario", aux);
		model.addAttribute("pedidos", servicioUser.verPedidos(aux.getNick()));
		return "historial";
	}

}

