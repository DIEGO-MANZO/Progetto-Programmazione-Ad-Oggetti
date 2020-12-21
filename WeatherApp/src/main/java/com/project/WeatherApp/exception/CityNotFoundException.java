package com.project.WeatherApp.exception;

/**
 * Questa classe  contiene il metodo che segnala l'eccezione riguardante l'errato inserimento del nome di una città. 
 * @author Federica Parlapiano
 * @author Francesca Palazzetti
 *
 */

public class CityNotFoundException extends Exception {
	
	String mex;
	
	/**
	 * Questo è il costruttore.
	 * @param mex rappresenta il messaggio di errore.
	 */
	
	public CityNotFoundException(String mex) {
		super();
		this.mex = mex;
	}
	
	/**
	 * Restituisce un messaggio di errore passato dal costruttore quando il nome della città non è stato trovato.
	 * @return String che contiene il messaggio d'errore che viene stampato.
	 */
	public String getMex() {
		return mex;
	}

}
