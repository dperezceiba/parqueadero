package com.co.ceiba.establecimiento.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.Moto;
import com.co.ceiba.establecimiento.servicio.EntradaService;

@RestController
@RequestMapping("/entrada/v1")
public class EntradaController {

	private EntradaService entradaService;

	public EntradaController(EntradaService entradaService) {
		this.entradaService = entradaService;
	}

	@PostMapping("/carro")
	public Entrada registrar(@RequestBody Carro carro) {
		LocalDateTime fechaEntrada = LocalDateTime.now();
		return entradaService.registrarIngreso(carro, fechaEntrada);
	}

	@PostMapping("/moto")
	public Entrada registrar(@RequestBody Moto moto) {
		LocalDateTime fechaEntrada = LocalDateTime.now();
		return entradaService.registrarIngreso(moto, fechaEntrada);
	}

	@GetMapping("/activas")
	public List<Entrada> listarActivas() {
		return entradaService.listarActivas();
	}

}
