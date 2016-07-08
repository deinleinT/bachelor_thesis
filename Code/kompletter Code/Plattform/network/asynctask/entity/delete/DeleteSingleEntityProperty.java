
package de.fhws.sdk.orca.network.asynctask.entity.delete;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;
import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.helper.NetworkHelper;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.network.callback.INoReturnValueCallback;

/**
 * AsyncTask-Klasse für HTTP-DELETE-REQUEST. Löscht das übergebene
 * EntityProperty. Das Callback gibt den Http-StatusCode und eine ErrorMessage
 * zurück. BACKEND-ENDPUNKT: ...api/{appname}/entities/{entityId}/{propertyName}
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class DeleteSingleEntityProperty<T extends Entity> extends
		AsyncTask<T, Void, Void> {
	
	private String					deleteUrl;
	private String					appName;
	private String					apiKey;
	private INoReturnValueCallback	callback;
	private T						originalEntity;
	private int						statusCodeHttpDelete;
	private String					errorMessage	= Constants.NO_ERROR;
	private String					propertyName;
	private HttpClient				httpDeleteClient;
	private HttpDelete				httpDelete;
	private HttpResponse			httpDeleteResponse;
	
	public DeleteSingleEntityProperty(String propertyName, String appName,
			String apiKey, INoReturnValueCallback callback) {
	
		this.callback = callback;
		this.propertyName = propertyName;
		this.appName = appName;
		this.apiKey = apiKey;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Void doInBackground(T... params) {
	
		try {
			if (checkParams(params)) {
				
				setOriginalEntity(params);
				setDeleteURL();
				
				Log.d(Constants.TAG_ORCASDK,
						Constants.TAG_BACKEND
								+ " DeleteSingleEntityProperty doInBackground DELETE-URL "
								+ deleteUrl);
				
				setHttpDeleteVariablesAndHeadersAndExecuteRequest();
				
				if (NetworkHelper.isStatusCode204(statusCodeHttpDelete)) {
					
					removePropertyFromOriginalEntity();
				}
				else if (NetworkHelper
						.isStatusCode403Or404(statusCodeHttpDelete)) {
					
					errorMessage = EntityUtils.toString(
							httpDeleteResponse.getEntity(),
							Constants.UTF_STRING);
				}
				else {
					errorMessage = "StatusCode is " + statusCodeHttpDelete
							+ ".";
					statusCodeHttpDelete = -1;
				}
				
			}
			else {
				statusCodeHttpDelete = -1;
				errorMessage = "DELETE-Request not possible, doInBackground-Method has more than one Entity attached, no valid EntityId or AppName or ApiKey are invalid!";
			}
		}
		catch (ClientProtocolException e) {
			Log.e(Constants.TAG_ORCASDK,
					"ClientProtocolExeption " + e.getMessage());
			e.printStackTrace();
			errorMessage = "ClientProtocolException " + e.getMessage();
			statusCodeHttpDelete = -1;
		}
		catch (ParseException e) {
			Log.e(Constants.TAG_ORCASDK, "ParseException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "ParseException " + e.getMessage();
			statusCodeHttpDelete = -1;
		}
		catch (IOException e) {
			Log.e(Constants.TAG_ORCASDK, "IOException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "IOException " + e.getMessage();
			statusCodeHttpDelete = -1;
		}
		catch (NullPointerException e) {
			Log.e(Constants.TAG_ORCASDK,
					"NullPointerException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "NullPointerException " + e.getMessage();
			statusCodeHttpDelete = -1;
		}
		
		return null;
	}
	
	private void setDeleteURL() throws UnsupportedEncodingException {
	
		deleteUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES + "/" + originalEntity.getId() + "/"
				+ URLEncoder.encode(propertyName, Constants.UTF_STRING);
	}
	
	private void setHttpDeleteVariablesAndHeadersAndExecuteRequest()
			throws IOException, ClientProtocolException {
	
		httpDeleteClient = new DefaultHttpClient();
		httpDelete = new HttpDelete(deleteUrl);
		httpDelete.addHeader(Constants.APIKEYHEADER, apiKey);
		httpDeleteResponse = httpDeleteClient.execute(httpDelete);
		statusCodeHttpDelete = httpDeleteResponse.getStatusLine()
				.getStatusCode();
	}
	
	private void removePropertyFromOriginalEntity() {
	
		originalEntity.getProperties().remove(propertyName);
	}
	
	@SuppressWarnings("unchecked")
	private void setOriginalEntity(T... params) {
	
		originalEntity = params[0];
	}
	
	@SuppressWarnings("unchecked")
	private boolean checkParams(T... params) {
	
		return params.length == 1 && params[0].getId() > 0 && appName != null
				&& !appName.isEmpty() && apiKey != null && !apiKey.isEmpty()
				&& propertyName != null && !propertyName.isEmpty();
	}
	
	@Override
	protected void onPostExecute(Void result) {
	
		if (callback != null) {
			
			callback.onComplete(statusCodeHttpDelete, errorMessage);
		}
	}
	
}
