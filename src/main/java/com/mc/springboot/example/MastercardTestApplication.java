package com.mc.springboot.example;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.mc.springboot.service.ICityNavigationService;

@SpringBootApplication
@ComponentScan({"com.mc.springboot.rest", "com.mc.springboot.rest.error",
				"com.mc.springboot.service","com.mc.springboot.service.impl"})
public class MastercardTestApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(MastercardTestApplication.class);

	@Autowired
	ICityNavigationService navigationService;


	public static void main(String[] args) {
		SpringApplication.run(MastercardTestApplication.class, args);
	}


	/**
	 * This method loads the initial data when the application is started
	 */
	@Override
	public void run(String... args) throws Exception {
		boolean isInitializationError = false;
		try {
			//load the data on startup
			logger.trace("loading the initialization data from classpath file");
			navigationService.load();
		} catch (IOException ex){
			isInitializationError = true;
			logger.error("IOException occured while reading the input file data", ex);
			throw ex;
		} finally {
			if (isInitializationError) {
				logger.error("Error occured while loading the initialization data");
			}
		}
	}

}
