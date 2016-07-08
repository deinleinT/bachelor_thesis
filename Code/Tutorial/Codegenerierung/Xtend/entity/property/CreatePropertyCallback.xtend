package org.xtext.orcasdk.entitymodel.generator.entity.property

import org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute

class CreatePropertyCallback {
	

def static CharSequence compilepropertycallback(AndroidAttribute attribute){
'''
package de.fhws.sdk.orca.callback;
		
«IF attribute.type.equalsIgnoreCase("date")»
import java.util.Date;
«ENDIF»
		
public interface I«attribute.type.toFirstUpper.replace("[","Array").replace("]","")»Callback{
	void onComplete(«attribute.type.toFirstUpper» returnValue, int statusCode, String errorMessage);
}
'''
}
}