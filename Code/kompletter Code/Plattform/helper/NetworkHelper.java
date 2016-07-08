
package de.fhws.sdk.orca.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.fhws.sdk.orca.model.Entity;

/**
 * Klasse mit statischen Hilfsmethoden, die für einige AsyncClass-Klassen für
 * Netzwerkzugriffe auf das Orca-Backend benötigt werden.
 * 
 * @author ThomasDeinlein
 */
public class NetworkHelper {
	
	/**
	 * Hiermit wird ein QueryString anhand der Properties der übergebenen Entity
	 * erstellt. Es werden nur String, Double und Long-Werte aus den Properties
	 * gelesen und mit AND verknüpft. Der erstellt QueryString verwendet, um zu
	 * prüfen, ob es schon eine Entity im Backend mit diesen Properties(-Werten)
	 * im Backend gibt.
	 * 
	 * @param entity die Entity, deren Properties für den String verwendet
	 *            werden
	 * @return der QueryString
	 * @throws UnsupportedEncodingException wird geworfen, falls bei der
	 *             Umwandlung in URL-UTF8-Strings Fehler auftreten
	 */
	public static String createQueryStringFromEntity(Entity entity)
			throws UnsupportedEncodingException {
	
		String result = "?query=";
		String orderBy = "";
		String temper = "";
		ArrayList<String> temp = new ArrayList<String>();
		
		HashMap<String, Object> map = entity.getProperties();
		Iterator<Map.Entry<String, Object>> iterator = map.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			
			if (isValueTypeOfString(value)) {
				if (value.toString().contains("WIDTH")
						|| value.toString().contains("HEIGHT")
						|| value.toString().contains("RADIUS")) {
					
				}
				else {
					String string = urlUTF8Formatter(key) + "%20%3D%3D%20%22"
							+ urlUTF8Formatter(value.toString()) + "%22";
					temp.add(string);
				}
			}
			if (isValueTypeOfSimpleType(value)) {
				
				String string = urlUTF8Formatter(key) + "%20%3D%3D%20"
						+ urlUTF8Formatter(value.toString());
				temp.add(string);
			}
		}
		String[] array = new String[temp.size()];
		temp.toArray(array);
		
		for (int i = 0; i < array.length; i++) {
			array[i] = array[i] + "%20AND%20";
		}
		array[array.length - 1] = array[array.length - 1].replace("%20AND%20",
				"");
		for (String string : array) {
			temper = temper + string;
		}
		
		result = "?query=" + temper + "&" + Constants.ENTITY_ENTITYTYPE + "="
				+ URLEncoder.encode(String.valueOf(entity.getType()), "UTF-8")
				+ "&orderby=" + URLEncoder.encode(orderBy, "UTF-8")
				+ "&offset=0&size=1000";
		return result;
	}
	
	/**
	 * Methode zum Überprüfen des ClassTypes des übergebenen Object.
	 * 
	 * @param value das zu prüfende Object.
	 * @return true, wenn Object vom Typ Double, Long oder Boolean ist
	 */
	public static boolean isValueTypeOfSimpleType(Object value) {
	
		return value != null ? value.getClass().getSimpleName()
				.equalsIgnoreCase(Constants.DOUBLE)
				|| value.getClass().getSimpleName()
						.equalsIgnoreCase(Constants.LONG)
				|| value.getClass().getSimpleName()
						.equalsIgnoreCase(Constants.BOOLEAN) : false;
	}
	
	public static boolean isArrayType(Object value) {
	
		return value != null ? value.getClass().isArray() : false;
	}
	
	public static boolean isValueTypeOfString(Object value) {
	
		return value != null ? value.getClass().getSimpleName()
				.equalsIgnoreCase(Constants.STRING) : false;
	}
	
	public static boolean isPrimitiveClassType(String string) {
	
		if (string.equalsIgnoreCase(Constants.DOUBLE)
				|| string.equalsIgnoreCase(Constants.LONG)
				|| string.equalsIgnoreCase(Constants.STRING)
				|| string.equalsIgnoreCase(Constants.BOOLEAN)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Methode zum Zusammensetzen eines Query-String. Dabei wird der String in
	 * das korrekte UTF8-Format umgewandelt.
	 * 
	 * @param queryString der umzuwandelnde String
	 * @param type der EntityType
	 * @param orderBy das SQL-OrderBy
	 * @param offset das Offset, das für die Query gesetzt werden soll
	 * @param size die Size, die für die Query gesetzt werden soll
	 * @param appName der AppName
	 * @return den umgewandelten QueryString
	 * @throws Exception wird bei Fehlern geworfen
	 */
	public static String setURLWithQueryStringParams(String queryString,
			Long type, String orderBy, Integer offset, Integer size,
			String appName) throws Exception {
	
		String temp = "";
		
		queryString = urlUTF8Formatter(queryString);
		
		temp = "?query=" + queryString + "&" + Constants.ENTITY_ENTITYTYPE
				+ "=" + type + "&orderby="
				+ URLEncoder.encode(orderBy, "UTF-8") + "&offset=" + offset
				+ "&size=" + size;
		
		return Constants.BACKEND_URI + URLEncoder.encode(appName, "UTF-8")
				+ "/entities" + temp;
		
	}
	
	/**
	 * Methode zum Umwandeln eines übergebenen Strings in das URL-UTF8-Format
	 * wird für die Backend-Query-Methoden benötigt WICHTIG: die Zeichen ( ) - .
	 * werden nicht umgewandelt, sondern im ReturnString übernommen
	 * 
	 * @param theStringToFormat der zu formatierende String
	 * @return der formatierte String
	 */
	public static String urlUTF8Formatter(String theStringToFormat) {
	
		theStringToFormat = theStringToFormat.replace("%", "%25");
		theStringToFormat = theStringToFormat.replace(" ", "%20");
		theStringToFormat = theStringToFormat.replace("!", "%21");
		theStringToFormat = theStringToFormat.replace("\"", "%22");
		theStringToFormat = theStringToFormat.replace("#", "%23");
		theStringToFormat = theStringToFormat.replace("$", "%24");
		theStringToFormat = theStringToFormat.replace("&", "%26");
		theStringToFormat = theStringToFormat.replace("'", "%27");
		// queryString = queryString.replace("(", "%28");
		// queryString = queryString.replace(")", "%29");
		
		theStringToFormat = theStringToFormat.replace("*", "%2A");
		theStringToFormat = theStringToFormat.replace("+", "%2B");
		theStringToFormat = theStringToFormat.replace(",", "%2C");
		// theStringToFormat = theStringToFormat.replace("-", "%2D");
		// theStringToFormat = theStringToFormat.replace(".", "%2E");
		theStringToFormat = theStringToFormat.replace("/", "%2F");
		
		theStringToFormat = theStringToFormat.replace(":", "%3A");
		theStringToFormat = theStringToFormat.replace(";", "%3B");
		theStringToFormat = theStringToFormat.replace("<", "%3C");
		theStringToFormat = theStringToFormat.replace("=", "%3D");
		theStringToFormat = theStringToFormat.replace(">", "%3E");
		theStringToFormat = theStringToFormat.replace("?", "%3F");
		theStringToFormat = theStringToFormat.replace("@", "%40");
		
		theStringToFormat = theStringToFormat.replace("ü", "%C3%BC");
		theStringToFormat = theStringToFormat.replace("Ü", "%C3%9C");
		
		theStringToFormat = theStringToFormat.replace("ä", "%C3%A4");
		theStringToFormat = theStringToFormat.replace("Ä", "%C3%84");
		
		theStringToFormat = theStringToFormat.replace("ö", "%C3%B6");
		theStringToFormat = theStringToFormat.replace("Ö", "%C3%96");
		
		theStringToFormat = theStringToFormat.replace("§", "%C2%A7");
		
		return theStringToFormat;
	}
	
	/**
	 * überprüft, ob die übergebenen Properties die originalen Key-Strings
	 * verwenden
	 * 
	 * @param entity die Entity, die ersetzt werden soll, oder deren Properties
	 *            ersetzt werden sollen
	 * @param theCheckProperties die neuen Properties
	 * @return true, wenn die neuen Properties ok sind, sonst false
	 */
	public static <T extends Entity> boolean checkEntityProperties(T entity,
			HashMap<String, Object> theCheckProperties) {
	
		for (String key : theCheckProperties.keySet()) {
			if (entity.getEntityPropertyDatatypes().containsKey(key)) {
				String dataTypeNewPropsValue = theCheckProperties.get(key)
						.getClass().getSimpleName();
				String dataTypeEntityValue = entity
						.getEntityPropertyDatatypes().get(key);
				
				if (dataTypeEntityValue.equalsIgnoreCase(dataTypeNewPropsValue)) {
					
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isStatusCode200(int number) {
	
		return (number == 200);
	}
	
	public static boolean isStatusCode204(int number) {
	
		return (number == 204);
	}
	
	public static boolean isStatusCode201(int number) {
	
		return (number == 201);
	}
	
	public static boolean isStatusCode500(int number) {
	
		return (number == 500);
	}
	
	public static boolean isStatusCode403Or404(int number) {
	
		return (number == 403 || number == 404);
	}
	
	public static boolean isStatusCodeBetween200And300(int number) {
	
		return (number >= 200 && number < 300);
	}
}
