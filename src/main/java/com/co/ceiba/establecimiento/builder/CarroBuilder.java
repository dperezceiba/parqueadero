package com.co.ceiba.establecimiento.builder;

import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.entidad.CarroEntity;

public final class CarroBuilder {

	private CarroBuilder() {
	}

	public static Carro convertirADominio(CarroEntity carroEntity) {
		Carro carro = null;
		if (carroEntity != null) {
			carro = new Carro(carroEntity.getPlaca(), carroEntity.getModelo());
		}
		return carro;
	}

	public static CarroEntity convertirAEntity(Carro carro) {
		CarroEntity carroEntity = null;
		if (carro != null) {
			carroEntity = new CarroEntity();
			carroEntity.setPlaca(carro.getPlaca());
			carroEntity.setModelo(carro.getModelo());
		}
		return carroEntity;
	}
}
