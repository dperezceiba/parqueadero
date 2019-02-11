package com.co.ceiba.establecimiento.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="carro")
public class CarroEntity extends VehiculoEntity{
	
	@Column(name="modelo", nullable=false)
	private String modelo;

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	
	
}
