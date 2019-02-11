package com.co.ceiba.establecimiento.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "vehiculo")
@Inheritance(strategy = InheritanceType.JOINED)
public class VehiculoEntity {

	@Id
	@Column(name = "placa", nullable = false)
	private String placa;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

}
