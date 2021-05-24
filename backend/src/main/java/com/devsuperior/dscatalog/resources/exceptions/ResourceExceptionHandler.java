package com.devsuperior.dscatalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class) 
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value()); //codigo 404
		err.setError("Rosource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI()); //Pega o caminho da requisição
		
		return ResponseEntity.status(status).body(err);
	}					//o metodo status permite que customizar o status que vou retornar	
	
	@ExceptionHandler(DatabaseException.class) 
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value()); //BAD_Request erro 400 de requisição.
		err.setError("Database exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI()); //Pega o caminho da requisição
		
		return ResponseEntity.status(status).body(err);
	}
	
}
