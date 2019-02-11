package com.co.ceiba.establecimiento.builder;

import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.entidad.EntradaEntity;

public final class EntradaBuilder {

	private EntradaBuilder() {
	}

	public static Entrada convertirADominio(EntradaEntity entradaEntity) {
		return EntradaBuilderFactory.getInstance().getEntradaBuilder(entradaEntity.getVehiculoEntity())
				.convertirADominio(entradaEntity);
	}

	public static EntradaEntity convertirAEntity(Entrada entrada) {
		return EntradaBuilderFactory.getInstance().getEntradaBuilder(entrada.getVehiculo()).convertirAEntity(entrada);
	}
}
