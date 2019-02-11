package com.co.ceiba.establecimiento.builder;

import com.co.ceiba.establecimiento.dominio.Salida;
import com.co.ceiba.establecimiento.entidad.SalidaEntity;

public final class SalidaBuilder {
	private SalidaBuilder() {
	}

	public static Salida convertirADominio(SalidaEntity salidaEntity) {
		Salida salida = null;
		if (salidaEntity != null) {
			salida = new Salida(salidaEntity.getId(), salidaEntity.getFechaSalida(), salidaEntity.getValor());
			salida.setEntrada(EntradaBuilder.convertirADominio(salidaEntity.getEntradaEntity()));
		}
		return salida;
	}

	public static SalidaEntity convertirAEntity(Salida salida) {
		SalidaEntity salidaEntity = null;
		if (salida != null) {
			salidaEntity = new SalidaEntity();
			salidaEntity.setId(salida.getId());
			salidaEntity.setFechaSalida(salidaEntity.getFechaSalida());
			salidaEntity.setValor(salida.getValor());
			salidaEntity.setEntradaEntity(EntradaBuilder.convertirAEntity(salida.getEntrada()));
		}
		return salidaEntity;
	}
}
