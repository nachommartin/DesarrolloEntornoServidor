package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comentario {
	
	@Id
	private long codigoComentario;
	
	private String comentario;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@ManyToOne
    @JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	

	public Comentario(String comentario, Usuario usuario) {
		super();
		this.comentario = comentario;
		this.usuario = usuario;
		this.fecha = new Date();

	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
