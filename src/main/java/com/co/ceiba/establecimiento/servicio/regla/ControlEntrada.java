package com.co.ceiba.establecimiento.servicio.regla;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.dominio.excepcion.EntradaException;
import com.co.ceiba.establecimiento.entidad.EntradaEntity;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;

public abstract class ControlEntrada {

	private static final String CRITERIO_INICIO_RESTRICCION = "A";
	public static final String MSG_NO_HAY_DISPONIBILIDAD = "Actualmente no se encuentran estacionamientos disponibles para este vehiculo";
	public static final String MSG_INGRESO_NO_PERMITIDO = "Ingreso no permitido para vehiculos con esta esta placa";
	public static final String MSG_INGRESO_EXISTENTE = "Vehiculo ya se encuentra ingresado";

	private Vehiculo vehiculo;
	private LocalDateTime fechaEntrada;
	protected EntradaRepository entradaRepository;

	public ControlEntrada(Vehiculo vehiculo, LocalDateTime fechaEntrada, EntradaRepository entradaRepository) {
		this.vehiculo = vehiculo;
		this.fechaEntrada = fechaEntrada;
		this.entradaRepository = entradaRepository;
	}

	public void validarIngreso() {
		if (!hayDisponibilidad()) {
			throw new EntradaException(MSG_NO_HAY_DISPONIBILIDAD);
		}else if(!ingresoValidoSegunDiaPlaca()) {
			throw new EntradaException(MSG_INGRESO_NO_PERMITIDO);
		}else if(existeEntradaRegistrada()) {
			throw new EntradaException(MSG_INGRESO_EXISTENTE);
		}
	}

	private Boolean ingresoValidoSegunDiaPlaca() {
		if (vehiculo.getPlaca().toUpperCase().startsWith(CRITERIO_INICIO_RESTRICCION)) {
			DayOfWeek diaSemana = fechaEntrada.getDayOfWeek();
			return diaSemana != DayOfWeek.SUNDAY && diaSemana != DayOfWeek.MONDAY;
		}
		return true;
	}

	private Boolean existeEntradaRegistrada() {
		List<EntradaEntity> listado = entradaRepository.listarEntradasActivasPorVehiculo(vehiculo.getPlaca());
		return !listado.isEmpty();
	}

	protected abstract Boolean hayDisponibilidad();
}
