
package de.fhws.sdk.orca.network.asynctask.entity.get;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;

import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.helper.NetworkHelper;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.model.EntityPage;
import de.fhws.sdk.orca.network.callback.IEntityPageWithEntityCallback;

/**
 * AsyncTask-Klasse für das Abrufen von Entities, auf die die Links vom Typ
 * {linkType} zeigen. Alle Entities werden in einer Entity-Page zurückgegeben
 * (über das Callback). BACKEND-ENDPUNKT:
 * ...api/{appname}/entities/{entityId}/links/{linkType}/objects
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class GetAllEntitiesFromSameLinkType<T extends Entity> extends
		AsyncTask<Void, Void, EntityPage<T>> {
	
	private String								getUrl;
	private String								errorMessage	= Constants.NO_ERROR;
	private String								appName;
	private String								apiKey;
	private IEntityPageWithEntityCallback<T>	callback;
	private int									statusCodeHttpGet;
	private EntityPage<T>						retValue		= new EntityPage<T>();
	private HttpClient							httpGetClient;
	private HttpGet								httpGet;
	private HttpResponse						httpGetResponse;
	private String								responseJSONString;
	private int									entityId;
	private String								linkType;
	
	public GetAllEntitiesFromSameLinkType(int entityId, String linkType,
			String appName, String apiKey,
			IEntityPageWithEntityCallback<T> callback) {
	
		this.callback = callback;
		this.entityId = entityId;
		this.linkType = linkType;
		this.appName = appName;
		this.apiKey = apiKey;
	}
	
	@Override
	protected EntityPage<T> doInBackground(Void... params) {
	
		try {
			if (checkParams()) {
				
				setGetURL();
				setHttpClientAndHeader();
				executeHttpResponseAndSetResponseString();
				
				if (NetworkHelper.isStatusCode200(statusCodeHttpGet)) {
					deserializeWithGensonTheResponseString();
					checkResponseEntityPageSize();
				}
				else if (NetworkHelper.isStatusCode403Or404(statusCodeHttpGet)) {
					
					errorMessage = EntityUtils.toString(
							httpGetResponse.getEntity(), Constants.UTF_STRING);
				}
				else {
					errorMessage = "StatusCode is " + statusCodeHttpGet + ".";
					statusCodeHttpGet = -1;
				}
			}
			
			else {
				statusCodeHttpGet = -1;
				errorMessage = "GET-Request not possible: EntityId is 0 or invalid linkType, or AppName or Apikey";
			}
		}
		catch (IOException e) {
			Log.e(Constants.TAG_ORCASDK, "IOException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "IOException " + e.getMessage();
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
				+ Constants.ENTITIES + "/" + entityId + "/" + Constants.LINKS
				+ "/" + URLEncoder.encode(linkType, Constants.UTF_STRING) + "/"
				+ Constants.OBJECTS;
	}
	
	private void checkResponseEntityPageSize() {
	
		if (isResponseEntityPageTotalSizeZero()) {
			errorMessage = "no Links found";
			statusCodeHttpGet = -1;
		}
	}
	
	private boolean isResponseEntityPageTotalSizeZero() {
	
		return retValue.getTotalSize() == 0;
	}
	
	private void deserializeWithGensonTheResponseString() {
	
		Genson genson = new Genson();
		retValue = genson.deserialize(responseJSONString,
				new GenericType<EntityPage<T>>() {
				});
	}
	
	private boolean checkParams() {
	
		return linkType != null && !linkType.isEmpty() && appName != null
				&& !appName.isEmpty() && apiKey != null && !apiKey.isEmpty()
				&& entityId > 0;
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
	
		if (callback != null && result != null) {
			
			callback.onComplete(result, statusCodeHttpGet, errorMessage);
		}
	}
}
