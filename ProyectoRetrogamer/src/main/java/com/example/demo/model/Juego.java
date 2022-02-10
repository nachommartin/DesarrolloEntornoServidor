package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Juego {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long referencia; 
	
	
	private String titulo; 
	
	private String plataforma;
	
	@Column(name="año")
	private String year;
	
	private String desarrollador;
	
	private String categoria;
	
	@OneToMany(mappedBy="juego", cascade = CascadeType.MERGE, orphanRemoval=true)
	private List<Votacion> votos;
	
	

	public Juego(String titulo, String plataforma, String year, String desarrollador, String categoria) {
		super();
		this.titulo = titulo;
		this.plataforma = plataforma;
		this.year = year;
		this.desarrollador = desarrollador;
		this.categoria = categoria;
		this.votos= new ArrayList<Votacion>();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDesarrollador() {
		return desarrollador;
	}
	
	public List<Votacion> getVotos() {
		return votos;
	}

	public void setDesarrollador(String desarrollador) {
		this.desarrollador = desarrollador;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	} 
	
	
	
	
	
	

}
