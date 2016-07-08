
package de.fhws.sdk.orca.network.asynctask.image.get;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.helper.NetworkHelper;
import de.fhws.sdk.orca.network.callback.IImageCallback;

/**
 * AsyncTask-Klasse zum Abfragen eines Entity-Image anhand entsprechenden
 * ImageLinks. Über das Callback kann das Bild als Bitmap abgefragt werden.
 * BACKEND-ENDPUNKT: der übergebene Link
 * 
 * @author ThomasDeinlein
 */
public class GetEntityImageByImageLink extends AsyncTask<Void, Void, Void> {
	
	private String			getUrl;
	private String			errorMessage	= Constants.NO_ERROR;
	private String			appName;
	private String			apiKey;
	private IImageCallback	callback;
	private int				width;
	private int				height;
	private int				radius;
	private String			backgroundColor;
	private String			imagePropertyLink;
	private int				statusCodeHttpGet;
	private HttpClient		httpGetClient;
	private HttpGet			httpGet;
	private HttpResponse	httpGetResponse;
	private Bitmap			retValue;
	
	public GetEntityImageByImageLink(String imagePropertyLink, int width,
			int height, int radius, String backgroundColor, String appName,
			String apiKey, IImageCallback callback) {
	
		this.width = width;
		this.height = height;
		this.radius = radius;
		this.imagePropertyLink = imagePropertyLink;
		this.backgroundColor = backgroundColor;
		this.callback = callback;
		this.appName = appName;
		this.apiKey = apiKey;
		
	}
	
	@Override
	protected Void doInBackground(Void... params) {
	
		try {
			if (checkParameters()) {
				
				setBackgroundColorDefaultValue();
				formatGetURL();
				setHttpGetVariablesAndExecuteRequest();
				
				if (NetworkHelper.isStatusCode200(statusCodeHttpGet)) {
					
					setRetValue();
					
				}
				else if (NetworkHelper.isStatusCode403Or404(statusCodeHttpGet)) {
					errorMessage = EntityUtils.toString(
							httpGetResponse.getEntity(), Constants.UTF_STRING);
				}
				else if (NetworkHelper.isStatusCode500(statusCodeHttpGet)) {
					errorMessage = EntityUtils.toString(
							httpGetResponse.getEntity(), Constants.UTF_STRING);
				}
				else {
					errorMessage = "StatusCode Get-Response is "
							+ statusCodeHttpGet + ".";
					statusCodeHttpGet = -1;
				}
				
			}
			else {
				statusCodeHttpGet = -1;
				errorMessage = "GET-Request not possible: invalid id, type, classType, appName or apiKey (0, null, empty)!";
			}
		}
		catch (ClientProtocolException e) {
			Log.e(Constants.TAG_ORCASDK,
					"ClientProtocolException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "ClientProtocolException " + e.getMessage();
			statusCodeHttpGet = -1;
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
		
		return null;
	}
	
	private void setRetValue() throws IOException {
	
		retValue = BitmapFactory.decodeStream(httpGetResponse.getEntity()
				.getContent());
	}
	
	private void setHttpGetVariablesAndExecuteRequest() throws IOException,
			ClientProtocolException {
	
		httpGetClient = new DefaultHttpClient();
		httpGet = new HttpGet(getUrl);
		httpGet.addHeader(Constants.APIKEYHEADER, apiKey);
		httpGetResponse = httpGetClient.execute(httpGet);
		statusCodeHttpGet = httpGetResponse.getStatusLine().getStatusCode();
	}
	
	private void formatGetURL() {
	
		getUrl = imagePropertyLink;
		getUrl = getUrl.replace(Constants.WIDTH, String.valueOf(width));
		getUrl = getUrl.replace(Constants.HEIGHT, String.valueOf(height));
		getUrl = getUrl.replace(Constants.RADIUS, String.valueOf(radius));
		getUrl = getUrl.replace(Constants.BGCOLOR, backgroundColor);
	}
	
	private void setBackgroundColorDefaultValue() {
	
		if (backgroundColor == null || backgroundColor.isEmpty()
				|| backgroundColor.length() != 8) {
			backgroundColor = "00000000";
		}
	}
	
	private boolean checkParameters() {
	
		return imagePropertyLink != null && !imagePropertyLink.isEmpty()
				&& appName != null && !appName.isEmpty() && apiKey != null
				&& !apiKey.isEmpty() && width > 0 && height > 0 && radius >= 0;
	}
	
	@Override
	protected void onPostExecute(Void result) {
	
		if (callback != null) {
			
			callback.onComplete(retValue, statusCodeHttpGet, errorMessage);
		}
	}
}
