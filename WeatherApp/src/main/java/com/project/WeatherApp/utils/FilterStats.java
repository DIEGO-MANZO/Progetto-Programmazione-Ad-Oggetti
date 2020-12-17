package com.project.WeatherApp.utils;

import java.util.ArrayList;

import org.json.JSONArray;

public interface FilterStats {
	
	public abstract JSONArray oneDay(ArrayList<String> cities, String value);
	public abstract JSONArray fiveDay(ArrayList<String> cities, String value);

}
