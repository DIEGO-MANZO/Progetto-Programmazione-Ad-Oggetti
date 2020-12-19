package com.project.WeatherApp.exception;

/**
 * Questa classe  contiene il metodo che segnala l'eccezione riguardante un numero non concesso del periodo. 
 * @author Federica Parlapiano
 * @author Francesca Palazzetti
 *
 */
public class WrongPeriodException extends Exception{
	
	String mex;
	/**
	 * Questo è il costruttore.
	 * @param mex rappresenta il messaggio di errore.
	 */
	public WrongPeriodException(String mex) {
		
		this.mex = mex;
	}
	
	/**
	 * Restituisce un messaggio di errore passato al costruttore quando viene inserito un numero non ammesso.
	 * @return String che contiene il messaggio d'errore che viene stampato.
	 */
	public String getMex() {
		return mex;
	}
}
