
package de.fhws.sdk.orca.network.asynctask.entity.get;

import java.io.IOException;
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

import com.owlike.genson.Genson;

import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.helper.JSONHelper;
import de.fhws.sdk.orca.helper.NetworkHelper;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.model.Link;
import de.fhws.sdk.orca.network.callback.IReturnValueCallback;

/**
 * AsyncTask-Klasse. Mit Hilfe dieser kann eine einzelne Entity vom Backend per
 * Link abgefragt werden. Das Callback liefert die gefundene Entity zurück.
 * BACKEND-ENDPUNKT: der zu übergebende Link
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class GetSingleEntityByLink<T extends Entity> extends
		AsyncTask<Void, Void, T> {
	
	private String					getUrl;
	private String					errorMessage	= Constants.NO_ERROR;
	private String					apiKey;
	private IReturnValueCallback<T>	callback;
	private int						statusCodeHttpGet;
	private HttpClient				httpGetClient;
	private HttpGet					httpGet;
	private HttpResponse			httpGetResponse;
	private T						retValue;
	private Class<? extends Entity>	classType;
	private String					link;
	
	public GetSingleEntityByLink(String link,
			Class<? extends Entity> classType, String apiKey,
			IReturnValueCallback<T> callback) {
	
		this.classType = classType;
		this.callback = callback;
		this.link = link;
		this.apiKey = apiKey;
		
	}
	
	@Override
	protected T doInBackground(Void... params) {
	
		try {
			if (checkParams()) {
				
				setGetURL();
				setHttpGetVariablesAndHeadersAndExecuteRequest();
				
				if (NetworkHelper.isStatusCode200(statusCodeHttpGet)) {
					String responseJSONString = setResponseJSONString();
					setRetValue(responseJSONString);
				}
				else if (NetworkHelper.isStatusCode403Or404(statusCodeHttpGet)) {
					
					errorMessage = EntityUtils.toString(
							httpGetResponse.getEntity(), "UTF-8");
				}
				else {
					statusCodeHttpGet = -1;
					errorMessage = "StatusCode isn't 200, 403 or 404.";
				}
				
			}
			else {
				statusCodeHttpGet = -1;
				errorMessage = "GET-Request not possible: invalid link, appName or apiKey (null or empty)";
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
	
	private void setRetValue(String responseJSONString) throws JSONException,
			ParseException {
	
		retValue = setNewEntity(responseJSONString);
	}
	
	private String setResponseJSONString() throws IOException {
	
		String responseJSONString = EntityUtils.toString(
				httpGetResponse.getEntity(), "UTF-8");
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
	
	private void setGetURL() {
	
		getUrl = link;
	}
	
	private boolean checkParams() {
	
		return link != null && !link.isEmpty() && apiKey != null
				&& !apiKey.isEmpty();
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
