package br.edu.infuse.app.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatUtils {
	public static String dateFormat(LocalDateTime localDateTime) {
		return localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}

	public static LocalDateTime localDateFormat(String localDateTime) {
		return LocalDateTime.parse(localDateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}
}