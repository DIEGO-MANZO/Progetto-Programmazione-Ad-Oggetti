/**
 * 
 */
package com.project.WeatherApp.service;

import com.project.WeatherApp.model.*;
import org.json.JSONObject;
import org.json.JSONArray;

import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * @author Federica
 * @author Francesca
 */
public interface Service {
	public abstract JSONObject getCityWeather(String city);
	public abstract City getCityInfofromApi(String city);
	public abstract JSONArray getVisibilityfromApi(String city);
	public abstract City getCityWeatherRistrictfromApi(String city);
}