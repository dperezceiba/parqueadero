package com.co.ceiba.establecimiento.servicio.regla;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.entidad.EntradaEntity;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;

public abstract class ControlEntrada {

	private static final String CRITERIO_INICIO_RESTRICCION = "A";

	public ControlEntrada() {
	}

	public Boolean ingresoValidoSegunDia(Vehiculo vehiculo, LocalDateTime fechaEntrada) {
		if (vehiculo.getPlaca().toUpperCase().startsWith(CRITERIO_INICIO_RESTRICCION)) {
			DayOfWeek diaSemana = fechaEntrada.getDayOfWeek();
			return diaSemana != DayOfWeek.SUNDAY && diaSemana != DayOfWeek.MONDAY;
		}
		return true;
	}

	public Boolean existeEntradaRegistrada(Vehiculo vehiculo, EntradaRepository entradaRepository) {
		List<EntradaEntity> listado = entradaRepository.listarEntradasActivasPorVehiculo(vehiculo.getPlaca());
		return !listado.isEmpty();
	}

	public abstract Boolean hayDisponibilidad(EntradaRepository entradaRepository);
}
