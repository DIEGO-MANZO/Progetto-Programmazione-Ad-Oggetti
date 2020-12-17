package com.project.WeatherApp.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class FilterFeelsLike implements FilterStats {
	
	Statistics statistic = new Statistics();
	
	public JSONArray oneDay (String name1, String name2, String name3, String value) {
		
		JSONArray array = new JSONArray();
		
		JSONObject city1 = new JSONObject();
		city1 = statistic.todayAverage(name1);
		
		JSONObject city2 = new JSONObject();
		city2 = statistic.todayAverage(name2);
		
		JSONObject city3 = new JSONObject();
		city3 = statistic.todayAverage(name3);
		
		double average1 = city1.getDouble("Feels_like Average");
		double average2 = city2.getDouble("Feels_like Average");
		double average3 = city3.getDouble("Feels_like Average");
		
		double request = 0;
		String name = "";
		
		if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
			if (average1==average2 && average2 == average3) {
				name = name1+", "+name2+" and "+name3;
				request = average1;
			}
			else if (average1 == average2 && average2>average3) {
				name = name1+" and "+name2;
				request = average1;
			}
			else if (average1 == average3 && average1>average2) {
				name = name1+" and "+name3;
				request = average1;
			}
			else if (average2==average3 && average2>average1) {
				name = name2+" and "+name3;
				request = average2;
			}
			else if (average1>=average2 && average1>=average3) {
				request = average1;
				name = name1;
			}
			else if (average2>=average1 && average2>=average3) {
				request = average2;
				name = name2;
			}
			else {
				request = average3;
				name = name3;
			}
		}
		else if (value.equals("min") || value.equals("MIN") || value.equals("Min")) {
			if (average1==average2 && average2 == average3) {
				name = name1+", "+name2+" and "+name3;
				request = average1;
			}
			else if (average1 == average2 && average2<average3) {
				name = name1+" and "+name2;
				request = average1;
			}
			else if (average1 == average3 && average1<average2) {
				name = name1+" and "+name3;
				request = average1;
			}
			else if (average2==average3 && average2<average1) {
				name = name2+" and "+name3;
				request = average2;
			}
			else if (average1<=average2 && average1<=average3) {
				request = average1;
				name = name1;
			}
			else if (average2<=average1 && average2<=average3) {
				request = average2;
				name = name2;
			}
			else  {
				request = average3;
				name = name3;
			}
		}
		else {
			//ECCEZIONE DA INSERIRE.
		}

		city1 = new JSONObject();
		city2 = new JSONObject();
		city3 = new JSONObject();
		
		city1.put("city1", name1);
		city1.put("feels_like_average", average1);
		
		city2.put("city2", name2);
		city2.put("feels_like_average", average2);
		
		city3.put("city3", name3);
		city3.put("feels_like_average", average3);
		
		JSONObject object = new JSONObject();
		
		if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
			object.put("City with max average", name);
			object.put("max average", request);
		}
		else { 
			object.put("City with min average", name);
			object.put("min average", request);
		}
		
		array.put(city1);
		array.put(city2);
		array.put(city3);
		array.put(object);
		
		return array;
		
	}
	
	public JSONArray fiveDay(String name1, String name2, String name3, String value) {
		JSONArray array = new JSONArray ();
		
		JSONObject city1 = new JSONObject();
		city1 = statistic.fiveDayAverage(name1);
		
		JSONObject city2 = new JSONObject();
		city2 = statistic.fiveDayAverage(name2);
		
		JSONObject city3 = new JSONObject();
		city3 = statistic.fiveDayAverage(name3);
		
		double average1 = city1.getDouble("Feels_like Average");
		double average2 = city2.getDouble("Feels_like Average");
		double average3 = city3.getDouble("Feels_like Average");
		
		double request = 0;
		String name = "";
		
		if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
			if (average1==average2 && average2 == average3) {
				name = name1+", "+name2+" and "+name3;
				request = average1;
			}
			else if (average1 == average2 && average2>average3) {
				name = name1+" and "+name2;
				request = average1;
			}
			else if (average1 == average3 && average1>average2) {
				name = name1+" and "+name3;
				request = average1;
			}
			else if (average2==average3 && average2>average1) {
				name = name2+" and "+name3;
				request = average2;
			}
			else if (average1>=average2 && average1>=average3) {
				request = average1;
				name = name1;
			}
			else if (average2>=average1 && average2>=average3) {
				request = average2;
				name = name2;
			}
			else {
				request = average3;
				name = name3;
			}
		}
		else if (value.equals("min") || value.equals("MIN") || value.equals("Min")) {
			if (average1==average2 && average2 == average3) {
				name = name1+", "+name2+" and "+name3;
				request = average1;
			}
			else if (average1 == average2 && average2<average3) {
				name = name1+" and "+name2;
				request = average1;
			}
			else if (average1 == average3 && average1<average2) {
				name = name1+" and "+name3;
				request = average1;
			}
			else if (average2==average3 && average2<average1) {
				name = name2+" and "+name3;
				request = average2;
			}
			else if (average1<=average2 && average1<=average3) {
				request = average1;
				name = name1;
			}
			else if (average2<=average1 && average2<=average3) {
				request = average2;
				name = name2;
			}
			else  {
				request = average3;
				name = name3;
			}
		}
		else {
			//ECCEZIONE DA INSERIRE.
		}

		city1 = new JSONObject();
		city2 = new JSONObject();
		city3 = new JSONObject();
		
		city1.put("city1", name1);
		city1.put("feels_like_average", average1);
		
		city2.put("city2", name2);
		city2.put("feels_like_average", average2);
		
		city3.put("city3", name3);
		city3.put("feels_like_average", average3);
		
		JSONObject object = new JSONObject();
		
		if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
			object.put("City with max average", name);
			object.put("max average", request);
		}
		else { 
			object.put("City with min average", name);
			object.put("min average", request);
		}
		
		array.put(city1);
		array.put(city2);
		array.put(city3);
		array.put(object);
		
		return array;
	}
	
}
