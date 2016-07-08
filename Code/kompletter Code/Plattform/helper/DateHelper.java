
package de.fhws.sdk.orca.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Klasse mit statischen Methoden für die korrekte Umwandlung von Variablen des
 * Date-Formats.
 * 
 * @author ThomasDeinlein
 */
public class DateHelper {
	
	/**
	 * Einheitliches Format ("EEE MMM dd HH:mm:ss zzz yyyy") für
	 * Date-Datentypen. Wird vom Orca-Backend vorgegeben und wird für alle
	 * Date-Objekt entsprechend übernommen.
	 */
	public static final String	DATEFORMAT	= "EEE MMM dd HH:mm:ss zzz yyyy";
	
	/**
	 * Methode zur Ausgabe des übergebenen Date-Objektes als String.
	 * 
	 * @param date das als String auszugebende Date-Objekt
	 * @return das übergebene Date-Objekt als String, das Format wird vom
	 *         Orca-Backend vorgegeben
	 */
	public static String dateToString(Date date) {
	
		SimpleDateFormat sf = new SimpleDateFormat(DATEFORMAT, Locale.ENGLISH);
		return sf.format(date).toString();
	}
	
	/**
	 * Methode zum Umwandeln des übergebenen Strings in ein Date-Objekt. Der
	 * String muss die Form {@linkplain #DATEFORMAT} haben.
	 * 
	 * @param stringDate der String, der in ein Date-Objekt umgewandelt werden
	 *            soll
	 * @return das Date-Objekt
	 * @throws ParseException wird geworfen, wenn Umwandlung fehl schlägt
	 */
	public static Date stringToDate(String stringDate) throws ParseException {
	
		SimpleDateFormat sf = new SimpleDateFormat(DATEFORMAT, Locale.ENGLISH);
		Date temp = null;
		
		temp = sf.parse(stringDate);
		
		return temp;
	}
	
	/**
	 * Methode zum abrufen des aktuellen Datums im Format
	 * "EEE MMM dd HH:mm:ss zzz yyyy".
	 * 
	 * @return String des aktuellen Datums.
	 */
	public static String getCurrentDateAsString() {
	
		SimpleDateFormat sf = new SimpleDateFormat(DATEFORMAT, Locale.ENGLISH);
		Date date = new Date();
		return sf.format(date);
	}
	
	/**
	 * Methode zum Umwandeln eines Date-Objekts in einen JSON-String nach dem
	 * Vorbild des Orca-Backend.
	 * 
	 * @param date das Date-Objekt, das umgewandelt werden soll
	 * @return JSON-String des übergebenen Date-Objektes
	 */
	public static String dateToJSONString(Date date) {
	
		String go = "{" + jsonString("value", dateToString(date)) + ","
				+ jsonString("type", "Date") + "}";
		
		return go;
	}
	
	private static String jsonString(String key, String value) {
	
		String ret = "\"" + key + "\"" + ":" + "\"" + value + "\"";
		return ret;
		
	}
	
}
