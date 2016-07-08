
package de.fhws.sdk.orca.network.asynctask.link.get;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
import de.fhws.sdk.orca.model.LinkPage;
import de.fhws.sdk.orca.network.callback.ILinkPageCallback;

/**
 * AsyncTask-Klasse: Dieser Endpunkt ermöglicht es alle Links einer Entität von
 * dem angegeben Typ in einer Link-Page zu erhalten. BACKEND-ENDPUNKT:
 * api/{appname}/entities/{id}/links/{type}
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class GetAllLinksFromSameTypeOfAnEntity<T extends Entity> extends
		AsyncTask<Void, Void, LinkPage> {
	
	private String				errorMessage	= Constants.NO_ERROR;
	private String				appName;
	private String				apiKey;
	private T					entity;
	private String				linkType;
	private int					statusCodeHttpGet;
	private HttpClient			httpGetClient;
	private HttpGet				httpGet;
	private HttpResponse		httpGetResponse;
	private ILinkPageCallback	callback;
	private String				getUrl;
	private String				responseJSONString;
	private LinkPage			linkPage;
	
	public GetAllLinksFromSameTypeOfAnEntity(T entity, String linkType,
			String appName, String apiKey, ILinkPageCallback callback) {
	
		this.entity = entity;
		this.linkType = linkType;
		this.callback = callback;
		this.appName = appName;
		this.apiKey = apiKey;
	}
	
	@Override
	protected LinkPage doInBackground(Void... params) {
	
		try {
			if (checkParameters()) {
				
				setGetURL();
				setHttpGetVariablesAndHeadersAndExecuteRequest();
				
				if (isPageSizeZero()) {
					statusCodeHttpGet = -1;
					errorMessage = Constants.NO_LINKS_FOUND;
					return null;
				}
				
				if (NetworkHelper.isStatusCode200(statusCodeHttpGet)) {
					createLinkPage();
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
				errorMessage = "GET-Request not possible: invalid parameters (0, null or empty)";
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
		
		return linkPage;
	}
	
	private void createLinkPage() {
	
		Genson genson = new Genson();
		linkPage = genson.deserialize(responseJSONString, LinkPage.class);
		entity.setLinks(linkPage.getLinks());
	}
	
	private boolean isPageSizeZero() throws JSONException {
	
		return JSONHelper.getEntityPagePageSize(responseJSONString) == 0;
	}
	
	private void setHttpGetVariablesAndHeadersAndExecuteRequest()
			throws IOException, ClientProtocolException {
	
		httpGetClient = new DefaultHttpClient();
		httpGet = new HttpGet(getUrl);
		httpGet.addHeader(Constants.APIKEYHEADER, apiKey);
		httpGetResponse = httpGetClient.execute(httpGet);
		statusCodeHttpGet = httpGetResponse.getStatusLine().getStatusCode();
		responseJSONString = EntityUtils.toString(httpGetResponse.getEntity(),
				Constants.UTF_STRING);
	}
	
	private void setGetURL() throws UnsupportedEncodingException {
	
		getUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES + "/" + entity.getId() + "/"
				+ Constants.LINKS + "/"
				+ URLEncoder.encode(linkType, Constants.UTF_STRING);
	}
	
	private boolean checkParameters() {
	
		return entity.getId() > 0 && linkType != null && !linkType.isEmpty()
				&& appName != null && !appName.isEmpty() && apiKey != null
				&& !apiKey.isEmpty();
	}
	
	@Override
	protected void onPostExecute(LinkPage result) {
	
		if (callback != null) {
			
			callback.onComplete(result, statusCodeHttpGet, errorMessage);
		}
	}
}
