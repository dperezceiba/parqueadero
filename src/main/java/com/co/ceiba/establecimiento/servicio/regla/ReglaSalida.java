package com.co.ceiba.establecimiento.servicio.regla;

import com.co.ceiba.establecimiento.dominio.Moto;
import com.co.ceiba.establecimiento.dominio.Vehiculo;

public class ReglaSalida {

	private static final Double CILINDRAJE_SUP = 500.0;
	public static final Double VALOR_EXCEDENTE = 2000.0;

	private static final Integer LIMITE_DIA_INFERIOR = 9;
	private static final Integer LIMITE_DIA_SUPERIOR = 24;
	
	private Vehiculo vehiculo;
	private Integer cantidadHoras;
	
	public ReglaSalida(Vehiculo vehiculo, Integer cantidadHoras) {
		this.vehiculo = vehiculo;
		this.cantidadHoras = cantidadHoras;
	}

	public DetalleSalida calcularDetalleSalida() {
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


}
