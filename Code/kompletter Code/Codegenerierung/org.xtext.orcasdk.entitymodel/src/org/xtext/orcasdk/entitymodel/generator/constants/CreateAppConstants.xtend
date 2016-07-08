package org.xtext.orcasdk.entitymodel.generator.constants

class CreateAppConstants {
	
	def static appconstantscompile(String appName, String apiKey){
		
		'''
		package de.fhws.sdk.orca;
		
		public class AppConstants {
			
			public static final String APPNAME = "«appName»";
			public static final String APIKEY = "«apiKey»";
			
		}
		'''
	}


}