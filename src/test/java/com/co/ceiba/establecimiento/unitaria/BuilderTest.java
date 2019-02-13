package com.co.ceiba.establecimiento.unitaria;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.builder.CarroBuilder;
import com.co.ceiba.establecimiento.builder.MotoBuilder;
import com.co.ceiba.establecimiento.builder.SalidaBuilder;
import com.co.ceiba.establecimiento.builder.TarifaBuilder;
import com.co.ceiba.establecimiento.entidad.CarroEntity;
import com.co.ceiba.establecimiento.entidad.MotoEntity;
import com.co.ceiba.establecimiento.entidad.SalidaEntity;
import com.co.ceiba.establecimiento.entidad.TarifaEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BuilderTest {

	@Test
	public void testBuilder() {
		MotoEntity motoEntity = null;
		CarroEntity carroEntity = null;
		SalidaEntity salidaEntity = null;
		TarifaEntity tarifaEntity = null;
		
		assertNull(MotoBuilder.convertirADominio(motoEntity));
		assertNull(CarroBuilder.convertirADominio(carroEntity));
		assertNull(SalidaBuilder.convertirADominio(salidaEntity));
		assertNull(TarifaBuilder.convertirADominio(tarifaEntity));
		
		
	}

}
