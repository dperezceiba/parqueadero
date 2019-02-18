package com.co.ceiba.establecimiento.dominio;

public class Carro extends Vehiculo {

	private String modelo;
	
	public Carro() {
	}
	
	public Carro(String placa, String modelo) {
		super(placa);
		this.modelo = modelo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	@Override
	public TipoVehiculo getTipoVehiculo() {
		return TipoVehiculo.CARRO;
	}

}
