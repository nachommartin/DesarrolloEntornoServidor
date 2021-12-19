package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.model.Empleado;
import com.example.demo.repository.EmpleadoRepository;

@Primary
@Service("empleadoServiceDB")
public class EmpleadoServiceDB implements EmpleadoService{
	
	@Autowired
	private EmpleadoRepository repositorio;
	

	@Override
	public Empleado add(Empleado e) {
		// TODO Auto-generated method stub
		return repositorio.save(e);
	}

	@Override
	public List<Empleado> findAll() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

	@Override
	public Empleado findById(long id) {
		// TODO Auto-generated method stub
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public Empleado edit(Empleado e) {
		// TODO Auto-generated method stub
		return repositorio.save(e);
	}

}
