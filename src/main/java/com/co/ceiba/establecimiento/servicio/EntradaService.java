package com.co.ceiba.establecimiento.servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntrada;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntradaFactory;
import com.co.ceiba.establecimiento.util.FechaUtils;

@Service
public class EntradaService {

	private EntradaRepository entradaRepository;

	public EntradaService(EntradaRepository entradaRepository) {
		this.entradaRepository = entradaRepository;
	}

	public Entrada registrarIngreso(Vehiculo vehiculo, LocalDateTime fechaEntrada) {
		ControlEntrada controlEntrada = ControlEntradaFactory.getInstance().getControlEntrada(vehiculo, fechaEntrada,
				entradaRepository);
		controlEntrada.validarIngreso();
		EntradaEntity entradaEntity = new EntradaEntity();
		entradaEntity.setActivo(Boolean.TRUE);
		entradaEntity.setFechaEntrada(FechaUtils.convertir(fechaEntrada));
		entradaEntity.setTipoVehiculo(getTipovehiculo(vehiculo));
		entradaEntity.setVehiculoEntity(getVehiculoEntity(vehiculo));
		entradaRepository.save(entradaEntity);
		return EntradaBuilder.convertirADominio(entradaEntity);
	}

	private TipoVehiculo getTipovehiculo(Vehiculo vehiculo) {
		return (vehiculo instanceof Carro) ? TipoVehiculo.CARRO : TipoVehiculo.MOTO;
	}

	private VehiculoEntity getVehiculoEntity(Vehiculo vehiculo) {
		return (vehiculo instanceof Carro) ? CarroBuilder.convertirAEntity((Carro) vehiculo)
				: MotoBuilder.convertirAEntity((Moto) vehiculo);
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
