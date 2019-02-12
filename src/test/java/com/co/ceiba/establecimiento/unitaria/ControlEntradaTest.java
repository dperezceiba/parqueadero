package com.co.ceiba.establecimiento.unitaria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.builder.EntradaBuilder;
import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntrada;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntradaCarro;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntradaFactory;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntradaMoto;
import com.co.ceiba.establecimiento.testdatabuilder.CarroTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.EntradaTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.MotoTestDataBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ControlEntradaTest {

	public static final String PLACA_EMPIEZA_A = "AXT002";
	public static final String PLACA_EMPIEZA_DIFERENTE_A = "TXT002";
	public static final LocalDate FECHA_LUNES = LocalDate.of(2019, 2, 11);

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

	@Test
	public void ingresoValidoSegunDiaReglaInvalidaDiaFecha() {
		Vehiculo vehiculo = new MotoTestDataBuilder().conPlaca(PLACA_EMPIEZA_A).build();
		ControlEntrada controlEntrada = ControlEntradaFactory.getInstance().getControlEntrada(vehiculo);
		assertFalse(
				controlEntrada.ingresoValidoSegunDia(vehiculo, new Date(java.sql.Date.valueOf(FECHA_LUNES).getTime())));
	}

	@Test
	public void ingresoValidoSegunDiaReglaValidaDiaFecha() {
		Vehiculo vehiculo = new MotoTestDataBuilder().conPlaca(PLACA_EMPIEZA_DIFERENTE_A).build();
		ControlEntrada controlEntrada = ControlEntradaFactory.getInstance().getControlEntrada(vehiculo);
		assertTrue(
				controlEntrada.ingresoValidoSegunDia(vehiculo, new Date(java.sql.Date.valueOf(FECHA_LUNES).getTime())));
	}

	@Test
	public void existeEntradaRegistrada() {
		Vehiculo vehiculo = new MotoTestDataBuilder().build();
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		when(entradaRepository.listarEntradasActivasPorVehiculo(vehiculo.getPlaca())).thenReturn(Arrays
				.asList(EntradaBuilder.convertirAEntity(new EntradaTestDataBuilder().conVehiculo(vehiculo).build())));
		ControlEntrada controlEntrada = ControlEntradaFactory.getInstance().getControlEntrada(vehiculo);
		assertTrue(controlEntrada.existeEntradaRegistrada(vehiculo, entradaRepository));
	}
	
	@Test
	public void noExisteEntradaRegistrada() {
		Vehiculo vehiculo = new MotoTestDataBuilder().build();
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		ControlEntrada controlEntrada = ControlEntradaFactory.getInstance().getControlEntrada(vehiculo);
		assertFalse(controlEntrada.existeEntradaRegistrada(vehiculo, entradaRepository));
	}

}
