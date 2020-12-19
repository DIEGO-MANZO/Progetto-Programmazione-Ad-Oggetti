package com.project.WeatherApp.exception;

/**
 * Questa classe  contiene il metodo che segnala l'eccezione riguardante una stringa non ammessa. 
 * @author Federica Parlapiano
 * @author Francesca Palazzetti
 *
 */
public class WrongParamException extends Exception{
	
	String mex;
	/**
	 * Questo è il costruttore.
	 * @param mex rappresenta il messaggio di errore.
	 */
	public WrongParamException(String mex) {
		
		this.mex = mex;
	}
	
	/**
	 * Restituisce un messaggio di errore passato al costruttore quando viene inserita una stringa non ammessa.
	 * @return String che contiene il messaggio d'errore che viene stampato.
	 */
	public String getMex() {
		return mex;
	}
}
