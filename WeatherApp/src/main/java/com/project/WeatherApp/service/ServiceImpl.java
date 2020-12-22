/**
 * 
 */
package com.project.WeatherApp.service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import com.project.WeatherApp.exception.CityNotFoundException;
import com.project.WeatherApp.exception.EmptyStringException;
import com.project.WeatherApp.exception.WrongPeriodException;
import com.project.WeatherApp.model.*;
import com.project.WeatherApp.utils.VisibilityStatistics;
import com.project.WeatherApp.utils.error.ErrorCalculator;



/** Questa classe è l'implementazione dell'interfaccia Service.
 * Contiene i metodi che vengono utilizzati dal controller.
 * @author Federica Parlapiano
 * @author Francesca Palazzetti 
 */

@Service

public class ServiceImpl implements com.project.WeatherApp.service.Service {
	
	
	/**
	 * api_key è la key necessaria per ottenere informazioni da OpenWeather.
	 */
	private String api_key = "666efac3e1caf3f728f8c5860edeb469";
	
	
	/**
	 * Questo metodo va a prendere da OpenWeather le previsioni meteo di una città.
	 * @param è il nome della città di cui si vuole conoscere le previsioni meteo.
	 * @return un JSONObject contenente le previsioni meteo complete.
	 */
	public JSONObject getCityWeather(String city) {
		
		JSONObject obj;
		String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid="+api_key;
		
		RestTemplate rt = new RestTemplate();
		
		obj = new JSONObject(rt.getForObject(url, String.class));
		
		return obj;
		
	}
	/**
	 * Questo metodo utilizza getCityWeather per andare prendere le previsioni sulla visibilità della città richiesta.
	 * @param è il nome della città di cui si vuole conoscere la visibilità.
	 * @return restituisce il JSONArray contente la visibilità con la relativa data e ora.
	 */

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
	
	
	/**
	 * Questo metodo utilizza getCityWeather per andare a selezionare le previsioni meteo ristrette (temperatura
	 * massima, minima, percepita e visibilità).
	 * @param name è il nome della città di cui si vogliono conoscere le previsioni ristrette.
	 * @return un oggetto di tipo City che contiene tutte le informazioni richieste e anche le informazioni sulla città.
	 */
	public City getCityWeatherRistrictfromApi(String name) {
		
		JSONObject object = getCityWeather(name);
		
		City city = new City(name);
		
		city = getCityInfofromApi(name);
		
		
		
		JSONArray weatherArray = object.getJSONArray("list");
		JSONObject counter;
		
		Vector<Weather> vector = new Vector<Weather>(weatherArray.length());
		
		
		try {
			
			
			for (int i = 0; i<weatherArray.length(); i++) {
				
				Weather weather = new Weather();
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
				vector.add(weather); 
		
			}
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		city.setVector(vector);
		
		return city;
		
	}
	
	/**
	 * Questo metodo serve per ottenere le informazioni sulla città da OpenWeather. Viene richiamato da
	 * getCityWeatherRistrictfromApi(String name).
	 * @param nome della città.
	 * @return un oggetto di tipo città popolato delle informazioni sulla città.
	 */
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
	
	
	/**
	 * Questo metodo richiama getCityWeatherRistrictfromApi(String name) e serve per salvare le previsioni meteo per 
	 * i prossimi cinque giorni della città passata come parametro.
	 * @param è il nome della città
	 * @return una stringa contenente il path del file salvato.
	 */
	public String save(String cityName) throws IOException {
        
		City city = getCityWeatherRistrictfromApi(cityName);        
        
		JSONObject obj = new JSONObject();
		ToJSON tojson = new ToJSON();
        
		obj = tojson.toJson(city);
        
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String today = date.format(new Date());
        
		String nomeFile = cityName+"_"+today;
        
		String path = "C:/Users/feder/eclipse-workspace/"+nomeFile+".txt";
        
		try{
			
			PrintWriter file_output = new PrintWriter(new BufferedWriter(new FileWriter(path)));
    
			file_output.println(obj.toString());
			file_output.close();
			
		}
		
		catch (Exception e) {
			System.err.println("Error: " + e);
		}
        
		return path;
        
	}
	
	/**
	 * Questo metodo richiama getCityWeatherRistrictfromApi(String name) e serve per salvare le previsioni meteo ogni ora.
	 * @param è il nome della città
	 * @return una stringa contenente il path del file salvato.
	 */
	public String saveEveryHour(String cityName) {
		
		String path = "C:/Users/feder/eclipse-workspace/"+cityName+"HourlyReport.txt";
		
		File file = new File(path);
		
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new Runnable() {
		    @Override
		    public void run() {
		    	
		    	JSONArray visibility = new JSONArray();
		    	visibility = getVisibilityfromApi(cityName);
		    	
		    	JSONObject actualvisibility = new JSONObject();
		    	actualvisibility = visibility.getJSONObject(0);

		    			try{
		    			    if(!file.exists()) {
		    			        file.createNewFile();
		    			    }

		    			    FileWriter fileWriter = new FileWriter(file, true);
		    				
		    				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		    			    bufferedWriter.write(actualvisibility.toString());
		    			    bufferedWriter.write("\n");
		    			    
		    			    bufferedWriter.close();
		    			    
		    			} catch(IOException e) {
		    			    System.out.println(e);
		    			}
		    	
		    }
		}, 0, 3, TimeUnit.HOURS);
		
		
		return "Il file è stato salvato in " + path;
		
	}
	
	/**
	 * Questo metodo si occupa della lettura dello storico della città passata in ingresso.
	 * @param name è il nome della città di cui si vuole leggere lo storico.
	 * @return il JSONArray che contiene tutte le informazioni sulla visibilità.
	 * @throws IOException se si verificano errori di input da file.
	 */
	
	public JSONArray readHistory(String name) throws IOException {
		
		
		
		String path = "C:/Users/feder/eclipse-workspace/History/" +name+".txt";
		
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
				
		
			JSONArray array = new JSONArray(everything);
	
			return array;
			
	}
	
	
	/**
	 * Questo metodo serve per leggere lo storico di ogni città passata in ingresso e richiama altri metodi che 
	 * servono per fare statistiche e filtri.
	 * @param ArrayList di stringhe dei nomi delle città, la soglia di errore di cui si vuole sapere se le città abbiano una
	 * soglia minore, maggiore o uguale (a seconda che value sia "$lt" o "$gt" o "=". 
	 * @param cities contiene i nomi di tutte le città su cui si vogliono applicare i filtri.
	 * @param error è l'intero che rappresenta la soglia con cui si vuole filtrare.
	 * @param value esprime il filtro che si vuole applicare, cioè se si vuole sapere quali città hanno un errore maggiore
	 *        o minore di un certo intero error
	 * @param period rappresenta i giorni di predizione (da 1 a 5).
	 * @return restituisce l'ArrayList di JSONObject filtrati secondo i filtri indicati.
	 * @throws EmptyStringException se almeno uno dei nomi inseriti è uguale alla stringa vuota.
	 */
	public ArrayList<JSONObject> readHistoryError(ArrayList<String> cities,int error,String value,int period) throws IOException, CityNotFoundException, EmptyStringException  {
		
			for(int i=0; i<cities.size(); i++) {
				if(cities.get(i).isEmpty())
					throw new EmptyStringException ("Hai dimenticato di inserire la città...");
				else if(!(cities.get(i).equals("Ancona") || cities.get(i).equals("Campobasso") || cities.get(i).equals("Macerata") || cities.get(i).equals("Roma") || cities.get(i).equals("San Martino in Pensilis") || cities.get(i).equals("Tolentino")))
					throw new CityNotFoundException("Città non trovata nello storico");
			}
		
		
			Iterator<String> it = cities.iterator();
			
			ArrayList<JSONArray> visibilityArray = new ArrayList<JSONArray>();
			ArrayList<JSONObject> errors = new ArrayList<JSONObject>();
			
			int p=0;
			
			while(it.hasNext()) {
				
				JSONArray array = new JSONArray();
				array = readHistory(it.next());
				JSONArray visibilityInfo = new JSONArray();
				
				for(int i=0; i<array.length(); i++) {
					
					JSONArray visibilityday = new JSONArray();
					
					JSONObject weather = new JSONObject();
					weather = array.getJSONObject(i);
					
					JSONArray arr = new JSONArray();
					arr = weather.getJSONArray("Weather");
					
					
					for(int j=0; j<arr.length();j++) {
						
						JSONObject visibility = new JSONObject();
						JSONObject all = new JSONObject();
						all = arr.getJSONObject(j);
						
						visibility.put("visibility", all.get("visibility"));
						visibility.put("data", all.get("data"));
						visibilityday.put(visibility);
						
					
					}
					
					visibilityInfo.put(visibilityday);
					
					
					
				}
				System.out.println(cities.get(p));
				System.out.println(visibilityInfo);
				visibilityArray.add(visibilityInfo);
				p++;
				
			}
			
			ErrorCalculator errorcalculator = new ErrorCalculator();
			errors = errorcalculator.calculate(cities,visibilityArray, error, value, period);
			
			return errors;
			
	}
	
	
	
	/**
	 * Questo metodo serve per andare a leggere i file su cui sono salvate le informazioni relative alla visibilità 
	 * per 3 settimane. Dopo aver salvato in un ArrayList di JSONArray le informazioni di ogni città, lo passa al metodo
	 * che serve per calcolare le statistiche sulla visibilità.
	 * @param cities rappresenta i nomi delle città su cui si vogliono fare statistiche.
	 * @param period rappresenta il periodo su cui si vuole fare la statistica.
	 * @throws IOException se si verifica un errore di lettura del file.
	 * @throws EmptyStringException se almeno una delle stringhe immesse è vuota.
	 * @throws CityNotFoundException se la città immessa non è una tra quelle indicate sopra.
	 * @throws EmptyStringException se una delle stringhe immesse è vuota.
	 * @throws WrongPeriodException se viene inserita una stringa errata per period.
	 */
	public ArrayList<JSONArray> readVisibilityHistory(ArrayList<String> cities, String period) throws IOException, EmptyStringException, CityNotFoundException, WrongPeriodException {
		
		Iterator<String> it1 = cities.iterator();
		Iterator<String> it2 = cities.iterator();
		ArrayList<JSONArray> visibilityInfo = new ArrayList<JSONArray>();
		ArrayList<JSONArray> info = new ArrayList<JSONArray>();
		
		for(int i=0; i<cities.size(); i++) {
			if(cities.get(i).isEmpty())
				throw new EmptyStringException ("Hai dimenticato di inserire la città...");
			else if(!(cities.get(i).equals("Ancona") || cities.get(i).equals("Campobasso") || cities.get(i).equals("Macerata") || cities.get(i).equals("Roma") || cities.get(i).equals("San Martino in Pensilis") || cities.get(i).equals("Tolentino")))
				throw new CityNotFoundException("Città non trovata nello storico");
		}
		
		
		while(it1.hasNext()) {
			
			JSONArray array = new JSONArray();
			array = readHistory(it1.next());
			
			visibilityInfo.add(array);
			
		}
		
		int i=0;
		while(it2.hasNext()) {
			
			VisibilityStatistics stats = new VisibilityStatistics();
			JSONArray array = new JSONArray();
			
			if(period.equals("giornaliera"))
				array = stats.dailyVisibilityStats(it2.next(),visibilityInfo.get(i));
			else if(period.equals("settimanale"))
				array = stats.oneWeekVisibilityStats(it2.next(),visibilityInfo.get(i));
			else if(period.equals("trisettimanale"))
				array = stats.threeWeekVisibilityStats(it2.next(),visibilityInfo.get(i));
			else throw new WrongPeriodException(period+" non è permessa. Devi inserire una stringa tra \"giornaliera\","
					+ "\"settimanale\" e \"trisettimanale\". ");
				
			info.add(array);
			i++;
		}
		
		return info;
		
		
		
	}
	
    
	
}
