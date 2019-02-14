package com.co.ceiba.establecimiento.unitaria;

import static org.junit.Assert.assertNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.builder.CarroBuilder;
import com.co.ceiba.establecimiento.builder.EntradaBuilder;
import com.co.ceiba.establecimiento.builder.MotoBuilder;
import com.co.ceiba.establecimiento.builder.SalidaBuilder;
import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.Moto;
import com.co.ceiba.establecimiento.dominio.Salida;
import com.co.ceiba.establecimiento.entidad.CarroEntity;
import com.co.ceiba.establecimiento.entidad.EntradaEntity;
import com.co.ceiba.establecimiento.entidad.MotoEntity;
import com.co.ceiba.establecimiento.entidad.SalidaEntity;
import com.co.ceiba.establecimiento.util.FechaUtils;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UtilidadesTest {

	@Test
	public void testBuilder() {
		MotoEntity motoEntity = null;
		CarroEntity carroEntity = null;
		SalidaEntity salidaEntity = null;
		Timestamp timestamp = null;
		EntradaEntity entradaEntity = null;
		Entrada entrada = null;
		Salida salida = null;
		Moto moto = null;
		Carro carro = null;

		assertNull(MotoBuilder.convertirADominio(motoEntity));
		assertNull(CarroBuilder.convertirADominio(carroEntity));
		assertNull(SalidaBuilder.convertirADominio(salidaEntity));
		assertNull(FechaUtils.convertir(timestamp));
		assertNull(EntradaBuilder.convertirADominio(entradaEntity));
		assertNull(EntradaBuilder.convertirAEntity(entrada));
		assertNull(SalidaBuilder.convertirAEntity(salida));
		assertNull(MotoBuilder.convertirAEntity(moto));
		assertNull(CarroBuilder.convertirAEntity(carro));
	}
	
	@Test
	public void probarFechas() {
		LocalDateTime fecha = null;
		assertNull(FechaUtils.convertir(fecha));
	}

}
