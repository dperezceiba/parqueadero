package com.co.ceiba.establecimiento.unitaria;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.dominio.Moto;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.testdatabuilder.CarroTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.MotoTestDataBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehiculoTest {

	private static final String PLACA = "FDR568";
	private static final String MODELO = "2017";
	private static final Double CILINDRAJE = 550.0;

	@Test
	public void crearVehiculoCarro() {
		Vehiculo vehiculo = new CarroTestDataBuilder().conPlaca(PLACA).conModelo(MODELO).build();

		assertEquals(PLACA, vehiculo.getPlaca());
		assertEquals(MODELO, ((Carro) vehiculo).getModelo());

	}

	@Test
	public void crearVehiculoMoto() {
		Vehiculo vehiculo = new MotoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE).build();

		assertEquals(PLACA, vehiculo.getPlaca());
		assertEquals(CILINDRAJE, ((Moto) vehiculo).getCilindraje());
	}

}
