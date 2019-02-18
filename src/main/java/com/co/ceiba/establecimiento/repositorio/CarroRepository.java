package com.co.ceiba.establecimiento.repositorio;

import java.util.Optional;

import com.co.ceiba.establecimiento.dominio.Carro;

public interface CarroRepository{
	public Optional<Carro> buscarPorPlaca(String placa);
}
