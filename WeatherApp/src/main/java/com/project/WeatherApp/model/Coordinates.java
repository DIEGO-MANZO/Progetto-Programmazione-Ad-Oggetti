package com.project.WeatherApp.model;


/** Classe che rappresenta le coordinate di una città.
 * @author Federica
 * @author Francesca
 */
public class Coordinates {

	static final long serialVersionUID = 1;
	
	private double latitude;
	private double longitude;
	
	
	/** Costruttore dell'oggetto.
     * @param latitude           rappresenta la latitudine
     * @param longitude          rappresenta la longitudine
     */
	public Coordinates(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
     * Metodo che restituisce la latitudine.
     * @return latitude
     */
	public double getLatitude() {
		return latitude;
	}
	
	/**
     * Metodo che setta la latitudine della città.
     * @param double latitude.
     */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	/**
     * Metodo che restituisce la longitudine
     * @return longitude
     */
	public double getLongitude() {
		return longitude;
	}
	
	/**
     * Metodo che setta la longitudine della città.
     * @param longitude.
     */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Override del metodo toString(),
	 * @return String che rappresenta le coordinate.
	 */
	@Override
	public String toString() {
		return "[latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	/**
	 * Override del metodo equals()
	 *  @param oggetto da confrontare,
	 *  @return true se i due oggetti sono uguali, false altrimenti.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}
}
