package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Empleado;
import com.example.demo.service.EmpleadoService;

@Controller
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService servicio;
	
	@GetMapping("/empleado/list")
	public String listAll(Model model) {
		model.addAttribute("listaEmpleados", servicio.findAll());
		return "list";
	}
	
	@GetMapping("/empleado/new")
	public String nuevoEmpleadoForm(Model model) {
		model.addAttribute("empleado", new Empleado());
		return "form";
	}
	//Antes de pasar a la vista, crea el objeto
	
	@PostMapping("/empleado/new/submit")
	public String nuevoEmpleadoSubmit(@ModelAttribute("empleado") Empleado nuevoEmpleado) {
		servicio.add(nuevoEmpleado);
		return "redirect:/empleado/list";
	}
	// Rellena el objeto con los datos del form se lo devuelve al controlador que modifica la lista y
	// redirige a la vista

}
