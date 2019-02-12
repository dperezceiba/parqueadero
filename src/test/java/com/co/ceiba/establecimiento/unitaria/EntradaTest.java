package com.co.ceiba.establecimiento.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.builder.EntradaBuilder;
import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;
import com.co.ceiba.establecimiento.servicio.EntradaService;
import com.co.ceiba.establecimiento.testdatabuilder.CarroTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.EntradaTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.MotoTestDataBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntradaTest {

	private static final LocalDateTime FECHA_PRUEBA = LocalDateTime.of(2019, 2, 12, 10, 2);
	private static final Integer TOTAL_ACTIVA = 1;

	@Test
	public void registrarEntradaCarroTest() {
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		EntradaService entradaService = new EntradaService(entradaRepository);
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		Entrada entrada = entradaService.registrarIngreso(vehiculo, FECHA_PRUEBA);
		assertTrue(entrada.getActivo());
	}

	@Test
	public void registrarEntradaMotoTest() {
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		EntradaService entradaService = new EntradaService(entradaRepository);
		Vehiculo vehiculo = new MotoTestDataBuilder().build();
		Entrada entrada = entradaService.registrarIngreso(vehiculo, FECHA_PRUEBA);
		assertTrue(entrada.getActivo());
	}

	@Test
	public void verificarEntradasActivasMoto() {
		Vehiculo vehiculo = new MotoTestDataBuilder().build();
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		EntradaService entradaService = new EntradaService(entradaRepository);
		when(entradaRepository.listarEntradasActivas(TipoVehiculo.MOTO.toString()))
				.thenReturn(Arrays.asList(EntradaBuilder.convertirAEntity(new EntradaTestDataBuilder()
						.conTipoVehiculo(TipoVehiculo.MOTO.toString()).conVehiculo(vehiculo).build())));
		List<Entrada> listado = entradaService.listarEntradasActivas(TipoVehiculo.MOTO);
		assertEquals(TOTAL_ACTIVA, Integer.valueOf(listado.size()));
	}

	@Test
	public void verificarEntradasActivasCarro() {
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		EntradaService entradaService = new EntradaService(entradaRepository);
		when(entradaRepository.listarEntradasActivas(TipoVehiculo.CARRO.toString()))
				.thenReturn(Arrays.asList(EntradaBuilder.convertirAEntity(new EntradaTestDataBuilder()
						.conTipoVehiculo(TipoVehiculo.CARRO.toString()).conVehiculo(vehiculo).build())));
		List<Entrada> listado = entradaService.listarEntradasActivas(TipoVehiculo.CARRO);
		assertEquals(TOTAL_ACTIVA, Integer.valueOf(listado.size()));
	}

}
