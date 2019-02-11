package com.co.ceiba.establecimiento.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="moto")
public class MotoEntity extends VehiculoEntity{
	
	@Column(name="cilindraje", nullable=false)
	private Double cilindraje;

	public Double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Double cilindraje) {
		this.cilindraje = cilindraje;
	}
	
}
