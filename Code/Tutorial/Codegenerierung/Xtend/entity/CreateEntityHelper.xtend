package org.xtext.orcasdk.entitymodel.generator.entity

import org.xtext.orcasdk.entitymodel.generator.HelperClass
import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity
import org.xtext.orcasdk.entitymodel.generator.entity.property.CreateAndroidEntityProperties
import org.xtext.orcasdk.entitymodel.generator.entity.property.CreateEntityPropertiesMethods

class CreateEntityHelper {
	

def static createEntityClassComment(){
'''
/**
 * Vom Generator erzeugte EntityKlasse. Die spezifischen Methoden für alle
 * Properties, Links und Images werden anhand der Eingaben im Model erzeugt.
 * Diese Klasse leitet von {@linkplain de.fhws.sdk.orca.model.Entity Entity} ab
 * und erbt die entsprechend benötigten Methoden.
 * 
 * @author ThomasDeinlein
 */
'''
}

def static createEntityImports(AndroidEntity androidEntity, String packagename){
'''
«createEntityAdditionalImports(androidEntity)»
import com.owlike.genson.annotation.JsonIgnore;
import java.util.*;
import android.content.Context;
import android.database.SQLException;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.helper.NetworkHelper;
import de.fhws.sdk.orca.model.EntityPage;
import «packagename».persistence.backend.«androidEntity.name».«androidEntity.name.toFirstUpper»PersistenceWrapper;
import de.fhws.sdk.orca.network.callback.IReturnValueCallback;
import de.fhws.sdk.orca.network.callback.IPageCallback;
import de.fhws.sdk.orca.network.callback.INoReturnValueCallback;
import de.fhws.sdk.orca.network.callback.IAllEntityIdsCallback;
import de.fhws.sdk.orca.network.callback.ILinkPageCallback;
import de.fhws.sdk.orca.network.callback.IImageCallback;
import «packagename».persistence.database.«androidEntity.name».«androidEntity.name.toFirstUpper»DatabaseWrapper;
'''
}

def static createEntityAdditionalImports(AndroidEntity androidEntity){
	
'''
«IF HelperClass.image.containsKey(androidEntity.name)»
«HelperClass.imageimport.get(0)»
«ENDIF»
«IF HelperClass.links.containsKey(androidEntity.name)»
«HelperClass.linkimport.get(0)»
«ENDIF»
'''
}
	
def static createAllEntityProperties(AndroidEntity androidEntity) {
'''
«FOR attribute: androidEntity.attributes»
«CreateAndroidEntityProperties.compileattributes(attribute)»
«ENDFOR»
'''
}
	
def static createEntityConstructor(AndroidEntity androidEntity, String theCorrectEntityType) {
'''
/**
* StandardConstructor dieses EntityTyps. In diesem werden der EntityType,
* der im Model eingegeben wurde, gesetzt. Ebenso werden alle
* EntityProperty, LinkProperty und ImageKeys in die dafür vorgesehenen
* Hashmaps eingetragen, damit die Zugriff auf die lokale Datenbank möglich
* ist.
*/
public «androidEntity.name.toFirstUpper»(){
	super.setType(«theCorrectEntityType»l);
	«FOR attributes : androidEntity.attributes»
	«IF attributes.type.equalsIgnoreCase("link")»
	«FOR linkprops : attributes.linkproperties»
	super.linkPropertyDatatypes.put("«attributes.name» «linkprops.name»","«linkprops.type»");
	«ENDFOR»
	«ELSEIF attributes.type.equalsIgnoreCase("image")»
	super.imagePropertyNames.add("«attributes.name»");
	«ELSE»
	super.entityPropertyDatatypes.put("«attributes.name»","«attributes.type»");
	«ENDIF»
	«ENDFOR»
}
'''
}
	
def static createAllEntityPropertyMethods(AndroidEntity androidEntity, String packagename) {
'''
«FOR attribute: androidEntity.attributes»
«CreateEntityPropertiesMethods.compileEntityAttributesMethods(attribute, androidEntity, packagename)»
«ENDFOR»
'''	
}
	
	
}