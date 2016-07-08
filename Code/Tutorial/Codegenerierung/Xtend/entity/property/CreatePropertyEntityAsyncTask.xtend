package org.xtext.orcasdk.entitymodel.generator.entity.property

import org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute

class CreatePropertyEntityAsyncTask {
	

def static CharSequence compilepropertyasynctask(AndroidAttribute attribute){
'''
package de.fhws.sdk.orca.property;		

«IF attribute.type.equalsIgnoreCase("date")»
import java.util.Date;
import de.fhws.sdk.orca.helper.DateHelper;
import org.json.JSONObject;
«ENDIF»

«IF attribute.type.equalsIgnoreCase("double[]")»
import com.owlike.genson.Genson;
import java.util.ArrayList;
import com.owlike.genson.GenericType;
«ENDIF»
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import android.os.AsyncTask;
import android.util.Log;
import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.helper.NetworkHelper;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.callback.I«attribute.type.toFirstUpper.replace("[","Array").replace("]","")»Callback;

/**
 * AsyncClass-Klasse für das Abrufen eine bestimmten EntityProperty vom Backend.
 * Für jeden möglichen RückgabedatenType wird eine solche automatisch generiert.
 * Ebenso werden zugehörige Callbacks generiert. 
 * BACKEND-ENDPUNKT: ...api/{appname}/entities/{entityId}/{property}
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class Get«attribute.type.toFirstUpper.replace("[","Array").replace("]","")»PropertyFromBackend<T extends Entity> extends
		AsyncTask<T, Void, «attribute.type.toFirstUpper»> {
	
	private String				checkUrl;
	private String				appName;
	private String				apiKey;
	private I«attribute.type.toFirstUpper.replace("[","Array").replace("]","")»Callback	callback;
	private T					originalEntity;
	private int					statusCodeHttpGet;
	private String				errorMessage	= Constants.NO_ERROR;
	private String				propertyName;
	private HttpClient			httpGetClient;
	private HttpGet				httpGet;
	private HttpResponse		httpGetResponse;
	private «attribute.type.toFirstUpper»	returnValue;
	
	public Get«attribute.type.toFirstUpper.replace("[","Array").replace("]","")»PropertyFromBackend(String propertyName, String appName,
			String apiKey, I«attribute.type.toFirstUpper.replace("[","Array").replace("]","")»Callback callback) {
	
		this.callback = callback;
		this.propertyName = propertyName;
		this.apiKey = apiKey;
		this.appName = appName;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected «attribute.type.toFirstUpper» doInBackground(T... params) {
	
		try {
			if (checkParameters(params)) {
				originalEntity = params[0];
				
				setGetURL();
				setHttpGetVariablesAndExecuteRequest();
				
				if (NetworkHelper.isStatusCode200(statusCodeHttpGet)) {
					String responseJSONString = EntityUtils.toString(
							httpGetResponse.getEntity(), Constants.UTF_STRING);
					returnValue = fitResponseStringToOriginalDataType(responseJSONString);
				}
				else if (NetworkHelper.isStatusCode403Or404(statusCodeHttpGet)) {
					errorMessage = EntityUtils.toString(
							httpGetResponse.getEntity(), Constants.UTF_STRING);
					statusCodeHttpGet = -1;
					return null;
				}
			}
			else {
				statusCodeHttpGet = -1;
				errorMessage = "GET-Request not possible: invalid Entity, appName, apiKey or propertyName (id==0, null or empty)";
			}
		}
		catch (ClientProtocolException e) {
			Log.e(Constants.TAG_BACKEND,
					"ClientProtocolException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "ClientProtocolException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (ParseException e) {
			Log.e(Constants.TAG_BACKEND, "ParseException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "ParseException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (IOException e) {
			Log.e(Constants.TAG_BACKEND, "IOException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "IOException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (JSONException e) {
			Log.e(Constants.TAG_BACKEND, "JSONException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "JSONException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (NullPointerException e) {
			Log.e(Constants.TAG_BACKEND,
					"NullPointerException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "NullPointerException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (Exception e) {
			Log.e(Constants.TAG_BACKEND, "Exception " + e.getMessage());
			e.printStackTrace();
			errorMessage = "Exception " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		return returnValue;
	}
	
	private void setGetURL() throws UnsupportedEncodingException {
	
		checkUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING)
				+ "/" + Constants.ENTITIES + "/"
				+ originalEntity.getId() + "/"
				+ URLEncoder.encode(propertyName, Constants.UTF_STRING);
	}
	
	private void setHttpGetVariablesAndExecuteRequest() throws IOException,
			ClientProtocolException {
	
		httpGetClient = new DefaultHttpClient();
		httpGet = new HttpGet(checkUrl);
		httpGet.addHeader(Constants.APIKEYHEADER, apiKey);
		httpGetResponse = httpGetClient.execute(httpGet);
		statusCodeHttpGet = httpGetResponse.getStatusLine()
				.getStatusCode();
	}
	
	@SuppressWarnings("unchecked")
	private boolean checkParameters(T... params) {
	
		return params.length == 1 && params[0] != null
				&& params[0].getId() > 0
			    && appName != null
				&& !appName.isEmpty() && apiKey != null
				&& !apiKey.isEmpty() && propertyName != null
				&& !propertyName.isEmpty();
	}
	
	private «attribute.type.toFirstUpper» fitResponseStringToOriginalDataType(String responseJSONString)
			throws JSONException, ParseException {
			
«IF attribute.type.equalsIgnoreCase("double")»
			«"return "+attribute.type.toFirstUpper+".parse"+attribute.type.toFirstUpper+"(responseJSONString);"»
			«ENDIF»
«IF (attribute.type.equalsIgnoreCase("boolean") || attribute.type.equalsIgnoreCase("long"))»
			«"return "+attribute.type.toFirstUpper+".get"+attribute.type.toFirstUpper+"(responseJSONString);"»
			«ENDIF»
«IF (attribute.type.equalsIgnoreCase("string"))»
			«"return responseJSONString;"»
			«ENDIF»

«IF (attribute.type.equalsIgnoreCase("double[]"))»
					«"Genson genson = new Genson();
			ArrayList<Double> list = genson.deserialize(responseJSONString, 
			new GenericType<ArrayList<Double>>(){});
			Double[] retValue = new Double[]{list.get(0),list.get(1)};
			return retValue;"»
			«ENDIF»

«IF (attribute.type.equalsIgnoreCase("date"))»
			«"JSONObject object = new JSONObject(responseJSONString);
			String value = object.getString(Constants.VALUE);
			return DateHelper.stringToDate(value);"»
			«ENDIF»
	}
	
	@Override
	protected void onPostExecute(«attribute.type.toFirstUpper» returnValue) {
			if (callback != null) {
				callback.onComplete(returnValue, statusCodeHttpGet, errorMessage);
			}
	}
}
'''
}

}