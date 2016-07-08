
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.helper.DateHelper;
import de.fhws.sdk.orca.helper.NetworkHelper;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.network.callback.IPropertyCallback;

/**
 * AsyncTask-Klasse: Dieser Endpunkt wird verwendet, um eine einzelne Property
 * einer Entität anzufordern. BACKEND-ENDPUNKT:
 * api/{appname}/entities/{id}/{property}
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class GetSingleEntityProperty<T extends Entity> extends
		AsyncTask<T, Void, Object> {
	
	private String				getUrl;
	private String				appName;
	private String				apiKey;
	private IPropertyCallback	callback;
	private T					originalEntity;
	private int					statusCodeHttpGet;
	private String				errorMessage	= Constants.NO_ERROR;
	private String				propertyName;
	private HttpClient			httpGetClient;
	private HttpGet				httpGet;
	private HttpResponse		httpGetResponse;
	private Object				returnValue;
	
	public GetSingleEntityProperty(String propertyName, String appName,
			String apiKey, IPropertyCallback callback) {
	
		this.callback = callback;
		this.propertyName = propertyName;
		this.apiKey = apiKey;
		this.appName = appName;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Object doInBackground(T... params) {
	
		try {
			if (checkParams(params)) {
				
				setOriginalEntity(params);
				setGetURL();
				
				Log.d(Constants.TAG_ORCASDK, Constants.TAG_BACKEND
						+ " GetSingleEntityProperty doInBackground getURL "
						+ getUrl);
				setHttpGetVariablesAndHeadersAndExecuteRequest();
				
				if (NetworkHelper.isStatusCode200(statusCodeHttpGet)) {
					String responseJSONString = setResponseString();
					setReturnValue(responseJSONString);
				}
				else if (NetworkHelper.isStatusCode403Or404(statusCodeHttpGet)) {
					
					errorMessage = EntityUtils.toString(
							httpGetResponse.getEntity(), "UTF-8");
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
		
		return returnValue;
	}
	
	private void setGetURL() throws UnsupportedEncodingException {
	
		getUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES + "/" + originalEntity.getId() + "/"
				+ URLEncoder.encode(propertyName, Constants.UTF_STRING);
	}
	
	private String setResponseString() throws IOException {
	
		String responseJSONString = EntityUtils.toString(
				httpGetResponse.getEntity(), "UTF-8");
		return responseJSONString;
	}
	
	private void setReturnValue(String responseJSONString)
			throws JSONException, ParseException {
	
		returnValue = fitResponseStringToOriginalDataType(responseJSONString);
	}
	
	private void setHttpGetVariablesAndHeadersAndExecuteRequest()
			throws IOException, ClientProtocolException {
	
		httpGetClient = new DefaultHttpClient();
		httpGet = new HttpGet(getUrl);
		httpGet.addHeader(Constants.APIKEYHEADER, apiKey);
		httpGetResponse = httpGetClient.execute(httpGet);
		statusCodeHttpGet = httpGetResponse.getStatusLine().getStatusCode();
	}
	
	@SuppressWarnings("unchecked")
	private void setOriginalEntity(T... params) {
	
		originalEntity = params[0];
	}
	
	@SuppressWarnings("unchecked")
	private boolean checkParams(T... params) {
	
		return params.length == 1 && params[0] != null && params[0].getId() > 0
				&& params[0].getProperties() != null
				&& params[0].getProperties().size() > 0 && appName != null
				&& !appName.isEmpty() && apiKey != null && !apiKey.isEmpty()
				&& propertyName != null && !propertyName.isEmpty();
	}
	
	private Object fitResponseStringToOriginalDataType(String responseJSONString)
			throws JSONException, ParseException {
	
		if (NetworkHelper.isArrayType(originalEntity.getProperties()
				.get(propertyName).getClass())
				|| originalEntity.getProperties().get(propertyName).getClass()
						.getSimpleName().equalsIgnoreCase("ArrayList")
				|| originalEntity.getProperties().get(propertyName).getClass()
						.getSimpleName().equalsIgnoreCase("Double[]")) {
			JSONArray temp = new JSONArray(responseJSONString);
			Double[] tempArray = new Double[temp.length()];
			for (int i = 0; i < tempArray.length; i++) {
				tempArray[i] = temp.getDouble(i);
			}
			return tempArray;
		}
		else if (originalEntity.getProperties().get(propertyName).getClass()
				.getSimpleName().equalsIgnoreCase("Long")) {
			return Long.parseLong(responseJSONString);
		}
		else if (originalEntity.getProperties().get(propertyName).getClass()
				.getSimpleName().equalsIgnoreCase("Double")) {
			return Double.parseDouble(responseJSONString);
		}
		else if (originalEntity.getProperties().get(propertyName).getClass()
				.getSimpleName().equalsIgnoreCase("Boolean")) {
			return Boolean.getBoolean(responseJSONString);
		}
		else if (originalEntity.getProperties().get(propertyName).getClass()
				.getSimpleName().equalsIgnoreCase("Date")) {
			JSONObject json = new JSONObject(responseJSONString);
			return DateHelper.stringToDate(json.getString(Constants.VALUE));
		}
		else if (originalEntity.getProperties().get(propertyName).getClass()
				.getSimpleName().equalsIgnoreCase("String")) {
			return responseJSONString;
		}
		
		else {
			return null;
		}
		
	}
	
	@Override
	protected void onPostExecute(Object returnValue) {
	
		if (returnValue != null) {
			if (callback != null) {
				callback.onComplete(returnValue, statusCodeHttpGet,
						errorMessage);
			}
		}
		else {
			if (callback != null) {
				callback.onComplete(null, statusCodeHttpGet, errorMessage);
			}
		}
	}
}
