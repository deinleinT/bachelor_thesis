
package de.fhws.sdk.orca.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import com.owlike.genson.annotation.JsonIgnore;

import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.helper.DateHelper;

/**
 * Zentrale Schnittstellen-Klasse für das Orca-SDK. Alle mit dem Generator
 * erzeugten Klassen sind von dieser Klasse abgeleitet. Alle generischen Klassen
 * erwarten ein Objekt dieser Klasse. Von dieser Klasse kann ein Objekt erzeugt
 * werden. Davon ist allerdings abzuraten, da nur durch die Erzeugung durch den
 * Generator sichergestellt wird, dass alle benötigten Variablen richtig gesetzt
 * werden.
 * 
 * @author ThomasDeinlein
 */
public class Entity {
	
	protected String					self;
	protected long						type;
	protected ArrayList<Link>			links;
	protected int						id;
	protected HashMap<String, Object>	properties;
	
	@JsonIgnore
	protected HashMap<String, String>	entityPropertyDatatypes;
	@JsonIgnore
	protected HashMap<String, String>	linkPropertyDatatypes;
	@JsonIgnore
	protected HashSet<String>			imagePropertyNames;
	
	public Entity() {
	
		properties = new HashMap<String, Object>();
		links = new ArrayList<Link>();
		entityPropertyDatatypes = new HashMap<String, String>();
		linkPropertyDatatypes = new HashMap<String, String>();
		imagePropertyNames = new HashSet<String>();
		
	}
	
	/**
	 * Getter-Methode für den Self-URL-String
	 * 
	 * @return SelfUrl
	 */
	public String getSelf() {
	
		return self;
	}
	
	/**
	 * Setter-Methode zum setzen der SelfURL. Von einer manuellen Verwendung ist
	 * zur Vermeidung von Fehlern abzusehen.
	 * 
	 * @param self der Self-URL-String
	 */
	public void setSelf(String self) {
	
		this.self = self;
		
	}
	
	/**
	 * Getter-Methode zum Abfragen des Entity-Type.
	 * 
	 * @return entityType
	 */
	public long getType() {
	
		return type;
	}
	
	/**
	 * Von der manuellen Verwendung dieser Methode ist abzuraten, denn alle
	 * Entities haben einen eindeutigen Type. Dieser wird über die DSL gesetzt.
	 * 
	 * @param type entityType
	 */
	public void setType(long type) {
	
		this.type = type;
		
	}
	
	/**
	 * Getter für die eindeutige Entity-ID.
	 * 
	 * @return die entityID
	 */
	public int getId() {
	
		return id;
	}
	
	/**
	 * Vom manuellen Setzen dieser Methode wird abgeraten. Die Id wird
	 * bestenfalls über das Backend vergeben. Dadurch wird sichergestellt, dass
	 * diese eindeutig ist.
	 * 
	 * @param id
	 */
	public void setId(int id) {
	
		this.id = id;
		
	}
	
	/**
	 * HashMap mit allen EntityProperties, die in der Unterklasse gesetzt
	 * werden.
	 * 
	 * @return die HashMap mit den Entity-Properties
	 */
	public HashMap<String, Object> getProperties() {
	
		return properties;
	}
	
	/**
	 * Vom manuellen Setzen der Properties wird abgeraten. Alle Properties
	 * werden über die Unterklassen und deren Methoden gesetzt. Manuelles Setzen
	 * kann zu Fehlern führen.
	 * 
	 * @param properties
	 */
	public void setProperties(HashMap<String, Object> properties) {
	
		this.properties = properties;
		
	}
	
	/**
	 * Getter für alle Links dieser Entity
	 * 
	 * @return ArrayList mit allen Links als Link-Objekt
	 */
	public ArrayList<Link> getLinks() {
	
		return links;
	}
	
	/**
	 * Vom manuellen Setzen dieser Klasse wird zur Vermeidung von Fehlern
	 * abgeraten.
	 * 
	 * @param links
	 */
	public void setLinks(ArrayList<Link> links) {
	
		this.links = links;
	}
	
	@JsonIgnore
	protected String getStringProperty(String propertyName,
			Class<String> classType) {
	
		if (getProperties().containsKey(propertyName)) {
			return classType.cast(getProperties().get(propertyName));
		}
		else {
			return null;
		}
	}
	
	@JsonIgnore
	protected Long getLongProperty(String propertyName, Class<Long> classType) {
	
		if (getProperties().containsKey(propertyName)) {
			return classType.cast(getProperties().get(propertyName));
		}
		else {
			return null;
		}
	}
	
	@JsonIgnore
	protected Double getDoubleProperty(String propertyName,
			Class<Double> classType) {
	
		if (getProperties().containsKey(propertyName)) {
			return classType.cast(getProperties().get(propertyName));
		}
		else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@JsonIgnore
	protected Date getDateProperty(String propertyName, Class<Date> classType) {
	
		Date date;
		if (getProperties().containsKey(propertyName)) {
			if (getProperties().get(propertyName).getClass().getSimpleName()
					.equalsIgnoreCase(Constants.DATE)) {
				return (Date) getProperties().get(propertyName);
			}
			HashMap<String, String> map = (HashMap<String, String>) getProperties()
					.get(propertyName);
			try {
				date = DateHelper.stringToDate(map.get("value"));
			}
			catch (ParseException e) {
				return null;
			}
			return date;
		}
		else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@JsonIgnore
	protected Double[] getDoubleArrayProperty(String propertyName,
			Class<Double[]> classType) {
	
		if (getProperties().containsKey(propertyName)) {
			if (getProperties().get(propertyName).getClass().getSimpleName()
					.equalsIgnoreCase("Double[]")) {
				return classType.cast(getProperties().get(propertyName));
			}
			ArrayList<Double> arrayList = (ArrayList<Double>) getProperties()
					.get(propertyName);
			Double[] doubleArray = new Double[arrayList.size()];
			doubleArray[0] = arrayList.get(0);
			doubleArray[1] = arrayList.get(1);
			
			return doubleArray;
		}
		else {
			return null;
		}
	}
	
	@JsonIgnore
	protected Boolean getBooleanProperty(String propertyName,
			Class<Boolean> classType) {
	
		if (getProperties().containsKey(propertyName)) {
			return classType.cast(getProperties().get(propertyName));
		}
		else {
			return null;
		}
	}
	
	/**
	 * Methode zum Setzen des Values des Property. ACHTUNG: Von der manuellen
	 * Verwendung ist zur Vermeidung von Fehlern abzusehen.
	 * 
	 * @param propertyName Name der Property
	 * @param value Wert, der dieser Property zugewiesen werden soll
	 */
	@JsonIgnore
	protected void setProperty(String propertyName, Object value) {
	
		if (value != null) {
			getProperties().put(propertyName, value);
		}
		else {
			getProperties().remove(propertyName);
		}
		
	}
	
	@JsonIgnore
	protected ArrayList<String> getLinksFromSameTypeAsStrings(String linkType) {
	
		ArrayList<String> retValue = new ArrayList<String>();
		
		if (getLinks() != null && getLinks().size() > 0) {
			for (Link link : getLinks()) {
				if (link.getType().equalsIgnoreCase(linkType)) {
					retValue.add(link.getObject());
				}
			}
		}
		
		return retValue;
	}
	
	/**
	 * Methode zum Abfragen der EntityPropertyDatatypes. Wird für die korrekte
	 * Funktionsweise der Datenbankfunktionen benötigt.
	 * 
	 * @return Hashmap mit den Datatypes aller Properties
	 */
	@JsonIgnore
	public HashMap<String, String> getEntityPropertyDatatypes() {
	
		return entityPropertyDatatypes;
	}
	
	/**
	 * Methode zum Abfragen der LinkPropertyDatatypes. Wird für die korrekte
	 * Funktionsweise der Datenbankfunktionen benötigt.
	 * 
	 * @return Hashmap mit den Datatypes aller LinkProperties
	 */
	@JsonIgnore
	public HashMap<String, String> getLinkPropertyDatatypes() {
	
		return linkPropertyDatatypes;
	}
	
	/**
	 * Methode zum Abfragen der ImagePropertKeys. Wird für die korrekte
	 * Funktionsweise der Datenbankfunktionen benötigt.
	 * 
	 * @return HashSet mit den Namen aller Images
	 */
	@JsonIgnore
	public HashSet<String> getImagePropertyNames() {
	
		return imagePropertyNames;
	}
	
}
