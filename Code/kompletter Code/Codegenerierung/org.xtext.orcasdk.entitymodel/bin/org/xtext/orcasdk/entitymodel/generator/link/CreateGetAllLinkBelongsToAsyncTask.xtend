package org.xtext.orcasdk.entitymodel.generator.link

import org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute

class CreateGetAllLinkBelongsToAsyncTask {
	

def static CharSequence compilespecialasynctask(AndroidAttribute attribute, String packagename, String androidEntityName){
			
'''
package de.fhws.sdk.orca.link;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.os.AsyncTask;
import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import de.fhws.sdk.orca.helper.*;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.model.EntityPage;
import de.fhws.sdk.orca.network.callback.IEntityPageWithEntityCallback;
import android.util.Log;

/**
 * AsyncClass-Klasse: Führt einen HTTP-Get im Backend durch. Hierüber können
 * alle EntityObjects abgerufen werden, auf die ein Link dieses Typs
 * zeigt. 
 * BACKEND-ENDPUNKT:
 * /api/{appname}/entities/{id}/links/{linkType}/objects
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class GetAll«attribute.objectType.name.toFirstUpper»sFromLink«attribute.name.toFirstUpper»BelongsToClass«packagename.replace(".","")»«androidEntityName.toFirstUpper»<T extends Entity>
		extends AsyncTask<Void, Void, EntityPage<T>> {
	
	private String									getUrl;
	private String									errorMessage	= Constants.NO_ERROR;
	private String									appName;
	private String									apiKey;
	private IEntityPageWithEntityCallback<«packagename».«attribute.objectType.name.toFirstUpper»>	callback;
	private int										statusCodeHttpGet;
	private EntityPage<T>	retValue = new EntityPage<T>();
	private HttpClient								httpGetClient;
	private HttpGet									httpGet;
	private HttpResponse							httpGetResponse;
	private String									responseJSONString;
	private int										entityId;
	private String									linkType;
	private Class<? extends Entity>					classType;
	
	public GetAll«attribute.objectType.name.toFirstUpper»sFromLink«attribute.name.toFirstUpper»BelongsToClass«packagename.replace(".","")»«androidEntityName.toFirstUpper»(int entityId,
			String linkType, Class<? extends Entity> classType, String appName,
			String apiKey, IEntityPageWithEntityCallback<«packagename».«attribute.objectType.name.toFirstUpper»> callback) {
	
		this.callback = callback;
		this.classType = classType;
		this.entityId = entityId;
		this.linkType = linkType;
		this.appName = appName;
		this.apiKey = apiKey;
	}
	
	@Override
	protected EntityPage<T> doInBackground(Void... params) {
	
		try {
			if (checkParameters()) {
				
				setGetURL();
				setHttpClientAndHeader();
				executeHttpResponseAndSetResponseString();
				
				if (NetworkHelper.isStatusCode200(statusCodeHttpGet)) {
					createEntityPage();
					
					if (isPageSizeZero()) {
						errorMessage = Constants.NO_LINKS_FOUND;
						statusCodeHttpGet = -1;
					}
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
		catch (UnsupportedEncodingException e) {
			Log.e(Constants.TAG_BACKEND,
					"UnsupportedEncodingException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "UnsupportedEncodingException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (IOException e) {
			Log.e(Constants.TAG_BACKEND, "IOException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "IOException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (NullPointerException e) {
			Log.e(Constants.TAG_BACKEND,
					"NullPointerException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "NullPointerException " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		catch (Exception e) {
			Log.e(Constants.TAG_BACKEND, "Exception " + e.getMessage());
			e.printStackTrace();
			errorMessage = "Exception " + e.getMessage();
			statusCodeHttpGet = -1;
		}
		
		return retValue;
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
	
	private boolean checkParameters() {
	
		return linkType != null && !linkType.isEmpty() && appName != null
				&& !appName.isEmpty() && apiKey != null
				&& !apiKey.isEmpty() && entityId > 0 && classType != null;
	}
	
	private void setGetURL() throws UnsupportedEncodingException {
	
		getUrl = Constants.BACKEND_URI
				+ URLEncoder.encode(appName, Constants.UTF_STRING)
				+ "/" + Constants.ENTITIES + "/" + entityId + "/"
				+ Constants.LINKS + "/"
				+ URLEncoder.encode(linkType, Constants.UTF_STRING)
				+ "/" + Constants.OBJECTS;
	}
	
	private boolean isPageSizeZero() {
	
		return retValue.getTotalSize() == 0;
	}
	
	private void createEntityPage() {
	
		Genson genson = new Genson();
		retValue = genson.deserialize(responseJSONString,
				new GenericType<EntityPage<T>>() {
				});
	}
	
	private EntityPage<«packagename».«attribute.objectType.name.toFirstUpper»> setCorrectFormatToReturnEntityPage(
			EntityPage<T> result) {
				
			EntityPage<«packagename».«attribute.objectType.name.toFirstUpper»> returnEntityPage = new EntityPage<«packagename».«attribute.objectType.name.toFirstUpper»>();
			returnEntityPage.setTotalSize(result.getTotalSize());
			returnEntityPage.setPageSize(result.getPageSize());
			returnEntityPage.setNavigationLinks(result.getNavigationLinks());
			ArrayList<«packagename».«attribute.objectType.name.toFirstUpper»> entityList = new ArrayList<«packagename».«attribute.objectType.name.toFirstUpper»>();
			
			for(T entity : retValue.getEntities()){
				«packagename».«attribute.objectType.name.toFirstUpper» object = new «packagename».«attribute.objectType.name.toFirstUpper»();
				object.setId(entity.getId());
				object.setLinks(entity.getLinks());
				object.setSelf(entity.getSelf());
				object.setProperties(entity.getProperties());
				entityList.add(object);
			}
			returnEntityPage.setEntities(entityList);
			
			return returnEntityPage;
	}
	
	@Override
	protected void onPostExecute(EntityPage<T> result) {
	
		if (callback != null && result != null) {
			
			EntityPage<«packagename».«attribute.objectType.name.toFirstUpper»> returnPage = setCorrectFormatToReturnEntityPage(result);
			callback.onComplete(returnPage, statusCodeHttpGet, errorMessage);
		}
	}
	
}
			
			'''
			
}


}