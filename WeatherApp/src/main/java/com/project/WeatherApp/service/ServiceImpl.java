/**
 * 
 */
package com.project.WeatherApp.service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.simple.parser.*;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import com.project.WeatherApp.model.*;


/**
 * @author Federica
 * @author Francesca
 */

@Service

public class ServiceImpl implements com.project.WeatherApp.service.Service {
	
	private String api_key = "666efac3e1caf3f728f8c5860edeb469";
	
	
	//va a prendere le previsioni meteo di una città
	public JSONObject getCityWeather(String city) {
		
		JSONObject obj;
		String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid="+api_key;
		
		RestTemplate rt = new RestTemplate();
		
		obj = new JSONObject(rt.getForObject(url, String.class));
		
		
		return obj;
		
	}
	
	
	

	public JSONArray getVisibilityfromApi(String name) {
	
		JSONObject object = getCityWeather(name);
		JSONArray toGive = new JSONArray();
			
			JSONArray weatherArray = object.getJSONArray("list");
			JSONObject support;
			int visibility;
			String data;
			
			for (int i = 0; i<weatherArray.length(); i++) {
				
				support = weatherArray.getJSONObject(i);
				visibility = (int) support.get("visibility");
				data = (String) support.get("dt_txt");
				JSONObject toReturn = new JSONObject();
				toReturn.put("Visibility", visibility);
				toReturn.put("Data", data);
				toGive.put(toReturn);
				
			}
	
		
		return toGive;
		
	}
	
	
	public City getCityWeatherRistrictfromApi(String name) {
		
		JSONObject object = getCityWeather(name);
		
		City city = new City(name);
		
		city = getCityInfofromApi(name);
		
		Weather weather = new Weather();
		
		JSONArray weatherArray = object.getJSONArray("list");
		JSONObject counter;
		
		Weather[] vector = new Weather[weatherArray.length()];
		
		
		try {
			
			
			for (int i = 0; i<weatherArray.length(); i++) {
				
				counter = weatherArray.getJSONObject(i);
				weather.setVisibility(counter.getInt("visibility"));
				weather.setData(counter.getString("dt_txt"));
				JSONArray arrayW = counter.getJSONArray("weather");
				JSONObject objectW = arrayW.getJSONObject(0);
				weather.setDescription(objectW.getString("description"));
				weather.setMain(objectW.getString("main"));
				JSONObject objectW2 = counter.getJSONObject("main");
				weather.setTemp_max(objectW2.getDouble("temp_max"));
				weather.setTemp_min(objectW2.getDouble("temp_min"));
				weather.setFeels_like(objectW2.getDouble("feels_like"));
				vector[i] = new Weather();
				vector[i].setData(weather.getData());
				vector[i].setVisibility(weather.getVisibility());
				vector[i].setTemp_max(weather.getTemp_max());
				vector[i].setTemp_min(weather.getTemp_min());
				vector[i].setFeels_like(weather.getFeels_like());
				vector[i].setDescription(weather.getDescription());
				vector[i].setMain(weather.getMain());
			}
	
		} catch(Exception e) {
		}
		
		
		city.setVector(vector);
		
		
		
		return city;
		
	}
	
	
	public City getCityInfofromApi(String name) {
		
		JSONObject object = getCityWeather(name);
		
		City city = new City(name);
		
		try {
			
			JSONObject cityObj = object.getJSONObject("city");
			String country = (String) cityObj.get("country");
			int id = (int) cityObj.get("id");
			JSONObject coordinatesObj = cityObj.getJSONObject("coord");
			double latitude = (double) coordinatesObj.get("lat");
			double longitude = (double) coordinatesObj.get("lon");
			Coordinates coordinates = new Coordinates(latitude,longitude); 
			city.setCountry(country);
			city.setId(id);
			city.setCoordinates(coordinates);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return city;
	}
	
	
	public String save(String cityName) throws IOException {
        
		City city = getCityWeatherRistrictfromApi(cityName);        
        
		JSONObject obj = new JSONObject();
		ToJSON tojson = new ToJSON();
        
		obj = tojson.parser(city);
        
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String today = date.format(new Date());
        
		String nomeFile = cityName+"_"+today;
        
		String path = "C:/Users/feder/eclipse-workspace/"+nomeFile+".txt";
        
		try{
			
			PrintWriter file_output = new PrintWriter(new BufferedWriter(new FileWriter(path)));
    
			file_output.println(obj.toString());
			file_output.close();
			
		}
		
		catch (Exception e){
			System.err.println("Error: " + e);
		}
        
		return nomeFile;
        
	}
	
	
	public String saveEveryHour(String cityName) {
		
		String path = "C:/Users/feder/eclipse-workspace/HourlyReport.txt";
		
		File file = new File(path);
		
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new Runnable() {
		    @Override
		    public void run() {
		    	
		    	City city = getCityWeatherRistrictfromApi(cityName);        
		        
				JSONObject obj = new JSONObject();
				ToJSON tojson = new ToJSON();
		        
				obj = tojson.parser(city);

		    			try{
		    			    if(!file.exists()) {
		    			        file.createNewFile();
		    			    }

		    			    FileWriter fileWriter = new FileWriter(file, true);
		    				
		    				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		    			    bufferedWriter.write(obj.toString());
		    			    bufferedWriter.write("\n");
		    			    
		    			    bufferedWriter.close();
		    			    
		    			} catch(IOException e) {
		    			    System.out.println(e);
		    			}
		    	
		    }
		}, 0, 1, TimeUnit.MINUTES);
		
		
		return "Il file è stato salvato in " + path;
		
	}
	
	public JSONArray readHistory(String name1, String name2, String name3) throws IOException {
		
		String path = "C:/Users/feder/eclipse-workspace/HourlyReport.txt";
		
			
			//Scanner file_output = new Scanner(new BufferedReader(new FileReader(path)));
			String everything;
			
			BufferedReader br = new BufferedReader(new FileReader(path));
			try {
			    StringBuilder sb = new StringBuilder();
			    String line = br.readLine();

			    while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
			    }
			    everything = sb.toString();
			} finally {
			    br.close();
			}
				
			System.out.println(everything);
		
			JSONArray array = new JSONArray(everything);
	
			return array;
			
	}
	
	
	
    
	
    public JSONObject todayAverage(String name) {
         
        
        City city = new City(name);
        city = getCityWeatherRistrictfromApi(name);
        
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
        city = getCityWeatherRistrictfromApi(name);
        
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
