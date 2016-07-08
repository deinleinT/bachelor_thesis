package org.xtext.orcasdk.entitymodel.generator.backend

class CreateBackendPersistenceWrapper {

def static compileBackendPersistenceWrapper() {
		
'''
package de.fhws.sdk.orca.persistence.wrapper;

import java.io.InputStream;

import android.content.Context;
import de.fhws.sdk.orca.network.asynctask.image.delete.DeleteEntityImage;
import de.fhws.sdk.orca.network.asynctask.image.get.GetEntityImage;
import de.fhws.sdk.orca.network.asynctask.image.get.GetEntityImageByImageLink;
import de.fhws.sdk.orca.network.asynctask.image.put.Image;
import de.fhws.sdk.orca.network.asynctask.image.put.PutEntityImage;
import de.fhws.sdk.orca.network.callback.IImageCallback;
import de.fhws.sdk.orca.network.callback.INoReturnValueCallback;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.AppConstants;
import java.util.HashMap;
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
import de.fhws.sdk.orca.network.asynctask.link.get.GetObjectEntityByLink;
import de.fhws.sdk.orca.network.callback.IAllEntityIdsCallback;
import de.fhws.sdk.orca.network.callback.IPageCallback;
import de.fhws.sdk.orca.network.callback.IPropertyCallback;
import de.fhws.sdk.orca.network.callback.IReturnValueCallback;
import de.fhws.sdk.orca.model.Link;
import de.fhws.sdk.orca.network.asynctask.entity.get.GetAllEntitiesFromSameLinkType;
import de.fhws.sdk.orca.network.asynctask.link.delete.DeleteAllLinksOfSameType;
import de.fhws.sdk.orca.network.asynctask.link.delete.DeleteSingleLink;
import de.fhws.sdk.orca.network.asynctask.link.delete.DeleteSingleLinkProperty;
import de.fhws.sdk.orca.network.asynctask.link.get.GetAllLinksFromSameTypeOfAnEntity;
import de.fhws.sdk.orca.network.asynctask.link.get.GetLinkPageByNavigationLink;
import de.fhws.sdk.orca.network.asynctask.link.post.PostSingleLink;
import de.fhws.sdk.orca.network.asynctask.link.post.PostSingleLinkByEntityIds;
import de.fhws.sdk.orca.network.asynctask.link.post.PostSingleLinkByEntityIdsAndLinkType;
import de.fhws.sdk.orca.network.asynctask.link.put.PutAdditionalPropertyToLinksOfSameLinkType;
import de.fhws.sdk.orca.network.asynctask.link.put.PutNewPropertiesToLinksOfSameLinkType;
import de.fhws.sdk.orca.network.asynctask.link.put.PutSingleLinkProperties;
import de.fhws.sdk.orca.network.callback.IEntityPageWithEntityCallback;
import de.fhws.sdk.orca.network.callback.ILinkPageCallback;
import de.fhws.sdk.orca.network.callback.IPostLinkCallback;


public class BackendPersistenceWrapper {
	

/**
	 * Diese Klassenmethode speichert eine übergebene Entity im Backend ab. Vor
	 * dem Speichern wird geprüft, ob es evtl. bereits Entitäten gibt, die
	 * ähnliche Properties haben. Hierbei werden jedoch nur bool, long, double
	 * und String (ohne Images!) Properties zur Abfrage verwendet. Wenn es schon
	 * eine oder mehrere Entitäten gibt, erfolgt keine Speicherung, über das
	 * Callback werden in der ErrorMessage die EntityIds angegeben, die im
	 * Backend schon gespeichert und ähnlich sind. Ansonsten erfolgt eine
	 * Speicherung der Entity und über das Callback kann diese abgerufen werden.
	 * 
	 * @param entity Die zu speichernde Entity, muss von
	 *            {@linkplain de.fhws.sdk.orca.model.Entity Entity} erben
	 * @param appName Der individuelle AppName, muss zur Authorisierung
	 *            angegeben werden
	 * @param apiKey Der individuelle ApiKey, muss zur Authorisierung angegeben
	 *            werden
	 * @param callback Das Callback, womit die gespeicherte Entity, der
	 *            http-StatusCode und errorMessages threadsicher abgefragt
	 *            werden können
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Entity> void saveEntityWithCheckWhetherEntityStillExists(
			T entity, IReturnValueCallback<T> callback) {
	
		PostSingleEntityWithCheckWhetherEntityStillExists<T> post = new PostSingleEntityWithCheckWhetherEntityStillExists<T>(
				AppConstants.APPNAME, AppConstants.APIKEY, callback);
		post.execute(entity);
	}
	
	/**
	 * Diese Klassenmethode speichert eine übergebene Entity im Backend ab. Es
	 * erfolgt KEINE Prüfung, ob es Entitäten mit ähnlichen Properties schon im
	 * Backend gibt, es erfolgt gleich eine Speicherung der Entity und über das
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
	 *            werden können
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Entity> void saveEntityWithoutCheck(T entity,
			IReturnValueCallback<T> callback) {
	
		PostSingleEntityWithoutCheck<T> post = new PostSingleEntityWithoutCheck<T>(
				AppConstants.APPNAME, AppConstants.APIKEY, callback);
		post.execute(entity);
	}
	
	/**
	 * Prüft anhand der Properties der übergebenen Entity, ob es ähnliche
	 * Entitäten schon im Backend gibt. Wenn ja, wird über das Callback eine
	 * {@linkplain de.fhws.sdk.orca.model.EntityPage EntityPage} mit allen
	 * gefundenen Entitäten abrufbar.
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
	public static <T extends Entity> void loadEntitiesByAttachedEntity(
			T entity, IPageCallback<T> callback) {
	
		GetEntitiesByAttachedEntity<T> get = new GetEntitiesByAttachedEntity<T>(
				AppConstants.APPNAME, AppConstants.APIKEY, callback);
		get.execute(entity);
	}
	
	
	/**
	 * Methode zur Abfrage von Entities anhand einer SQL-Query. Der Query-String
	 * muss korrekt sein, damit Anfrage erfolgen kann. Nach erfolgter Anfrage
	 * kann über das Callback die EntityPage mit den gefundenen Entities
	 * abgerufen werden.
	 * 
	 * @param queryString Die abzufragende Query
	 * @param type der EntityType, ist notwendig, sonst keine Abfrage möglich
	 * @param orderBy hier kann ein Property angegeben werden, wonach die
	 *            Abfrage sortiert wird, es kann auch ein leerer String
	 *            übergeben werden aber nicht null
	 * @param offset Gibt an, bei welchem Offset die
	 *            {@linkplain de.fhws.sdk.orca.model.EntityPage EntityPage}
	 *            beginnen soll
	 * @param size Gibt an, wie groß die
	 *            {@linkplain de.fhws.sdk.orca.model.EntityPage EntityPage}
	 *            werden darf.
	 * @param der Klassentyp der Entität, die abgefragt werden soll
	 * @param appName Der individuelle AppName, muss zur Authorisierung
	 *            angegeben werden
	 * @param apiKey Der individuelle ApiKey, muss zur Authorisierung angegeben
	 *            werden
	 * @param callback Das Callback zur Abfrage der EntityPage, des
	 *            HTTP-StatusCodes und der errorMessage
	 */
	public static <T extends Entity> void loadEntitiesByQueryString(
			String queryString, long type, String orderBy, int offset,
			int size, Class<? extends Entity> classType, IPageCallback<T> callback) {
	
		GetEntitiesByQueryString<T> get = new GetEntitiesByQueryString<T>(
				queryString, type, orderBy, offset, size, classType, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		get.execute();
	}
	
	/**
	 * Diese Methode dient dazu, Entities von einem NavigationLink einer
	 * EntityPage zu laden. Über ein EntityPageObjekt können mit der Methode
	 * getNavigationLinks() alle entsprechenden Links abgerufen werden. Dieser
	 * Methode ist dann ein einzelner String-Link zu übergeben. Über das
	 * Callback erhält man die entsprechende neue EntityPage
	 * 
	 * @param link String eines EntityPage NavigationsLink
	 * @param entityType der EntityType der Entitäten, die über diesen Link
	 *            abgerufen werden können
	 * @param classType der classType der Entitäten, die über diesen Link
	 *            abgerufen werden können
	 * @param apiKey der individuelle ApiKey, dient zur Authorisierung
	 * @param callback zum Erhalt der angeforderten EntityPage
	 */
	public static <T extends Entity> void loadEntitiesByNavigationLink(
			String link, Long entityType, Class<? extends Entity> classType, IPageCallback<T> callback) {
	
		GetEntitiesByNavigationLink<T> get = new GetEntitiesByNavigationLink<T>(
				link, entityType, classType, AppConstants.APIKEY, callback);
		get.execute();
	}
	
	
	/**
	 * Methode zum Laden einer Entität anhand eines Links (z.B. selfUrl)
	 * 
	 * @param link String mit dem URL-Link
	 * @param classType der Typ, der von diesem Link angefordert werden soll
	 * @param apiKey für die Authorisierung
	 * @param callback damit kann auf Entity nach dem Laden zugegriffen werden
	 */
	public static <T extends Entity> void loadEntityByLink(String link,
			Class<? extends Entity> classType, IReturnValueCallback<T> callback) {
	
		GetSingleEntityByLink<T> get = new GetSingleEntityByLink<T>(link,
				classType, AppConstants.APIKEY, callback);
		get.execute();
	}
	
	
	/**
	 * Methode, die es anhand der (vom Backend beim ersten Speichern vergebenen)
	 * EntityId ermöglicht, die Entity zu laden..
	 * 
	 * @param id die EntityId, wird beim Speichern im Backend vergeben und ist
	 *            für jede Entity eindeutig
	 * @param type der EntityType
	 * @param classType der ClassType der entsprechenden Entity
	 * @param appName wird für die Authorisierung benötigt
	 * @param apiKey wird für die Authorisierung benötigt
	 * @param callback Callback zum threadsicheren Zugriff auf die geladene
	 *            Entity
	 */
	public static <T extends Entity> void loadEntityById(int id, long type,
			Class<? extends Entity> classType, IReturnValueCallback<T> callback) {
	
		GetSingleEntityById<T> get = new GetSingleEntityById<T>(id, type,
				classType, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		get.execute();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T extends Entity> void loadSingleEntityProperty(T entity,
			String propertyName, IPropertyCallback callback) {
	
		GetSingleEntityProperty<T> get = new GetSingleEntityProperty<T>(
				propertyName, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		get.execute(entity);
	}
	
	
	public static <T extends Entity> void loadSingleEntityPropertyById(
			int entityId, String propertyName, IPropertyCallback callback) {
	
		GetSingleEntityPropertyById<T> get = new GetSingleEntityPropertyById<T>(
				entityId, propertyName, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		get.execute();
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T extends Entity> void deleteSingleEntityProperty(T entity,
			String propertyName, INoReturnValueCallback callback) {
	
		DeleteSingleEntityProperty<T> delete = new DeleteSingleEntityProperty<T>(
				propertyName, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		delete.execute(entity);
	}
	
	
	public static void deleteSingleEntityPropertyByEntityId(int entityId,
			String propertyName, INoReturnValueCallback callback) {
	
		DeleteSingleEntityPropertyByEntityId delete = new DeleteSingleEntityPropertyByEntityId(
				entityId, propertyName, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		delete.execute();
	}
	
	
	public static void deleteEntityById(int id,INoReturnValueCallback callback) {
	
		DeleteSingleEntityById delete = new DeleteSingleEntityById(id, AppConstants.APPNAME, AppConstants.APIKEY, callback);
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
	 * @param callback für
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Entity> void updateEntityWithNewEntity(
			T originalEntity, T newEntity, INoReturnValueCallback callback) {
	
		PutSingleEntity<T> put = new PutSingleEntity<T>(originalEntity,
				newEntity, AppConstants.APPNAME, AppConstants.APIKEY, callback);
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
	 * @param callback für
	 */
	public static <T extends Entity> void updateEntityByEntityIdWithNewEntity(
			int entityIdOfOriginalEntity, T newEntity, INoReturnValueCallback callback) {
	
		PutSingleEntityByEntityId<T> put = new PutSingleEntityByEntityId<T>(
				entityIdOfOriginalEntity, newEntity, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		put.execute();
		
	}
	
	
	/**
	 * Methode zum Updaten der Properties. Aktualisiert die Properties im
	 * Backend und im übergebenen Objekt. WICHTIG: Es können nur Properties
	 * geupdatet werden, die in der ursprünglichen Entity schon vorhanden sind.
	 * 
	 * @param entity Objekt der Entität, deren Properties aktualisiert werden
	 *            sollen.
	 * @param newProperties die neuen Properties, es dürfen nur bereits
	 *            vorhandene Properties aktualisiert werden
	 * @param appName zur Authorisierung
	 * @param apiKey zur Authorisierung
	 * @param callback für
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Entity> void updateEntityWithNewProperties(
			T entity, HashMap<String, Object> newProperties, INoReturnValueCallback callback) {
	
		PutSingleEntityProperties<T> put = new PutSingleEntityProperties<T>(
				newProperties, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		put.execute(entity);
	}
	
	
	public static <T extends Entity> void updateEntityWithNewPropertiesByEntityId(
			int entityId, HashMap<String, Object> newProperties, INoReturnValueCallback callback) {
	
		PutSingleEntityPropertiesById put = new PutSingleEntityPropertiesById(
				entityId, newProperties, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		put.execute();
	}
	
	
	public static <T extends Entity> void loadObjectEntityByLink(int entityId,
			int objectId, String linkType,
			Class<? extends Entity> classTypeOfObject, IReturnValueCallback<T> callback) {
	
		GetObjectEntityByLink<T> get = new GetObjectEntityByLink<T>(entityId,
				objectId, linkType, classTypeOfObject, AppConstants.APPNAME, AppConstants.APIKEY,
				callback);
		get.execute();
	}
	
	
	public static <T extends Entity> void loadAllEntitiesFromSameType(
			long type, String orderBy, int offset, int size,
			Class<? extends Entity> classType, IPageCallback<T> callback) {
	
		GetEntitiesByQueryString<T> get = new GetEntitiesByQueryString<T>("",
				type, orderBy, offset, size, classType, AppConstants.APPNAME, AppConstants.APIKEY,
				callback);
		get.execute();
	}
	
	public static <T extends Entity> void loadAllEntitiesIds(Long type,
			Class<? extends Entity> classType, IAllEntityIdsCallback callback) {
	
		GetAllEntitiesIds<T> get = new GetAllEntitiesIds<T>(type, classType,
				AppConstants.APPNAME, AppConstants.APIKEY, callback);
		get.execute();
	}
	
	
	public static <T extends Entity> void saveLink(T entityOne, T entityTwo,
			Link link, IPostLinkCallback callback) {
	
		PostSingleLink<T> post = new PostSingleLink<T>(link, entityOne,
				entityTwo, AppConstants.APPNAME, AppConstants.APIKEY,callback);
		post.execute();
		
	}
	
	
	public static void saveLinkByEntityIds(int entityId, int objectId,
			Link link, IPostLinkCallback callback) {
	
		PostSingleLinkByEntityIds post = new PostSingleLinkByEntityIds(
				entityId, objectId, link, AppConstants.APPNAME, AppConstants.APIKEY,callback);
		post.execute();
	}
	
	
	public static <T extends Entity> void saveLinkByEntityIdsAndLinkType(
			int entityId, int objectId, T entity, String linkType, IPostLinkCallback callback) {
	
		PostSingleLinkByEntityIdsAndLinkType<T> post = new PostSingleLinkByEntityIdsAndLinkType<T>(
				entityId, objectId, entity, linkType, AppConstants.APPNAME, AppConstants.APIKEY,callback);
		post.execute();
		
	}
	
	
	public static <T extends Entity> void updateSingleLinkProperties(
			int entityId, int objectId, String linkType,
			HashMap<String, Object> newProperties, T entity, INoReturnValueCallback callback) {
	
		PutSingleLinkProperties<T> put = new PutSingleLinkProperties<T>(
				entityId, objectId, linkType, entity, newProperties, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		put.execute();
	}
	
	
	
	public static <T extends Entity> void updateAllLinkPropertiesOfSameLinkType(
			int entityId, String linkType, T entity,
			HashMap<String, Object> newLinkProperties, INoReturnValueCallback callback) {
	
		PutNewPropertiesToLinksOfSameLinkType<T> put = new PutNewPropertiesToLinksOfSameLinkType<T>(
				entityId, linkType, entity, newLinkProperties, AppConstants.APPNAME, AppConstants.APIKEY,
				callback);
		put.execute();
	}
	
	
	public static <T extends Entity> void deleteSingleLink(int entityId,
			int objectId, String linkType, T entity, INoReturnValueCallback callback) {
	
		DeleteSingleLink<T> delete = new DeleteSingleLink<T>(entityId,
				objectId, linkType, entity, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		delete.execute();
	}
	
	
	public static <T extends Entity> void deleteAllLinkPropertiesOfSameLinkType(
			int entityId, String linkType, T entity, INoReturnValueCallback callback) {
	
		DeleteAllLinksOfSameType<T> delete = new DeleteAllLinksOfSameType<T>(
				entityId, linkType, entity, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		delete.execute();
	}
	
	
	public static <T extends Entity> void deleteSingleLinkPropertyOfSameLinkType(
			int entityId, int objectId, String linkType, T entity,
			String propertyNameToDelete, INoReturnValueCallback callback) {
	
		DeleteSingleLinkProperty<T> delete = new DeleteSingleLinkProperty<T>(
				entityId, objectId, linkType, entity, propertyNameToDelete,
				AppConstants.APPNAME, AppConstants.APIKEY, callback);
		delete.execute();
	}
	
	
	public static <T extends Entity> void updateLinkPropertyAddAditionalProperties(
			int entityId, int objectId, String linkType, T entity,
			HashMap<String, Object> newLinkProperties, INoReturnValueCallback callback) {
	
		PutAdditionalPropertyToLinksOfSameLinkType<T> put = new PutAdditionalPropertyToLinksOfSameLinkType<T>(
				entityId, objectId, linkType, entity, newLinkProperties,
				AppConstants.APPNAME, AppConstants.APIKEY, callback);
		put.execute();
	}
	
	
	public static <T extends Entity> void loadAllLinksFromSameTypeOfAnEntity(
			T entity, String linkType, ILinkPageCallback callback) {
	
		GetAllLinksFromSameTypeOfAnEntity<T> get = new GetAllLinksFromSameTypeOfAnEntity<T>(
				entity, linkType, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		get.execute();
	}
	
	
	public static void loadLinksByEntityPageNavigationLink(String navLink, ILinkPageCallback callback) {
	
		GetLinkPageByNavigationLink get = new GetLinkPageByNavigationLink(
				navLink, AppConstants.APIKEY, callback);
		get.execute();
	}
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static <T extends Entity> void loadEntitiesFromTheSameLinkType(
			int entityId, String linkType, IEntityPageWithEntityCallback callback) {
	
		GetAllEntitiesFromSameLinkType<T> get = new GetAllEntitiesFromSameLinkType<T>(
				entityId, linkType, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		get.execute();
	}
	
	
	public static void saveOrUpdateEntityImageByIntRessource(int entityId,
			String imagePropertyName, Context context, int ressourceOfImage,
			INoReturnValueCallback callback) {
	
		Image image = new Image(context, ressourceOfImage);
		
		PutEntityImage put = new PutEntityImage(entityId, imagePropertyName,
				image, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		put.execute();
	}
	
	
	public static void saveOrUpdateEntityImageByInputStream(int entityId,
			String imagePropertyName, Context context, InputStream inputStream, INoReturnValueCallback callback) {
	
		Image image = new Image(context, inputStream);
		
		PutEntityImage put = new PutEntityImage(entityId, imagePropertyName,
				image, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		put.execute();
	}
	
	
	public static void deleteEntityImage(int entityId,
			String imagePropertyName, INoReturnValueCallback callback) {
	
		DeleteEntityImage delete = new DeleteEntityImage(entityId,
				imagePropertyName, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		delete.execute();
	}
	
	
	public static void loadEntityImage(int entityId, String imagePropertyName,
			int width, int height, int radius, String backgroundColor, IImageCallback callback) {
	
		GetEntityImage get = new GetEntityImage(entityId, imagePropertyName,
				width, height, radius, backgroundColor, AppConstants.APPNAME, AppConstants.APIKEY,
				callback);
		get.execute();
	}
	
	
	public static void loadEntityImageByImageLink(String imagePropertyLink,
			int width, int height, int radius, String backgroundColor, IImageCallback callback) {
	
		GetEntityImageByImageLink get = new GetEntityImageByImageLink(
				imagePropertyLink, width, height, radius, backgroundColor,
				AppConstants.APPNAME, AppConstants.APIKEY, callback);
		get.execute();
	}
	
	
	
	
	
}
'''
		
	}
	



}