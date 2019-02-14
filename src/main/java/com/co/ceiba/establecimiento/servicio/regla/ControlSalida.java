package com.co.ceiba.establecimiento.servicio.regla;

import com.co.ceiba.establecimiento.constante.ModalidadTarifa;
import com.co.ceiba.establecimiento.constante.ValorTarifa;
import com.co.ceiba.establecimiento.dominio.Tarifa;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.util.TipoUtils;

public class ControlSalida {

	private Vehiculo vehiculo;

	public ControlSalida(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Double totalPagarSalida(Integer cantidadHoras) {
		DetalleSalida detalleSalida = ReglaSalidaSingleton.getInstance().calcularDetalleSalida(vehiculo, cantidadHoras);
		Tarifa tarifaDia = ValorTarifa.getInstance().getTarifaModalidadPorTipo(TipoUtils.getTipoVehiculo(vehiculo),
				ModalidadTarifa.DIA);
		Tarifa tarifaHora = ValorTarifa.getInstance().getTarifaModalidadPorTipo(TipoUtils.getTipoVehiculo(vehiculo),
				ModalidadTarifa.HORA);

		Double total = tarifaDia.getValor() * detalleSalida.getDias();
		total += tarifaHora.getValor() * detalleSalida.getHoras();
		total += detalleSalida.getExcedente();
		return total;
	}

}
