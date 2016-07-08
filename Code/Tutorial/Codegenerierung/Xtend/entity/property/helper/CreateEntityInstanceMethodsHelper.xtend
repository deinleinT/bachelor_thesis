package org.xtext.orcasdk.entitymodel.generator.entity.property.helper

import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity

class CreateEntityInstanceMethodsHelper {
	
def static createAllInstanceMethode(AndroidEntity androidEntity, String packagename) {
'''
/**
* NUR BACKEND: Methode zum Abspeichern einer Entity im Backend. ACHTUNG: Hierbei wird
* zuerst geprüft, ob es diese Entity im Backend schon gibt. Dabei wird aus
* den EntityProperties eine Query erzeugt. Kommt zu dieser Query mindestens
* ein ErgebnisEntity zurück, erfolgt keine Speicherung. In der über die über
* das Callback abrufbare errorMessage werden alle gefundenen EntityIds
* ausgegeben. Wenn die Get-Request keine Entity zurückliefert, erfolgt der
* Post der Entity. Über das Callback kann die gespeicherte Entity dann
* abgefragt werden.
* BACKEND-ENDPUNKT: /api/{appname}/entities
* 
* @param callback Callback zum Abrufen der gespeicherte Entity, des
*            Http-statusCode (-1 bei Fehlern) und der errorMessage
*/
@JsonIgnore
public void BACKENDsaveThis«androidEntity.name.toFirstUpper»ToBackendWithCheckWhetherThisEntityAlreadyExists(IReturnValueCallback<«packagename».«androidEntity.name.toFirstUpper»> callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.save«androidEntity.name.toFirstUpper»WithCheckWhether«androidEntity.name.toFirstUpper»StillExists(this, callback);
}

/**
* NUR BACKEND: Methode zum Abspeichern einer Entity im Backend. Über das Callback kann die gespeicherte Entity dann
* abgefragt werden.
*
* BACKEND-ENDPUNKT: /api/{appname}/entities
* 
* @param callback Callback zum Abrufen der gespeicherte Entity, des
*            Http-statusCode (-1 bei Fehlern) und der errorMessage
*/
@JsonIgnore
public void BACKENDsaveThis«androidEntity.name.toFirstUpper»ToBackendWithoutCheck(IReturnValueCallback<«packagename».«androidEntity.name.toFirstUpper»> callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.save«androidEntity.name.toFirstUpper»WithoutCheck(this, callback);
}

/**
* NUR BACKEND: Methode zum Prüfen, ob es evtl. schon ein Entity mit gleichen Properties
* im Backend gibt. Dient zum Auffinden / Vermeiden von Dupletten im
* Backend. Aus den Entity Properties wird ein QueryString erstellt. Anhand
* dieses String erfolgt eine Request ans Backend. Wenn Entities gefunden
* werden, können diese über das Callback abgerufen werden.
* BACKEND-ENDPUNKT: .../api/{appname}/entities
* 
* @param callback Callback über das die Ergebnis-EntityPage, der
*            Http-StatusCode und eine ErrorMessag abgerufen werden kann
*/
@JsonIgnore
public void BACKENDloadSimilar«androidEntity.name.toFirstUpper»sFromBackend(IPageCallback<«packagename».«androidEntity.name.toFirstUpper»> callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.load«androidEntity.name.toFirstUpper»sByAttached«androidEntity.name.toFirstUpper»Object(this, callback);
}	

/**
* NUR BACKEND: Methode zum Updaten aller EntityProperties im Backend. Falls eine leere
* Hashmap übergeben wird erfolgt kein Update. BACKEND-ENDPUNKT:
* api/{appname}/entities/{id}/property
* 
* @param newProperties die neuen Properties --> falls diese leer sind, wird
*            kein Update durchgeführt
* @param callback Callback zum Abrufen des Http-StatusCode und der
*            ErrorMessage
*/
@JsonIgnore
private void BACKENDupdate«androidEntity.name.toFirstUpper»WithNewProperties(HashMap<String,Object> newProperties, INoReturnValueCallback callback){		
	
	if(newProperties.size()!=0){
		«androidEntity.name.toFirstUpper»PersistenceWrapper.update«androidEntity.name.toFirstUpper»WithNewProperties(this, newProperties, callback);	
	}
}

/**
* NUR BACKEND: Methode zum Updaten aller EntityProperties im Backend. Falls alle PropertyParameter mit null belegt werden, erfolgt kein Update. 
* BACKEND-ENDPUNKT: api/{appname}/entities/{id}/property
* 
* @param callback Callback zum Abrufen des Http-StatusCode und der
*            ErrorMessage
*/
@JsonIgnore
public void BACKENDupdate«androidEntity.name.toFirstUpper»WithNewPropertiesAtBackendAndAtThisObject(«FOR attribute:androidEntity.attributes»«IF (!(attribute.type.equalsIgnoreCase("link") || attribute.type.equalsIgnoreCase("image")))»«attribute.type.toFirstUpper» «attribute.name», «ENDIF»«ENDFOR» INoReturnValueCallback callback){
	HashMap<String,Object> map = new HashMap<String,Object>();
	«FOR attribute: androidEntity.attributes»
	«IF (!(attribute.type.equalsIgnoreCase("link") || attribute.type.equalsIgnoreCase("image")))»
	if(«attribute.name» != null){
		«"map.put(\""+attribute.name+"\", "+attribute.name+");"»
	}
	«ENDIF»
	«ENDFOR»
	
	if(map.size()!=0){
		«androidEntity.name.toFirstUpper»PersistenceWrapper.update«androidEntity.name.toFirstUpper»WithNewProperties(this, map, callback);
	}
}
	
/**
* LOKALE DATENBANK: Methode zum Speichern dieser Entity in der lokalen
* SQLite-Datenbank. Bei Fehlern wird eine Exception geworfen.
* 
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
* @returns die ID, unter der Entity in DB gespeichert wurde, -1 bei Fehlern
* @throws Exception bei Fehlern, die während des Speichervorgangs auftreten
*/
@JsonIgnore
public long DATABASEsaveToLocalDatabase(Context context) throws Exception {
	return «androidEntity.name.toFirstUpper»DatabaseWrapper.saveEntity(context, this);
}

/**
* LOKALE DATENBANK: Methode zum updaten dieser Entity in der SQLite-DB.
* ACHTUNG: Die Entity muss vorher schon in der DB gespeichert worden sein.
* 
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
* @return anzahl der Rows, die in der Entity-Tabelle geändert wurden
* @throws Exception bei auftretenden Datenbankfehlern
*/
@JsonIgnore
public int DATABASEupdateAtLocalDatabase(Context context) throws Exception {
	return «androidEntity.name.toFirstUpper»DatabaseWrapper.updateEntity(context, this);
}

/**
* LOKALE DATENBANK: Methode zum Löschen dieser Entity aus der SQLite-DB.
* 
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
* @return Anzahl der Rows, die in der Entity-Tabelle gelöscht wurden -->
*         Fehler, wenn nicht 1 zurückgegeben wird
* @throws Exception bei auftretenden Datenbankfehlern
*/
@JsonIgnore
public int DATABASEdeleteFromLocalDatabase(Context context) throws Exception {
	return «androidEntity.name.toFirstUpper»DatabaseWrapper.deleteEntity(context, this);
}

/**
* BACKEND UND LOKALE DATENBANK: Methode zum löschen der «androidEntity.name.toFirstUpper»-Entity, von der die Methode aufgerufen wird.
* Dabei wird die «androidEntity.name.toFirstUpper»-Entity zuerst vom Backend gelöscht. War dies
* erfolgreich, wird die Entity auch von der lokalen Datenbank gelöscht. ACHTUNG: Das Java-Objekt dieser Entity kann danach nicht 
* mehr gespeichert werden!
* 
*
* BACKEND-ENDPUNKT: api/{appname}/entities/{entityId}
* 
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
* @param noReturnValueCallback das Callback gibt den StatusCode und eine
*            ErrorMessage zurück. Der StatusCode ist -1 bei Fehlern.
*            Ansonsten ist der StatusCode der StatusCode der
*            HttpGet-Response.
*/
@JsonIgnore
public void MIXdeleteFromBackendAndLocalDatabase(final Context context, final INoReturnValueCallback noReturnValueCallback){
	final «androidEntity.name.toFirstUpper» «androidEntity.name» = this;
	«androidEntity.name.toFirstUpper»PersistenceWrapper.delete«androidEntity.name.toFirstUpper»ById(«androidEntity.name».getId(), new INoReturnValueCallback() {
					
					@Override
					public void onComplete(int statusCode, String errorMessage) {
					
						if (NetworkHelper.isStatusCodeBetween200And300(statusCode)){
							try {
								«androidEntity.name».DATABASEdeleteFromLocalDatabase(context);
								if(noReturnValueCallback!=null){
									noReturnValueCallback.onComplete(statusCode, errorMessage);
								}
							}
							catch (Exception e) {
								if(noReturnValueCallback!=null){
									noReturnValueCallback.onComplete(-1, e.getMessage());
								}
							}
						}else {
							if(noReturnValueCallback!=null){
								noReturnValueCallback.onComplete(statusCode, errorMessage);
							}
						}
					}
				});
}
	
/**
* BACKEND UND LOKALE DATENBANK: 
* Methode zum updaten der «androidEntity.name.toFirstUpper»-Entity im Backend. Im Backend werden alle aktuell im Entity befindlichen EntitiesProperties geupdatet.
* War das Update im Backend erfolgreich, wird Entity auch in der lokalen
* SQLite-DB upgedatet.
*
* BACKEND-ENDPUNKT: /api/{appname}/entities/{id}
* 
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
* @param callback das Callback gibt den StatusCode und eine ErrorMessage
*            zurück. Der StatusCode ist -1 bei Fehlern. Ansonsten ist der
*            StatusCode der StatusCode der HttpGet-Response.
*/
@JsonIgnore
public synchronized void MIXupdateAtBackendAndLocalDatabase(final Context context, final INoReturnValueCallback callback){
	final «androidEntity.name.toFirstUpper» «androidEntity.name» = this;
	«androidEntity.name.toFirstUpper»PersistenceWrapper.update«androidEntity.name.toFirstUpper»WithNew«androidEntity.name.toFirstUpper»(this, this, new INoReturnValueCallback() {
					
					@Override
					public void onComplete(int statusCode, String errorMessage) {
					
						if (NetworkHelper.isStatusCodeBetween200And300(statusCode)){
							try {
								«androidEntity.name».DATABASEupdateAtLocalDatabase(context);
								if (callback != null) {
									callback.onComplete(statusCode, errorMessage);
								}
							}
							catch (Exception e) {
								if (callback != null) {
									callback.onComplete(-1, e.getMessage());
								}
							}
						}else {
							if(callback!=null){
								callback.onComplete(statusCode, errorMessage);
							}
						}
					}
				});
}
'''
	
}
	
}