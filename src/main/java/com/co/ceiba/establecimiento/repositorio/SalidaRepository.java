package com.co.ceiba.establecimiento.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.co.ceiba.establecimiento.entidad.SalidaEntity;

public interface SalidaRepository extends JpaRepository<SalidaEntity, Long> {

	@Query(value = "SELECT sal.* FROM salida sal WHERE sal.id_entrada = ?1", nativeQuery = true)
	List<SalidaEntity> consultarPorEntrada(Long idEntrada);

}
