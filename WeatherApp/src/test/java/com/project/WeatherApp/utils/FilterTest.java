/**
 * 
 */
package com.project.WeatherApp.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.project.WeatherApp.exception.CityNotFoundException;
import com.project.WeatherApp.exception.WrongParamException;
import com.project.WeatherApp.exception.WrongPeriodException;

/** Questa classe testa i metodi di Filter.
 * @author Federica Parlapiano
 * @author Francesca Palazzetti
 */
class FilterTest {
	
	private Filter filter;
	private ArrayList<String> cities;
	
	/**
     * Inizializza i componenti necessari a testare i metodi.
     * @throws java.lang.Exception
     */
	@BeforeEach
	void setUp() throws Exception {
		cities = new ArrayList<String>();
	}


	/**
     * Serve per distruggere ciò che è stato inizializzato dal metodo setUp.
     * throws java.lang.Exception
     */
	@AfterEach
	void tearDown() throws Exception {
	}

	
	/**
	 * Questo Test verifica se viene generata correttamente l'eccezione WrongPeriod.
	 */
    @Test
    @DisplayName("Corretta generazione dell'eccezione WrongPeriodException.")
    void analyze1() {
		
    	cities.add("Campobasso");
        cities.add("Macerata");
        
        filter = new Filter(cities,"max","visibility",7);
    	
        WrongPeriodException e = assertThrows(WrongPeriodException.class, () -> {filter.analyze();});
    
        assertEquals("7 non è un numero ammesso. Inserisci un numero che sia o 1 o 5.", e.getMex());
        
    }
    
    /**
	 * Questo Test verifica se viene generata correttamente l'eccezione WrongParam.
	 */
    @Test
    @DisplayName("Corretta generazione dell'eccezione WrongParamException.")
    void analyze2() {
		
    	cities.add("Campobasso");
        cities.add("Macerata");
        
        filter = new Filter(cities,"visibi", "max",1);
    	
        WrongParamException e = assertThrows(WrongParamException.class, () -> {filter.analyze();});
    
        assertEquals("visibi non è una stringa ammessa.Inserisci una stringa tra temp_min,temp_max,feels_like e visibility", e.getMex());
        
    }
	
	

}
