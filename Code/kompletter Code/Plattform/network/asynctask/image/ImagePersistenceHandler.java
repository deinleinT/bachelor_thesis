
package de.fhws.sdk.orca.network.asynctask.image;

import java.io.InputStream;

import android.content.Context;
import de.fhws.sdk.orca.network.asynctask.image.delete.DeleteEntityImage;
import de.fhws.sdk.orca.network.asynctask.image.get.GetEntityImage;
import de.fhws.sdk.orca.network.asynctask.image.get.GetEntityImageByImageLink;
import de.fhws.sdk.orca.network.asynctask.image.put.Image;
import de.fhws.sdk.orca.network.asynctask.image.put.PutEntityImage;
import de.fhws.sdk.orca.network.callback.IImageCallback;
import de.fhws.sdk.orca.network.callback.INoReturnValueCallback;

public class ImagePersistenceHandler {
	
	/**
	 * * Zum Speichern/Updaten eines Entity-Images per IntRessource --> siehe
	 * {@linkplain de.fhws.sdk.orca.network.asynctask.image.put.PutEntityImage
	 * PutEntityImage}
	 */
	public static void saveOrUpdateEntityImageByIntRessource(int entityId,
			String imagePropertyName, Context context, int ressourceOfImage,
			String appName, String apiKey, INoReturnValueCallback callback) {
	
		Image image = new Image(context, ressourceOfImage);
		
		PutEntityImage put = new PutEntityImage(entityId, imagePropertyName,
				image, appName, apiKey, callback);
		put.execute();
	}
	
	/**
	 * Zum Speichern/Updaten eines Entity-Images per InputStream --> siehe
	 * {@linkplain de.fhws.sdk.orca.network.asynctask.image.put.PutEntityImage
	 * PutEntityImage}
	 */
	public static void saveOrUpdateEntityImageByInputStream(int entityId,
			String imagePropertyName, Context context, InputStream inputStream,
			String appName, String apiKey, INoReturnValueCallback callback) {
	
		Image image = new Image(context, inputStream);
		
		PutEntityImage put = new PutEntityImage(entityId, imagePropertyName,
				image, appName, apiKey, callback);
		put.execute();
	}
	
	/**
	 * Zum Löschen von EntityImages. --> siehe
	 * {@linkplain de.fhws.sdk.orca.network.asynctask.image.delete.DeleteEntityImage
	 * DeleteEntityImage}
	 */
	public static void deleteEntityImage(int entityId,
			String imagePropertyName, String appName, String apiKey,
			INoReturnValueCallback callback) {
	
		DeleteEntityImage delete = new DeleteEntityImage(entityId,
				imagePropertyName, appName, apiKey, callback);
		delete.execute();
	}
	
	/**
	 * Zum Laden eines EntityImages. --> siehe
	 * {@linkplain de.fhws.sdk.orca.network.asynctask.image.get.GetEntityImage
	 * GetEntityImage}
	 */
	public static void loadEntityImage(int entityId, String imagePropertyName,
			int width, int height, int radius, String backgroundColor,
			String appName, String apiKey, IImageCallback callback) {
	
		GetEntityImage get = new GetEntityImage(entityId, imagePropertyName,
				width, height, radius, backgroundColor, appName, apiKey,
				callback);
		get.execute();
	}
	
	/**
	 * Zum Laden eines EntityImages. --> siehe
	 * {@linkplain de.fhws.sdk.orca.network.asynctask.image.get.GetEntityImageByImageLink
	 * GetEntityImageByImageLink}
	 */
	public static void loadEntityImageByImageLink(String imagePropertyLink,
			int width, int height, int radius, String backgroundColor,
			String appName, String apiKey, IImageCallback callback) {
	
		GetEntityImageByImageLink get = new GetEntityImageByImageLink(
				imagePropertyLink, width, height, radius, backgroundColor,
				appName, apiKey, callback);
		get.execute();
	}
	
}
