package com.co.ceiba.establecimiento.repositorio.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.co.ceiba.establecimiento.builder.EntradaBuilder;
import com.co.ceiba.establecimiento.builder.SalidaBuilder;
import com.co.ceiba.establecimiento.dominio.Salida;
import com.co.ceiba.establecimiento.entidad.SalidaEntity;
import com.co.ceiba.establecimiento.repositorio.SalidaRepository;
import com.co.ceiba.establecimiento.util.FechaUtils;

@Transactional
@Repository
public class SalidaRepositoryImpl implements SalidaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Salida> consultarPorEntrada(Long idEntrada) {
		TypedQuery<SalidaEntity> query = entityManager.createQuery(
				"SELECT sal FROM salida sal WHERE sal.entrada.id = :idEntrada AND sal.entrada.activo = true",
				SalidaEntity.class);
		query.setParameter("idEntrada", idEntrada);
		return query.getResultList().stream().map(SalidaBuilder::convertirADominio).collect(Collectors.toList());
	}

	@Override
	public Salida guardar(Salida salida) {
		SalidaEntity salidaEntity = new SalidaEntity();
		salidaEntity.setFechaSalida(FechaUtils.convertir(salida.getFechaSalida()));
		salidaEntity.setValor(salida.getValor());
		salidaEntity.setEntrada(EntradaBuilder.convertirAEntity(salida.getEntrada()));
		salidaEntity.getEntrada().setActivo(Boolean.FALSE);
		entityManager.persist(salidaEntity);
		return SalidaBuilder.convertirADominio(salidaEntity);
	}

}
