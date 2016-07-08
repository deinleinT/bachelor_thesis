package org.xtext.orcasdk.entitymodel.generator.entity.property

import org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute

class CreateAndroidEntityProperties {


//Methode fürs Generieren der einzelnen Entity Properties
def static compileattributes(AndroidAttribute attribute)
{
		if(attribute!=null)
		{if(!attribute.type.equalsIgnoreCase("link") && (!attribute.type.equalsIgnoreCase("image") && (!attribute.type.equalsIgnoreCase("linkwithproperties")))){
'''
	@JsonIgnore
	private «attribute.type.toFirstUpper» «attribute.name»;
'''
}
		}
	}	
}