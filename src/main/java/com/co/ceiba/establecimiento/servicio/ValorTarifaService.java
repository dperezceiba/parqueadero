package com.co.ceiba.establecimiento.servicio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.co.ceiba.establecimiento.dominio.ModalidadTarifa;
import com.co.ceiba.establecimiento.dominio.Tarifa;
import com.co.ceiba.establecimiento.dominio.TipoVehiculo;

@Service
public class ValorTarifaService {
	
	public static final Double VALOR_HORA_CARRO = 1000.0;
	public static final Double VALOR_HORA_MOTO = 500.0;
	public static final Double VALOR_DIA_CARRO = 8000.0;
	public static final Double VALOR_DIA_MOTO = 4000.0;

	private List<Tarifa> tarifas;
	
	public ValorTarifaService() {
		tarifas = new ArrayList<>();
		tarifas.add(new Tarifa(VALOR_DIA_CARRO, TipoVehiculo.CARRO, ModalidadTarifa.DIA));
		tarifas.add(new Tarifa(VALOR_HORA_CARRO, TipoVehiculo.CARRO, ModalidadTarifa.HORA));
		tarifas.add(new Tarifa(VALOR_DIA_MOTO, TipoVehiculo.MOTO, ModalidadTarifa.DIA));
		tarifas.add(new Tarifa(VALOR_HORA_MOTO, TipoVehiculo.MOTO, ModalidadTarifa.HORA));
	}

	public List<Tarifa> getTarifasCarro() {
		return getTarifasPorTipo(TipoVehiculo.CARRO);
	}

	public List<Tarifa> getTarifasMoto() {
		return getTarifasPorTipo(TipoVehiculo.MOTO);
	}

	public List<Tarifa> getTodasTarifas() {
		return tarifas;
	}

	public List<Tarifa> getTarifasPorTipo(TipoVehiculo tipoVehiculo) {
		return tarifas.stream().filter(obj -> obj.getTipoVehiculo() == tipoVehiculo).collect(Collectors.toList());
	}

	public Tarifa getTarifaModalidadPorTipo(TipoVehiculo tipoVehiculo, ModalidadTarifa modalidadTarifa) {
		return tarifas.stream()
				.filter(obj -> obj.getModalidad() == modalidadTarifa && obj.getTipoVehiculo() == tipoVehiculo)
				.findFirst().orElse(new Tarifa(BigDecimal.ZERO.doubleValue()));
	}

}
