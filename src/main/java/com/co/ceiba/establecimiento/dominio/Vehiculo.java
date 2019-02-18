package com.co.ceiba.establecimiento.dominio;

public class Vehiculo {

	private String placa;

	public Vehiculo() {
		super();
	}

	public Vehiculo(String placa) {
		super();
		this.placa = placa;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public TipoVehiculo getTipoVehiculo() {
		return TipoVehiculo.CARRO;
	}

}
