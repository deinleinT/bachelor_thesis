
package de.fhws.sdk.orca.network.callback;

public interface IPropertyCallback {
	
	/**
	 * Diese Methode wird aufgerufen, wenn der Zugriff zum Backend abgeschlossen
	 * ist.
	 * 
	 * @param returnValue Ist das geladene Property-Object. Evtl. ist ein Cast
	 *            auf den richtigen Objekttyp notwendig
	 * @param statusCode Dies ist der HttpStatusCode. Falls beim Zugriff auf das
	 *            Backend eine Exception auftritt oder ein anderer Fehler, dann
	 *            wird der Status auf -1 gesetzt --> sollte immer geprueft
	 *            werden
	 * @param errorMessage Die Fehlernachricht, die den entsprechenden Fehler
	 *            beschreibt. Wenn kein Fehler auftritt erscheint "no error".
	 */
	void onComplete(Object returnValue, int statusCode, String errorMessage);
	
}
