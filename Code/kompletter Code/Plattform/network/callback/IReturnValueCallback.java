
package de.fhws.sdk.orca.network.callback;

import de.fhws.sdk.orca.model.Entity;

public interface IReturnValueCallback<T extends Entity> {
	
	/**
	 * Diese Methode wird aufgerufen, wenn der Zugriff zum Backend abgeschlossen
	 * ist.
	 * 
	 * @param entity Die vom Backend geladene Entity oder null --> sollte
	 *            deshalb immer auf null geprueft werden, bevor darauf
	 *            zugegriffen wird.
	 * @param statusCode Dies ist der HttpStatusCode. Falls beim Zugriff auf das
	 *            Backend eine Exception auftritt oder ein anderer Fehler, dann
	 *            wird der Status auf -1 gesetzt --> sollte immer geprueft
	 *            werden
	 * @param errorMessage Die Fehlernachricht, die den entsprechenden Fehler
	 *            beschreibt. Wenn kein Fehler auftritt erscheint "no error".
	 */
	void onComplete(T entity, int statusCode, String errorMessage);
	
}
