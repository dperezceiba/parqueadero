package com.co.ceiba.establecimiento.builder;

import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.entidad.CarroEntity;
import com.co.ceiba.establecimiento.entidad.MotoEntity;
import com.co.ceiba.establecimiento.entidad.VehiculoEntity;

public final class VehiculoBuilder {

	private VehiculoBuilder() {
	}
	
	public static Vehiculo convertirADominio(VehiculoEntity vehiculoEntity) {
		if (vehiculoEntity instanceof CarroEntity) {
			return CarroBuilder.convertirADominio((CarroEntity) vehiculoEntity);
		} else {
			return MotoBuilder.convertirADominio((MotoEntity) vehiculoEntity);
		}
	}


}
