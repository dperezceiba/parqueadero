package com.co.ceiba.establecimiento.testdatabuilder;

import com.co.ceiba.establecimiento.dominio.Carro;

public class CarroTestDataBuilder {

	private static final String PLACA_CONST = "TRO058";
	private static final String MODELO_CONST = "2018";

	private String placa;
	private String modelo;

	public CarroTestDataBuilder() {
		this.placa = PLACA_CONST;
		this.modelo = MODELO_CONST;
	}

	public CarroTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public CarroTestDataBuilder conModelo(String modelo) {
		this.modelo = modelo;
		return this;
	}

	public Carro build() {
		return new Carro(placa, modelo);
	}

}
