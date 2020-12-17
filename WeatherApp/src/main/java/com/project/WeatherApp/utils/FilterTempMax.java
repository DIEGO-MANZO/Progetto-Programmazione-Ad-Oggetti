package com.project.WeatherApp.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;


public class FilterTempMax implements FilterStats {
	
	Statistics statistic = new Statistics();
	
	public JSONArray oneDay (ArrayList<String> cities, String value) {
		
		JSONArray array = new JSONArray();
		
		ArrayList<JSONObject> average = new ArrayList<JSONObject>();
		ArrayList<Double> averageTempMax = new ArrayList<Double>();
		ArrayList<JSONObject> objects = new ArrayList<JSONObject>();
		
		Iterator<String> it = cities.iterator();
		
		double request1 = 0;
		double request2 = 1000;
		//String name = "";
		int i = 0;
		
		while(it.hasNext()) {
			JSONObject object = new JSONObject();
			object = statistic.todayAverage(it.next());
			average.add(object);
			double ave = object.getDouble("Temp_Max Average");
			averageTempMax.add(ave);
			
			JSONObject obj = new JSONObject();
			obj.put("cityName:",cities.get(i));
			obj.put("temp_max_average:",ave);
			objects.add(obj);
			array.put(obj);
			
			if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
				
					if(ave>=request1) {
						request1 = ave;
						//name += cities.get(i) + " ";
					}
					i++;
				
			}
			else if(value.equals("min") || value.equals("MIN") || value.equals("Min")) {
				
					if(ave<=request2) {
						request2 = ave;
						//name += cities.get(i) + " ";
					}
					i++;
			}
				
		}
		
		JSONObject object = new JSONObject();
		
		if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
			//object.put("City with max average", name);
			object.put("max average", request1);
		}
		else { 
			//object.put("City with min average", name);
			object.put("min average", request2);
		}
		
		
		array.put(object);
	
		
		return array;
		
	}
	
	
	public JSONArray fiveDay (ArrayList<String> cities, String value) {
		
		JSONArray array = new JSONArray();
		
		ArrayList<JSONObject> average = new ArrayList<JSONObject>();
		ArrayList<Double> averageTempMax = new ArrayList<Double>();
		ArrayList<JSONObject> objects = new ArrayList<JSONObject>();
		
		Iterator<String> it = cities.iterator();
		
		double request1 = 0;
		double request2 = 1000;
		//String name = "";
		int i = 0;
		
		while(it.hasNext()) {
			JSONObject object = new JSONObject();
			object = statistic.fiveDayAverage(it.next());
			average.add(object);
			double ave = object.getDouble("Temp_Max Average");
			averageTempMax.add(ave);
			
			JSONObject obj = new JSONObject();
			obj.put("cityName:",cities.get(i));
			obj.put("temp_max_average:",ave);
			objects.add(obj);
			array.put(obj);
			
			if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
				
					if(ave>=request1) {
						request1 = ave;
						//name += cities.get(i) + " ";
					}
					i++;
				
			}
			else if(value.equals("min") || value.equals("MIN") || value.equals("Min")) {
				
					if(ave<=request2) {
						request2 = ave;
						//name += cities.get(i) + " ";
					}
					i++;
			}
				
		}
		
		JSONObject object = new JSONObject();
		
		if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
			//object.put("City with max average", name);
			object.put("max average", request1);
		}
		else { 
			//object.put("City with min average", name);
			object.put("min average", request2);
		}
		
		
		array.put(object);
		
		return array;
		
		
	}
	
	
	
}
