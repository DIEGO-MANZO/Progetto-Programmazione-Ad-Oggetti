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

import com.project.WeatherApp.exception.WrongParamException;
import com.project.WeatherApp.exception.WrongValueException;

/** Questa classe testa i metodi di ServiceImpl.
 * @author Federica Parlapiano
 * @author Francesca Palazzetti
 */
class FilterVisibilityTest {
	
	private ArrayList<String> cities;
	private FilterVisibility filter;
	
	/**
	 * Inizializza i componenti necessari a testare i metodi.
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		cities = new ArrayList<String>();
		filter = new FilterVisibility();
	}

	/**
	 * Serve per distruggere ciò che è stato inizializzato dal metodo setUp.
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	 /**
	  * Questo Test verifica se viene generata correttamente l'eccezione WrongValue.
	  */
	    @Test
	    @DisplayName("Corretta generazione dell'eccezione WrongValueException.")
	    void oneDay() {
			
	    	cities.add("Campobasso");
	        cities.add("Macerata");
	        
	    	
	        WrongValueException e = assertThrows(WrongValueException.class, () -> {filter.oneDay(cities,"m");});
	    
	        assertEquals("m è una stringa errata! Devi inserire una stringa tra max/MAX/Max oppure min/MIN/Min", e.getMex());
	        
	    }
	    
	    /**
		  * Questo Test verifica se viene generata correttamente l'eccezione WrongValue.
		  */
		    @Test
		    @DisplayName("Corretta generazione dell'eccezione WrongValueException.")
		    void fiveDay() {
				
		    	cities.add("Campobasso");
		        cities.add("Macerata");
		        
		    	
		        WrongValueException e = assertThrows(WrongValueException.class, () -> {filter.oneDay(cities,"k");});
		    
		        assertEquals("k è una stringa errata! Devi inserire una stringa tra max/MAX/Max oppure min/MIN/Min", e.getMex());
		        
		    }
}
