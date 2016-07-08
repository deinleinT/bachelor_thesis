
package de.fhws.sdk.orca.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Klasse, die eine Orca-Backend EntityPage als Java-Objekt repräsentiert. Von
 * der manuellen Erstellung ist zur Vermeidung von Fehlern abzuraten.
 * 
 * @author ThomasDeinlein
 * @param <T> muss vom Typ Entity sein
 */
public class EntityPage<T extends Entity> {
	
	private ArrayList<T>			entities;
	private HashMap<String, String>	navigationLinks;
	private int						pageSize;
	private int						totalSize;
	
	public EntityPage() {
	
		entities = new ArrayList<T>();
		navigationLinks = new HashMap<String, String>();
	}
	
	/**
	 * Getter für die NavigationLinks einer EntityPage
	 * 
	 * @return HashMap mit NavigationLinks
	 */
	public HashMap<String, String> getNavigationLinks() {
	
		return navigationLinks;
	}
	
	/**
	 * Von der manuellen Verwendung ist abzuraten!
	 * 
	 * @param navigationLinks
	 */
	public void setNavigationLinks(HashMap<String, String> navigationLinks) {
	
		this.navigationLinks = navigationLinks;
	}
	
	/**
	 * Getter für die Anzahl an Entities, die in dieser EntityPage enthalten
	 * sind
	 * 
	 * @return Anzahl der Entities
	 */
	public int getPageSize() {
	
		return pageSize;
	}
	
	/**
	 * Von der manuellen Verwendung ist abzuraten!
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
	
		this.pageSize = pageSize;
	}
	
	/**
	 * Getter für die Anzahl aller Entities, die es zu der vorausgegangenen
	 * Query im Backend gibt
	 * 
	 * @return
	 */
	public int getTotalSize() {
	
		return totalSize;
	}
	
	/**
	 * Von der manuellen Verwendung ist abzusehen!
	 * 
	 * @param totalSize
	 */
	public void setTotalSize(int totalSize) {
	
		this.totalSize = totalSize;
	}
	
	/**
	 * Getter für alle in dieser EntityPage enthaltenen Entities
	 * 
	 * @return ArrayList mit allen Entities
	 */
	public ArrayList<T> getEntities() {
	
		return entities;
	}
	
	/**
	 * @param entities
	 */
	public void setEntities(ArrayList<T> entities) {
	
		this.entities = entities;
	}
	
}
