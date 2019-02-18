package com.co.ceiba.establecimiento.servicio.regla;

import com.co.ceiba.establecimiento.dominio.ModalidadTarifa;
import com.co.ceiba.establecimiento.dominio.Tarifa;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.servicio.ValorTarifaService;

public class ControlSalida {

	private Vehiculo vehiculo;
	private ValorTarifaService valorTarifaService;

	public ControlSalida(Vehiculo vehiculo, ValorTarifaService valorTarifa) {
		this.vehiculo = vehiculo;
		this.valorTarifaService = valorTarifa;
	}

	public Double totalPagarSalida(Integer cantidadHoras) {
		DetalleSalida detalleSalida = new ReglaSalida(vehiculo, cantidadHoras).calcularDetalleSalida();
		Tarifa tarifaDia = valorTarifaService.getTarifaModalidadPorTipo(vehiculo.getTipoVehiculo(), ModalidadTarifa.DIA);
		Tarifa tarifaHora = valorTarifaService.getTarifaModalidadPorTipo(vehiculo.getTipoVehiculo(), ModalidadTarifa.HORA);

		Double total = tarifaDia.getValor() * detalleSalida.getDias();
		total += tarifaHora.getValor() * detalleSalida.getHoras();
		total += detalleSalida.getExcedente();
		return total;
	}

}
