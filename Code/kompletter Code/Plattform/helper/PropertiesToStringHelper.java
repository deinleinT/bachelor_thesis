
package de.fhws.sdk.orca.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.model.Link;

/**
 * Klasse mit statischen Methoden, die es ermöglichen, Entity- und
 * Link-Properties zu übergeben und Ausgabe-Strings mit den entsprechenden
 * Werten zu erhalten.
 * 
 * @author ThomasDeinlein
 */
public class PropertiesToStringHelper {
	
	/**
	 * Methode zum Auslesen eines Entity. Alle Entity-Properties und auch alle
	 * Links (mit jeweiligen Properties) werden ausgelesen und als String
	 * zurückgegeben. Alle Properties werden durch einen Absatz getrennt.
	 * 
	 * @param entity die auszulesende Entity
	 * @return String, der alle Entity-Properties und LinkProperties enthält.
	 */
	public static <T extends Entity> String createStringByProperties(T entity) {
	
		if (entity != null) {
			HashMap<String, Object> map = entity.getProperties();
			String returnValue = "";
			returnValue += "ID: " + entity.getId() + "\n" + "Self: "
					+ entity.getSelf() + "\n" + "Type: " + entity.getType()
					+ "\n";
			Iterator<Map.Entry<String, Object>> iterator = map.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> entry = iterator.next();
				String key = entry.getKey();
				Object value = entry.getValue();
				
				if (value.getClass().getSimpleName()
						.equalsIgnoreCase("Double[]")) {
					returnValue = returnValue + uppercaseFirstLetters(key)
							+ ": " + "[" + ((Double[]) value)[0] + ","
							+ ((Double[]) value)[1] + "]";
				}
				else if (value.getClass().getSimpleName()
						.equalsIgnoreCase("Date")) {
					returnValue = returnValue + uppercaseFirstLetters(key)
							+ ": " + DateHelper.dateToString((Date) value);
				}
				else {
					returnValue = returnValue + uppercaseFirstLetters(key)
							+ ": " + value.toString();
				}
				returnValue = returnValue + "\n";
			}
			
			return returnValue;
		}
		else {
			return "Error - Entity is null.";
		}
		
	}
	
	/**
	 * Setzt aus den übergebenen Properties einen String mit allen enthaltenen
	 * Properties zusammen, und trennt diese per Absatz.
	 * 
	 * @param properties die EntityProperties
	 * @return String, der die übergebenen Properties ausgelesen hat
	 */
	public static <T extends Entity> String createStringByProperties(
			HashMap<String, Object> properties) {
	
		if (properties != null) {
			HashMap<String, Object> map = properties;
			String returnValue = "";
			
			Iterator<Map.Entry<String, Object>> iterator = map.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> entry = iterator.next();
				String key = entry.getKey();
				Object value = entry.getValue();
				
				if (value.getClass().getSimpleName()
						.equalsIgnoreCase("Double[]")) {
					returnValue = returnValue + uppercaseFirstLetters(key)
							+ ": " + "[" + ((Double[]) value)[0] + ","
							+ ((Double[]) value)[1] + "]";
				}
				else if (value.getClass().getSimpleName()
						.equalsIgnoreCase("Date")) {
					returnValue = returnValue + uppercaseFirstLetters(key)
							+ ": " + DateHelper.dateToString((Date) value);
				}
				else {
					returnValue = returnValue + uppercaseFirstLetters(key)
							+ ": " + value.toString();
				}
				returnValue = returnValue + "\n";
			}
			
			return returnValue;
		}
		else {
			return "Error - properties are null.";
		}
		
	}
	
	/**
	 * Lokale Hilfsmethode. Setzt in einem String alle Anfangsbuchstaben von
	 * Wörtern als Großbuchstaben.
	 * 
	 * @param str der umzuwandelnde String
	 * @return der Ergebnis-String
	 */
	private static String uppercaseFirstLetters(String str) {
	
		boolean prevWasWhiteSp = true;
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (Character.isLetter(chars[i])) {
				if (prevWasWhiteSp) {
					chars[i] = Character.toUpperCase(chars[i]);
				}
				prevWasWhiteSp = false;
			}
			else {
				prevWasWhiteSp = Character.isWhitespace(chars[i]);
			}
		}
		return new String(chars);
	}
	
	/**
	 * Setzt einen String zusammen aus den übergebenen Links. Alle enthaltenen
	 * Links werden durchnummeriert und durch einen Absatz getrennt. Es werden
	 * jeweils das LinkProperty mit dem entsprechenden Wert ausgegeben.
	 * 
	 * @param list eine ArrayList mit den EntityLinks
	 * @return der zusammengesetzte String
	 */
	public static String createLinkStringFromEntityLinks(ArrayList<Link> list) {
	
		String retValue = "";
		int counter = 1;
		if (list != null && list.size() > 0) {
			for (Link link : list) {
				String temp = "";
				temp = "Link " + counter + ":\n";
				temp = temp + "Object: " + link.getObject() + "\n";
				temp = temp + "Type: " + link.getType() + "\n";
				temp = temp + createStringByProperties(link.getProperties())
						+ "\n";
				retValue += temp;
				counter++;
			}
		}
		
		return retValue;
	}
	
}
