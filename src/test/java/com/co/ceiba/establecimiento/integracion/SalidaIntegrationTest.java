package com.co.ceiba.establecimiento.integracion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.ErrorResponse;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.servicio.SalidaService;
import com.co.ceiba.establecimiento.testdatabuilder.CarroTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalidaIntegrationTest {

	private static final String PLACA_PRUEBA5 = "TPPZ005";
	private static final String PLACA_PRUEBA6 = "TPPZ006";
	private static final String LOCALHOST_URL = "http://localhost:";

	private static final String URL_SALIDA_VEHICULO = "/salida/v1/registrar";
	private static final String URL_INGRESO_CARRO = "/entrada/v1/carro";

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void registrarSalidaVehiculo() {
		Vehiculo vehiculo = new CarroTestDataBuilder().conPlaca(PLACA_PRUEBA5).build();
		HttpEntity<Vehiculo> vehiculoDominio = new HttpEntity<>(vehiculo, headers);
		ResponseEntity<Entrada> response = restTemplate.exchange(createURLWithPort(URL_INGRESO_CARRO), HttpMethod.POST,
				vehiculoDominio, Entrada.class);
		Entrada entrada = response.getBody();

		HttpEntity<Entrada> entity = new HttpEntity<>(entrada, headers);
		ResponseEntity<String> responseSalida = restTemplate.exchange(createURLWithPort(URL_SALIDA_VEHICULO),
				HttpMethod.POST, entity, String.class);
		HttpStatus codigo = responseSalida.getStatusCode();
		assertEquals(HttpStatus.OK, codigo);
	}

	@Test
	public void registrarSalidaConEntradaInactiva() {
		Vehiculo vehiculo = new CarroTestDataBuilder().conPlaca(PLACA_PRUEBA6).build();
		HttpEntity<Vehiculo> vehiculoDominio = new HttpEntity<>(vehiculo, headers);
		ResponseEntity<Entrada> response = restTemplate.exchange(createURLWithPort(URL_INGRESO_CARRO), HttpMethod.POST,
				vehiculoDominio, Entrada.class);
		Entrada entrada = response.getBody();

		HttpEntity<Entrada> entity = new HttpEntity<>(entrada, headers);
		restTemplate.exchange(createURLWithPort(URL_SALIDA_VEHICULO), HttpMethod.POST, entity, String.class);

		ResponseEntity<ErrorResponse> responseSalida = restTemplate.exchange(createURLWithPort(URL_SALIDA_VEHICULO),
				HttpMethod.POST, entity, ErrorResponse.class);
		ErrorResponse error = responseSalida.getBody();
		assertEquals(error.getMensaje(), SalidaService.MSG_ENTRADA_INACTIVA);
	}

	private String createURLWithPort(String uri) {
		return LOCALHOST_URL + port + uri;
	}

}
