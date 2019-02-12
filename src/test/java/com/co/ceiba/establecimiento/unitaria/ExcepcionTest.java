package com.co.ceiba.establecimiento.unitaria;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.dominio.excepcion.EntradaException;
import com.co.ceiba.establecimiento.dominio.excepcion.SalidaException;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;
import com.co.ceiba.establecimiento.repositorio.SalidaRepository;
import com.co.ceiba.establecimiento.repositorio.TarifaRepository;
import com.co.ceiba.establecimiento.servicio.EntradaService;
import com.co.ceiba.establecimiento.servicio.SalidaService;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntradaCarro;
import com.co.ceiba.establecimiento.testdatabuilder.CarroTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.EntradaTestDataBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExcepcionTest {

	private static final LocalDateTime FECHA_PRUEBA = LocalDateTime.of(2019, 2, 12, 5, 20);

	private static final Long ID_ENTRADA = 5L;

	@Test(expected = EntradaException.class)
	public void capturarExcepcionEntrada() {
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		when(entradaRepository.cantidadEntradasActivas(TipoVehiculo.CARRO.toString()))
				.thenReturn(ControlEntradaCarro.MAXIMO_CARRO);
		EntradaService entradaService = new EntradaService(entradaRepository);
		entradaService.registrarIngreso(vehiculo, FECHA_PRUEBA);
	}

	@Test(expected = SalidaException.class)
	public void capturarExcepcionSalida() {
		Vehiculo vehiculo = new CarroTestDataBuilder().build();

		EntradaRepository entradaRepository = mock(EntradaRepository.class);
		TarifaRepository tarifaRepository = mock(TarifaRepository.class);
		SalidaRepository salidaRepository = mock(SalidaRepository.class);
		SalidaService salidaService = new SalidaService(entradaRepository, tarifaRepository, salidaRepository);
		Entrada entrada = new EntradaTestDataBuilder().conId(ID_ENTRADA).conActivo(Boolean.FALSE).conVehiculo(vehiculo)
				.build();
		salidaService.generarSalida(entrada, LocalDateTime.now());
	}

}
