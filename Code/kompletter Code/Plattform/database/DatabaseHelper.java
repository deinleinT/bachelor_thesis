
package de.fhws.sdk.orca.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.model.Entity;

/**
 * Klasse mit einer Vielzahl von statischen Hilfsmethoden für die Umwandlung von
 * Entities in das korrekte Datenbankformat.
 * 
 * @author ThomasDeinlein
 */
public class DatabaseHelper {
	
	/**
	 * erstellt den ganzen SQL-Create-Table-Befehl anhand des übergebenen
	 * Entity-Objekts, ohne Autoincrement der ID
	 * 
	 * @param entity die Entity
	 * @return
	 */
	public static <T extends Entity> String createEntityTableCreateString(
			T entity) {
	
		String retValue = "";
		String tableName = createFromEntityTableName(entity);
		String entityPropertyString = createEntityPropertyString(entity,
				createStringWithIdAndTypeAndSelfAndAutoIncrement());
		
		retValue = "CREATE TABLE IF NOT EXISTS " + tableName + "("
				+ entityPropertyString + ")";
		
		return retValue;
	}
	
	/**
	 * erstellt den ganzen SQL-Create-Table-Befehl anhand des übergebenen
	 * Entity-Objekts.
	 * 
	 * @param entity die Entity, für die String erstellt werden soll
	 * @return der Create-Table-Befehl als String
	 */
	public static <T extends Entity> String createEntityTableCreateStringWithAutoincrement(
			T entity) {
	
		String retValue = "";
		String tableName = createFromEntityTableName(entity);
		String entityPropertyString = createEntityPropertyString(entity,
				createStringWithIdAndTypeAndSelfAndAutoIncrement());
		
		retValue = "CREATE TABLE IF NOT EXISTS " + tableName + "("
				+ entityPropertyString + ")";
		
		return retValue;
	}
	
	/**
	 * Erstellt den ganzen SQL-Create-Table-Befehl für die
	 * Entity-Datatypes-Tabelle anhand der übergebenen Entity.
	 * 
	 * @param entity die Entity, für die String erstellt werden soll
	 * @return den SQL-Create-Befehl als String
	 */
	public static <T extends Entity> String createEntityPropertyDatatypesTableCreateString(
			T entity) {
	
		String retValue = "";
		String entityDatatypesTableName = createEntityDatatypesTableName(entity);
		String entityPropertyString = createEntityPropertyDatatypesString(entity);
		
		retValue = "CREATE TABLE IF NOT EXISTS " + entityDatatypesTableName
				+ "(" + entityPropertyString + ")";
		
		return retValue;
	}
	
	/**
	 * Erstellt den ganzen SQL-Create-Table-Befehl für die
	 * Link-Datatypes-Tabelle anhand der übergebenen Entity.
	 * 
	 * @param entity die Entity, für die String erstellt werden soll
	 * @return den SQL-Create-Befehl als String
	 */
	public static <T extends Entity> String createLinkPropertyDatatypesTableCreateString(
			T entity) {
	
		String retValue = "";
		String linkDatatypesTableName = createLinkDatatypesTableName(entity);
		String linkPropertyString = createLinkPropertyDatatypesString(entity);
		
		retValue = "CREATE TABLE IF NOT EXISTS " + linkDatatypesTableName
				+ "(" + linkPropertyString + ")";
		
		return retValue;
	}
	
	/**
	 * für die Erstellung des Tabellennamens, dazu werden Package-Name und
	 * Entity-Name zusammengefügt. Als Trennzeichen werden alle Punkte durch
	 * einen Unterstrich ersetzt, alle Buchstaben werden klein geschrieben.
	 * 
	 * @param entity die Entity, für die String erstellt werden soll
	 * @return den Entity-Table-Namen als String
	 */
	public static <T extends Entity> String createFromEntityTableName(T entity) {
	
		String tableName = entity.getClass().getPackage().getName() + "."
				+ entity.getClass().getSimpleName();
		tableName = tableName.toLowerCase().replace(".", "_");
		return tableName;
	}
	
	/**
	 * für die Erstellung des Tabellennamens der EntityPropertyDatatypes, dazu
	 * werden Package-Name, Entity-Name und "datatypes" zusammengefügt. Als
	 * Trennzeichen werden alle Punkte durch einen Unterstrich ersetzt, alle
	 * Buchstaben werden klein geschrieben.
	 * 
	 * @param entity die Entity, für die String erstellt werden soll
	 * @return den Entity-Table-Datatypes-Namen als String
	 */
	public static <T extends Entity> String createEntityDatatypesTableName(
			T entity) {
	
		String tableName = entity.getClass().getPackage().getName() + "."
				+ entity.getClass().getSimpleName() + "_datatypes";
		tableName = tableName.toLowerCase().replace(".", "_");
		return tableName;
	}
	
	/**
	 * für die Erstellung des Tabellennamens der LinkPropertyDatatypes, dazu
	 * werden Package-Name, Entity-Name und "_link_datatypes" zusammengefügt.
	 * Als Trennzeichen werden alle Punkte durch einen Unterstrich ersetzt, alle
	 * Buchstaben werden klein geschrieben.
	 * 
	 * @param entity die Entity, für die String erstellt werden soll
	 * @return den Entity-Table-Datatypes-Namen als String
	 */
	public static <T extends Entity> String createLinkDatatypesTableName(
			T entity) {
	
		String tableName = entity.getClass().getPackage().getName() + "."
				+ entity.getClass().getSimpleName() + "_link" + "_datatypes";
		tableName = tableName.toLowerCase().replace(".", "_");
		return tableName;
	}
	
	/**
	 * erstellt den ganzen SQL-Create-Table-Befehl für die LinkTabelle anhand
	 * des übergebenen Entity-Objekts.
	 * 
	 * @param entity die Entity, für die String erstellt werden soll
	 * @return der Create-Table-Befehl für die LinkTabelle als String
	 */
	public static <T extends Entity> String createLinkTableCreateString(T entity) {
	
		String retValue = "";
		String tableName = "";
		String linkPropertyString = "";
		tableName = createFromEntityTableName(entity) + "_link";
		linkPropertyString = createLinkPropertyString(entity);
		tableName = tableName.toLowerCase().replace(".", "_");
		
		retValue = "CREATE TABLE IF NOT EXISTS " + tableName + "("
				+ linkPropertyString + ")";
		
		return retValue;
	}
	
	/**
	 * Sortiert die Keys der übergebenen HashMap alphabetisch und gibt diese als
	 * ArrayList zurück.
	 * 
	 * @param theMap HashMap, deren Keys alphabetisch sortiert werden
	 * @return ArrayList mit den sortierten Keys
	 */
	public static ArrayList<String> createSortedKeysList(
			HashMap<String, String> theMap) {
	
		HashMap<String, String> entityProperties = theMap;
		
		Set<String> propertyKeys = entityProperties.keySet();
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(propertyKeys);
		Collections.sort(list, new Comparator<String>() {
			
			@Override
			public int compare(String f1, String f2) {
			
				return f1.toString().compareTo(f2.toString());
			}
		});
		return list;
	}
	
	/**
	 * Hilfsmethode zum erzeugen des Create-Table-Strings für die übergebenen
	 * Entity. Alle Properties werden mit den korrekten Datentypen
	 * berücksichtigt.
	 * 
	 * @param entity die Entity, für die die Tabelle erstellt werden soll
	 * @return den Create-Table-String
	 */
	private static <T extends Entity> String createEntityPropertyString(
			T entity, String autoincrement) {
	
		HashMap<String, String> entityProperties = entity
				.getEntityPropertyDatatypes();
		
		for (String imageKey : entity.getImagePropertyNames()) {
			entityProperties.put(imageKey, "image");
		}
		
		ArrayList<String> listWithSortedKeys = createSortedKeysList(entityProperties);
		
		String retValue = autoincrement;
		int counter = 0;
		
		for (String key : listWithSortedKeys) {
			
			String value = entity.getEntityPropertyDatatypes().get(key);
			
			if (counter > 0) {
				retValue += ",";
			}
			
			switch (value) {
			
				case ("long") :
					retValue = retValue + createLongEntityString(key);
					break;
				
				case ("double") :
					retValue = retValue + createDoubleEntityString(key);
					break;
				
				case ("double[]") :
					retValue = retValue + createDoubleArrayEntityString(key);
					break;
				
				case ("string") :
					retValue = retValue + createStringEntityString(key);
					break;
				
				case ("boolean") :
					retValue = retValue + createBooleanEntityString(key);
					break;
				
				case ("date") :
					retValue = retValue + createDateEntityString(key);
					break;
				
				case ("image") :
					retValue = retValue + createImageEntityString(key);
					break;
			}
			counter++;
		}
		
		return retValue;
	}
	
	private static String createStringWithIdAndTypeAndSelfAndAutoIncrement() {
	
		return "id INTEGER PRIMARY KEY AUTOINCREMENT, type INTEGER, self TEXT,";
	}
	
	/**
	 * Hilfsmethode zum erzeugen des Create-Table-Strings für die
	 * DATATYPES-TABELLE des übergebenen Entity.
	 * 
	 * @param entity die Entity, für die die Tabelle erstellt werden soll
	 * @return den Create-Table-String der Entity-Datatypes-Tabelle
	 */
	private static <T extends Entity> String createEntityPropertyDatatypesString(
			T entity) {
	
		HashMap<String, String> entityProperties = entity
				.getEntityPropertyDatatypes();
		
		for (String imageKey : entity.getImagePropertyNames()) {
			entityProperties.put(imageKey, Constants.IMAGE);
		}
		
		ArrayList<String> listWithSortedKeys = createSortedKeysList(entityProperties);
		
		String retValue = "id TEXT PRIMARY KEY, type TEXT, self TEXT,";
		int counter = 0;
		
		for (String key : listWithSortedKeys) {
			
			if (counter > 0) {
				retValue += ",";
			}
			retValue += "" + key + " TEXT";
			counter++;
		}
		
		return retValue;
	}
	
	/**
	 * Hilfsmethode zum erzeugen des Create-Table-Strings für die LinkTabelle
	 * des übergebenen Entity. Alle LinkProperties werden mit den korrekten
	 * Datentypen berücksichtigt.
	 * 
	 * @param entity die Entity, für die die Tabelle erstellt werden soll
	 * @return den Create-Table-String
	 */
	private static <T extends Entity> String createLinkPropertyString(T entity) {
	
		ArrayList<String> linkKeys = createSortedKeysList(entity
				.getLinkPropertyDatatypes());
		String retValue = "entity_id INTEGER, object TEXT PRIMARY KEY, type TEXT, ";
		
		for (String key : linkKeys) {
			
			retValue = setRetValueLinks(entity, retValue, key);
		}
		
		retValue = retValue + "FOREIGN KEY (entity_id) REFERENCES "
				+ createFromEntityTableName(entity) + "(id) ON DELETE CASCADE";
		
		return retValue;
	}
	
	private static <T extends Entity> String setRetValueLinks(T entity,
			String retValue, String key) {
	
		String value = entity.getLinkPropertyDatatypes().get(key);
		
		switch (value) {
		
			case ("long") :
				retValue = retValue
						+ createLongEntityString(key.replace(" ", "_"));
				break;
			
			case ("double") :
				retValue = retValue
						+ createDoubleEntityString(key.replace(" ", "_"));
				break;
			
			case ("double[]") :
				retValue = retValue
						+ createDoubleArrayEntityString(key.replace(" ", "_"));
				break;
			
			case ("string") :
				retValue = retValue
						+ createStringEntityString(key.replace(" ", "_"));
				break;
			
			case ("boolean") :
				retValue = retValue
						+ createBooleanEntityString(key.replace(" ", "_"));
				break;
			
			case ("date") :
				retValue = retValue
						+ createDateEntityString(key.replace(" ", "_"));
				break;
		}
		retValue += ",";
		return retValue;
	}
	
	private static <T extends Entity> String createLinkPropertyDatatypesString(
			T entity) {
	
		ArrayList<String> linkKeys = createSortedKeysList(entity
				.getLinkPropertyDatatypes());
		String retValue = "entity_id TEXT, object TEXT PRIMARY KEY, type TEXT, ";
		int counter = 0;
		
		for (String key : linkKeys) {
			
			if (counter > 0) {
				retValue += ",";
			}
			retValue += "" + key.replace(" ", "_") + " TEXT";
			
			counter++;
		}
		
		return retValue;
	}
	
	private static String createDateEntityString(String key) {
	
		return " " + key + " TEXT";
	}
	
	private static String createBooleanEntityString(String key) {
	
		return " " + key + " NUMERIC";
	}
	
	private static String createStringEntityString(String key) {
	
		return " " + key + " TEXT";
	}
	
	private static String createDoubleArrayEntityString(String key) {
	
		return " " + key + "_A REAL, " + key + "_B REAL";
	}
	
	private static String createDoubleEntityString(String key) {
	
		return " " + key + " REAL";
	}
	
	private static String createLongEntityString(String key) {
	
		return " " + key + " INTEGER";
	}
	
	private static String createImageEntityString(String key) {
	
		return " " + key + " TEXT";
	}
	
}
