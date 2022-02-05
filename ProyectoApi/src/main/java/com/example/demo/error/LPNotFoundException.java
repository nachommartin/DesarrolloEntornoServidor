package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LPNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -6734027569391630482L;

	public LPNotFoundException(long ref) {
		super("No se puede encontrar productos en el pedido con la siguiente referencia: " + ref);
	
	}


}
