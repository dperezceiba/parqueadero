package com.co.ceiba.establecimiento.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public final class FechaUtils {

	private FechaUtils() {
	}

	public static Timestamp convertir(LocalDateTime localDateTime) {
		return localDateTime != null ? Timestamp.valueOf(localDateTime) : null;
	}

	public static LocalDateTime convertir(Timestamp timestamp) {
		return timestamp != null ? timestamp.toLocalDateTime() : null;
	}
}
