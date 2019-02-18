package com.co.ceiba.establecimiento.servicio.regla;

import java.time.LocalDateTime;

import com.co.ceiba.establecimiento.dominio.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;

public class ControlEntradaCarro extends ControlEntrada {
	
	public static final Integer MAXIMO_CARRO = 20;
	
	public ControlEntradaCarro(Vehiculo vehiculo, LocalDateTime fechaEntrada, EntradaRepository entradaRepository) {
		super(vehiculo, fechaEntrada, entradaRepository);
	}

	@Override
	public Boolean hayDisponibilidad() {
		Integer cantidad = entradaRepository.cantidadActivas(TipoVehiculo.CARRO.toString());
		return cantidad < MAXIMO_CARRO;
	}

	

}
