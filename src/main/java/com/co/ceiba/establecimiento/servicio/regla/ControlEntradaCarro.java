package com.co.ceiba.establecimiento.servicio.regla;

import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;

public class ControlEntradaCarro extends ControlEntrada {

	public static final Integer MAXIMO_CARRO = 20;

	public ControlEntradaCarro() {
		super();
	}

	@Override
	public Boolean hayDisponibilidad(EntradaRepository entradaRepository) {
		Integer cantidad = entradaRepository.cantidadEntradasActivas(TipoVehiculo.CARRO.toString());
		return cantidad < MAXIMO_CARRO;
	}

	

}
