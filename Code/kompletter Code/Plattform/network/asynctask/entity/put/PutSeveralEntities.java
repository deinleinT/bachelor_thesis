
package de.fhws.sdk.orca.network.asynctask.entity.put;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

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
 * AsyncTask-Klasse: Dient für eine Entity-Update im Backend. Hierbei können in
 * einer ArrayList mehrere Entities übergeben werden, die upgedatet werden
 * sollen. Die Entities können vorher mit den Setter-Methoden der
 * EntityProperties bearbeitet werden. Im Backend werden die vorhanden Entities
 * mit den übergebenen ersetzt. Über das Callback wird in der ErrorMessage
 * angezeigt, für welche IDs das Update erfolgreich war und bei welchen es fehl
 * schlug. BACKEND-ENDPUNKT: /api/{appname}/entities/{id}
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class PutSeveralEntities<T extends Entity> extends
		AsyncTask<Void, Void, String> {
	
	private String					putUrl;
	private String					appName;
	private String					apiKey;
	private INoReturnValueCallback	callback;
	private ArrayList<T>			entityList;
	private T						originalEntity;
	private T						newEntity;
	private int						statusCodeHttpPut;
	private HttpClient				httpPutClient;
	private HttpPut					httpPut;
	private HttpResponse			httpDeleteResponse;
	private String					returnString;
	
	public PutSeveralEntities(ArrayList<T> entityList, String appName,
			String apiKey, INoReturnValueCallback callback) {
	
		this.entityList = entityList;
		this.callback = callback;
		this.apiKey = apiKey;
		this.appName = appName;
	}
	
	@Override
	protected String doInBackground(Void... params) {
	
		HashSet<Integer> idsOK = new HashSet<Integer>();
		HashSet<Integer> idsNotOK = new HashSet<Integer>();
		
		for (T entity : entityList) {
			setEntities(entity);
			
			try {
				if (checkParameters()) {
					
					JSONHelper
							.setPropsToCorrectFormatBeforeSendToBackend(newEntity
									.getProperties());
					
					setPutURL();
					initializeHttpPutHeader();
					
					String jsonString = createJSONStringByGenson();
					setHttpPutVariablesAndExecuteRequest(jsonString);
					
					if (NetworkHelper.isStatusCode204(statusCodeHttpPut)) {
						setPropertiesToCorrectFormatAndAddIdToIsOkSet(idsOK);
					}
					else if (NetworkHelper
							.isStatusCode403Or404(statusCodeHttpPut)) {
						
						addIdToIsNotOKSet(idsNotOK);
					}
					else {
						addIdToIsNotOKSet(idsNotOK);
					}
				}
				else {
					addIdToIsNotOKSet(idsNotOK);
				}
			}
			catch (Exception e) {
				Log.e(Constants.TAG_ORCASDK, "Exception " + e.getMessage());
				e.printStackTrace();
			}
		}
		setReturnString(idsOK, idsNotOK);
		
		return returnString;
	}
	
	private void setPutURL() throws UnsupportedEncodingException {
	
		putUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING) + "/"
				+ Constants.ENTITIES + "/" + originalEntity.getId();
	}
	
	private void setEntities(T entity) {
	
		originalEntity = entity;
		newEntity = entity;
	}
	
	private void setReturnString(HashSet<Integer> idsOK,
			HashSet<Integer> idsNotOK) {
	
		returnString = "Entities with the following IDs updatet successfully: "
				+ idsOK.toString()
				+ ". The Saving of the following Ids failed: "
				+ idsNotOK.toString();
	}
	
	private void addIdToIsNotOKSet(HashSet<Integer> idsNotOK) {
	
		idsNotOK.add(originalEntity.getId());
	}
	
	private void setPropertiesToCorrectFormatAndAddIdToIsOkSet(
			HashSet<Integer> idsOK) throws ParseException {
	
		JSONHelper.setPropertiesToCorrectDatatypesFromResponseEntity(newEntity
				.getProperties());
		addIdToIsNotOKSet(idsOK);
	}
	
	private void setHttpPutVariablesAndExecuteRequest(String jsonString)
			throws UnsupportedEncodingException, IOException,
			ClientProtocolException {
	
		httpPut.setEntity(new StringEntity(jsonString, HTTP.UTF_8));
		httpDeleteResponse = httpPutClient.execute(httpPut);
		statusCodeHttpPut = httpDeleteResponse.getStatusLine().getStatusCode();
	}
	
	private String createJSONStringByGenson() {
	
		Genson genson = new GensonBuilder().exclude(Constants.ENTITY_ENTITYID)
				.exclude(Constants.ENTITY_SELF_URL).create();
		String string = genson.serialize(newEntity);
		return string;
	}
	
	private boolean checkParameters() {
	
		return originalEntity.getId() > 0 && originalEntity.getType() > 0
				&& appName != null && !appName.isEmpty() && apiKey != null
				&& !apiKey.isEmpty() && originalEntity != null
				&& originalEntity.getProperties() != null && newEntity != null
				&& newEntity.getProperties() != null;
	}
	
	private void initializeHttpPutHeader() {
	
		httpPutClient = new DefaultHttpClient();
		httpPut = new HttpPut(putUrl);
		httpPut.addHeader(Constants.APIKEYHEADER, apiKey);
		httpPut.setHeader(Constants.CONTENT_TYPE,
				Constants.CONTENT_TYPE_VALUE_JSON);
	}
	
	@Override
	protected void onPostExecute(String result) {
	
		if (callback != null) {
			
			callback.onComplete(statusCodeHttpPut, result);
		}
	}
}
