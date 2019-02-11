package com.co.ceiba.establecimiento.servicio.regla;

import com.co.ceiba.establecimiento.dominio.Moto;
import com.co.ceiba.establecimiento.dominio.Vehiculo;

public final class ReglaSalidaSingleton {

	private static final Double CILINDRAJE_SUP = 500.0;
	private static final Double VALOR_EXCEDENTE = 2000.0;

	private static final Integer LIMITE_DIA_INFERIOR = 9;
	private static final Integer LIMITE_DIA_SUPERIOR = 24;

	private static final ReglaSalidaSingleton INSTANCE = new ReglaSalidaSingleton();

	private ReglaSalidaSingleton() {
	}

	public DetalleSalida calcularDetalleSalida(Vehiculo vehiculo, Integer cantidadHoras) {
		Integer dias = 0;
		Integer horas = 0;
		DetalleSalida calculoTiempo = new DetalleSalida();

		if (cantidadHoras > LIMITE_DIA_SUPERIOR) {
			dias = cantidadHoras / LIMITE_DIA_SUPERIOR;
			horas = cantidadHoras % LIMITE_DIA_SUPERIOR;
			if (horas > LIMITE_DIA_INFERIOR) {
				dias++;
				horas = 0;
			}
		} else if (cantidadHoras > LIMITE_DIA_INFERIOR) {
			dias++;
			horas = 0;
		} else {
			horas = cantidadHoras;
		}

		calculoTiempo.setDias(dias);
		calculoTiempo.setHoras(horas);
		calculoTiempo.setExcedente(calcularExcedente(vehiculo));

		return calculoTiempo;
	}

	private Double calcularExcedente(Vehiculo vehiculo) {
		Double excedente = 0.0;
		if ((vehiculo instanceof Moto) && aplicaReglaCilindraje((Moto) vehiculo)) {
			excedente += VALOR_EXCEDENTE;
		}
		return excedente;
	}

	private static boolean aplicaReglaCilindraje(Moto moto) {
		return moto.getCilindraje() > CILINDRAJE_SUP;
	}

	public static ReglaSalidaSingleton getInstance() {
		return INSTANCE;
	}

}
