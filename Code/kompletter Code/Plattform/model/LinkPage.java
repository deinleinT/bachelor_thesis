
package de.fhws.sdk.orca.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Repräsentiert eine Orca-Backend LinkPage. Von der manuellen Erstellung ist
 * abzuraten!
 * 
 * @author ThomasDeinlein
 */
public class LinkPage {
	
	private HashMap<String, String>	navigationLinks;
	private Integer					pageSize;
	private Integer					totalSize;
	private ArrayList<Link>			links;
	
	public LinkPage() {
	
	}
	
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
	 * Getter für die in dieser LinkPage enthaltene Anzahl an Links
	 * 
	 * @return
	 */
	public Integer getPageSize() {
	
		return pageSize;
	}
	
	/**
	 * Von der manuellen Verwendung ist abzuraten!
	 * 
	 * @param pageSize
	 */
	public void setPageSize(Integer pageSize) {
	
		this.pageSize = pageSize;
	}
	
	/**
	 * Getter für die gesamte Anzahl an Links, die zu der Query, die dieser
	 * LinkPage vorausgeht, gehört
	 * 
	 * @return
	 */
	public Integer getTotalSize() {
	
		return totalSize;
	}
	
	/**
	 * Von der manuellen Verwendung ist abzuraten!
	 * 
	 * @param totalSize
	 */
	public void setTotalSize(Integer totalSize) {
	
		this.totalSize = totalSize;
	}
	
	/**
	 * Getter für alle Links dieser LinkPage
	 * 
	 * @return
	 */
	public ArrayList<Link> getLinks() {
	
		return links;
	}
	
	/**
	 * Von der manuellen Verwendung ist abzuraten!
	 * 
	 * @param linkList
	 */
	public void setLinks(ArrayList<Link> linkList) {
	
		this.links = linkList;
	}
	
}
