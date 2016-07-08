
package de.fhws.sdk.orca.network.asynctask.link.post;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.helper.NetworkHelper;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.model.Link;
import de.fhws.sdk.orca.network.callback.IPostLinkCallback;

/**
 * AsyncTask-Klasse: Diese Klasse ermöglicht es einen einzelnen Link zu setzen.
 * BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/links/{type}/{objectId}
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class PostSingleLinkByEntityIdsAndLinkType<T extends Entity> extends
		AsyncTask<Void, Void, Void> {
	
	private String				errorMessage	= Constants.NO_ERROR;
	private String				appName;
	private String				apiKey;
	private IPostLinkCallback	callback;
	private T					entity;
	private int					entityId;
	private int					objectId;
	private int					statusCodeHttpPost;
	private Link				theLink			= new Link();
	private HttpClient			httpPostClient;
	private HttpPost			httpPost;
	private HttpResponse		httpPostResponse;
	private String				postUri;
	private String				ressourceUri;
	private String				linkType;
	
	public PostSingleLinkByEntityIdsAndLinkType(int entityId, int objectId,
			T entity, String linkType, String appName, String apiKey,
			IPostLinkCallback callback) {
	
		this.entityId = entityId;
		this.objectId = objectId;
		this.callback = callback;
		this.linkType = linkType;
		this.apiKey = apiKey;
		this.appName = appName;
		this.entity = entity;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
	
		try {
			if (checkParameters()) {
				
				setPostURL();
				String postString = createPostString();
				setHttpPostVariablesAndExecuteRequest(postString);
				
				if (NetworkHelper.isStatusCode201(statusCodeHttpPost)) {
					formatLink();
				}
				else if (NetworkHelper.isStatusCode403Or404(statusCodeHttpPost)) {
					errorMessage = EntityUtils.toString(
							httpPostResponse.getEntity(), Constants.UTF_STRING);
				}
				else {
					statusCodeHttpPost = -1;
					errorMessage = "StatusCode isn't 201, 403 or 404.";
				}
			}
			else {
				statusCodeHttpPost = -1;
				errorMessage = "POST-Request not possible: invalid parameter(s) (0, null or empty)";
			}
		}
		catch (UnsupportedEncodingException e) {
			Log.e(Constants.TAG_ORCASDK,
					"UnsupportedEncodingException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "UnsupportedEncodingException " + e.getMessage();
			statusCodeHttpPost = -1;
		}
		catch (ParseException e) {
			Log.e(Constants.TAG_ORCASDK, "ParseException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "ParseException " + e.getMessage();
			statusCodeHttpPost = -1;
		}
		catch (IOException e) {
			Log.e(Constants.TAG_ORCASDK, "IOException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "IOException " + e.getMessage();
			statusCodeHttpPost = -1;
		}
		catch (NullPointerException e) {
			Log.e(Constants.TAG_ORCASDK,
					"NullPointerException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "NullPointerException " + e.getMessage();
			statusCodeHttpPost = -1;
		}
		catch (Exception e) {
			Log.e(Constants.TAG_ORCASDK, "Exception " + e.getMessage());
			e.printStackTrace();
			errorMessage = "Exception " + e.getMessage();
			statusCodeHttpPost = -1;
		}
		
		return null;
	}
	
	private void formatLink() {
	
		ressourceUri = httpPostResponse.getFirstHeader(
				Constants.HEADER_LOCATION).getValue();
		theLink.setObject(ressourceUri);
		entity.getLinks().add(theLink);
	}
	
	private void setHttpPostVariablesAndExecuteRequest(String postString)
			throws UnsupportedEncodingException, IOException,
			ClientProtocolException {
	
		httpPostClient = new DefaultHttpClient();
		httpPost = new HttpPost(postUri);
		httpPost.addHeader(Constants.APIKEYHEADER, apiKey);
		httpPost.setHeader(Constants.CONTENT_TYPE,
				Constants.CONTENT_TYPE_VALUE_JSON);
		httpPost.setEntity(new StringEntity(postString, HTTP.UTF_8));
		
		httpPostResponse = httpPostClient.execute(httpPost);
		statusCodeHttpPost = httpPostResponse.getStatusLine().getStatusCode();
	}
	
	private String createPostString() {
	
		theLink.setType(linkType);
		Genson genson = new GensonBuilder().exclude(Constants.OBJECT).create();
		String postString = genson.serialize(theLink);
		return postString;
	}
	
	private void setPostURL() throws UnsupportedEncodingException {
	
		postUri = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES + "/" + entityId + "/" + Constants.LINKS
				+ "/" + URLEncoder.encode(linkType, Constants.UTF_STRING) + "/"
				+ objectId;
	}
	
	private boolean checkParameters() {
	
		return entityId > 0 && objectId > 0 && linkType != null
				&& !linkType.isEmpty() && appName != null && !appName.isEmpty()
				&& apiKey != null && !apiKey.isEmpty();
	}
	
	@Override
	protected void onPostExecute(Void result) {
	
		if (callback != null) {
			
			callback.onComplete(statusCodeHttpPost, ressourceUri, errorMessage);
		}
	}
}
