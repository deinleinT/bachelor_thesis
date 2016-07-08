package org.xtext.orcasdk.entitymodel.generator.link

import org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute
import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity
import org.xtext.orcasdk.entitymodel.entityModel.LinkProperties

class CreateEntityLinkProperties {
	
def static compilelinks(AndroidAttribute attribute, AndroidEntity androidEntity, String packagename){	
'''
	
//ab hier alles Link «attribute.name»

/**
* Methode zum Abrufen aller in dieser Entity vorhanden Links vom Typ "«attribute.name»".
* Hierbei erfolgt keine Anfrage ans Backend. Es werden nur die in dieser Entity vorhandenen 
* Links zurückgegeben.
* 
* @return ArrayList mit allen enthaltenen Links vom angegebenen Typ als
*         String
*/
@JsonIgnore
public ArrayList<String> getAll«attribute.name.toFirstUpper»LinksAsString(){
	return super.getLinksFromSameTypeAsStrings("«attribute.name»");
}

/**
* NUR BACKEND: Methode zum Abrufen aller Entities vom Orca-Backend, auf die der Link vom Typ "«attribute.name»" zeigt. 
* BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/links/{linkType}/objects
* 
* @param callback Callback zum Abfragen der Ergebnis-EntityPage, des
*            Http-Response-StatusCodes und einer ErrorMessage. Über die
*            EntityPage-Methode getEntities() können alle gefundenen
*            Entites abgerufen werden.
*/
@JsonIgnore
public void BACKENDload«attribute.objectType.name.toFirstUpper»sFromBackendBy«attribute.name.toFirstUpper»Links(IEntityPageWithEntityCallback<«packagename».«attribute.objectType.name.toFirstUpper»> callback){
	de.fhws.sdk.orca.link.GetAll«attribute.objectType.name.toFirstUpper»sFromLink«attribute.name.toFirstUpper»BelongsToClass«packagename.replace(".","")»«androidEntity.name.toFirstUpper»<«attribute.objectType.name.toFirstUpper»> get = new de.fhws.sdk.orca.link.GetAll«attribute.objectType.name.toFirstUpper»sFromLink«attribute.name.toFirstUpper»BelongsToClass«packagename.replace(".","")»«androidEntity.name.toFirstUpper»<«packagename».«attribute.objectType.name.toFirstUpper»>(this.getId(),
		"«attribute.name»", «packagename».«attribute.objectType.name.toFirstUpper».class, de.fhws.sdk.orca.AppConstants.APPNAME, de.fhws.sdk.orca.AppConstants.APIKEY, callback);
	get.execute();
}

/**
* NUR BACKEND: Methode zum Abrufen einer «attribute.objectType.name.toFirstUpper»Entity vom Orca-Backend anhand des Links vom Typ "«attribute.name»".
* Die Id der abzurufenden Entity ist anzugeben.
* 
* BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/links/{type}/{objectId}
* 
* @param objectEntityId die Id der «attribute.objectType.name.toFirstUpper»Entity die vom Backend abgefragt werden soll
* @param callback Callback zum Abrufen der «attribute.objectType.name.toFirstUpper»Entity, des Http-StatusCode und der ErrorMessage
*/
@JsonIgnore
public void BACKENDload«attribute.objectType.name.toFirstUpper»FromBackendBy«attribute.name.toFirstUpper»LinkAndObjectEntityId(int objectEntityId, IReturnValueCallback<«packagename».«attribute.objectType.name.toFirstUpper»> callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.<«packagename».«attribute.objectType.name.toFirstUpper»>loadObjectEntityByLink(this.getId(), objectEntityId, "«attribute.name»", «packagename».«attribute.objectType.name.toFirstUpper».class, callback);
}

/**
* NUR BACKEND: Methode zum Anlegen eines neuen Links vom Typ "«attribute.name»" im Orca-Backend. Alle
* verfügbaren LinkProperties können entsprechend gesetzt werden. ACHTUNG:
* Wird für alle Properties null angegeben, entfällt der LinkPost.
* BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/links/{type}/{objectId}
* 
* @param objectEntity die ObjectEntity, auf die der Link zeigen soll
* @param callback Callback zum Abruf des Http-statusCode, der angelegten
*            selfURL für diesen Link und eine errorMessage
*/
@JsonIgnore
public void BACKENDsave«attribute.name.toFirstUpper»SingleLinkToBackendWithProperties(«packagename».«attribute.objectType.name.toFirstUpper» objectEntity, «FOR linkprops: attribute.linkproperties»«linkprops.type.toFirstUpper+" "+linkprops.name+", "»«ENDFOR» IPostLinkCallback callback){
	HashMap<String,Object> properties = new HashMap<String,Object>();
	«FOR linkprops: attribute.linkproperties»
	if(«linkprops.name» != null){
		«"properties.put(\""+linkprops.name+"\", "+linkprops.name+");"»
	}
	«ENDFOR»
	
	if(properties.size()!=0){
		Link link = new Link();
		link.setType("«attribute.name»");
		link.setProperties(properties);

		«androidEntity.name.toFirstUpper»PersistenceWrapper.saveLinkWithPropertiesByEntityIds(this, objectEntity, link, callback);
	}
}

/**
* NUR BACKEND: Methode zum Updaten der Properties eines Links vom Typ "«attribute.name»" im Orca-Backend. Alle
* verfügbaren LinkProperties können entsprechend aktualisiert werden. ACHTUNG:
* Wird für alle Properties null angegeben, wird kein Update durchgeführt.
* BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/links/{type}/{objectId}
* 
* @param objectEntity die ObjectEntity, auf die der Link zeigen soll
* @param callback Callback zum Abruf des Http-statusCode, der angelegten
* selfURL für diesen Link und eine errorMessage
*/
@JsonIgnore
public void BACKENDupdate«attribute.name.toFirstUpper»LinkPropertiesAtBackendAndThisObject(«packagename».«attribute.objectType.name.toFirstUpper» objectEntity, «FOR linkprops: attribute.linkproperties»«linkprops.type.toFirstUpper+" "+linkprops.name+","»«ENDFOR» INoReturnValueCallback callback){
	HashMap<String,Object> properties = new HashMap<String,Object>();
	«FOR linkprops: attribute.linkproperties»
	if(«linkprops.name» != null){
		«"properties.put(\""+linkprops.name+"\", "+linkprops.name+");"»
	}
	«ENDFOR»
	
	if(properties.size()!=0){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.updateSingleLinkProperties(this.getId(), objectEntity.getId(), "«attribute.name»", properties, this, callback);
	}
}
	
/**
* NUR BACKEND: Methode zum Updaten der Properties aller Links vom Typ "«attribute.name»" im Orca-Backend. Alle
* verfügbaren LinkProperties können entsprechend aktualisiert werden. ACHTUNG:
* Wird für alle Properties null angegeben, wird kein Update durchgeführt.
* BACKEND-ENDPUNKT: api/{appname}/entities/{id}/links/{type}
* 
* @param objectEntity die ObjectEntity, auf die der Link zeigen soll
* @param callback Callback zum Abruf des Http-statusCode, der angelegten
* selfURL für diesen Link und eine errorMessage
*/
@JsonIgnore
public void BACKENDupdateAll«attribute.name.toFirstUpper»LinkPropertiesAtBackendAndThisObject(«FOR linkprops: attribute.linkproperties»«linkprops.type.toFirstUpper+" "+linkprops.name+","»«ENDFOR» INoReturnValueCallback callback){
	HashMap<String,Object> properties = new HashMap<String,Object>();
	«FOR linkprops: attribute.linkproperties»
	if(«linkprops.name» != null){
		«"properties.put(\""+linkprops.name+"\", "+linkprops.name+");"»
	}
	«ENDFOR»
	
	if(properties.size()!=0){
		«androidEntity.name.toFirstUpper»PersistenceWrapper.updateAllLinkPropertiesOfSameLinkType(this.getId(), "«attribute.name»", this, properties, callback);
	}
}
	
/**
* NUR BACKEND: Methode zum Löschen eines Links vom Typ "«attribute.name»" im Orca-Backend.
* BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/links/{type}/{objectId}
* 
* @param objectEntity die ObjectEntity, auf die der Link zeigt
* @param callback Callback zum Abruf des Http-statusCode und einer
*            errorMessage
*/
@JsonIgnore
public void BACKENDdeleteSingle«attribute.name.toFirstUpper»LinkFromBackendAndThisObject(«packagename».«attribute.objectType.name.toFirstUpper» objectEntity, INoReturnValueCallback callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.deleteSingleLink(this.getId(), objectEntity.getId(), "«attribute.name»", this, callback);
}
	
/**
* NUR BACKEND: Methode zum Löschen ALLER Links vom Typ "«attribute.name»" im Orca-Backend, die zu dieser Entity gehören.
* BACKEND-ENDPUNKT: api/{appname}/entities/{id}/links/{type}
* 
* @param objectEntity die ObjectEntity, auf die der Link zeigt
* @param callback Callback zum Abruf des Http-statusCode und einer
*            errorMessage
*/	
@JsonIgnore
public void BACKENDdeleteAll«attribute.name.toFirstUpper»LinksFromBackendAndThisObject(INoReturnValueCallback callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.deleteAllLinkPropertiesOfSameLinkType(this.getId(), "«attribute.name»", this, callback);
}
	
/**
* NUR BACKEND: Methode zum Abfragen aller Links einer Entity von dem LinkType "«attribute.name»". Über
* das Callback kann eine LinkPage mit den gefundenen Links abgerufen
* werden. BACKEND-ENDPUNKT: api/{appname}/entities/{id}/links/{type}
* 
* @param callback Callback zum Abruf der Ergebnis-LinkPage, des
*            Http-statusCode und der errorMessage
*/
@JsonIgnore
public void BACKENDloadAll«attribute.name.toFirstUpper»Links(ILinkPageCallback callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.loadAllLinksFromSameTypeOfAnEntity(this, "«attribute.name»", callback);
}

//bis hier

// ab hier die zusätzlichen Methoden
/**
* BACKEND UND LOKALE DATENBANK: Methode zum Anlegen eines neuen Links vom
* Typ "«attribute.name»" im Backend. Wenn die Speicherung im Backend erfolgreich war,
* erfolgt ein Update der lokalen Datenbank. ACHTUNG: Ein Update der lokalen
* Datenbank kann nur erfolgen, wenn die Entity, für die diese Methode
* aufgerufen wird, schon in der lokalen DB gespeichert wurde. Sonst erfolgt
* nur eine Speicherung im Backend. Auch wird der gesamte Speichervorgang abgebrochen,
* wenn alle Properties mit null gefüllt werden.
* BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/links/{type}/{objectId}
* 
* @param objectEntity Entity auf die Link zeigen soll
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird
* @param callback Callback zum Abruf des Http-statusCode und einer
*            errorMessage
*/
@JsonIgnore
public void MIXsave«attribute.name.toFirstUpper»LinkToBackendAndUpdateLocalDatabase(«packagename».«attribute.objectType.name.toFirstUpper» objectEntity, «FOR linkprops: attribute.linkproperties»«linkprops.type.toFirstUpper+" "+linkprops.name+", "»«ENDFOR» final Context context, final INoReturnValueCallback callback){
	
	final «androidEntity.name.toFirstUpper» «androidEntity.name» = this;
	HashMap<String,Object> properties = new HashMap<String,Object>();
	«FOR linkprops: attribute.linkproperties»
	if(«linkprops.name» != null){
		«"properties.put(\""+linkprops.name+"\", "+linkprops.name+");"»
	}
	«ENDFOR»
	
	if(properties.size()!=0){	
	
		Link link = new Link();
		link.setType("«attribute.name»");
		link.setProperties(properties);

		«androidEntity.name.toFirstUpper»PersistenceWrapper.saveLinkWithPropertiesByEntityIds(this, objectEntity, link, new IPostLinkCallback() {
					
						@Override
						public void onComplete(int statusCode, String selfURL, String errorMessage) {
					
							if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
								«androidEntity.name.toFirstUpper».MIXload«androidEntity.name.toFirstUpper»FromBackendByIdAndUpdateToLocalDatabase(«androidEntity.name».getId(), context, new IReturnValueCallback<«androidEntity.name.toFirstUpper»>() {
										
											@Override
											public void onComplete(«androidEntity.name.toFirstUpper» entity, int statusCode, String errorMessage) {
										
												if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
													try {
														if (callback != null) {
															callback.onComplete(statusCode, Constants.UPDATE_SUCCESSFUL);
														}
													}
													catch (Exception e) {
														if (callback != null) {
															callback.onComplete(-1,	errorMessage);
														}
													}
												}
											}
										});
							}
							else {
								callback.onComplete(statusCode, errorMessage);
							}
						
						}
					});
	}
}

/**
* BACKEND UND LOKALE DATENBANK: Methode zum Anlegen MEHRER neuer Links vom
* Typ "«attribute.name»" im Backend. Wenn die Speicherung im Backend erfolgreich war,
* erfolgt ein Update der lokalen Datenbank. ACHTUNG: Ein Update der lokalen
* Datenbank kann nur erfolgen, wenn die Entity, für die diese Methode
* aufgerufen wird, schon in der lokalen DB gespeichert wurde. Sonst erfolgt
* nur eine Speicherung im Backend. Auch wird der gesamte Speichervorgang abgebrochen,
* wenn alle Properties-Methodenparameter mit null gefüllt werden.
* BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/links/{type}/{objectId}
* 
* @param objectEntities HashSet mit allen ObjectEntities
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird
* @param callback Callback zum Abruf des Http-statusCode und einer
*            errorMessage
*/
@JsonIgnore
public void MIXsave«attribute.name.toFirstUpper»LinkToSeveral«attribute.objectType.name.toFirstUpper»ToBackendAndUpdateLocalDatabase(HashSet<«packagename».«attribute.objectType.name.toFirstUpper»> objectEntities, «FOR linkprops: attribute.linkproperties»«linkprops.type.toFirstUpper+" "+linkprops.name+", "»«ENDFOR» final Context context, final INoReturnValueCallback callback) {
	
	final «androidEntity.name.toFirstUpper» «attribute.name» = this;
	HashMap<String,Object> properties = new HashMap<String,Object>();
	«FOR linkprops: attribute.linkproperties»
	if(«linkprops.name» != null){
		«"properties.put(\""+linkprops.name+"\", "+linkprops.name+");"»
	}
	«ENDFOR»
	
	if(properties.size()!=0){
		
		Link link = new Link();
		link.setType("«attribute.name»");
		link.setProperties(properties);

		«androidEntity.name.toFirstUpper»PersistenceWrapper.saveLinkOfSameTypeWithPropertiesToSeveralObjectEntities(link, this, objectEntities, new IPostLinkCallback() {
					
						@Override
						public void onComplete(int statusCode, String selfURL, String errorMessage) {
					
							if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
								«androidEntity.name.toFirstUpper».MIXload«androidEntity.name.toFirstUpper»FromBackendByIdAndUpdateToLocalDatabase(«attribute.name».getId(), context, new IReturnValueCallback<«androidEntity.name.toFirstUpper»>() {
										
											@Override
											public void onComplete(«androidEntity.name.toFirstUpper» entity, int statusCode, String errorMessage) {
										
												if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
													try {
														if (callback != null) {
															callback.onComplete(statusCode, Constants.UPDATE_SUCCESSFUL);
														}
													}
													catch (Exception e) {
														if (callback != null) {
															callback.onComplete(-1,	errorMessage);
														}
													}
												}
											}
										});
							}
							else {
								callback.onComplete(statusCode, errorMessage);
							}
						
						}
					});
	}
}

/**
* BACKEND UND LOKALE DATENBANK: Methode zum Updaten eines Links (dessen Properties) vom
* Typ "«attribute.name»" im Backend. Wenn die Aktualisierung im Backend erfolgreich war,
* erfolgt ein Update der lokalen Datenbank. ACHTUNG: Ein Update der lokalen
* Datenbank kann nur erfolgen, wenn die Entity, für die diese Methode
* aufgerufen wird, schon in der lokalen DB gespeichert wurde. Sonst erfolgt
* nur ein Update im Backend. Auch wird der gesamte Updatevorgang abgebrochen,
* wenn alle Properties-Methodenparameter mit null gefüllt werden.
* BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/links/{type}/{objectId}
* 
* @param objectEntity objectEntity auf die Link zeigen soll
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird
* @param callback Callback zum Abruf des Http-statusCode und einer
*            errorMessage
*/
@JsonIgnore
public void MIXupdate«attribute.name.toFirstUpper»LinkPropertiesAtBackendAndLocalDatabase(«packagename».«attribute.objectType.name.toFirstUpper» objectEntity, «FOR linkprops: attribute.linkproperties»«linkprops.type.toFirstUpper+" "+linkprops.name+","»«ENDFOR» final Context context, final INoReturnValueCallback callback){
	
	final «androidEntity.name.toFirstUpper» «androidEntity.name» = this;
	HashMap<String,Object> properties = new HashMap<String,Object>();
	«FOR linkprops: attribute.linkproperties»
	if(«linkprops.name» != null){
		«"properties.put(\""+linkprops.name+"\", "+linkprops.name+");"»
	}
	«ENDFOR»
	
	if(properties.size()!=0){
		
		«androidEntity.name.toFirstUpper»PersistenceWrapper.updateSingleLinkProperties(this.getId(), objectEntity.getId(), "«attribute.name»", properties, this, new INoReturnValueCallback() {
					
						@Override
						public void onComplete(int statusCode, String errorMessage) {
					
							if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
								«androidEntity.name.toFirstUpper».MIXload«androidEntity.name.toFirstUpper»FromBackendByIdAndUpdateToLocalDatabase(«androidEntity.name».getId(), context, new IReturnValueCallback<«androidEntity.name.toFirstUpper»>() {
										
											@Override
											public void onComplete(«androidEntity.name.toFirstUpper» entity, int statusCode, String errorMessage) {
										
												if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
													try {
														if (callback != null) {
															callback.onComplete(statusCode, Constants.UPDATE_SUCCESSFUL);
														}
													}
													catch (Exception e) {
														if (callback != null) {
															callback.onComplete(-1,	errorMessage);
														}
													}
												}
											}
										});
							}
							else {
								callback.onComplete(statusCode, errorMessage);
							}
						}
					});
	}
}
	
/**
* BACKEND UND LOKALE DATENBANK: Methode zum Updaten ALLER Links (deren Properties) vom
* Typ "«attribute.name»" im Backend. Wenn die Aktualisierung im Backend erfolgreich war,
* erfolgt ein Update der lokalen Datenbank. ACHTUNG: Ein Update der lokalen
* Datenbank kann nur erfolgen, wenn die Entity, für die diese Methode
* aufgerufen wird, schon in der lokalen DB gespeichert wurde. Sonst erfolgt
* nur ein Update im Backend. Auch wird der gesamte Updatevorgang abgebrochen,
* wenn alle Properties-Methodenparameter mit null gefüllt werden.
* BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/links/{type}
* 
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird
* @param callback Callback zum Abruf des Http-statusCode und einer
*            errorMessage, DARF NICHT NULL SEIN
*/
@JsonIgnore
public void MIXupdateAll«attribute.name.toFirstUpper»LinkPropertiesAtBackendAndAtLocalDatabase(«FOR linkprops: attribute.linkproperties»«linkprops.type.toFirstUpper+" "+linkprops.name+","»«ENDFOR» final Context context, final INoReturnValueCallback callback)  {
	
	final «androidEntity.name.toFirstUpper» «androidEntity.name» = this;
	HashMap<String,Object> properties = new HashMap<String,Object>();
	«FOR linkprops: attribute.linkproperties»
	if(«linkprops.name» != null){
		«"properties.put(\""+linkprops.name+"\", "+linkprops.name+");"»
	}
	«ENDFOR»
	
	if(properties.size()!=0){
		
		«androidEntity.name.toFirstUpper»PersistenceWrapper.updateAllLinkPropertiesOfSameLinkType(this.getId(), "«attribute.name»", this, properties, new INoReturnValueCallback() {
					
						@Override
						public void onComplete(int statusCode, String errorMessage) {
					
							if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
								«androidEntity.name.toFirstUpper».MIXload«androidEntity.name.toFirstUpper»FromBackendByIdAndUpdateToLocalDatabase(«androidEntity.name».getId(), context, new IReturnValueCallback<«androidEntity.name.toFirstUpper»>() {
										
											@Override
											public void onComplete(«androidEntity.name.toFirstUpper» entity, int statusCode, String errorMessage) {
										
												if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
													try {
														if (callback != null) {
															callback.onComplete(statusCode, Constants.UPDATE_SUCCESSFUL);
														}
													}
													catch (Exception e) {
														if (callback != null) {
															callback.onComplete(-1,	errorMessage);
														}
													}
												}
											}
										});
							}
							else {
								callback.onComplete(statusCode, errorMessage);
							}
						}
					});
	}
}
	
/**
* BACKEND UND LOKALE DATENBANK: Methode zum Löschen eines Links vom
* Typ "«attribute.name»" im Backend. Wenn die Löschung im Backend erfolgreich war,
* erfolgt ein Update der lokalen Datenbank. ACHTUNG: Ein Update der lokalen
* Datenbank kann nur erfolgen, wenn die Entity, für die diese Methode
* aufgerufen wird, schon in der lokalen DB gespeichert wurde. Sonst erfolgt
* nur eine Löschung im Backend. 
* BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/links/{type}/{objectId}
* 
* @param objectEntity ObjectEntity, auf die Link verweist
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird
* @param callback Callback zum Abruf des Http-statusCode und einer
*            errorMessage
*/
@JsonIgnore
public void MIXdeleteSingle«attribute.name.toFirstUpper»LinkFromBackendAndUpdateLocalDatabase(«packagename».«attribute.objectType.name.toFirstUpper» objectEntity, final Context context, final INoReturnValueCallback callback){
	
	final «androidEntity.name.toFirstUpper» «androidEntity.name» = this;
	«androidEntity.name.toFirstUpper»PersistenceWrapper.deleteSingleLink(this.getId(), objectEntity.getId(), "«attribute.name»", this, new INoReturnValueCallback() {
					
					@Override
					public void onComplete(int statusCode, String errorMessage) {
					
						if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
							«androidEntity.name.toFirstUpper».MIXload«androidEntity.name.toFirstUpper»FromBackendByIdAndUpdateToLocalDatabase(«androidEntity.name».getId(), context, new IReturnValueCallback<«androidEntity.name.toFirstUpper»>() {
										
										@Override
										public void onComplete(«androidEntity.name.toFirstUpper» entity, int statusCode, String errorMessage) {
										
											if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
												try {
													if (callback != null) {
														callback.onComplete(statusCode, Constants.UPDATE_SUCCESSFUL);
													}
												}
												catch (Exception e) {
													if (callback != null) {
														callback.onComplete(-1,	errorMessage);
													}
												}
											}
										}
									});
						}
						else {
							callback.onComplete(statusCode, errorMessage);
						}
						
					}
				});
}
	
/**
* BACKEND UND LOKALE DATENBANK: Methode zum Löschen ALLER Links vom
* Typ "«attribute.name»" im Backend. Wenn die Aktualisierung im Backend erfolgreich war,
* erfolgt ein Update der lokalen Datenbank. ACHTUNG: Ein Update der lokalen
* Datenbank kann nur erfolgen, wenn die Entity, für die diese Methode
* aufgerufen wird, schon in der lokalen DB gespeichert wurde. Sonst erfolgt
* nur eine Löschung im Backend. 
* BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/links/{type}
* 
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird
* @param callback Callback zum Abruf des Http-statusCode und einer
*            errorMessage
*/
@JsonIgnore
public void MIXdeleteAll«attribute.name.toFirstUpper»LinksFromBackendAndUpdateLocalDatabase(final Context context, final INoReturnValueCallback callback){
	
	final «androidEntity.name.toFirstUpper» «androidEntity.name» = this;
	«androidEntity.name.toFirstUpper»PersistenceWrapper.deleteAllLinkPropertiesOfSameLinkType(this.getId(), "«attribute.name»", this, new INoReturnValueCallback() {
					
					@Override
					public void onComplete(int statusCode, String errorMessage) {
					
						if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
							«androidEntity.name.toFirstUpper».MIXload«androidEntity.name.toFirstUpper»FromBackendByIdAndUpdateToLocalDatabase(«androidEntity.name».getId(), context, new IReturnValueCallback<«androidEntity.name.toFirstUpper»>() {
										
										@Override
										public void onComplete(«androidEntity.name.toFirstUpper» entity, int statusCode, String errorMessage) {
										
											if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
												try {
													if (callback != null) {
														callback.onComplete(statusCode, Constants.UPDATE_SUCCESSFUL);
													}
												}
												catch (Exception e) {
													if (callback != null) {
														callback.onComplete(-1,	errorMessage);
													}
												}
											}
										}
									});
						}
						else {
							callback.onComplete(statusCode, errorMessage);
						}
						
					}
				});
}


//

«FOR linkprops: attribute.linkproperties»
«compilelinkproperties(attribute, androidEntity, packagename, linkprops)»
«ENDFOR»

'''
}


def static compilelinkproperties(AndroidAttribute attribute, AndroidEntity androidEntity, String packagename, LinkProperties linkProperties){//attribute ist hier der link, attribute name = linktype, attribute objectType = object auf das link zeigt
'''
//ab hier LinkProperty «linkProperties.name»
/**
* NUR BACKEND: Methode zum Updaten des LinkProperty "«linkProperties.name»" vom Typ "«attribute.name»" im
* Orca-Backend.  ACHTUNG: Wenn als Property-Parameter null übergeben wird, erfolgt kein Update.
* 
* BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/links/{type}/{objectId}/property
* 
* @param objectEntity ObjectEntity, auf die der Link zeigt
* @param callback Callback zum Abfragen des Http-StatusCode und der
*            ErrorMessage
*/
public void BACKENDupdate«attribute.name.toFirstUpper»LinkProperty«linkProperties.name.toFirstUpper»AtBackendAndThisObject(«packagename».«attribute.objectType.name.toFirstUpper» objectEntity, «linkProperties.type.toFirstUpper» new«linkProperties.name.toFirstUpper»Value, INoReturnValueCallback callback){
	
	HashMap<String,Object> property = new HashMap<String,Object>();
	
	if(new«linkProperties.name.toFirstUpper»Value != null){
		property.put("«linkProperties.name»", new«linkProperties.name.toFirstUpper»Value);
	}
	
	if(property.size()!=0){
		«androidEntity.name.toFirstUpper»PersistenceWrapper.updateLinkPropertyAddAditionalProperties(this.getId(), objectEntity.getId(), "«attribute.name»", this, property, callback);
	}
}
	
/** 
* NUR BACKEND: Methode zum löschen des LinkProperty "«linkProperties.name»" vom Typ "«attribute.name»" im
* Orca-Backend. 
* 
* BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/links/{type}/{objectId}/{property}
* @param objectEntity ObjectEntity, auf die der Link zeigt
* @param callback Callback zum Abfragen des Http-StatusCode und der
*            ErrorMessage
*/
@JsonIgnore 
public void BACKENDdelete«attribute.name.toFirstUpper»LinkProperty«linkProperties.name.toFirstUpper»AtBackendAndThisObject(«packagename».«attribute.objectType.name.toFirstUpper» objectEntity, INoReturnValueCallback callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.deleteSingleLinkPropertyOfSameLinkType(this.getId(), objectEntity.getId(), "«attribute.name»", this, "«linkProperties.name»", callback);
}
		
// bis hier

// zusätzliche Methode
/**
	 * BACKEND UND LOKALE DATENBANK: Methode zum updaten des LinkProperty "«linkProperties.name»"
	 * des LinkTyps "«attribute.name»" im Backend. Wenn das Update im Backend erfolgreich war,
	 * erfolgt ein Update der lokalen Datenbank. ACHTUNG: Das Update der lokalen
	 * Datenbank kann nur erfolgen, wenn die Entity, für die diese Methode
	 * aufgerufen wird, schon in der lokalen DB gespeichert wurde. Das Update
	 * wird nicht durchgeführt, wenn alle PropertyParameter dieser Methode mit
	 * null belegt werden. BACKEND-ENDPUNKT:
	 * /api/{appname}/entities/{id}/links/{type}/{objectId}/property
	 * 
	 * @param objectEntity ObjectEntity, auf die der Link zeigt
	 * @param context ApplicationContext der Activity, von der aus diese Methode
	 *            aufgerufen wird
	 * @param callback Callback zum Abfragen des Http-StatusCode und der
	 *            ErrorMessage
	 */
@JsonIgnore
public void MIXupdate«attribute.name.toFirstUpper»LinkProperty«linkProperties.name.toFirstUpper»AtBackendAndUpdateLocalDatabase(«packagename».«attribute.objectType.name.toFirstUpper» objectEntity, «linkProperties.type.toFirstUpper» new«linkProperties.name.toFirstUpper»Value, final Context context, final INoReturnValueCallback callback){
	
	final «androidEntity.name.toFirstUpper» «androidEntity.name» = this;
	HashMap<String,Object> property = new HashMap<String,Object>();
	
	if(new«linkProperties.name.toFirstUpper»Value != null){
		property.put("«linkProperties.name»", new«linkProperties.name.toFirstUpper»Value);
	}
	
	if(property.size()!=0){
		
		«androidEntity.name.toFirstUpper»PersistenceWrapper.updateLinkPropertyAddAditionalProperties(this.getId(), objectEntity.getId(), "«attribute.name»", this, property, new INoReturnValueCallback() {
					
						@Override
						public void onComplete(int statusCode, String errorMessage) {
					
							if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
								«androidEntity.name.toFirstUpper».MIXload«androidEntity.name.toFirstUpper»FromBackendByIdAndUpdateToLocalDatabase(«androidEntity.name».getId(), context, new IReturnValueCallback<«androidEntity.name.toFirstUpper»>() {
										
											@Override
											public void onComplete(«androidEntity.name.toFirstUpper» entity, int statusCode, String errorMessage) {
											
												if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
													try {
														if (callback != null) {
															callback.onComplete(statusCode, Constants.UPDATE_SUCCESSFUL);
														}
													}
													catch (Exception e) {
														if (callback != null) {
															callback.onComplete(-1,	errorMessage);
														}
													}
												}
											}
										});
							}
							else {
								callback.onComplete(statusCode, errorMessage);
							}
						}
					});
	}
}
//
'''
}	

}