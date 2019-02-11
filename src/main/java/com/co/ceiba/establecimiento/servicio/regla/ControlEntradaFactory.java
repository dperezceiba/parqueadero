package com.co.ceiba.establecimiento.servicio.regla;

import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.dominio.Vehiculo;

public final class ControlEntradaFactory {

	private static final ControlEntradaFactory instance = new ControlEntradaFactory();

	private ControlEntradaFactory() {
	}

	public ControlEntrada getControlEntrada(Vehiculo vehiculo) {
		if (vehiculo instanceof Carro) {
			return new ControlEntradaCarro();
		} else {
			return new ControlEntradaMoto();
		}
	}

	public static ControlEntradaFactory getInstance() {
		return instance;
	}

}
