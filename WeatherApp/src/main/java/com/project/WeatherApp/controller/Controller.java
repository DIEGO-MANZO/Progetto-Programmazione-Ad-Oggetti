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

import com.project.WeatherApp.exception.CityNotFoundException;
import com.project.WeatherApp.exception.EmptyStringException;
import com.project.WeatherApp.exception.WrongParamException;
import com.project.WeatherApp.exception.WrongPeriodException;
import com.project.WeatherApp.exception.WrongValueException;
import com.project.WeatherApp.model.*;
import com.project.WeatherApp.service.Service;
import com.project.WeatherApp.utils.Statistics;
import com.project.WeatherApp.utils.Filter;

import com.project.WeatherApp.service.ToJSON;


/**
 * Questa classe gestisce tutte le chiamate al server che il client può fare
 * 
 * @author Federica Parlapiano
 * @author Francesca Palazzetti
 * 
 */

@RestController

public class Controller {
	
	@Autowired
	Service service;
	Statistics statistic = new Statistics();

	/**
	 * Rotta di tipo GET che mostra le previsioni complete per i 5 giorni successivi della città richiesta dall'utente.
	 * 
	 * @param cityName rappresenta la città di cui si richiedono le previsioni meteo.
	 * @return le previsioni meteo complete della città richiesta e le informazioni generali 
	 * sulla città.
	 * @throws 
	 */
	
	
	@GetMapping(value="/city")
    public ResponseEntity<Object> getCity(@RequestParam String cityName) {
	
			return new ResponseEntity<> (service.getCityWeather(cityName).toString(), HttpStatus.OK);
		
    }
	
	/**
	 * Rotta di tipo GET che mostra le previsioni ristrette (temperatura massima, minima, percepita e
	 * visibilità) per i 5 giorni successivi della città richiesta dall'utente.
	 * 
	 * @param cityName rappresenta la città di cui si richiedono le previsioni meteo ristrette.
	 * @return le previsioni meteo ristrette della città richiesta e le informazioni generali sulla città.
	 * @throws 
	 */

	@GetMapping(value="/restrictCityWeather")
    public ResponseEntity<Object> getCityWeather(@RequestParam String cityName) {
		
		City city = service.getCityWeatherRistrictfromApi(cityName);
		
		JSONObject obj = new JSONObject();
		ToJSON tojson = new ToJSON();
		
		obj = tojson.parser(city);
		
		
		return new ResponseEntity<> (obj.toString(), HttpStatus.OK);
    }
	
	/**
	 * Rotta di tipo GET che mostra le previsioni sulla visibilità per i 5 giorni successivi della città richiesta 
	 * dall'utente.
	 * 
	 * @param cityName rappresenta la città di cui si richiedono le previsioni sulla visibilità.
	 * @return le previsioni meteo sulla visibilità della città richiesta e le informazioni generali sulla città.
	 * @throws 
	 */
	
	@GetMapping(value="/visibility")
    public ResponseEntity<Object> getVisibility(@RequestParam String cityName) {
		return new ResponseEntity<> (service.getVisibilityfromApi(cityName).toString(), HttpStatus.OK);
    }
	
	/**
	 * Rotta di tipo GET che salva in un file "cityName_annomesegiorno.txt" le previsioni meteo ristrette di una città per 
	 * i 5 giorni successivi al momento della richiesta.
	 * 
	 * @param cityName rappresenta la città di cui si richiedono le previsioni meteo ristrette da salvare sul file.
	 * @return il path dove viene salvato il file.
	 * @throws IOException se si verificano errori di output su file.
	 */
	
	@GetMapping(value="/save")
    public ResponseEntity<Object> saveToAFile(@RequestParam String cityName) throws IOException {
		
		String path = service.save(cityName);
		
		return new ResponseEntity<> ("Salvato in "+ path , HttpStatus.OK);
	}
	
	/**
	 * Rotta di tipo GET che salva automaticamente in un file "HourlyReport.txt" le previsioni meteo ristrette della
	 * città richiesta dall'utente.
	 * 
	 * @param cityName rappresenta la città della quale si richiede di salvare il report.
	 * @return il path dove viene salvato il file.
	 * @throws IOException se si verificano errori di output su file.
	 */
	
	@GetMapping(value="/saveEveryHour")
    public ResponseEntity<Object> saveHour(@RequestParam String cityName) throws IOException {
		
		String path = service.saveEveryHour(cityName);;
		
		return new ResponseEntity<> (path, HttpStatus.OK);
	}
	
	/**
	 * Rotta di tipo GET che mostra lo storico della città richiesta dall'utente.
	 * 
	 * @param cityName rappresenta la città di cui si richiede lo storico.
	 * @return lo storico sotto forma di JSONArray.
	 * @throws IOException se si verificano errori di input da file.
	 */
	
	@GetMapping(value="/showHistory")
    public ResponseEntity<Object> showHistory(@RequestParam String cityName) throws IOException {
		
		JSONArray history = new JSONArray();
		history = service.readHistory(cityName);
		
		return new ResponseEntity<> (history.toString(), HttpStatus.OK);
	}
	
	/**
	 * Rotta di tipo GET che mostra la media della temperatura massima, minima, percepita e della visibilità del giorno
	 * corrente.
	 * 
	 * @param cityName rappresenta la città di cui si richiede la statistica.
	 * @return il JSONObject che contiene la statistica richiesta.
	 * @throws 
	 */
	
	@GetMapping(value="/todayAverage")
    public ResponseEntity<Object> todayAverage(@RequestParam String cityName) throws IOException {
		
		JSONObject stat = new JSONObject();
		stat = statistic.todayAverage(cityName);
		
		return new ResponseEntity<> (stat.toString(), HttpStatus.OK);
	}
	
	/**
	 * Rotta di tipo GET che mostra la media della temperatura massima, minima, percepita e della visibilità dei 5 giorni
	 * successivi a quello corrente.
	 * 
	 * @param cityName rappresenta la città di cui si richiede la statistica.
	 * @return il JSONObject che contiene la statistica richiesta.
	 * @throws 
	 */

	@GetMapping(value="/fiveDayAverage")
    public ResponseEntity<Object> fiveDayAverage(@RequestParam String cityName) throws IOException {
		
		JSONObject obj = new JSONObject();
		obj = statistic.fiveDayAverage(cityName);
		
		return new ResponseEntity<> (obj.toString(), HttpStatus.OK);
	}
	
	/**
	 * Rotta di tipo POST che filtra le statistiche in base all'informazione che si vuole richiedere.
	 * L'utente deve inserire un JSONObject di questo tipo:
	 * 
	 * {
     *     "cities": [
     *        {
     *          "name": "Tolentino"
     *        },
     *        {
     *          "name": "San Martino in Pensilis"
     *        },
     *        {
     *          "name": "Ancona"
     *        }
     *      ],
     *     "param": "temp_max",
     *     "value": "max",
     *     "period": 1
     *  }
	 * 
	 * a seconda del "param"(temp_max o min o feels_like o visibility) di cui vuole conoscere quale delle
	 * (innumerevoli) città abbia la media più alta o più bassa(a seconda che "value" sia max o min) e in quale 
	 * "period"(1 o 5 giorni).
	 * 
	 * 
	 * @param è un JSONObject come sopra indicato.
	 * @return il JSONArray che contiene tanti JSONObject quante sono le città specificate nella richiesta
	 *         ognuno dei quali contiene il nome della città e la media del "param" indicato. In più il JSONArray contiene
	 *         un ultimo JSONObject al cui interno è contenuta la massima o minima media a seconda del valore indicato.
	 * @throws WrongPeriodException se il numero immesso è errato.
	 * @throws WrongValueException se viene inserita una stringa errata per value.
	 * @throws WrongParamException se viene inserita una stringa errata per param.
	 */
	@PostMapping("/filters")
	public ResponseEntity<Object> filters(@RequestBody String body) throws WrongPeriodException, WrongValueException, WrongParamException {
		
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
		
		try {
        	return new ResponseEntity<>(filter.analyze().toString(),HttpStatus.OK);
        }
		catch(WrongPeriodException e) {
	        	return new ResponseEntity<>(e.getMex(),HttpStatus.BAD_REQUEST);
	        }
		catch(WrongValueException e) {
        	return new ResponseEntity<>(e.getMex(),HttpStatus.BAD_REQUEST);
        }
		catch(WrongParamException e) {
        	return new ResponseEntity<>(e.getMex(),HttpStatus.BAD_REQUEST);
        }
		
		
	}
	

	/**
	 * Rotta di tipo POST che filtra le statistiche sulla visibilità in base ad una soglia di errore.
	 * L'utente deve inserire un JSONObject di questo tipo:
	 * 
	 * {
     *     "cities": [
     *        {
     *          "name": "Tolentino"
     *        },
     *        {
     *          "name": "San Martino in Pensilis"
     *        },
     *        {
     *          "name": "Ancona"
     *        }
     *      ],
     *     "param": "visibility",
     *     "error": "0.5",
     *     "period": 3
     *  }
	 * 
	 * Dove le città possono essere quante se ne vuole inserire, ma che possono essere solo Ancona, Campobasso,
	 * Macerata, Roma, San Martino in Pensilis e Tolentino.
	 * 
	 * 
	 * @param è un JSONObject come sopra indicato.
	 * @return
	 * @throws CityNotFoundException se tra i nomi delle città ce n'è almeno uno diverso da quelli indicati sopra.
	 * @throws EmptyStringException se tra i nomi delle città ce n'è almeno uno vuoto.
	 */
	
	
	
	@PostMapping("/filtersHistory")
	public ResponseEntity<Object> filtersHistory(@RequestBody String body) throws IOException,EmptyStringException, CityNotFoundException {
		
		JSONObject object = new JSONObject(body);
        JSONArray array = new JSONArray();

        array = object.getJSONArray("cities");
        
        ArrayList<String> cities = new ArrayList<String>(array.length());
        
        for(int i=0; i<array.length();i++) {
            JSONObject obj = new JSONObject();
            obj = array.getJSONObject(i);
            cities.add(obj.getString("name"));
        }
        
        int error = object.getInt("error");
        String value = object.getString("value");
        int period = object.getInt("period");
        
        try {
        	return new ResponseEntity<>(service.readHistory2(cities,error,value,period).toString(),HttpStatus.OK);
        }
        catch(EmptyStringException e) {
        	return new ResponseEntity<>(e.getMex(),HttpStatus.BAD_REQUEST);
        }
        catch(CityNotFoundException e) {
        	return new ResponseEntity<>(e.getMex(),HttpStatus.BAD_REQUEST);
        }
		
	}

	
	
	
}
