
package de.fhws.sdk.orca.network.asynctask.entity.get;

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
import org.json.JSONObject;

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
 * AsyncTask-Klasse: Dieser Endpunkt ermöglicht es, bereits gespeicherte
 * Entities vom Backend anhand der EntityId anzufordern. Das Callback gibt das
 * gefundene Entity zurück. BACKEND-ENDPUNKT: /api/{appname}/entities/{id}
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class GetSingleEntityById<T extends Entity> extends
		AsyncTask<Void, Void, T> {
	
	private String					getUrl;
	private String					errorMessage	= Constants.NO_ERROR;
	private String					appName;
	private String					apiKey;
	private IReturnValueCallback<T>	callback;
	private int						entityId;
	private long					entityType;
	private int						statusCodeHttpGet;
	private HttpClient				httpGetClient;
	private HttpGet					httpGet;
	private HttpResponse			httpGetResponse;
	private T						retValue;
	private Class<? extends Entity>	classType;
	
	public GetSingleEntityById(int entityId, long entityType,
			Class<? extends Entity> classType, String appName, String apiKey,
			IReturnValueCallback<T> callback) {
	
		this.entityId = entityId;
		this.entityType = entityType;
		this.classType = classType;
		this.callback = callback;
		this.appName = appName;
		this.apiKey = apiKey;
		
	}
	
	@Override
	protected T doInBackground(Void... params) {
	
		try {
			if (checkParams()) {
				
				setGetURL();
				Log.d(Constants.TAG_ORCASDK, Constants.TAG_BACKEND
						+ " GetSingleEntityById doInBackground getURL "
						+ getUrl);
				setHttpGetVariablesAndHeadersAndExecuteRequest();
				
				if (NetworkHelper.isStatusCode200(statusCodeHttpGet)) {
					String responseJSONString = setResponseJSONString();
					
					if (isEntityTypeCorrect(responseJSONString)) {
						retValue = setNewEntity(responseJSONString);
					}
					else {
						statusCodeHttpGet = -1;
						errorMessage = "invalid datatypes, loaded entity is not from correct type";
						return null;
					}
					
				}
				else if (NetworkHelper.isStatusCode403Or404(statusCodeHttpGet)) {
					errorMessage = EntityUtils.toString(
							httpGetResponse.getEntity(), Constants.UTF_STRING);
				}
				else {
					errorMessage = "StatusCode Get-Response is "
							+ statusCodeHttpGet + ".";
					statusCodeHttpGet = -1;
				}
				
			}
			else {
				statusCodeHttpGet = -1;
				errorMessage = "GET-Request not possible: invalid id, type, classType, appName or apiKey (0, null, empty)!";
			}
		}
		catch (ClientProtocolException e) {
			Log.e(Constants.TAG_ORCASDK,
					"ClientProtocolException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "ClientProtocolException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (IOException e) {
			Log.e(Constants.TAG_ORCASDK, "IOException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "IOException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (JSONException e) {
			Log.e(Constants.TAG_ORCASDK, "ParseException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "JSONException " + e.getMessage();
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
	
	private void setGetURL() throws UnsupportedEncodingException {
	
		getUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES + "/" + entityId;
	}
	
	private String setResponseJSONString() throws IOException {
	
		String responseJSONString = EntityUtils.toString(
				httpGetResponse.getEntity(), Constants.UTF_STRING);
		return responseJSONString;
	}
	
	private void setHttpGetVariablesAndHeadersAndExecuteRequest()
			throws IOException, ClientProtocolException {
	
		httpGetClient = new DefaultHttpClient();
		httpGet = new HttpGet(getUrl);
		httpGet.addHeader(Constants.APIKEYHEADER, apiKey);
		httpGetResponse = httpGetClient.execute(httpGet);
		statusCodeHttpGet = httpGetResponse.getStatusLine().getStatusCode();
	}
	
	private boolean checkParams() {
	
		return entityId > 0 && entityType > 0 && appName != null
				&& !appName.isEmpty() && apiKey != null && !apiKey.isEmpty()
				&& classType != null;
	}
	
	private boolean isEntityTypeCorrect(String responseJSONString)
			throws JSONException {
	
		JSONObject temp = new JSONObject(responseJSONString);
		long type = temp.getLong(Constants.TYPE);
		
		return type != entityType ? false : true;
	}
	
	@SuppressWarnings("unchecked")
	private T setNewEntity(String responseJSONString) throws JSONException,
			ParseException {
	
		Genson gen = new Genson();
		T retValue = (T) gen.deserialize(responseJSONString, classType);
		
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
