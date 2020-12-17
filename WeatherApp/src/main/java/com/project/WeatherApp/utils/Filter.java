package com.project.WeatherApp.utils;

import org.json.JSONArray;

public class Filter {
	
	private String city1;
	private String city2;
	private String city3;
	private String param;
	private String value;
	private int period;
	
	public Filter(String city1, String city2, String city3, String param, String value, int period) {
		this.city1 = city1;
		this.city2 = city2;
		this.city3 = city3;
		this.param = param;
		this.value = value;
		this.period = period;
	}
	
	public JSONArray analyze() {
		
		JSONArray array = new JSONArray ();
		if(period==1) {
			if(param.equals("temp_max")) {
				FilterTempMax filter = new FilterTempMax();
				array = filter.oneDay(this.city1, this.city2, this.city3, value);
			} 
			else if (param.equals("temp_min")) {
				FilterTempMin filter = new FilterTempMin();
				array = filter.oneDay(this.city1, this.city2, this.city3, value);
			}
			else if (param.equals("feels_like")) {
				FilterFeelsLike filter = new FilterFeelsLike();
				array = filter.oneDay(this.city1, this.city2, this.city3, value);
			}
			else if (param.equals("visibility")) {
				FilterVisibility filter = new FilterVisibility();
				array = filter.oneDay(this.city1, this.city2, this.city3, value);
			}
						
		}
		
		else if(period==5) {
			if(param.equals("temp_max")) {
				FilterTempMax filter = new FilterTempMax();
				array = filter.fiveDay(this.city1, this.city2, this.city3, value);
			}
			else if (param.equals("temp_min")) {
				FilterTempMin filter = new FilterTempMin();
				array = filter.fiveDay(this.city1, this.city2, this.city3, value);
			}
			else if (param.equals("feels_like")) {
				FilterFeelsLike filter = new FilterFeelsLike();
				array = filter.fiveDay(this.city1, this.city2, this.city3, value);
			}
			else if (param.equals("visibility")) {
				FilterVisibility filter = new FilterVisibility();
				array = filter.fiveDay(this.city1, this.city2, this.city3, value);
			}
						
		}
		
		return array;
	}
	
	
	
}
