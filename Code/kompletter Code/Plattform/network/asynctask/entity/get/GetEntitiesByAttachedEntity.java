
package de.fhws.sdk.orca.network.asynctask.entity.get;

import java.io.IOException;
import java.net.URLEncoder;
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
import de.fhws.sdk.orca.helper.NetworkHelper;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.model.EntityPage;
import de.fhws.sdk.orca.network.callback.IPageCallback;

/**
 * Prüft anhand der Properties der übergebenen Entity, ob es ähnliche Entitäten
 * schon im Backend gibt. Wenn ja, wird über das Callback eine
 * {@linkplain de.fhws.sdk.orca.model.EntityPage EntityPage} mit allen
 * gefundenen Entitäten abrufbar. BACKEND-ENDPUNKT: .../api/{appname}/entities
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class GetEntitiesByAttachedEntity<T extends Entity> extends
		AsyncTask<T, Void, EntityPage<T>> {
	
	private String				getUrl;
	private String				errorMessage	= Constants.NO_ERROR;
	private String				apiKey;
	private String				appName;
	private IPageCallback<T>	callback;
	private T					originalEntity;
	private int					statusCodeHttpGet;
	private HttpClient			httpGetClient;
	private HttpGet				httpGet;
	private HttpResponse		httpGetResponse;
	private EntityPage<T>		retValue;
	private String				responseJSONString;
	
	public GetEntitiesByAttachedEntity(String appName, String apiKey,
			IPageCallback<T> callback) {
	
		this.callback = callback;
		this.apiKey = apiKey;
		this.appName = appName;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected EntityPage<T> doInBackground(T... params) {
	
		try {
			if (isParamsLengthOne(params)) {
				initializeOriginalEntity(params);
				if (isEntityTypeOfOriginalEntityNegativeOrNull()) {
					setErrorMessageAndStatusCodeIfEntityTypeIsZero();
					return null;
				}
				
				if (checkParams()) {
					errorMessage = "invalid appName or apiKey";
					statusCodeHttpGet = -1;
					return null;
				}
				formatProperties();
				setGetURL();
				Log.d(Constants.TAG_ORCASDK,
						Constants.TAG_BACKEND
								+ " GetEntitiesByAttachedEntityObject doInBackground getURL "
								+ getUrl);
				setHttpClientAndHeader();
				executeHttpResponseAndSetResponseString();
				setRetValue();
			}
			
			else {
				statusCodeHttpGet = -1;
				errorMessage = "GET-Request not possible, doInBackground-Method has more than one Entity attached or no EntityId available";
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
	
	private boolean checkParams() {
	
		return appName == null || appName.isEmpty() || apiKey == null
				|| apiKey.isEmpty() || originalEntity == null
				|| originalEntity.getProperties() == null
				|| originalEntity.getProperties().size() <= 0;
	}
	
	private void setRetValue() throws JSONException, ParseException, Exception {
	
		if (JSONHelper.getEntityPageTotalSize(responseJSONString) == 0) {
			errorMessage = "no Entities found";
			statusCodeHttpGet = -1;
			retValue = null;
			
		}
		else {
			retValue = JSONHelper.createEntityPageFromResponsJSONString(
					responseJSONString, originalEntity.getClass(),
					originalEntity.getType());
		}
	}
	
	private void executeHttpResponseAndSetResponseString() throws IOException,
			ClientProtocolException {
	
		httpGetResponse = httpGetClient.execute(httpGet);
		statusCodeHttpGet = httpGetResponse.getStatusLine().getStatusCode();
		
		if (NetworkHelper.isStatusCode200(statusCodeHttpGet)) {
			responseJSONString = EntityUtils.toString(
					httpGetResponse.getEntity(), Constants.UTF_STRING);
		}
		else {
			errorMessage = EntityUtils.toString(httpGetResponse.getEntity(),
					Constants.UTF_STRING);
		}
		
	}
	
	private void setHttpClientAndHeader() {
	
		httpGetClient = new DefaultHttpClient();
		httpGet = new HttpGet(getUrl);
		httpGet.addHeader(Constants.APIKEYHEADER, apiKey);
	}
	
	private void setGetURL() throws Exception {
	
		getUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES
				+ NetworkHelper.createQueryStringFromEntity(originalEntity);
	}
	
	private void formatProperties() throws Exception {
	
		JSONHelper.setPropsToCorrectFormatBeforeSendToBackend(originalEntity
				.getProperties());
	}
	
	private void setErrorMessageAndStatusCodeIfEntityTypeIsZero() {
	
		errorMessage = "no Entity-Type or invalid value! You have to set the Entity Type.";
		statusCodeHttpGet = -1;
	}
	
	@SuppressWarnings("unchecked")
	private void initializeOriginalEntity(T... params) {
	
		originalEntity = params[0];
	}
	
	@SuppressWarnings("unchecked")
	private boolean isParamsLengthOne(T... params) {
	
		return params.length == 1;
	}
	
	private boolean isEntityTypeOfOriginalEntityNegativeOrNull() {
	
		return (originalEntity.getType() <= 0);
	}
	
	@Override
	protected void onPostExecute(EntityPage<T> result) {
	
		if (callback != null) {
			
			callback.onComplete(result, statusCodeHttpGet, errorMessage);
		}
	}
	
}
