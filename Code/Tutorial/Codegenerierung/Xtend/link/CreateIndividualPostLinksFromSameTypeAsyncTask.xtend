package org.xtext.orcasdk.entitymodel.generator.link

import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity

class CreateIndividualPostLinksFromSameTypeAsyncTask {
	
def static createPostLinkAsyncTask(AndroidEntity androidEntity, String packagename){

'''
package de.fhws.sdk.orca.link;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.ClientProtocolException;
import de.fhws.sdk.orca.helper.JSONHelper;
import org.apache.http.protocol.HTTP;
import android.os.AsyncTask;
import android.util.Log;
import de.fhws.sdk.orca.helper.NetworkHelper;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import de.fhws.sdk.orca.helper.Constants;
import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.model.Link;
import de.fhws.sdk.orca.network.callback.IPostLinkCallback;

/**
 * AsyncClass-Klasse: Diese Klasse ermöglicht es mehrere Links auf einmal zu
 * setzen. Die ObjectEntities sind als HashSet zu übergeben. Diese Klasse setzt
 * für Entities der Klasse «androidEntity.name.toFirstUpper» mehrere Links zu ObjectEntities.
 * BACKEND-ENDPUNKT: /api/{appname}/entities/{id}/links/{type}/{objectId}
 * 
 * @author ThomasDeinlein
 * @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}
 *            ableitet
 */
public class PostLinksFromSameType«packagename.replace(".","")»«androidEntity.name.toFirstUpper»<T extends Entity> extends
		AsyncTask<Void, Void, Void> {
	
	private String				errorMessage	= Constants.NO_ERROR;
	private String				saveOkMessage	= "Saving of following ObjectIds was successful: ";
	private String				appName;
	private String				apiKey;
	private IPostLinkCallback	callback;
	private «packagename».«androidEntity.name.toFirstUpper»					entityOne;
	private HashSet<T>			objectEntities;
	private int					statusCodeHttpPost;
	private Link				link;
	private HttpClient			httpPostClient;
	private HttpPost			httpPost;
	private HttpResponse		httpPostResponse;
	private String				postUri;
	private String				ressourceUri;
	private String				postString;
	
	public PostLinksFromSameType«packagename.replace(".","")»«androidEntity.name.toFirstUpper»(Link link, «packagename».«androidEntity.name.toFirstUpper» entityOne,
			HashSet<T> objectEntities, String appName, String apiKey,
			IPostLinkCallback callback) {
	
		this.entityOne = entityOne;
		this.objectEntities = objectEntities;
		this.callback = callback;
		this.link = link;
		this.appName = appName;
		this.apiKey = apiKey;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
	
		try {
			if (checkParameters()) {

				JSONHelper.setPropsToCorrectFormatBeforeSendToBackend(link
						.getProperties());
				createPostString();
				
				for (T objectEntity : objectEntities) {
					
					errorMessage = Constants.ERROR_POST_LINKS_FROM_SAME_TYPE;
					
					if (isObjectEntityFormatOK(objectEntity)) {
						
						setPostURL(objectEntity);
						setHttpPostVariablesAndExecuteRequest();
						
						if (NetworkHelper.isStatusCode201(statusCodeHttpPost)) {
							formatLinksAndMessage(objectEntity);
						}
						else if (NetworkHelper
								.isStatusCode403Or404(statusCodeHttpPost)) {
							errorMessage = "" + objectEntity.getId() + " ";
						}
						else {
							errorMessage = "" + objectEntity.getId() + " ";
							statusCodeHttpPost = -1;
						}
					}
					else {
						errorMessage = errorMessage + " " + " failed. ";
					}
				}
			}
			else {
				statusCodeHttpPost = -1;
				errorMessage = "POST-Request not possible: invalid parameter(s) (0, null or empty)";
			}
		}
		catch (UnsupportedEncodingException e) {
			Log.e(Constants.TAG_BACKEND,
					"UnsupportedEncodingException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "UnsupportedEncodingException " + e.getMessage();
			statusCodeHttpPost = -1;
		}
		catch (ParseException e) {
			Log.e(Constants.TAG_BACKEND, "ParseException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "ParseException " + e.getMessage();
			statusCodeHttpPost = -1;
		}
		catch (IOException e) {
			Log.e(Constants.TAG_BACKEND, "IOException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "IOException " + e.getMessage();
			statusCodeHttpPost = -1;
		}
		catch (NullPointerException e) {
			Log.e(Constants.TAG_BACKEND,
					"NullPointerException " + e.getMessage());
			e.printStackTrace();
			errorMessage = "NullPointerException " + e.getMessage();
			statusCodeHttpPost = -1;
		}
		catch (Exception e) {
			Log.e(Constants.TAG_BACKEND, "Exception " + e.getMessage());
			e.printStackTrace();
			errorMessage = "Exception " + e.getMessage();
			statusCodeHttpPost = -1;
		}
		
		return null;
	}
	
	private boolean checkParameters() {
	
		return entityOne != null && objectEntities != null
				&& entityOne.getId() > 0 && objectEntities.size() > 0
				&& link != null && !link.getType().isEmpty()
				&& appName != null && !appName.isEmpty() && apiKey != null
				&& !apiKey.isEmpty() && entityOne.getType() > 0
				&& link.getProperties().size() > 0;
	}
	
	private void createPostString() {
	
		Genson genson = new GensonBuilder().exclude(Constants.OBJECT).create();
		postString = genson.serialize(link);
	}
	
	private boolean isObjectEntityFormatOK(T objectEntity) {
	
		return objectEntity != null && objectEntity.getId() > 0
				&& objectEntity.getType() > 0;
	}
	
	private void setHttpPostVariablesAndExecuteRequest()
			throws UnsupportedEncodingException, IOException,
			ClientProtocolException {
	
		httpPostClient = new DefaultHttpClient();
		httpPost = new HttpPost(postUri);
		httpPost.addHeader(Constants.APIKEYHEADER, apiKey);
		httpPost.setHeader(Constants.CONTENT_TYPE,
				Constants.CONTENT_TYPE_VALUE_JSON);
		httpPost.setEntity(new StringEntity(postString,
				HTTP.UTF_8));
		httpPostResponse = httpPostClient.execute(httpPost);
		statusCodeHttpPost = httpPostResponse.getStatusLine()
				.getStatusCode();
	}
	
	private void setPostURL(T objectEntity) throws UnsupportedEncodingException {
	
		postUri = Constants.BACKEND_URI
				+ URLEncoder.encode(appName,
						Constants.UTF_STRING)
				+ "/"
				+ Constants.ENTITIES
				+ "/"
				+ entityOne.getId()
				+ "/"
				+ Constants.LINKS
				+ "/"
				+ URLEncoder.encode(link.getType(),
						Constants.UTF_STRING) + "/"
				+ objectEntity.getId();
	}
	
	private void formatLinksAndMessage(T objectEntity) {
	
		ressourceUri = httpPostResponse.getFirstHeader(
				Constants.HEADER_LOCATION).getValue();
		link.setObject(ressourceUri);
		entityOne.getLinks().add(link);
		saveOkMessage = saveOkMessage + " "
				+ objectEntity.getId();
	}
	
	@Override
	protected void onPostExecute(Void result) {
	
		if (callback != null) {
			ressourceUri = "The individual LinkUri has been saved to the Entity LinkList.";
			if(statusCodeHttpPost == 201){
				errorMessage = saveOkMessage;
			}
			callback.onComplete(statusCodeHttpPost, ressourceUri, errorMessage);
		}
	}
}
	'''
}

	
}