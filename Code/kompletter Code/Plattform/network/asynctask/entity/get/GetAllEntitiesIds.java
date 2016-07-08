
package de.fhws.sdk.orca.network.asynctask.entity.get;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import android.os.AsyncTask;
import android.util.Log;
import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.helper.JSONHelper;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.model.EntityPage;
import de.fhws.sdk.orca.network.callback.IAllEntityIdsCallback;

/**
 * AsyncTask-Klasse zum Abrufen aller im Backend vergebener Ids eines selben
 * EntityTypes. Das Callback gibt die gefundenen IDs als ArrayList zurück.
 * BACKEND-ENDPUNKT: .../api/{appname}/entities
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class GetAllEntitiesIds<T extends Entity> extends
		AsyncTask<Void, Void, EntityPage<T>> {
	
	private String					getUrl;
	private String					errorMessage	= Constants.NO_ERROR;
	private String					appName;
	private String					apiKey;
	private IAllEntityIdsCallback	callback;
	private int						statusCodeHttpGet;
	private EntityPage<T>			retValue		= new EntityPage<T>();
	private HttpClient				httpGetClient;
	private HttpGet					httpGet;
	private HttpResponse			httpGetResponse;
	private String					responseJSONString;
	private Long					type;
	private Class<? extends Entity>	classType;
	
	public GetAllEntitiesIds(Long type, Class<? extends Entity> classType,
			String appName, String apiKey, IAllEntityIdsCallback callback) {
	
		this.callback = callback;
		this.type = type;
		this.classType = classType;
		this.apiKey = apiKey;
		this.appName = appName;
		
	}
	
	@Override
	protected EntityPage<T> doInBackground(Void... params) {
	
		try {
			if (checkParams()) {
				
				setGetURL();
				
				Log.d(Constants.TAG_ORCASDK, Constants.TAG_BACKEND
						+ " GetAllEntitiesIds doInBackground GET-URL " + getUrl);
				
				setHttpClientAndHeader();
				executeHttpResponseAndSetResponseString();
				
				if (isEntityPageSizeZero()) {
					statusCodeHttpGet = -1;
					errorMessage = "no Entities found";
					return null;
				}
				
				setRetValue();
				
			}
			
			else {
				statusCodeHttpGet = -1;
				errorMessage = "GET-Request not possible: invalid QueryString, appName, apiKey or classType";
			}
			
		}
		catch (IOException e) {
			Log.e(Constants.TAG_ORCASDK, "IOException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "IOException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (JSONException e) {
			Log.e(Constants.TAG_ORCASDK, "ParseException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "JSONException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (NullPointerException e) {
			Log.e(Constants.TAG_ORCASDK,
					"NullPointerException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "NullPointerException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (Exception e) {
			Log.e(Constants.TAG_ORCASDK, "Exception " + e.getMessage());
			e.printStackTrace();
			errorMessage = "Exception " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		
		return retValue;
	}
	
	private void setGetURL() throws UnsupportedEncodingException {
	
		getUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES + "?query=&type=" + type
				+ "&orderby=&offset=0&size=1000";
	}
	
	private void setRetValue() throws JSONException, ParseException, Exception {
	
		retValue = JSONHelper.<T> createEntityPageFromResponsJSONString(
				responseJSONString, classType, type);
	}
	
	private boolean isEntityPageSizeZero() throws JSONException {
	
		return JSONHelper.getEntityPageTotalSize(responseJSONString) == 0;
	}
	
	private boolean checkParams() {
	
		return type != null && type > 0 && appName != null
				&& !appName.isEmpty() && apiKey != null && !apiKey.isEmpty()
				&& classType != null;
	}
	
	private void executeHttpResponseAndSetResponseString() throws IOException,
			ClientProtocolException {
	
		httpGetResponse = httpGetClient.execute(httpGet);
		statusCodeHttpGet = httpGetResponse.getStatusLine().getStatusCode();
		responseJSONString = EntityUtils.toString(httpGetResponse.getEntity(),
				Constants.UTF_STRING);
		
	}
	
	private void setHttpClientAndHeader() {
	
		httpGetClient = new DefaultHttpClient();
		httpGet = new HttpGet(getUrl);
		httpGet.addHeader(Constants.APIKEYHEADER, apiKey);
	}
	
	@Override
	protected void onPostExecute(EntityPage<T> result) {
	
		if (callback != null) {
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			for (T entity : result.getEntities()) {
				list.add(entity.getId());
			}
			Collections.sort(list);
			
			callback.onComplete(list, statusCodeHttpGet, errorMessage);
		}
	}
}
