package com.project.WeatherApp.utils;

import java.util.ArrayList;

import org.json.JSONArray;

import com.project.WeatherApp.exception.WrongParamException;
import com.project.WeatherApp.exception.WrongPeriodException;
import com.project.WeatherApp.exception.WrongValueException;

/** Questa classe contiene i metodi necessari al filtraggio.
 *  @author Federica Parlapiano
 * @author Francesca Palazzetti 
 */

public class Filter {
	
	private ArrayList<String> cities = new ArrayList<String>();
	private String param;
	private String value;
	private int period;
	
   /**
    *  Questo è il costruttore della classe.
    * @param cities è un ArrayList di Stringhe contenenti i nomi delle città che si vogliono filtrare.
    * @param param parametro per cui si vuole effettuare il filtraggio.
    * @param value valore max o min di param.
    * @param period periodo in cui si vuole fare la statistica.
    */
	
	public Filter(ArrayList<String> cities, String param, String value, int period) {
		super();
		this.cities = cities;
		this.param = param;
		this.value = value;
		this.period = period;
	}

	/**
	 * Questo metodo filtra il periodo e il parametro. Richiama altri metodi per filtrare il value.
	 * @return JSONArray contenente le città filtrate e le statistiche ottenute.
	 * @throws WrongPeriodException se il numero inserito non è 1 o 5.
	 * @throws WrongValueException se è stato inserita una stringa errata per value.
	 * @throws WrongParamException se è stato inserita una stringa errata per param.
	 */
	
	public JSONArray analyze() throws WrongPeriodException, WrongValueException, WrongParamException {
		
		JSONArray array = new JSONArray ();
		
		if(period==1) {
			if(param.equals("temp_max")) {
				FilterTempMax filter = new FilterTempMax();
				array = filter.oneDay(cities, value);
			} 
			else if (param.equals("temp_min")) {
				FilterTempMin filter = new FilterTempMin();
				array = filter.oneDay(cities, value);
			}
			else if (param.equals("feels_like")) {
				FilterFeelsLike filter = new FilterFeelsLike();
				array = filter.oneDay(cities, value);
			}
			else if (param.equals("visibility")) {
				FilterVisibility filter = new FilterVisibility();
				array = filter.oneDay(cities, value);
			}
			else  throw new WrongParamException (param+ " non è una stringa ammessa.Inserisci una stringa tra temp_min,temp_max,feels_like e visibility");   
						
		}
		
		else if(period==5) {
			if(param.equals("temp_max")) {
				FilterTempMax filter = new FilterTempMax();
				array = filter.fiveDay(cities, value);
			}
			else if (param.equals("temp_min")) {
				FilterTempMin filter = new FilterTempMin();
				array = filter.fiveDay(cities, value);
			}
			else if (param.equals("feels_like")) {
				FilterFeelsLike filter = new FilterFeelsLike();
				array = filter.fiveDay(cities, value);
			}
			else if (param.equals("visibility")) {
				FilterVisibility filter = new FilterVisibility();
				array = filter.fiveDay(cities, value);
			}
			else  throw new WrongParamException (param+ " non è una stringa ammessa.Inserisci una stringa tra temp_min,temp_max,feels_like e visibility");
						
		}
		else  throw new WrongPeriodException (period+ " non è un numero ammesso. Inserisci un numero che sia o 1 o 5.");
		
		return array;
	}
	
	
	
	
	
	
}
