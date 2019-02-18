package com.co.ceiba.establecimiento.repositorio.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.co.ceiba.establecimiento.builder.CarroBuilder;
import com.co.ceiba.establecimiento.builder.EntradaBuilder;
import com.co.ceiba.establecimiento.builder.MotoBuilder;
import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.Moto;
import com.co.ceiba.establecimiento.dominio.Vehiculo;
import com.co.ceiba.establecimiento.entidad.EntradaEntity;
import com.co.ceiba.establecimiento.entidad.VehiculoEntity;
import com.co.ceiba.establecimiento.repositorio.EntradaRepository;
import com.co.ceiba.establecimiento.util.FechaUtils;

@Transactional
@Repository
public class EntradaRepositoryImpl implements EntradaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Integer cantidadActivas(String tipoVehiculo) {
		Query query = entityManager.createNativeQuery(
				"SELECT COUNT(*) FROM entrada ent WHERE ent.tipo_vehiculo = ? AND ent.activo = true");
		query.setParameter(1, tipoVehiculo);
		return ((Number) query.getSingleResult()).intValue();
	}

	@Override
	public List<Entrada> listarActivas(String tipoVehiculo) {
		TypedQuery<EntradaEntity> query = entityManager.createQuery(
				"SELECT ent FROM entrada ent WHERE ent.tipoVehiculo = :tipoVehiculo AND ent.activo = true",
				EntradaEntity.class);
		query.setParameter("tipoVehiculo", tipoVehiculo);
		return query.getResultList().stream().map(EntradaBuilder::convertirADominio).collect(Collectors.toList());
	}

	@Override
	public List<Entrada> listarActivas() {
		TypedQuery<EntradaEntity> query = entityManager
				.createQuery("SELECT ent FROM entrada ent WHERE ent.activo = true", EntradaEntity.class);
		return query.getResultList().stream().map(EntradaBuilder::convertirADominio).collect(Collectors.toList());
	}

	@Override
	public List<Entrada> listarActivasPorVehiculo(String placa) {
		TypedQuery<EntradaEntity> query = entityManager.createQuery(
				"SELECT ent FROM entrada ent WHERE ent.vehiculo.placa = :placa AND ent.activo = true",
				EntradaEntity.class);
		query.setParameter("placa", placa);
		return query.getResultList().stream().map(EntradaBuilder::convertirADominio).collect(Collectors.toList());
	}

	@Override
	public Entrada guardar(Entrada entrada) {
		VehiculoEntity vehiculoEntity = getVehiculoEntity(entrada.getVehiculo());
		EntradaEntity entradaEntity = new EntradaEntity();
		entradaEntity.setActivo(Boolean.TRUE);
		entradaEntity.setFechaEntrada(FechaUtils.convertir(entrada.getFechaEntrada()));
		entradaEntity.setTipoVehiculo(entrada.getVehiculo().getTipoVehiculo().toString());
		entradaEntity.setVehiculoEntity(vehiculoEntity);
		entityManager.persist(entradaEntity);
		return EntradaBuilder.convertirADominio(entradaEntity);
	}

	private VehiculoEntity getVehiculoEntity(Vehiculo vehiculo) {
		if (vehiculo instanceof Carro) {
			return CarroBuilder.convertirAEntity((Carro) vehiculo);
		} else {
			return MotoBuilder.convertirAEntity((Moto) vehiculo);
		}
	}

	@Override
	public Entrada consultarPorId(Long id) {
		return EntradaBuilder.convertirADominio(entityManager.find(EntradaEntity.class, id));
	}

}
