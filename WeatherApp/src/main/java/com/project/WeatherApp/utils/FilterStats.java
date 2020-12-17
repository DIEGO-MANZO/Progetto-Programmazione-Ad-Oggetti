package com.project.WeatherApp.utils;

import org.json.JSONArray;

public interface FilterStats {
	
	public abstract JSONArray oneDay(String name1, String name2, String name3, String value);
	public abstract JSONArray fiveDay(String name1, String name2, String name3, String value);

}
