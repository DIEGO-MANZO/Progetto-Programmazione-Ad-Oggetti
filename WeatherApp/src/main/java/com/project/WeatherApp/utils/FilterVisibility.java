package com.project.WeatherApp.utils;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class FilterVisibility implements FilterStats{
	
	Statistics statistic = new Statistics();
	
	public JSONArray oneDay (ArrayList<String> cities, String value) {
		
		JSONArray array = new JSONArray();
		
		ArrayList<JSONObject> average = new ArrayList<JSONObject>();
		ArrayList<JSONObject> averageVisibility = new ArrayList<JSONObject>();
		ArrayList<JSONObject> objects = new ArrayList<JSONObject>();
		
		Iterator<String> it = cities.iterator();
		
		//String name = "";
		int i = 0;
		
		double request1max = 0;
		double request1min = 100000;
		double request2max = 0;
		double request2min = 100000;
		double request3max = 0;
		double request3min = 100000;
		double request4max = 0;
		double request4min = 100000;
		
		while(it.hasNext()) {
			JSONObject object = new JSONObject();
			object = statistic.todayAverage(it.next());
			average.add(object);
			JSONObject visibilitydata = object.getJSONObject("Visibility Data");
			averageVisibility.add(visibilitydata);
			
			double max_visibility = visibilitydata.getDouble("Max visibility");
			double min_visibility = visibilitydata.getDouble("Min Visibility");
			double visibility_average = visibilitydata.getDouble("Visibility average");
			double visibility_variance = visibilitydata.getDouble("Visibility variance");
			
			JSONObject obj = new JSONObject();
			obj.put("cityName",cities.get(i));
			obj.put("Visibility",visibilitydata);
			objects.add(obj);
			array.put(obj);
			
			if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
				
					if(max_visibility>=request1max) {
						request1max = max_visibility;
						//name += cities.get(i) + " ";
					}
					if(min_visibility>=request2max) {
						request2max = min_visibility;
						//name += cities.get(i) + " ";
					}
					if(visibility_average>=request3max) {
						request3max = visibility_average;
						//name += cities.get(i) + " ";
					}
					if(visibility_variance>=request4max) {
						request4max = visibility_variance;
						//name += cities.get(i) + " ";
					}
					i++;
				
			}
			else if(value.equals("min") || value.equals("MIN") || value.equals("Min")) {
				
				if(max_visibility<=request1min) {
					request1min = max_visibility;
					//name += cities.get(i) + " ";
				}
				if(min_visibility<=request2min) {
					request2min = min_visibility;
					//name += cities.get(i) + " ";
				}
				if(visibility_average<=request3min) {
					request3min = visibility_average;
					//name += cities.get(i) + " ";
				}
				if(visibility_variance<=request4min) {
					request4min = visibility_variance;
					//name += cities.get(i) + " ";
				}
					i++;
			}
				
		}
		
		JSONObject object = new JSONObject();
		
		if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
			//object.put("City with max average", name);
			object.put("max max visibility", request1max);
			object.put("max min visibility", request2max);
			object.put("max visibility average", request3max);
			object.put("max variance average", request4max);
		}
		else { 
			//object.put("City with min average", name);
			object.put("min max visibility", request1min);
			object.put("min min visibility", request2min);
			object.put("min visibility average", request3min);
			object.put("min variance average", request4min);
		}
		
		
		array.put(object);
		
		return array;
	}
	
	public JSONArray fiveDay (ArrayList<String> cities, String value) {
		
		JSONArray array = new JSONArray();
		
		ArrayList<JSONObject> average = new ArrayList<JSONObject>();
		ArrayList<JSONObject> averageVisibility = new ArrayList<JSONObject>();
		ArrayList<JSONObject> objects = new ArrayList<JSONObject>();
		
		Iterator<String> it = cities.iterator();
		
		//String name = "";
		int i = 0;
		
		double request1max = 0;
		double request1min = 100000;
		double request2max = 0;
		double request2min = 100000;
		double request3max = 0;
		double request3min = 100000;
		double request4max = 0;
		double request4min = 100000;
		
		while(it.hasNext()) {
			JSONObject object = new JSONObject();
			object = statistic.fiveDayAverage(it.next());
			average.add(object);
			JSONObject visibilitydata = object.getJSONObject("Visibility Data");
			averageVisibility.add(visibilitydata);
			
			double max_visibility = visibilitydata.getDouble("Max visibility");
			double min_visibility = visibilitydata.getDouble("Min Visibility");
			double visibility_average = visibilitydata.getDouble("Visibility average");
			double visibility_variance = visibilitydata.getDouble("Visibility variance");
			
			JSONObject obj = new JSONObject();
			obj.put("cityName",cities.get(i));
			obj.put("Visibility",visibilitydata);
			objects.add(obj);
			array.put(obj);
			
			if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
				
					if(max_visibility>=request1max) {
						request1max = max_visibility;
						//name += cities.get(i) + " ";
					}
					if(min_visibility>=request2max) {
						request2max = min_visibility;
						//name += cities.get(i) + " ";
					}
					if(visibility_average>=request3max) {
						request3max = visibility_average;
						//name += cities.get(i) + " ";
					}
					if(visibility_variance>=request4max) {
						request4max = visibility_variance;
						//name += cities.get(i) + " ";
					}
					i++;
				
			}
			else if(value.equals("min") || value.equals("MIN") || value.equals("Min")) {
				
				if(max_visibility<=request1min) {
					request1min = max_visibility;
					//name += cities.get(i) + " ";
				}
				if(min_visibility<=request2min) {
					request2min = min_visibility;
					//name += cities.get(i) + " ";
				}
				if(visibility_average<=request3min) {
					request3min = visibility_average;
					//name += cities.get(i) + " ";
				}
				if(visibility_variance<=request4min) {
					request4min = visibility_variance;
					//name += cities.get(i) + " ";
				}
					i++;
			}
				
		}
		
		JSONObject object = new JSONObject();
		
		if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
			//object.put("City with max average", name);
			object.put("max max visibility", request1max);
			object.put("max min visibility", request2max);
			object.put("max visibility average", request3max);
			object.put("max variance average", request4max);
		}
		else { 
			//object.put("City with min average", name);
			object.put("min max visibility", request1min);
			object.put("min min visibility", request2min);
			object.put("min visibility average", request3min);
			object.put("min variance average", request4min);
		}
		
		
		array.put(object);
		
		
		
		
		return array;
		
		
	}
	
	
}
