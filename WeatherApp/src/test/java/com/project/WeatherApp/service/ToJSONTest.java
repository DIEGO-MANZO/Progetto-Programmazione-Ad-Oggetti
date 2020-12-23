package com.project.WeatherApp.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.project.WeatherApp.model.City;
import com.project.WeatherApp.model.Coordinates;
import com.project.WeatherApp.model.Weather;

/** Questa classe testa il metodo di ToJSON.
 * @author Federica Parlapiano
 * @author Francesca Palazzetti
 */
class ToJSONTest {
	
	City city;
	Coordinates coordinates;
	Vector<Weather> weather;
	ToJSON tojson;
	
	/**
     * Inizializza i componenti necessari a testare i metodi.
     * @throws java.lang.Exception
     */
	@BeforeEach
	void setUp() throws Exception {
		city = new City();
		coordinates = new Coordinates(12,12);
		weather = new Vector<Weather>();
		tojson = new ToJSON();
	}

	/**
     * Serve per distruggere ciò che è stato inizializzato dal metodo setUp.
     * throws java.lang.Exception
     */
	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
     * Questo Test verifica se l'oggetto city viene converito correttamente in JSONObject.
     */
	@Test
    @DisplayName("Corretto parsing")
    void toJson() throws IOException {
    	
        city.setName("Fermo");
        city.setId(12);
        city.setCountry("IT");
        city.setCoordinates(coordinates);
        Weather weat = new Weather("cloudy","clouds",10000,270,270,270,"25-12-2020");
        weather.add(weat);
        city.setVector(weather);
        
        JSONObject weatherObject = new JSONObject();
        
        weatherObject.put("name", city.getName());
		weatherObject.put("country", city.getCountry());
		weatherObject.put("id", city.getId());
		JSONObject coordinates = new JSONObject();
		coordinates.put("latitude", (city.getCoordinates()).getLatitude());
		coordinates.put("longitude", (city.getCoordinates()).getLongitude());
		weatherObject.put("coordinates", coordinates);
        
		JSONArray weatherArr = new JSONArray();
		
		JSONObject weatherObj = new JSONObject();
		weatherObj.put("data", (weat.getData()));
		weatherObj.put("main", (weat.getMain()));
		weatherObj.put("description", (weat.getDescription()));
		weatherObj.put("visibility", (weat.getVisibility()));
		weatherObj.put("temp_max", (weat.getTemp_max()));
		weatherObj.put("temp_min", (weat.getTemp_min()));
		weatherObj.put("feels_like", (weat.getFeels_like()));
		weatherArr.put(weatherObj);
		
		weatherObject.put("Weather", weatherArr);
		
		assertEquals(weatherObject.toString(),tojson.toJson(city).toString());
		
    }

	

}
