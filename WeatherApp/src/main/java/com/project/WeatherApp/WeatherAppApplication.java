package com.project.WeatherApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.WeatherApp.service.ServiceImpl;

/** Avvia l'applicazione Spring.
 * @author Federica Parlapiano
 * @author Francesca Palazzetti
 *
 */
@SpringBootApplication(scanBasePackages={"com.project.WeatherApp.controller", "com.project.WeatherApp.service","com.project.WeatherApp.model"})

public class WeatherAppApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(WeatherAppApplication.class, args);
		
		/**
		 * Una volta avviato il programma, salverà su un file ogni ora la visibilità attuale delle città sotto indicate 
		 * richiamando il metodo di ServiceImpl.
		 */
		ServiceImpl service = new ServiceImpl();
		service.saveEveryHour("Ancona");
		service.saveEveryHour("Campobasso");
		service.saveEveryHour("Macerata");
		service.saveEveryHour("Roma");
		service.saveEveryHour("San Martino in Pensilis");
		service.saveEveryHour("Tolentino");
		
	}

}

