package com.co.ceiba.establecimiento.builder;

import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.entidad.CarroEntity;
import com.co.ceiba.establecimiento.entidad.VehiculoEntity;

public final class EntradaBuilderFactory {

	private static final EntradaBuilderFactory instace = new EntradaBuilderFactory();

	private EntradaBuilderFactory() {
	}

	public IEntradaBuilder getEntradaBuilder(VehiculoEntity vehiculoEntity) {
		if (vehiculoEntity instanceof CarroEntity) {
			return new EntradaCarroBuilder();
		} else {
			return new EntradaMotoBuilder();
		}
	}

	public IEntradaBuilder getEntradaBuilder(Vehiculo vehiculo) {
		if (vehiculo instanceof Carro) {
			return new EntradaCarroBuilder();
		} else {
			return new EntradaMotoBuilder();
		}
	}

	public static EntradaBuilderFactory getInstance() {
		return instace;
	}

}
