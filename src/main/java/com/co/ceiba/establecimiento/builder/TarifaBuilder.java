package com.co.ceiba.establecimiento.builder;

import com.co.ceiba.establecimiento.constante.ModalidadTarifa;
import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Tarifa;
import com.co.ceiba.establecimiento.entidad.TarifaEntity;

public final class TarifaBuilder {
	private TarifaBuilder() {
	}

	public static Tarifa convertirADominio(TarifaEntity tarifaEntity) {
		Tarifa tarifa = null;
		if (tarifaEntity != null) {
			tarifa = new Tarifa(tarifaEntity.getValor(), tarifaEntity.getTipoVehiculo().toString(),
					tarifaEntity.getModalidad().toString());
		}
		return tarifa;
	}

	public static TarifaEntity convertirAEntity(Tarifa tarifa) {
		TarifaEntity tarifaEntity = null;
		if (tarifa != null) {
			tarifaEntity = new TarifaEntity();
			tarifaEntity.setValor(tarifa.getValor());
			tarifaEntity.setTipoVehiculo(TipoVehiculo.valueOf(tarifa.getTipoVehiculo()));
			tarifaEntity.setModalidad(ModalidadTarifa.valueOf(tarifa.getModalidad()));
		}
		return tarifaEntity;
	}
}
