
package de.fhws.sdk.orca.network.asynctask.entity.post;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.helper.JSONHelper;
import de.fhws.sdk.orca.helper.NetworkHelper;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.network.callback.IReturnValueCallback;

/**
 * Klasse verhält sich wie
 * {@linkplain de.fhws.sdk.orca.network.asynctask.entity.post.PostSingleEntityWithCheckWhetherEntityStillExists
 * PostSingleEntityWithCheckWhetherEntityStillExists}, nur erfolgt hier keine
 * vorherige Abfrage, ob es die zu speichernde Entity schon gibt. Die Entity
 * wird gleich gespeichert, unabhängig, ob es diese im Backend evtl. schon gibt.
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class PostSingleEntityWithoutCheck<T extends Entity> extends
		AsyncTask<T, Void, T> {
	
	private String					postUrl;
	private String					selfUri;
	private IReturnValueCallback<T>	callback;
	private T						originalEntity;
	private T						returnValue;
	private int						statusCodeHttpPost;
	private String					errorMessage	= Constants.NO_ERROR;
	private String					appName;
	private String					apiKey;
	private JSONObject				jsonEntity;
	private HttpClient				httpPostClient;
	private HttpPost				httpPost;
	private HttpResponse			httpPostResponse;
	
	public PostSingleEntityWithoutCheck(String appName, String apiKey,
			IReturnValueCallback<T> callback) {
	
		this.callback = callback;
		this.appName = appName;
		this.apiKey = apiKey;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected T doInBackground(T... params) {
	
		try {
			if (checkParameters(params)) {
				
				setOriginalEntity(params);
				setPropertiesToCorrectFormatAndPostEntity();
				
			}
			else {
				statusCodeHttpPost = -1;
				errorMessage = "Post-Request not possible: invalid entity, appName or apiKey (id!=0, null or empty)";
			}
		}
		catch (JSONException e) {
			Log.e(Constants.TAG_ORCASDK, "JSONException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "JSONException " + e.getMessage();
			statusCodeHttpPost = -1;
		}
		catch (ParseException e) {
			Log.e(Constants.TAG_ORCASDK, "ParseException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "ParseException " + e.getMessage();
			statusCodeHttpPost = -1;
		}
		catch (ClientProtocolException e) {
			Log.e(Constants.TAG_ORCASDK,
					"ClientProtocolException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "ClientProtocolException " + e.getMessage();
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
		
		return returnValue;
	}
	
	private void setPropertiesToCorrectFormatAndPostEntity() throws Exception,
			ClientProtocolException, IOException, JSONException, ParseException {
	
		JSONHelper.setPropsToCorrectFormatBeforeSendToBackend(originalEntity
				.getProperties());
		postTheEntity();
	}
	
	@SuppressWarnings("unchecked")
	private void setOriginalEntity(T... params) {
	
		originalEntity = params[0];
	}
	
	@SuppressWarnings("unchecked")
	private boolean checkParameters(T... params) {
	
		return params.length == 1 && appName != null && !appName.isEmpty()
				&& apiKey != null && !apiKey.isEmpty()
				&& params[0].getType() > 0;
	}
	
	private void postTheEntity() throws ClientProtocolException, IOException,
			JSONException, ParseException {
	
		setPostURL();
		
		// Images und Links werden aus den Properties entfernt
		for (String key : originalEntity.getImagePropertyNames()) {
			originalEntity.getProperties().remove(key);
		}
		for (String key : originalEntity.getLinkPropertyDatatypes().keySet()) {
			originalEntity.getProperties().remove(key);
		}
		
		String postString = setPostString();
		sendPostToBackend(postString);
		
		if (NetworkHelper.isStatusCode201(statusCodeHttpPost)) {
			
			try {
				sendHttpGetToBackend();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			JSONHelper
					.setPropertiesToCorrectDatatypesFromResponseEntity(originalEntity
							.getProperties());
			errorMessage = Constants.POST_FAILED;
			statusCodeHttpPost = -1;
		}
	}
	
	private String setPostString() {
	
		Genson genson = new GensonBuilder().exclude(Constants.ENTITY_ENTITYID)
				.exclude(Constants.ENTITY_SELF_URL)
				.exclude(Constants.ENTITY_LINKS).create();
		String postString = genson.serialize(originalEntity);
		return postString;
	}
	
	private void setPostURL() throws UnsupportedEncodingException {
	
		postUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES;
	}
	
	private void sendHttpGetToBackend() throws IOException,
			ClientProtocolException, JSONException, ParseException {
	
		selfUri = httpPostResponse.getFirstHeader(Constants.HEADER_LOCATION)
				.getValue();
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(selfUri);
		get.addHeader(Constants.APIKEYHEADER, apiKey);
		HttpResponse res = httpClient.execute(get);
		String response = EntityUtils.toString(res.getEntity(), "UTF-8");
		
		returnValue = setNewEntityFromSingleEntity(response);
	}
	
	private void sendPostToBackend(String jsonString)
			throws UnsupportedEncodingException, IOException,
			ClientProtocolException {
	
		httpPostClient = new DefaultHttpClient();
		httpPost = new HttpPost(postUrl);
		httpPost.addHeader(Constants.APIKEYHEADER, apiKey);
		httpPost.setHeader(Constants.CONTENT_TYPE,
				Constants.CONTENT_TYPE_VALUE_JSON);
		httpPost.setEntity(new StringEntity(jsonString, HTTP.UTF_8));
		setResponseAndStatusCodeHttpPost();
	}
	
	private void setResponseAndStatusCodeHttpPost() throws IOException,
			ClientProtocolException {
	
		httpPostResponse = httpPostClient.execute(httpPost);
		statusCodeHttpPost = httpPostResponse.getStatusLine().getStatusCode();
	}
	
	@SuppressWarnings("unchecked")
	private T setNewEntityFromSingleEntity(String responseJSONString)
			throws JSONException, ParseException {
	
		jsonEntity = new JSONObject(responseJSONString);
		Genson gen = new Genson();
		T retValue = (T) gen.deserialize(jsonEntity.toString(),
				originalEntity.getClass());
		JSONHelper.setPropertiesToCorrectDatatypesFromResponseEntity(retValue
				.getProperties());
		originalEntity.setId(retValue.getId());
		originalEntity.setProperties(retValue.getProperties());
		originalEntity.setSelf(retValue.getSelf());
		
		return retValue;
	}
	
	@Override
	protected void onPostExecute(T result) {
	
		if (callback != null) {
			
			callback.onComplete(result, statusCodeHttpPost, errorMessage);
		}
	}
	
}
