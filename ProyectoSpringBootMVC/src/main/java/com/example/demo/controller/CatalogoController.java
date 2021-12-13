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
		Usuario aux = new Usuario();
		aux.setNick(sesion.getAttribute("userSaved").toString());
		model.addAttribute("usuario", aux);
		return "inicioApp";
	}
	
	@GetMapping({"/catalogo"})
	public String catalogo(Model model) {
		Usuario aux = servicioUser.getByNick(sesion.getAttribute("userSaved").toString());
		if(sesion.getAttribute("pedidoSaved")!= null ) {
			Pedido ped = (Pedido) sesion.getAttribute("pedidoSaved");
			servicioUser.borrarPedido(ped, aux.getNick());	
			sesion.removeAttribute("pedidoSaved");
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
			if(sesion.getAttribute("pedidoSaved")==null) {
				String auxCadena= "Tu carrito est� vac�o";
				model.addAttribute("carritoNo",auxCadena);

			}
			else {
				ped = (Pedido) sesion.getAttribute("pedidoSaved");
				String auxCadena= servicioPed.quitarProductos(ped, producto, producto.getCantidad());
				model.addAttribute("carritoNo",auxCadena);	
				sesion.setAttribute("pedidoSaved", ped);
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
	Pedido ped = new Pedido();
	ped = (Pedido) sesion.getAttribute("pedidoSaved");
	model.addAttribute("usuario",aux);
	model.addAttribute("precio",precioASumar);
	if(precioASumar!=null && !direccion.equals("")) {
		ped.setCoste((double)Math.round((ped.getCoste()+precioASumar) * 100d) / 100d);
		ped.setDireccion(direccion);
		sesion.setAttribute("pedidoSaved", ped);
		servicioUser.addPedido(ped, aux.getNick());
		sesion.setAttribute("envioSaved", precioASumar);
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
	Pedido ped = (Pedido) sesion.getAttribute("pedidoSaved");
	Double gastosEnvio = (Double) sesion.getAttribute("envioSaved"); 
	model.addAttribute("usuario",aux);
	model.addAttribute("envio",gastosEnvio);
	ArrayList<String> lista= ped.verCarrito();
	model.addAttribute("carrito", lista);
	String total= ""+ped.getCoste();
	model.addAttribute("sumaCarrito", total);
	System.out.println(ped.getDireccion());
	return "factura";
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
