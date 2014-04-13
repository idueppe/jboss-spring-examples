package io.crowdcode.vehicle.web;

import io.crowdcode.vehicle.controller.spi.DBFixture;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitDatabase {
	
	private static Logger log = Logger.getLogger(InitDatabase.class.getName());
	
	@Autowired
	private DBFixture dbFixture;
	
	@PostConstruct
	public void postConstruct() {
		log.info("Starting to initialize the database...");
//		dbFixture.createDefaultDataInDatabase();
		log.info("Finished initializing the database.");
	}

}
