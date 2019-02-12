package com.co.ceiba.establecimiento.entidad;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "salida")
public class SalidaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_entrada")
	private EntradaEntity entradaEntity;

	@Column(name = "fecha_salida", nullable = false)
	private Timestamp fechaSalida;

	@Column(name = "valor", nullable = false)
	private Double valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntradaEntity getEntradaEntity() {
		return entradaEntity;
	}

	public void setEntradaEntity(EntradaEntity entradaEntity) {
		this.entradaEntity = entradaEntity;
	}

	public Timestamp getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Timestamp fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
