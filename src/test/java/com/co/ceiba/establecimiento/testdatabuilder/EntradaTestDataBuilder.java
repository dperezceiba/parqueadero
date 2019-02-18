package com.co.ceiba.establecimiento.testdatabuilder;

import java.time.LocalDateTime;

import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Vehiculo;

public class EntradaTestDataBuilder {

	private static final Long ID_CONST = 1L;
	private static final LocalDateTime FECHA_ENTRADA_CONS = LocalDateTime.now();
	private static final Boolean ACTIVO_CONST = Boolean.TRUE;
	private static final Vehiculo VEHICULO_CONST = new CarroTestDataBuilder().build();
	private static final TipoVehiculo TIPO_VEHICULO_CONST = TipoVehiculo.CARRO;

	private Long id;
	private LocalDateTime fechaEntrada;
	private Boolean activo;
	private Vehiculo vehiculo;
	private TipoVehiculo tipoVehiculo;

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

	public EntradaTestDataBuilder conFechaEntrada(LocalDateTime fechaEntrada) {
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

	public EntradaTestDataBuilder conTipoVehiculo(TipoVehiculo tipoVehiculo) {
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
