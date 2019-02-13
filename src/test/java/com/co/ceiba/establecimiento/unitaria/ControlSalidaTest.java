package com.co.ceiba.establecimiento.unitaria;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.constante.ModalidadTarifa;
import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Tarifa;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.servicio.regla.ControlSalida;
import com.co.ceiba.establecimiento.servicio.regla.ReglaSalidaSingleton;
import com.co.ceiba.establecimiento.testdatabuilder.CarroTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.MotoTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.TarifaTestDataBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ControlSalidaTest {

	private static final Double VALOR_HORA_CARRO = 1000.0;
	private static final Double VALOR_HORA_MOTO = 500.0;
	private static final Double VALOR_DIA_CARRO = 8000.0;
	private static final Double VALOR_DIA_MOTO = 4000.0;

	private static final Integer VEINTIOCHO_HORAS = 28;
	private static final Integer TREINTAYCUATRO_HORAS = 34;
	private static final Integer SIETE_HORAS = 7;
	private static final Integer DIEZ_HORAS = 10;

	private static final Integer VEINTICUATRO_HORAS = 24;

	private static final Double CILINDRAJE_MENOR_500 = 400.0;
	private static final Double CILINDRAJE_MAYOR_500 = 600.0;

	@Test
	public void controlarSalidaCarroHora() {
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		List<Tarifa> tarifas = new ArrayList<>();
		tarifas.add(new TarifaTestDataBuilder().conModalidad(ModalidadTarifa.DIA.toString())
				.conTipoVehiculo(TipoVehiculo.CARRO.toString()).conValor(VALOR_DIA_CARRO).build());
		tarifas.add(new TarifaTestDataBuilder().conModalidad(ModalidadTarifa.HORA.toString())
				.conTipoVehiculo(TipoVehiculo.CARRO.toString()).conValor(VALOR_HORA_CARRO).build());

		ControlSalida controlSalida = new ControlSalida(vehiculo, tarifas);
		Double total = controlSalida.totalPagarSalida(SIETE_HORAS);
		Tarifa tarifaHora = controlSalida.getTarifaPorModalidad(ModalidadTarifa.HORA);
		Double esperado = tarifaHora.getValor() * SIETE_HORAS;
		assertEquals(esperado, total);
	}

	@Test
	public void controlarSalidaMotoHoraNormal() {
		Vehiculo vehiculo = new MotoTestDataBuilder().conCilindraje(CILINDRAJE_MENOR_500).build();
		List<Tarifa> tarifas = new ArrayList<>();
		tarifas.add(new TarifaTestDataBuilder().conModalidad(ModalidadTarifa.DIA.toString())
				.conTipoVehiculo(TipoVehiculo.MOTO.toString()).conValor(VALOR_DIA_MOTO).build());
		tarifas.add(new TarifaTestDataBuilder().conModalidad(ModalidadTarifa.HORA.toString())
				.conTipoVehiculo(TipoVehiculo.MOTO.toString()).conValor(VALOR_HORA_MOTO).build());

		ControlSalida controlSalida = new ControlSalida(vehiculo, tarifas);
		Double total = controlSalida.totalPagarSalida(SIETE_HORAS);
		Tarifa tarifaHora = controlSalida.getTarifaPorModalidad(ModalidadTarifa.HORA);
		Double esperado = tarifaHora.getValor() * SIETE_HORAS;
		assertEquals(esperado, total);
	}

	@Test
	public void controlarSalidaMotoHoraClindrajeMayor() {
		Vehiculo vehiculo = new MotoTestDataBuilder().conCilindraje(CILINDRAJE_MAYOR_500).build();
		List<Tarifa> tarifas = new ArrayList<>();
		tarifas.add(new TarifaTestDataBuilder().conModalidad(ModalidadTarifa.DIA.toString())
				.conTipoVehiculo(TipoVehiculo.MOTO.toString()).conValor(VALOR_DIA_MOTO).build());
		tarifas.add(new TarifaTestDataBuilder().conModalidad(ModalidadTarifa.HORA.toString())
				.conTipoVehiculo(TipoVehiculo.MOTO.toString()).conValor(VALOR_HORA_MOTO).build());

		ControlSalida controlSalida = new ControlSalida(vehiculo, tarifas);
		Double total = controlSalida.totalPagarSalida(SIETE_HORAS);
		Tarifa tarifaHora = controlSalida.getTarifaPorModalidad(ModalidadTarifa.HORA);
		Double esperado = tarifaHora.getValor() * SIETE_HORAS + ReglaSalidaSingleton.VALOR_EXCEDENTE;
		assertEquals(esperado, total);
	}

	@Test
	public void controlarSalidaCarroDiaHoras() {
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		List<Tarifa> tarifas = new ArrayList<>();
		tarifas.add(new TarifaTestDataBuilder().conModalidad(ModalidadTarifa.DIA.toString())
				.conTipoVehiculo(TipoVehiculo.CARRO.toString()).conValor(VALOR_DIA_CARRO).build());
		tarifas.add(new TarifaTestDataBuilder().conModalidad(ModalidadTarifa.HORA.toString())
				.conTipoVehiculo(TipoVehiculo.CARRO.toString()).conValor(VALOR_HORA_CARRO).build());

		ControlSalida controlSalida = new ControlSalida(vehiculo, tarifas);
		Double total = controlSalida.totalPagarSalida(VEINTIOCHO_HORAS);
		Tarifa tarifaHora = controlSalida.getTarifaPorModalidad(ModalidadTarifa.HORA);
		Tarifa tarifaDia = controlSalida.getTarifaPorModalidad(ModalidadTarifa.DIA);
		Double esperado = tarifaDia.getValor() + tarifaHora.getValor() * (VEINTIOCHO_HORAS - VEINTICUATRO_HORAS);
		assertEquals(esperado, total);
	}

	@Test
	public void controlarSalidaCarroDiaHorasDias() {
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		List<Tarifa> tarifas = new ArrayList<>();
		tarifas.add(new TarifaTestDataBuilder().conModalidad(ModalidadTarifa.DIA.toString())
				.conTipoVehiculo(TipoVehiculo.CARRO.toString()).conValor(VALOR_DIA_CARRO).build());
		tarifas.add(new TarifaTestDataBuilder().conModalidad(ModalidadTarifa.HORA.toString())
				.conTipoVehiculo(TipoVehiculo.CARRO.toString()).conValor(VALOR_HORA_CARRO).build());

		ControlSalida controlSalida = new ControlSalida(vehiculo, tarifas);
		Double total = controlSalida.totalPagarSalida(TREINTAYCUATRO_HORAS);
		Tarifa tarifaDia = controlSalida.getTarifaPorModalidad(ModalidadTarifa.DIA);
		Double esperado = tarifaDia.getValor() + tarifaDia.getValor();
		assertEquals(esperado, total);
	}

	@Test
	public void controlarSalidaCarroHoraMayor9() {
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		List<Tarifa> tarifas = new ArrayList<>();
		tarifas.add(new TarifaTestDataBuilder().conModalidad(ModalidadTarifa.DIA.toString())
				.conTipoVehiculo(TipoVehiculo.CARRO.toString()).conValor(VALOR_DIA_CARRO).build());
		tarifas.add(new TarifaTestDataBuilder().conModalidad(ModalidadTarifa.HORA.toString())
				.conTipoVehiculo(TipoVehiculo.CARRO.toString()).conValor(VALOR_HORA_CARRO).build());

		ControlSalida controlSalida = new ControlSalida(vehiculo, tarifas);
		Double total = controlSalida.totalPagarSalida(DIEZ_HORAS);
		Tarifa tarifaDia = controlSalida.getTarifaPorModalidad(ModalidadTarifa.DIA);
		Double esperado = tarifaDia.getValor();
		assertEquals(esperado, total);
	}

	@Test
	public void controlarSalidaMotoDiaHorasNormal() {
		Vehiculo vehiculo = new MotoTestDataBuilder().conCilindraje(CILINDRAJE_MENOR_500).build();
		List<Tarifa> tarifas = new ArrayList<>();
		tarifas.add(new TarifaTestDataBuilder().conModalidad(ModalidadTarifa.DIA.toString())
				.conTipoVehiculo(TipoVehiculo.MOTO.toString()).conValor(VALOR_DIA_MOTO).build());
		tarifas.add(new TarifaTestDataBuilder().conModalidad(ModalidadTarifa.HORA.toString())
				.conTipoVehiculo(TipoVehiculo.MOTO.toString()).conValor(VALOR_HORA_MOTO).build());

		ControlSalida controlSalida = new ControlSalida(vehiculo, tarifas);
		Double total = controlSalida.totalPagarSalida(VEINTIOCHO_HORAS);
		Tarifa tarifaHora = controlSalida.getTarifaPorModalidad(ModalidadTarifa.HORA);
		Tarifa tarifaDia = controlSalida.getTarifaPorModalidad(ModalidadTarifa.DIA);
		Double esperado = tarifaDia.getValor() + tarifaHora.getValor() * (VEINTIOCHO_HORAS - VEINTICUATRO_HORAS);
		assertEquals(esperado, total);
	}

	@Test
	public void controlarSalidaMotoDiaHorasCindrajeMayor() {
		Vehiculo vehiculo = new MotoTestDataBuilder().conCilindraje(CILINDRAJE_MAYOR_500).build();
		List<Tarifa> tarifas = new ArrayList<>();
		tarifas.add(new TarifaTestDataBuilder().conModalidad(ModalidadTarifa.DIA.toString())
				.conTipoVehiculo(TipoVehiculo.MOTO.toString()).conValor(VALOR_DIA_MOTO).build());
		tarifas.add(new TarifaTestDataBuilder().conModalidad(ModalidadTarifa.HORA.toString())
				.conTipoVehiculo(TipoVehiculo.MOTO.toString()).conValor(VALOR_HORA_MOTO).build());

		ControlSalida controlSalida = new ControlSalida(vehiculo, tarifas);
		Double total = controlSalida.totalPagarSalida(VEINTIOCHO_HORAS);
		Tarifa tarifaHora = controlSalida.getTarifaPorModalidad(ModalidadTarifa.HORA);
		Tarifa tarifaDia = controlSalida.getTarifaPorModalidad(ModalidadTarifa.DIA);
		Double esperado = tarifaDia.getValor() + tarifaHora.getValor() * (VEINTIOCHO_HORAS - VEINTICUATRO_HORAS)
				+ ReglaSalidaSingleton.VALOR_EXCEDENTE;
		assertEquals(esperado, total);
	}

}