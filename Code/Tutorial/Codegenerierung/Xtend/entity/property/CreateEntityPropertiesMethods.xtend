package org.xtext.orcasdk.entitymodel.generator.entity.property

import org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute
import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity
import org.xtext.orcasdk.entitymodel.generator.image.CreateImageMethods
import org.xtext.orcasdk.entitymodel.generator.link.CreateEntityLinkProperties

class CreateEntityPropertiesMethods {
	
def static compileEntityAttributesMethods(AndroidAttribute attribute, AndroidEntity androidEntity, String packagename) 
{
	if(attribute!=null){
	 	if(isAttributeTypeImage(attribute)){
	 		CreateImageMethods.compileimages(attribute, androidEntity, packagename)
	 	}else if(isAttributeTypeLink(attribute)){
	 		CreateEntityLinkProperties.compilelinks(attribute, androidEntity, packagename)
	 	}else{
'''«compileGetterAndSetterAndOtherMethodsForEachProperty(attribute, packagename, androidEntity)»'''		
			} 		
	 	}
	}
	
def static compileGetterAndSetterAndOtherMethodsForEachProperty(AndroidAttribute attribute, String packagename, AndroidEntity androidEntity)
'''
//getter und setter aller Properties
«createGetterAndSetterMethods(attribute)»
		
/**
* NUR BACKEND: Methode zum Abfragen des EntityProperty-Wertes mit Namen "«attribute.name»" vom Backend.
* Über das Callback kann der Property-Wert abgefragt werden.
* BACKEND-ENDPUNKT: ...api/{appname}/entities/{entityId}/{property}
* 
* @param callback Callback zum Abfragen des Wertes des Property vom Typ
* «attribute.type.toFirstUpper», von Fehlern und des Http-Response-StatusCode
*/
@JsonIgnore
public void BACKENDloadProperty«attribute.name.toFirstUpper»FromBackend(de.fhws.sdk.orca.callback.I«attribute.type.toFirstUpper.replace("[","Array").replace("]","")»Callback callback){
	de.fhws.sdk.orca.property.Get«attribute.type.toFirstUpper.replace("[","Array").replace("]","")»PropertyFromBackend<«packagename+"."+androidEntity.name.toFirstUpper»> get = new de.fhws.sdk.orca.property.Get«attribute.type.toFirstUpper.replace("[","Array").replace("]","")»PropertyFromBackend<«packagename+"."+androidEntity.name.toFirstUpper»>("«attribute.name»", de.fhws.sdk.orca.AppConstants.APPNAME, de.fhws.sdk.orca.AppConstants.APIKEY, callback);
	get.execute(this);
}
		
/**
* NUR BACKEND: Methode zum Löschen des EntityProperty-Wertes mit Namen "«attribute.name»" im Orca-Backend.
* Über das Callback kann der Wert abgefragt werden.
* BACKEND-ENDPUNKT: api/{appname}/entities/{entityId}/{property}
* 
* @param callback Callback zum Abfragen von Fehlern und des Http-Response-StatusCode
*/
@JsonIgnore
public void BACKENDdeleteProperty«attribute.name.toFirstUpper»AtBackendAndFromThisObject(INoReturnValueCallback callback){
	«androidEntity.name.toFirstUpper»PersistenceWrapper.deleteSingle«androidEntity.name.toFirstUpper»Property(this, "«attribute.name»", callback);
}
		
/**
* NUR BACKEND: Methode zum Updaten des EntityPropert mit Namen "«attribute.name» im Orca-Backend.
* Falls als Wert null übergeben wird, erfolgt kein update.
* 
* @param newValue der neue Property-Wert vom Typ «attribute.type.toFirstUpper»
* @param callback Callback zum Abfragen von Fehlern und des
*            Http-Response-StatusCode.
*/
@JsonIgnore
public void BACKENDupdateProperty«attribute.name.toFirstUpper»AtBackendAndAtThisObject(«attribute.type.toFirstUpper» newValue, INoReturnValueCallback callback){
	HashMap<String,Object> map = new HashMap<String,Object>();
	map.put("«attribute.name»", newValue);
							
	if(map.size()!=0){
		BACKENDupdate«androidEntity.name.toFirstUpper»WithNewProperties(map, callback);
	}
}
		'''
	
	
def static createGetterAndSetterMethods(AndroidAttribute attribute){
'''
«IF attribute.type.equalsIgnoreCase("double[]")»
«createCommentForGetter(attribute)»
@JsonIgnore
public «attribute.type.toFirstUpper» get«attribute.name.toFirstUpper»() {
	return super.getDoubleArrayProperty("«attribute.name»", «attribute.type.toFirstUpper».class);
}
«createCommentForSetter(attribute)»
@JsonIgnore
public void set«attribute.name.toFirstUpper»(«attribute.type.toFirstUpper» «attribute.name») {
	
	super.setProperty("«attribute.name»", «attribute.name»);
}
«ELSE»
«createCommentForGetter(attribute)»
@JsonIgnore
public «attribute.type.toFirstUpper» get«attribute.name.toFirstUpper»() {
	return super.get«attribute.type.toFirstUpper»Property("«attribute.name»", «attribute.type.toFirstUpper».class);
}

«createCommentForSetter(attribute)»
@JsonIgnore
public void set«attribute.name.toFirstUpper»(«attribute.type.toFirstUpper» «attribute.name») {
   	
	super.setProperty("«attribute.name»", «attribute.name»);

}
«ENDIF»
'''
}	
	
def static isAttributeTypeLink(AndroidAttribute attribute) {
	attribute.type.equalsIgnoreCase("link")
}
	
def static isAttributeTypeImage(AndroidAttribute attribute) {
	attribute.type.equalsIgnoreCase("image")
}
	
def static createCommentForSetter(AndroidAttribute attribute) {
'''
/**
* Setter-Methode für das EntityProperty "«attribute.name»". ACHTUNG: Wird als Value null übergeben, 
* wird dieses Property gelöscht.
* 
* @param «attribute.name» muss vom Typ «attribute.type.toFirstUpper» sein
*/
'''
}
	
def static createCommentForGetter(AndroidAttribute attribute) {
'''
/**
* Getter-Methode für das EntityProperty "«attribute.name»"
* 
* @return Wert des Property vom Typ «attribute.type.toFirstUpper»
*/
'''		
}

}