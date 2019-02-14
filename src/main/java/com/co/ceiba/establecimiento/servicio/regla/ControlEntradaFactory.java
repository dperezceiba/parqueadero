package com.co.ceiba.establecimiento.servicio.regla;

import java.time.LocalDateTime;

import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;

public final class ControlEntradaFactory {

	private static final ControlEntradaFactory instance = new ControlEntradaFactory();

	private ControlEntradaFactory() {
	}

	public ControlEntrada getControlEntrada(Vehiculo vehiculo, LocalDateTime fechaEntrada, EntradaRepository entradaRepository) {
		if (vehiculo instanceof Carro) {
			return new ControlEntradaCarro(vehiculo, fechaEntrada, entradaRepository);
		} else {
			return new ControlEntradaMoto(vehiculo, fechaEntrada, entradaRepository);
		}
	}

	public static ControlEntradaFactory getInstance() {
		return instance;
	}

}
