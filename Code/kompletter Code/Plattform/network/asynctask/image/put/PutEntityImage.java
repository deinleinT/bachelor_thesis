
package de.fhws.sdk.orca.network.asynctask.image.put;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;
import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.helper.NetworkHelper;
import de.fhws.sdk.orca.network.callback.INoReturnValueCallback;

/**
 * AsyncTaskKlasse: Wird zum Speichern und Updaten von Entity-Images verwendet
 * BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/image/{property}
 * 
 * @author ThomasDeinlein
 */
public class PutEntityImage extends AsyncTask<Void, Void, Void> {
	
	private String					errorMessage	= Constants.NO_ERROR;
	private String					appName;
	private String					apiKey;
	private int						entityId;
	private String					imagePropertyName;
	private Image					image;
	private int						statusCodeHttpPut;
	private HttpClient				httpPutClient;
	private HttpPut					httpPut;
	private HttpResponse			httpPutResponse;
	private INoReturnValueCallback	callback;
	private String					putURL;
	
	public PutEntityImage(int entityId, String imagePropertyName, Image image,
			String appName, String apiKey, INoReturnValueCallback callback) {
	
		this.entityId = entityId;
		this.imagePropertyName = imagePropertyName;
		this.callback = callback;
		this.appName = appName;
		this.apiKey = apiKey;
		this.image = image;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
	
		try {
			if (checkParameters()) {
				
				setPutURL();
				setHttpPutVariablesAndHeadersAndExecuteRequest();
				
				if (NetworkHelper
						.isStatusCodeBetween200And300(statusCodeHttpPut)) {
					
				}
				else if (NetworkHelper.isStatusCode403Or404(statusCodeHttpPut)) {
					errorMessage = EntityUtils.toString(
							httpPutResponse.getEntity(), Constants.UTF_STRING);
				}
				else if (NetworkHelper.isStatusCode500(statusCodeHttpPut)) {
					errorMessage = EntityUtils.toString(
							httpPutResponse.getEntity(), Constants.UTF_STRING);
				}
				else {
					errorMessage = "StatusCode is " + statusCodeHttpPut;
					statusCodeHttpPut = -1;
				}
			}
			else {
				statusCodeHttpPut = -1;
				errorMessage = "PUT-Request not possible: parameters are null, empty or 0.";
			}
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
	
	private void setHttpPutVariablesAndHeadersAndExecuteRequest()
			throws IOException, ClientProtocolException {
	
		httpPutClient = new DefaultHttpClient();
		httpPut = new HttpPut(putURL);
		httpPut.addHeader(Constants.APIKEYHEADER, apiKey);
		httpPut.setHeader(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_IMAGE);
		httpPut.setEntity(image.getInputStreamEntity());
		httpPutResponse = httpPutClient.execute(httpPut);
		statusCodeHttpPut = httpPutResponse.getStatusLine().getStatusCode();
	}
	
	private void setPutURL() throws UnsupportedEncodingException {
	
		putURL = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES + "/" + entityId + "/" + Constants.IMAGE
				+ "/"
				+ URLEncoder.encode(imagePropertyName, Constants.UTF_STRING);
	}
	
	private boolean checkParameters() {
	
		return entityId > 0 && appName != null && !appName.isEmpty()
				&& apiKey != null && !apiKey.isEmpty()
				&& imagePropertyName != null && !imagePropertyName.isEmpty()
				&& image != null && image.getContext() != null
				&& image.getInputStreamEntity() != null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
	
		if (callback != null) {
			
			callback.onComplete(statusCodeHttpPut, errorMessage);
		}
	}
}
