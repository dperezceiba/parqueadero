package com.co.ceiba.establecimiento.entidad;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.co.ceiba.establecimiento.constante.TipoVehiculo;

@Entity(name = "entrada")
public class EntradaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "fecha_entrada", nullable = false)
	private Date fechaEntrada;

	@Column(name = "activo", nullable = false)
	private Boolean activo;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name = "placa")
	private VehiculoEntity vehiculoEntity;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_vehiculo", nullable = false)
	private TipoVehiculo tipoVehiculo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public VehiculoEntity getVehiculoEntity() {
		return vehiculoEntity;
	}

	public void setVehiculoEntity(VehiculoEntity vehiculoEntity) {
		this.vehiculoEntity = vehiculoEntity;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

}
