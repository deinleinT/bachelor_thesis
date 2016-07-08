
package de.fhws.sdk.orca.database;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.fhws.sdk.orca.AppConstants;
import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.helper.DateHelper;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.model.Link;

/**
 * Generische Klasse, über die alle Methoden zum Erstellen, Ändern, Laden und
 * Löschen von Entities aus/in der/die lokale/n Datenbank bereitgestellt werden.
 * Die onUpgrade-Methode wird nicht verwendet.
 * 
 * @author ThomasDeinlein
 * @param <T> generischer Parameter, es muss sich um eine Entity-Klasse oder
 *            davon abgeleitete Klasse handeln
 */
@SuppressLint({"DefaultLocale", "UseValueOf", "SimpleDateFormat"})
public class DatabaseHandler<T extends Entity> extends SQLiteOpenHelper {
	
	@SuppressWarnings("unused")
	private Context			context;
	private SQLiteDatabase	db;
	private String			createEntityTableString;
	private String			createLinkTableString;
	private String			linkDatatypesCreateTableString;
	private String			entityDatatypesCreateTableString;
	
	public DatabaseHandler(Context context) {
	
		super(context, AppConstants.APPNAME, null, 1);
		this.context = context;
		this.db = getWritableDatabase();
	}
	
	public DatabaseHandler(Context context, String createEntityTableString,
			String createLinkTableString,
			String entityDatatypesCreateTableString,
			String linkDatatypesCreateTableString) {
	
		super(context, AppConstants.APPNAME, null, 1);
		this.context = context;
		this.createEntityTableString = createEntityTableString;
		this.createLinkTableString = createLinkTableString;
		this.entityDatatypesCreateTableString = entityDatatypesCreateTableString;
		this.linkDatatypesCreateTableString = linkDatatypesCreateTableString;
		this.db = getWritableDatabase();
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
	
		createTablesForEntityAndLink(db);
	}
	
	/**
	 * Methode zum Abfragen aller Entities, die sich zum Zeitpunkt des Aufrufs
	 * in der lokalen Datenbank befinden. Es können nur Entities des selben
	 * ClassTypes abgerufen werden.
	 * 
	 * @param classType der ClassType der gefundenen Entities
	 * @param queryString der SQL-Query-String
	 * @return eine ArrayList mit den gefundenen Entities
	 * @throws Exception wird bei DB-Fehlern geworfen
	 */
	public ArrayList<T> loadAllEntities(Class<? extends Entity> classType)
			throws Exception {
	
		db = getWritableDatabase();
		String entityTableName = getEntityTableNameFromClassType(classType);
		String linkTableName = getLinkTableNameFromEntityTableName(entityTableName);
		String entityPropertyDatatypesTableName = getEntityPropertyDatatypesTableNameFromEntityTableName(entityTableName);
		String linkPropertyDatatypesTableName = getEntityPropertyDatatypesTableNameFromEntityTableName(linkTableName);
		HashMap<String, String> entityPropertyDatatypes = getEntityPropertyDatatypes(entityPropertyDatatypesTableName);
		HashMap<String, String> linkPropertyDatatypes = getLinkPropertyDatatypes(linkPropertyDatatypesTableName);
		ArrayList<T> returnValue = new ArrayList<T>();
		
		Cursor cursorEntity = createCursorEntity(entityTableName);
		
		iterateOverCursorEntity(linkTableName, entityPropertyDatatypes,
				linkPropertyDatatypes, returnValue, cursorEntity);
		
		return returnValue;
		
	}
	
	@SuppressWarnings("unchecked")
	private void iterateOverCursorEntity(String linkTableName,
			HashMap<String, String> entityPropertyDatatypes,
			HashMap<String, String> linkPropertyDatatypes,
			ArrayList<T> returnValue, Cursor cursorEntity)
			throws ParseException {
	
		do {
			int entityIdPosition = cursorEntity
					.getColumnIndex(Constants.ENTITY_ENTITYID);
			int entityId = cursorEntity.getInt(entityIdPosition);
			
			if (isLinkPropertyDatatypesNotNull(linkPropertyDatatypes)) {
				createCursorLinkAndCreateEntity(linkTableName,
						entityPropertyDatatypes, linkPropertyDatatypes,
						returnValue, cursorEntity, entityId);
			}
			else {
				Entity entity = createEntityFromCursors(
						entityPropertyDatatypes, linkPropertyDatatypes,
						cursorEntity, null, 0);
				returnValue.add((T) entity);
			}
			
		} while (cursorEntity.moveToNext());
	}
	
	@SuppressWarnings("unchecked")
	private void createCursorLinkAndCreateEntity(String linkTableName,
			HashMap<String, String> entityPropertyDatatypes,
			HashMap<String, String> linkPropertyDatatypes,
			ArrayList<T> returnValue, Cursor cursorEntity, int entityId)
			throws ParseException {
	
		// Cursor über die LinkTabelle
		String linkQueryString = "entity_id = " + entityId;
		
		Cursor cursorLink = db.query(linkTableName, null, linkQueryString,
				null, null, null, null);
		int countRowsLink = cursorLink.getCount();
		cursorLink.moveToFirst();
		//
		
		Entity entity = createEntityFromCursors(entityPropertyDatatypes,
				linkPropertyDatatypes, cursorEntity, cursorLink, countRowsLink);
		returnValue.add((T) entity);
	}
	
	private boolean isLinkPropertyDatatypesNotNull(
			HashMap<String, String> linkPropertyDatatypes) {
	
		return linkPropertyDatatypes != null
				&& linkPropertyDatatypes.keySet().size() > 0;
	}
	
	private Cursor createCursorEntity(String entityTableName) throws Exception {
	
		// Cursor für die EntityTabelle
		Cursor cursorEntity = db.query(entityTableName, null, null, null, null,
				null, null);
		int countRowsEntity = cursorEntity.getCount();
		
		throwExceptionIfCountRowsEntityIsZero(countRowsEntity);
		cursorEntity.moveToFirst();
		//
		return cursorEntity;
	}
	
	/**
	 * Methode zum Abfragen mehrerer Entities anhand einer SQLite-Query. Es
	 * können nur Entities des selben ClassTypes abgerufen werden.
	 * 
	 * @param classType der ClassType der gefundenen Entities
	 * @param queryString der SQL-Query-String
	 * @return eine ArrayList mit den gefundenen Entities
	 * @throws Exception wird bei DB-Fehlern geworfen
	 */
	public ArrayList<T> loadEntitiesByQuery(Class<? extends Entity> classType,
			String queryString) throws Exception {
	
		db = getWritableDatabase();
		String entityTableName = getEntityTableNameFromClassType(classType);
		String linkTableName = getLinkTableNameFromEntityTableName(entityTableName);
		String entityPropertyDatatypesTableName = getEntityPropertyDatatypesTableNameFromEntityTableName(entityTableName);
		String linkPropertyDatatypesTableName = getEntityPropertyDatatypesTableNameFromEntityTableName(linkTableName);
		HashMap<String, String> entityPropertyDatatypes = getEntityPropertyDatatypes(entityPropertyDatatypesTableName);
		HashMap<String, String> linkPropertyDatatypes = getLinkPropertyDatatypes(linkPropertyDatatypesTableName);
		ArrayList<T> returnValue = new ArrayList<T>();
		
		Cursor cursorEntity = createCursorEntity(queryString, entityTableName);
		
		iterateOverCursorEntity(linkTableName, entityPropertyDatatypes,
				linkPropertyDatatypes, returnValue, cursorEntity);
		
		return returnValue;
		
	}
	
	private Cursor createCursorEntity(String queryString, String entityTableName)
			throws Exception {
	
		// Cursor für die EntityTabelle
		Cursor cursorEntity = db.query(entityTableName, null, queryString,
				null, null, null, null);
		int countRowsEntity = cursorEntity.getCount();
		throwExceptionIfCountRowsEntityIsZero(countRowsEntity);
		cursorEntity.moveToFirst();
		//
		return cursorEntity;
	}
	
	private void throwExceptionIfCountRowsEntityIsZero(int countRowsEntity)
			throws Exception {
	
		if (countRowsEntity <= 0) {
			throw new Exception("Error - loading failed. Table is empty.");
		}
	}
	
	/**
	 * Methode zum Laden einer einzelnen Entity anhand der eindeutigen
	 * Entity-ID.
	 * 
	 * @param entityId die eindeutige Entity-ID
	 * @param classType der ClassType der zu ladenenden Entity
	 * @return die gefundene Entity
	 * @throws Exception wird bei DB-Fehlern geworfen
	 */
	@SuppressWarnings("unchecked")
	public T loadEntityById(int entityId, Class<? extends Entity> classType)
			throws Exception {
	
		db = getReadableDatabase();
		String entityTableName = getEntityTableNameFromClassType(classType);
		String linkTableName = getLinkTableNameFromEntityTableName(entityTableName);
		String entityPropertyDatatypesTableName = getEntityPropertyDatatypesTableNameFromEntityTableName(entityTableName);
		String linkPropertyDatatypesTableName = getEntityPropertyDatatypesTableNameFromEntityTableName(linkTableName);
		HashMap<String, String> entityPropertyDatatypes = getEntityPropertyDatatypes(entityPropertyDatatypesTableName);
		HashMap<String, String> linkPropertyDatatypes = getLinkPropertyDatatypes(linkPropertyDatatypesTableName);
		
		String entityQueryString = "id = " + entityId;
		String linkQueryString = "entity_id = " + entityId;
		
		Cursor cursorEntity = createCursorEntity(entityId, entityTableName,
				entityQueryString);
		
		// Cursor über die LinkTabelle
		Cursor cursorLink;
		int countRowsLink;
		try {
			cursorLink = db.query(linkTableName, null, linkQueryString, null,
					null, null, null);
			countRowsLink = cursorLink.getCount();
			cursorLink.moveToFirst();
		}
		catch (SQLException e) {
			cursorLink = null;
			countRowsLink = 0;
		}
		//
		
		// Entity erstellen
		Entity returnEntity = createEntityFromCursors(entityPropertyDatatypes,
				linkPropertyDatatypes, cursorEntity, cursorLink, countRowsLink);
		//
		
		db.close();
		return (T) returnEntity;
	}
	
	private Cursor createCursorEntity(int entityId, String entityTableName,
			String entityQueryString) throws Exception {
	
		// Cursor über die EntityTabelle
		Cursor cursorEntity = db.query(entityTableName, null,
				entityQueryString, null, null, null, null);
		int countRowsEntity = cursorEntity.getCount();
		throwExceptionIfCursorEntityHasMoreThanOneRows(entityId,
				countRowsEntity);
		cursorEntity.moveToFirst();
		//
		return cursorEntity;
	}
	
	/**
	 * Methode zum Löschen aus der lokalen Datenbank einer einzelnen Entity
	 * 
	 * @param entity die aus der DB gelöscht werden soll
	 * @return Anzahl der Rows, die in der Entity-Tabelle gelöscht wurden
	 * @throws Exception wird bei DB-Fehlern geworfen
	 */
	@SuppressWarnings("unused")
	public int deleteEntity(T entity) throws Exception {
	
		db = this.getWritableDatabase();
		
		throwExceptionIfEntityIdIsZero(entity);
		
		// für die Entity
		String entityTableName = DatabaseHelper
				.createFromEntityTableName(entity);
		int deleteEntity = db.delete(entityTableName, "id=" + entity.getId(),
				null);
		//
		
		// Links
		String linkTableName = getLinkTableNameFromEntityTableName(entityTableName);
		int deleteLinks = db.delete(linkTableName,
				"entity_id=" + entity.getId(), null);
		//
		
		return deleteEntity;
	}
	
	private void throwExceptionIfEntityIdIsZero(T entity) throws Exception {
	
		if (entity.getId() == 0) {
			throw new Exception(
					"EntityId is 0. It is not possible to save an Entity without a valid EntityId.");
		}
	}
	
	/**
	 * Methode zum Löschen aller zum Entity-ClassType gehörenden Tabellen.
	 * 
	 * @param classType der ClassType der Entity-Klasse, deren Tabellen gelöscht
	 *            werden sollen
	 */
	public void deleteTable(Class<? extends Entity> classType) {
	
		db = this.getWritableDatabase();
		String entityTableName = getEntityTableNameFromClassType(classType);
		String linkTableName = getLinkTableNameFromEntityTableName(entityTableName);
		String entityPropertyDatatypesTableName = getEntityPropertyDatatypesTableNameFromEntityTableName(entityTableName);
		String linkPropertyDatatypesTableName = getEntityPropertyDatatypesTableNameFromEntityTableName(linkTableName);
		
		try {
			db.delete(entityTableName, null, null);
			
		}
		catch (Exception e) {
		}
		try {
			
			db.delete(linkTableName, null, null);
			
		}
		catch (Exception e) {
		}
		try {
			
			db.delete(entityPropertyDatatypesTableName, null, null);
			
		}
		catch (Exception e) {
		}
		try {
			
			db.delete(linkPropertyDatatypesTableName, null, null);
		}
		catch (Exception e) {
		}
	}
	
	/**
	 * Methode zum Updaten der übergebenen Entity in der DB. Die Entity wurde
	 * vorher über die getter und setter entsprechend geändert. ACHTUNG: Die ID,
	 * der Type und der Self-Link bleiben unverändert. Auch die Links werden
	 * upgedatet. Die Links werden komplett überschrieben. Wenn die übergebene
	 * Entity keine Links mehr beinhaltet werden alle vorherigen Links gelöscht.
	 * 
	 * @param entity die Entity, die upgedatet werden soll
	 * @return anzahl der Rows, die in der Entity-Tabelle geändert wurden
	 * @throws Exception
	 */
	public int updateEntity(T entity) throws Exception {
	
		db = this.getWritableDatabase();
		
		throwExceptionIfEntityIdIsZero(entity);
		
		// ContentValues für Entity
		String entityTableName = DatabaseHelper
				.createFromEntityTableName(entity);
		ContentValues values = new ContentValues();
		HashMap<String, String> allKeys = entity.getEntityPropertyDatatypes();
		addImageKeysToAllKeys(entity, allKeys);
		
		ArrayList<String> allEntityPropertyKeys = DatabaseHelper
				.createSortedKeysList(allKeys);
		
		values = fillContentValuesWithEntityPropertiesForUpdates(
				allEntityPropertyKeys, values, entity);
		int entityCheck = db.update(entityTableName, values,
				"id=" + entity.getId(), null);
		// bis hier für die Entity
		
		updateAllEntityLinks(entity, entityTableName);
		
		db.close();
		return entityCheck;
	}
	
	private void updateAllEntityLinks(T entity, String entityTableName)
			throws Exception {
	
		// für die links
		String linkTableName = getLinkTableNameFromEntityTableName(entityTableName);
		try {
			
			db.delete(linkTableName, "entity_id=" + entity.getId(), null);
			ArrayList<String> correctLinkPropertyKeys = DatabaseHelper
					.createSortedKeysList(entity.getLinkPropertyDatatypes());
			
			loopOverAllEntityLinks(entity, linkTableName,
					correctLinkPropertyKeys);
		}
		catch (SQLException e) {
			
		}
		// Links Ende
	}
	
	private void addImageKeysToAllKeys(T entity, HashMap<String, String> allKeys) {
	
		for (String imageKey : entity.getImagePropertyNames()) {
			allKeys.put(imageKey, Constants.IMAGE);
		}
	}
	
	/**
	 * Methode zum Speichern einer Entity. Falls die Tabellen noch nicht
	 * existieren, werden diese angelegt.
	 * 
	 * @param entity die zu speichernde Entity
	 * @return die ID, unter der die Entity gespeichert wurde
	 * @throws Exception wird geworfen, wenn Entity keine ID hat, oder innerhalb
	 *             der Links ein Link ohne ObjectURL vorhanden ist.
	 */
	public long saveEntity(T entity) throws Exception {
	
		db = this.getWritableDatabase();
		
		createTablesForEntityAndLink(db);
		insertEntityDatatypeTables(entity);
		
		if (entity.getLinkPropertyDatatypes() != null
				&& entity.getLinkPropertyDatatypes().keySet().size() > 0) {
			insertLinkDatatypeTables(entity);
		}
		
		for (Link link : entity.getLinks()) {
			if (link.getObject() == null || link.getObject().isEmpty()
					|| link.getType() == null || link.getType().isEmpty()) {
				throw new Exception(
						"Error within the Links. One or more LinkObjectUrls aren't set.");
			}
		}
		
		// für die Entity
		String entityTableName = DatabaseHelper
				.createFromEntityTableName(entity);
		ContentValues values = new ContentValues();
		int realEntityid = entity.getId();
		long realEntitytype = entity.getType();
		String realEntitySelf = entity.getSelf();
		
		setValuesWithIDSelfAndType(values, realEntityid, realEntitytype,
				realEntitySelf);
		
		ArrayList<String> allEntityPropertyKeys = DatabaseHelper
				.createSortedKeysList(entity.getEntityPropertyDatatypes());
		
		values = fillContentValuesWithEntityProperties(allEntityPropertyKeys,
				values, entity);
		
		long entityCheck = db.insert(entityTableName, null, values);
		// bis hier für die Entity
		
		// für die links
		String linkTableName = getLinkTableNameFromEntityTableName(entityTableName);
		ArrayList<String> correctLinkPropertyKeys = DatabaseHelper
				.createSortedKeysList(entity.getLinkPropertyDatatypes());
		
		loopOverAllEntityLinks(entity, linkTableName, correctLinkPropertyKeys);
		// Links Ende
		
		db.close();
		return entityCheck;
	}
	
	private void setValuesWithIDSelfAndType(ContentValues values,
			int realEntityid, long realEntitytype, String realEntitySelf) {
	
		if (isEntityIdNotNull(realEntityid)) {
			values.put(Constants.ENTITY_ENTITYID, realEntityid);
		}
		values.put(Constants.ENTITY_ENTITYTYPE, realEntitytype);
		values.put(Constants.ENTITY_SELF_URL, realEntitySelf);
	}
	
	private boolean isEntityIdNotNull(int realEntityid) {
	
		return realEntityid != 0;
	}
	
	/**
	 * Hilfsmethode für saveEntity. Die übergebenen ContentValues werden anhand
	 * der in der Entity befindlichen LinkProperties entsprechend gefüllt und
	 * aufbereitet.
	 * 
	 * @param entity die zu speichernde Entity
	 * @param values ContentValues, die die zu speichernden Entity-Properties
	 *            beinhalten
	 * @param linkTableName
	 * @param correctLinkPropertyKeys
	 * @throws Exception
	 */
	private void loopOverAllEntityLinks(T entity, String linkTableName,
			ArrayList<String> correctLinkPropertyKeys) throws Exception {
	
		for (Link link : entity.getLinks()) {
			
			ContentValues valuesLink = new ContentValues();
			valuesLink.put("entity_" + Constants.ENTITY_ENTITYID,
					entity.getId());
			valuesLink.put(Constants.LINK_OBJECT, link.getObject());
			valuesLink.put(Constants.LINK_TYPE, link.getType());
			
			HashMap<String, Object> realLinkProperties = link.getProperties();
			HashMap<String, String> correctLinkPropertyKeysAndDatatypesOfThisLink = new HashMap<String, String>();
			
			loopOverCorrectLinkPropertyKeysAndFillCorrectLinkPropertyKeysAndDatatypesOfThisLink(
					entity, correctLinkPropertyKeys, link,
					correctLinkPropertyKeysAndDatatypesOfThisLink);
			
			ArrayList<String> correctLinkPropertyKeysSorted = DatabaseHelper
					.createSortedKeysList(correctLinkPropertyKeysAndDatatypesOfThisLink);
			
			correctImageDatatypeToString(entity, link, realLinkProperties,
					correctLinkPropertyKeysAndDatatypesOfThisLink,
					correctLinkPropertyKeysSorted);
			
			for (String linkPropertyKey : correctLinkPropertyKeysSorted) {
				String datatype = correctLinkPropertyKeysAndDatatypesOfThisLink
						.get(linkPropertyKey).toLowerCase();
				
				if (link.getProperties().get(linkPropertyKey) != null) {
					switch (datatype) {
					
						case (Constants.LONG_LOWCASE) :
							valuesLink.put(link.getType() + "_"
									+ linkPropertyKey, (Long) link
									.getProperties().get(linkPropertyKey));
							break;
						
						case (Constants.DOUBLE_LOWCASE) :
							valuesLink.put(link.getType() + "_"
									+ linkPropertyKey, (Double) link
									.getProperties().get(linkPropertyKey));
							break;
						
						case (Constants.DOUBLEARRAY_LOWCASE) :
							
							Double[] doubleArray = (Double[]) link
									.getProperties().get(linkPropertyKey);
							valuesLink.put(link.getType() + "_"
									+ linkPropertyKey + "_A", doubleArray[0]);
							valuesLink.put(link.getType() + "_"
									+ linkPropertyKey + "_B", doubleArray[1]);
							break;
						
						case (Constants.STRING_LOWCASE) :
							if (((String) link.getProperties().get(
									linkPropertyKey)).isEmpty()) {
								
							}
							else {
								valuesLink.put(link.getType() + "_"
										+ linkPropertyKey, (String) link
										.getProperties().get(linkPropertyKey));
							}
							break;
						
						case (Constants.BOOLEAN_LOWCASE) :
							if ((Boolean) link.getProperties().get(
									linkPropertyKey)) {
								valuesLink.put(link.getType() + "_"
										+ linkPropertyKey, 1);
							}
							else {
								valuesLink.put(link.getType() + "_"
										+ linkPropertyKey, 0);
							}
							break;
						
						case (Constants.DATE_LOWCASE) :
							Date date = (Date) link.getProperties().get(
									linkPropertyKey);
							valuesLink.put(link.getType() + "_"
									+ linkPropertyKey,
									DateHelper.dateToString(date));
							break;
						
						default :
							throw new Exception(
									"Error occured while saving Entity to Database. There must be an incompatible value within the properties.");
					}
				}
			}
			db.insert(linkTableName, null, valuesLink);
		}
	}
	
	/**
	 * Hilfsmethode zum aufbereiten der EntityProperties.
	 * 
	 * @param entity
	 * @param link
	 * @param realLinkProperties
	 * @param correctLinkPropertyKeysAndDatatypesOfThisLink
	 * @param correctLinkPropertyKeysSorted
	 * @throws Exception
	 */
	private void correctImageDatatypeToString(
			T entity,
			Link link,
			HashMap<String, Object> realLinkProperties,
			HashMap<String, String> correctLinkPropertyKeysAndDatatypesOfThisLink,
			ArrayList<String> correctLinkPropertyKeysSorted) throws Exception {
	
		for (String linkPropertyKey : correctLinkPropertyKeysSorted) {
			
			if (link.getProperties().containsKey(linkPropertyKey)) {
				String correctDatatype = correctLinkPropertyKeysAndDatatypesOfThisLink
						.get(linkPropertyKey);
				if (correctDatatype.equalsIgnoreCase(Constants.IMAGE)) {
					correctDatatype = Constants.STRING_LOWCASE;
				}
				String realDatatype = realLinkProperties.get(linkPropertyKey)
						.getClass().getSimpleName().toLowerCase();
				
				if (!correctDatatype.equalsIgnoreCase(realDatatype)) {
					throw new Exception("Error - The LinkProperty "
							+ linkPropertyKey + " of LinkType" + link.getType()
							+ " hasn't the correct Datatype of "
							+ correctDatatype + " but " + realDatatype + ".");
				}
			}
		}
	}
	
	/**
	 * Hilfsmethode zum aufbereiten der linkPropertyKeys
	 * 
	 * @param entity
	 * @param correctLinkPropertyKeys
	 * @param link
	 * @param correctLinkPropertyKeysAndDatatypesOfThisLink
	 */
	private void loopOverCorrectLinkPropertyKeysAndFillCorrectLinkPropertyKeysAndDatatypesOfThisLink(
			T entity,
			ArrayList<String> correctLinkPropertyKeys,
			Link link,
			HashMap<String, String> correctLinkPropertyKeysAndDatatypesOfThisLink) {
	
		for (String key : correctLinkPropertyKeys) {
			if (key.contains(link.getType())) {
				String[] temp = key.split(" ");
				correctLinkPropertyKeysAndDatatypesOfThisLink.put(temp[1],
						entity.getLinkPropertyDatatypes().get(key));
			}
		}
	}
	
	/**
	 * Hilfsmethode zum befüllen der ContentValues mit den EntityProperties.
	 * 
	 * @param allEntityPropertyKeys die Key-Werte aller EntityProperties
	 * @param values die zu befüllenden ContentValues
	 * @param entity die zu speichernde Entity
	 * @return die aufbereiteten ContentValues
	 * @throws Exception wird bei auftretetenden Fehlern geworfen
	 */
	private ContentValues fillContentValuesWithEntityProperties(
			ArrayList<String> allEntityPropertyKeys, ContentValues values,
			T entity) throws Exception {
	
		for (String key : allEntityPropertyKeys) {
			
			String valueDatatype = entity.getEntityPropertyDatatypes().get(key)
					.toLowerCase();
			
			if (entity.getProperties().get(key) != null) {
				
				switch (valueDatatype) {
				
					case (Constants.LONG_LOWCASE) :
						values.put(key, (Long) entity.getProperties().get(key));
						break;
					
					case (Constants.DOUBLE_LOWCASE) :
						values.put(key, (Double) entity.getProperties()
								.get(key));
						break;
					
					case (Constants.DOUBLEARRAY_LOWCASE) :
						Double[] doubleArray = (Double[]) entity
								.getProperties().get(key);
						values.put(key + "_A", doubleArray[0]);
						values.put(key + "_B", doubleArray[1]);
						break;
					
					case (Constants.IMAGE) :
					case (Constants.STRING_LOWCASE) :
						if (((String) entity.getProperties().get(key))
								.isEmpty()) {
						}
						else {
							values.put(key, (String) entity.getProperties()
									.get(key));
						}
						break;
					
					case (Constants.BOOLEAN_LOWCASE) :
						if ((Boolean) entity.getProperties().get(key)) {
							values.put(key, 1);
						}
						else {
							values.put(key, 0);
						}
						break;
					
					case (Constants.DATE_LOWCASE) :
						Date date = (Date) entity.getProperties().get(key);
						values.put(key, DateHelper.dateToString(date));
						break;
					
					default :
						throw new Exception(
								"Error occured while saving Entity to Database. There must be an incompatible value within the properties.");
				}
			}
		}
		return values;
	}
	
	/**
	 * Hilfsmethode für das korrekte Befüllen der ContentValues beim Update
	 * einer Entity.
	 * 
	 * @param allEntityPropertyKeys alle EntityPropertyKeys
	 * @param values ContentValues für das Update
	 * @param entity die Entity, die upgedatet werden soll
	 * @return das entsprechend aufbereitete ContentValues-Objekt
	 * @throws Exception wird geworfen, wenn ein unbekannter Datentyp vorkommt
	 */
	private ContentValues fillContentValuesWithEntityPropertiesForUpdates(
			ArrayList<String> allEntityPropertyKeys, ContentValues values,
			T entity) throws Exception {
	
		HashMap<String, String> allKeys = entity.getEntityPropertyDatatypes();
		addImageKeysToAllKeys(entity, allKeys);
		
		for (String key : allEntityPropertyKeys) {
			
			String valueDatatype = allKeys.get(key).toLowerCase();
			
			if (entity.getProperties().get(key) != null) {
				switch (valueDatatype) {
				
					case (Constants.LONG_LOWCASE) :
						values.put(key, (Long) entity.getProperties().get(key));
						break;
					
					case (Constants.DOUBLE_LOWCASE) :
						values.put(key, (Double) entity.getProperties()
								.get(key));
						break;
					
					case (Constants.DOUBLEARRAY_LOWCASE) :
						Double[] doubleArray = (Double[]) entity
								.getProperties().get(key);
						values.put(key + "_A", doubleArray[0]);
						values.put(key + "_B", doubleArray[1]);
						break;
					
					case (Constants.IMAGE) :
					case (Constants.STRING_LOWCASE) :
						if (((String) entity.getProperties().get(key))
								.isEmpty()) {
						}
						else {
							values.put(key, (String) entity.getProperties()
									.get(key));
						}
						break;
					
					case (Constants.BOOLEAN_LOWCASE) :
						if ((Boolean) entity.getProperties().get(key)) {
							values.put(key, 1);
						}
						else {
							values.put(key, 0);
						}
						break;
					
					case (Constants.DATE_LOWCASE) :
						Date date = (Date) entity.getProperties().get(key);
						values.put(key, DateHelper.dateToString(date));
						break;
					
					default :
						throw new Exception(
								"Error occured while saving Entity to Database. There must be an incompatible value within the properties.");
				}
			}
			else {
				if (valueDatatype
						.equalsIgnoreCase(Constants.DOUBLEARRAY_LOWCASE)) {
					values.putNull(key + "_A");
					values.putNull(key + "_B");
				}
				else {
					values.putNull(key);
				}
			}
		}
		return values;
	}
	
	/**
	 * Hilfsmethode zum Abfragen des Tabellennamens der
	 * Entity-Property-Datatypes-Tabelle.
	 * 
	 * @param entityTableName der Entity-Table-Name
	 * @return der korrekte Table-Name der Entity-Property-Datatypes-Tabelle
	 */
	private String getEntityPropertyDatatypesTableNameFromEntityTableName(
			String entityTableName) {
	
		String entityPropertyDatatypesTableName = entityTableName
				+ "_datatypes";
		return entityPropertyDatatypesTableName;
	}
	
	/**
	 * Hilfsmethode zum Abfragen des Tabellennamens der
	 * Link-Property-Datatypes-Tabelle
	 * 
	 * @param entityTableName Name der Entity-Table
	 * @return der korrekte Table-Name der Link-Property-Datatypes
	 */
	private String getLinkTableNameFromEntityTableName(String entityTableName) {
	
		String linkTableName = entityTableName + "_link";
		return linkTableName;
	}
	
	/**
	 * Hilfsmethode zum Abfragen des Entity-Table-Name anhand des ClassType
	 * 
	 * @param classType für den die Abfrage erfolgen soll
	 * @return der Tabellen-Name
	 */
	private String getEntityTableNameFromClassType(
			Class<? extends Entity> classType) {
	
		String entityTableName = (classType.getPackage() + "." + classType
				.getSimpleName()).toLowerCase().replace(".", "_")
				.replace("package ", "");
		return entityTableName;
	}
	
	/**
	 * Hilfsmethode zum Aufruf der LinkPropertyDatatypesTable. Liest die Tabelle
	 * aus, und erstellt daraus eine HashMap.
	 * 
	 * @param linkPropertyDatatypesTableName Name der
	 *            Link-Property-Datatypes-Tabelle
	 * @return die HashMap mit den Link-Property-Datatypes-Wertepaaren
	 */
	private HashMap<String, String> getLinkPropertyDatatypes(
			String linkPropertyDatatypesTableName) {
	
		HashMap<String, String> linkPropertyDatatypes = new HashMap<String, String>();
		
		try {
			Cursor cursorDatatypeLink = db.query(
					linkPropertyDatatypesTableName, null, null, null, null,
					null, null);
			cursorDatatypeLink.moveToFirst();
			
			String[] columnLinkDatatypesNames = cursorDatatypeLink
					.getColumnNames();
			
			for (String columnName : columnLinkDatatypesNames) {
				int position = cursorDatatypeLink.getColumnIndex(columnName);
				String value = cursorDatatypeLink.getString(position);
				linkPropertyDatatypes.put(columnName, value);
			}
			
			return linkPropertyDatatypes;
		}
		catch (SQLiteException e) {
			return null;
		}
		
	}
	
	/**
	 * Hilfsmethode für DB-Insert der Entity-Property-Datatypes
	 * 
	 * @param entity die Entity
	 */
	@SuppressWarnings("unused")
	private void insertEntityDatatypeTables(T entity) {
	
		ContentValues valuesEntity = new ContentValues();
		Set<String> propertyDatatypes = entity.getEntityPropertyDatatypes()
				.keySet();
		valuesEntity.put(Constants.ENTITY_ENTITYID,
				Constants.INTEGER.toLowerCase());
		valuesEntity.put(Constants.ENTITY_ENTITYTYPE,
				Constants.LONG.toLowerCase());
		valuesEntity.put(Constants.ENTITY_SELF_URL,
				Constants.STRING.toLowerCase());
		for (String key : propertyDatatypes) {
			valuesEntity.put(key, entity.getEntityPropertyDatatypes().get(key));
		}
		long entityDatatypeInsert = db.insert(
				DatabaseHelper.createEntityDatatypesTableName(entity), null,
				valuesEntity);
	}
	
	/**
	 * Hilfsmethode für DB-Insert der Link-Property-Datatypes
	 * 
	 * @param entity die Entity
	 */
	@SuppressWarnings("unused")
	private void insertLinkDatatypeTables(T entity) {
	
		ContentValues valuesLink = new ContentValues();
		Set<String> propertyDatatypes = entity.getLinkPropertyDatatypes()
				.keySet();
		valuesLink.put("entity_" + Constants.ENTITY_ENTITYID,
				Constants.STRING.toLowerCase());
		valuesLink.put(Constants.LINK_TYPE, Constants.STRING.toLowerCase());
		valuesLink.put(Constants.LINK_OBJECT, Constants.STRING.toLowerCase());
		for (String key : propertyDatatypes) {
			
			String realKey = key.replace(" ", "_");
			
			valuesLink.put(realKey, entity.getLinkPropertyDatatypes().get(key));
		}
		long linkDatatypeInsert = db.insert(
				DatabaseHelper.createLinkDatatypesTableName(entity), null,
				valuesLink);
	}
	
	/**
	 * Hilfsmethode zum Aufruf der EntityPropertyDatatypesTable. Liest die
	 * Tabelle aus, und erstellt daraus eine HashMap.
	 * 
	 * @param entityPropertyDatatypesTableName Name der
	 *            Entity-Property-Datatypes-Tabelle
	 * @return die HashMap mit den Entity-Property-Datatypes-Wertepaaren
	 */
	private HashMap<String, String> getEntityPropertyDatatypes(
			String entityPropertyDatatypesTableName) {
	
		HashMap<String, String> entityPropertyDatatypes = new HashMap<String, String>();
		Cursor cursorDatatypeEntity = db.query(
				entityPropertyDatatypesTableName, null, null, null, null, null,
				null);
		cursorDatatypeEntity.moveToFirst();
		String[] columnEntityDatatypesNames = cursorDatatypeEntity
				.getColumnNames();
		
		for (String columnName : columnEntityDatatypesNames) {
			int position = cursorDatatypeEntity.getColumnIndex(columnName);
			String value = cursorDatatypeEntity.getString(position);
			entityPropertyDatatypes.put(columnName, value);
		}
		return entityPropertyDatatypes;
	}
	
	/**
	 * Hilfsmethode zum Erstellen der Ergebnis-Entity anhand des übergebenen
	 * Cursors. Dieser wird vollständig durchlaufen und die enthaltenen
	 * Properties werden ausgelesen und im richtigen Format der Entity
	 * hinzugefügt.
	 * 
	 * @param entityPropertyDatatypes HashMap mit den EntityPropertyDatatypes
	 * @param linkPropertyDatatypes HashMap mit den LinkPropertyDatatypes
	 * @param cursorEntity Cursor für die Ergebnis-Rows aus der Entity-Tabelle
	 * @param cursorLink Cursor für die Ergebnis-Rows aus der Link-Tabelle
	 * @param countRowsLink Anzahl der Ergebnis-Rows des cursorLink
	 * @return die Entity
	 * @throws ParseException bei String-Parsefehlern
	 */
	private Entity createEntityFromCursors(
			HashMap<String, String> entityPropertyDatatypes,
			HashMap<String, String> linkPropertyDatatypes, Cursor cursorEntity,
			Cursor cursorLink, int countRowsLink) throws ParseException {
	
		Entity returnEntity = new Entity();
		
		createEntity(entityPropertyDatatypes, cursorEntity, returnEntity);
		
		createAllLinks(linkPropertyDatatypes, cursorLink, countRowsLink,
				returnEntity);
		
		return returnEntity;
	}
	
	private void createAllLinks(HashMap<String, String> linkPropertyDatatypes,
			Cursor cursorLink, int countRowsLink, Entity returnEntity)
			throws ParseException {
	
		// Links erstellen
		
		ArrayList<Link> links = new ArrayList<Link>();
		
		if (countRowsLink > 0) {
			Set<String> linkKeys = linkPropertyDatatypes.keySet();
			linkKeys.remove(Constants.LINK_OBJECT);
			linkKeys.remove(Constants.LINK_TYPE);
			linkKeys.remove("entity_id");
			cursorLink.moveToFirst();
			
			do {
				Link link = new Link();
				int typePosition = cursorLink
						.getColumnIndex(Constants.LINK_TYPE);
				String type = cursorLink.getString(typePosition);
				int objectPosition = cursorLink
						.getColumnIndex(Constants.LINK_OBJECT);
				String object = cursorLink.getString(objectPosition);
				
				link.setObject(object);
				link.setType(type);
				HashMap<String, Object> linkProperties = new HashMap<String, Object>();
				
				for (String key : linkKeys) {
					if (key.contains(type)) {
						
						String datatype = linkPropertyDatatypes.get(key);
						int columnPosition = cursorLink.getColumnIndex(key);
						String[] splitter = key.split("_");
						String propertyName = splitter[1];
						
						if (datatype
								.equalsIgnoreCase(Constants.DOUBLEARRAY_LOWCASE)) {
							columnPosition = cursorLink.getColumnIndex(key
									+ "_A");
						}
						
						switch (datatype) {
							case Constants.LONG_LOWCASE :
								if (!cursorLink.isNull(columnPosition)) {
									Long longTemp = (Long) cursorLink
											.getLong(columnPosition);
									linkProperties.put(propertyName, longTemp);
								}
								
								break;
							
							case (Constants.DOUBLE_LOWCASE) :
								if (!cursorLink.isNull(columnPosition)) {
									Double doubleTemp = (Double) cursorLink
											.getDouble(columnPosition);
									linkProperties
											.put(propertyName, doubleTemp);
								}
								
								break;
							
							case (Constants.DOUBLEARRAY_LOWCASE) :
								if (!cursorLink.isNull(columnPosition)) {
									int positionOfFirstDoubleField = cursorLink
											.getColumnIndex(key + "_A");
									int positionOfSecondDoubleField = cursorLink
											.getColumnIndex(key + "_B");
									Double[] doubleArrayTemp = new Double[2];
									doubleArrayTemp[0] = cursorLink
											.getDouble(positionOfFirstDoubleField);
									doubleArrayTemp[1] = cursorLink
											.getDouble(positionOfSecondDoubleField);
									linkProperties.put(propertyName,
											doubleArrayTemp);
								}
								
								break;
							
							case (Constants.IMAGE) :
							case (Constants.STRING_LOWCASE) :
								if (!cursorLink.isNull(columnPosition)) {
									String stringTemp = (String) cursorLink
											.getString(columnPosition);
									linkProperties
											.put(propertyName, stringTemp);
								}
								break;
							
							case (Constants.BOOLEAN_LOWCASE) :
								if (!cursorLink.isNull(columnPosition)) {
									int booleanTemp = cursorLink
											.getInt(columnPosition);
									if (booleanTemp == 0) {
										linkProperties.put(propertyName,
												new Boolean(false));
									}
									else if (booleanTemp == 1) {
										linkProperties.put(propertyName,
												new Boolean(true));
									}
								}
								
								break;
							
							case (Constants.DATE_LOWCASE) :
								if (!cursorLink.isNull(columnPosition)) {
									String dateString = (String) cursorLink
											.getString(columnPosition);
									linkProperties
											.put(propertyName, DateHelper
													.stringToDate(dateString));
								}
								break;
						}
						
					}
				}
				link.setProperties(linkProperties);
				links.add(link);
			} while (cursorLink.moveToNext());
		}
		returnEntity.setLinks(links);
	}
	
	private void createEntity(HashMap<String, String> entityPropertyDatatypes,
			Cursor cursorEntity, Entity returnEntity) throws ParseException {
	
		// Entity erstellen
		
		int entityIdPosition = cursorEntity
				.getColumnIndex(Constants.ENTITY_ENTITYID);
		int entitySelfPosition = cursorEntity
				.getColumnIndex(Constants.ENTITY_SELF_URL);
		int entityTypePosition = cursorEntity
				.getColumnIndex(Constants.ENTITY_ENTITYTYPE);
		returnEntity.setId(cursorEntity.getInt(entityIdPosition));
		returnEntity.setSelf(cursorEntity.getString(entitySelfPosition));
		returnEntity.setType(cursorEntity.getLong(entityTypePosition));
		HashMap<String, Object> entityProperties = new HashMap<String, Object>();
		Set<String> keys = entityPropertyDatatypes.keySet();
		keys.remove(Constants.ENTITY_ENTITYID);
		keys.remove(Constants.ENTITY_SELF_URL);
		keys.remove(Constants.ENTITY_ENTITYTYPE);
		
		for (String key : entityPropertyDatatypes.keySet()) {
			String datatype = entityPropertyDatatypes.get(key).toLowerCase();
			int positionOfCurrentKey = 0;
			
			if (datatype.equalsIgnoreCase(Constants.DOUBLEARRAY_LOWCASE)) {
				positionOfCurrentKey = cursorEntity.getColumnIndex(key + "_A");
			}
			else {
				positionOfCurrentKey = cursorEntity.getColumnIndex(key);
			}
			
			switch (datatype) {
			
				case Constants.LONG_LOWCASE :
					if (!cursorEntity.isNull(positionOfCurrentKey)) {
						Long longTemp = (Long) cursorEntity
								.getLong(positionOfCurrentKey);
						
						entityProperties.put(key, longTemp);
					}
					break;
				
				case (Constants.DOUBLE_LOWCASE) :
					if (!cursorEntity.isNull(positionOfCurrentKey)) {
						Double doubleTemp = (Double) cursorEntity
								.getDouble(positionOfCurrentKey);
						
						entityProperties.put(key, doubleTemp);
					}
					break;
				
				case (Constants.DOUBLEARRAY_LOWCASE) :
					if (!cursorEntity.isNull(positionOfCurrentKey)) {
						int positionOfFirstDoubleField = cursorEntity
								.getColumnIndex(key + "_A");
						int positionOfSecondDoubleField = cursorEntity
								.getColumnIndex(key + "_B");
						Double[] doubleArrayTemp = new Double[2];
						doubleArrayTemp[0] = cursorEntity
								.getDouble(positionOfFirstDoubleField);
						doubleArrayTemp[1] = cursorEntity
								.getDouble(positionOfSecondDoubleField);
						
						entityProperties.put(key, doubleArrayTemp);
					}
					break;
				
				case (Constants.IMAGE) :
				case (Constants.STRING_LOWCASE) :
					if (!cursorEntity.isNull(positionOfCurrentKey)) {
						String stringTemp = (String) cursorEntity
								.getString(positionOfCurrentKey);
						
						entityProperties.put(key, stringTemp);
					}
					break;
				
				case (Constants.BOOLEAN_LOWCASE) :
					if (!cursorEntity.isNull(positionOfCurrentKey)) {
						int booleanTemp = cursorEntity
								.getInt(positionOfCurrentKey);
						if (booleanTemp == 0) {
							entityProperties.put(key, new Boolean(false));
						}
						else if (booleanTemp == 1) {
							entityProperties.put(key, new Boolean(true));
						}
					}
					break;
				
				case (Constants.DATE_LOWCASE) :
					if (!cursorEntity.isNull(positionOfCurrentKey)) {
						String dateString = (String) cursorEntity
								.getString(positionOfCurrentKey);
						if (dateString != null && !dateString.isEmpty()) {
							entityProperties.put(key,
									DateHelper.stringToDate(dateString));
						}
					}
					break;
			}
		}
		returnEntity.setProperties(entityProperties);
		//
	}
	
	/**
	 * Hilfsmethode zum Wurf einer Exception, wenn die übergebenen
	 * countRowsEntity nicht 1 ist.
	 * 
	 * @param entityId EntityId
	 * @param countRowsEntity Anzahl der gefundenen Rows
	 * @throws Exception wird geworfen, wenn countRowsEntity nicht 1 ist
	 */
	private void throwExceptionIfCursorEntityHasMoreThanOneRows(int entityId,
			int countRowsEntity) throws Exception {
	
		if (countRowsEntity != 1) {
			throw new Exception(
					"Error - loading the ID "
							+ entityId
							+ " failed. Propably is this ID from wrong EntityType or there is no Entity with ID "
							+ entityId + " saved.");
		}
	}
	
	/**
	 * Hilfsmethode für den Table-Create der Entity- und Link-Tabellen, sowie
	 * für deren Datatypes-Tabellen. Der Create-Befehl wird nur ausgeführt, wenn
	 * die entsprechenden createTableString nicht null oder leer sind (diese
	 * werden im Constructor übergeben).
	 * 
	 * @param db
	 */
	private void createTablesForEntityAndLink(SQLiteDatabase db) {
	
		if (createEntityTableString != null
				&& !createEntityTableString.isEmpty()) {
			Log.d(Constants.TAG_ORCASDK,
					"DATABASE, create ENTITY-Table Statement: "
							+ createEntityTableString);
			db.execSQL(createEntityTableString);
			Log.d(Constants.TAG_ORCASDK,
					"DATABASE, create ENTITYDATATYPES-Table Statement: "
							+ entityDatatypesCreateTableString);
			db.execSQL(entityDatatypesCreateTableString);
		}
		
		if (createLinkTableString != null && !createLinkTableString.isEmpty()
				&& linkDatatypesCreateTableString != null
				&& !linkDatatypesCreateTableString.isEmpty()) {
			Log.d(Constants.TAG_ORCASDK,
					"DATABASE, create LINK-Table Statement: "
							+ createLinkTableString);
			db.execSQL(createLinkTableString);
			Log.d(Constants.TAG_ORCASDK,
					"DATABASE, create LINKDATATYPES-Table Statement: "
							+ linkDatatypesCreateTableString);
			db.execSQL(linkDatatypesCreateTableString);
		}
	}
	
	@Override
	public synchronized void close() {
	
		if (db != null) {
			db.close();
			db = null;
		}
		super.close();
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
	}
}
