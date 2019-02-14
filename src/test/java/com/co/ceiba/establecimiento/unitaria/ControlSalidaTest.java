package com.co.ceiba.establecimiento.unitaria;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.establecimiento.constante.ModalidadTarifa;
import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.constante.ValorTarifa;
import com.co.ceiba.establecimiento.dominio.Tarifa;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.servicio.regla.ControlSalida;
import com.co.ceiba.establecimiento.servicio.regla.ReglaSalidaSingleton;
import com.co.ceiba.establecimiento.testdatabuilder.CarroTestDataBuilder;
import com.co.ceiba.establecimiento.testdatabuilder.MotoTestDataBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ControlSalidaTest {

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
		ControlSalida controlSalida = new ControlSalida(vehiculo);
		Double total = controlSalida.totalPagarSalida(SIETE_HORAS);
		Tarifa tarifaHora = ValorTarifa.getInstance().getTarifaModalidadPorTipo(TipoVehiculo.CARRO,
				ModalidadTarifa.HORA);
		Double esperado = tarifaHora.getValor() * SIETE_HORAS;
		assertEquals(esperado, total);
	}

	@Test
	public void controlarSalidaMotoHoraNormal() {
		Vehiculo vehiculo = new MotoTestDataBuilder().conCilindraje(CILINDRAJE_MENOR_500).build();
		ControlSalida controlSalida = new ControlSalida(vehiculo);
		Double total = controlSalida.totalPagarSalida(SIETE_HORAS);
		Tarifa tarifaHora = ValorTarifa.getInstance().getTarifaModalidadPorTipo(TipoVehiculo.MOTO,
				ModalidadTarifa.HORA);
		Double esperado = tarifaHora.getValor() * SIETE_HORAS;
		assertEquals(esperado, total);
	}

	@Test
	public void controlarSalidaMotoHoraClindrajeMayor() {
		Vehiculo vehiculo = new MotoTestDataBuilder().conCilindraje(CILINDRAJE_MAYOR_500).build();
		ControlSalida controlSalida = new ControlSalida(vehiculo);
		Double total = controlSalida.totalPagarSalida(SIETE_HORAS);
		Tarifa tarifaHora = ValorTarifa.getInstance().getTarifaModalidadPorTipo(TipoVehiculo.MOTO,
				ModalidadTarifa.HORA);
		Double esperado = tarifaHora.getValor() * SIETE_HORAS + ReglaSalidaSingleton.VALOR_EXCEDENTE;
		assertEquals(esperado, total);
	}

	@Test
	public void controlarSalidaCarroDiaHoras() {
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		ControlSalida controlSalida = new ControlSalida(vehiculo);
		Double total = controlSalida.totalPagarSalida(VEINTIOCHO_HORAS);
		Tarifa tarifaHora = ValorTarifa.getInstance().getTarifaModalidadPorTipo(TipoVehiculo.CARRO,
				ModalidadTarifa.HORA);
		Tarifa tarifaDia = ValorTarifa.getInstance().getTarifaModalidadPorTipo(TipoVehiculo.CARRO, ModalidadTarifa.DIA);
		Double esperado = tarifaDia.getValor() + tarifaHora.getValor() * (VEINTIOCHO_HORAS - VEINTICUATRO_HORAS);
		assertEquals(esperado, total);
	}

	@Test
	public void controlarSalidaCarroDiaHorasDias() {
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		ControlSalida controlSalida = new ControlSalida(vehiculo);
		Double total = controlSalida.totalPagarSalida(TREINTAYCUATRO_HORAS);
		Tarifa tarifaDia = ValorTarifa.getInstance().getTarifaModalidadPorTipo(TipoVehiculo.CARRO, ModalidadTarifa.DIA);
		Double esperado = tarifaDia.getValor() + tarifaDia.getValor();
		assertEquals(esperado, total);
	}

	@Test
	public void controlarSalidaCarroHoraMayor9() {
		Vehiculo vehiculo = new CarroTestDataBuilder().build();
		ControlSalida controlSalida = new ControlSalida(vehiculo);
		Double total = controlSalida.totalPagarSalida(DIEZ_HORAS);
		Tarifa tarifaDia = ValorTarifa.getInstance().getTarifaModalidadPorTipo(TipoVehiculo.CARRO, ModalidadTarifa.DIA);
		Double esperado = tarifaDia.getValor();
		assertEquals(esperado, total);
	}

	@Test
	public void controlarSalidaMotoDiaHorasNormal() {
		Vehiculo vehiculo = new MotoTestDataBuilder().conCilindraje(CILINDRAJE_MENOR_500).build();
		ControlSalida controlSalida = new ControlSalida(vehiculo);
		Double total = controlSalida.totalPagarSalida(VEINTIOCHO_HORAS);
		Tarifa tarifaHora = ValorTarifa.getInstance().getTarifaModalidadPorTipo(TipoVehiculo.MOTO,
				ModalidadTarifa.HORA);
		Tarifa tarifaDia = ValorTarifa.getInstance().getTarifaModalidadPorTipo(TipoVehiculo.MOTO, ModalidadTarifa.DIA);
		Double esperado = tarifaDia.getValor() + tarifaHora.getValor() * (VEINTIOCHO_HORAS - VEINTICUATRO_HORAS);
		assertEquals(esperado, total);
	}

	@Test
	public void controlarSalidaMotoDiaHorasCindrajeMayor() {
		Vehiculo vehiculo = new MotoTestDataBuilder().conCilindraje(CILINDRAJE_MAYOR_500).build();
		ControlSalida controlSalida = new ControlSalida(vehiculo);
		Double total = controlSalida.totalPagarSalida(VEINTIOCHO_HORAS);
		Tarifa tarifaHora = ValorTarifa.getInstance().getTarifaModalidadPorTipo(TipoVehiculo.MOTO,
				ModalidadTarifa.HORA);
		Tarifa tarifaDia = ValorTarifa.getInstance().getTarifaModalidadPorTipo(TipoVehiculo.MOTO, ModalidadTarifa.DIA);
		Double esperado = tarifaDia.getValor() + tarifaHora.getValor() * (VEINTIOCHO_HORAS - VEINTICUATRO_HORAS)
				+ ReglaSalidaSingleton.VALOR_EXCEDENTE;
		assertEquals(esperado, total);
	}

}
