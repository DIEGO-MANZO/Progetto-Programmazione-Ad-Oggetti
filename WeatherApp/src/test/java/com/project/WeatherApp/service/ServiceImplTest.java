package com.project.WeatherApp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.project.WeatherApp.exception.CityNotFoundException;
import com.project.WeatherApp.exception.EmptyStringException;


/** Questa classe testa i metodi di ServiceImpl.
 * @author Federica Parlapiano
 * @author Francesca Palazzetti
 */
class ServiceImplTest {

	private ServiceImpl service;
    private ArrayList<String> cities;
    
	 /**
     * Inizializza i componenti necessari a testare i metodi.
     * @throws java.lang.Exception
     */
	@BeforeEach
	void setUp() throws Exception {
		service = new ServiceImpl();
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
     * Questo Test verifica se il path in cui viene salvato il file è corretto.
     */
	@Test
    @DisplayName("Salvataggio corretto del file")
    void salvaFile() throws IOException {
    	
        String cityName = "Tolentino";
        
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String today = date.format(new Date());
		
		String path = "C:/Users/feder/eclipse-workspace/"+cityName+"_"+today+".txt";
		
        assertEquals(path,service.save(cityName));
        
        
    }
    
    
	/**
	 * Questo Test verifica se viene generata correttamente l'eccezione CityNotFound.
	 */
    @Test
    @DisplayName("Corretta generazione dell'eccezione CityNotFoundException.")
    void readHistory1() {
		
    	cities.add("San Martino in Pensilis");
        cities.add("Fermo");
    	
        CityNotFoundException e = assertThrows(CityNotFoundException.class, () -> {service.readHistoryError(cities,1,"max",1);});
    
        assertEquals("Città non trovata nello storico", e.getMex());
        
    }
    
    /**
	 * Questo Test verifica se viene generata correttamente l'eccezione EmptyString.
	 */
	@Test
    @DisplayName("Corretta generazione dell'eccezione EmptyStringException.")
    void readHistory2() {
	
    	cities.add("Ancona");
        cities.add("");
    	
        EmptyStringException e = assertThrows(EmptyStringException.class, () -> {service.readHistoryError(cities,1,"max",1);});
        
        assertEquals("Hai dimenticato di inserire la città...", e.getMex());
        
    }
	
	
}
