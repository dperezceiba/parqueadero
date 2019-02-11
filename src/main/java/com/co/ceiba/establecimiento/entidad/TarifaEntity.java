package com.co.ceiba.establecimiento.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.co.ceiba.establecimiento.constante.ModalidadTarifa;
import com.co.ceiba.establecimiento.constante.TipoVehiculo;

@Entity(name="tarifa")
public class TarifaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="valor", nullable=false)
	private Double valor;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_vehiculo", nullable=false)
	private TipoVehiculo tipoVehiculo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="modalidad", nullable=false)
	private ModalidadTarifa modalidad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public ModalidadTarifa getModalidad() {
		return modalidad;
	}

	public void setModalidad(ModalidadTarifa modalidad) {
		this.modalidad = modalidad;
	}

}
