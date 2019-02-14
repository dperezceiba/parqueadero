package com.co.ceiba.establecimiento.dominio;

import java.time.LocalDateTime;

public class Salida {
	private Long idSalida;
	private LocalDateTime fechaSalida;
	private Double valor;
	private Entrada entrada;

	public Salida() {
		super();
	}

	public Salida(Long idSalida, LocalDateTime fechaSalida, Double valor) {
		this.idSalida = idSalida;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
	}

	public Long getIdSalida() {
		return idSalida;
	}

	public void setIdSalida(Long idSalida) {
		this.idSalida = idSalida;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

}
