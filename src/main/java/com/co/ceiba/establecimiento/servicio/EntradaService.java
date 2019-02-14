package com.co.ceiba.establecimiento.servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.co.ceiba.establecimiento.builder.CarroBuilder;
import com.co.ceiba.establecimiento.builder.EntradaBuilder;
import com.co.ceiba.establecimiento.builder.MotoBuilder;
import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.Moto;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.entidad.EntradaEntity;
import com.co.ceiba.establecimiento.entidad.VehiculoEntity;
import com.co.ceiba.establecimiento.repositorio.CarroRepository;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;
import com.co.ceiba.establecimiento.repositorio.MotoRepository;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntrada;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntradaFactory;
import com.co.ceiba.establecimiento.util.FechaUtils;
import com.co.ceiba.establecimiento.util.TipoUtils;

@Service
public class EntradaService {

	private static Logger LOGGER = LoggerFactory.getLogger(EntradaService.class);

	private EntradaRepository entradaRepository;
	private CarroRepository carroRepository;
	private MotoRepository motoRepository;

	public EntradaService(EntradaRepository entradaRepository, CarroRepository carroRepository,
			MotoRepository motoRepository) {
		this.entradaRepository = entradaRepository;
		this.carroRepository = carroRepository;
		this.motoRepository = motoRepository;
	}

	public Entrada registrarIngreso(Vehiculo vehiculo, LocalDateTime fechaEntrada) {
		ControlEntrada controlEntrada = ControlEntradaFactory.getInstance().getControlEntrada(vehiculo, fechaEntrada,
				entradaRepository);
		controlEntrada.validarIngreso();
		LOGGER.info("Valido el ingreso del vehiculo................................");
		VehiculoEntity vehiculoEntity = getVehiculoEntity(vehiculo);
		EntradaEntity entradaEntity = new EntradaEntity();
		entradaEntity.setActivo(Boolean.TRUE);
		entradaEntity.setFechaEntrada(FechaUtils.convertir(fechaEntrada));
		entradaEntity.setTipoVehiculo(TipoUtils.getTipoVehiculo(vehiculo));
		entradaEntity.setVehiculoEntity(vehiculoEntity);
		entradaRepository.save(entradaEntity);
		return EntradaBuilder.convertirADominio(entradaEntity);
	}

	private VehiculoEntity getVehiculoEntity(Vehiculo vehiculo) {
		if (vehiculo instanceof Carro) {
			return carroRepository.findById(vehiculo.getPlaca())
					.orElse(CarroBuilder.convertirAEntity((Carro) vehiculo));
		} else {
			return motoRepository.findById(vehiculo.getPlaca()).orElse(MotoBuilder.convertirAEntity((Moto) vehiculo));
		}
	}

	public List<Entrada> listarEntradasActivas(TipoVehiculo tipoVehiculo) {
		return entradaRepository.listarEntradasActivas(tipoVehiculo.toString()).stream()
				.map(EntradaBuilder::convertirADominio).collect(Collectors.toList());
	}

	public List<Entrada> listarEntradasActivas() {
		return entradaRepository.listarEntradasActivas().stream().map(EntradaBuilder::convertirADominio)
				.collect(Collectors.toList());
	}

}
