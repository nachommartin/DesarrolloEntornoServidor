package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String welcome(Model model) {
		model.addAttribute("mensaje","Bienvenido a nuestra  web");
		model.addAttribute("enlace","¿Qué hacemos?");
		model.addAttribute("contacto","Contacta con nosotros");
		return "index";
		}
	@GetMapping("/que")
	public String what(Model model) {
		model.addAttribute("texto", "MegaTruck, es una empresa totalmente andaluza, dedicada al almacenaje y distribución de mercancías por carretera.\n"
				+ " \n"
				+ "Disponemos de una red nacional que nos permite llegar a cualquier punto de España.\n");
		model.addAttribute("back","Volver atrás");
		return "que"; 
	}
	@GetMapping("/contacto")
	public String contact(Model model) {
		model.addAttribute("tfno", "955435109");
		model.addAttribute("back","Volver atrás");
		return "contacto"; 
	}
}
