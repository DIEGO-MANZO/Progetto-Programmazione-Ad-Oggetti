package com.project.WeatherApp.utils.error;

import java.util.ArrayList;

import org.json.JSONObject;

/** Questa classe contiene il metodo necessario a filtrare le città in base alla soglia di errore e al valore.
 * @author Francesca Palazzetti
 * @author Federica Parlapiano
 */
public class ErrorFilter {
	
	
	/**
	 * Questo metodo serve per trovare le città che rispettano i requisiti che l'utente impone sulla soglia di errore.
	 * In base alla stringa value, il metodo troverà i metodi che hanno errore maggiore, minore o uguale alla soglia
	 * di errore inserita dall'utente.
	 * @param errors ArrayList di JSONObject, ciascuno dei quali contiene il nome delle città e il relativo errore.
	 * @param error rappresenta la soglia di errore immessa dall'utente.
	 * @param value può assumere i valori "$gt","$lt" e "=" a seconda che l'utente voglia sapere quali città abbiano un
	 *        errore maggiore, minore o uguale a lt.
	 * @return l'ArrayList di JSONObject che aveva avuto in ingresso, con l'aggiunta di un JSONObject in cui sono presenti
	 *         le città che rispettano la condizione espressa.
	 */
	public ArrayList<JSONObject> errorFilter(ArrayList<JSONObject> errors, int error, String value) {
		
		String names = "";
		
		if(value.equals("$gt")) {
			for(int i=0;i<errors.size();i++) {
				JSONObject cityInfo = new JSONObject();
				cityInfo = errors.get(i);
				int cityError = cityInfo.getInt("error AME");
				if (cityError>error)
					names+=cityInfo.getString("City")+" ";
			}
			JSONObject max = new JSONObject();
			max.put(">"+error,names);
			errors.add(max);		
		}
		else if(value.equals("$lt")) {
			for(int i=0;i<errors.size();i++) {
				JSONObject cityInfo = new JSONObject();
				cityInfo = errors.get(i);
				int cityError = cityInfo.getInt("error AME");
				if (cityError<error)
					names+=cityInfo.getString("City")+" ";
			}
			JSONObject max = new JSONObject();
			max.put("<"+error,names);
			errors.add(max);
		}
		else if(value.equals("=")) {
			for(int i=0;i<errors.size();i++) {
				JSONObject cityInfo = new JSONObject();
				cityInfo = errors.get(i);
				int cityError = cityInfo.getInt("error AME");
				if (cityError==error)
					names+=cityInfo.getString("City")+" ";
			}
			JSONObject max = new JSONObject();
			max.put("="+error,names);
			errors.add(max);
				
		}
		
		return errors;
		
	}
	
}
