package com.co.ceiba.establecimiento.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.ErrorResponse;
import com.co.ceiba.establecimiento.dominio.Moto;
import com.co.ceiba.establecimiento.servicio.regla.ControlEntrada;
import com.co.ceiba.establecimiento.testdatabuilder.CarroTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.MotoTestDataBuilder;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EntradaIntegrationTest {

	private static final String PLACA_DUPLICADA = "RIO036";

	private static final String PLACA_PRUEBA1 = "APPZ001";
	private static final String PLACA_PRUEBA2 = "APPZ002";

	private static final String LOCALHOST_URL = "http://localhost:";

	private static final String URL_INGRESO_CARRO = "/entrada/v1/carro";
	private static final String URL_INGRESO_MOTO = "/entrada/v1/moto";

	private static final String URL_LISTADO_ENTRADAS = "/entrada/v1/activas";

	private static final String URL_LISTADO_ENTRADAS_V2 = "/entrada/v2/activas";

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void registrarIngresoCarro() {
		Carro carro = new CarroTestDataBuilder().build();
		HttpEntity<Carro> entity = new HttpEntity<>(carro, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(URL_INGRESO_CARRO), HttpMethod.POST,
				entity, String.class);
		HttpStatus codigo = response.getStatusCode();
		assertEquals(HttpStatus.OK, codigo);

	}

	@Test
	public void registrarIngresoMoto() {
		Moto moto = new MotoTestDataBuilder().build();
		HttpEntity<Moto> entity = new HttpEntity<>(moto, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(URL_INGRESO_MOTO), HttpMethod.POST,
				entity, String.class);
		HttpStatus codigo = response.getStatusCode();
		assertEquals(HttpStatus.OK, codigo);
	}

	@Test
	public void registrarIngresoMotoDuplicado() throws JsonParseException, JsonMappingException, IOException {
		Moto moto = new MotoTestDataBuilder().conPlaca(PLACA_DUPLICADA).build();
		HttpEntity<Moto> entity = new HttpEntity<>(moto, headers);
		restTemplate.exchange(createURLWithPort(URL_INGRESO_MOTO), HttpMethod.POST, entity, String.class);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(URL_INGRESO_MOTO), HttpMethod.POST,
				entity, String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		ErrorResponse error = objectMapper.readValue(response.getBody(), ErrorResponse.class);
		assertEquals(error.getMensaje(), ControlEntrada.MSG_INGRESO_EXISTENTE);
	}

	@Test
	public void listarIngresosRegistrados() {
		Moto moto = new MotoTestDataBuilder().conPlaca(PLACA_PRUEBA1).build();
		HttpEntity<Moto> entity = new HttpEntity<>(moto, headers);
		restTemplate.exchange(createURLWithPort(URL_INGRESO_MOTO), HttpMethod.POST, entity, String.class);
		Carro carro = new CarroTestDataBuilder().conPlaca(PLACA_PRUEBA2).build();
		HttpEntity<Carro> entityCarro = new HttpEntity<>(carro, headers);
		restTemplate.exchange(createURLWithPort(URL_INGRESO_CARRO), HttpMethod.POST, entityCarro, String.class);

		ResponseEntity<List<Entrada>> response = restTemplate.exchange(createURLWithPort(URL_LISTADO_ENTRADAS),
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Entrada>>() {
				});
		List<Entrada> listado = response.getBody();
		assertFalse(listado.isEmpty());
	}

	@Test(expected = Exception.class)
	public void testOtraExcepcion() {
		restTemplate.exchange(createURLWithPort(URL_LISTADO_ENTRADAS_V2), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Entrada>>() {
				});
	}

	private String createURLWithPort(String uri) {
		return LOCALHOST_URL + port + uri;
	}

}
