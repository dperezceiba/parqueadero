package com.co.ceiba.establecimiento.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.establecimiento.dominio.Entrada;
import com.co.ceiba.establecimiento.dominio.Salida;
import com.co.ceiba.establecimiento.servicio.SalidaService;

@RestController
@RequestMapping("/salida/v1")
public class SalidaController {

	private SalidaService salidaService;

	public SalidaController(SalidaService salidaService) {
		this.salidaService = salidaService;
	}
	
	@CrossOrigin
	@PostMapping("/registrar")
	public Salida registrarSalida(@RequestBody Entrada entrada) {
		LocalDateTime fechaSalida = LocalDateTime.now();
		return salidaService.generarSalida(entrada, fechaSalida);
	}

}
