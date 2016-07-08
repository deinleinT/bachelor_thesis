
package de.fhws.sdk.orca.network.asynctask.entity;

import java.util.HashMap;

import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.network.asynctask.entity.delete.DeleteSingleEntityById;
import de.fhws.sdk.orca.network.asynctask.entity.delete.DeleteSingleEntityProperty;
import de.fhws.sdk.orca.network.asynctask.entity.delete.DeleteSingleEntityPropertyByEntityId;
import de.fhws.sdk.orca.network.asynctask.entity.get.GetAllEntitiesIds;
import de.fhws.sdk.orca.network.asynctask.entity.get.GetEntitiesByAttachedEntity;
import de.fhws.sdk.orca.network.asynctask.entity.get.GetEntitiesByNavigationLink;
import de.fhws.sdk.orca.network.asynctask.entity.get.GetEntitiesByQueryString;
import de.fhws.sdk.orca.network.asynctask.entity.get.GetSingleEntityById;
import de.fhws.sdk.orca.network.asynctask.entity.get.GetSingleEntityByLink;
import de.fhws.sdk.orca.network.asynctask.entity.get.GetSingleEntityProperty;
import de.fhws.sdk.orca.network.asynctask.entity.get.GetSingleEntityPropertyById;
import de.fhws.sdk.orca.network.asynctask.entity.post.PostSingleEntityWithCheckWhetherEntityStillExists;
import de.fhws.sdk.orca.network.asynctask.entity.post.PostSingleEntityWithoutCheck;
import de.fhws.sdk.orca.network.asynctask.entity.put.PutSingleEntity;
import de.fhws.sdk.orca.network.asynctask.entity.put.PutSingleEntityByEntityId;
import de.fhws.sdk.orca.network.asynctask.entity.put.PutSingleEntityProperties;
import de.fhws.sdk.orca.network.asynctask.entity.put.PutSingleEntityPropertiesById;
import de.fhws.sdk.orca.network.callback.IAllEntityIdsCallback;
import de.fhws.sdk.orca.network.callback.INoReturnValueCallback;
import de.fhws.sdk.orca.network.callback.IPageCallback;
import de.fhws.sdk.orca.network.callback.IPropertyCallback;
import de.fhws.sdk.orca.network.callback.IReturnValueCallback;

/**
 * Zentrale Klasse f�r die Kommunikation mit dem Backend. Hierin sind alle
 * statischen Methoden enthalten, die f�r POST, GET, PUT und DELETE HTTP
 * Requests f�r das Orca-Backend ben�tigt werden. Kann f�r manuelle
 * Backend-Zugriffe verwendet werden.
 * 
 * @author ThomasDeinlein
 */

public class EntityPersistenceHandler {
	
	/**
	 * Diese Klassenmethode speichert eine �bergebene Entity im Backend ab. Vor
	 * dem Speichern wird gepr�ft, ob es evtl. bereits Entit�ten gibt, die
	 * �hnliche Properties haben. Hierbei werden jedoch nur bool, long, double
	 * und String (ohne Images!) Properties zur Abfrage verwendet. Wenn es schon
	 * eine oder mehrere Entit�ten gibt, erfolgt keine Speicherung, �ber das
	 * Callback werden in der ErrorMessage die EntityIds angegeben, die im
	 * Backend schon gespeichert und �hnlich sind. Ansonsten erfolgt eine
	 * Speicherung der Entity und �ber das Callback kann diese abgerufen werden.
	 * 
	 * @param entity Die zu speichernde Entity, muss von
	 *            {@linkplain de.fhws.sdk.orca.model.Entity Entity} erben
	 * @param appName Der individuelle AppName, muss zur Authorisierung
	 *            angegeben werden
	 * @param apiKey Der individuelle ApiKey, muss zur Authorisierung angegeben
	 *            werden
	 * @param callback Das Callback, womit die gespeicherte Entity, der
	 *            http-StatusCode und errorMessages threadsicher abgefragt
	 *            werden k�nnen
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Entity> void saveEntityWithCheckWhetherEntityStillExists(
			T entity, String appName, String apiKey,
			IReturnValueCallback<T> callback) {
	
		PostSingleEntityWithCheckWhetherEntityStillExists<T> post = new PostSingleEntityWithCheckWhetherEntityStillExists<T>(
				appName, apiKey, callback);
		post.execute(entity);
	}
	
	/**
	 * Diese Klassenmethode speichert eine �bergebene Entity im Backend ab. Es
	 * erfolgt KEINE Pr�fung, ob es Entit�ten mit �hnlichen Properties schon im
	 * Backend gibt, es erfolgt gleich eine Speicherung der Entity und �ber das
	 * Callback kann diese abgerufen werden.
	 * 
	 * @param entity Die zu speichernde Entity, muss von
	 *            de.fhws.sdk.orca.model.Entity erben
	 * @param appName Der individuelle AppName, muss zur Authorisierung
	 *            angegeben werden
	 * @param apiKey Der individuelle ApiKey, muss zur Authorisierung angegeben
	 *            werden
	 * @param callback Das Callback, womit die gespeicherte Entity, der
	 *            http-StatusCode und errorMessages threadsicher abgefragt
	 *            werden k�nnen
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Entity> void saveEntityWithoutCheck(T entity,
			String appName, String apiKey, IReturnValueCallback<T> callback) {
	
		PostSingleEntityWithoutCheck<T> post = new PostSingleEntityWithoutCheck<T>(
				appName, apiKey, callback);
		post.execute(entity);
	}
	
	/**
	 * Pr�ft anhand der Properties der �bergebenen Entity, ob es �hnliche
	 * Entit�ten schon im Backend gibt. Wenn ja, wird �ber das Callback eine
	 * {@linkplain de.fhws.sdk.orca.model.EntityPage EntityPage} mit allen
	 * gefundenen Entit�ten abrufbar.
	 * 
	 * @param entity Anhand deren Properties erfolgt die Abfrage
	 * @param appName Der individuelle AppName, muss zur Authorisierung
	 *            angegeben werden
	 * @param apiKey Der individuelle ApiKey, muss zur Authorisierung angegeben
	 *            werden
	 * @param callback Das Callback zur Abfrage der EntityPage, des
	 *            HTTP-StatusCodes und der errorMessage
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Entity> void loadEntitiesByAttachedEntityObject(
			T entity, String appName, String apiKey, IPageCallback<T> callback) {
	
		GetEntitiesByAttachedEntity<T> get = new GetEntitiesByAttachedEntity<T>(
				appName, apiKey, callback);
		get.execute(entity);
	}
	
	/**
	 * Methode zur Abfrage von Entities anhand einer SQL-Query. Der Query-String
	 * muss korrekt sein, damit Anfrage erfolgen kann. Nach erfolgter Anfrage
	 * kann �ber das Callback die EntityPage mit den gefundenen Entities
	 * abgerufen werden.
	 * 
	 * @param queryString Die abzufragende Query
	 * @param type der EntityType, ist notwendig, sonst keine Abfrage m�glich
	 * @param orderBy hier kann ein Property angegeben werden, wonach die
	 *            Abfrage sortiert wird, es kann auch ein leerer String
	 *            �bergeben werden aber nicht null
	 * @param offset Gibt an, bei welchem Offset die
	 *            {@linkplain de.fhws.sdk.orca.model.EntityPage EntityPage}
	 *            beginnen soll
	 * @param size Gibt an, wie gro� die
	 *            {@linkplain de.fhws.sdk.orca.model.EntityPage EntityPage}
	 *            werden darf.
	 * @param der Klassentyp der Entit�t, die abgefragt werden soll
	 * @param appName Der individuelle AppName, muss zur Authorisierung
	 *            angegeben werden
	 * @param apiKey Der individuelle ApiKey, muss zur Authorisierung angegeben
	 *            werden
	 * @param callback Das Callback zur Abfrage der EntityPage, des
	 *            HTTP-StatusCodes und der errorMessage
	 */
	public static <T extends Entity> void loadEntitiesByQueryString(
			String queryString, long type, String orderBy, int offset,
			int size, Class<? extends Entity> classType, String appName,
			String apiKey, IPageCallback<T> callback) {
	
		GetEntitiesByQueryString<T> get = new GetEntitiesByQueryString<T>(
				queryString, type, orderBy, offset, size, classType, appName,
				apiKey, callback);
		get.execute();
	}
	
	/**
	 * Diese Methode dient dazu, Entities von einem NavigationLink einer
	 * EntityPage zu laden. �ber ein EntityPageObjekt k�nnen mit der Methode
	 * getNavigationLinks() alle entsprechenden Links abgerufen werden. Dieser
	 * Methode ist dann ein einzelner String-Link zu �bergeben. �ber das
	 * Callback erh�lt man die entsprechende neue EntityPage
	 * 
	 * @param link String eines EntityPage NavigationsLink
	 * @param entityType der EntityType der Entit�ten, die �ber diesen Link
	 *            abgerufen werden k�nnen
	 * @param classType der classType der Entit�ten, die �ber diesen Link
	 *            abgerufen werden k�nnen
	 * @param apiKey der individuelle ApiKey, dient zur Authorisierung
	 * @param callback zum Erhalt der angeforderten EntityPage
	 */
	public static <T extends Entity> void loadEntitiesByNavigationLink(
			String link, Long entityType, Class<? extends Entity> classType,
			String apiKey, IPageCallback<T> callback) {
	
		GetEntitiesByNavigationLink<T> get = new GetEntitiesByNavigationLink<T>(
				link, entityType, classType, apiKey, callback);
		get.execute();
	}
	
	/**
	 * Methode zum Laden einer Entit�t anhand eines Links (z.B. selfUrl)
	 * 
	 * @param link String mit dem URL-Link
	 * @param classType der Typ, der von diesem Link angefordert werden soll
	 * @param apiKey f�r die Authorisierung
	 * @param callback damit kann auf Entity nach dem Laden zugegriffen werden
	 */
	public static <T extends Entity> void loadEntityByLink(String link,
			Class<? extends Entity> classType, String apiKey,
			IReturnValueCallback<T> callback) {
	
		GetSingleEntityByLink<T> get = new GetSingleEntityByLink<T>(link,
				classType, apiKey, callback);
		get.execute();
	}
	
	/**
	 * Methode, die es anhand der (vom Backend beim ersten Speichern vergebenen)
	 * EntityId erm�glicht, die Entity zu laden..
	 * 
	 * @param id die EntityId, wird beim Speichern im Backend vergeben und ist
	 *            f�r jede Entity eindeutig
	 * @param type der EntityType
	 * @param classType der ClassType der entsprechenden Entity
	 * @param appName wird f�r die Authorisierung ben�tigt
	 * @param apiKey wird f�r die Authorisierung ben�tigt
	 * @param callback Callback zum threadsicheren Zugriff auf die geladene
	 *            Entity
	 */
	public static <T extends Entity> void loadEntityById(int id, long type,
			Class<? extends Entity> classType, String appName, String apiKey,
			IReturnValueCallback<T> callback) {
	
		GetSingleEntityById<T> get = new GetSingleEntityById<T>(id, type,
				classType, appName, apiKey, callback);
		get.execute();
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Entity> void loadSingleEntityProperty(T entity,
			String propertyName, String appName, String apiKey,
			IPropertyCallback callback) {
	
		GetSingleEntityProperty<T> get = new GetSingleEntityProperty<T>(
				propertyName, appName, apiKey, callback);
		get.execute(entity);
	}
	
	public static <T extends Entity> void loadSingleEntityPropertyById(
			int entityId, String propertyName, String appName, String apiKey,
			IPropertyCallback callback) {
	
		GetSingleEntityPropertyById<T> get = new GetSingleEntityPropertyById<T>(
				entityId, propertyName, appName, apiKey, callback);
		get.execute();
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Entity> void deleteSingleEntityProperty(T entity,
			String propertyName, String appName, String apiKey,
			INoReturnValueCallback callback) {
	
		DeleteSingleEntityProperty<T> delete = new DeleteSingleEntityProperty<T>(
				propertyName, appName, apiKey, callback);
		delete.execute(entity);
	}
	
	public static void deleteSingleEntityPropertyByEntityId(int entityId,
			String propertyName, String appName, String apiKey,
			INoReturnValueCallback callback) {
	
		DeleteSingleEntityPropertyByEntityId delete = new DeleteSingleEntityPropertyByEntityId(
				entityId, propertyName, appName, apiKey, callback);
		delete.execute();
	}
	
	public static void deleteEntityById(int id, String appName, String apiKey,
			INoReturnValueCallback callback) {
	
		DeleteSingleEntityById delete = new DeleteSingleEntityById(id, appName,
				apiKey, callback);
		delete.execute();
	}
	
	/**
	 * Methode zum updaten einer Entity im Backend. Die originalEntity wird mit
	 * der newEntity ersetzt.
	 * 
	 * @param originalEntity die zu ersetzende Entity, WICHTIG: Die entityId
	 *            muss gesetzt und korrekt sein!
	 * @param newEntity mit dieser Entity wird die originalEntity ersetzt
	 * @param appName zur Authorisierung
	 * @param apiKey zur Authorisierung
	 * @param callback f�r
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Entity> void updateEntityWithNewEntity(
			T originalEntity, T newEntity, String appName, String apiKey,
			INoReturnValueCallback callback) {
	
		PutSingleEntity<T> put = new PutSingleEntity<T>(originalEntity,
				newEntity, appName, apiKey, callback);
		put.execute(originalEntity);
		
	}
	
	/**
	 * Methode zum updaten einer Entity im Backend. Die originalEntity wird mit
	 * der newEntity ersetzt.
	 * 
	 * @param entityIdOfOriginalEntity die EntityId der zu ersetzenden Entity
	 * @param newEntity mit dieser Entity wird die originalEntity ersetzt
	 * @param appName zur Authorisierung
	 * @param apiKey zur Authorisierung
	 * @param callback f�r
	 */
	public static <T extends Entity> void updateEntityByEntityIdWithNewEntity(
			int entityIdOfOriginalEntity, T newEntity, String appName,
			String apiKey, INoReturnValueCallback callback) {
	
		PutSingleEntityByEntityId<T> put = new PutSingleEntityByEntityId<T>(
				entityIdOfOriginalEntity, newEntity, appName, apiKey, callback);
		put.execute();
		
	}
	
	/**
	 * Methode zum Updaten der Properties. Aktualisiert die Properties im
	 * Backend und im �bergebenen Objekt. WICHTIG: Es k�nnen nur Properties
	 * geupdatet werden, die in der urspr�nglichen Entity schon vorhanden sind.
	 * 
	 * @param entity Objekt der Entit�t, deren Properties aktualisiert werden
	 *            sollen.
	 * @param newProperties die neuen Properties, es d�rfen nur bereits
	 *            vorhandene Properties aktualisiert werden
	 * @param appName zur Authorisierung
	 * @param apiKey zur Authorisierung
	 * @param callback f�r
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Entity> void updateEntityWithNewProperties(
			T entity, HashMap<String, Object> newProperties, String appName,
			String apiKey, INoReturnValueCallback callback) {
	
		PutSingleEntityProperties<T> put = new PutSingleEntityProperties<T>(
				newProperties, appName, apiKey, callback);
		put.execute(entity);
	}
	
	public static <T extends Entity> void updateEntityWithNewPropertiesByEntityId(
			int entityId, HashMap<String, Object> newProperties,
			String appName, String apiKey, INoReturnValueCallback callback) {
	
		PutSingleEntityPropertiesById put = new PutSingleEntityPropertiesById(
				entityId, newProperties, appName, apiKey, callback);
		put.execute();
	}
	
	// public static <T extends Entity> void loadObjectEntityByLink(int
	// entityId,
	// int objectId, String linkType,
	// Class<? extends Entity> classTypeOfObject, String appName,
	// String apiKey, IReturnValueCallback<T> callback) {
	//
	// GetObjectEntityByLink<T> get = new GetObjectEntityByLink<T>(entityId,
	// objectId, linkType, classTypeOfObject, appName, apiKey,
	// callback);
	// get.execute();
	// }
	
	public static <T extends Entity> void loadAllEntitiesFromSameType(
			long type, String orderBy, int offset, int size,
			Class<? extends Entity> classType, String appName, String apiKey,
			IPageCallback<T> callback) {
	
		GetEntitiesByQueryString<T> get = new GetEntitiesByQueryString<T>("",
				type, orderBy, offset, size, classType, appName, apiKey,
				callback);
		get.execute();
	}
	
	public static <T extends Entity> void loadAllEntitiesIds(Long type,
			Class<? extends Entity> classType, String appName, String apiKey,
			IAllEntityIdsCallback callback) {
	
		GetAllEntitiesIds<T> get = new GetAllEntitiesIds<T>(type, classType,
				appName, apiKey, callback);
		get.execute();
	}
}
