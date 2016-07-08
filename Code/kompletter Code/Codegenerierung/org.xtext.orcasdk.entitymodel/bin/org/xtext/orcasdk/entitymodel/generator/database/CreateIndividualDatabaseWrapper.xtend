package org.xtext.orcasdk.entitymodel.generator.database

import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity

class CreateIndividualDatabaseWrapper {
	

	def static createIndividualDatabaseWrapper(AndroidEntity androidEntity, String packagename){
	'''
	package «packagename».persistence.database.«androidEntity.name»;
	
	import java.util.ArrayList;

	import android.content.Context;
	import de.fhws.sdk.orca.database.*;
	import de.fhws.sdk.orca.model.Entity;
	
	
	public class «androidEntity.name.toFirstUpper»DatabaseWrapper {
	
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
	public static «packagename».«androidEntity.name.toFirstUpper» loadEntityById(int entityId,
			Context context)
			throws Exception {
	
		DatabaseHandler<Entity> dbHandler = new DatabaseHandler<Entity>(context);
		Entity entity = dbHandler.loadEntityById(entityId, «packagename».«androidEntity.name.toFirstUpper».class);
		«packagename».«androidEntity.name.toFirstUpper» retValue = new «packagename».«androidEntity.name.toFirstUpper»();
		retValue.setId(entity.getId());
		retValue.setSelf(entity.getSelf());
		retValue.setLinks(entity.getLinks());
		retValue.setProperties(entity.getProperties());
		
		dbHandler.close();
		
		return retValue;
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
	public static ArrayList<«packagename».«androidEntity.name.toFirstUpper»> loadAllEntitiesOfSameType(
			Context context)
			throws Exception {
	
		DatabaseHandler<«packagename».«androidEntity.name.toFirstUpper»> dbHandler = new DatabaseHandler<«packagename».«androidEntity.name.toFirstUpper»>(context);
		ArrayList<«packagename».«androidEntity.name.toFirstUpper»> retValue = dbHandler.loadAllEntities(«packagename».«androidEntity.name.toFirstUpper».class);
		dbHandler.close();
		
		return retValue;
	}
	
	/**
	 * Methode lädt alle Entities vom selben Typ, die anhand des
	 * übergebenen whereClauseSQLiteStrings gefunden werden.
	 * 
	 * @param context der Context der Activity, von der Methode aufgerufen wird
	 * @param classType der ClassType von den Entities, die geladen werden
	 *            sollen
	 * @return eine ArrayList mit den geladenen Entities
	 * @throws Exception wird z.B. geworfen, wenn keine Entities gefunden werden
	 */
	public static ArrayList<«packagename».«androidEntity.name.toFirstUpper»> loadEntitiesOfSameTypeByQuery(
			Context context, String whereClauseSelectionString) throws Exception {
	
		DatabaseHandler<«packagename».«androidEntity.name.toFirstUpper»> dbHandler = new DatabaseHandler<«packagename».«androidEntity.name.toFirstUpper»>(context);
		ArrayList<«packagename».«androidEntity.name.toFirstUpper»> retValue = dbHandler.loadEntitiesByQuery(«packagename».«androidEntity.name.toFirstUpper».class,
				whereClauseSelectionString);
		dbHandler.close();
		
		return retValue;
	}
	
	/**
	 * Methode zum Abspeichern einer Entity in der Datenbank. Diese Methode ist
	 * für das erstmalige Abspeichern gedacht. 
	 * 
	 * @param context der Context der Activity, von der Methode aufgerufen wird
	 * @param entity die in die Datenbank zu speichernden Entities
	 * @returns die ID, unter der Entity in DB gespeichert wurde, -1 bei Fehlern
	 * @throws Exception bei Fehlern, die während des Speichervorgangs auftreten
	 */
	public static long saveEntity(Context context, «packagename».«androidEntity.name.toFirstUpper» entity)
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
		
		DatabaseHandler<«packagename».«androidEntity.name.toFirstUpper»> db = new DatabaseHandler<«packagename».«androidEntity.name.toFirstUpper»>(context,
				createEntityTableString, createLinkTableString,
				entityDatatypesCreateTableString,
				linkDatatypesCreateTableString);
		long retValue = db.saveEntity(entity);
		db.close();
		return retValue;
	}
	
	/**
	 * Methode zum erstmaligen Abspeichern mehrere Entities vom
	 * gleichen Typ.
	 * 
	 * @param context der Context der Activity, von der Methode aufgerufen wird
	 * @param entities die in die Datenbank zu speichernden Entities
	 * @throws Exception bei Fehlern, die während des Speichervorgangs auftreten
	 */
	public static void saveEntities(Context context,
			ArrayList<«packagename».«androidEntity.name.toFirstUpper»> entities) throws Exception {
	
		for («packagename».«androidEntity.name.toFirstUpper» entity : entities) {
			saveEntity(context, entity);
		}
	}
	
	/**
	 * Methode zum updaten der Entity in der lokalen DB. Die
	 * EntityID, SelfString und Type werden beibehalten. Alle EntityProperties
	 * und Links werden in der DB upgedatet.
	 * 
	 * @param context der Context der Activity, von der die Methode aufgerufen wird
	 * @param entity die Entity, die upgedatet werden soll
	 * @throws Exception bei Fehlern, z.B. wenn die EntityId nicht gesetzt oder 0 ist.
	 */
	public static int updateEntity(Context context, «packagename».«androidEntity.name.toFirstUpper» entity)
			throws Exception {
	
		DatabaseHandler<«packagename».«androidEntity.name.toFirstUpper»> db = new DatabaseHandler<«packagename».«androidEntity.name.toFirstUpper»>(context);
		int retValue = db.updateEntity(entity);
		db.close();
		return retValue;
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
	public static int deleteEntity(Context context, «packagename».«androidEntity.name.toFirstUpper» entity)
			throws Exception {
	
		DatabaseHandler<«packagename».«androidEntity.name.toFirstUpper»> dbHandler = new DatabaseHandler<«packagename».«androidEntity.name.toFirstUpper»>(context);
		int retValue = dbHandler.deleteEntity(entity);
		dbHandler.close();
		return retValue;
	}
	
	/**
	 * Methode zum löschen aller zum übergebenen KlassenTyp gehörenden Tabellen.
	 * 
	 * @param context Context der Activity, von der Methode aufgerufen wird
	 * @param classType Klassentyp der Entity, deren Tabellen gelöscht werden
	 *            sollen
	 */
	public static void deleteTable(Context context) {
	
		DatabaseHandler<«packagename».«androidEntity.name.toFirstUpper»> db = new DatabaseHandler<«packagename».«androidEntity.name.toFirstUpper»>(context);
		db.deleteTable(«packagename».«androidEntity.name.toFirstUpper».class);
		db.close();
	}
	
	/**
	 * Methode für einen Datenbank-Reset. ACHTUNG: Die komplette
	 * lokale Datenbank wird zurückgesetzt. Alle gespeicherten Tabellen werden
	 * gelöscht.
	 * 
	 * @param context Context, von dem aus auf die Datenbank zugegriffen wird.
	 * @param entity
	 */
	public static void resetDatabase(Context context) {
	
		try {
			context.deleteDatabase(DatabaseWrapper.getDBPathName(context));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	'''
}

}