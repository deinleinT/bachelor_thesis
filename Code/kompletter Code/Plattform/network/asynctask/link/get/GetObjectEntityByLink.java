
package de.fhws.sdk.orca.network.asynctask.link.get;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import android.os.AsyncTask;
import android.util.Log;

import com.owlike.genson.Genson;

import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.helper.JSONHelper;
import de.fhws.sdk.orca.helper.NetworkHelper;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.model.Link;
import de.fhws.sdk.orca.network.callback.IReturnValueCallback;

/**
 * AsyncTask-Klasse: Dieser Endpunkt ermöglicht es die Objekt-Entität
 * abzufragen, auf die der übergebene Link zeigt. BACKEND-ENDPUNKT:
 * /api/{appname}/entities/{id}/links/{type}/{objectId}
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class GetObjectEntityByLink<T extends Entity> extends
		AsyncTask<Void, Void, T> {
	
	private String					errorMessage	= Constants.NO_ERROR;
	private String					appName;
	private String					apiKey;
	private IReturnValueCallback<T>	callback;
	private int						entityId;
	private int						objectId;
	private String					linkType;
	private int						statusCodeHttpGet;
	private HttpClient				httpGetClient;
	private HttpGet					httpGet;
	private HttpResponse			httpGetResponse;
	private String					getUrl;
	private String					responseString;
	private Class<? extends Entity>	classTypeOfObject;
	private T						retValue;
	
	public GetObjectEntityByLink(int entityId, int objectId, String linkType,
			Class<? extends Entity> classTypeOfObject, String appName,
			String apiKey, IReturnValueCallback<T> callback) {
	
		this.entityId = entityId;
		this.objectId = objectId;
		this.linkType = linkType;
		this.classTypeOfObject = classTypeOfObject;
		this.callback = callback;
		this.apiKey = apiKey;
		this.appName = appName;
	}
	
	@Override
	protected T doInBackground(Void... params) {
	
		try {
			if (checkParameters()) {
				
				setGetURL();
				setHttpGetVariablesAndExecuteRequest();
				
				if (NetworkHelper.isStatusCode200(statusCodeHttpGet)) {
					
					setResponseStringAndRetValue();
				}
				else if (NetworkHelper.isStatusCode403Or404(statusCodeHttpGet)) {
					errorMessage = EntityUtils.toString(
							httpGetResponse.getEntity(), Constants.UTF_STRING);
				}
				else {
					errorMessage = "StatusCode is " + statusCodeHttpGet + ".";
					statusCodeHttpGet = -1;
				}
			}
			else {
				statusCodeHttpGet = -1;
				errorMessage = "GET-Request not possible: invalid parameter(s) (0, null or empty)";
			}
		}
		
		catch (JSONException e) {
			Log.e(Constants.TAG_ORCASDK, "JSONException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "JSONException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (UnsupportedEncodingException e) {
			Log.e(Constants.TAG_ORCASDK,
					"UnsupportedEncodingException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "UnsupportedEncodingException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (ParseException e) {
			Log.e(Constants.TAG_ORCASDK, "ParseException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "ParseException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (IOException e) {
			Log.e(Constants.TAG_ORCASDK, "IOException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "IOException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (NullPointerException e) {
			Log.e(Constants.TAG_ORCASDK,
					"NullPointerException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "NullPointerException " + e.getMessage();
			statusCodeHttpGet = -1;
			
		}
		catch (Exception e) {
			Log.e(Constants.TAG_ORCASDK, "Exception " + e.getMessage());
			e.printStackTrace();
			errorMessage = "Exception " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		
		return retValue;
	}
	
	private void setResponseStringAndRetValue() throws IOException,
			JSONException, java.text.ParseException {
	
		responseString = EntityUtils.toString(httpGetResponse.getEntity(),
				Constants.UTF_STRING);
		retValue = setNewEntity(responseString);
	}
	
	private void setHttpGetVariablesAndExecuteRequest() throws IOException,
			ClientProtocolException {
	
		httpGetClient = new DefaultHttpClient();
		httpGet = new HttpGet(getUrl);
		httpGet.addHeader(Constants.APIKEYHEADER, apiKey);
		httpGet.setHeader(Constants.CONTENT_TYPE,
				Constants.CONTENT_TYPE_VALUE_JSON);
		httpGetResponse = httpGetClient.execute(httpGet);
		statusCodeHttpGet = httpGetResponse.getStatusLine().getStatusCode();
	}
	
	private boolean checkParameters() {
	
		return entityId > 0 && objectId > 0 && linkType != null
				&& !linkType.isEmpty() && appName != null && !appName.isEmpty()
				&& apiKey != null && !apiKey.isEmpty()
				&& classTypeOfObject != null;
	}
	
	private void setGetURL() throws UnsupportedEncodingException {
	
		getUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES + "/" + entityId + "/" + Constants.LINKS
				+ "/" + URLEncoder.encode(linkType, Constants.UTF_STRING) + "/"
				+ objectId;
	}
	
	@SuppressWarnings("unchecked")
	private T setNewEntity(String responseJSONString) throws JSONException,
			java.text.ParseException {
	
		Genson gen = new Genson();
		T retValue = (T) gen.deserialize(responseJSONString, classTypeOfObject);
		
		JSONHelper.setPropertiesToCorrectDatatypesFromResponseEntity(retValue
				.getProperties());
		for (Link link : retValue.getLinks()) {
			
			JSONHelper.setPropertiesToCorrectDatatypesFromResponseEntity(link
					.getProperties());
		}
		
		return retValue;
	}
	
	@Override
	protected void onPostExecute(T result) {
	
		if (callback != null) {
			
			callback.onComplete(result, statusCodeHttpGet, errorMessage);
		}
	}
	
}
