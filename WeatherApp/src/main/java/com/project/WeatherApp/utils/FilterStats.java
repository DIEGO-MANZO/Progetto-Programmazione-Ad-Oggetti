package com.project.WeatherApp.utils;

import java.util.ArrayList;

import org.json.JSONArray;

/**
 * Questa è l'interfaccia contenente i metodi per il filtraggio di value. 
 * @author Federica Parlapiano
 * @author Francesca Palazzetti 
 */

public interface FilterStats {
	
	public abstract JSONArray oneDay(ArrayList<String> cities, String value);
	public abstract JSONArray fiveDay(ArrayList<String> cities, String value);

}
