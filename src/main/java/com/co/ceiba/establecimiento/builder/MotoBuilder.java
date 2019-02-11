package com.co.ceiba.establecimiento.builder;

import com.co.ceiba.establecimiento.dominio.Moto;
import com.co.ceiba.establecimiento.entidad.MotoEntity;

public final class MotoBuilder {

	private MotoBuilder() {
	}

	public static Moto convertirADominio(MotoEntity motoEntity) {
		Moto moto = null;
		if (motoEntity != null) {
			moto = new Moto(motoEntity.getPlaca(), motoEntity.getCilindraje());
		}
		return moto;
	}

	public static MotoEntity convertirAEntity(Moto moto) {
		MotoEntity motoEntity = null;
		if (moto != null) {
			motoEntity = new MotoEntity();
			motoEntity.setPlaca(moto.getPlaca());
			motoEntity.setCilindraje(moto.getCilindraje());
		}
		return motoEntity;
	}
}
