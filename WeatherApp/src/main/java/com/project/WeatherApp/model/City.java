package com.project.WeatherApp.model;

import java.util.Vector;

/** Questa classe descrive le proprietà di ogni città e le relative previsioni meteo ristrette.
 * @author Federica
 * @autor Francesca
 */



public class City {
	
	private String name;
	private String country;
	private long id;
	private Coordinates coordinates;
	private Vector<Weather> vector = new Vector<Weather>();
	
	/** Costruttore dell'oggetto.
	 * 
	 */
	public City() {
		super();
	}
	
	/** Costruttore dell'oggetto.
     * @param id                 ID della città
     */
	public City(long id) {
		this.id=id;
		this.name=null;
		this.coordinates=null;
		this.country=null;
		//this.weatherArray = null;
	}
	
	/** Costruttore dell'oggetto.
     * @param name               Nome della città
     */
	public City(String name) {
		this.id=0;
		this.name=name;
		this.coordinates=null;
		this.country=null;
		//this.weatherArray = null;
	}
	
	/** Costruttore dell'oggetto.
     * @param coordinates        Coordinate della città
     */
	public City(Coordinates coordinates) {
		this.id=0;
		this.name=null;
		this.coordinates=coordinates;
		this.country=null;
		//this.weatherArray = null;
	}
	
	/** Costruttore dell'oggetto.
     * @param name               Nome della città
     * @param country            Stato della città
     */
	public City(String name, String country) {
		this.id=0;
		this.name=name;
		this.coordinates=null;
		this.country=country;
		//this.weatherArray = null;
	}

	/** Costruttore completo.
     * @param name               Nome della città
     * @param country            Stato della città
     * @param id                 ID della città
     * @param coordinates        Coordinate della città
     * @param vector             Previsioni meteo ristrette della città
     */
	public City(long id, String name, Coordinates coordinates, String country,
			int population, int timezone, String sunrise, String sunset) {
		this.id = id;
		this.name = name;
		this.coordinates = coordinates;
		this.country = country;
		//this.weatherArray = null;
	}

	
	/**
     * Metodo che restituisce l'id della città.
     * @return id
     */
	public long getId() {
		return id;
	}
	
	/**
     * Metodo che setta l'id della città.
     * @param long id.
     */
	public void setId(long id) {
		this.id = id;
	}

	/**
     * Metodo che restituisce il nome della città.
     * @return name
     */
	public String getName() {
		return name;
	}
	
	/**
     * Metodo che setta il nome della città.
     * @param String name.
     */
	public void setName(String name) {
		this.name = name;
	}
	

	/**
     * Metodo che restituisce le coordinate della città.
     * @return coordinate.
     */
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	/**
     * Metodo che setta le coordinate della città.
     * @param Coordinates coordinates.
     */
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	/**
     * Metodo che restituisce lo stato della città.
     * @return country
     */
	public String getCountry() {
		return country;
	}
	
	/**
     * Metodo che setta lo stato della città.
     * @param String Country.
     */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
     * Metodo che restituisce il vettore di weather(previsioni) della città.
     * @return vector.
     */
	public Vector<Weather> getVector() {
		return vector;
	}

	/**
     * Metodo che setta il vettore di weather(previsioni) della città.
     * @param vettore di weather.
     */
	public void setVector(Vector<Weather> vector) {
		this.vector = vector;
	}
	
	/**
     * Metodo che scrive il vettore come una stringa.
     * @return String toReturn che rappresenta le previsioni meteo.
     */
	public String toStringVector() {
		String toReturn="";
		for (int i=0; i<vector.size(); i++)
			toReturn += vector.get(i).toString();
		return toReturn;
	}

	/**
	 * Override del metodo toString.
	 * @return String che rappresenta la città.
	 */
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", coordinates=" + coordinates + ", country=" + country
				+ ", weatherArray=" + toStringVector() + "";
	}

	/**
	 * Override del metodo equals.
	 * @param oggetto City da confrontare.
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
		City other = (City) obj;
		if (coordinates == null) {
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}