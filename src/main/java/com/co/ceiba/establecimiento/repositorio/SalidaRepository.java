package com.co.ceiba.establecimiento.repositorio;

import java.util.List;

import com.co.ceiba.establecimiento.dominio.Salida;

public interface SalidaRepository {

	List<Salida> consultarPorEntrada(Long idEntrada);
	
	Salida guardar(Salida salida);

}
