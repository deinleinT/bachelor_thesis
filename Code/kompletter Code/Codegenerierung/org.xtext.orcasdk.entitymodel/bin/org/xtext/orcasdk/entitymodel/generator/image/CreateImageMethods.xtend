package org.xtext.orcasdk.entitymodel.generator.image

import org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute
import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity

class CreateImageMethods {
	
def static compileimages(AndroidAttribute attribute, AndroidEntity androidEntity, String packagename){
'''
//ab hier Methoden für images «attribute.name»

/**
* NUR BACKEND: Methode zum Speichern oder Updaten eines Images vom Namen "«attribute.name»" im Orca-Backend. BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/image/{property}
* 
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird
* @param ressourceOfImage die AndroidInt-Ressource, z.B. R.drawable.ic_launcher
* @param callback Callback zum Abfragen von Fehlern und des Http-Response-StatusCode
*/
@JsonIgnore
public void BACKENDsaveOrUpdateImage«attribute.name.toFirstUpper»ByIntRessourceAtBackend(Context context, int ressourceOfImage, INoReturnValueCallback callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.saveOrUpdate«androidEntity.name.toFirstUpper»ImageByIntRessource(this.getId(), "«attribute.name»", context, ressourceOfImage, callback);
}

/**
* NUR BACKEND: Methode zum Speichern oder Updaten eines Images vom Namen "«attribute.name»" im Orca-Backend. BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/image/{property}
* 
* @param context ApplicationContext der Actitivity, von der aus diese
*            Methode aufgerufen wird
* @param inputStream das Bild als InputStream
* @param callback Callback zum Abfragen von Fehlern und des Http-Response-StatusCode
*/
@JsonIgnore
public void BACKENDsaveOrUpdateImage«attribute.name.toFirstUpper»ByInputStreamAtBackend(Context context, InputStream inputStream, INoReturnValueCallback callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.saveOrUpdate«androidEntity.name.toFirstUpper»ImageByInputStream(this.getId(), "«attribute.name»", context, inputStream, callback);
}

/**
* NUR BACKEND: Methode zum Löschen des Image mit Namen «attribute.name» im Orca-Backend.
* BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/image/{property}
* 
* @param callback Callback zum Abfragen des Http-Response-StatusCode und einer ErrorMessage, falls Fehler auftreten.
*/
@JsonIgnore
public void BACKENDdeleteImage«attribute.name.toFirstUpper»FromBackend(INoReturnValueCallback callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.delete«androidEntity.name.toFirstUpper»Image(this, "«attribute.name»", callback);
}

/**
* NUR BACKEND: Methode zum Laden des Image mit Namen "«attribute.name»" aus dem Orca-Backend.
* BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/image/{property}
* 
* @param width die Breite, die das abgefragte Bild haben soll (in Pixeln)
* @param height die Höhe, die das abgefragte Bild haben soll (in Pixeln)
* @param radius hierüber wird angegeben, ob und wie stark die Ecken abgerundet werden
* @param backgroundColor Hintergrundfarbe als Hex-Wert, dieser ist als String zu übergeben! 
* Der String muss genau 8 Zeichen lang sein, und einen korrekten Farbwert darstellen, ansonsten
* treten Fehler auf. (DefaultWert ist Farbe weiß "00000000").
* @param callback Callback zum Abfragen des HTTP-Response-StatusCode und einer ErrorMessage
*/
@JsonIgnore
public void BACKENDloadImage«attribute.name.toFirstUpper»FromBackend(int width, int height, int radius, String backgroundColor, IImageCallback callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.load«androidEntity.name.toFirstUpper»Image(this.getId(), "«attribute.name»", width, height, radius, backgroundColor, callback);
}
	
// bis hier image «attribute.name»

// hier kommen die zusätzlichen imageMethoden

/**
 * BACKEND UND LOKALE DATENBANK: Methode zum Speichern/Updaten eines Image mit Namen "«attribute.name»" im Backend 
 * und der lokalen DB. Wenn Speichern/Update im Backend erfolgreich war, dann wird die entsprechend neue Entity vom 
 * Backend geladen und mit dem neuen ImageProperty in der lokalen Datenbank gespeichert/upgedatet.
 * ACHTUNG: Es sollte sichergestellt sein, dass die Entity schon in der lokalen Datenbank vorhanden
 * ist. Ansonsten kommt es zu Fehlern und das Image wird nur im Backend gespeichert. 
 * BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/image/{property}
 * 
 * @param ressourceOfImage die AndroidInt-Ressource, z.B. R.drawable.ic_launcher
 * @param context ApplicationContext der Actitivity, von der aus diese
 *            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
 * @param callback Callback zum Abfragen von Fehlern und des Http-Response-StatusCode, DARF NICHT NULL SEIN
 */
@JsonIgnore
public void MIXsaveOrUpdateImage«attribute.name.toFirstUpper»ByIntRessourceAtBackendAndUpdateLocalDatabase(int ressourceOfImage, final Context context, final INoReturnValueCallback callback){
	
	final «androidEntity.name.toFirstUpper» «androidEntity.name» = this;
	«androidEntity.name.toFirstUpper»PersistenceWrapper.saveOrUpdate«androidEntity.name.toFirstUpper»ImageByIntRessource(this.getId(), "«attribute.name»", context, ressourceOfImage, new INoReturnValueCallback() {
					
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
 * BACKEND UND LOKALE DATENBANK: Methode zum Speichern/Updaten eines Image mit Namen "«attribute.name»" im Backend 
 * und der lokalen DB. Wenn Speichern/Update im Backend erfolgreich war, dann wird die entsprechend neue Entity vom 
 * Backend geladen und mit dem neuen ImageProperty in der lokalen Datenbank gespeichert/upgedatet.
 * ACHTUNG: Es sollte sichergestellt sein, dass die Entity schon in der lokalen Datenbank vorhanden
 * ist. Ansonsten kommt es zu Fehlern und das Image wird nur im Backend gespeichert. 
 * BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/image/{property}
 * 
 * @param inputStream das Bild als InputStream
 * @param context ApplicationContext der Actitivity, von der aus diese
 *            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
 * @param callback Callback zum Abfragen von Fehlern und des Http-Response-StatusCode, DARF NICHT NULL SEIN
 */
@JsonIgnore
public void MIXsaveOrUpdateImage«attribute.name.toFirstUpper»ByInputStreamAtBackendAndUpdateLocalDatabase(InputStream inputStream, final Context context, final INoReturnValueCallback callback){
	
	final «androidEntity.name.toFirstUpper» «androidEntity.name» = this;
	«androidEntity.name.toFirstUpper»PersistenceWrapper.saveOrUpdate«androidEntity.name.toFirstUpper»ImageByInputStream(this.getId(), "«attribute.name»", context, inputStream, new INoReturnValueCallback() {
					
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
 * BACKEND UND LOKALE DATENBANK: Methode zum Löschen des Image mit Namen "«attribute.name»" 
 * Wenn Löschen im Backend erfolgreich war, dann wird in der lokalen Datenbank die Entity upgedatet
 * (das ImageProperty wird entfernt).
 * ACHTUNG: Es sollte sichergestellt sein, dass die Entity schon in der lokalen Datenbank vorhanden
 * ist. Ansonsten kommt es zu Fehlern und das Image wird nur im Backend gespeichert. 
 * BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/image/{property}
 * @param context ApplicationContext der Actitivity, von der aus diese
 *            Methode aufgerufen wird; wird für das Öffnen, Lesen und Verändern der DB benötigt
 * @param callback Callback zum Abfragen von Fehlern und des Http-Response-StatusCode
 */
@JsonIgnore
public void MIXdeleteImage«attribute.name.toFirstUpper»FromBackendAndUpdateLocalDatabase(final Context context, final INoReturnValueCallback callback){
	
	final «androidEntity.name.toFirstUpper» «androidEntity.name» = this;
	«androidEntity.name.toFirstUpper»PersistenceWrapper.delete«androidEntity.name.toFirstUpper»Image(this, "«attribute.name»", new INoReturnValueCallback() {
					
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

'''
}


}