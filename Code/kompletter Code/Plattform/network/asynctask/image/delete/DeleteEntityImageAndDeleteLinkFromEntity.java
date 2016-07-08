
package de.fhws.sdk.orca.network.asynctask.image.delete;

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
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.network.callback.INoReturnValueCallback;

/**
 * AsyncTask-Klasse für das Löschen eines Entity-Image. BACKEND-ENDPUNKT:
 * /api/{appname}/entities/{id}/image/{property}
 * 
 * @author ThomasDeinlein
 */
public class DeleteEntityImageAndDeleteLinkFromEntity<T extends Entity> extends
		AsyncTask<Void, Void, Void> {
	
	private String					errorMessage	= Constants.NO_ERROR;
	private String					appName;
	private String					apiKey;
	private T						entity;
	private String					imagePropertyName;
	private int						statusCodeHttpDelete;
	private HttpClient				httpDeleteClient;
	private HttpDelete				httpDelete;
	private HttpResponse			httpDeleteResponse;
	private INoReturnValueCallback	callback;
	private String					deleteUrl;
	
	public DeleteEntityImageAndDeleteLinkFromEntity(T entity,
			String imagePropertyName, String appName, String apiKey,
			INoReturnValueCallback callback) {
	
		this.entity = entity;
		this.imagePropertyName = imagePropertyName;
		this.callback = callback;
		this.appName = appName;
		this.apiKey = apiKey;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
	
		try {
			if (checkParameters()) {
				
				setDeleteURL();
				setHttpDeleteVariablesAndExecuteRequest();
				
				if (NetworkHelper.isStatusCode204(statusCodeHttpDelete)) {
					formatProperties();
				}
				else if (NetworkHelper
						.isStatusCode403Or404(statusCodeHttpDelete)) {
					errorMessage = EntityUtils.toString(
							httpDeleteResponse.getEntity(),
							Constants.UTF_STRING);
				}
				else if (NetworkHelper.isStatusCode500(statusCodeHttpDelete)) {
					errorMessage = EntityUtils.toString(
							httpDeleteResponse.getEntity(),
							Constants.UTF_STRING);
				}
				else {
					statusCodeHttpDelete = -1;
					errorMessage = "StatusCode is " + statusCodeHttpDelete
							+ ".";
				}
			}
			else {
				statusCodeHttpDelete = -1;
				errorMessage = "DELETE-Request not possible: ivalid entityId, linkType, appName or apiKey (id==0, null or empty)";
			}
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
		catch (Exception e) {
			Log.e(Constants.TAG_ORCASDK, "Exception " + e.getMessage());
			e.printStackTrace();
			errorMessage = "Exception " + e.getMessage();
			statusCodeHttpDelete = -1;
		}
		
		return null;
	}
	
	private void formatProperties() {
	
		entity.getProperties().remove(imagePropertyName);
	}
	
	private void setHttpDeleteVariablesAndExecuteRequest() throws IOException,
			ClientProtocolException {
	
		httpDeleteClient = new DefaultHttpClient();
		httpDelete = new HttpDelete(deleteUrl);
		httpDelete.addHeader(Constants.APIKEYHEADER, apiKey);
		httpDeleteResponse = httpDeleteClient.execute(httpDelete);
		statusCodeHttpDelete = httpDeleteResponse.getStatusLine()
				.getStatusCode();
	}
	
	private void setDeleteURL() throws UnsupportedEncodingException {
	
		deleteUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES + "/" + entity.getId() + "/"
				+ Constants.IMAGE + "/"
				+ URLEncoder.encode(imagePropertyName, Constants.UTF_STRING);
	}
	
	private boolean checkParameters() {
	
		return entity.getId() > 0 && imagePropertyName != null
				&& !imagePropertyName.isEmpty() && appName != null
				&& apiKey != null && !appName.isEmpty() && !apiKey.isEmpty();
	}
	
	@Override
	protected void onPostExecute(Void result) {
	
		if (callback != null) {
			
			callback.onComplete(statusCodeHttpDelete, errorMessage);
		}
	}
	
}
