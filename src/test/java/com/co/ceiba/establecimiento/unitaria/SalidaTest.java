package com.co.ceiba.establecimiento.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.Salida;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.dominio.excepcion.SalidaException;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;
import com.co.ceiba.establecimiento.repositorio.SalidaRepository;
import com.co.ceiba.establecimiento.servicio.EntradaService;
import com.co.ceiba.establecimiento.servicio.SalidaService;
import com.co.ceiba.establecimiento.servicio.ValorTarifaService;
import com.co.ceiba.establecimiento.testdatabuilder.CarroTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.EntradaTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.MotoTestDataBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SalidaTest {

	private static final LocalDateTime FECHA_ENTRADA = LocalDateTime.of(2019, 2, 12, 8, 2);
	private static final LocalDateTime FECHA_SALIDA = LocalDateTime.of(2019, 2, 12, 9, 2);

	@Test
	public void calcularSalidaCarroTest() {
		ValorTarifaService valorTarifa = new ValorTarifaService();
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		SalidaRepository salidaRepository = mock(SalidaRepository.class);
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		when(entradaRepository.guardar(Mockito.any(Entrada.class)))
				.thenReturn(new EntradaTestDataBuilder().conActivo(Boolean.TRUE).build());

		EntradaService entradaService = new EntradaService(entradaRepository);

		Entrada entrada = entradaService.registrarIngreso(vehiculo, FECHA_ENTRADA);
		assertNotNull(entrada);

		when(entradaRepository.consultarPorId(entrada.getIdEntrada())).thenReturn(entrada);
		when(salidaRepository.guardar(Mockito.any(Salida.class))).thenReturn(new Salida());
		when(entradaRepository.listarActivasPorVehiculo(vehiculo.getPlaca()))
		.thenReturn(Arrays.asList(new EntradaTestDataBuilder().conVehiculo(vehiculo).build()));

		SalidaService salidaService = new SalidaService(entradaRepository, salidaRepository, valorTarifa);

		Salida salida = salidaService.generar(vehiculo.getPlaca(), FECHA_SALIDA);
		assertNotNull(salida);

	}

	@Test
	public void calcularSalidaMotoTest() {
		ValorTarifaService valorTarifa = new ValorTarifaService();
		Vehiculo vehiculo = new MotoTestDataBuilder().build();
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		SalidaRepository salidaRepository = mock(SalidaRepository.class);
		when(entradaRepository.guardar(Mockito.any(Entrada.class)))
				.thenReturn(new EntradaTestDataBuilder().conVehiculo(vehiculo).conActivo(Boolean.TRUE).build());
		EntradaService entradaService = new EntradaService(entradaRepository);
		Entrada entrada = entradaService.registrarIngreso(vehiculo, FECHA_ENTRADA);
		assertNotNull(entrada);

		when(entradaRepository.consultarPorId(entrada.getIdEntrada())).thenReturn(entrada);
		when(salidaRepository.guardar(Mockito.any(Salida.class))).thenReturn(new Salida());
		when(entradaRepository.listarActivasPorVehiculo(vehiculo.getPlaca()))
				.thenReturn(Arrays.asList(new EntradaTestDataBuilder().conVehiculo(vehiculo).build()));

		SalidaService salidaService = new SalidaService(entradaRepository, salidaRepository, valorTarifa);

		Salida salida = salidaService.generar(vehiculo.getPlaca(), FECHA_SALIDA);
		assertNotNull(salida);

	}
	
	@Test
	public void capturarExcepcionSalida() {
		ValorTarifaService valorTarifa = new ValorTarifaService();
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		when(entradaRepository.listarActivasPorVehiculo(vehiculo.getPlaca()))
		.thenReturn(Arrays.asList());
		SalidaRepository salidaRepository = mock(SalidaRepository.class);
		SalidaService salidaService = new SalidaService(entradaRepository, salidaRepository, valorTarifa);
		try {
			salidaService.generar(vehiculo.getPlaca(), LocalDateTime.now());
			fail();
		}catch (SalidaException e) {
			assertEquals(e.getMessage(), SalidaService.MSG_ENTRADA_NO_ENCONTRADA);
		}
		
	}

}
