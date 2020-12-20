package com.project.WeatherApp.utils.error;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class ErrorFilter {
	
	public ArrayList<JSONObject> error(ArrayList<String> cities, ArrayList<JSONArray> visibilityInfo, int error, String value) {
		
		ArrayList<JSONObject> errors = new ArrayList<JSONObject>();
		
		return errors;
	}
	
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
		
		
		System.out.println(errors);
		
		return errors;
		
	}
	
}
