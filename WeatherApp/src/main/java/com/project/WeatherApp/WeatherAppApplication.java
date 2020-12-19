package com.project.WeatherApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Avvia l'applicazione Spring.
 * @author Federica Parlapiano
 * @author Francesca Palazzetti
 *
 */
@SpringBootApplication(scanBasePackages={"com.project.WeatherApp.controller", "com.project.WeatherApp.service","com.project.WeatherApp.model"})

public class WeatherAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherAppApplication.class, args);
	}

}
