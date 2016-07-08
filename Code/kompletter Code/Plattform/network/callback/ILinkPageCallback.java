
package de.fhws.sdk.orca.network.callback;

import de.fhws.sdk.orca.model.LinkPage;

public interface ILinkPageCallback {
	
	void onComplete(LinkPage linkPage, int statusCode, String errorMessage);
}
