
package de.fhws.sdk.orca.helper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;

import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.model.EntityPage;
import de.fhws.sdk.orca.model.Link;

/**
 * Diese Klasse hat mehrere statische Methoden, die für die Umwandlung von
 * JSON-Objekten verwendet wird.
 * 
 * @author ThomasDeinlein
 */
public class JSONHelper {
	
	/**
	 * Methode zum Abfragen der TotalSize einer EntityPage
	 * 
	 * @param entityPageString der JSON-String der EntityPage
	 * @return die TotalSize
	 * @throws JSONException wird geworfen, falls während der JSON-Konvertierung
	 *             ein Fehler auftritt
	 */
	public static int getEntityPageTotalSize(String entityPageString)
			throws JSONException {
	
		JSONObject temp = new JSONObject(entityPageString);
		int ret = temp.getInt(Constants.ENTITYPAGE_TOTAL_SIZE);
		return ret;
	}
	
	/**
	 * Methode zum Abfragen der PageSize einer EntityPage
	 * 
	 * @param entity PageString der JSON-String der EntityPage
	 * @return die PageSize
	 * @throws JSONException wird geworfen, falls während der JSON-Konvertierung
	 *             ein Fehler auftritt
	 */
	public static int getEntityPagePageSize(String entityPageString)
			throws JSONException {
	
		JSONObject temp = new JSONObject(entityPageString);
		int ret = temp.getInt(Constants.ENTITYPAGE_PAGE_SIZE);
		return ret;
	}
	
	/**
	 * Methode, die innerhalb des SDK verwendet wird, um das Format der
	 * Entity-Properties vor dem Post/Put ans Backend richtig zu setzen, damit
	 * es die Properties speichern kann.
	 * 
	 * @param newProperties die EntityProperties, die richtig formatiert werden
	 *            sollen
	 * @throws Exception wird bei Konvertierungsfehlern geworfen
	 */
	public static void setPropsToCorrectFormatBeforeSendToBackend(
			HashMap<String, Object> newProperties) throws Exception {
	
		iterateOverNewPropertiesAndSetCorrectDateAndDoubleArrayFormats(newProperties);
	}
	
	private static void iterateOverNewPropertiesAndSetCorrectDateAndDoubleArrayFormats(
			HashMap<String, Object> newProperties) throws Exception {
	
		HashMap<String, Object> map = newProperties;
		Iterator<Map.Entry<String, Object>> iterator = map.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			
			if (isEntryValueNotNullAndEntryValueEqualsDate(entry)) {
				
				setCorrectDateFormatInNewProperties(newProperties, entry, key);
			}
			if (isEntryValueNotNullAndEntryValueEqualsDoublearrayOrArraylist(entry)) {
				setCorrectDoubleArrayFormatInNewPropertiesAndThrowExceptionIfArrayHasNotTheLengthTwo(entry);
			}
		}
	}
	
	private static void setCorrectDoubleArrayFormatInNewPropertiesAndThrowExceptionIfArrayHasNotTheLengthTwo(
			Map.Entry<String, Object> entry) throws Exception {
	
		Double[] array = (Double[]) entry.getValue();
		if (array.length != 2) {
			throw new Exception("Error - the Property " + entry.getKey()
					+ " is a Double-Array with invalid size (size must be 2)!");
		}
	}
	
	private static boolean isEntryValueNotNullAndEntryValueEqualsDoublearrayOrArraylist(
			Map.Entry<String, Object> entry) {
	
		return entry.getValue() != null
				&& (entry.getValue().getClass().getSimpleName()
						.equalsIgnoreCase(Constants.DOUBLEARRAY) || entry
						.getValue().getClass().getSimpleName()
						.contains(Constants.ARRAYLIST));
	}
	
	private static void setCorrectDateFormatInNewProperties(
			HashMap<String, Object> newProperties,
			Map.Entry<String, Object> entry, String key) {
	
		String newDate = DateHelper.dateToString((Date) entry.getValue());
		HashMap<String, String> temp = new HashMap<String, String>();
		temp.put(Constants.VALUE, newDate);
		temp.put(Constants.TYPE, Constants.DATE);
		newProperties.put(key, temp);
	}
	
	private static boolean isEntryValueNotNullAndEntryValueEqualsDate(
			Map.Entry<String, Object> entry) {
	
		return entry.getValue() != null
				&& entry.getValue().getClass().getSimpleName()
						.equalsIgnoreCase(Constants.DATE);
	}
	
	/**
	 * Diese Methode wandelt die Properties, die vom Backend abgefragt wurden,
	 * in das richtige Format zurück.
	 * 
	 * @param properties die Properties, die richtig konvertiert werden sollen
	 * @throws ParseException wird bei JSON-Fehlern geworfen
	 */
	public static void setPropertiesToCorrectDatatypesFromResponseEntity(
			HashMap<String, Object> properties) throws ParseException {
	
		iterateOverPropertiesAndSetCorrectDateAndDoubleArrayFormat(properties);
	}
	
	private static void iterateOverPropertiesAndSetCorrectDateAndDoubleArrayFormat(
			HashMap<String, Object> properties) throws ParseException {
	
		HashMap<String, Object> map = properties;
		Iterator<Map.Entry<String, Object>> iterator = map.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			
			if (isPropertiesNotNull(properties, key)) {
				if (isEntryValueHashMap(entry)) {
					
					@SuppressWarnings("rawtypes")
					HashMap check = (HashMap) entry.getValue();
					
					if (isCheckHashMapFilledWithDateValue(check)) {
						
						setCorrectDateFormatAndPutToProperties(properties, key,
								check);
					}
				}
				if (isEntryValueArrayList(entry)) {
					
					@SuppressWarnings("rawtypes")
					ArrayList list = (ArrayList) entry.getValue();
					
					if (isListSizeEqualsTwo(list)) {
						if (isListIndexZeroDoubleType(list)) {
							
							setCorrectDoubleArrayFormatAndPutToProperties(
									properties, key);
						}
					}
				}
				
			}
		}
	}
	
	private static void setCorrectDoubleArrayFormatAndPutToProperties(
			HashMap<String, Object> properties, String key) {
	
		@SuppressWarnings("unchecked")
		ArrayList<Double> arrayList = (ArrayList<Double>) properties.get(key);
		Double[] doubleArray = new Double[arrayList.size()];
		doubleArray[0] = arrayList.get(0);
		doubleArray[1] = arrayList.get(1);
		properties.put(key, doubleArray);
	}
	
	private static boolean isListIndexZeroDoubleType(
			@SuppressWarnings("rawtypes") ArrayList list) {
	
		return list.get(0).getClass().equals(Double.class);
	}
	
	private static boolean isListSizeEqualsTwo(
			@SuppressWarnings("rawtypes") ArrayList list) {
	
		return list.size() == 2;
	}
	
	private static boolean isEntryValueArrayList(Map.Entry<String, Object> entry) {
	
		return entry.getValue().getClass().equals(ArrayList.class);
	}
	
	@SuppressWarnings("rawtypes")
	private static void setCorrectDateFormatAndPutToProperties(
			HashMap<String, Object> properties, String key, HashMap check)
			throws ParseException {
	
		String dateValue = (String) check.get("value");
		Date date = DateHelper.stringToDate(dateValue);
		properties.put(key, date);
	}
	
	@SuppressWarnings("rawtypes")
	private static boolean isCheckHashMapFilledWithDateValue(HashMap check) {
	
		return check.containsKey("value") && (check.containsKey("type"));
	}
	
	private static boolean isEntryValueHashMap(Map.Entry<String, Object> entry) {
	
		return entry.getValue().getClass().equals(HashMap.class);
	}
	
	private static boolean isPropertiesNotNull(
			HashMap<String, Object> properties, String key) {
	
		return properties.get(key) != null;
	}
	
	/**
	 * Wandelt den übergebenen JSONString in ein Entity-Page-Objekt um. ACHTUNG:
	 * Aus dem JSON-String werden sämtliche Backslashes entfernt. Sollte der
	 * ursprüngliche JSON-String Backslashes enthalten, kann die Umwandlung
	 * nicht erfolgen, was zu einer Exception führt.
	 * 
	 * @param responseJSONString der JSON-String
	 * @param classType Klassen diesen Typs sind in der EntityPage enthalten
	 * @param type der Long-Entity-Type
	 * @return das Entity-Page-Objekt
	 * @throws JSONException wird bei JSON-Fehlern geworfen
	 * @throws ParseException wird bei Genson-Fehlern geworfen
	 */
	public static <T extends Entity> EntityPage<T> createEntityPageFromResponsJSONString(
			String responseJSONString, Class<? extends Entity> classType,
			long type) throws JSONException, ParseException, Exception {
	
		EntityPage<T> retValue = new EntityPage<T>();
		if (stringContainsNotBackslashes(responseJSONString)) {
			
			transformJSONStringToEntityPage(responseJSONString, classType,
					type, retValue);
		}
		else {
			throwExceptionBecauseJSONStringContainsBackslashes();
		}
		return retValue;
	}
	
	private static void throwExceptionBecauseJSONStringContainsBackslashes()
			throws Exception {
	
		throw new Exception(
				"Error - Creation of EntityPage not possible, because the JSON-String contains Backslashes, which will be removed while transformation.");
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends Entity> void transformJSONStringToEntityPage(
			String responseJSONString, Class<? extends Entity> classType,
			long type, EntityPage<T> retValue) throws JSONException,
			ParseException {
	
		Genson genson = new Genson();
		JSONObject whole = new JSONObject(responseJSONString);
		JSONArray entities = whole.getJSONArray(Constants.ENTITY_PAGE_ENTITIES);
		JSONObject navigationLinks = whole
				.getJSONObject(Constants.ENTITYPAGE_NAVIGATIONLINKS);
		int pageSize = whole.getInt(Constants.ENTITYPAGE_PAGE_SIZE);
		int totalSize = whole.getInt(Constants.ENTITYPAGE_TOTAL_SIZE);
		
		HashMap<String, String> theNavigationLinks = genson.deserialize(
				navigationLinks.toString(),
				new GenericType<HashMap<String, String>>() {
				});
		
		ArrayList<T> array = new ArrayList<T>();
		for (int i = 0; i < entities.length(); i++) {
			
			Genson json = new Genson();
			String string = entities.getJSONObject(i).toString();
			string = string.replace("\\", "");
			T temp = (T) json.deserialize(string, classType);
			
			if (temp.getType() != type) {
				throw new IllegalStateException("Error, attached type = "
						+ type + ", loaded entityType = " + temp.getType());
			}
			else {
				JSONHelper
						.setPropertiesToCorrectDatatypesFromResponseEntity(temp
								.getProperties());
				for (Link link : temp.getLinks()) {
					
					JSONHelper
							.setPropertiesToCorrectDatatypesFromResponseEntity(link
									.getProperties());
				}
				array.add(temp);
			}
		}
		
		retValue.setEntities(array);
		retValue.setNavigationLinks(theNavigationLinks);
		retValue.setPageSize(pageSize);
		retValue.setTotalSize(totalSize);
	}
	
	private static boolean stringContainsNotBackslashes(
			String responseJSONString) {
	
		return !responseJSONString.contains("\\");
	}
}
