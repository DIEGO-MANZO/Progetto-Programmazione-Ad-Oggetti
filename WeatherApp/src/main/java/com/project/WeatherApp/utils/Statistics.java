package com.project.WeatherApp.utils;


import org.json.JSONObject;


import com.project.WeatherApp.model.City;
import com.project.WeatherApp.service.Service;
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
    

}