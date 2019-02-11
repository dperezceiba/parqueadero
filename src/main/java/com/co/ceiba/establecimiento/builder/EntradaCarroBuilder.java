package com.co.ceiba.establecimiento.builder;

import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.entidad.CarroEntity;
import com.co.ceiba.establecimiento.entidad.EntradaEntity;

public class EntradaCarroBuilder implements IEntradaBuilder{
	
	@Override
	public Entrada convertirADominio(EntradaEntity entradaEntity) {
		Entrada entrada = null;
		if (entradaEntity != null) {
			entrada = new Entrada(entradaEntity.getId(), entradaEntity.getFechaEntrada(), entradaEntity.getActivo());
			entrada.setTipoVehiculo(TipoVehiculo.CARRO.toString());
			entrada.setVehiculo(CarroBuilder.convertirADominio((CarroEntity) entradaEntity.getVehiculoEntity()));
		}
		return entrada;
	}
	
	@Override
	public EntradaEntity convertirAEntity(Entrada entrada) {
		EntradaEntity entradaEntity = null;
		if (entrada != null) {
			entradaEntity = new EntradaEntity();
			entradaEntity.setActivo(entrada.getActivo());
			entradaEntity.setFechaEntrada(entrada.getFechaEntrada());
			entradaEntity.setId(entrada.getIdEntrada());
			entradaEntity.setTipoVehiculo(TipoVehiculo.CARRO);
			entradaEntity.setVehiculoEntity(CarroBuilder.convertirAEntity((Carro) entrada.getVehiculo()));
		}
		return entradaEntity;
	}

}
