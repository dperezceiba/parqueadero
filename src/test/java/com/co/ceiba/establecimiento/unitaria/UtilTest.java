package com.co.ceiba.establecimiento.unitaria;

import static org.junit.Assert.assertNull;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.util.FechaUtils;

@RunWith(SpringRunner.class)
public class UtilTest {

	@Test
	public void probarFechas() {
		LocalDateTime fecha = null;
		assertNull(FechaUtils.convertir(fecha));
	}

}
