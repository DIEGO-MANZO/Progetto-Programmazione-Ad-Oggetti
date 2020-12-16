/**
 * 
 */
package com.project.WeatherApp.service;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import org.json.JSONArray;
import org.json.simple.parser.*;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import com.project.WeatherApp.model.*;


/**
 * @author Federica
 * @author Francesca
 */

@Service

public class ServiceImpl implements com.project.WeatherApp.service.Service {
	
	private String api_key = "666efac3e1caf3f728f8c5860edeb469";
	
	
	//va a prendere le previsioni meteo di una città
	public JSONObject getCityWeather(String city) {
		
		JSONObject obj;
		String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid="+api_key;
		
		RestTemplate rt = new RestTemplate();
		
		obj = new JSONObject(rt.getForObject(url, String.class));
		
		
		return obj;
		
	}
	
	
	

	public JSONArray getVisibilityfromApi(String name) {
	
		JSONObject object = getCityWeather(name);
		JSONArray toGive = new JSONArray();
			
			JSONArray weatherArray = object.getJSONArray("list");
			JSONObject support;
			int visibility;
			String data;
			
			for (int i = 0; i<weatherArray.length(); i++) {
				
				support = weatherArray.getJSONObject(i);
				visibility = (int) support.get("visibility");
				data = (String) support.get("dt_txt");
				JSONObject toReturn = new JSONObject();
				toReturn.put("Visibility", visibility);
				toReturn.put("Data", data);
				toGive.put(toReturn);
				
			}
	
		
		return toGive;
		
	}
	
	
	public City getCityWeatherRistrictfromApi(String name) {
		
		JSONObject object = getCityWeather(name);
		
		City city = new City(name);
		
		city = getCityInfofromApi(name);
		
		Weather weather = new Weather();
		
		JSONArray weatherArray = object.getJSONArray("list");
		JSONObject counter;
		
		Weather[] vector = new Weather[weatherArray.length()];
		
		
		try {
			
			
			for (int i = 0; i<weatherArray.length(); i++) {
				
				counter = weatherArray.getJSONObject(i);
				weather.setVisibility(counter.getInt("visibility"));
				weather.setData(counter.getString("dt_txt"));
				JSONArray arrayW = counter.getJSONArray("weather");
				JSONObject objectW = arrayW.getJSONObject(0);
				weather.setDescription(objectW.getString("description"));
				weather.setMain(objectW.getString("main"));
				JSONObject objectW2 = counter.getJSONObject("main");
				weather.setTemp_max(objectW2.getDouble("temp_max"));
				weather.setTemp_min(objectW2.getDouble("temp_min"));
				weather.setFeels_like(objectW2.getDouble("feels_like"));
				vector[i] = new Weather();
				vector[i].setData(weather.getData());
				vector[i].setVisibility(weather.getVisibility());
				vector[i].setTemp_max(weather.getTemp_max());
				vector[i].setTemp_min(weather.getTemp_min());
				vector[i].setFeels_like(weather.getFeels_like());
				vector[i].setDescription(weather.getDescription());
				vector[i].setMain(weather.getMain());
			}
	
		} catch(Exception e) {
		}
		
		
		city.setVector(vector);
		
		
		
		return city;
		
	}
	
	
	public City getCityInfofromApi(String name) {
		
		JSONObject object = getCityWeather(name);
		
		City city = new City(name);
		
		try {
			
			JSONObject cityObj = object.getJSONObject("city");
			String country = (String) cityObj.get("country");
			int id = (int) cityObj.get("id");
			JSONObject coordinatesObj = cityObj.getJSONObject("coord");
			double latitude = (double) coordinatesObj.get("lat");
			double longitude = (double) coordinatesObj.get("lon");
			Coordinates coordinates = new Coordinates(latitude,longitude); 
			city.setCountry(country);
			city.setId(id);
			city.setCoordinates(coordinates);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return city;
		
	}
	
	
}
