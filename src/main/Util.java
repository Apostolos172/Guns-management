package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
	
	/*
	 * function for get the current time and date in specific format
	 */
	public static String getTime() {
		// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd/MM");
		LocalDateTime now = LocalDateTime.now();
		// System.out.println(dtf.format(now));
		return dtf.format(now);
	}
}
