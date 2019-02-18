package com.co.ceiba.establecimiento.servicio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntrada;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntradaCarro;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntradaMoto;

@Service
public class EntradaService {

	private EntradaRepository entradaRepository;

	public EntradaService(EntradaRepository entradaRepository) {
		this.entradaRepository = entradaRepository;
	}

	public Entrada registrarIngreso(Vehiculo vehiculo, LocalDateTime fechaEntrada) {
		ControlEntrada controlEntrada = getControlEntrada(vehiculo, fechaEntrada, entradaRepository);
		controlEntrada.validarIngreso();
		Entrada entrada = new Entrada();
		entrada.setActivo(Boolean.TRUE);
		entrada.setFechaEntrada(fechaEntrada);
		entrada.setVehiculo(vehiculo);
		return entradaRepository.guardar(entrada);
	}

	public Entrada consultarPorId(Long id) {
		return entradaRepository.consultarPorId(id);
	}

	public List<Entrada> listarActivas(TipoVehiculo tipoVehiculo) {
		return entradaRepository.listarActivas(tipoVehiculo.toString());
	}

	public List<Entrada> listarActivas() {
		return entradaRepository.listarActivas();
	}

	public ControlEntrada getControlEntrada(Vehiculo vehiculo, LocalDateTime fechaEntrada,
			EntradaRepository entradaRepository) {
		if (vehiculo instanceof Carro) {
			return new ControlEntradaCarro(vehiculo, fechaEntrada, entradaRepository);
		} else {
			return new ControlEntradaMoto(vehiculo, fechaEntrada, entradaRepository);
		}
	}

}
