package org.xtext.orcasdk.entitymodel.generator.entity

import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity
import org.xtext.orcasdk.entitymodel.generator.entity.property.helper.CreateEntityInstanceMethodsHelper
import org.xtext.orcasdk.entitymodel.generator.entity.property.helper.CreateEntityStaticMethodsHelper

class CreateEntity {

//Methode für die einzelnen Entitätsklassen
def static compileEntity(AndroidEntity androidEntity, String packagename, String theCorrectEntityType)
{
	
'''
package «packagename»;

«CreateEntityHelper.createEntityImports(androidEntity, packagename)»

«CreateEntityHelper.createEntityClassComment»
public class «androidEntity.name.toFirstUpper» extends Entity{ 

«CreateEntityHelper.createAllEntityProperties(androidEntity)»

«CreateEntityHelper.createEntityConstructor(androidEntity,theCorrectEntityType)»

«CreateEntityHelper.createAllEntityPropertyMethods(androidEntity, packagename)»

// zuerst die InstanzMethoden

«CreateEntityInstanceMethodsHelper.createAllInstanceMethode(androidEntity, packagename)»

// hier die Klassenmethoden

«CreateEntityStaticMethodsHelper.createAllStaticMethods(androidEntity, packagename)»

}
	'''
}


}