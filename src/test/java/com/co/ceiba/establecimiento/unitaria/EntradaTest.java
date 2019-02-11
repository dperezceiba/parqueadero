package com.co.ceiba.establecimiento.unitaria;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntradaTest {

	private static final Date FECHA_INGRESO = new Date();

	@Test
	public void validarEntradaHayDisponibilidad() {
		
	}

}
