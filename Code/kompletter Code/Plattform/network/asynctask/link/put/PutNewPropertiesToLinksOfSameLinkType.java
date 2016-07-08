
package de.fhws.sdk.orca.network.asynctask.link.put;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
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
import de.fhws.sdk.orca.network.callback.INoReturnValueCallback;

/**
 * AsyncTask-Klasse: Dieser Endpunkt wird genutzt, um möglichst effizient alle
 * Links eines bestimmten Typs zu aktualisieren. BACKEND-ENDPUNKT:
 * api/{appname}/entities/{id}/links/{type}
 * 
 * @author ThomasDeinlein
 */
public class PutNewPropertiesToLinksOfSameLinkType<T extends Entity> extends
		AsyncTask<Void, Void, Void> {
	
	private String					errorMessage	= Constants.NO_ERROR;
	private String					appName;
	private String					apiKey;
	private T						entity;
	private int						entityId;
	private String					linkType;
	private int						statusCodeHttpPut;
	private HttpClient				httpPutClient;
	private HttpPut					httpPut;
	private HttpResponse			httpPutResponse;
	private INoReturnValueCallback	callback;
	private HashMap<String, Object>	newProperties;
	private String					putUri;
	
	public PutNewPropertiesToLinksOfSameLinkType(int entityId, String linkType,
			T entity, HashMap<String, Object> newLinkProperties,
			String appName, String apiKey, INoReturnValueCallback callback) {
	
		this.entityId = entityId;
		this.entity = entity;
		this.linkType = linkType;
		this.newProperties = newLinkProperties;
		this.callback = callback;
		this.appName = appName;
		this.apiKey = apiKey;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
	
		try {
			if (checkParameters()) {
				
				setPutURL();
				
				setCorrectPropertiesFormat();
				setHttpPutVariablesAndExecuteRequest();
				
				if (NetworkHelper
						.isStatusCodeBetween200And300(statusCodeHttpPut)) {
					
				}
				else if (NetworkHelper.isStatusCode403Or404(statusCodeHttpPut)) {
					errorMessage = EntityUtils.toString(
							httpPutResponse.getEntity(), Constants.UTF_STRING);
				}
				else {
					errorMessage = "StatusCode is " + statusCodeHttpPut + ".";
					statusCodeHttpPut = -1;
				}
			}
			else {
				statusCodeHttpPut = -1;
				errorMessage = "PUT-Request not possible: ivalid entityId, linkType, appName or apiKey (id==0, null or empty)";
			}
		}
		catch (IOException e) {
			Log.e(Constants.TAG_ORCASDK, "IOException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "IOException " + e.getMessage();
			statusCodeHttpPut = -1;
		}
		catch (JSONException e) {
			Log.e(Constants.TAG_ORCASDK, "JSONException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "JSONException " + e.getMessage();
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
	
	private void setPutURL() throws UnsupportedEncodingException {
	
		putUri = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES + "/" + entity.getId() + "/"
				+ Constants.LINKS + "/"
				+ URLEncoder.encode(linkType, Constants.UTF_STRING);
	}
	
	private boolean checkParameters() {
	
		return entityId > 0 && linkType != null && !linkType.isEmpty()
				&& appName != null && !appName.isEmpty() && apiKey != null
				&& !apiKey.isEmpty();
	}
	
	private void setHttpPutVariablesAndExecuteRequest()
			throws UnsupportedEncodingException, IOException,
			ClientProtocolException {
	
		httpPutClient = new DefaultHttpClient();
		httpPut = new HttpPut(putUri);
		httpPut.addHeader(Constants.APIKEYHEADER, apiKey);
		httpPut.setHeader(Constants.CONTENT_TYPE,
				Constants.CONTENT_TYPE_VALUE_JSON);
		Genson genson = new Genson();
		String string = genson.serialize(newProperties);
		httpPut.setEntity(new StringEntity(string, Constants.UTF_STRING));
		httpPutResponse = httpPutClient.execute(httpPut);
		statusCodeHttpPut = httpPutResponse.getStatusLine().getStatusCode();
	}
	
	private void setCorrectPropertiesFormat() throws Exception {
	
		JSONHelper.setPropsToCorrectFormatBeforeSendToBackend(newProperties);
	}
	
	@Override
	protected void onPostExecute(Void result) {
	
		if (callback != null) {
			
			callback.onComplete(statusCodeHttpPut, errorMessage);
		}
	}
}
