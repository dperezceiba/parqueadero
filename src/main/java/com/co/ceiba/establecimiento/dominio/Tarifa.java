package com.co.ceiba.establecimiento.dominio;

public class Tarifa {
	private Double valor;
	private String tipoVehiculo;
	private String modalidad;
	
	public Tarifa(Double valor){
		this.valor = valor;
	}
	
	public Tarifa(Double valor, String tipoVehiculo, String modalidad) {
		this.valor = valor;
		this.tipoVehiculo = tipoVehiculo;
		this.modalidad = modalidad;
	}

	public Double getValor() {
		return valor;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public String getModalidad() {
		return modalidad;
	}

}
