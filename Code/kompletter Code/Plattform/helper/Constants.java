
package de.fhws.sdk.orca.helper;

/**
 * Klasse mit allen statischen Konstanten.
 * 
 * @author ThomasDeinlein
 */
public class Constants {
	
	public static final String	ENTITYPAGE_NAVIGATIONLINKS					= "navigationLinks";
	public static final String	ENTITYPAGE_NAVIGATIONLINKS_NEXT				= "next";
	public static final String	ENTITYPAGE_NAVIGATIONLINKS_SELF				= "self";
	public static final String	ENTITYPAGE_NAVIGATIONLINKS_PREV				= "prev";
	public static final String	ENTITYPAGE_PAGE_SIZE						= "pageSize";
	public static final String	ENTITYPAGE_TOTAL_SIZE						= "totalSize";
	
	public static final String	ENTITY_ENTITYID								= "id";
	public static final String	ENTITY_SELF_URL								= "self";
	public static final String	ENTITY_ENTITYTYPE							= "type";
	public static final String	ENTITY_PROPERTIES							= "properties";
	public static final String	ENTITY_LINKS_NAME							= "links";
	public static final String	ENTITY_PAGE_ENTITIES						= "entities";
	public static final String	ENTITY_LINKS								= "links";
	public static final String	ENTITY_LINKS_OBJECT							= "object";
	public static final String	ENTITY_LINKS_PROPERTIES						= "properties";
	public static final String	ENTITY_LINKS_TYPE							= "type";
	
	public static final String	HEADER_LOCATION								= "location";
	public static final String	VALUE										= "value";
	public static final String	TYPE										= "type";
	public static final String	DATE_IN_PROPERTIES							= "date";
	public static final String	DATATYPES									= "datatypes";
	
	// Link-Constants
	public static final String	LINK_OBJECT									= "object";
	public static final String	LINK_PROPERTIES								= "properties";
	public static final String	LINK_TYPE									= "type";
	
	// LogCat
	public static final String	TAG_ORCASDK									= "orcasdk";
	public static final String	TAG_BACKEND									= "BACKEND";
	public static final String	TAG_DATABASE								= "DATABASE";
	
	// Datatypes
	public static final String	INTEGER										= "Integer";
	public static final String	LONG										= "Long";
	public static final String	LONG_LOWCASE								= "long";
	public static final String	DOUBLE										= "Double";
	public static final String	DOUBLE_LOWCASE								= "double";
	public static final String	DOUBLEARRAY									= "Double[]";
	public static final String	DOUBLEARRAY_LOWCASE							= "double[]";
	public static final String	STRING										= "String";
	public static final String	STRING_LOWCASE								= "string";
	public static final String	ARRAYLIST									= "ArrayList";
	public static final String	BOOLEAN										= "Boolean";
	public static final String	DATE										= "Date";
	public static final String	BOOLEAN_LOWCASE								= "boolean";
	public static final String	DATE_LOWCASE								= "date";
	
	// HTTP-Headers
	public static final String	CONTENT_TYPE								= "Content-type";
	public static final String	CONTENT_TYPE_VALUE_JSON						= "application/json";
	public static final String	CONTENT_TYPE_IMAGE							= "application/octet-stream";
	
	// NetworkConstants
	public static final String	BACKEND_URI									= "http://staging.applab.fhws.de:8080/gen/api/";
	public static final String	APIKEYHEADER								= "X-RiP-Apikey";
	public static final String	LOCATIONHEADER								= "Location";
	public static final String	UTF_STRING									= "UTF-8";
	public static final String	ENTITIES									= "entities";
	public static final String	LINKS										= "links";
	public static final String	OBJECTS										= "objects";
	public static final String	OBJECT										= "object";
	public static final String	PROPERTY									= "property";
	public static final String	IMAGE										= "image";
	public static final String	WIDTH										= "WIDTH";
	public static final String	HEIGHT										= "HEIGHT";
	public static final String	RADIUS										= "RADIUS";
	public static final String	BGCOLOR										= "BGCOLOR";
	
	// Error
	public static final String	NO_ERROR									= "no Error ";
	public static final String	POST_FAILED									= "httpPost failed";
	public static final String	NO_LINKS_FOUND								= "no Links found";
	public static final String	SAVING_OF_SEVERAL_ENTITIES_OK				= "Saving of following ObjectIds was successful: ";
	public static final String	ERROR_SAVING_ENTITIES						= "Error occured - Saving link to Object-Entity/ies ";
	public static final String	NO_IMAGE_LINK_AVAILABLE						= "image-link still not available";
	public static final String	NO_ERROR_WHILE_SAVE_BACKEND_AND_DATABASE	= "OK - Entity saved to Backend and LocalDatabase. ID is ";
	public static final String	UPDATE_SUCCESSFUL							= "Update successfull";
	public static final String	ERROR_DELETE_ENTITY_BY_ID					= "DELETE-Request not possible: entityId is 0, appName is null or empty, apiKey is null or empty";
	public static final String	ERROR_POST_LINKS_FROM_SAME_TYPE				= "Error occured - Saving link to Object-Entity/ies ";
	
}
