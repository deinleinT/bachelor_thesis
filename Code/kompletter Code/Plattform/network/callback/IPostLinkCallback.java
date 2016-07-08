
package de.fhws.sdk.orca.network.callback;

public interface IPostLinkCallback {
	
	/**
	 * Diese Methode wird aufgerufen, wenn der Zugriff zum Backend abgeschlossen
	 * ist.
	 * 
	 * @param statusCode Dies ist der HttpStatusCode. Falls beim Zugriff auf das
	 *            Backend eine Exception auftritt oder ein anderer Fehler, dann
	 *            wird der Status auf -1 gesetzt --> sollte immer geprueft
	 *            werden
	 * @param selfURL Die Http-Uri, die auf den gespeicherten Link verweist.
	 * @param errorMessage Die Fehlernachricht, die den entsprechenden Fehler
	 *            beschreibt. Wenn kein Fehler auftritt erscheint "no error".
	 */
	void onComplete(int statusCode, String selfURL, String errorMessage);
	
}
