package com.co.ceiba.establecimiento.testdatabuilder;

import java.util.Calendar;

import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.entidad.CarroEntity;
import com.co.ceiba.establecimiento.entidad.EntradaEntity;

public final class EntradaTestData {
	
	public static final String PLACA_CONST = "GTJ89";
	public static final String MODELO_CONST = "2018";
	
	private EntradaTestData() {
	}
	
	public static EntradaEntity getEntradaEntityCarroTest() {
		CarroEntity carroEntity = new CarroEntity();
		carroEntity.setPlaca(PLACA_CONST);
		carroEntity.setModelo(MODELO_CONST);
		
		EntradaEntity entity = new EntradaEntity();
		entity.setActivo(Boolean.TRUE);
		entity.setFechaEntrada(Calendar.getInstance().getTime());
		entity.setTipoVehiculo(TipoVehiculo.CARRO);
		entity.setVehiculoEntity(carroEntity);
		return entity;
	}
	
}
