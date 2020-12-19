package com.project.WeatherApp.utils;


import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;


import com.project.WeatherApp.model.City;
import com.project.WeatherApp.service.ServiceImpl;


public class Statistics {
	
	ServiceImpl service = new ServiceImpl();

    public JSONObject todayAverage(String name) {
         
        
        City city = new City(name);
        city = service.getCityWeatherRistrictfromApi(name);
        
        double temp_max_ave = 0;
        double temp_min_ave = 0;
        double feels_like_ave = 0;
        double visibility_ave = 0;
        double variance = 0;
        
        int i=0;
        
        String date = "";
        date += (city.getVector().get(0).getData()).charAt(8);
        date += (city.getVector().get(0).getData()).charAt(9);
    
        String effectiveDate = date;
        
        double max_visibility = 0;
        double min_visibility = city.getVector().get(i).getVisibility();
        
        while(date.equals(effectiveDate)) {
            temp_max_ave += city.getVector().get(i).getTemp_max();
            temp_min_ave += city.getVector().get(i).getTemp_min();
            feels_like_ave += city.getVector().get(i).getFeels_like();
            visibility_ave += city.getVector().get(i).getVisibility();
            if(city.getVector().get(i).getVisibility()>max_visibility)
            	max_visibility = city.getVector().get(i).getVisibility();
            if (city.getVector().get(i).getVisibility()<min_visibility)
            	min_visibility = city.getVector().get(i).getVisibility();
            i++;
            effectiveDate = "";
            effectiveDate += (city.getVector().get(i).getData()).charAt(8);
            effectiveDate += (city.getVector().get(i).getData()).charAt(9);
        }
        
        
        temp_max_ave = temp_max_ave/i;
        temp_min_ave = temp_min_ave/i;
       	feels_like_ave = feels_like_ave/i;
       	visibility_ave = visibility_ave/i;
        
       	effectiveDate = date;
        i=0;
        //calcolo della varianza di visibilità
        while(date.equals(effectiveDate)) {
        	variance += ((int)((city.getVector().get(i).getVisibility())-visibility_ave))^2;
        	i++;
            effectiveDate = "";
            effectiveDate += (city.getVector().get(i).getData()).charAt(8);
            effectiveDate += (city.getVector().get(i).getData()).charAt(9);
        	
        }
    
        
        variance /= i;
        
        JSONObject object = new JSONObject();
        JSONObject visibility_data = new JSONObject();
        
        visibility_data.put("Visibility average",visibility_ave);
        visibility_data.put("Max visibility",max_visibility);
        visibility_data.put("Min Visibility", min_visibility);
        visibility_data.put("Visibility variance", variance);
        
        object.put("CityName", name);
        object.put("Temp_Max Average", temp_max_ave);
        object.put("Temp_Min Average", temp_min_ave);
        object.put("Feels_like Average", feels_like_ave);
        object.put("Visibility Data", visibility_data);
        
        return object;
        
    }
	
    public JSONObject fiveDayAverage(String name) {
         
        
        City city = new City(name);
        city = service.getCityWeatherRistrictfromApi(name);
        
        double temp_max_ave = 0;
        double temp_min_ave = 0;
        double feels_like_ave = 0;
        double visibility_ave = 0;
        double variance = 0;
        
        int i=0;
        
        double max_visibility = 0;
        double min_visibility = city.getVector().get(i).getVisibility();
        
        while( i<city.getVector().size() ) {
        	 temp_max_ave += city.getVector().get(i).getTemp_max();
             temp_min_ave += city.getVector().get(i).getTemp_min();
             feels_like_ave += city.getVector().get(i).getFeels_like();
             visibility_ave += city.getVector().get(i).getVisibility();
             if(city.getVector().get(i).getVisibility()>max_visibility)
             	max_visibility = city.getVector().get(i).getVisibility();
             if (city.getVector().get(i).getVisibility()<min_visibility)
             	min_visibility = city.getVector().get(i).getVisibility();
             i++;
            }
            
        temp_max_ave = temp_max_ave/i;
        temp_min_ave = temp_min_ave/i;
        feels_like_ave = feels_like_ave/i;
        visibility_ave = visibility_ave/i;
        
        
        i=0;
        //calcolo della varianza di visibilità
        while(i<city.getVector().size()) {
        	variance += ((int)((city.getVector().get(i).getVisibility())-visibility_ave))^2;
        	i++;
        }
        
        variance /=i;
        
        JSONObject object = new JSONObject();
        JSONObject visibility_data = new JSONObject();
        
        visibility_data.put("Visibility average",visibility_ave);
        visibility_data.put("Max visibility",max_visibility);
        visibility_data.put("Min Visibility", min_visibility);
        visibility_data.put("Visibility variance", variance);
        
        object.put("CityName", name);
        object.put("Temp_Max Average", temp_max_ave);
        object.put("Temp_Min Average", temp_min_ave);
        object.put("Feels_like Average", feels_like_ave);
        object.put("Visibility Data", visibility_data);

        return object;
        
    }
    
    
    public JSONObject errorThreshold(ArrayList<String> cities,ArrayList<JSONArray> visibilityInfo,int period) {
    	
    	ArrayList<JSONObject> errors = new ArrayList<JSONObject>();
    	
    	if(period==1) {
    		int i=0;
    		
    		while(i<visibilityInfo.size()) {
    			
    			JSONArray visibility = new JSONArray();
    			visibility = visibilityInfo.get(i);
    			
    			JSONObject visibilityall = visibility.getJSONObject(0);
    			String data = visibilityall.getString("data");
    			
    			String firstday = "";
                firstday += data.charAt(8);
                firstday += data.charAt(9);
                
                boolean equals = true;
                int j=1;
                while(equals && j<visibility.length()) {
                	JSONObject appoggio = new JSONObject();
                	appoggio = visibility.getJSONObject(j);
                	String comparedate = appoggio.getString("data");
                	String day = "";
                	day += comparedate.charAt(8);
                    day += comparedate.charAt(9);
                    if (firstday.equals(day))
                    	equals = true;
                    else equals = false;
                    j++;
                }
    			
                /*
                JSONObject newest = new JSONObject();
                newest = visibility.getJSONObject(j-1);
                String alldate = newest.getString("data");
                int bility = newest.getInt("visibility");
                */
                
                int errore = 0;
                int errortot = 0;
                System.out.println(errore);
                
                int k=j-1;
                while(k<j+7) {
                	JSONObject newest = new JSONObject();
                    newest = visibility.getJSONObject(k);
                    String alldate = newest.getString("data");
                    int bility = newest.getInt("visibility");
                    System.out.println(bility);
                    
                	for(int h=j; h<visibility.length();h++) {
                		JSONObject ob = new JSONObject();
                		ob = visibility.getJSONObject(h);
                		String d = ob.getString("data");
                		if(d.equals(alldate)) {
            
                			int visi = ob.getInt("visibility");
                			errore = Math.abs((visi-bility)/2);
                			errortot += errore;
                			
                		}
                			
                	}
                	k++;
                }
                
                JSONObject city = new JSONObject();
                city.put("City",  cities.get(i));
                city.put("error AME", errortot);
                errors.add(city);
                i++;
    			
    		}
    		
    	}
    	
    	System.out.println(errors);
    	
    	JSONObject obj = new JSONObject();
    	return obj;
    	
    }
    
    
    

}