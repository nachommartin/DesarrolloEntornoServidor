package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	@GetMapping("/hola")
	public String welcome(Model model) {
		model.addAttribute("mensaje","Hola amigo");
		return "saludo";
	}
	
	@GetMapping("/saludo")
	public String saludar (
		@RequestParam(name="name", required=false, defaultValue="World")
		String name, Model model){
		model.addAttribute("name", name);
		return "saludo"; 
		}
	
	@GetMapping("/saludo/{name}")
	public String saludarSendero (
		@PathVariable(name="name", required=false)
		String name, Model model){
		model.addAttribute("name", name);
		return "saludo"; 
		}
		
}