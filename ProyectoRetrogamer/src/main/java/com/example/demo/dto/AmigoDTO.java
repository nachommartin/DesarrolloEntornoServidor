package com.example.demo.dto;

import java.io.Serializable;

public class AmigoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5037276998537268932L;
	private String correo;
	
	
	public AmigoDTO() {
		super();
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	} 
	
	
	
	

}
