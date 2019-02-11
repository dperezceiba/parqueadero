package com.co.ceiba.establecimiento.servicio;

import java.util.Date;
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

@Service
public class EntradaService {

	private static final String MSG_NO_HAY_DISPONIBILIDAD = "Actualmente no se encuentran estacionamientos disponibles para este vehiculo";
	private static final String MSG_INGRESO_NO_PERMITIDO = "Ingreso no permitido para vehiculos con esta esta placa";
	private static final String MSG_INGRESO_EXISTENTE = "Vehiculo ya se encuentra ingresado";

	private EntradaRepository entradaRepository;

	public EntradaService(EntradaRepository entradaRepository) {
		this.entradaRepository = entradaRepository;
	}

	public Entrada registrarIngreso(Vehiculo vehiculo) {
		Date fechaEntrada = new Date();
		validarIngreso(vehiculo, fechaEntrada);
		EntradaEntity entradaEntity = new EntradaEntity();
		entradaEntity.setActivo(Boolean.TRUE);
		entradaEntity.setFechaEntrada(fechaEntrada);
		entradaEntity.setTipoVehiculo(getTipovehiculo(vehiculo));
		entradaEntity.setVehiculoEntity(getVehiculoEntity(vehiculo));
		entradaRepository.save(entradaEntity);
		return EntradaBuilder.convertirADominio(entradaEntity);
	}

	public void validarIngreso(Vehiculo vehiculo, Date fechaEntrada) throws EntradaException {
		ControlEntrada controlEntrada = ControlEntradaFactory.getInstance().getControlEntrada(vehiculo);
		if (!controlEntrada.hayDisponibilidad(entradaRepository)) {
			throw new EntradaException(MSG_NO_HAY_DISPONIBILIDAD);
		} else if (!controlEntrada.ingresoValidoSegunDia(vehiculo, fechaEntrada)) {
			throw new EntradaException(MSG_INGRESO_NO_PERMITIDO);
		}else if(controlEntrada.existeEntradaRegistrada(vehiculo, entradaRepository)) {
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
		return entradaRepository.listarEntradasActivas(tipoVehiculo.toString()).stream().map(EntradaBuilder::convertirADominio)
				.collect(Collectors.toList());
	}

}
