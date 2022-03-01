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
	private Usuario usuarioReceptor;
	
	 @ManyToOne()
	 @JoinColumn(name = "follower_id")
	 private Usuario usuarioEmisor;
	
	

	public Comentario(String comentario, Usuario usuarioSource, Usuario usuarioTarget) {
		super();
		this.comentario = comentario;
		this.usuarioEmisor = usuarioSource;
		this.usuarioReceptor = usuarioTarget;
		this.fecha = new Date();

	}
	
	public Comentario() {
		super();
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Usuario getUsuarioReceptor() {
		return usuarioReceptor;
	}

	public void setUsuarioReceptor(Usuario usuarioReceptor) {
		this.usuarioReceptor = usuarioReceptor;
	}

	public Usuario getUsuarioEmisor() {
		return usuarioEmisor;
	}

	public void setUsuarioEmisor(Usuario usuarioEmisor) {
		this.usuarioEmisor = usuarioEmisor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
	

}
