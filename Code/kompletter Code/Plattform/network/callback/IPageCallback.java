
package de.fhws.sdk.orca.network.callback;

import de.fhws.sdk.orca.model.Entity;
import de.fhws.sdk.orca.model.EntityPage;

public interface IPageCallback<T extends Entity> {
	
	void onComplete(EntityPage<T> entityPage, int statusCode,
			String errorMessage);
	
}
