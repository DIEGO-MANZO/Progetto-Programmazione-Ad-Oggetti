/**
 * 
 */
package com.project.WeatherApp.utils.error;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author feder
 *
 */
public class ErrorCalculator extends FindDay {

	/**
	 * 
	 */
	public ErrorCalculator() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<JSONObject> calculate(ArrayList<String> cities,ArrayList<JSONArray> visibilityInfo,int error,String value,int period) {
		
		ArrayList<JSONObject> ret = new ArrayList<JSONObject> ();
		
		String day = "";
		
		int errortot = 0;
		Iterator visibilityIt = visibilityInfo.iterator();
		Iterator citiesIt = cities.iterator();
		
		int i=0,contatore=0;
		
		while(visibilityIt.hasNext() && citiesIt.hasNext()) {
			
			//mettiamo le informazioni della città che si trova al posto i
			JSONArray cityVisibility = new JSONArray();
			cityVisibility = (JSONArray)visibilityIt.next();
			
			//vado a prendere le informazioni sulla visibilità solo del primo giorno di previsione
			JSONArray app = new JSONArray();
			app = cityVisibility.getJSONArray(0); //ho tutto il primo giorno di visibilità
			
			
			JSONObject information = new JSONObject();
			information = findDay(cityVisibility,period);
		    String dataInizio = information.getString("date");
		    int startPosition = information.getInt("position");
		    int endPosition = findDay(cityVisibility,period+1).getInt("position");
		    
		    while(startPosition<endPosition) {
		    	
		    	for(int k=0; k<cityVisibility.getJSONArray(period).length();k++) {
		    		
		    		JSONObject visibility = new JSONObject();
		    		visibility = cityVisibility.getJSONArray(period).getJSONObject(k);
		    		
		    		if(dataInizio.equals(visibility.getString("data"))) {
		    			int errore;
		    			errore = (app.getJSONObject(startPosition).getInt("visibility")-visibility.getInt("visibility"));
		    			errortot+=errore;
		    			contatore++;
		    		}
		    		
		    	}
		    	startPosition++;
		    	dataInizio = app.getJSONObject(startPosition).getString("data");
		    
		    }
		  
		    errortot/=contatore;
            JSONObject errorInfo = new JSONObject();
            errorInfo.put("error AME", errortot);
            errorInfo.put("City", citiesIt.next());
            ret.add(errorInfo);
           
           
        }
       
        ErrorFilter filter = new ErrorFilter();
       
        ret = filter.errorFilter(ret,error,value);
		
		return ret;
	}

}
