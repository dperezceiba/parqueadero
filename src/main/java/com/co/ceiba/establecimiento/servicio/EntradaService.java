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
import com.co.ceiba.establecimiento.dominio.excepcion.EntradaException;
import com.co.ceiba.establecimiento.entidad.EntradaEntity;
import com.co.ceiba.establecimiento.entidad.VehiculoEntity;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntrada;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntradaFactory;
import com.co.ceiba.establecimiento.util.FechaUtils;

@Service
public class EntradaService {

	public static final String MSG_NO_HAY_DISPONIBILIDAD = "Actualmente no se encuentran estacionamientos disponibles para este vehiculo";
	public static final String MSG_INGRESO_NO_PERMITIDO = "Ingreso no permitido para vehiculos con esta esta placa";
	public static final String MSG_INGRESO_EXISTENTE = "Vehiculo ya se encuentra ingresado";

	private EntradaRepository entradaRepository;

	public EntradaService(EntradaRepository entradaRepository) {
		this.entradaRepository = entradaRepository;
	}

	public Entrada registrarIngreso(Vehiculo vehiculo, LocalDateTime fechaEntrada) {
		validarIngreso(vehiculo, fechaEntrada);
		EntradaEntity entradaEntity = new EntradaEntity();
		entradaEntity.setActivo(Boolean.TRUE);
		entradaEntity.setFechaEntrada(FechaUtils.convertir(fechaEntrada));
		entradaEntity.setTipoVehiculo(getTipovehiculo(vehiculo));
		entradaEntity.setVehiculoEntity(getVehiculoEntity(vehiculo));
		entradaRepository.save(entradaEntity);
		return EntradaBuilder.convertirADominio(entradaEntity);
	}

	private void validarIngreso(Vehiculo vehiculo, LocalDateTime fechaEntrada) {
		ControlEntrada controlEntrada = ControlEntradaFactory.getInstance().getControlEntrada(vehiculo);
		if (!controlEntrada.hayDisponibilidad(entradaRepository)) {
			throw new EntradaException(MSG_NO_HAY_DISPONIBILIDAD);
		} else if (!controlEntrada.ingresoValidoSegunDia(vehiculo, fechaEntrada)) {
			throw new EntradaException(MSG_INGRESO_NO_PERMITIDO);
		} else if (controlEntrada.existeEntradaRegistrada(vehiculo, entradaRepository)) {
			throw new EntradaException(MSG_INGRESO_EXISTENTE);
		}
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

}
