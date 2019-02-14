package com.co.ceiba.establecimiento.testdatabuilder;

import com.co.ceiba.establecimiento.constante.ModalidadTarifa;
import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Tarifa;

public class TarifaTestDataBuilder {

	private static final Double VALOR_CONST = 1000.0;
	private static final String TIPO_VEHICULO_CONST = TipoVehiculo.CARRO.toString();
	private static final String MODALIDAD_CONST = ModalidadTarifa.DIA.toString();
	
	private Double valor;
	private String tipoVehiculo;
	private String modalidad;

	public TarifaTestDataBuilder() {
		this.valor = VALOR_CONST;
		this.tipoVehiculo = TIPO_VEHICULO_CONST;
		this.modalidad = MODALIDAD_CONST;
	}

	public TarifaTestDataBuilder conValor(Double valor) {
		this.valor = valor;
		return this;
	}

	public TarifaTestDataBuilder conTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public TarifaTestDataBuilder conModalidad(String modalidad) {
		this.modalidad = modalidad;
		return this;
	}

	public Tarifa build() {
		return new Tarifa(valor, tipoVehiculo, modalidad);
	}

}
