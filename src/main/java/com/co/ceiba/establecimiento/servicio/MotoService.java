package com.co.ceiba.establecimiento.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.co.ceiba.establecimiento.builder.MotoBuilder;
import com.co.ceiba.establecimiento.dominio.Moto;
import com.co.ceiba.establecimiento.entidad.MotoEntity;
import com.co.ceiba.establecimiento.repositorio.MotoRepository;

@Service
public class MotoService {

	private MotoRepository motoRepository;

	public MotoService(MotoRepository motoRepository) {
		this.motoRepository = motoRepository;
	}

	public Moto guardar(Moto moto) {
		MotoEntity motoEntity = MotoBuilder.convertirAEntity(moto);
		motoRepository.save(motoEntity);
		return moto;
	}

	public List<Moto> listar() {
		return motoRepository.findAll().stream().map(MotoBuilder::convertirADominio).collect(Collectors.toList());
	}

}
