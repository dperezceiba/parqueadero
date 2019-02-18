package com.co.ceiba.establecimiento.dominio;

public class Moto extends Vehiculo {

	private Double cilindraje;
	
	public Moto() {
	}

	public Moto(String placa, Double cilindraje) {
		super(placa);
		this.cilindraje = cilindraje;
	}

	public Double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Double cilindraje) {
		this.cilindraje = cilindraje;
	}
	
	@Override
	public TipoVehiculo getTipoVehiculo() {
		return TipoVehiculo.MOTO;
	}

}
