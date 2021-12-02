package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Empleado;
import com.example.demo.service.EmpleadoService;

@Controller
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService servicio;
	
	@GetMapping({"/", "/empleado/list"})
	public String listado(Model model) {
		model.addAttribute("listaEmpleados", servicio.findAll());
		return "list";
	}
	
	@GetMapping("/empleado/new")
	public String nuevoEmpleadoForm(Model model) {
		model.addAttribute("empleado", new Empleado());
		return "form";
	}
	
	@PostMapping("/empleado/new/submit")
	public String nuevoEmpleadoSubmit(@Valid @ModelAttribute("empleado") Empleado nuevoEmpleado,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form";
		} else {
			servicio.add(nuevoEmpleado);
			return "redirect:/empleado/list";
		}
	}
	
	@GetMapping("/empleado/edit/{id}")
	public String editarEmpleadoForm(@PathVariable long id, Model model) {
		Empleado empleado = servicio.findById(id);
		if (empleado != null) {
			model.addAttribute("empleado", empleado);
			return "form";
		} else {
			return "redirect:/empleado/new";
		}
	}
	
	@PostMapping("/empleado/edit/submit")
	public String editarEmpleadoSubmit(@Valid @ModelAttribute("empleado") Empleado empleado,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form";
		} else {
			servicio.edit(empleado);
			return "redirect:/empleado/list";
		}
	}

}
