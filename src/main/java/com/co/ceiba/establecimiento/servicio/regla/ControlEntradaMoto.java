package com.co.ceiba.establecimiento.servicio.regla;

import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;

public class ControlEntradaMoto extends ControlEntrada {

	public static final Integer MAXIMO_MOTO = 10;

	public ControlEntradaMoto() {
		super();
	}

	@Override
	public Boolean hayDisponibilidad(EntradaRepository entradaRepository) {
		Integer cantidad = entradaRepository.cantidadEntradasActivas(TipoVehiculo.MOTO.toString());
		return cantidad < MAXIMO_MOTO;
	}

}
