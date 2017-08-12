package br.com.projeto.regis.api.auth.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Utility class to manipulate information about date
 * 
 * @author regis.rocha
 *
 */
public class DateUtil {
	
	/**
	 * Pattern yyyy/MM/dd hh:mm:ss
	 */
	private static final String PATTERN_DATE_TIME = "yyyy/MM/dd hh:mm:ss"; 
	
	/**
	 * Convert calendar date to String yyyy/MM/dd hh:mm:ss
	 * 
	 * @param date - Calendar
	 * 
	 * @return String 
	 */
	public static String convertCalendarToStringDateAndTime(final Calendar date) {
		if (date == null) {
			return "";
		}
		
		final DateFormat df = new SimpleDateFormat(PATTERN_DATE_TIME);
		
		return df.format(date.getTime());
	}
}
