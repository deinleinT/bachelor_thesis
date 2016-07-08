
package de.fhws.sdk.orca.network.callback;

import android.graphics.Bitmap;

public interface IImageCallback {
	
	void onComplete(Bitmap loadedBitmap, int statusCode, String errorMessage);
}
