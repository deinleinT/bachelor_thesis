
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
import de.fhws.sdk.orca.model.Link;
import de.fhws.sdk.orca.network.callback.INoReturnValueCallback;

/**
 * AsyncTask-Klasse: Dieser Endpunkt ermöglicht es, einen bestimmten Link
 * zwischen zwei Entitäten zusätzliche Properties hinzuzufügen, oder vorhandene
 * zu aktualisieren. BACKEND-ENDPUNKT:
 * /api/{appname}/entities/{id}/links/{type}/{objectId}/property
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class PutAdditionalPropertyToLinksOfSameLinkType<T extends Entity>
		extends AsyncTask<Void, Void, Void> {
	
	private String					errorMessage	= Constants.NO_ERROR;
	private String					appName;
	private String					apiKey;
	private T						entity;
	private int						entityId;
	private int						objectId;
	private String					linkType;
	private int						statusCodeHttpPut;
	private HttpClient				httpPutClient;
	private HttpPut					httpPut;
	private HttpResponse			httpPutResponse;
	private INoReturnValueCallback	callback;
	private HashMap<String, Object>	newProperty;
	private String					putUrl;
	
	public PutAdditionalPropertyToLinksOfSameLinkType(int entityId,
			int objectId, String linkType, T entity,
			HashMap<String, Object> newProperty, String appName, String apiKey,
			INoReturnValueCallback callback) {
	
		this.entityId = entityId;
		this.objectId = objectId;
		this.entity = entity;
		this.linkType = linkType;
		this.newProperty = newProperty;
		this.callback = callback;
		this.apiKey = apiKey;
		this.appName = appName;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
	
		try {
			if (checkParameters()) {
				
				setPutURL();
				formatProperties();
				setHttpPutVariablesAndExecuteRequest();
				
				if (NetworkHelper.isStatusCode204(statusCodeHttpPut)) {
					formatLinks();
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
				errorMessage = "PUT-Request not possible: ivalid entityId, linkType, newProperty, appName or apiKey (id==0, HashmapSize is not 1, null or empty)";
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
	
	private void formatLinks() {
	
		if (entity.getLinks().size() > 0) {
			for (Link link : entity.getLinks()) {
				if (link.getObject().contains(String.valueOf(entity.getId()))
						&& link.getObject().contains(String.valueOf(objectId))) {
					link.getProperties().put(linkType, newProperty);
				}
			}
		}
	}
	
	private void setHttpPutVariablesAndExecuteRequest()
			throws UnsupportedEncodingException, IOException,
			ClientProtocolException {
	
		httpPutClient = new DefaultHttpClient();
		httpPut = new HttpPut(putUrl);
		httpPut.addHeader(Constants.APIKEYHEADER, apiKey);
		httpPut.setHeader(Constants.CONTENT_TYPE,
				Constants.CONTENT_TYPE_VALUE_JSON);
		Genson genson = new Genson();
		String string = genson.serialize(newProperty);
		httpPut.setEntity(new StringEntity(string, Constants.UTF_STRING));
		httpPutResponse = httpPutClient.execute(httpPut);
		statusCodeHttpPut = httpPutResponse.getStatusLine().getStatusCode();
	}
	
	private void formatProperties() throws Exception {
	
		JSONHelper.setPropsToCorrectFormatBeforeSendToBackend(newProperty);
	}
	
	private void setPutURL() throws UnsupportedEncodingException {
	
		putUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES + "/" + entityId + "/" + Constants.LINKS
				+ "/" + URLEncoder.encode(linkType, Constants.UTF_STRING) + "/"
				+ objectId + "/" + Constants.PROPERTY;
	}
	
	private boolean checkParameters() {
	
		return entityId > 0 && objectId > 0 && linkType != null
				&& !linkType.isEmpty() && newProperty != null
				&& newProperty.size() > 0;
	}
	
	@Override
	protected void onPostExecute(Void result) {
	
		if (callback != null) {
			
			callback.onComplete(statusCodeHttpPut, errorMessage);
		}
	}
}
