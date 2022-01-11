package com.example.demo.services;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;

public class ProductoService{
	
	@Autowired
	private ProductoRepository repositorio;

	public Producto getByRef(String ref) {
		// TODO Auto-generated method stub
		return repositorio.findById(ref).orElse(null);
	}

	public List<Producto> mostrarProductos() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}
	
	@PostConstruct
	public void init() {
		repositorio.saveAll(
				Arrays.asList(new Producto("Sonic The Hedgehog", "Mega Drive", 29.99),
						new Producto("Strider", "Mega Drive", 39.99),
						new Producto("Quackshot", "Mega Drive", 19.99),
						new Producto("Flashback", "Mega Drive", 19.99),
						new Producto("Super Mario World", "Super NES", 24.99),
						new Producto ("Street Fighter II", "Super NES", 29.99),
						new Producto ("Megaman 2", "NES", 19.99),
						new Producto ("Kirby Dream Land", "Game Boy", 24.99)
						)
				);
		

	}

}
