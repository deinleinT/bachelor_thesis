
package de.fhws.sdk.orca.network.asynctask.entity.post;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Set;

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
import de.fhws.sdk.orca.model.EntityPage;
import de.fhws.sdk.orca.network.callback.IReturnValueCallback;

/**
 * AsyncTask-Klasse: Führt einen HTTP-Post im Backend durch. ACHTUNG: Zuvor wird
 * geprüft, ob die zu speichernde Entity schon im Backend vorhanden ist. Dazu
 * werden die Entity-Properties ausgelesen und es erfolgt zunächst ein
 * GET-Request. Wenn hierbei keine Entity gefunden wird, erfolgt der Post.
 * Ansonsten erfolgt kein Post. Über das Callback wird in der ErrorMessage die
 * EntityId(s) ausgegeben, die der zu speichernden Entity ähnlich sind.
 * BACKEND-ENDPUNKT: /api/{appname}/entities
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class PostSingleEntityWithCheckWhetherEntityStillExists<T extends Entity>
		extends AsyncTask<T, Void, T> {
	
	private String					postUrl;
	private String					selfUri;
	private IReturnValueCallback<T>	callback;
	private T						originalEntity;
	private T						returnValue;
	private int						statusCodeHttpPost;
	private int						statusCodeHttpGet;
	private String					errorMessage	= Constants.NO_ERROR;
	private String					appName;
	private String					apiKey;
	private JSONObject				jsonEntity;
	private HttpClient				httpPostClient;
	private HttpPost				httpPost;
	private HttpResponse			httpPostResponse;
	private String					similiarIds		= "";
	
	public PostSingleEntityWithCheckWhetherEntityStillExists(String appName,
			String apiKey, IReturnValueCallback<T> callback) {
	
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
				checkIfOriginalEntityStillExists();
				
				if (NetworkHelper.isStatusCode200(statusCodeHttpGet)) {
					setEntityPropertiesToCorrectFormatAndPostEntity();
				}
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
	
	@SuppressWarnings("unchecked")
	private void setOriginalEntity(T... params) {
	
		originalEntity = params[0];
	}
	
	private void setEntityPropertiesToCorrectFormatAndPostEntity()
			throws Exception, ClientProtocolException, IOException,
			JSONException, ParseException {
	
		JSONHelper.setPropsToCorrectFormatBeforeSendToBackend(originalEntity
				.getProperties());
		postTheEntity();
	}
	
	@SuppressWarnings("unchecked")
	private boolean checkParameters(T... params) {
	
		return params.length == 1 && params[0] != null && appName != null
				&& !appName.isEmpty() && apiKey != null && !apiKey.isEmpty()
				&& params[0].getType() > 0;
	}
	
	private void checkIfOriginalEntityStillExists() throws Exception {
	
		String getUrl;
		HttpResponse httpGetResponse;
		String responseJSONStringGet;
		
		getUrl = setGetURL();
		httpGetResponse = setHttpGetVariablesAndExecuteRequest(getUrl);
		responseJSONStringGet = EntityUtils.toString(
				httpGetResponse.getEntity(), Constants.UTF_STRING);
		
		if (isEntityPageSizeNotZero(responseJSONStringGet)) {
			EntityPage<Entity> entityPage = createEntityPage(responseJSONStringGet);
			statusCodeHttpGet = -1;
			statusCodeHttpPost = -1;
			ArrayList<Entity> list = entityPage.getEntities();
			createErrorMessage(responseJSONStringGet, list);
		}
		else {
			statusCodeHttpGet = 200;
		}
		
	}
	
	private HttpResponse setHttpGetVariablesAndExecuteRequest(String getUrl)
			throws IOException, ClientProtocolException {
	
		HttpClient httpGetClient;
		HttpGet httpGet;
		HttpResponse httpGetResponse;
		httpGetClient = new DefaultHttpClient();
		httpGet = new HttpGet(getUrl);
		httpGet.addHeader(Constants.APIKEYHEADER, apiKey);
		httpGetResponse = httpGetClient.execute(httpGet);
		statusCodeHttpGet = httpGetResponse.getStatusLine().getStatusCode();
		return httpGetResponse;
	}
	
	private void createErrorMessage(String responseJSONStringGet,
			ArrayList<Entity> list) throws JSONException {
	
		for (Entity e : list) {
			similiarIds = similiarIds + " " + e.getId();
		}
		errorMessage = "there are "
				+ JSONHelper.getEntityPageTotalSize(responseJSONStringGet)
				+ " Entities already saved, which are similar to the Entity, which shall be saved. Please check Entities with the IDs: "
				+ similiarIds + " ";
	}
	
	private EntityPage<Entity> createEntityPage(String responseJSONStringGet)
			throws JSONException, ParseException, Exception {
	
		EntityPage<Entity> entityPage = JSONHelper
				.createEntityPageFromResponsJSONString(responseJSONStringGet,
						Entity.class, originalEntity.getType());
		return entityPage;
	}
	
	private boolean isEntityPageSizeNotZero(String responseJSONStringGet)
			throws JSONException {
	
		return JSONHelper.getEntityPageTotalSize(responseJSONStringGet) > 0;
	}
	
	private void postTheEntity() throws ClientProtocolException, IOException,
			JSONException, ParseException {
	
		setPostURL();
		Set<String> entityPropertyKeys = getAllPropertyKeysFromOriginalEntity();
		setPropertiesToCorrectFormat(entityPropertyKeys);
		String postString = createPostString();
		sendPostToBackend(postString);
		
		if (NetworkHelper.isStatusCode201(statusCodeHttpPost)) {
			
			try {
				sendHttpGetToBackendToLoadThePostedEntity();
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
	
	private String createPostString() {
	
		Genson genson = new GensonBuilder().exclude(Constants.ENTITY_ENTITYID)
				.exclude(Constants.ENTITY_SELF_URL)
				.exclude(Constants.ENTITY_LINKS).create();
		String postString = genson.serialize(originalEntity);
		return postString;
	}
	
	private Set<String> getAllPropertyKeysFromOriginalEntity() {
	
		Set<String> entityPropertyKeys = originalEntity.getProperties()
				.keySet();
		return entityPropertyKeys;
	}
	
	private void setPostURL() throws UnsupportedEncodingException {
	
		postUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES;
	}
	
	private void setPropertiesToCorrectFormat(Set<String> entityPropertyKeys) {
	
		// Images werden aus den Properties entfernt
		for (String key : entityPropertyKeys) {
			if (originalEntity.getProperties().get(key) != null
					&& originalEntity.getProperties().get(key).getClass()
							.getSimpleName().equalsIgnoreCase(Constants.STRING)) {
				String value = (String) originalEntity.getProperties().get(key);
				if (value.contains(Constants.WIDTH)
						|| value.contains(Constants.HEIGHT)
						|| value.contains(Constants.RADIUS)) {
					originalEntity.getProperties().remove(key);
				}
			}
		}
		//
	}
	
	private void sendHttpGetToBackendToLoadThePostedEntity()
			throws IOException, ClientProtocolException, JSONException,
			ParseException {
	
		setSelfURL();
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(selfUri);
		get.addHeader(Constants.APIKEYHEADER, apiKey);
		HttpResponse res = httpClient.execute(get);
		String response = EntityUtils.toString(res.getEntity(),
				Constants.UTF_STRING);
		
		setReturnValue(response);
	}
	
	private void setReturnValue(String response) throws JSONException,
			ParseException {
	
		returnValue = setNewEntityFromSingleEntity(response);
	}
	
	private void setSelfURL() {
	
		selfUri = httpPostResponse.getFirstHeader(Constants.HEADER_LOCATION)
				.getValue();
	}
	
	private void sendPostToBackend(String jsonString)
			throws UnsupportedEncodingException, IOException,
			ClientProtocolException {
	
		setHttpPostVariablesAndHeadersAndExecuteRequest(jsonString);
		setResponseAndStatusCodeHttpPost();
	}
	
	private void setHttpPostVariablesAndHeadersAndExecuteRequest(
			String jsonString) throws UnsupportedEncodingException {
	
		httpPostClient = new DefaultHttpClient();
		httpPost = new HttpPost(postUrl);
		httpPost.addHeader(Constants.APIKEYHEADER, apiKey);
		httpPost.setHeader(Constants.CONTENT_TYPE,
				Constants.CONTENT_TYPE_VALUE_JSON);
		httpPost.setEntity(new StringEntity(jsonString, HTTP.UTF_8));
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
	
	private String setGetURL() throws Exception {
	
		return Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES
				+ NetworkHelper.createQueryStringFromEntity(originalEntity);
	}
	
	@Override
	protected void onPostExecute(T result) {
	
		if (callback != null) {
			
			callback.onComplete(result, statusCodeHttpPost, errorMessage);
		}
	}
	
}
