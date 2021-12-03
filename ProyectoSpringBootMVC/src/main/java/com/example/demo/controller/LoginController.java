package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Empleado;
import com.example.demo.services.UsuarioService;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	private HttpSession sesion;


	@Autowired
	private UsuarioService servicio;
	
	
	@GetMapping({"inicio"})
	public String catalogo(Model model) {
	// servicio.comprobadorTotal(null, null));

		return "list";
	}
	
}
