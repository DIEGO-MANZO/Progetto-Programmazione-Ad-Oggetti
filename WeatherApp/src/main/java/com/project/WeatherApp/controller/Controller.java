/**
 * 
 */
package com.project.WeatherApp.controller;

import java.io.IOException;

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
    public ResponseEntity<Object> filter(@RequestBody JSONObject object) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	
}
