/**
 * 
 */
package com.project.WeatherApp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.WeatherApp.model.*;
import com.project.WeatherApp.service.Service;
import com.project.WeatherApp.utils.Statistics;
import com.project.WeatherApp.utils.Filter;

import com.project.WeatherApp.service.ToJSON;


/**
 * @author Federica
 * @author Francesca
 */

@RestController

public class Controller {
	
	@Autowired
	Service service;
	Statistics statistic = new Statistics();

	
	@GetMapping(value="/city")
    public ResponseEntity<Object> getCity(@RequestParam String cityName) {
		return new ResponseEntity<> (service.getCityWeather(cityName).toString(), HttpStatus.OK);
    }
    
	
	@GetMapping(value="/visibility")
    public ResponseEntity<Object> getVisibility(@RequestParam String cityName) {
		return new ResponseEntity<> (service.getVisibilityfromApi(cityName).toString(), HttpStatus.OK);
    }
	
	@GetMapping(value="/restrictCityWeather")
    public ResponseEntity<Object> getCityWeather(@RequestParam String cityName) {
		
		City city = service.getCityWeatherRistrictfromApi(cityName);
		
		JSONObject obj = new JSONObject();
		ToJSON tojson = new ToJSON();
		
		obj = tojson.parser(city);
		
		
		return new ResponseEntity<> (obj.toString(), HttpStatus.OK);
    }
	
	@GetMapping(value="/save")
    public ResponseEntity<Object> saveToAFile(@RequestParam String cityName) throws IOException {
		
		String nomeFile = service.save(cityName);
		
		return new ResponseEntity<> ("Salvato nel file "+nomeFile , HttpStatus.OK);
	}
	
	@GetMapping(value="/saveEveryHour")
    public ResponseEntity<Object> saveHour(@RequestParam String cityName) throws IOException {
		
		String nomeFile = service.saveEveryHour(cityName);;
		
		return new ResponseEntity<> (nomeFile, HttpStatus.OK);
	}
	
	@GetMapping(value="/showHistory")
    public ResponseEntity<Object> showHistory(@RequestParam String cityName) throws IOException {
		
		JSONArray array = new JSONArray();
		array = service.readHistory(cityName, cityName, cityName);
		
		return new ResponseEntity<> (array.toString(), HttpStatus.OK);
	}
	
	@GetMapping(value="/todayAverage")
    public ResponseEntity<Object> todayAverage(@RequestParam String cityName) throws IOException {
		
		JSONObject obj = new JSONObject();
		obj = statistic.todayAverage(cityName);
		
		return new ResponseEntity<> (obj.toString(), HttpStatus.OK);
	}
	

	@GetMapping(value="/fiveDayAverage")
    public ResponseEntity<Object> fiveDayAverage(@RequestParam String cityName) throws IOException {
		
		JSONObject obj = new JSONObject();
		obj = statistic.fiveDayAverage(cityName);
		
		return new ResponseEntity<> (obj.toString(), HttpStatus.OK);
	}
	
	@PostMapping("/filter")
    public ResponseEntity<Object> filter(@RequestBody String request) {
		
		JSONObject object = new JSONObject(request);
		JSONArray array = new JSONArray();
		
		String city1 = (String) object.get("city1");
		String city2 = (String) object.get("city2");
		String city3 = (String) object.get("city3");
		String param = (String) object.get("param");
		String value = (String) object.get("value");
		int period = (int) object.get("period");
		
		Filter filter;
		filter = new Filter(city1,city2,city3,param,value,period);
		
		array = filter.analyze();
		
        return new ResponseEntity<>(array.toString(),HttpStatus.OK);
        
	}
	
	@PostMapping("/filters")
	public ResponseEntity<Object> filters(@RequestBody String body) {
		
		JSONObject object = new JSONObject(body);
        JSONArray array = new JSONArray();

 

        array = object.getJSONArray("cities");
        
        ArrayList<String> cities = new ArrayList<String>(array.length());
        
        for(int i=0; i<array.length();i++) {
            JSONObject obj = new JSONObject();
            obj = array.getJSONObject(i);
            cities.add(obj.getString("name"));
        }
        
        String param = object.getString("param");
        String value = object.getString("value");
        int period = object.getInt("period");
		
        Filter filter;
		filter = new Filter(cities,param,value,period);
		array = filter.analyze2();
        
		return new ResponseEntity<>(array.toString(),HttpStatus.OK);
		
	}
	
	
	
}
