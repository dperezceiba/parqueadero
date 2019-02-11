package com.co.ceiba.establecimiento.controller;

import java.util.Calendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.co.ceiba.establecimiento.dominio.ErrorResponse;
import com.co.ceiba.establecimiento.dominio.excepcion.EntradaException;
import com.co.ceiba.establecimiento.dominio.excepcion.SalidaException;

@ControllerAdvice
public class ErrorHandlerAdvice {

	@ExceptionHandler(EntradaException.class)
	public ResponseEntity<ErrorResponse> handleException(EntradaException ex, WebRequest request) {
		ErrorResponse excepcion = new ErrorResponse(Calendar.getInstance().getTime(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(excepcion, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(SalidaException.class)
	public ResponseEntity<ErrorResponse> handleException(SalidaException ex, WebRequest request) {
		ErrorResponse excepcion = new ErrorResponse(Calendar.getInstance().getTime(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(excepcion, HttpStatus.CONFLICT);
	}

}
