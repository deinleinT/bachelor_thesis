package org.xtext.orcasdk.entitymodel.generator.backend

import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity
import java.util.HashMap

class CreateIndividualPersistenceWrapper {
	
	static HashMap<String,String> check = new HashMap<String,String>
	
	
def static compileIndividualEntityWrapper(AndroidEntity entity, String packagename) {	
		'''
package «packagename».persistence.backend.«entity.name»;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.InputStream;
import android.content.Context;
import de.fhws.sdk.orca.network.asynctask.image.delete.DeleteEntityImageAndDeleteLinkFromEntity;
import de.fhws.sdk.orca.network.asynctask.image.get.GetEntityImage;
import de.fhws.sdk.orca.network.asynctask.image.get.GetEntityImageByImageLink;
import de.fhws.sdk.orca.network.asynctask.image.put.Image;
import de.fhws.sdk.orca.network.asynctask.image.put.PutEntityImage;
import de.fhws.sdk.orca.network.callback.IImageCallback;
import de.fhws.sdk.orca.network.asynctask.link.delete.DeleteAllLinksOfSameType;
import de.fhws.sdk.orca.network.asynctask.link.delete.DeleteSingleLink;
import de.fhws.sdk.orca.network.asynctask.link.delete.DeleteSingleLinkProperty;
import de.fhws.sdk.orca.network.asynctask.link.get.GetAllLinksFromSameTypeOfAnEntity;
import de.fhws.sdk.orca.network.asynctask.link.get.GetLinkPageByNavigationLink;
import de.fhws.sdk.orca.network.asynctask.link.put.PutAdditionalPropertyToLinksOfSameLinkType;
import de.fhws.sdk.orca.network.asynctask.link.put.PutNewPropertiesToLinksOfSameLinkType;
import de.fhws.sdk.orca.network.asynctask.link.put.PutSingleLinkProperties;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.model.Link;
import java.util.HashSet;
import de.fhws.sdk.orca.network.asynctask.entity.put.PutSeveralEntities;
import de.fhws.sdk.orca.link.PostLinksFromSameType«packagename.replace(".","")»«entity.name.toFirstUpper»;
import de.fhws.sdk.orca.network.asynctask.entity.delete.DeleteSingleEntityById;
import de.fhws.sdk.orca.network.asynctask.entity.delete.DeleteSingleEntityProperty;
import de.fhws.sdk.orca.network.asynctask.entity.get.GetAllEntitiesIds;
import de.fhws.sdk.orca.network.asynctask.entity.get.GetEntitiesByAttachedEntity;
import de.fhws.sdk.orca.network.asynctask.entity.get.GetEntitiesByNavigationLink;
import de.fhws.sdk.orca.network.asynctask.entity.get.GetEntitiesByQueryString;
import de.fhws.sdk.orca.network.asynctask.entity.get.GetSingleEntityById;
import de.fhws.sdk.orca.network.asynctask.entity.get.GetSingleEntityByLink;
import de.fhws.sdk.orca.network.asynctask.entity.post.PostSingleEntityWithCheckWhetherEntityStillExists;
import de.fhws.sdk.orca.network.asynctask.entity.post.PostSingleEntityWithoutCheck;
import de.fhws.sdk.orca.network.asynctask.link.post.PostSingleLink;
import de.fhws.sdk.orca.network.asynctask.entity.put.PutSingleEntity;
import de.fhws.sdk.orca.network.asynctask.entity.put.PutSingleEntityProperties;
import de.fhws.sdk.orca.network.asynctask.link.get.GetObjectEntityByLink;
import de.fhws.sdk.orca.network.callback.INoReturnValueCallback;
import de.fhws.sdk.orca.network.callback.IPageCallback;
import de.fhws.sdk.orca.network.callback.IReturnValueCallback;
import de.fhws.sdk.orca.network.callback.IAllEntityIdsCallback;
import de.fhws.sdk.orca.network.callback.ILinkPageCallback;
import de.fhws.sdk.orca.network.callback.IPostLinkCallback;
import de.fhws.sdk.orca.AppConstants;
«FOR attributes : entity.attributes»
«IF attributes.type.equalsIgnoreCase("date")»
«check.put("date","import java.util.Date;")»
«ENDIF»
«ENDFOR»
«IF check.containsKey("date")»
«check.get("date")»
«check.clear»
«ENDIF»
/**
 * @author ThomasDeinlein
 */
public class «entity.name.toFirstUpper»PersistenceWrapper {
	
	
	public static void save«entity.name.toFirstUpper»WithCheckWhether«entity.name.toFirstUpper»StillExists(
			«packagename».«entity.name.toFirstUpper» «entity.name», IReturnValueCallback<«packagename».«entity.name.toFirstUpper»> callback) {
	
		PostSingleEntityWithCheckWhetherEntityStillExists<«packagename».«entity.name.toFirstUpper»> post = new PostSingleEntityWithCheckWhetherEntityStillExists<«packagename».«entity.name.toFirstUpper»>(
				AppConstants.APPNAME, AppConstants.APIKEY, callback);
		post.execute(«entity.name»);
	}
	
	public static void save«entity.name.toFirstUpper»WithCheckWhether«entity.name.toFirstUpper»StillExistsWithProperties(«FOR attribute : entity.attributes»«IF !attribute.type.equalsIgnoreCase("link") && !attribute.type.equalsIgnoreCase("image")»«attribute.type.toFirstUpper» «attribute.name»,«ENDIF»«ENDFOR» IReturnValueCallback<«packagename».«entity.name.toFirstUpper»> callback) {
	
		«packagename».«entity.name.toFirstUpper» temp = new «packagename».«entity.name.toFirstUpper»();
	
		«FOR attribute : entity.attributes»
		«IF !attribute.type.equalsIgnoreCase("link") && !attribute.type.equalsIgnoreCase("image")»
		if(«attribute.name»!=null){
			temp.set«attribute.name.toFirstUpper»(«attribute.name»);
		}
		«ENDIF»
		«ENDFOR»
	
		if(temp.getProperties().size()!=0){
			PostSingleEntityWithCheckWhetherEntityStillExists<«packagename».«entity.name.toFirstUpper»> post = new PostSingleEntityWithCheckWhetherEntityStillExists<«packagename».«entity.name.toFirstUpper»>(
					AppConstants.APPNAME, AppConstants.APIKEY, callback);
			post.execute(temp);
		}
	}
	
	public static void save«entity.name.toFirstUpper»WithProperties(«FOR attribute : entity.attributes»«IF !attribute.type.equalsIgnoreCase("link") && !attribute.type.equalsIgnoreCase("image")»«attribute.type.toFirstUpper» «attribute.name»,«ENDIF»«ENDFOR» IReturnValueCallback<«packagename».«entity.name.toFirstUpper»> callback) {
	
		«packagename».«entity.name.toFirstUpper» temp = new «packagename».«entity.name.toFirstUpper»();
	
		«FOR attribute : entity.attributes»
		«IF !attribute.type.equalsIgnoreCase("link") && !attribute.type.equalsIgnoreCase("image")»
		if(«attribute.name»!=null){
			temp.set«attribute.name.toFirstUpper»(«attribute.name»);
		}
		«ENDIF»
		«ENDFOR»
	
		PostSingleEntityWithoutCheck<«packagename».«entity.name.toFirstUpper»> post = new PostSingleEntityWithoutCheck<«packagename».«entity.name.toFirstUpper»>(
				AppConstants.APPNAME, AppConstants.APIKEY, callback);
		post.execute(temp);
	}
	
	
	public static void save«entity.name.toFirstUpper»WithoutCheck(«packagename».«entity.name.toFirstUpper» «entity.name», IReturnValueCallback<«packagename».«entity.name.toFirstUpper»> callback) {
	
		PostSingleEntityWithoutCheck<«packagename».«entity.name.toFirstUpper»> post = new PostSingleEntityWithoutCheck<«packagename».«entity.name.toFirstUpper»>(
				AppConstants.APPNAME, AppConstants.APIKEY, callback);
		post.execute(«entity.name»);
	}
	
	
	public static void load«entity.name.toFirstUpper»sByAttached«entity.name.toFirstUpper»Object(
			«packagename».«entity.name.toFirstUpper» «entity.name», IPageCallback<«packagename».«entity.name.toFirstUpper»> callback) {
	
		GetEntitiesByAttachedEntity<«packagename».«entity.name.toFirstUpper»> get = new GetEntitiesByAttachedEntity<«packagename».«entity.name.toFirstUpper»>(
				AppConstants.APPNAME, AppConstants.APIKEY, callback);
		get.execute(«entity.name»);
	}
	
	
	public static void load«entity.name.toFirstUpper»sByQueryString(
			String queryString, String orderBy, int offset,
			int size, IPageCallback<«packagename».«entity.name.toFirstUpper»> callback) {
	
		GetEntitiesByQueryString<«packagename».«entity.name.toFirstUpper»> get = new GetEntitiesByQueryString<«packagename».«entity.name.toFirstUpper»>(
				queryString, «packagename».EntityTypeConstants.TYPE_«packagename.replace(".","_").toUpperCase»_«entity.name.toUpperCase», orderBy, offset, size, «packagename».«entity.name.toFirstUpper».class, AppConstants.APPNAME,
				AppConstants.APIKEY, callback);
		get.execute();
	}
	
	
	public static void load«entity.name.toFirstUpper»sByNavigationLink(
			String link, IPageCallback<«packagename».«entity.name.toFirstUpper»> callback) {
	
		GetEntitiesByNavigationLink<«packagename».«entity.name.toFirstUpper»> get = new GetEntitiesByNavigationLink<«packagename».«entity.name.toFirstUpper»>(
				link, «packagename».EntityTypeConstants.TYPE_«packagename.replace(".","_").toUpperCase»_«entity.name.toUpperCase», «packagename».«entity.name.toFirstUpper».class, AppConstants.APIKEY, callback);
		get.execute();
	}
	
	
	public static void load«entity.name.toFirstUpper»ByLink(String link,
			IReturnValueCallback<«packagename».«entity.name.toFirstUpper»> callback) {
	
		GetSingleEntityByLink<«packagename».«entity.name.toFirstUpper»> get = new GetSingleEntityByLink<«packagename».«entity.name.toFirstUpper»>(link,
				«packagename».«entity.name.toFirstUpper».class, AppConstants.APIKEY, callback);
		get.execute();
	}
	
	
	public static void load«entity.name.toFirstUpper»ById(int id, IReturnValueCallback<«packagename».«entity.name.toFirstUpper»> callback) {
	
		GetSingleEntityById<«packagename».«entity.name.toFirstUpper»> get = new GetSingleEntityById<«packagename».«entity.name.toFirstUpper»>(id, «packagename».EntityTypeConstants.TYPE_«packagename.replace(".","_").toUpperCase»_«entity.name.toUpperCase»,
				«packagename».«entity.name.toFirstUpper».class, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		get.execute();
		
	}
	
	
	public static void deleteSingle«entity.name.toFirstUpper»Property(«packagename».«entity.name.toFirstUpper» «entity.name»,
			String propertyName, INoReturnValueCallback callback) {
	
		DeleteSingleEntityProperty<«packagename».«entity.name.toFirstUpper»> delete = new DeleteSingleEntityProperty<«packagename».«entity.name.toFirstUpper»>(
				propertyName, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		delete.execute(«entity.name»);
	}
	
	
	public static void delete«entity.name.toFirstUpper»ById(int id, INoReturnValueCallback callback) {
	
		DeleteSingleEntityById delete = new DeleteSingleEntityById(id, AppConstants.APPNAME,
				AppConstants.APIKEY, callback);
		delete.execute();
	}
	
	
	public static void update«entity.name.toFirstUpper»WithNew«entity.name.toFirstUpper»(
			«packagename».«entity.name.toFirstUpper» original«entity.name.toFirstUpper», «packagename».«entity.name.toFirstUpper» new«entity.name.toFirstUpper», INoReturnValueCallback callback) {
	
		PutSingleEntity<«packagename».«entity.name.toFirstUpper»> put = new PutSingleEntity<«packagename».«entity.name.toFirstUpper»>(original«entity.name.toFirstUpper»,
				new«entity.name.toFirstUpper», AppConstants.APPNAME, AppConstants.APIKEY, callback);
		put.execute(original«entity.name.toFirstUpper»);
		
	}
	
	public static void updateSeveral«entity.name.toFirstUpper»sAtBackendWithAll«entity.name.toFirstUpper»sFromLocalDatabase(ArrayList<«packagename».«entity.name.toFirstUpper»> entityList,
			INoReturnValueCallback callback) {
	
		PutSeveralEntities<«packagename».«entity.name.toFirstUpper»> put = new PutSeveralEntities<«packagename».«entity.name.toFirstUpper»>(entityList,
				AppConstants.APPNAME, AppConstants.APIKEY, callback);
		put.execute();
		
	}
	
	public static void update«entity.name.toFirstUpper»WithNewProperties(«packagename».«entity.name.toFirstUpper» «entity.name», HashMap<String, Object> newProperties, INoReturnValueCallback callback) {
	
		PutSingleEntityProperties<«packagename».«entity.name.toFirstUpper»> put = new PutSingleEntityProperties<«packagename».«entity.name.toFirstUpper»>(
				newProperties, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		put.execute(«entity.name»);
	}
	
	public static <T extends Entity> void loadObjectEntityByLink(int «entity.name.toFirstUpper»Id,
			int objectId, String linkType,
			Class<? extends Entity> classTypeOfObject, IReturnValueCallback<T> callback) {
	
		GetObjectEntityByLink<T> get = new GetObjectEntityByLink<T>(«entity.name.toFirstUpper»Id,
				objectId, linkType, classTypeOfObject, AppConstants.APPNAME, AppConstants.APIKEY,
				callback);
		get.execute();
	}
	
	
	public static void loadAll«entity.name.toFirstUpper»sFromSameType(
			String orderBy, int offset, int size,
			IPageCallback<«packagename».«entity.name.toFirstUpper»> callback) {
	
		GetEntitiesByQueryString<«packagename».«entity.name.toFirstUpper»> get = new GetEntitiesByQueryString<«packagename».«entity.name.toFirstUpper»>("",
				«packagename».EntityTypeConstants.TYPE_«packagename.replace(".","_").toUpperCase»_«entity.name.toUpperCase», orderBy, offset, size, «packagename».«entity.name.toFirstUpper».class, AppConstants.APPNAME, AppConstants.APIKEY,
				callback);
		get.execute();
	}	
	
	
	public static void loadAll«entity.name.toFirstUpper»sIds(IAllEntityIdsCallback callback) {
	
		GetAllEntitiesIds<«packagename».«entity.name.toFirstUpper»> get = new GetAllEntitiesIds<«packagename».«entity.name.toFirstUpper»>(«packagename».EntityTypeConstants.TYPE_«packagename.replace(".","_").toUpperCase»_«entity.name.toUpperCase», «packagename».«entity.name.toFirstUpper».class,
				AppConstants.APPNAME, AppConstants.APIKEY, callback);
		get.execute();
	}
	
	
	//ab hier alles LinkMethoden
	
	public static <T extends Entity> void saveLinkWithPropertiesByEntityIds(T entityOne, T entityTwo,
			Link link, IPostLinkCallback callback) {
	
		PostSingleLink<T> post = new PostSingleLink<T>(link, entityOne,
				entityTwo, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		post.execute();
		
	}
	
	
	public static <T extends Entity> void saveLinkOfSameTypeWithPropertiesToSeveralObjectEntities(Link link, «packagename».«entity.name.toFirstUpper» entityOne,
			HashSet<T> objectEntities, IPostLinkCallback callback){
		
		PostLinksFromSameType«packagename.replace(".","")»«entity.name.toFirstUpper»<T> post = new PostLinksFromSameType«packagename.replace(".","")»«entity.name.toFirstUpper»<T>(link, entityOne, objectEntities, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		post.execute();
	}
	
	
	public static void updateSingleLinkProperties(int «entity.name»Id, int objectId, String linkType,
			HashMap<String, Object> newProperties, «packagename».«entity.name.toFirstUpper» «entity.name», INoReturnValueCallback callback) {
	
		PutSingleLinkProperties<«packagename».«entity.name.toFirstUpper»> put = new PutSingleLinkProperties<«packagename».«entity.name.toFirstUpper»>(«entity.name»Id, objectId, linkType, «entity.name», newProperties, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		put.execute();
	}
	
	
	public static void updateLinkPropertyAddAditionalProperties(int «entity.name»Id,
			int objectId, String linkType, «packagename».«entity.name.toFirstUpper» «entity.name»,
			HashMap<String, Object> newLinkProperties, INoReturnValueCallback callback) {
	
		PutAdditionalPropertyToLinksOfSameLinkType<«packagename».«entity.name.toFirstUpper»> put = new PutAdditionalPropertyToLinksOfSameLinkType<«packagename».«entity.name.toFirstUpper»>(
				«entity.name»Id, objectId, linkType, «entity.name», newLinkProperties, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		put.execute();
	}
	
	
	public static void updateAllLinkPropertiesOfSameLinkType(int «entity.name»Id,
			String linkType, «packagename».«entity.name.toFirstUpper» entity, HashMap<String, Object> newLinkProperties, INoReturnValueCallback callback) {
	
		PutNewPropertiesToLinksOfSameLinkType<«packagename».«entity.name.toFirstUpper»> put = new PutNewPropertiesToLinksOfSameLinkType<«packagename».«entity.name.toFirstUpper»>(
				«entity.name»Id, linkType, entity, newLinkProperties, AppConstants.APPNAME, AppConstants.APIKEY,
				callback);
		put.execute();
	}
	
	
	public static void deleteSingleLink(int «entity.name»Id, int objectId,
			String linkType, «packagename».«entity.name.toFirstUpper» «entity.name», INoReturnValueCallback callback) {
	
		DeleteSingleLink<«packagename».«entity.name.toFirstUpper»> delete = new DeleteSingleLink<«packagename».«entity.name.toFirstUpper»>(«entity.name»Id, objectId,
				linkType, «entity.name», AppConstants.APPNAME, AppConstants.APIKEY, callback);
		delete.execute();
	}
	
	
	public static void deleteAllLinkPropertiesOfSameLinkType(int «entity.name»Id,
			String linkType, «packagename».«entity.name.toFirstUpper» «entity.name», INoReturnValueCallback callback) {
	
		DeleteAllLinksOfSameType<«packagename».«entity.name.toFirstUpper»> delete = new DeleteAllLinksOfSameType<«packagename».«entity.name.toFirstUpper»>(
				«entity.name»Id, linkType, «entity.name», AppConstants.APPNAME, AppConstants.APIKEY, callback);
		delete.execute();
	}
	
	public static void deleteSingleLinkPropertyOfSameLinkType(int «entity.name»Id,
			int objectId, String linkType, «packagename».«entity.name.toFirstUpper» «entity.name», String propertyNameToDelete, INoReturnValueCallback callback) {
	
		DeleteSingleLinkProperty<«packagename».«entity.name.toFirstUpper»> delete = new DeleteSingleLinkProperty<«packagename».«entity.name.toFirstUpper»>(
				«entity.name»Id, objectId, linkType, «entity.name», propertyNameToDelete, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		delete.execute();
	}
	
	
	public static <T extends Entity> void loadAllLinksFromSameTypeOfAnEntity(T «entity.name»,
			String linkType, ILinkPageCallback callback) {
	
		GetAllLinksFromSameTypeOfAnEntity<T> get = new GetAllLinksFromSameTypeOfAnEntity<T>(
				«entity.name», linkType, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		get.execute();
	}
	
	public static void loadLinksByNavigationLink(String navLink, ILinkPageCallback callback) {
	
		GetLinkPageByNavigationLink get = new GetLinkPageByNavigationLink(
				navLink, AppConstants.APIKEY, callback);
		get.execute();
	}
	
	
	//ab hier images
	public static void saveOrUpdate«entity.name.toFirstUpper»ImageByIntRessource(int «entity.name»Id,
			String imagePropertyName, Context context, int ressourceOfImage, INoReturnValueCallback callback) {
	
		Image image = new Image(context, ressourceOfImage);
		
		PutEntityImage put = new PutEntityImage(«entity.name»Id, imagePropertyName,
				image, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		put.execute();
	}
	
	
	public static void saveOrUpdate«entity.name.toFirstUpper»ImageByInputStream(int «entity.name»Id,
			String imagePropertyName, Context context, InputStream inputStream, INoReturnValueCallback callback) {
	
		Image image = new Image(context, inputStream);
		
		PutEntityImage put = new PutEntityImage(«entity.name»Id, imagePropertyName,
				image, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		put.execute();
	}
	
	
	public static <T extends Entity> void delete«entity.name.toFirstUpper»Image(T «entity.name»,
			String imagePropertyName, INoReturnValueCallback callback) {
	
		DeleteEntityImageAndDeleteLinkFromEntity<T> delete = new DeleteEntityImageAndDeleteLinkFromEntity<T>(«entity.name»,
				imagePropertyName, AppConstants.APPNAME, AppConstants.APIKEY, callback);
		delete.execute();
	}
	
	
	public static void load«entity.name.toFirstUpper»Image(int «entity.name»Id, String imagePropertyName,
			int width, int height, int radius, String backgroundColor, IImageCallback callback) {
	
		GetEntityImage get = new GetEntityImage(«entity.name»Id, imagePropertyName,
				width, height, radius, backgroundColor, AppConstants.APPNAME, AppConstants.APIKEY,
				callback);
		get.execute();
	}
	
	
	public static void load«entity.name.toFirstUpper»ImageByImageLink(String imagePropertyLink,
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