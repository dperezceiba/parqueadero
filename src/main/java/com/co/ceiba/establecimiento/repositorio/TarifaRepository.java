package com.co.ceiba.establecimiento.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.co.ceiba.establecimiento.entidad.TarifaEntity;

public interface TarifaRepository extends JpaRepository<TarifaEntity, Long> {
	
	@Query(value = "SELECT tar.* FROM tarifa tar WHERE tar.tipo_vehiculo = ?1", nativeQuery = true)
	List<TarifaEntity> listarTarifas(String tipoVehiculo);
	
}
