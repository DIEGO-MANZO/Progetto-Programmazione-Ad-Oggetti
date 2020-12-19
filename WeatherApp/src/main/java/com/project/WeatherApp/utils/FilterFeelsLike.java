package com.project.WeatherApp.utils;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Questa classe implementa FilterStats e contiene i metodi per il filtraggio rispetto alla temperatura percepita.
 * @author Federica Parlapiano
 * @author Francesca Palazzetti 
 */

public class FilterFeelsLike implements FilterStats {
	
	Statistics statistic = new Statistics();
	
	/**
	 * Questo metodo calcola la media della temperatura percepita di un giorno delle città passate in ingresso e
	 * filtra rispetto al value. Restituisce un JSONArray contenente JSONObject che rappresentano le città e le relative 
	 * temperature percepite. 
	 * A seconda di value c'è un JSONObject che rappresenta la città con max/min valore di temperatura percepita
	 * @param cities rappresenta le città con cui si vuole fare la statistica e il filtraggio
	 * @param value rappresenta il valore con cui si vuole fare il filtraggio.
	 * @return JSONArray come descritto sopra. 
	 */
	
	public JSONArray oneDay (ArrayList<String> cities, String value) {
		
		JSONArray array = new JSONArray();
		
		ArrayList<JSONObject> average = new ArrayList<JSONObject>();
		ArrayList<Double> averageFeelsLike = new ArrayList<Double>();
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
			double ave = object.getDouble("Feels_like Average");
			averageFeelsLike.add(ave);
			
			JSONObject obj = new JSONObject();
			obj.put("cityName:",cities.get(i));
			obj.put("feels_like_average:",ave);
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
	
	/**
	 * Questo metodo calcola la media della temperatura percepita su cinque giorni delle città passate in ingresso e
	 * filtra rispetto al value. Restituisce un JSONArray contenente JSONObject che rappresentano le città e le relative 
	 * temperature percepite. 
	 * A seconda di value c'è un JSONObject che rappresenta la città con max/min valore di temperatura percepita
	 * @param cities rappresenta le città con cui si vuole fare la statistica e il filtraggio
	 * @param value rappresenta il valore con cui si vuole fare il filtraggio.
	 * @return JSONArray come descritto sopra. 
	 */
	
	public JSONArray fiveDay (ArrayList<String> cities, String value) {
		JSONArray array = new JSONArray();
		
		ArrayList<JSONObject> average = new ArrayList<JSONObject>();
		ArrayList<Double> averageFeelsLike = new ArrayList<Double>();
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
			double ave = object.getDouble("Feels_like Average");
			averageFeelsLike.add(ave);
			
			JSONObject obj = new JSONObject();
			obj.put("cityName:",cities.get(i));
			obj.put("feels_like_average:",ave);
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
