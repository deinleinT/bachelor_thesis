
package de.fhws.sdk.orca.network.asynctask.link;

import java.util.HashMap;

import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.model.Link;
import de.fhws.sdk.orca.network.asynctask.entity.get.GetAllEntitiesFromSameLinkType;
import de.fhws.sdk.orca.network.asynctask.link.delete.DeleteAllLinksOfSameType;
import de.fhws.sdk.orca.network.asynctask.link.delete.DeleteSingleLink;
import de.fhws.sdk.orca.network.asynctask.link.delete.DeleteSingleLinkProperty;
import de.fhws.sdk.orca.network.asynctask.link.get.GetAllLinksFromSameTypeOfAnEntity;
import de.fhws.sdk.orca.network.asynctask.link.get.GetLinkPageByNavigationLink;
import de.fhws.sdk.orca.network.asynctask.link.post.PostSingleLink;
import de.fhws.sdk.orca.network.asynctask.link.put.PutAdditionalPropertyToLinksOfSameLinkType;
import de.fhws.sdk.orca.network.asynctask.link.put.PutNewPropertiesToLinksOfSameLinkType;
import de.fhws.sdk.orca.network.asynctask.link.put.PutSingleLinkProperties;
import de.fhws.sdk.orca.network.callback.IEntityPageWithEntityCallback;
import de.fhws.sdk.orca.network.callback.ILinkPageCallback;
import de.fhws.sdk.orca.network.callback.INoReturnValueCallback;
import de.fhws.sdk.orca.network.callback.IPostLinkCallback;

public class LinkPersistenceHandler {
	
	public static <T extends Entity> void saveLink(T entityOne, T entityTwo,
			Link link, String appName, String apiKey, IPostLinkCallback callback) {
	
		PostSingleLink<T> post = new PostSingleLink<T>(link, entityOne,
				entityTwo, appName, apiKey, callback);
		post.execute();
		
	}
	
	/**
	 * alle Properties, für die null gesetzt wird, werden gelöscht, die anderen
	 * werden entsprechend geändert
	 * 
	 * @param entityId
	 * @param objectId
	 * @param linkType
	 * @param newProperties
	 * @param entity
	 * @param appName
	 * @param apiKey
	 * @param callback
	 */
	public static <T extends Entity> void updateSingleLinkProperties(
			int entityId, int objectId, String linkType,
			HashMap<String, Object> newProperties, T entity, String appName,
			String apiKey, INoReturnValueCallback callback) {
	
		PutSingleLinkProperties<T> put = new PutSingleLinkProperties<T>(
				entityId, objectId, linkType, entity, newProperties, appName,
				apiKey, callback);
		put.execute();
	}
	
	/**
	 * nur die übergebenen Properties werden geupdatet, alle anderen Properties
	 * werden gelöscht, von allen entsprechenden Linktypen Ist für das sezten
	 * von Startwerten geeignet
	 */
	public static <T extends Entity> void updateAllLinkPropertiesOfSameLinkType(
			int entityId, String linkType, T entity,
			HashMap<String, Object> newLinkProperties, String appName,
			String apiKey, INoReturnValueCallback callback) {
	
		PutNewPropertiesToLinksOfSameLinkType<T> put = new PutNewPropertiesToLinksOfSameLinkType<T>(
				entityId, linkType, entity, newLinkProperties, appName, apiKey,
				callback);
		put.execute();
	}
	
	public static <T extends Entity> void deleteSingleLink(int entityId,
			int objectId, String linkType, T entity, String appName,
			String apiKey, INoReturnValueCallback callback) {
	
		DeleteSingleLink<T> delete = new DeleteSingleLink<T>(entityId,
				objectId, linkType, entity, appName, apiKey, callback);
		delete.execute();
	}
	
	/**
	 * löschen aller Links vom gleichen LinkType
	 */
	public static <T extends Entity> void deleteAllLinkPropertiesOfSameLinkType(
			int entityId, String linkType, T entity, String appName,
			String apiKey, INoReturnValueCallback callback) {
	
		DeleteAllLinksOfSameType<T> delete = new DeleteAllLinksOfSameType<T>(
				entityId, linkType, entity, appName, apiKey, callback);
		delete.execute();
	}
	
	public static <T extends Entity> void deleteSingleLinkPropertyOfSameLinkType(
			int entityId, int objectId, String linkType, T entity,
			String propertyNameToDelete, String appName, String apiKey,
			INoReturnValueCallback callback) {
	
		DeleteSingleLinkProperty<T> delete = new DeleteSingleLinkProperty<T>(
				entityId, objectId, linkType, entity, propertyNameToDelete,
				appName, apiKey, callback);
		delete.execute();
	}
	
	public static <T extends Entity> void updateLinkPropertyAddAditionalProperties(
			int entityId, int objectId, String linkType, T entity,
			HashMap<String, Object> newLinkProperties, String appName,
			String apiKey, INoReturnValueCallback callback) {
	
		PutAdditionalPropertyToLinksOfSameLinkType<T> put = new PutAdditionalPropertyToLinksOfSameLinkType<T>(
				entityId, objectId, linkType, entity, newLinkProperties,
				appName, apiKey, callback);
		put.execute();
	}
	
	public static <T extends Entity> void loadAllLinksFromSameTypeOfAnEntity(
			T entity, String linkType, String appName, String apiKey,
			ILinkPageCallback callback) {
	
		GetAllLinksFromSameTypeOfAnEntity<T> get = new GetAllLinksFromSameTypeOfAnEntity<T>(
				entity, linkType, appName, apiKey, callback);
		get.execute();
	}
	
	public static void loadLinksByEntityPageNavigationLink(String navLink,
			String apiKey, ILinkPageCallback callback) {
	
		GetLinkPageByNavigationLink get = new GetLinkPageByNavigationLink(
				navLink, apiKey, callback);
		get.execute();
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static <T extends Entity> void loadEntitiesFromTheSameLinkType(
			int entityId, String linkType, String appName, String apiKey,
			IEntityPageWithEntityCallback callback) {
	
		GetAllEntitiesFromSameLinkType<T> get = new GetAllEntitiesFromSameLinkType<T>(
				entityId, linkType, appName, apiKey, callback);
		get.execute();
	}
}
