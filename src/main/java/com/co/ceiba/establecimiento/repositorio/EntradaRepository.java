package com.co.ceiba.establecimiento.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.co.ceiba.establecimiento.entidad.EntradaEntity;

public interface EntradaRepository extends JpaRepository<EntradaEntity, Long> {

	@Query(value = "SELECT COUNT(*) FROM entrada ent WHERE ent.tipo_vehiculo = ?1 AND ent.activo = true", nativeQuery = true)
	Integer cantidadEntradasActivas(String tipoVehiculo);

	@Query(value = "SELECT ent.* FROM entrada ent WHERE ent.tipo_vehiculo = ?1 AND ent.activo = true", nativeQuery = true)
	List<EntradaEntity> listarEntradasActivas(String tipoVehiculo);
	
	@Query(value = "SELECT ent.* FROM entrada ent WHERE ent.activo = true", nativeQuery = true)
	List<EntradaEntity> listarEntradasActivas();
	
	@Query(value = "SELECT ent.* FROM entrada ent WHERE ent.placa = ?1 AND ent.activo = true", nativeQuery = true)
	List<EntradaEntity> listarEntradasActivasPorVehiculo(String placa);

}
