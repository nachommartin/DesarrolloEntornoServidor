package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Empleado;

@Service
public class EmpleadoService {

	private List<Empleado> repositorio = new ArrayList<>();
	
	public Empleado add(Empleado e) {
		repositorio.add(e);
		return e;
	}
	
	public List<Empleado> findAll() {
		return repositorio;
	}
	
	@PostConstruct
	public void init() {
		repositorio.addAll(
				Arrays.asList(new Empleado(1, "José Pérez", "jose@dominio.es", "656111111"),
						new Empleado(2, "María Sánchez", "maria@dominio.es", "656222222"),
						new Empleado(3, "Miguel Rodríguez", "miguel@dominio.es", "656333333")));
	}
}
