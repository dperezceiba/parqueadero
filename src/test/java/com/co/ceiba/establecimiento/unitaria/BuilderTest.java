package com.co.ceiba.establecimiento.unitaria;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.builder.MotoBuilder;
import com.co.ceiba.establecimiento.entidad.MotoEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BuilderTest {

	@Test
	public void testMotoBuilder() {
		MotoEntity motoEntity = null;
		assertNull(MotoBuilder.convertirADominio(motoEntity));
	}

}
