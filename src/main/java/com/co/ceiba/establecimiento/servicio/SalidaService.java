package com.co.ceiba.establecimiento.servicio;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.co.ceiba.establecimiento.builder.EntradaBuilder;
import com.co.ceiba.establecimiento.builder.SalidaBuilder;
import com.co.ceiba.establecimiento.builder.TarifaBuilder;
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
	public static final String MSG_ENTRADA_NO_ENCONTRADA = "No se puede generar salida porque no hay entrada relacionada";
	public static final String MSG_ENTRADA_INACTIVA = "No se puede generar salida a una entrada inactiva";
	public static final String MSG_SALIDA_REGISTRADA = "Ya se encuentra una salida registrada para esta entrada";

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
	public Salida generarSalida(Entrada entrada, LocalDateTime fechaSalida) {
		Salida salida = calcularTotalSalida(entrada, fechaSalida);
		SalidaEntity salidaEntity = SalidaBuilder.convertirAEntity(salida);
		salidaRepository.save(salidaEntity);
		deshabilitarEntrada(salidaEntity);
		return SalidaBuilder.convertirADominio(salidaEntity);
	}

	private Salida calcularTotalSalida(Entrada entrada, LocalDateTime fechaSalida) {
		Optional<EntradaEntity> resultado = entradaRepository.findById(entrada.getIdEntrada());
		if (!resultado.isPresent()) {
			throw new SalidaException(MSG_ENTRADA_NO_ENCONTRADA);
		}
		validarSalida(resultado.get());
		Long cantidadHoras = calcularCantidadHoras(entrada, fechaSalida);
		List<TarifaEntity> tarifas = tarifaRepository.listarTarifas(entrada.getTipoVehiculo());
		ControlSalida controlSalida = new ControlSalida(entrada.getVehiculo(),
				tarifas.stream().map(TarifaBuilder::convertirADominio).collect(Collectors.toList()));
		Salida salida = new Salida();
		salida.setEntrada(EntradaBuilder.convertirADominio(resultado.get()));
		salida.setFechaSalida(fechaSalida);
		salida.setValor(controlSalida.totalPagarSalida(cantidadHoras.intValue()));
		return salida;
	}

	private void validarSalida(EntradaEntity entradaEntity) {
		if (!entradaEntity.getActivo()) {
			throw new SalidaException(MSG_ENTRADA_INACTIVA);
		} else {
			List<SalidaEntity> salidasAux = salidaRepository.consultarPorEntrada(entradaEntity.getId());
			if (!salidasAux.isEmpty()) {
				throw new SalidaException(MSG_SALIDA_REGISTRADA);
			}
		}
	}

	private Long calcularCantidadHoras(Entrada entrada, LocalDateTime fechaSalida) {
		Long diferencia = Timestamp.valueOf(fechaSalida).getTime()
				- Timestamp.valueOf(entrada.getFechaEntrada()).getTime();
		return diferencia / HORA_MILISEGUNDOS;
	}

	private void deshabilitarEntrada(SalidaEntity salidaEntity) {
		if (salidaEntity.getEntradaEntity() != null) {
			EntradaEntity entradaEntity = salidaEntity.getEntradaEntity();
			entradaEntity.setActivo(Boolean.FALSE);
			entradaRepository.save(entradaEntity);
		}
	}

}
