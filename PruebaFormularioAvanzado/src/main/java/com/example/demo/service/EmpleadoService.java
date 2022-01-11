package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Empleado;

public interface EmpleadoService {
	
	public Empleado add(Empleado e);	
	public List<Empleado> findAll();	
	public Empleado findById(long id);	
	public Empleado edit(Empleado e);

}
