package com.co.ceiba.establecimiento.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.establecimiento.constante.TipoVehiculo;
import com.co.ceiba.establecimiento.dominio.Carro;
import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.Moto;
import com.co.ceiba.establecimiento.servicio.EntradaService;

@RestController
@RequestMapping("/entrada")
public class EntradaController {

	private EntradaService entradaService;

	public EntradaController(EntradaService entradaService) {
		this.entradaService = entradaService;
	}

	@PostMapping("/carro")
	public ResponseEntity<Entrada> registrarEntrada(@RequestBody Carro carro) {
		LocalDateTime fechaEntrada = LocalDateTime.now();
		Entrada entrada = entradaService.registrarIngreso(carro, fechaEntrada);
		return ResponseEntity.ok(entrada);
	}

	@PostMapping("/moto")
	public ResponseEntity<Entrada> registrarEntrada(@RequestBody Moto moto) {
		LocalDateTime fechaEntrada = LocalDateTime.now();
		Entrada entrada = entradaService.registrarIngreso(moto, fechaEntrada);
		return ResponseEntity.ok(entrada);
	}

	@GetMapping("/moto/activas")
	public List<Entrada> listarMotoActivas() {
		return entradaService.listarEntradasActivas(TipoVehiculo.MOTO);
	}

	@GetMapping("/carro/activas")
	public List<Entrada> listarCarroActivas() {
		return entradaService.listarEntradasActivas(TipoVehiculo.CARRO);
	}

}
