package com.project.WeatherApp.model;


/** Classe che rappresenta le previsioni meteo ristrette.
 * @author Federica
 * @author Francesca
 */
public class Weather {
	
	private String main;
	private String description;
	private int visibility;
	private double temp_max;
	private double temp_min;
	private double feels_like;
	private String data;
	
	
	/** Costruttore dell'oggetto.
	 * 
     */
	public Weather() {
		this.main = null;
		this.description = null;
		this.visibility = 0;
		this.temp_max = 0;
		this.temp_min = 0;
		this.feels_like = 0;
		this.data = null;
	}
	
	
	/** Costruttore dell'oggetto.
     * @param main            Indicazione generale sul meteo.
     * @param description     Descrizione del meteo.
     */
	public Weather(String main, String description) {
		super();
		this.main = main;
		this.description = description;
	}
	

	/** Costruttore dell'oggetto.
     * @param main            Indicazione generale sul meteo.
     * @param description     Descrizione del meteo.
     * @param visibility      Visibilità
     * @param temp_max        Temperatura massima
     * @param temp_min        Temperatura minima
     */
	public Weather(String main, String description, int visibility, double temp_max, double temp_min) {
		super();
		this.main = main;
		this.description = description;
		this.visibility = visibility;
		this.temp_max = temp_max;
		this.temp_min = temp_min;
	}

	
	/** Costruttore dell'oggetto.
     * @param main            Indicazione generale sul meteo.
     * @param description     Descrizione del meteo.
     * @param visibility      Visibilità
     * @param temp_max        Temperatura massima
     * @param temp_min        Temperatura minima
     * @param feels_like      Temperatura percepita
     * @param data            Giorno e ora a cui si riferisce la previsione
     */
	public Weather(String main, String description, int visibility, double temp_max, double temp_min, double feels_like,
			String data) {
		super();
		this.main = main;
		this.description = description;
		this.visibility = visibility;
		this.temp_max = temp_max;
		this.temp_min = temp_min;
		this.feels_like = feels_like;
		this.data = data;
	}

	/**
     * Metodo che restituisce la principale informazione sul meteo.
     * @return main
     */
	public String getMain() {
		return main;
	}
	
	/**
     * Metodo che setta il main.
     * @param String main.
     */
	public void setMain(String main) {
		this.main = main;
	}
	
	/**
     * Metodo che restituisce la descrizione del meteo.
     * @return descriprion
     */
	public String getDescription() {
		return description;
	}
	
	/**
     * Metodo che setta la descrizione del meteo.
     * @param String description.
     */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
     * Metodo che restituisce la visibilità.
     * @return visibility
     */
	public int getVisibility() {
		return visibility;
	}
	
	/**
     * Metodo che setta la visibilità.
     * @param int visibility.
     */
	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	
	/**
     * Metodo che restituisce la temperatura massima.
     * @return temp_max
     */
	public double getTemp_max() {
		return temp_max;
	}
	
	/**
     * Metodo che setta la temperatura massima.
     * @param long id.
     */
	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}
	
	/**
     * Metodo che restituisce la temperatura minima.
     * @return temp_min
     */
	public double getTemp_min() {
		return temp_min;
	}
	
	/**
     * Metodo che setta la temperatura minima.
     * @param double temp_min.
     */
	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}
	
	/**
     * Metodo che restituisce la temperatura percepita.
     * @return feels_like
     */
	public double getFeels_like() {
		return feels_like;
	}
	
	/**
     * Metodo che setta la temperatura percepita.
     * @param double feels_like.
     */
	public void setFeels_like(double feels_like) {
		this.feels_like = feels_like;
	}
	
	/**
     * Metodo che restituisce giorno ed ora.
     * @return data
     */
	public String getData() {
		return data;
	}
	
	/**
     * Metodo che setta giorno e ora.
     * @param String data.
     */
	public void setData(String data) {
		this.data = data;
	}
	
	
	/**
	 * Override del metodo equals.
	 * @param oggetto Weather da confrontare.
	 * @return true o false a seconda che i due oggetti siano uguali.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weather other = (Weather) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Double.doubleToLongBits(feels_like) != Double.doubleToLongBits(other.feels_like))
			return false;
		if (main == null) {
			if (other.main != null)
				return false;
		} else if (!main.equals(other.main))
			return false;
		if (Double.doubleToLongBits(temp_max) != Double.doubleToLongBits(other.temp_max))
			return false;
		if (Double.doubleToLongBits(temp_min) != Double.doubleToLongBits(other.temp_min))
			return false;
		if (visibility != other.visibility)
			return false;
		return true;
	}


	/**
	 * Override del metodo toString.
	 * @return String che rappresenta il meteo.
	 */
	@Override
	public String toString() {
		return "data=" + data + "main=" + main + ", description=" + description + ", visibility=" + visibility + ", temp_max="
				+ temp_max + ", temp_min=" + temp_min + ", feels_like=" + feels_like + "";
	}
	
	
	
	

}
