
package de.fhws.sdk.orca.network.callback;

import java.util.ArrayList;

/**
 * Callback-Interface, wird für AsyncClass-Klassen verwendet, die die im Backend
 * vorhandenen Entities abfragen, und deren Entity-Ids als ArrayList zurückgibt.
 * 
 * @author ThomasDeinlein
 */
public interface IAllEntityIdsCallback {
	
	void onComplete(ArrayList<Integer> entityIdsList, int statusCode,
			String errorMessage);
}
