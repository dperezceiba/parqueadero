package com.co.ceiba.establecimiento.dominio;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Entrada {
	private Long idEntrada;
	private LocalDateTime fechaEntrada;
	private Boolean activo;
	private Vehiculo vehiculo;
	private String tipoVehiculo;

	public Entrada() {
	}

	public Entrada(Long idEntrada, LocalDateTime fechaEntrada, Boolean activo) {
		this.idEntrada = idEntrada;
		this.fechaEntrada = fechaEntrada;
		this.activo = activo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public Long getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(Long idEntrada) {
		this.idEntrada = idEntrada;
	}
}
