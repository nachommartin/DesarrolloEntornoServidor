package com.example.demo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;


@Service
public class ProductoService {

	
	private List<Producto> repositorio = new ArrayList<>();
	
	/**
	 * Método para recuperar un producto por referencia
	 * @param ref
	 * @return
	 */
	public Producto getByRef(String ref) {
		Producto resul = null;
		boolean bandera = false;
		int i = 0;
		while (!bandera && i<repositorio.size()) {
			if (repositorio.get(i).getReferenciaProducto().equals(ref)) {
				bandera = true;
				resul = repositorio.get(i);
			} else {
				i++;
			}	
		}
		return resul;
	}
	
	/**
	 * Método para mostrar todo el repositorio de productos
	 * @return
	 */
	public List<Producto> mostrarProductos(){
		return repositorio;
	}
	
	/**
	 * Introducción de los productos en su repositorio
	 */
	@PostConstruct
	public void init() {
		repositorio.addAll(
				Arrays.asList(new Producto("001", "Sonic The Hedgehog", "Mega Drive", 29.99),
						new Producto("002", "Strider", "Mega Drive", 39.99),
						new Producto("003", "Quackshot", "Mega Drive", 19.99),
						new Producto("004", "Flashback", "Mega Drive", 19.99),
						new Producto("005", "Super Mario World", "Super NES", 24.99),
						new Producto ("006", "Street Fighter II", "Super NES", 29.99),
						new Producto ("007", "Megaman 2", "NES", 19.99),
						new Producto ("008", "Kirby Dream Land", "Game Boy", 24.99)
						)
				);
		

	}




}
