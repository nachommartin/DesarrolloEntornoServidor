package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy="juego", cascade = CascadeType.ALL, orphanRemoval=true)
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
	
	public Juego() {
		super();
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

	@Override
	public int hashCode() {
		return Objects.hash(referencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Juego other = (Juego) obj;
		return referencia == other.referencia;
	}

	@Override
	public String toString() {
		return "Juego " + titulo;
	} 
	
	
	
	
	
	

}
