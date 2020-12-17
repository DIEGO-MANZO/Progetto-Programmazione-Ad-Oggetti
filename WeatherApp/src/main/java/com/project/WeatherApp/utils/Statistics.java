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
        
        int i=0;
        
        String date = "";
        date += (city.getVector()[0].getData()).charAt(8);
        date += (city.getVector()[0].getData()).charAt(9);
    
        String effectiveDate = date;
        
        while( date.equals(effectiveDate) ) {
            temp_max_ave += city.getVector()[i].getTemp_max();
            temp_min_ave += city.getVector()[i].getTemp_min();
            feels_like_ave += city.getVector()[i].getFeels_like();
            visibility_ave += city.getVector()[i].getVisibility();
            i++;
            effectiveDate = "";
            effectiveDate += (city.getVector()[i].getData()).charAt(8);
            effectiveDate += (city.getVector()[i].getData()).charAt(9);
        }
            
        temp_max_ave = temp_max_ave/i;
        temp_min_ave = temp_min_ave/i;
        feels_like_ave = feels_like_ave/i;
        visibility_ave = visibility_ave/i;
        
        JSONObject object = new JSONObject();
        
        object.put("CityName", name);
        object.put("Temp_Max Average", temp_max_ave);
        object.put("Temp_Min Average", temp_min_ave);
        object.put("Feels_like Average", feels_like_ave);
        object.put("Visibility Average", visibility_ave);
        
        return object;
        
    }
	
    public JSONObject fiveDayAverage(String name) {
         
        
        City city = new City(name);
        city = service.getCityWeatherRistrictfromApi(name);
        
        double temp_max_ave = 0;
        double temp_min_ave = 0;
        double feels_like_ave = 0;
        double visibility_ave = 0;
        
        int i=0;
        
        while( i<city.getVector().length ) {
            temp_max_ave += city.getVector()[i].getTemp_max();
            temp_min_ave += city.getVector()[i].getTemp_min();
            feels_like_ave += city.getVector()[i].getFeels_like();
            visibility_ave += city.getVector()[i].getVisibility();
            i++;
            }
            
        temp_max_ave = temp_max_ave/i;
        temp_min_ave = temp_min_ave/i;
        feels_like_ave = feels_like_ave/i;
        visibility_ave = visibility_ave/i;
        
        JSONObject object = new JSONObject();
        
        object.put("CityName", name);
        object.put("Temp_Max Average", temp_max_ave);
        object.put("Temp_Min Average", temp_min_ave);
        object.put("Feels_like Average", feels_like_ave);
        object.put("Visibility Average", visibility_ave);

 

        return object;
        
    }
    

}