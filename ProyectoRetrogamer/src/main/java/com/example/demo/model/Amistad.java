package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Amistad{
	@Id 	
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long referenciaAmigo; 
	
	@ManyToOne()
    @JoinColumn(name = "user_id")
    private Usuario usuarioSource;

    @ManyToOne()
    @JoinColumn(name = "follower_id")
    private Usuario usuarioTarget;

	public Amistad(Usuario usuarioSource, Usuario usuarioTarget) {
		super();
		this.usuarioSource = usuarioSource;
		this.usuarioTarget = usuarioTarget;
	}
	
	public Amistad() {
		super();
	}

	public Usuario getUsuarioSource() {
		return usuarioSource;
	}

	public void setUsuarioSource(Usuario usuarioSource) {
		this.usuarioSource = usuarioSource;
	}

	public Usuario getUsuarioTarget() {
		return usuarioTarget;
	}

	public void setUsuarioTarget(Usuario usuarioTarget) {
		this.usuarioTarget = usuarioTarget;
	}
	
	
    
	
	

}
