
package de.fhws.sdk.orca.network.asynctask.entity.get;

import java.io.IOException;
import java.text.ParseException;

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
import de.fhws.sdk.orca.network.callback.IPageCallback;

/**
 * AsyncTask-Klasse: Dient dazu, Entities von einem NavigationLink einer
 * EntityPage zu laden. Über ein EntityPageObjekt können mit der Methode
 * getNavigationLinks() alle entsprechenden Links abgerufen werden. Dieser
 * Methode ist dann ein einzelner String-Link zu übergeben. Über das Callback
 * erhält man die entsprechende neue EntityPage BACKEND-ENDPUNKT:
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class GetEntitiesByNavigationLink<T extends Entity> extends
		AsyncTask<Void, Void, EntityPage<T>> {
	
	private String					getUrl;
	private String					errorMessage	= Constants.NO_ERROR;
	private String					apiKey;
	private IPageCallback<T>		callback;
	private int						statusCodeHttpGet;
	private EntityPage<T>			retValue		= new EntityPage<T>();
	private HttpClient				httpGetClient;
	private HttpGet					httpGet;
	private HttpResponse			httpGetResponse;
	private String					responseJSONString;
	private Long					type;
	private Class<? extends Entity>	classType;
	private String					navigationLink;
	
	public GetEntitiesByNavigationLink(String link, Long entityType,
			Class<? extends Entity> classType, String apiKey,
			IPageCallback<T> callback) {
	
		this.callback = callback;
		this.classType = classType;
		this.type = entityType;
		this.navigationLink = link;
		this.apiKey = apiKey;
	}
	
	@Override
	protected EntityPage<T> doInBackground(Void... params) {
	
		try {
			if (checkParams()) {
				
				setGetURL();
				Log.d(Constants.TAG_ORCASDK, Constants.TAG_BACKEND
						+ " GetEntitiesByNavigationLink doInBackground getURL "
						+ getUrl);
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
				errorMessage = "GET-Request not possible: invalid navigationLink, or invalid appName or apiKey";
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
	
	private void setRetValue() throws JSONException, ParseException, Exception {
	
		retValue = JSONHelper.<T> createEntityPageFromResponsJSONString(
				responseJSONString, classType, type);
	}
	
	private boolean isEntityPageSizeZero() throws JSONException {
	
		return JSONHelper.getEntityPageTotalSize(responseJSONString) == 0;
	}
	
	private void setGetURL() {
	
		getUrl = navigationLink;
	}
	
	private boolean checkParams() {
	
		return navigationLink != null && !navigationLink.isEmpty()
				&& type != null && type > 0 && apiKey != null
				&& !apiKey.isEmpty() && classType != null;
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
			
			callback.onComplete(result, statusCodeHttpGet, errorMessage);
		}
	}
	
}
