package com.co.ceiba.establecimiento.servicio.regla;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.entidad.EntradaEntity;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;

public abstract class ControlEntrada {

	private static final String CRITERIO_INICIO_RESTRICCION = "A";

	public ControlEntrada() {
	}

	public Boolean ingresoValidoSegunDia(Vehiculo vehiculo, Date fechaEntrada) {
		if (vehiculo.getPlaca().startsWith(CRITERIO_INICIO_RESTRICCION)) {
			LocalDate fechaIngreso = fechaEntrada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			DayOfWeek diaSemana = fechaIngreso.getDayOfWeek();
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
