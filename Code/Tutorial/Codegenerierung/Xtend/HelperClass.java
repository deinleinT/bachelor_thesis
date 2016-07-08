package org.xtext.orcasdk.entitymodel.generator;

import java.util.HashMap;
import java.util.HashSet;

public class HelperClass {

	//zum Zwischenspeichern für EntityTypes
	public static HashMap<String,String> packageEntitiesTypes = new HashMap<String, String>();
	
	//zum Einfügen der spezifischen Imports
	public static HashMap<String, String> links = new HashMap<String,String>();
	
	//zum Speichern aller Imports, die für link-Methoden benötigt werden
	public static HashSet<String> linkimport = new HashSet<String>();
	
	//zum Speichern aller Imports, die für image-Methoden benötigt werden
	public static HashSet<String> imageimport = new HashSet<String>();
	
	//zum Kennzeichnen, wenn Images vorhanden sind
	public static HashMap<String, String> image = new HashMap<String,String>();

	static{
		
		linkimport.add("import de.fhws.sdk.orca.model.Link;\nimport de.fhws.sdk.orca.network.callback.IPostLinkCallback;\nimport de.fhws.sdk.orca.network.callback.IEntityPageWithEntityCallback;\n");
		
		imageimport.add("import java.io.InputStream;\n");
	}
	
	

}
