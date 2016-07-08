
package de.fhws.sdk.orca.network.asynctask.link.get;

import java.io.IOException;

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
import de.fhws.sdk.orca.model.LinkPage;
import de.fhws.sdk.orca.network.callback.ILinkPageCallback;

/**
 * AsyncTask-Klasse: Dieser Endpunkt ermöglicht es eine LinkPage per
 * NavigationLink anzufordern. Über das Callback kann diese abgefragt werden.
 * BACKEND-ENDPUNKT: der übergebene Link
 * 
 * @author ThomasDeinlein
 */
public class GetLinkPageByNavigationLink extends
		AsyncTask<Void, Void, LinkPage> {
	
	private String				errorMessage	= Constants.NO_ERROR;
	private String				apiKey;
	private String				navLink;
	private int					statusCodeHttpGet;
	private HttpClient			httpGetClient;
	private HttpGet				httpGet;
	private HttpResponse		httpGetResponse;
	private ILinkPageCallback	callback;
	private String				getUri;
	private String				responseJSONString;
	private LinkPage			linkPage;
	
	public GetLinkPageByNavigationLink(String navLink, String apiKey,
			ILinkPageCallback callback) {
	
		this.navLink = navLink;
		this.callback = callback;
		this.apiKey = apiKey;
	}
	
	@Override
	protected LinkPage doInBackground(Void... params) {
	
		try {
			if (checkParameters()) {
				
				setGetURL();
				setHttpGetVariablesAndExecuteRequest();
				
				if (isPageSizeZero()) {
					statusCodeHttpGet = -1;
					errorMessage = Constants.NO_LINKS_FOUND;
					return null;
				}
				
				if (NetworkHelper
						.isStatusCodeBetween200And300(statusCodeHttpGet)) {
					createLinkPage();
				}
				else if (NetworkHelper.isStatusCode403Or404(statusCodeHttpGet)) {
					errorMessage = EntityUtils.toString(
							httpGetResponse.getEntity(), Constants.UTF_STRING);
				}
				else {
					statusCodeHttpGet = -1;
					errorMessage = "StatusCode is " + statusCodeHttpGet + ".";
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
	}
	
	private boolean isPageSizeZero() throws JSONException {
	
		return JSONHelper.getEntityPagePageSize(responseJSONString) == 0;
	}
	
	private void setHttpGetVariablesAndExecuteRequest() throws IOException,
			ClientProtocolException {
	
		httpGetClient = new DefaultHttpClient();
		httpGet = new HttpGet(getUri);
		httpGet.addHeader(Constants.APIKEYHEADER, apiKey);
		httpGetResponse = httpGetClient.execute(httpGet);
		statusCodeHttpGet = httpGetResponse.getStatusLine().getStatusCode();
		responseJSONString = EntityUtils.toString(httpGetResponse.getEntity(),
				Constants.UTF_STRING);
	}
	
	private void setGetURL() {
	
		getUri = navLink;
	}
	
	private boolean checkParameters() {
	
		return navLink != null && !navLink.isEmpty() && apiKey != null
				&& !apiKey.isEmpty();
	}
	
	@Override
	protected void onPostExecute(LinkPage result) {
	
		if (callback != null) {
			
			callback.onComplete(result, statusCodeHttpGet, errorMessage);
		}
	}
}
