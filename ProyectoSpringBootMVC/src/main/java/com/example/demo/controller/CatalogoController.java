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
		Usuario aux = new Usuario();
		aux.setNick(sesion.getAttribute("userSaved").toString());
		model.addAttribute("usuario", aux);
		return "inicioApp";
	}
	
	@GetMapping({"/catalogo"})
	public String catalogo(Model model) {
		Usuario aux = new Usuario();
		Pedido ped = new Pedido();
		sesion.setAttribute("pedidoSaved", ped);
		aux.setNick(sesion.getAttribute("userSaved").toString());
		Producto producto= new Producto();
		model.addAttribute("usuario", aux);
		model.addAttribute("productos", servicioPro.mostrarProductos());
		model.addAttribute("productoGenerado", producto);
		return "catalogo";
	}
	
	@PostMapping("/catalogo")
	public String submitCatalogo(Model model, @Valid @ModelAttribute("productoGenerado") Producto producto,
			BindingResult bindingResult) {
		Usuario aux = new Usuario();
		aux.setNick(sesion.getAttribute("userSaved").toString());
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
			if(sesion.getAttribute("pedidoSaved")==null) {
				servicioPed.addProductos(ped, producto, producto.getCantidad());
				sesion.setAttribute("pedidoSaved", ped);
				servicioUser.addPedido(ped, aux.getNick());
			}
			else {
				ped = (Pedido) sesion.getAttribute("pedidoSaved");
				servicioPed.addProductos(ped, producto, producto.getCantidad());
				sesion.setAttribute("pedidoSaved", ped);
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
	
	@GetMapping({"/envio"})
		public String envio(Model model) {
		Usuario aux = new Usuario();
		aux.setNick(sesion.getAttribute("userSaved").toString());
		Pedido ped = new Pedido();
		ped = (Pedido) sesion.getAttribute("pedidoSaved");

			return "envio";
		}

	
	@GetMapping({"/historial"})
		public String historial(Model model) {
		Usuario aux = new Usuario();
		aux.setNick(sesion.getAttribute("userSaved").toString());
		model.addAttribute("usuario", aux);
		model.addAttribute("pedidos", servicioUser.verPedidos(aux.getNick()));
		return "historial";
	}

}
