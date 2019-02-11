package com.co.ceiba.establecimiento.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.co.ceiba.establecimiento.builder.CarroBuilder;
import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.entidad.CarroEntity;
import com.co.ceiba.establecimiento.repositorio.CarroRepository;

@Service
public class CarroService {

	private CarroRepository carroRepository;

	public CarroService(CarroRepository carroRepository) {
		this.carroRepository = carroRepository;
	}

	public Carro guardar(Carro carro) {
		CarroEntity carroEntity = CarroBuilder.convertirAEntity(carro);
		carroRepository.save(carroEntity);
		return carro;
	}

	public List<Carro> listar() {
		return carroRepository.findAll().stream().map(CarroBuilder::convertirADominio).collect(Collectors.toList());
	}

}
