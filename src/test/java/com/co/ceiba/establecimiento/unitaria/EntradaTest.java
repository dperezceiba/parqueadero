package com.co.ceiba.establecimiento.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.dominio.excepcion.EntradaException;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;
import com.co.ceiba.establecimiento.servicio.EntradaService;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntrada;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntradaCarro;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntradaMoto;
import com.co.ceiba.establecimiento.testdatabuilder.CarroTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.EntradaTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.MotoTestDataBuilder;
import com.co.ceiba.establecimiento.util.FechaUtils;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntradaTest {

	private static final String PLACA_EMPIEZA_A = "AXT002";
	private static final LocalDateTime FECHA_DOMINGO = LocalDateTime.of(2019, 2, 10, 10, 2);
	private static final LocalDateTime FECHA_PRUEBA = LocalDateTime.of(2019, 2, 12, 10, 2);
	private static final Integer TOTAL_ACTIVA = 1;

	@Test
	public void registrarEntradaCarroTest() {
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		when(entradaRepository.guardar(Mockito.any(Entrada.class)))
				.thenReturn(new EntradaTestDataBuilder().conActivo(Boolean.TRUE).build());
		EntradaService entradaService = new EntradaService(entradaRepository);
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		Entrada entrada = entradaService.registrarIngreso(vehiculo, FECHA_PRUEBA);
		assertTrue(entrada.getActivo());
	}

	@Test
	public void registrarEntradaMotoTest() {
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		when(entradaRepository.guardar(Mockito.any(Entrada.class)))
		.thenReturn(new EntradaTestDataBuilder().conActivo(Boolean.TRUE).build());
		EntradaService entradaService = new EntradaService(entradaRepository);
		Vehiculo vehiculo = new MotoTestDataBuilder().build();
		Entrada entrada = entradaService.registrarIngreso(vehiculo, FECHA_PRUEBA);
		assertTrue(entrada.getActivo());
	}

	@Test
	public void registrarEntradaMotoSinDisponibilidadTest() {
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		EntradaService entradaService = new EntradaService(entradaRepository);
		when(entradaRepository.cantidadActivas(TipoVehiculo.MOTO.toString()))
				.thenReturn(ControlEntradaMoto.MAXIMO_MOTO);
		Vehiculo vehiculo = new MotoTestDataBuilder().build();
		try {
			entradaService.registrarIngreso(vehiculo, FECHA_PRUEBA);
			fail();
		} catch (EntradaException e) {
			assertEquals(ControlEntrada.MSG_NO_HAY_DISPONIBILIDAD, e.getMessage());
		}

	}

	@Test
	public void verificarEntradasActivasMoto() {
		Vehiculo vehiculo = new MotoTestDataBuilder().build();
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		EntradaService entradaService = new EntradaService(entradaRepository);
		when(entradaRepository.listarActivas(TipoVehiculo.MOTO.toString())).thenReturn(Arrays
				.asList(new EntradaTestDataBuilder().conTipoVehiculo(TipoVehiculo.MOTO).conVehiculo(vehiculo).build()));
		List<Entrada> listado = entradaService.listarActivas(TipoVehiculo.MOTO);
		assertEquals(TOTAL_ACTIVA, Integer.valueOf(listado.size()));
	}

	@Test
	public void verificarEntradasActivasCarro() {
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		EntradaService entradaService = new EntradaService(entradaRepository);
		when(entradaRepository.listarActivas(TipoVehiculo.CARRO.toString())).thenReturn(Arrays.asList(
				new EntradaTestDataBuilder().conFechaEntrada(FechaUtils.convertir(new Timestamp(new Date().getTime())))
						.conTipoVehiculo(TipoVehiculo.CARRO).conVehiculo(vehiculo).build()));
		List<Entrada> listado = entradaService.listarActivas(TipoVehiculo.CARRO);
		assertEquals(TOTAL_ACTIVA, Integer.valueOf(listado.size()));
	}

	@Test
	public void ingresoNoPermitidoParaCarro() {
		Vehiculo vehiculo = new CarroTestDataBuilder().conPlaca(PLACA_EMPIEZA_A).build();
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		EntradaService entradaService = new EntradaService(entradaRepository);
		try {
			entradaService.registrarIngreso(vehiculo, FECHA_DOMINGO);
			fail();
		} catch (EntradaException e) {
			assertEquals(ControlEntrada.MSG_INGRESO_NO_PERMITIDO, e.getMessage());
		}

	}

	@Test(expected = EntradaException.class)
	public void capturarExcepcionEntrada() {
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		when(entradaRepository.cantidadActivas(TipoVehiculo.CARRO.toString()))
				.thenReturn(ControlEntradaCarro.MAXIMO_CARRO);
		EntradaService entradaService = new EntradaService(entradaRepository);
		entradaService.registrarIngreso(vehiculo, FECHA_PRUEBA);
	}

}
