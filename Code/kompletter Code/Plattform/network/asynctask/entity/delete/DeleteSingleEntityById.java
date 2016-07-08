
package de.fhws.sdk.orca.network.asynctask.entity.delete;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;
import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.helper.NetworkHelper;
import de.fhws.sdk.orca.network.callback.INoReturnValueCallback;

/**
 * AsyncTask-Klasse für HTTP-DELETE-Request. Löscht ein Entity anhand der
 * übergebenen ID. Das übergebene Callback gibt den Http-Response-StatusCode und
 * eine ErrorMessage zurück. BACKEND-ENDPUNKT:
 * ...api/{appname}/entities/{entityId}
 * 
 * @author ThomasDeinlein
 */
public class DeleteSingleEntityById extends AsyncTask<Void, Void, Void> {
	
	private String					deleteUrl;
	private String					appName;
	private String					apiKey;
	private INoReturnValueCallback	callback;
	private int						entityId;
	private int						statusCodeHttpDelete;
	private String					errorMessage	= Constants.NO_ERROR;
	private HttpClient				httpDeleteClient;
	private HttpDelete				httpDelete;
	private HttpResponse			httpDeleteResponse;
	
	public DeleteSingleEntityById(int entityId, String appName, String apiKey,
			INoReturnValueCallback callback) {
	
		this.appName = appName;
		this.apiKey = apiKey;
		this.entityId = entityId;
		this.callback = callback;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
	
		try {
			if (checkParamaters()) {
				
				setDeleteURL();
				Log.d(Constants.TAG_ORCASDK, Constants.TAG_BACKEND
						+ " DeleteSingleEntityById doInBackground DELETE-URL "
						+ deleteUrl);
				
				setHttpVariablesAndHeadersAndExecuteRequest();
				if (NetworkHelper
						.isStatusCodeBetween200And300(statusCodeHttpDelete)) {
					
				}
				if (NetworkHelper.isStatusCode403Or404(statusCodeHttpDelete)) {
					errorMessage = EntityUtils.toString(
							httpDeleteResponse.getEntity(),
							Constants.UTF_STRING);
				}
			}
			else {
				statusCodeHttpDelete = -1;
				errorMessage = Constants.ERROR_DELETE_ENTITY_BY_ID;
				
			}
		}
		
		catch (ClientProtocolException e) {
			Log.e(Constants.TAG_ORCASDK,
					"ClientProtocolExeption " + e.getMessage());
			e.printStackTrace();
			errorMessage = "ClientProtocolException: " + e.getMessage();
			statusCodeHttpDelete = -1;
		}
		catch (IOException e) {
			Log.e(Constants.TAG_ORCASDK, "IOExeption " + e.getMessage());
			e.printStackTrace();
			errorMessage = "IOException: " + e.getMessage();
			statusCodeHttpDelete = -1;
		}
		catch (NullPointerException e) {
			Log.e(Constants.TAG_ORCASDK,
					"NullPointerExeption " + e.getMessage());
			e.printStackTrace();
			errorMessage = "NullPointerException: " + e.getMessage();
			statusCodeHttpDelete = -1;
		}
		
		return null;
	}
	
	private void setDeleteURL() throws UnsupportedEncodingException {
	
		deleteUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES + "/" + entityId;
	}
	
	private boolean checkParamaters() {
	
		return entityId > 0 && appName != null && !appName.isEmpty()
				&& apiKey != null && !appName.isEmpty() && !apiKey.isEmpty();
	}
	
	private void setHttpVariablesAndHeadersAndExecuteRequest()
			throws IOException, ClientProtocolException {
	
		httpDeleteClient = new DefaultHttpClient();
		httpDelete = new HttpDelete(deleteUrl);
		httpDelete.addHeader(Constants.APIKEYHEADER, apiKey);
		httpDeleteResponse = httpDeleteClient.execute(httpDelete);
		statusCodeHttpDelete = httpDeleteResponse.getStatusLine()
				.getStatusCode();
	}
	
	@Override
	protected void onPostExecute(Void result) {
	
		if (callback != null) {
			
			callback.onComplete(statusCodeHttpDelete, errorMessage);
		}
	}
	
}
