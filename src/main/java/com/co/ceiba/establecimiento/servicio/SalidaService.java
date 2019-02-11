package com.co.ceiba.establecimiento.servicio;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.co.ceiba.establecimiento.builder.EntradaBuilder;
import com.co.ceiba.establecimiento.builder.SalidaBuilder;
import com.co.ceiba.establecimiento.builder.TarifaBuilder;
import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.Salida;
import com.co.ceiba.establecimiento.dominio.excepcion.SalidaException;
import com.co.ceiba.establecimiento.entidad.EntradaEntity;
import com.co.ceiba.establecimiento.entidad.SalidaEntity;
import com.co.ceiba.establecimiento.entidad.TarifaEntity;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;
import com.co.ceiba.establecimiento.repositorio.SalidaRepository;
import com.co.ceiba.establecimiento.repositorio.TarifaRepository;
import com.co.ceiba.establecimiento.servicio.regla.ControlSalida;

@Service
public class SalidaService {

	private static final Long HORA_MILISEGUNDOS = 3600000L;
	private static final String MSG_ENTRADA_INACTIVA = "No se puede generar salida a una entrada inactiva";
	private static final String MSG_SALIDA_REGISTRADA = "Ya se encuentra una salida registrada para esta entrada";

	private EntradaRepository entradaRepository;
	private TarifaRepository tarifaRepository;
	private SalidaRepository salidaRepository;

	public SalidaService(EntradaRepository entradaRepository, TarifaRepository tarifaRepository,
			SalidaRepository salidaRepository) {
		this.entradaRepository = entradaRepository;
		this.tarifaRepository = tarifaRepository;
		this.salidaRepository = salidaRepository;
	}

	@Transactional
	public Salida generarSalida(Entrada entrada) {
		validarSalida(entrada);
		Date fechaSalida = Calendar.getInstance().getTime();
		Long cantidadHoras = calcularCantidadHoras(entrada, fechaSalida);
		List<TarifaEntity> tarifas = tarifaRepository.listarTarifas(TipoVehiculo.valueOf(entrada.getTipoVehiculo()));
		ControlSalida controlSalida = new ControlSalida(entrada.getVehiculo(),
				tarifas.stream().map(TarifaBuilder::convertirADominio).collect(Collectors.toList()));
		SalidaEntity salidaEntity = new SalidaEntity();
		salidaEntity.setValor(controlSalida.totalPagarSalida(cantidadHoras.intValue()));
		salidaEntity.setFechaSalida(fechaSalida);
		salidaEntity.setEntradaEntity(EntradaBuilder.convertirAEntity(entrada));
		salidaEntity = salidaRepository.save(salidaEntity);
		deshabilitarEntrada(entrada);
		return SalidaBuilder.convertirADominio(salidaEntity);
	}

	private Long calcularCantidadHoras(Entrada entrada, Date fechaSalida) {
		Long diferencia = fechaSalida.getTime() - entrada.getFechaEntrada().getTime();
		return diferencia / HORA_MILISEGUNDOS;
	}

	private void validarSalida(Entrada entrada) {
		if (!entrada.getActivo()) {
			throw new SalidaException(MSG_ENTRADA_INACTIVA);
		} else {
			List<SalidaEntity> salidasAux = salidaRepository.consultarPorEntrada(entrada.getIdEntrada());
			if (!salidasAux.isEmpty()) {
				throw new SalidaException(MSG_SALIDA_REGISTRADA);
			}
		}
	}

	@Transactional
	private void deshabilitarEntrada(Entrada entrada) {
		Optional<EntradaEntity> entradaOptional = entradaRepository.findById(entrada.getIdEntrada());
		if (entradaOptional.isPresent()) {
			entradaOptional.get().setActivo(Boolean.FALSE);
			entradaRepository.save(entradaOptional.get());
		}
	}

}
