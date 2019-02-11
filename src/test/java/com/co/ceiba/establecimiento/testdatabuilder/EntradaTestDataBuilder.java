package com.co.ceiba.establecimiento.testdatabuilder;

import java.util.Date;

import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.Vehiculo;

public class EntradaTestDataBuilder {

	private static final Long ID_CONST = 1L;
	private static final Date FECHA_ENTRADA_CONS = new Date();
	private static final Boolean ACTIVO_CONST = Boolean.TRUE;
	private static final Vehiculo VEHICULO_CONST = new CarroTestDataBuilder().build();
	private static final String TIPO_VEHICULO_CONST = TipoVehiculo.CARRO.toString();

	private Long id;
	private Date fechaEntrada;
	private Boolean activo;
	private Vehiculo vehiculo;
	private String tipoVehiculo;

	public EntradaTestDataBuilder() {
		this.id = ID_CONST;
		this.fechaEntrada = FECHA_ENTRADA_CONS;
		this.activo = ACTIVO_CONST;
		this.vehiculo = VEHICULO_CONST;
		this.tipoVehiculo = TIPO_VEHICULO_CONST;
	}

	public EntradaTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}

	public EntradaTestDataBuilder conFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
		return this;
	}

	public EntradaTestDataBuilder conActivo(Boolean activo) {
		this.activo = activo;
		return this;
	}

	public EntradaTestDataBuilder conVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}

	public EntradaTestDataBuilder conTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public Entrada build() {
		Entrada entrada = new Entrada(id, fechaEntrada, activo);
		entrada.setTipoVehiculo(tipoVehiculo);
		entrada.setVehiculo(vehiculo);
		return entrada;
	}

}
