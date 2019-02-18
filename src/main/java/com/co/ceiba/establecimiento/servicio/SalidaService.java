package com.co.ceiba.establecimiento.servicio;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.Salida;
import com.co.ceiba.establecimiento.dominio.excepcion.SalidaException;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;
import com.co.ceiba.establecimiento.repositorio.SalidaRepository;
import com.co.ceiba.establecimiento.servicio.regla.ControlSalida;

@Service
public class SalidaService {

	private static final Long HORA_MILISEGUNDOS = 3600000L;
	public static final String MSG_ENTRADA_NO_ENCONTRADA = "No se puede generar salida porque no hay entrada relacionada";
	public static final String MSG_SALIDA_REGISTRADA = "Ya se encuentra una salida registrada para esta entrada";

	private EntradaRepository entradaRepository;
	private SalidaRepository salidaRepository;
	private ValorTarifaService valorTarifaService;

	public SalidaService(EntradaRepository entradaRepository, SalidaRepository salidaRepository,
			ValorTarifaService valorTarifaService) {
		this.entradaRepository = entradaRepository;
		this.salidaRepository = salidaRepository;
		this.valorTarifaService = valorTarifaService;
	}

	@Transactional
	public Salida generar(String placa, LocalDateTime fechaSalida) {
		List<Entrada> entradas = entradaRepository.listarActivasPorVehiculo(placa);
		if (!entradas.isEmpty()) {
			Entrada entrada = entradas.get(0);
			Salida salida = calcularTotal(entrada, fechaSalida);
			salidaRepository.guardar(salida);
			return salida;
		} else {
			throw new SalidaException(MSG_ENTRADA_NO_ENCONTRADA);
		}
	}

	private Salida calcularTotal(Entrada entrada, LocalDateTime fechaSalida) {
		validar(entrada);
		Long cantidadHoras = calcularCantidadHoras(entrada.getFechaEntrada(), fechaSalida);
		ControlSalida controlSalida = new ControlSalida(entrada.getVehiculo(), valorTarifaService);
		Salida salida = new Salida();
		salida.setEntrada(entrada);
		salida.setFechaSalida(fechaSalida);
		salida.setValor(controlSalida.totalPagarSalida(cantidadHoras.intValue()));
		return salida;
	}

	private void validar(Entrada entrada) {
		if (!salidaRepository.consultarPorEntrada(entrada.getIdEntrada()).isEmpty()) {
			throw new SalidaException(MSG_SALIDA_REGISTRADA);
		}
	}

	private Long calcularCantidadHoras(LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
		Long diferencia = Timestamp.valueOf(fechaSalida).getTime() - Timestamp.valueOf(fechaEntrada).getTime();
		return diferencia / HORA_MILISEGUNDOS;
	}

}
