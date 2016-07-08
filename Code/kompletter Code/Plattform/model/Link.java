
package de.fhws.sdk.orca.model;

import java.util.HashMap;

/**
 * Repr�sentiert einen Orca-Backend-Link. Von der manuellen Verwendung ist
 * abzuraten!
 * 
 * @author ThomasDeinlein
 */
public class Link {
	
	private String					object;
	private HashMap<String, Object>	properties;
	private String					type;
	
	public Link() {
	
		properties = new HashMap<String, Object>();
	}
	
	/**
	 * Getter f�r den String, der die URL dieses Links repr�sentiert
	 * 
	 * @return
	 */
	public String getObject() {
	
		return object;
	}
	
	/**
	 * Von der manuellen Verwendung ist abzusehen!
	 * 
	 * @param object
	 */
	public void setObject(String object) {
	
		this.object = object;
	}
	
	/**
	 * Getter f�r die LinkProperties, die zu diesem Link geh�ren
	 * 
	 * @return HashMap mit den LinkProperties
	 */
	public HashMap<String, Object> getProperties() {
	
		return properties;
	}
	
	/**
	 * Von der manuellen Verwendung ist abzusehen!
	 * 
	 * @param properties
	 */
	public void setProperties(HashMap<String, Object> properties) {
	
		this.properties = properties;
	}
	
	/**
	 * Getter f�r den Link-Type
	 * 
	 * @return
	 */
	public String getType() {
	
		return type;
	}
	
	/**
	 * Von der manuellen Verwendung ist abzusehen!
	 * 
	 * @param type
	 */
	public void setType(String type) {
	
		this.type = type;
	}
	
}
