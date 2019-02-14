package com.co.ceiba.establecimiento.unitaria;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.builder.EntradaBuilder;
import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.Salida;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.entidad.SalidaEntity;
import com.co.ceiba.establecimiento.repositorio.CarroRepository;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;
import com.co.ceiba.establecimiento.repositorio.MotoRepository;
import com.co.ceiba.establecimiento.repositorio.SalidaRepository;
import com.co.ceiba.establecimiento.servicio.EntradaService;
import com.co.ceiba.establecimiento.servicio.SalidaService;
import com.co.ceiba.establecimiento.testdatabuilder.CarroTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.MotoTestDataBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SalidaTest {

	private static final LocalDateTime FECHA_ENTRADA = LocalDateTime.of(2019, 2, 12, 8, 2);
	private static final LocalDateTime FECHA_SALIDA = LocalDateTime.of(2019, 2, 12, 9, 2);

	@Test
	public void calcularSalidaCarroTest() {
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		SalidaRepository salidaRepository = mock(SalidaRepository.class);

		CarroRepository carroRepository = mock(CarroRepository.class);
		MotoRepository motoRepository = mock(MotoRepository.class);
		EntradaService entradaService = new EntradaService(entradaRepository, carroRepository, motoRepository);

		Entrada entrada = entradaService.registrarIngreso(vehiculo, FECHA_ENTRADA);
		assertNotNull(entrada);

		when(entradaRepository.findById(entrada.getIdEntrada()))
				.thenReturn(Optional.of(EntradaBuilder.convertirAEntity(entrada)));

		when(salidaRepository.save(Mockito.any(SalidaEntity.class))).thenReturn(new SalidaEntity());

		SalidaService salidaService = new SalidaService(entradaRepository, salidaRepository);

		Salida salida = salidaService.generarSalida(entrada, FECHA_SALIDA);
		assertNotNull(salida);

	}

	@Test
	public void calcularSalidaMotoTest() {
		Vehiculo vehiculo = new MotoTestDataBuilder().build();
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		SalidaRepository salidaRepository = mock(SalidaRepository.class);
		CarroRepository carroRepository = mock(CarroRepository.class);
		MotoRepository motoRepository = mock(MotoRepository.class);

		EntradaService entradaService = new EntradaService(entradaRepository, carroRepository, motoRepository);

		Entrada entrada = entradaService.registrarIngreso(vehiculo, FECHA_ENTRADA);
		assertNotNull(entrada);

		when(entradaRepository.findById(entrada.getIdEntrada()))
				.thenReturn(Optional.of(EntradaBuilder.convertirAEntity(entrada)));

		when(salidaRepository.save(Mockito.any(SalidaEntity.class))).thenReturn(new SalidaEntity());

		SalidaService salidaService = new SalidaService(entradaRepository, salidaRepository);

		Salida salida = salidaService.generarSalida(entrada, FECHA_SALIDA);
		assertNotNull(salida);

	}

}
