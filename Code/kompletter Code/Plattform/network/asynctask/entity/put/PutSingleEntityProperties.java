
package de.fhws.sdk.orca.network.asynctask.entity.put;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import android.os.AsyncTask;
import android.util.Log;

import com.owlike.genson.Genson;

import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.helper.JSONHelper;
import de.fhws.sdk.orca.helper.NetworkHelper;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.network.callback.INoReturnValueCallback;

/**
 * AsyncTask-Klasse: Dient zum Updaten einzelner EntityProperties im Backend
 * BACKEND-ENDPUNKT: api/{appname}/entities/{id}/property
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class PutSingleEntityProperties<T extends Entity> extends
		AsyncTask<T, Void, Void> {
	
	private String					putUrl;
	private String					appName;
	private String					apiKey;
	private INoReturnValueCallback	callback;
	private T						originalEntity;
	private int						statusCodeHttpPut;
	private String					errorMessage	= Constants.NO_ERROR;
	private HttpClient				httpPutClient;
	private HttpPut					httpPut;
	private HttpResponse			httpPutResponse;
	private HashMap<String, Object>	newProperties;
	
	public PutSingleEntityProperties(HashMap<String, Object> newProperties,
			String appName, String apiKey, INoReturnValueCallback callback) {
	
		this.callback = callback;
		this.newProperties = newProperties;
		this.appName = appName;
		this.apiKey = apiKey;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Void doInBackground(T... params) {
	
		try {
			if (checkParameters(params)) {
				setOriginalEntity(params);
				setPropertiesToCorrectFormat();
				setPutURL();
				setHttpPutVariablesAndHeadersAndExecuteRequest();
				
				if (NetworkHelper.isStatusCode204(statusCodeHttpPut)) {
					formatProperties();
				}
				else if (NetworkHelper.isStatusCode403Or404(statusCodeHttpPut)) {
					errorMessage = EntityUtils.toString(
							httpPutResponse.getEntity(), Constants.UTF_STRING);
				}
				else {
					statusCodeHttpPut = -1;
					errorMessage = "StatusCode isn't 200, 403 or 404.";
				}
				
			}
			else {
				statusCodeHttpPut = -1;
				errorMessage = "PUT-Request not possible: invalid entity, newProperties, appName or apiKey (id==0, null or empty)";
			}
		}
		catch (UnsupportedEncodingException e) {
			Log.e(Constants.TAG_ORCASDK,
					"UnsupportedEncodingException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "UnsupportedEncodingException " + e.getMessage();
			statusCodeHttpPut = -1;
		}
		catch (ParseException e) {
			Log.e(Constants.TAG_ORCASDK, "ParseException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "ParseException " + e.getMessage();
			statusCodeHttpPut = -1;
		}
		catch (JSONException e) {
			Log.e(Constants.TAG_ORCASDK, "JSONException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "JSONException " + e.getMessage();
			statusCodeHttpPut = -1;
		}
		catch (ClientProtocolException e) {
			Log.e(Constants.TAG_ORCASDK,
					"ClientProtocolException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "ClientProtocolException " + e.getMessage();
			statusCodeHttpPut = -1;
		}
		catch (IOException e) {
			Log.e(Constants.TAG_ORCASDK, "IOException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "IOException " + e.getMessage();
			statusCodeHttpPut = -1;
		}
		catch (NullPointerException e) {
			Log.e(Constants.TAG_ORCASDK,
					"NullPointerException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "NullPointerException " + e.getMessage();
			statusCodeHttpPut = -1;
		}
		catch (Exception e) {
			Log.e(Constants.TAG_ORCASDK, "Exception " + e.getMessage());
			e.printStackTrace();
			errorMessage = "Exception " + e.getMessage();
			statusCodeHttpPut = -1;
		}
		
		return null;
	}
	
	private void formatProperties() throws ParseException {
	
		for (String key : newProperties.keySet()) {
			
			originalEntity.getProperties().put(key, newProperties.get(key));
		}
		
		JSONHelper
				.setPropertiesToCorrectDatatypesFromResponseEntity(originalEntity
						.getProperties());
	}
	
	private void setHttpPutVariablesAndHeadersAndExecuteRequest()
			throws Exception, IOException, ClientProtocolException {
	
		httpPutClient = new DefaultHttpClient();
		httpPut = new HttpPut(putUrl);
		httpPut.addHeader(Constants.APIKEYHEADER, apiKey);
		httpPut.setHeader(Constants.CONTENT_TYPE,
				Constants.CONTENT_TYPE_VALUE_JSON);
		createJSONObjectFromNewPropsAndSetHttpPutEntity();
		httpPutResponse = httpPutClient.execute(httpPut);
		statusCodeHttpPut = httpPutResponse.getStatusLine().getStatusCode();
	}
	
	private void setPutURL() throws UnsupportedEncodingException {
	
		putUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES + "/" + originalEntity.getId() + "/"
				+ Constants.PROPERTY;
	}
	
	private void setPropertiesToCorrectFormat() throws Exception {
	
		JSONHelper.setPropsToCorrectFormatBeforeSendToBackend(originalEntity
				.getProperties());
		JSONHelper.setPropsToCorrectFormatBeforeSendToBackend(newProperties);
	}
	
	@SuppressWarnings("unchecked")
	private void setOriginalEntity(T... params) {
	
		originalEntity = params[0];
	}
	
	@SuppressWarnings("unchecked")
	private boolean checkParameters(T... params) {
	
		return params.length == 1 && params[0].getId() > 0 && appName != null
				&& !appName.isEmpty() && apiKey != null && !apiKey.isEmpty()
				&& newProperties != null && newProperties.size() > 0;
	}
	
	private void createJSONObjectFromNewPropsAndSetHttpPutEntity()
			throws Exception {
	
		JSONHelper.setPropsToCorrectFormatBeforeSendToBackend(newProperties);
		Genson genson = new Genson();
		String string = genson.serialize(newProperties);
		httpPut.setEntity(new StringEntity(string, HTTP.UTF_8));
	}
	
	@Override
	protected void onPostExecute(Void result) {
	
		if (callback != null) {
			
			callback.onComplete(statusCodeHttpPut, errorMessage);
		}
	}
}
