package org.xtext.orcasdk.entitymodel.generator.entity.property.helper

import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity

class CreateEntityStaticMethodsHelper {
	
def static createAllStaticMethods(AndroidEntity androidEntity, String packagename) {
'''

/**
 * NUR BACKEND: Methode zum Absetzen einer SQL-Query ans Orca-Backend. In der Response wird eine EntityPage mit den gefundenen Entities zurückgesendet.
 * 
 * BACKEND-ENDPUNKT: /api/{appname}/entities
 * 
 * @param queryString der SQL-Query-String
 * @param orderBy ein OrderBy als String, es kann auch null übergeben werden
 * @param offset das Offset für die EntityPage
 * @param size die Anzahl an Entities, die die EntityPage maximal enthalten soll
 * @param callback Callback über das die Ergebnis-entityPage, der Http-statusCode und eine errorMessage abgerufen werden können
 */
@JsonIgnore
public static void BACKENDload«androidEntity.name.toFirstUpper»sFromBackendByQueryString(String queryString, String orderBy, int offset, int size, IPageCallback<«packagename».«androidEntity.name.toFirstUpper»> callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.load«androidEntity.name.toFirstUpper»sByQueryString(queryString, orderBy, offset, size, callback);
}
	
/**
 * NUR BACKEND: Methode zum Laden einer EntityPage anhand eines übergebenen EntityPageNavigationLink. Über das Callback kann die gefundene EntityPage abgerufen werden.
 * 
 * BACKEND-ENDPUNKT: der übergebene NavigationLink
 * 
 * @param link der EntityPageNavigationLink, über den eine weitere EntityPage abgerufen werden kann
 * @param callback Callback über das die Ergebnis-entityPage, der Http-statusCode und eine errorMessage abgerufen werden können
 */
@JsonIgnore 
public static void BACKENDload«androidEntity.name.toFirstUpper»sFromBackendByNavigationLink(String link, IPageCallback<«packagename».«androidEntity.name.toFirstUpper»> callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.load«androidEntity.name.toFirstUpper»sByNavigationLink(link, callback);
}

/**
 * NUR BACKEND: Methode zum Abrufen einer Entity vom Orca-Backend anhand des übergebenen Links.
 * 
 * BACKEND-ENDPUNKT: der übergebene Link
 * 
 * @param link der entsprechende Link auf eine Entity
 * @param callback Callback über das die gefundene Entity, der HTTP-StatusCode und eine ErrorMessage abgerufen werden kann
 */
@JsonIgnore
public static void BACKENDload«androidEntity.name.toFirstUpper»FromBackendByEntityLink(String link, IReturnValueCallback<«packagename».«androidEntity.name.toFirstUpper»> callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.load«androidEntity.name.toFirstUpper»ByLink(link, callback);
}

/**
 * NUR BACKEND: Methode zum Abrufen einer Entity vom Orca-Backend anhand der Entity-ID.
 * 
 * BACKEND-ENDPUNKT: /api/{appname}/entities/{id}
 * 
 * @param id EntityId
 * @param callback Callback über das die gefundene Entity, der HTTP-StatusCode und eine ErrorMessage abgerufen werden kann
 */
@JsonIgnore
public static void BACKENDload«androidEntity.name.toFirstUpper»FromBackendById(int id, IReturnValueCallback<«packagename».«androidEntity.name.toFirstUpper»> callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.load«androidEntity.name.toFirstUpper»ById(id, callback);
}

/**
 * NUR BACKEND: Methode zum Löschen einer Entity vom Orca-Backend anhand der Entity-ID.
 * 
 * BACKEND-ENDPUNKT: /api/{appname}/entities/{id}
 * 
 * @param id EntityId
 * @param callback Callback über das die gefundene Entity, der HTTP-StatusCode und eine ErrorMessage abgerufen werden kann
 */
@JsonIgnore
public static void BACKENDdelete«androidEntity.name.toFirstUpper»ByIdFromBackend(int id, INoReturnValueCallback callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.delete«androidEntity.name.toFirstUpper»ById(id, callback);
}

/**
 * NUR BACKEND: Methode zum Updaten einer Entity mit einer übergebenen Entity im Orca-Backend.
 * 
 * BACKEND-ENDPUNKT: /api/{appname}/entities/{id}
 * 
 * @param «androidEntity.name»WhichShallBeUpdated die upzudatende Entity
 * @param newFilm die neue Entity
 * @param callback Callback über das der HTTP-StatusCode und eine ErroMessage abgerufen werden kann
 */
@JsonIgnore
public static void BACKENDupdate«androidEntity.name.toFirstUpper»WithNew«androidEntity.name.toFirstUpper»AtBackend(«packagename+"."+androidEntity.name.toFirstUpper» «androidEntity.name»WhichShallBeUpdated, «packagename+"."+androidEntity.name.toFirstUpper» new«androidEntity.name.toFirstUpper», INoReturnValueCallback callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.update«androidEntity.name.toFirstUpper»WithNew«androidEntity.name.toFirstUpper»( «androidEntity.name»WhichShallBeUpdated, new«androidEntity.name.toFirstUpper», callback);
}

/**
 * NUR BACKEND: Methode zum Laden aller im Orca-Backend gespeicherten Entities vom Typ «androidEntity.name.toFirstUpper». 
 * 
 * BACKEND-ENDPUNKT: /api/{appname}/entities
 * 
 * @param orderBy OrderBy-String oder null
 * @param offset das Offset
 * @param size die PageSize
 * @param callback Callback über das die Ergebnis-entityPage, der Http-statusCode und eine errorMessage abgerufen werden können
 */
@JsonIgnore
public static void BACKENDloadAll«androidEntity.name.toFirstUpper»sFromBackend(String orderBy, int offset, int size, IPageCallback<«packagename».«androidEntity.name.toFirstUpper»> callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.loadAll«androidEntity.name.toFirstUpper»sFromSameType(orderBy, offset, size, callback);
}

/**
 * NUR BACKEND: Methode zum Abrufen aller schon vergebenen EntityIds für Entities vom Typ «androidEntity.name.toFirstUpper».
 * Als Ergebnis wird eine ArrayList über das Callback zurückgegeben.
 * 
 * BACKEND-ENDPUNKT: /api/{appname}/entities
 * 
 * @param callback Callback zum Abrufen der ArrayList, des HTTP-StatusCodes und einer errorMessage
 */
@JsonIgnore
public static void BACKENDloadAll«androidEntity.name.toFirstUpper»IdsFromBackend(IAllEntityIdsCallback callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.loadAll«androidEntity.name.toFirstUpper»sIds(callback);
}

/**
 * NUR BACKEND: Methode zum Abrufen einer LinkPage per NavigationLink anzufordern.
 * 
 * BACKEND-ENDPUNKT: der übergebene Link.
 * 
 * @param navLink der EntityPageNavigationLink
 * @param callback Callback über das die LinkPage, der HTTP-StatusCode und eine ErrorMessage abgerufen werden kann
 */
@JsonIgnore
public static void BACKENDloadLinksByNavigationLink(String navLink, ILinkPageCallback callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.loadLinksByNavigationLink(navLink, callback);
}

/**
 * NUR BACKEND: Methode zum Laden eines Entity-Image anhand eines ImageLinks.
 * 
 * BACKEND-ENDPUNKT: der übergebene Link
 * 
 * @param imageLink der ImageLink
 * @param width die Breite, die das abgefragte Bild haben soll (in Pixeln)
 * @param height die Höhe, die das abgefragte Bild haben soll (in Pixeln)
 * @param radius hierüber wird angegeben, ob und wie stark die Ecken abgerundet werden
 * @param backgroundColor Hintergrundfarbe als Hex-Wert, dieser ist als String zu übergeben! 
 * @param callback Callback zum Abfragen des HTTP-Response-StatusCode und einer ErrorMessage
 */
@JsonIgnore
public static void BACKENDload«androidEntity.name.toFirstUpper»ImageByImageLink(String imageLink, int width, int height, int radius, String backgroundColor, IImageCallback callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.load«androidEntity.name.toFirstUpper»ImageByImageLink(imageLink, width, height, radius, backgroundColor, callback);
}

/**
 * NUR BACKEND: Methode zum Speichern einer neuen «androidEntity.name.toFirstUpper»-Entity im Backend. Alle EntityProperties können als Parameter übergeben werden. 
 * Wird bei allen PropertyParametern null angebenen, erfolgt keine Speicherung. ACHTUNG: Es erfolgt zuerst eine Prüfung,
 * ob es bereits eine Entity mit den übergebenen Parametern im Backend gibt. Falls ja, erfolgt keine Speicherung, in 
 * der über das Callback abrufbaren ErrorMessage werden die IDs angegeben, die gefunden wurden.
 * 
 * BACKEND-ENDPUNKT: /api/{appname}/entities
 * 
 * @param callback Callback zum Abrufen der gespeicherten Entity, des HTTP-StatusCode und einer ErrorMessage
 */
@JsonIgnore
public static void BACKENDsaveNew«androidEntity.name.toFirstUpper»ToBackendWithCheckWhetherAlreadyExistsWithProperties(«FOR attribute : androidEntity.attributes»«IF !attribute.type.equalsIgnoreCase("link") && !attribute.type.equalsIgnoreCase("image")»«attribute.type.toFirstUpper» «attribute.name»,«ENDIF»«ENDFOR» IReturnValueCallback<«packagename».«androidEntity.name.toFirstUpper»> callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.save«androidEntity.name.toFirstUpper»WithCheckWhether«androidEntity.name.toFirstUpper»StillExistsWithProperties(«FOR attribute : androidEntity.attributes»«IF !attribute.type.equalsIgnoreCase("link") && !attribute.type.equalsIgnoreCase("image")»«attribute.name»,«ENDIF»«ENDFOR» callback);
}

/**
 * LOKALE DATENBANK: Methode zum Laden einer «androidEntity.name.toFirstUpper»-Entity aus der lokalen SQLite-DB anhand der EntityId.
 * 
 * @param «androidEntity.name»EntityId die EntityId
 * @param context ApplicationContext der Actitivity, von der aus diese
 *            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
 * @return die geladene «androidEntity.name.toFirstUpper»-Entity
 * @throws Exception wird bei DB-Fehlern geworfen
 */
@JsonIgnore
public static «androidEntity.name.toFirstUpper» DATABASEload«androidEntity.name.toFirstUpper»ByIdFromLocalDatabase(int «androidEntity.name»EntityId, Context context) throws Exception {
	
	«androidEntity.name.toFirstUpper» temp = «androidEntity.name.toFirstUpper»DatabaseWrapper.loadEntityById(«androidEntity.name»EntityId, context);
	«androidEntity.name.toFirstUpper» retValue = new «androidEntity.name.toFirstUpper»();
	retValue.setId(temp.getId());
	retValue.setSelf(temp.getSelf());
	retValue.setProperties(temp.getProperties());
	retValue.setLinks(temp.getLinks());
	
	return retValue;
}

/**
 *LOKALE DATENBANK: Methode zum Laden aller «androidEntity.name.toFirstUpper»-Entities aus der lokalen SQLite-DB.
 * 
 * @param context ApplicationContext der Actitivity, von der aus diese
 *            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
 * @return ArrayList mit allen «androidEntity.name.toFirstUpper»-Entities, die in der lokalen DB gespeichert sind
 * @throws Exception wird bei DB-Fehlern geworfen
 */
@JsonIgnore
public static ArrayList<«androidEntity.name.toFirstUpper»> DATABASEloadAll«androidEntity.name.toFirstUpper»sFromLocalDatabase(Context context) throws Exception {
	ArrayList<«androidEntity.name.toFirstUpper»> temp = «androidEntity.name.toFirstUpper»DatabaseWrapper.loadAllEntitiesOfSameType(context);
	ArrayList<«androidEntity.name.toFirstUpper»> retValue = new ArrayList<«androidEntity.name.toFirstUpper»>();
	
	for(Entity entity : temp){
		«androidEntity.name.toFirstUpper» newEntity = new «androidEntity.name.toFirstUpper»();
		newEntity.setId(entity.getId());
		newEntity.setSelf(entity.getSelf());
		newEntity.setProperties(entity.getProperties());
		newEntity.setLinks(entity.getLinks());
		retValue.add(newEntity);
	}
	
	return retValue;
}

/**
 * LOKALE DATENBANK: Methode zum Abrufen von «androidEntity.name.toFirstUpper»-Entities aus der lokalen Datenbank unter 
 * Angabe des SQL-whereClauseSelectionString. 
 * 
 * @param context ApplicationContext der Actitivity, von der aus diese
 *            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
 * @param whereClauseSelectionString der Where-Claus-String oder null
 * @return alle gefundenen Entities als ArrayList
 * @throws Exception wird bei DB-Fehlern geworfen
 */
@JsonIgnore
public static ArrayList<«androidEntity.name.toFirstUpper»> DATABASEloadAll«androidEntity.name.toFirstUpper»sByQueryFromLocalDatabase(Context context, String whereClauseSelectionString) throws Exception {
	ArrayList<«androidEntity.name.toFirstUpper»> temp = «androidEntity.name.toFirstUpper»DatabaseWrapper.loadEntitiesOfSameTypeByQuery(context, whereClauseSelectionString);
	ArrayList<«androidEntity.name.toFirstUpper»> retValue = new ArrayList<«androidEntity.name.toFirstUpper»>();
	
	for(Entity entity : temp){
		«androidEntity.name.toFirstUpper» newEntity = new «androidEntity.name.toFirstUpper»();
		newEntity.setId(entity.getId());
		newEntity.setSelf(entity.getSelf());
		newEntity.setProperties(entity.getProperties());
		newEntity.setLinks(entity.getLinks());
		retValue.add(newEntity);
	}
	
	return retValue;
}

/**
 * LOKALE DATENBANK: Methode zum Speichern mehrerer «androidEntity.name.toFirstUpper»-Entities in der lokalen Datenbank. 
 * 
 * @param context ApplicationContext der Actitivity, von der aus diese
 *            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
 * @param entities die zu speichernden «androidEntity.name.toFirstUpper»-Entities in einer ArrayList
 * @throws Exception wird bei DB-Fehlern geworfen
 */
@JsonIgnore
public static void DATABASEsaveSeveral«androidEntity.name.toFirstUpper»sToLocalDatabase(Context context, ArrayList<«androidEntity.name.toFirstUpper»> entities) throws Exception {
	«androidEntity.name.toFirstUpper»DatabaseWrapper.saveEntities(context, entities);
}

/**
 * LOKALE DATENBANK: Methode für einen Reset der lokalen Datenbank. ACHTUNG: Es werden nicht nur die 
 * zu diesem EntityType gehörenden Tabellen gelöscht. Die ganze DB mit allen enthaltenen Tabellen wird gelöscht.
 * 
 * @param context ApplicationContext der Actitivity, von der aus diese
 *            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
 */
@JsonIgnore
public static void DATABASEresetDatabase(Context context){
	«androidEntity.name.toFirstUpper»DatabaseWrapper.resetDatabase(context);
}

/**
 * LOKALE DATENBANK: Methode für einen Reset aller «androidEntity.name.toFirstUpper»-Entity-Tabellen. Alle lokal gespeicherten 
 * «androidEntity.name.toFirstUpper»-Entities sind danach gelöscht.
 * 
 * @param context ApplicationContext der Actitivity, von der aus diese
 *            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
 */
@JsonIgnore
public static void DATABASEdeleteTable(Context context){
	«androidEntity.name.toFirstUpper»DatabaseWrapper.deleteTable(context);
}


/**
* BACKEND UND LOKALE DATENBANK: Methode zum erstmaligen Speichern einer neuen Kino-Entity. Zuerst erfolgt
* die Speicherung im Backend. Wenn die Speicherung erfolgreich war, wird
* die Kino-Entity von dort abgerufen und in der lokalen Datenbank
* gespeichert. Es wird geprüft, ob schon ähnliche Entitäten im Backend
* vorhanden sind. Falls dies der Fall ist, werden in der ErrorMessages die
* IDs der Entitäten angegeben, die ähnlich zur speichernden Entity sind.
* Sollte in der lokalen Datenbank schon eine Entity mit der selben ID
* gespeichert sein, wird eine Exception geworfen.
*
* BACKEND-ENDPUNKT: /api/{appname}/entities
* 
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
* @param returnCallback das Callback gibt den StatusCode und eine
*            ErrorMessage zurück. Der StatusCode ist -1 bei Fehlern.
*            Ansonsten ist der StatusCode der StatusCode der
*            HttpGet-Response. Ebenso wird bei erfolgreicher Speicherung 
*            die gespeicherte Entity über die onComplete-Methode abrufbar. 
*/
@JsonIgnore
public static void MIXsaveNew«androidEntity.name.toFirstUpper»ToBackendAndLocalDatabaseWithCheckWhetherAlreadyExistsWithProperties(«FOR attribute : androidEntity.attributes»«IF !attribute.type.equalsIgnoreCase("link") && !attribute.type.equalsIgnoreCase("image")»«attribute.type.toFirstUpper» «attribute.name»,«ENDIF»«ENDFOR»  final Context context, final IReturnValueCallback<«androidEntity.name.toFirstUpper»> returnCallback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.save«androidEntity.name.toFirstUpper»WithCheckWhether«androidEntity.name.toFirstUpper»StillExistsWithProperties(«FOR attribute : androidEntity.attributes»«IF !attribute.type.equalsIgnoreCase("link") && !attribute.type.equalsIgnoreCase("image")»«attribute.name»,«ENDIF»«ENDFOR» new IReturnValueCallback<«androidEntity.name.toFirstUpper»>() {

		@Override
		public void onComplete(«androidEntity.name.toFirstUpper» entity, int statusCode, String errorMessage) {
		
			if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
				
					try {
						entity.DATABASEsaveToLocalDatabase(context);
						if(returnCallback!=null){
							returnCallback.onComplete(entity, statusCode, Constants.NO_ERROR_WHILE_SAVE_BACKEND_AND_DATABASE+ entity.getId()+ ".");
						}
					}
					catch (Exception e) {
						if(returnCallback!=null){
							returnCallback.onComplete(null, -1, e.getMessage());
						}
					}
				
			}
			else {
				if(returnCallback!=null){
					returnCallback.onComplete(null, statusCode, errorMessage);
				}
			}
		}
	});
}

/**
* BACKEND UND LOKALE DATENBANK: Methode zum erstmaligen Speichern einer neuen «androidEntity.name.toFirstUpper»-Entity. Zuerst erfolgt
* die Speicherung im Backend. Wenn die Speicherung erfolgreich war, wird
* die «androidEntity.name.toFirstUpper»-Entity von dort abgerufen und in der lokalen Datenbank
* gespeichert. Es wird nicht geprüft, ob im Backend schon eine ähnliche
* Entity vorhanden ist. Sollte in der lokalen Datenbank schon eine Entity
* mit der selben ID gespeichert sein, wird eine Exception geworfen.
*
* BACKEND-ENDPUNKT: /api/{appname}/entities
* 
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
* @param returnCallback das Callback gibt den StatusCode und eine
*            ErrorMessage zurück. Der StatusCode ist -1 bei Fehlern.
*            Ansonsten ist der StatusCode der StatusCode der
*            HttpGet-Response. Ebenso wird bei erfolgreicher Speicherung 
*            die gespeicherte Entity über die onComplete-Methode abrufbar. 
*/
@JsonIgnore
public static void MIXsaveNew«androidEntity.name.toFirstUpper»ToBackendAndLocalDatabaseWithProperties(«FOR attribute : androidEntity.attributes»«IF !attribute.type.equalsIgnoreCase("link") && !attribute.type.equalsIgnoreCase("image")»«attribute.type.toFirstUpper» «attribute.name»,«ENDIF»«ENDFOR»  final Context context, final IReturnValueCallback<«androidEntity.name.toFirstUpper»> returnCallback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.save«androidEntity.name.toFirstUpper»WithProperties(«FOR attribute : androidEntity.attributes»«IF !attribute.type.equalsIgnoreCase("link") && !attribute.type.equalsIgnoreCase("image")»«attribute.name»,«ENDIF»«ENDFOR» new IReturnValueCallback<«androidEntity.name.toFirstUpper»>() {

		@Override
		public void onComplete(«androidEntity.name.toFirstUpper» entity, int statusCode, String errorMessage) {
		
			if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
				
					try {
						entity.DATABASEsaveToLocalDatabase(context);
						if(returnCallback!=null){
							returnCallback.onComplete(entity, statusCode, Constants.NO_ERROR_WHILE_SAVE_BACKEND_AND_DATABASE+ entity.getId()+ ".");
						}
					}
					catch (Exception e) {
						if(returnCallback!=null){
							returnCallback.onComplete(null, -1, e.getMessage());
						}
					}
				
			}
			else {
				if(returnCallback!=null){
					returnCallback.onComplete(null, statusCode, errorMessage);
				}
			}
		}
	});
}

/**
* BACKEND UND LOKALE DATENBANK: Methode zum Laden aller «androidEntity.name.toFirstUpper»-Entities vom Backend (per Query). Nach erfolgreichem
* Laden werden alle Entities in der lokalen DB gespeichert. ACHTUNG: Die
* zur «androidEntity.name.toFirstUpper»-Entity gehörenden Tabellen werden zuvor gelöscht! Die Methode
* eignet sich für das Laden der Entities zum Programmstart. Es werden
* maximal 10000 Entities geladen. 
*
* BACKEND-Endpunkt: /api/{appname}/entities
* 
* @param queryString die SQL-Query
* @param orderBy das SQL-OrderBy, es kann auch ein leerer String angegeben
*            werden, aber nicht null
* @param offset Offset
* @param size die maximale Anzahl der Entities in der EntityPage
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
* @param noReturnValueCallback das Callback gibt den StatusCode und eine
*            ErrorMessage zurück. Der StatusCode ist -1 bei Fehlern.
*            Ansonsten ist der StatusCode der StatusCode der
*            HttpGet-Response.
*/
@JsonIgnore
public static void MIXload«androidEntity.name.toFirstUpper»sFromBackendByQueryStringAndSaveToLocalDatabase(String queryString, String orderBy, int offset, int size, final Context context, final INoReturnValueCallback noReturnValueCallback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.load«androidEntity.name.toFirstUpper»sByQueryString(queryString, orderBy, offset, size, new IPageCallback<«androidEntity.name.toFirstUpper»>() {
					
					@Override
					public void onComplete(EntityPage<«androidEntity.name.toFirstUpper»> entityPage, int statusCode, String errorMessage) {
					
						if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
							
							try{
								«androidEntity.name.toFirstUpper».DATABASEdeleteTable(context);
							}catch(SQLException e){
							}
							
							try {
								«androidEntity.name.toFirstUpper».DATABASEsaveSeveral«androidEntity.name.toFirstUpper»sToLocalDatabase(context, entityPage.getEntities());
								if(noReturnValueCallback!=null){
									noReturnValueCallback.onComplete(statusCode, errorMessage);
								}
							}
							catch (Exception e) {
								if(noReturnValueCallback!=null){
									noReturnValueCallback.onComplete(-1, e.getMessage());
								}
							}
						}
						else {
							if(noReturnValueCallback!=null){
								noReturnValueCallback.onComplete(statusCode, errorMessage);
							}
						}
						
					}
				});
}

/**
* BACKEND UND LOKALE DATENBANK: Methode zum Laden aller «androidEntity.name.toFirstUpper»-Entities vom Backend. Nach erfolgreichem
* Laden werden alle Entities in der lokalen DB gespeichert. ACHTUNG: Die
* zur «androidEntity.name.toFirstUpper»-Entity gehörenden Tabellen werden zuvor gelöscht! Die Methode
* eignet sich für das Laden der Entities zum Programmstart.
* Es werden maximal 10000 Entities geladen.
* 
* BACKEND-Endpunkt: /api/{appname}/entities
* 
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
* @param noReturnValueCallback das Callback gibt den StatusCode und eine
*            ErrorMessage zurück. Der StatusCode ist -1 bei Fehlern.
*            Ansonsten ist der StatusCode der StatusCode der
*            HttpGet-Response.
*/
@JsonIgnore
public static void MIXloadAll«androidEntity.name.toFirstUpper»sFromBackendAndSaveToLocalDatabase(final Context context, final INoReturnValueCallback noReturnValueCallback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.loadAll«androidEntity.name.toFirstUpper»sFromSameType("", 0, 10000, new IPageCallback<«androidEntity.name.toFirstUpper»>() {
					
					@Override
					public void onComplete(EntityPage<«androidEntity.name.toFirstUpper»> entityPage,
							int statusCode, String errorMessage) {
					
						if (NetworkHelper.isStatusCodeBetween200And300(statusCode)) {
							
							try{
								«androidEntity.name.toFirstUpper».DATABASEdeleteTable(context);
							}catch(SQLException e){
							}
							
							try {
								«androidEntity.name.toFirstUpper».DATABASEsaveSeveral«androidEntity.name.toFirstUpper»sToLocalDatabase(context, entityPage.getEntities());
								if(noReturnValueCallback!=null){
									noReturnValueCallback.onComplete(statusCode, errorMessage);
								}
							}
							catch (Exception e) {
								if(noReturnValueCallback!=null){
									noReturnValueCallback.onComplete(-1, e.getMessage());
								}
							}
						}
						else {
							if(noReturnValueCallback!=null){
								noReturnValueCallback.onComplete(statusCode, errorMessage);
							}
						}
						
					}
				});
}


/**
 * BACKEND UND LOKALE DATENBANK: Methode zum Laden einer «androidEntity.name.toFirstUpper»-Entity vom Backend. Nach erfolgreichem Laden
 * wird die «androidEntity.name.toFirstUpper»-Entity in der lokalen Datenbank gespeichert. Diese Methode ist zu verwenden, wenn die 
 * entsprechende Entity noch nicht in der lokalen Datenbank gespeichert ist.
 * 
 * BACKEND-ENDPUNKT: /api/{appname}/entities/{id}
 * 
 * @param id EntityId
 * @param context ApplicationContext der Actitivity, von der aus diese
 *            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
 * @param callback Callback über das die geladene Entity, der HttpStatus-Code und eine errorMessage abgerufen werden kann
 */
@JsonIgnore
public static void MIXload«androidEntity.name.toFirstUpper»FromBackendByIdAndSaveToLocalDatabase(int id, final Context context, final IReturnValueCallback<«packagename».«androidEntity.name.toFirstUpper»> callback){
	
	«androidEntity.name.toFirstUpper»PersistenceWrapper.load«androidEntity.name.toFirstUpper»ById(id, new IReturnValueCallback<«androidEntity.name.toFirstUpper»>() {
					
					@Override
					public void onComplete(«androidEntity.name.toFirstUpper» entity, int statusCode,
							String errorMessage) {
					
						if (NetworkHelper.isStatusCodeBetween200And300(statusCode)){
							try {
								entity.DATABASEsaveToLocalDatabase(context);
								if (callback != null) {
									callback.onComplete(entity, statusCode,	errorMessage);
								}
							}
							catch (Exception e) {
								if (callback != null) {
									callback.onComplete(null, -1, e.getMessage());
								}
							}
						}else {
							if(callback!=null){
								callback.onComplete(null, statusCode, errorMessage);
							}
						}
					}
				});
}

/**
 * BACKEND UND LOKALE DATENBANK: Methode zum Laden einer «androidEntity.name.toFirstUpper»-Entity vom Backend. Nach erfolgreichem Laden
 * wird die geladene «androidEntity.name.toFirstUpper»-Entity für ein Update der lokalen DB herangezogen. Diese Methode ist zu verwenden, wenn die 
 * entsprechende Entity schon in der lokalen DB gespeichert wurde.
 * 
 * BACKEND-ENDPUNKT: /api/{appname}/entities/{id}
 * 
 * @param id EntityId
 * @param context ApplicationContext der Actitivity, von der aus diese
 *            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
 * @param callback Callback über das die geladene Entity, der HttpStatus-Code und eine errorMessage abgerufen werden kann
 */
@JsonIgnore
public static void MIXload«androidEntity.name.toFirstUpper»FromBackendByIdAndUpdateToLocalDatabase(int id, final Context context, final IReturnValueCallback<«packagename».«androidEntity.name.toFirstUpper»> callback){
	
	«androidEntity.name.toFirstUpper»PersistenceWrapper.load«androidEntity.name.toFirstUpper»ById(id, new IReturnValueCallback<«androidEntity.name.toFirstUpper»>() {
					
					@Override
					public void onComplete(«androidEntity.name.toFirstUpper» entity, int statusCode,
							String errorMessage) {
					
						if (NetworkHelper.isStatusCodeBetween200And300(statusCode)){
							try {
								entity.DATABASEupdateAtLocalDatabase(context);
								if (callback != null) {
									callback.onComplete(entity, statusCode,	errorMessage);
								}
							}
							catch (Exception e) {
								if (callback != null) {
									callback.onComplete(null, -1, e.getMessage());
								}
							}
						}else {
							if(callback!=null){
								callback.onComplete(null, statusCode, errorMessage);
							}
						}
					}
				});
}

/**
 * BACKEND UND LOKALE DATENBANK: Methode zum Laden aller «androidEntity.name.toFirstUpper»-Entities aus der lokalen Datenbank. Nach erfolgreichem Laden
 * erfolgt von diesen Entities ein Update im Backend. Diese Methode eignet sich, wenn über längere Zeit die EntityProperties
 * nur in der lokalen DB geändert wurden und noch kein Update von allen Entities erfolgt ist. Im Backend werden die Entities
 * mit dem Stand aus der lokalen DB geupdatet. Die Entities bleiben in der lokalen DB weiterhin gespeichert.
 * 
 * @param context ApplicationContext der Actitivity, von der aus diese
 *            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
 * @param callback das Callback gibt den StatusCode und eine
 *            ErrorMessage zurück. Der StatusCode ist -1 bei Fehlern.
 *            Ansonsten ist der StatusCode der StatusCode der
 *            HttpGet-Response.
 * @throws Exception wird bei DB-Fehlern geworfen
 */
@JsonIgnore
public static void MIXupdateSeveral«androidEntity.name.toFirstUpper»sAtBackendWithAll«androidEntity.name.toFirstUpper»sFromLocalDatabase(final Context context, final INoReturnValueCallback callback) throws Exception {
	
	ArrayList<«androidEntity.name.toFirstUpper»> entityList = «androidEntity.name.toFirstUpper».DATABASEloadAll«androidEntity.name.toFirstUpper»sFromLocalDatabase(context);
	«androidEntity.name.toFirstUpper»PersistenceWrapper.updateSeveral«androidEntity.name.toFirstUpper»sAtBackendWithAll«androidEntity.name.toFirstUpper»sFromLocalDatabase(entityList, callback);
}
//
'''
}
	
}