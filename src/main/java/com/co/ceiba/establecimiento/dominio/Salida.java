package com.co.ceiba.establecimiento.dominio;

import java.util.Date;

public class Salida {
	private Long id;
	private Date fechaSalida;
	private Double valor;
	private Entrada entrada;
	
	public Salida() {
		super();
	}
	
	public Salida(Long id, Date fechaSalida, Double valor) {
		this.id = id;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
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
