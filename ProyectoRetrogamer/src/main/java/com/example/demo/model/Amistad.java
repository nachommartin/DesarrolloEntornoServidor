package com.example.demo.model;

import java.util.Objects;

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
    private Usuario usuario;

    @ManyToOne()
    @JoinColumn(name = "follower_id")
    private Usuario follower;

	public Amistad(Usuario usuarioSource, Usuario usuarioTarget) {
		super();
		this.usuario = usuarioSource;
		this.follower = usuarioTarget;
	}
	
	public Amistad() {
		super();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuarioSource) {
		this.usuario = usuarioSource;
	}

	public Usuario getFollower() {
		return follower;
	}

	public void setFollower(Usuario usuarioTarget) {
		this.follower = usuarioTarget;
	}


	
    
	
	

}
