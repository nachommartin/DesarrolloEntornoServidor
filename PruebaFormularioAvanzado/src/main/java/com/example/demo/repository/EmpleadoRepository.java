package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Empleado;

public interface EmpleadoRepository 
	extends JpaRepository<Empleado, Long>{

}
