package com.co.ceiba.establecimiento.util;

import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.dominio.Vehiculo;

public final class TipoUtils {

	private TipoUtils() {
	}

	public static TipoVehiculo getTipoVehiculo(Vehiculo vehiculo) {
		return (vehiculo instanceof Carro) ? TipoVehiculo.CARRO : TipoVehiculo.MOTO;
	}

}
