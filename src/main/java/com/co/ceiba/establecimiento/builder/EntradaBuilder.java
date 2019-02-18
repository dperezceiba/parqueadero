package com.co.ceiba.establecimiento.builder;

import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.Moto;
import com.co.ceiba.establecimiento.dominio.TipoVehiculo;
import com.co.ceiba.establecimiento.entidad.CarroEntity;
import com.co.ceiba.establecimiento.entidad.EntradaEntity;
import com.co.ceiba.establecimiento.entidad.MotoEntity;
import com.co.ceiba.establecimiento.util.FechaUtils;

public final class EntradaBuilder {

	private EntradaBuilder() {
	}

	public static Entrada convertirADominio(EntradaEntity entradaEntity) {
		Entrada entrada = null;
		if (entradaEntity != null) {
			entrada = new Entrada(entradaEntity.getId(), FechaUtils.convertir(entradaEntity.getFechaEntrada()),
					entradaEntity.getActivo());
			entrada.setTipoVehiculo(TipoVehiculo.valueOf(entradaEntity.getTipoVehiculo()));
			if (entradaEntity.getVehiculo() instanceof CarroEntity) {
				entrada.setVehiculo(CarroBuilder.convertirADominio((CarroEntity) entradaEntity.getVehiculo()));
			} else {
				entrada.setVehiculo(MotoBuilder.convertirADominio((MotoEntity) entradaEntity.getVehiculo()));
			}
		}
		return entrada;
	}

	public static EntradaEntity convertirAEntity(Entrada entrada) {
		EntradaEntity entradaEntity = null;
		if (entrada != null) {
			entradaEntity = new EntradaEntity();
			entradaEntity.setActivo(entrada.getActivo());
			entradaEntity.setFechaEntrada(FechaUtils.convertir(entrada.getFechaEntrada()));
			entradaEntity.setId(entrada.getIdEntrada());
			if (entrada.getVehiculo() instanceof Carro) {
				entradaEntity.setTipoVehiculo(TipoVehiculo.CARRO.toString());
				entradaEntity.setVehiculoEntity(CarroBuilder.convertirAEntity((Carro) entrada.getVehiculo()));
			} else {
				entradaEntity.setTipoVehiculo(TipoVehiculo.MOTO.toString());
				entradaEntity.setVehiculoEntity(MotoBuilder.convertirAEntity((Moto) entrada.getVehiculo()));
			}
		}
		return entradaEntity;
	}
}
