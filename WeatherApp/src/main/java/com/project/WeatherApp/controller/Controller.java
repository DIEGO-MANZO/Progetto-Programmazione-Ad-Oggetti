package com.project.WeatherApp.controller;

import java.io.IOException;
import java.util.ArrayList;

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
	 * Rotta di tipo GET che mostra le informazioni attuali sulla visibilità e le previsioni 
	 * per i 5 giorni successivi della città richiesta dall'utente.
	 * 
	 * @param cityName rappresenta la città di cui si richiedono le previsioni sulla visibilità.
	 * @return le previsioni meteo sulla visibilità della città richiesta e le informazioni generali sulla città.
	 */
	
	@GetMapping(value="/visibility")
    public ResponseEntity<Object> getVisibility(@RequestParam String cityName) {
		return new ResponseEntity<> (service.getVisibilityfromApi(cityName).toString(), HttpStatus.OK);
    }
	
	
	/**
	 * Rotta di tipo GET che salva ogni ora le previsioni sulla visibilità della città inserita dall'utente.
	 * 
	 * @param cityName rappresenta la città della quale si richiede di salvare il report.
	 * @return il path dove viene salvato il file.
	 * @throws IOException se si verificano errori di output su file.
	 */
	
	@GetMapping(value="/saveEveryHour")
    public ResponseEntity<Object> saveHour(@RequestParam String cityName) throws IOException {
		
		String path = service.saveEveryHour(cityName);
		
		return new ResponseEntity<> (path, HttpStatus.OK);
	}
	
	
	/**
	 * Rotta di tipo POST che filtra in base al periodo le statistiche sulla visibilità delle città 
	 * delle quali è presente lo storico.
	 * 
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
     *     "period": "giornaliera"
     *  }
	 * 
	 * a seconda del "period"(giornaliera, settimanale, trisettimanale) su cui si vuole fare 
	 * statistica. Le città ammesse sono solo Ancona, Campobasso, Macerata, Roma, San Martino in Pensilis e Tolentino.
	 * 
	 * @param body è un JSONObject come sopra indicato.
	 * @return le statistiche di ciascuna città con periodicità specificata dall'utente.
	 * 
	 * @throws EmptyStringException se una delle stringhe immesse è vuota.
	 * @throws CityNotFoundException se la città immessa non è una tra quelle indicate sopra.
	 * @throws WrongPeriodException se viene inserita una stringa errata per period, cioè una stringa diversa da
	 *         "giornaliera", "settimanale", "trisettimanale".
	 * @throws IOException se ci sono errori di input da file.
	 */
	
	@PostMapping(value="/statsHistory")
    public ResponseEntity<Object> statsHistory(@RequestBody String body) 
    		throws EmptyStringException, CityNotFoundException, WrongPeriodException, IOException {
		
		JSONObject object = new JSONObject(body);
        JSONArray array = new JSONArray();

        array = object.getJSONArray("cities");
        
        ArrayList<String> cities = new ArrayList<String>(array.length());
        
        for(int i=0; i<array.length();i++) {
            JSONObject obj = new JSONObject();
            obj = array.getJSONObject(i);
            cities.add(obj.getString("name"));
        }
		
        String period = object.getString("period");
        
        try {
        	return new ResponseEntity<>(service.readVisibilityHistory(cities,period).toString(),HttpStatus.OK);
        }
        catch (EmptyStringException e) {
			return new ResponseEntity<>(e.getMex(),HttpStatus.BAD_REQUEST);
		}
        catch (CityNotFoundException e) {
			return new ResponseEntity<>(e.getMex(),HttpStatus.BAD_REQUEST);
		}
        catch (WrongPeriodException e) {
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
     *     "error": 1,
     *     "value": "$gt"
     *     "period": 3
     *  }
	 * 
	 * Dove le città possono essere quante se ne vuole inserire, ma a scelta solo tra Ancona, Campobasso,
	 * Macerata, Roma, San Martino in Pensilis e Tolentino, perché sono le sole di cui si hanno i dati necessari.
	 * 
	 * 
	 * @param body è un JSONObject come sopra indicato.
	 * @return un JSONArray contenente i JSONObject con tutte le informazioni sulle previsioni azzeccate e l'errore AME di
	 *         ogni città, infine un ulteriore JSONObject che contiene le città che rispettano i filtri richiesti dallo
	 *         utente.
	 * @throws CityNotFoundException se tra i nomi delle città ce n'è almeno uno diverso da quelli indicati sopra.
	 * @throws EmptyStringException se tra i nomi delle città ce n'è almeno uno vuoto.
	 * @throws WrongValueException se si è inserito una stringa non ammessa per value.
	 * @throws WrongPeriodException se si è inserito un numero non ammesso per period.
	 * @throws IOException se si sono verificati errori di input da file.
	 */
	
	@PostMapping("/errors")
	public ResponseEntity<Object> filtersHistory(@RequestBody String body) 
			throws EmptyStringException, CityNotFoundException, WrongValueException, WrongPeriodException, IOException {
		
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
        	return new ResponseEntity<>(service.readHistoryError(cities,error,value,period).toString(),HttpStatus.OK);
        }
        catch(EmptyStringException e) {
        	return new ResponseEntity<>(e.getMex(),HttpStatus.BAD_REQUEST);
        }
        catch(CityNotFoundException e) {
        	return new ResponseEntity<>(e.getMex(),HttpStatus.BAD_REQUEST);
        }
        catch(WrongValueException e) {
        	return new ResponseEntity<>(e.getMex(),HttpStatus.BAD_REQUEST);
        }
        catch(WrongPeriodException e) {
        	return new ResponseEntity<>(e.getMex(),HttpStatus.BAD_REQUEST);
        }
		
	}
	
	
	/**
	 * Rotta di tipo GET che mostra le previsioni ristrette (temperatura massima, minima, percepita e
	 * visibilità) per i 5 giorni successivi alla richiesta della città inserita dall'utente.
	 * 
	 * @param cityName rappresenta la città di cui si richiedono le previsioni meteo ristrette.
	 * @return un JSONObject contenente le previsioni meteo ristrette della città richiesta e 
	 *         le informazioni generali su di essa.
	 */

	@GetMapping(value="/weather")
    public ResponseEntity<Object> getCityWeather(@RequestParam String cityName) {
		
		City city = service.getCityWeatherRistrictfromApi(cityName);
		
		JSONObject obj = new JSONObject();
		ToJSON tojson = new ToJSON();
		
		obj = tojson.toJson(city);
		
		
		return new ResponseEntity<> (obj.toString(), HttpStatus.OK);
    }
	
	
	/**
	 * Rotta di tipo POST che mostra la media della temperatura massima, minima, percepita e la media, la minima,
	 * la massima e la varianza della visibilità del giorno corrente o su 5 giorni, a seconda del period che 
	 * l'utente immette. 
	 * 
	 * {
     *		"city" : "Ancona",
     *		"period" : "five day"
	 *	}
	 * 
	 * @param body è un JSONObject come sopra indicato.
	 * @return il JSONObject che contiene la statistica richiesta.
	 * @throws WrongPeriodException se l'utente ha immesso una stringa per period non ammessa.
	 * @throws IOException se c'è stato un errore di lettura del file.
	 */
	
	@PostMapping(value="/stats")
    public ResponseEntity<Object> stats(@RequestBody String body) throws WrongPeriodException, IOException {
		
		JSONObject request = new JSONObject(body);
		String period = request.getString("period");
		String cityName = request.getString("city");
		
		try {
		if(period.equals("today"))
			return new ResponseEntity<> (statistic.todayAverage(cityName).toString(), HttpStatus.OK);
		else if(period.equals("five day"))
			return new ResponseEntity<> (statistic.fiveDayAverage(cityName).toString(), HttpStatus.OK);
		else throw new WrongPeriodException(period + " non è ammesso");
		}
		catch (WrongPeriodException e) {
			return new ResponseEntity<> (e.getMex(), HttpStatus.BAD_REQUEST);
		}
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
	 * @param body è un JSONObject come sopra indicato.
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
	
	
}
