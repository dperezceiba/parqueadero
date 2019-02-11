package com.co.ceiba.establecimiento.unitaria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntrada;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntradaCarro;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntradaFactory;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntradaMoto;
import com.co.ceiba.establecimiento.testdatabuilder.CarroTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.MotoTestDataBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ControlEntradaTest {

	@Test
	public void hayDisponibilidadCarro() {
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		ControlEntrada controlEntrada = ControlEntradaFactory.getInstance().getControlEntrada(vehiculo);
		assertTrue(controlEntrada.hayDisponibilidad(entradaRepository));
	}

	@Test
	public void noHayDisponibilidadCarro() {
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		when(entradaRepository.cantidadEntradasActivas(TipoVehiculo.CARRO.toString()))
				.thenReturn(ControlEntradaCarro.MAXIMO_CARRO);
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		ControlEntrada controlEntrada = ControlEntradaFactory.getInstance().getControlEntrada(vehiculo);
		assertFalse(controlEntrada.hayDisponibilidad(entradaRepository));
	}

	@Test
	public void hayDisponibilidadMoto() {
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		Vehiculo vehiculo = new MotoTestDataBuilder().build();
		ControlEntrada controlEntrada = ControlEntradaFactory.getInstance().getControlEntrada(vehiculo);
		assertTrue(controlEntrada.hayDisponibilidad(entradaRepository));
	}

	@Test
	public void noHayDisponibilidadMoto() {
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		when(entradaRepository.cantidadEntradasActivas(TipoVehiculo.MOTO.toString()))
				.thenReturn(ControlEntradaMoto.MAXIMO_MOTO);
		Vehiculo vehiculo = new MotoTestDataBuilder().build();
		ControlEntrada controlEntrada = ControlEntradaFactory.getInstance().getControlEntrada(vehiculo);
		assertFalse(controlEntrada.hayDisponibilidad(entradaRepository));
	}

}
