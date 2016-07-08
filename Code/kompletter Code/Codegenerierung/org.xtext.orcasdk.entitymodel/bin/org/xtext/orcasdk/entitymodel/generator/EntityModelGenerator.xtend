
package org.xtext.orcasdk.entitymodel.generator

import java.util.HashMap
import java.util.HashSet
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.xtext.orcasdk.entitymodel.entityModel.AppConstants
import org.xtext.orcasdk.entitymodel.entityModel.Packages
import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity
import org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute
import org.xtext.orcasdk.entitymodel.generator.link.CreateIndividualPostLinksFromSameTypeAsyncTask
import org.xtext.orcasdk.entitymodel.generator.backend.CreateIndividualPersistenceWrapper
import org.xtext.orcasdk.entitymodel.generator.backend.CreateBackendPersistenceWrapper
import org.xtext.orcasdk.entitymodel.generator.entity.CreateEntity
import org.xtext.orcasdk.entitymodel.generator.constants.CreateEntityTypeConstants
import org.xtext.orcasdk.entitymodel.generator.constants.CreateAppConstants
import org.xtext.orcasdk.entitymodel.generator.database.CreateIndividualDatabaseWrapper
import org.xtext.orcasdk.entitymodel.generator.link.CreateGetAllLinkBelongsToAsyncTask
import org.xtext.orcasdk.entitymodel.generator.entity.property.CreatePropertyCallback
import org.xtext.orcasdk.entitymodel.generator.entity.property.CreatePropertyEntityAsyncTask

class EntityModelGenerator implements IGenerator {
	val HashSet<String> listToCheckAndSetCorrectEntityTypes = new HashSet<String>()
	int tempVariableToCheckAndSetCorrectEntityType
	String theCorrectEntityType

	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		
		loopOverAppConstants(resource, fsa);
	}
	
	def loopOverAppConstants(Resource resource, IFileSystemAccess fsa) {
		
		for(appconstants: resource.allContents.toIterable.filter(AppConstants)){
			loopOverPackages(resource, fsa)
			generateAppConstantsClass(fsa, appconstants)
			generateBackendPersistenceWrapper(fsa)
		 	initializeEntityTypeCheckVariables()
		}
	}
	
	def loopOverPackages(Resource resource, IFileSystemAccess fsa) {
		
		for(packages : resource.allContents.toIterable.filter(Packages)){	
			loopOverAndroidEntities(packages, fsa)
			generateEntityTypeConstantsClass(fsa, packages)	
		}
	}
	
	def loopOverAndroidEntities(Packages packages, IFileSystemAccess fsa) {
				
		for(AndroidEntity androidEntity : packages.entities){ 
			entityTypeCheck(androidEntity)		
			loopOverAttributes(androidEntity, fsa, packages)
			generateSeveralClasses(fsa, androidEntity, packages)
			clearHelperClassImageAndLinksHashMap()
		}
	}
	
	def loopOverAttributes(AndroidEntity androidEntity, IFileSystemAccess fsa, Packages packages) {
					
		for(AndroidAttribute attribute : androidEntity.attributes){
			
			if(isAttributeTypeImage(attribute.type)){
				
				setImageImportsHelperClassVariable(androidEntity)	
				
			}else if(isAttributeTypeLink(attribute.type)){
				
				setLinkImportsHelperClassVariable(androidEntity, attribute)
				generateGetAllEntitiesFromLinkClass(fsa, attribute, androidEntity, packages)
				
			}else{	
				generateDatatypeCallback(fsa, attribute)
				generateGetDatatypePropertyClass(fsa, attribute)
			}
		}
	}
	
	def setLinkImportsHelperClassVariable(AndroidEntity androidEntity, AndroidAttribute attribute) {
		HelperClass.links.put(androidEntity.name,attribute.name)		
	}
	
	def setImageImportsHelperClassVariable(AndroidEntity androidEntity) {
		HelperClass.image.put(androidEntity.name,"image");
	}
	
	def generateGetDatatypePropertyClass(IFileSystemAccess fsa, AndroidAttribute attribute) {
		fsa.generateFile(("de.fhws.sdk.orca.property").replace(".","/")+"/"+"Get"+attribute.type.toFirstUpper.replace("[","Array").replace("]","")+"PropertyFromBackend"+".java",CreatePropertyEntityAsyncTask.compilepropertyasynctask(attribute))
	}
	
	def generateDatatypeCallback(IFileSystemAccess fsa, AndroidAttribute attribute) {
		fsa.generateFile(("de.fhws.sdk.orca.callback").replace(".","/")+"/"+"I"+attribute.type.toFirstUpper.replace("[","Array").replace("]","")+"Callback"+".java",CreatePropertyCallback.compilepropertycallback(attribute))		
	}
	
	def generateGetAllEntitiesFromLinkClass(IFileSystemAccess fsa, AndroidAttribute attribute, AndroidEntity androidEntity, Packages packages) {
		fsa.generateFile(("de.fhws.sdk.orca.link").replace(".","/")+"/"+"GetAll"+attribute.objectType.name.toFirstUpper+"sFromLink"+attribute.name.toFirstUpper+"BelongsToClass"+packages.name.replace(".","")+androidEntity.name.toFirstUpper+".java",CreateGetAllLinkBelongsToAsyncTask.compilespecialasynctask(attribute, packages.name, androidEntity.name));
	}
	
	def generateBackendPersistenceWrapper(IFileSystemAccess fsa) {
		fsa.generateFile("de/fhws/sdk/orca/persistence"+"/"+"wrapper"+"/"+"BackendPersistenceWrapper.java",CreateBackendPersistenceWrapper.compileBackendPersistenceWrapper)	
	}
	
	def generateIndividualBackendPersistenceWrapper(IFileSystemAccess fsa, Packages packages, AndroidEntity androidEntity) {
		fsa.generateFile(packages.name.replace(".","/")+"/"+"persistence/backend"+"/"+androidEntity.name+"/"+androidEntity.name.toFirstUpper+"PersistenceWrapper.java",CreateIndividualPersistenceWrapper.compileIndividualEntityWrapper(androidEntity,packages.name))	
	}

	def generateEntityTypeConstantsClass(IFileSystemAccess fsa, Packages packages) {
		fsa.generateFile(packages.name.replace(".","/")+"/"+"EntityTypeConstants"+".java",CreateEntityTypeConstants.packagecompile(packages));
	}

	def generateAppConstantsClass(IFileSystemAccess fsa, AppConstants appconstants) {
		fsa.generateFile("de/fhws/sdk/orca"+"/"+"AppConstants"+".java",CreateAppConstants.appconstantscompile(appconstants.valueappname.toString.replace("[","").replace("]",""), appconstants.valueapikey.toString.replace("[","").replace("]","")));	
	}
	
	def generateEntityClass(IFileSystemAccess fsa, AndroidEntity androidEntity, Packages packages) {
		fsa.generateFile(packages.name.replace(".","/")+"/"+androidEntity.name.toFirstUpper+".java",CreateEntity.compileEntity(androidEntity,packages.name, theCorrectEntityType));	
	}
	
	def generatePostLinksFromSameTypeClass(IFileSystemAccess fsa, AndroidEntity androidEntity, Packages packages) {  
		fsa.generateFile(("de.fhws.sdk.orca.link.").replace(".","/")+"PostLinksFromSameType"+packages.name.replace(".","")+androidEntity.name.toFirstUpper+".java",CreateIndividualPostLinksFromSameTypeAsyncTask.createPostLinkAsyncTask(androidEntity,packages.name))			
	}
	
	def generateIndividualDatabasePersistenceWrapper(IFileSystemAccess fsa, AndroidEntity androidEntity, Packages packages) {
		fsa.generateFile(packages.name.replace(".","/")+"/"+"persistence/database"+"/"+androidEntity.name+"/"+androidEntity.name.toFirstUpper+"DatabaseWrapper.java",CreateIndividualDatabaseWrapper.createIndividualDatabaseWrapper(androidEntity, packages.name))	
	}
	
	def clearHelperClassImageAndLinksHashMap() {
		HelperClass.image.clear
		HelperClass.links.clear
	}
	
	def initializeEntityTypeCheckVariables() {
		HelperClass.packageEntitiesTypes = new HashMap<String,String>()
		listToCheckAndSetCorrectEntityTypes.clear
	}
	
	def entityTypeCheck(AndroidEntity androidEntity) {
		checkEntityTypeAndSetToCorrectValue(androidEntity.value.toString.replace("[","").replace("]",""))
		HelperClass.packageEntitiesTypes.put(androidEntity.name,theCorrectEntityType)	
	}

	def generateSeveralClasses(IFileSystemAccess fsa, AndroidEntity androidEntity, Packages packages) {
		generatePostLinksFromSameTypeClass(fsa, androidEntity, packages)
		generateEntityClass(fsa, androidEntity, packages)
		generateIndividualBackendPersistenceWrapper(fsa, packages, androidEntity)	
		generateIndividualDatabasePersistenceWrapper(fsa, androidEntity, packages)	
	}
	
	def boolean isAttributeTypeLink(String string) {
		return string.equalsIgnoreCase("link")
	}
	
	def boolean isAttributeTypeImage(String string) {
		return string.equalsIgnoreCase("image")
	}
	
	// Methode, die die EntityTypes auf Dupletten überprüft und evtl. um eins erhöht, wenn Wert schon vergeben wurde
	def checkEntityTypeAndSetToCorrectValue(String string) {
		theCorrectEntityType = string
		if(!listToCheckAndSetCorrectEntityTypes.contains(theCorrectEntityType)){
			listToCheckAndSetCorrectEntityTypes.add(theCorrectEntityType)
		}else{
			while(listToCheckAndSetCorrectEntityTypes.contains(theCorrectEntityType)){
				tempVariableToCheckAndSetCorrectEntityType = Integer.parseInt(theCorrectEntityType)
				tempVariableToCheckAndSetCorrectEntityType += 1
				theCorrectEntityType = String.valueOf(tempVariableToCheckAndSetCorrectEntityType)
				if(!listToCheckAndSetCorrectEntityTypes.contains(theCorrectEntityType)){
					listToCheckAndSetCorrectEntityTypes.add(theCorrectEntityType)
					return
				}
			}
		}
	}
}
