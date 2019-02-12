package com.co.ceiba.establecimiento.testdatabuilder;

import com.co.ceiba.establecimiento.dominio.Moto;

public class MotoTestDataBuilder {

	private static final String PLACA_CONST = "TRD058";
	private static final Double CILINDRAJE_CONST = 450.0;

	private String placa;
	private Double cilindraje;

	public MotoTestDataBuilder() {
		this.placa = PLACA_CONST;
		this.cilindraje = CILINDRAJE_CONST;
	}

	public MotoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public MotoTestDataBuilder conCilindraje(Double cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public Moto build() {
		return new Moto(placa, cilindraje);
	}

}
