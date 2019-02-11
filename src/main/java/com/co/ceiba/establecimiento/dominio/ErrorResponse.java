package com.co.ceiba.establecimiento.dominio;

import java.util.Date;

public class ErrorResponse {
	private Date fecha;
	private String mensaje;
	private String descripcion;
	
	public ErrorResponse() {
		super();
	}
	
	public ErrorResponse(Date fecha, String mensaje, String descripcion) {
		this.fecha = fecha;
		this.mensaje = mensaje;
		this.descripcion = descripcion;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
