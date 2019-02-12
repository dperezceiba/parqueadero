package com.co.ceiba.establecimiento.servicio.regla;

import java.math.BigDecimal;
import java.util.List;

import com.co.ceiba.establecimiento.constante.ModalidadTarifa;
import com.co.ceiba.establecimiento.dominio.Tarifa;
import com.co.ceiba.establecimiento.dominio.Vehiculo;

public class ControlSalida {

	private Vehiculo vehiculo;
	private List<Tarifa> tarifas;

	public ControlSalida(Vehiculo vehiculo, List<Tarifa> tarifas) {
		this.vehiculo = vehiculo;
		this.tarifas = tarifas;
	}

	public Tarifa getTarifaPorModalidad(ModalidadTarifa modalidadTarifa) {
		return tarifas.stream().filter(obj -> ModalidadTarifa.valueOf(obj.getModalidad()) == modalidadTarifa)
				.findFirst().orElse(new Tarifa(BigDecimal.ZERO.doubleValue()));
	}

	public Double totalPagarSalida(Integer cantidadHoras) {
		DetalleSalida detalleSalida = ReglaSalidaSingleton.getInstance().calcularDetalleSalida(vehiculo, cantidadHoras);
		Tarifa tarifaDia = getTarifaPorModalidad(ModalidadTarifa.DIA);
		Tarifa tarifaHora = getTarifaPorModalidad(ModalidadTarifa.HORA);

		Double total = tarifaDia.getValor() * detalleSalida.getDias();
		total += tarifaHora.getValor() * detalleSalida.getHoras();
		total += detalleSalida.getExcedente();
		return total;
	}

}
