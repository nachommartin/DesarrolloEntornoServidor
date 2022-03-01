package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenVotosException extends RuntimeException {
	
	public ForbiddenVotosException(String user) {
		super("No tienes acceso a ver los votos del usuario: " + user);
	}


}
