package com.co.ceiba.establecimiento.dominio;

import com.co.ceiba.establecimiento.constante.ModalidadTarifa;
import com.co.ceiba.establecimiento.constante.TipoVehiculo;

public class Tarifa {
	private Double valor;
	private TipoVehiculo tipoVehiculo;
	private ModalidadTarifa modalidad;
	
	public Tarifa(Double valor){
		this.valor = valor;
	}
	
	public Tarifa(Double valor, TipoVehiculo tipoVehiculo, ModalidadTarifa modalidad) {
		this.valor = valor;
		this.tipoVehiculo = tipoVehiculo;
		this.modalidad = modalidad;
	}

	public Double getValor() {
		return valor;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public ModalidadTarifa getModalidad() {
		return modalidad;
	}

}
