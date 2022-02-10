package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Votacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long codigo; 
	
	@ManyToOne
    @JoinColumn(name = "juego_id")
	private Juego juego;
	
	@ManyToOne
    @JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	private int voto;
	
	@Column(name= "reseña")
	private String review;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	

	public Votacion(Juego juego, Usuario usuario, int voto, String review) {
		super();
		this.juego = juego;
		this.usuario = usuario;
		this.voto = voto;
		this.review = review;
		this.fecha = new Date();
	}
	
	public Votacion(Juego juego, Usuario usuario, int voto) {
		super();
		this.juego = juego;
		this.usuario = usuario;
		this.voto = voto;
		this.fecha = new Date();;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	} 
	
	
	

}
