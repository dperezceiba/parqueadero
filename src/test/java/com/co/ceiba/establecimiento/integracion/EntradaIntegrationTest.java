package com.co.ceiba.establecimiento.integracion;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.CeibaEstablecimientoApplication;
import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.dominio.ErrorResponse;
import com.co.ceiba.establecimiento.dominio.Moto;
import com.co.ceiba.establecimiento.servicio.EntradaService;
import com.co.ceiba.establecimiento.testdatabuilder.CarroTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.MotoTestDataBuilder;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CeibaEstablecimientoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EntradaIntegrationTest {

	private static final String LOCALHOST_URL = "http://localhost:";

	private static final String URL_INGRESO_CARRO = "/entrada/carro";
	private static final String URL_INGRESO_MOTO = "/entrada/moto";

	private Logger logger = LoggerFactory.getLogger(getClass());

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
		Moto moto = new MotoTestDataBuilder().build();
		HttpEntity<Moto> entity = new HttpEntity<>(moto, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(URL_INGRESO_MOTO), HttpMethod.POST,
				entity, String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		ErrorResponse error = objectMapper.readValue(response.getBody(), ErrorResponse.class);
		assertEquals(error.getMensaje(), EntradaService.MSG_INGRESO_EXISTENTE);
	}

	private String createURLWithPort(String uri) {
		return LOCALHOST_URL + port + uri;
	}

}
