
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
import de.fhws.sdk.orca.helper.JSONHelper;
import de.fhws.sdk.orca.helper.NetworkHelper;
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
public class PostSingleLinkByEntityIds extends AsyncTask<Void, Void, Void> {
	
	private String				errorMessage	= Constants.NO_ERROR;
	private String				appName;
	private String				apiKey;
	private IPostLinkCallback	callback;
	private int					entityId;
	private int					objectId;
	private int					statusCodeHttpPost;
	private Link				link;
	private HttpClient			httpPostClient;
	private HttpPost			httpPost;
	private HttpResponse		httpPostResponse;
	private String				postUri;
	private String				ressourceUri;
	
	public PostSingleLinkByEntityIds(int entityId, int objectId, Link link,
			String appName, String apiKey, IPostLinkCallback callback) {
	
		this.entityId = entityId;
		this.objectId = objectId;
		this.callback = callback;
		this.link = link;
		this.appName = appName;
		this.apiKey = apiKey;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
	
		try {
			if (checkParameters()) {
				
				JSONHelper.setPropsToCorrectFormatBeforeSendToBackend(link
						.getProperties());
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
					errorMessage = "StatusCode is " + statusCodeHttpPost + ".";
					statusCodeHttpPost = -1;
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
		link.setObject(ressourceUri);
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
	
		Genson genson = new GensonBuilder().exclude("object").create();
		String postString = genson.serialize(link);
		return postString;
	}
	
	private void setPostURL() throws UnsupportedEncodingException {
	
		postUri = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES + "/" + entityId + "/" + Constants.LINKS
				+ "/" + URLEncoder.encode(link.getType(), Constants.UTF_STRING)
				+ "/" + objectId;
	}
	
	private boolean checkParameters() {
	
		return entityId > 0 && objectId > 0 && link != null
				&& link.getType() != null && !link.getType().isEmpty()
				&& appName != null && !appName.isEmpty() && apiKey != null
				&& !apiKey.isEmpty() && link.getProperties().size() > 0;
	}
	
	@Override
	protected void onPostExecute(Void result) {
	
		if (callback != null) {
			
			callback.onComplete(statusCodeHttpPost, ressourceUri, errorMessage);
		}
	}
}
