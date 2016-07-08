package org.xtext.orcasdk.entitymodel.generator.constants

import org.xtext.orcasdk.entitymodel.entityModel.Packages
import org.xtext.orcasdk.entitymodel.generator.HelperClass

class CreateEntityTypeConstants {
	
def static packagecompile(Packages pack){
		
'''
package «pack.name»;
		
public class EntityTypeConstants {

«FOR aentity : pack.entities»
public static final Long TYPE_«pack.name.replace(".","_").toUpperCase»_«aentity.name.toUpperCase» = «HelperClass.packageEntitiesTypes.get(aentity.name)»l;
«ENDFOR»
}
'''
}

}