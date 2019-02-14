package com.co.ceiba.establecimiento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CeibaEstablecimientoApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(CeibaEstablecimientoApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(CeibaEstablecimientoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Verificando las tarifas...");
	}

}
