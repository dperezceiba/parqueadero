package com.co.ceiba.establecimiento.builder;

import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.entidad.EntradaEntity;

public interface IEntradaBuilder {
	
	Entrada convertirADominio(EntradaEntity entradaEntity);

	EntradaEntity convertirAEntity(Entrada entrada);
	
}
