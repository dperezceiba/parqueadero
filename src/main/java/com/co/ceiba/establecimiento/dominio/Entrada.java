package com.co.ceiba.establecimiento.dominio;

import java.util.Date;

public class Entrada {
	private Long idEntrada;
	private Date fechaEntrada;
	private Boolean activo;
	private Vehiculo vehiculo;
	private String tipoVehiculo;

	public Entrada() {
	}

	public Entrada(Long idEntrada, Date fechaEntrada, Boolean activo) {
		this.setIdEntrada(idEntrada);
		this.fechaEntrada = fechaEntrada;
		this.activo = activo;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
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
