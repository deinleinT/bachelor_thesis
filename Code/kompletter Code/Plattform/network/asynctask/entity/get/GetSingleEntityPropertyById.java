
package de.fhws.sdk.orca.network.asynctask.entity.get;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;

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

import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;

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
public class GetSingleEntityPropertyById<T extends Entity> extends
		AsyncTask<Void, Void, Object> {
	
	private String				getUrl;
	private String				appName;
	private String				apiKey;
	private IPropertyCallback	callback;
	private int					entityId;
	private int					statusCodeHttpGet;
	private String				errorMessage	= Constants.NO_ERROR;
	private String				propertyName;
	private HttpClient			httpGetClient;
	private HttpGet				httpGet;
	private HttpResponse		httpGetResponse;
	private Object				returnValue;
	
	public GetSingleEntityPropertyById(int entityId, String propertyName,
			String appName, String apiKey, IPropertyCallback callback) {
	
		this.callback = callback;
		this.propertyName = propertyName;
		this.apiKey = apiKey;
		this.appName = appName;
		this.entityId = entityId;
	}
	
	@Override
	protected Object doInBackground(Void... params) {
	
		try {
			if (checkParams()) {
				
				setGetURL();
				setHttpGetVariablesAndHeadersAndExecuteRequest();
				
				if (NetworkHelper.isStatusCode200(statusCodeHttpGet)) {
					String responseJSONString = setResponseString();
					setReturnValue(responseJSONString);
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
				+ Constants.ENTITIES + "/" + entityId + "/"
				+ URLEncoder.encode(propertyName, Constants.UTF_STRING);
	}
	
	private void setReturnValue(String responseJSONString)
			throws JSONException, ParseException {
	
		returnValue = fitResponsStringToOriginalDataType(responseJSONString);
	}
	
	private String setResponseString() throws IOException {
	
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
	
		return appName != null && !appName.isEmpty() && apiKey != null
				&& !apiKey.isEmpty() && propertyName != null
				&& !propertyName.isEmpty() && entityId > 0;
	}
	
	private Object fitResponsStringToOriginalDataType(String responseJSONString)
			throws JSONException, ParseException {
	
		if (responseJSONString.contains("[")
				&& responseJSONString.contains("]")) {
			Genson genson = new Genson();
			ArrayList<Double> list = genson.deserialize(responseJSONString,
					new GenericType<ArrayList<Double>>() {
					});
			Double[] retValue = new Double[]{list.get(0), list.get(1)};
			return retValue;
		}
		if (responseJSONString.contains(Constants.VALUE)
				&& responseJSONString.contains(Constants.TYPE)
				&& responseJSONString.contains(Constants.DATE)) {
			JSONObject object = new JSONObject(responseJSONString);
			String value = object.getString(Constants.VALUE);
			return DateHelper.stringToDate(value);
		}
		
		return responseJSONString;
		
	}
	
	@Override
	protected void onPostExecute(Object result) {
	
		if (result != null) {
			if (callback != null) {
				callback.onComplete(result, statusCodeHttpGet, errorMessage);
			}
		}
		else {
			if (callback != null) {
				errorMessage = "no result";
				callback.onComplete(null, statusCodeHttpGet, errorMessage);
			}
		}
	}
	
}
