package com.project.WeatherApp.utils;

import java.util.ArrayList;

import org.json.JSONArray;

import com.project.WeatherApp.exception.CityNotFoundException;
import com.project.WeatherApp.exception.WrongValueException;

/**
 * Questa è l'interfaccia contenente i metodi per il filtraggio di value. 
 * @author Federica Parlapiano
 * @author Francesca Palazzetti 
 */

public interface FilterStats {
	
	public abstract JSONArray oneDay(ArrayList<String> cities, String value) throws WrongValueException;
	public abstract JSONArray fiveDay(ArrayList<String> cities, String value) throws WrongValueException;

}
