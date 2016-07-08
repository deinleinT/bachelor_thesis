
package de.fhws.sdk.orca.database;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.model.Entity;

/**
 * In dieser Klasse sind sämtliche Methoden für die SQL-Lite Datenbank
 * implementiert.
 * 
 * @author ThomasDeinlein
 */

public class DatabaseWrapper {
	
	/**
	 * Methode zum Laden einer bestimmten Entity anhand der ID.
	 * 
	 * @param entityId die ID der Entität, die geladen werden soll
	 * @param context Context der Activity, von der die Methode aufgerufen wird
	 * @param classType der ClassType von der Entität, die geladen werden soll.
	 *            Falls die ID nicht zu diesem Typ gehört, wird ein Exception
	 *            geworfen.
	 * @return die geladene Entity
	 * @throws Exception wenn die übergebene ID nicht zum angegebenen Classtype
	 *             gehört.
	 */
	public static <T extends Entity> Entity loadEntityById(int entityId,
			Context context, Class<? extends Entity> classType)
			throws Exception {
	
		DatabaseHandler<T> dbHandler = new DatabaseHandler<T>(context);
		T entity = dbHandler.loadEntityById(entityId, classType);
		
		dbHandler.close();
		return entity;
	}
	
	/**
	 * Methode lädt alle Entities vom selben Typ.
	 * 
	 * @param context der Context der Activity, von der Methode aufgerufen wird
	 * @param classType der ClassType von den Entities, die geladen werden
	 *            sollen
	 * @return eine ArrayList mit den geladenen Entities
	 * @throws Exception wird z.B. geworfen, wenn keine Entities gefunden werden
	 */
	public static <T extends Entity> ArrayList<T> loadAllEntitiesOfSameType(
			Context context, Class<? extends Entity> classType)
			throws Exception {
	
		DatabaseHandler<T> dbHandler = new DatabaseHandler<T>(context);
		ArrayList<T> retValue = dbHandler.loadAllEntities(classType);
		dbHandler.close();
		
		return retValue;
	}
	
	/**
	 * Methode lädt alle Entities vom selben Typ, die anhand des übergebenen
	 * whereClauseSQLiteStrings gefunden werden.
	 * 
	 * @param context der Context der Activity, von der Methode aufgerufen wird
	 * @param classType der ClassType von den Entities, die geladen werden
	 *            sollen
	 * @return eine ArrayList mit den geladenen Entities
	 * @throws Exception wird z.B. geworfen, wenn keine Entities gefunden werden
	 */
	public static <T extends Entity> ArrayList<T> loadEntitiesOfSameTypeByQuery(
			Context context, String whereClauseSelectionString,
			Class<? extends Entity> classType) throws Exception {
	
		DatabaseHandler<T> dbHandler = new DatabaseHandler<T>(context);
		ArrayList<T> retValue = dbHandler.loadEntitiesByQuery(classType,
				whereClauseSelectionString);
		dbHandler.close();
		
		return retValue;
	}
	
	/**
	 * Methode zum Abspeichern einer Entity in der Datenbank. Diese Methode ist
	 * für das erstmalige Abspeichern gedacht. *** ACHTUNG: Für die Entity-ID
	 * ist bei dieser Methode kein Autoincrement vorgesehen. Alle Entities, die
	 * hierüber gespeichert werden, müssen eine korrekte und eindeutige ID
	 * gesetzt haben.
	 * 
	 * @param context der Context der Activity, von der Methode aufgerufen wird
	 * @param entity die in die Datenbank zu speichernden Entities
	 * @throws Exception bei Fehlern, die während des Speichervorgangs auftreten
	 */
	public static <T extends Entity> void saveEntity(Context context, T entity)
			throws Exception {
	
		String createEntityTableString = DatabaseHelper
				.createEntityTableCreateString(entity);
		String entityDatatypesCreateTableString = DatabaseHelper
				.createEntityPropertyDatatypesTableCreateString(entity);
		String createLinkTableString = "";
		String linkDatatypesCreateTableString = "";
		
		if (isEntityHasLinks(entity)) {
			createLinkTableString = DatabaseHelper
					.createLinkTableCreateString(entity);
			linkDatatypesCreateTableString = DatabaseHelper
					.createLinkPropertyDatatypesTableCreateString(entity);
		}
		
		DatabaseHandler<T> db = new DatabaseHandler<T>(context,
				createEntityTableString, createLinkTableString,
				entityDatatypesCreateTableString,
				linkDatatypesCreateTableString);
		db.saveEntity(entity);
		db.close();
		
	}
	
	/**
	 * Methode zum erstmaligen Abspeichern mehrere Entities vom gleichen Typ.
	 * 
	 * @param context der Context der Activity, von der Methode aufgerufen wird
	 * @param entities die in die Datenbank zu speichernden Entities
	 * @throws Exception bei Fehlern, die während des Speichervorgangs auftreten
	 */
	public static <T extends Entity> void saveEntities(Context context,
			ArrayList<T> entities) throws Exception {
	
		for (T entity : entities) {
			saveEntity(context, entity);
		}
	}
	
	/**
	 * Methode zum updaten der Entity in der lokalen DB. Die EntityID,
	 * SelfString und Type werden beibehalten. Alle EntityProperties und Links
	 * werden in der DB upgedatet.
	 * 
	 * @param context der Context der Activity, von der die Methode aufgerufen
	 *            wird
	 * @param entity die Entity, die upgedatet werden soll
	 * @throws Exception bei Fehlern, z.B. wenn die EntityId nicht gesetzt oder
	 *             0 ist.
	 */
	public static <T extends Entity> void updateEntity(Context context, T entity)
			throws Exception {
	
		String createEntityTableString = DatabaseHelper
				.createEntityTableCreateString(entity);
		String entityDatatypesCreateTableString = DatabaseHelper
				.createEntityPropertyDatatypesTableCreateString(entity);
		String createLinkTableString = "";
		String linkDatatypesCreateTableString = "";
		
		if (entity.getLinkPropertyDatatypes().keySet().size() > 0) {
			createLinkTableString = DatabaseHelper
					.createLinkTableCreateString(entity);
			linkDatatypesCreateTableString = DatabaseHelper
					.createLinkPropertyDatatypesTableCreateString(entity);
		}
		
		DatabaseHandler<T> db = new DatabaseHandler<T>(context,
				createEntityTableString, createLinkTableString,
				entityDatatypesCreateTableString,
				linkDatatypesCreateTableString);
		
		db.updateEntity(entity);
		db.close();
	}
	
	/**
	 * Methode zum Löschen der Entity aus der Datenbank.
	 * 
	 * @param der Context der Activity, von der die Methode aufgerufen wird
	 * @param entity die aus der Datenbank zu löschende Entity
	 * @throws Exception wird bei allen auftretenden Fehlern geworfen (z.B. wenn
	 *             Tabelle nicht existiert oder die zu löschende Entity nicht in
	 *             der DB gespeichert ist)
	 */
	public static <T extends Entity> void deleteEntity(Context context, T entity)
			throws Exception {
	
		DatabaseHandler<T> dbHandler = new DatabaseHandler<T>(context);
		dbHandler.deleteEntity(entity);
		dbHandler.close();
		
	}
	
	/**
	 * Methode zum löschen aller zum übergebenen KlassenTyp gehörenden Tabellen.
	 * 
	 * @param context Context der Activity, von der Methode aufgerufen wird
	 * @param classType Klassentyp der Entity, deren Tabellen gelöscht werden
	 *            sollen
	 */
	public static void deleteTable(Context context,
			Class<? extends Entity> classType) {
	
		DatabaseHandler<Entity> db = new DatabaseHandler<Entity>(context);
		db.deleteTable(classType);
		db.close();
	}
	
	/**
	 * Methode zum abrufen des Datenbank-Pfadnamens.
	 * 
	 * @param context der Context, von dem die Methode aufgerufen wird
	 * @return den kompletten Database-Pfad als String
	 */
	public static <T extends Entity> String getDBPathName(Context context) {
	
		DatabaseHandler<T> db = new DatabaseHandler<T>(context);
		String retValue = db.getReadableDatabase().getPath();
		db.close();
		
		return retValue;
	}
	
	/**
	 * Methode für einen Datenbank-Reset. ACHTUNG: Die komplette lokale
	 * Datenbank wird zurückgesetzt. Alle gespeicherten Tabellen werden
	 * gelöscht.
	 * 
	 * @param context Context, von dem aus auf die Datenbank zugegriffen wird.
	 */
	public static <T extends Entity> void resetDatabase(Context context) {
	
		String dbPathName = getDBPathName(context);
		try {
			boolean deleted = context.deleteDatabase(dbPathName);
			Log.d(Constants.TAG_ORCASDK, "DATABASE resetDatabase " + dbPathName
					+ " " + deleted);
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.d(Constants.TAG_ORCASDK, "DATABASE resetDatabase Exception "
					+ e.getMessage());
		}
	}
	
	private static <T extends Entity> boolean isEntityHasLinks(T entity) {
	
		return entity.getLinkPropertyDatatypes().keySet().size() > 0;
	}
}
