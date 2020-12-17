package com.project.WeatherApp.service;

import com.project.WeatherApp.model.*;
import org.json.JSONObject;
import org.json.JSONArray;

public class ToJSON {
	
	City city = new City();
	
	public JSONObject parser(City city) {
		
		
		JSONObject object = new JSONObject();
		
		object.put("name", city.getName());
		object.put("country", city.getCountry());
		object.put("id", city.getId());
		JSONObject coordinates = new JSONObject();
		coordinates.put("latitude", (city.getCoordinates()).getLatitude());
		coordinates.put("longitude", (city.getCoordinates()).getLongitude());
		object.put("coordinates", coordinates);
		
		JSONArray arr = new JSONArray();
		
		for(int i=0; i<(city.getVector()).size(); i++) {
			JSONObject weather = new JSONObject();
			weather.put("data", (city.getVector()).get(i).getData());
			weather.put("main", (city.getVector()).get(i).getMain());
			weather.put("description", (city.getVector()).get(i).getDescription());
			weather.put("visibility", (city.getVector()).get(i).getVisibility());
			weather.put("temp_max", (city.getVector()).get(i).getTemp_max());
			weather.put("temp_min", (city.getVector()).get(i).getTemp_min());
			weather.put("feels_like", (city.getVector()).get(i).getFeels_like());
			arr.put(weather);
		}
		
		
		object.put("Weather", arr);
		
		return object;
	}
	
	
}
