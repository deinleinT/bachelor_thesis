
package de.fhws.sdk.orca.network.asynctask.image.put;

import java.io.InputStream;

import org.apache.http.entity.InputStreamEntity;

import android.content.Context;

/**
 * Klasse für die Verarbeitung von Images mit dem Backend. Von der manuellen
 * Erstellung ist abzusehen.
 * 
 * @author ThomasDeinlein
 */
public class Image {
	
	private InputStreamEntity	inputStreamEntity;
	private InputStream			inputStream;
	private int					ressourceValueOfPicture;
	private Context				context;
	
	public Image(Context context, int ressourceValueOfPicture) {
	
		this.context = context;
		this.ressourceValueOfPicture = ressourceValueOfPicture;
		this.inputStream = (context.getResources()
				.openRawResource(ressourceValueOfPicture));
		this.inputStreamEntity = new InputStreamEntity(inputStream, -1);
	}
	
	public Image(Context context, InputStream inputStream) {
	
		this.context = context;
		this.inputStream = inputStream;
		this.inputStreamEntity = new InputStreamEntity(inputStream, -1);
	}
	
	int getRessourceValueOfPicture() {
	
		return ressourceValueOfPicture;
	}
	
	void setRessourceValueOfPicture(int ressourceValueOfPicture) {
	
		this.ressourceValueOfPicture = ressourceValueOfPicture;
	}
	
	InputStream getInputStream() {
	
		return inputStream;
	}
	
	void setInputStream(InputStream inputStream) {
	
		this.inputStream = inputStream;
	}
	
	InputStreamEntity getInputStreamEntity() {
	
		return inputStreamEntity;
	}
	
	void setInputStreamEntity(InputStreamEntity inputStreamEntity) {
	
		this.inputStreamEntity = inputStreamEntity;
	}
	
	Context getContext() {
	
		return context;
	}
	
	void setContext(Context context) {
	
		this.context = context;
	}
}
