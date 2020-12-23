package com.project.WeatherApp.service;

import com.project.WeatherApp.exception.CityNotFoundException;
import com.project.WeatherApp.exception.EmptyStringException;
import com.project.WeatherApp.exception.WrongPeriodException;
import com.project.WeatherApp.exception.WrongValueException;
import com.project.WeatherApp.model.*;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

/** Questa classe è l'interfaccia di ServiceImpl e contiene i metodi richiamati dal Controller.
 * @author Federica Parlapiano
 * @author Francesca Palazzetti 
 */
public interface Service {
	
	public abstract JSONObject getCityWeather(String city);
	public abstract City getCityInfofromApi(String city);
	public abstract JSONArray getVisibilityfromApi(String city);
	public abstract City getCityWeatherRistrictfromApi(String city);
	public abstract String save(String city) throws IOException;
	public String saveEveryHour(String cityName);
	public abstract ArrayList<JSONObject> readHistoryError(ArrayList<String> cities,int error,String value,int period) throws EmptyStringException, CityNotFoundException, WrongPeriodException, WrongValueException, IOException;
	public abstract ArrayList<JSONArray> readVisibilityHistory(ArrayList<String> cities,String period) throws EmptyStringException, CityNotFoundException, WrongPeriodException, IOException;
}
