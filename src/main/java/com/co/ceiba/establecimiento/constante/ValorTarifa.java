package com.co.ceiba.establecimiento.constante;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.co.ceiba.establecimiento.dominio.Tarifa;

public final class ValorTarifa {

	public static final Double VALOR_HORA_CARRO = 1000.0;
	public static final Double VALOR_HORA_MOTO = 500.0;
	public static final Double VALOR_DIA_CARRO = 8000.0;
	public static final Double VALOR_DIA_MOTO = 4000.0;

	private ValorTarifa() {
	}

	public static List<Tarifa> getTarifasCarro() {
		List<Tarifa> tarifas = new ArrayList<Tarifa>();
		tarifas.add(new Tarifa(VALOR_DIA_CARRO, TipoVehiculo.CARRO.toString(), ModalidadTarifa.DIA.toString()));
		tarifas.add(new Tarifa(VALOR_HORA_CARRO, TipoVehiculo.CARRO.toString(), ModalidadTarifa.HORA.toString()));
		return tarifas;
	}

	public static List<Tarifa> getTarifasMoto() {
		List<Tarifa> tarifas = new ArrayList<>();
		tarifas.add(new Tarifa(VALOR_DIA_MOTO, TipoVehiculo.MOTO.toString(), ModalidadTarifa.DIA.toString()));
		tarifas.add(new Tarifa(VALOR_HORA_MOTO, TipoVehiculo.MOTO.toString(), ModalidadTarifa.HORA.toString()));
		return tarifas;
	}

	public static List<Tarifa> getTodasTarifas() {
		return Stream.concat(getTarifasCarro().stream(), getTarifasMoto().stream()).collect(Collectors.toList());
	}

	public static List<Tarifa> getTarifasPorTipo(TipoVehiculo tipoVehiculo) {
		if (tipoVehiculo == TipoVehiculo.CARRO) {
			return getTarifasCarro();
		} else {
			return getTarifasMoto();
		}
	}

}
