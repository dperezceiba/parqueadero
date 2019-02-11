package com.co.ceiba.establecimiento.controller;

import java.util.List;

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
	public Entrada registrarEntrada(@RequestBody Carro carro) {
		return entradaService.registrarIngreso(carro);
	}
	
	@PostMapping("/moto")
	public Entrada registrarEntrada(@RequestBody Moto moto) {
		return entradaService.registrarIngreso(moto);
	}
	
	@GetMapping("/moto/activas")
	public List<Entrada> listarMotoActivas(){
		return entradaService.listarEntradasActivas(TipoVehiculo.MOTO);
	}
	
	@GetMapping("/carro/activas")
	public List<Entrada> listarCarroActivas(){
		return entradaService.listarEntradasActivas(TipoVehiculo.CARRO);
	}

}
