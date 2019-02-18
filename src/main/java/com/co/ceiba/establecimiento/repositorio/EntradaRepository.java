package com.co.ceiba.establecimiento.repositorio;

import java.util.List;

import com.co.ceiba.establecimiento.dominio.Entrada;

public interface EntradaRepository {

	Integer cantidadActivas(String tipoVehiculo);

	List<Entrada> listarActivas(String tipoVehiculo);

	List<Entrada> listarActivas();

	List<Entrada> listarActivasPorVehiculo(String placa);
	
	Entrada guardar(Entrada entrada);
	
	Entrada consultarPorId(Long id);

}
