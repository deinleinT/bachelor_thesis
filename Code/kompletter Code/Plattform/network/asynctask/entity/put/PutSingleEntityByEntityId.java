
package de.fhws.sdk.orca.network.asynctask.entity.put;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
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
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.network.callback.INoReturnValueCallback;

/**
 * AsyncTaskKlasse: Ist für das Updaten einer Entity im Backend gedacht.
 * BACKEND-ENDPUNKT: /api/{appname}/entities/{id}
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class PutSingleEntityByEntityId<T extends Entity> extends
		AsyncTask<Void, Void, T> {
	
	private String					putUrl;
	private String					appName;
	private String					apiKey;
	private INoReturnValueCallback	callback;
	private int						entityIdOfOriginalEntity;
	private T						newEntity;
	private int						statusCodeHttpPut;
	private String					errorMessage	= Constants.NO_ERROR;
	private HttpClient				httpPutClient;
	private HttpPut					httpPut;
	private HttpResponse			httpDeleteResponse;
	
	public PutSingleEntityByEntityId(int entityIdOfOriginalEntity, T newEntity,
			String appName, String apiKey, INoReturnValueCallback callback) {
	
		this.entityIdOfOriginalEntity = entityIdOfOriginalEntity;
		this.newEntity = newEntity;
		this.callback = callback;
		this.apiKey = apiKey;
		this.appName = appName;
	}
	
	@Override
	protected T doInBackground(Void... params) {
	
		try {
			if (checkParameters()) {
				
				JSONHelper.setPropsToCorrectFormatBeforeSendToBackend(newEntity
						.getProperties());
				setPutURL();
				setHttpPutVariablesAndHeadersAndExecuteRequest();
				
				if (NetworkHelper.isStatusCode204(statusCodeHttpPut)) {
					setCorrectFormatToProperties();
				}
				else if (NetworkHelper.isStatusCode403Or404(statusCodeHttpPut)) {
					
					errorMessage = EntityUtils.toString(
							httpDeleteResponse.getEntity(),
							Constants.UTF_STRING);
				}
				else {
					errorMessage = "Response-StatusCode is "
							+ statusCodeHttpPut + ".";
					statusCodeHttpPut = -1;
				}
			}
			else {
				statusCodeHttpPut = -1;
				errorMessage = "Put-Request not possible: invalid entityId, entityProperties, appName or apiKey (id==0, null or empty)";
			}
		}
		catch (ParseException e) {
			Log.e(Constants.TAG_ORCASDK, "ParseException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "ParseException " + e.getMessage();
			statusCodeHttpPut = -1;
		}
		catch (IOException e) {
			Log.e(Constants.TAG_ORCASDK, "IOException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "IOException " + e.getMessage();
			statusCodeHttpPut = -1;
		}
		catch (NullPointerException e) {
			Log.e(Constants.TAG_ORCASDK,
					"NullPointerException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "NullPointerException " + e.getMessage();
			statusCodeHttpPut = -1;
		}
		catch (Exception e) {
			Log.e(Constants.TAG_ORCASDK, "Exception " + e.getMessage());
			e.printStackTrace();
			errorMessage = "Exception " + e.getMessage();
			statusCodeHttpPut = -1;
		}
		
		return newEntity;
	}
	
	private void setCorrectFormatToProperties() throws ParseException {
	
		JSONHelper.setPropertiesToCorrectDatatypesFromResponseEntity(newEntity
				.getProperties());
	}
	
	private void setHttpPutVariablesAndHeadersAndExecuteRequest()
			throws UnsupportedEncodingException, IOException,
			ClientProtocolException {
	
		httpPutClient = new DefaultHttpClient();
		httpPut = new HttpPut(putUrl);
		httpPut.addHeader(Constants.APIKEYHEADER, apiKey);
		httpPut.setHeader(Constants.CONTENT_TYPE,
				Constants.CONTENT_TYPE_VALUE_JSON);
		Genson genson = new GensonBuilder().exclude(Constants.ENTITY_ENTITYID)
				.exclude(Constants.ENTITY_SELF_URL).create();
		String string = genson.serialize(newEntity);
		httpPut.setEntity(new StringEntity(string, HTTP.UTF_8));
		httpDeleteResponse = httpPutClient.execute(httpPut);
		statusCodeHttpPut = httpDeleteResponse.getStatusLine().getStatusCode();
	}
	
	private void setPutURL() throws UnsupportedEncodingException {
	
		putUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES + "/" + entityIdOfOriginalEntity;
	}
	
	private boolean checkParameters() {
	
		return entityIdOfOriginalEntity > 0 && appName != null
				&& !appName.isEmpty() && apiKey != null && !apiKey.isEmpty()
				&& newEntity != null && newEntity.getProperties() != null
				&& newEntity.getProperties().size() > 0;
	}
	
	@Override
	protected void onPostExecute(T result) {
	
		if (callback != null) {
			
			callback.onComplete(statusCodeHttpPut, errorMessage);
		}
	}
}
