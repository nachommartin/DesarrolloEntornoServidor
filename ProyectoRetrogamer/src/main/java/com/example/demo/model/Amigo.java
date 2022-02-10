package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Amigo{
	@Id
	private Usuario amigo1;
	
	@Id
	private Usuario amigo2;

}
