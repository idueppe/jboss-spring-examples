package io.crowdcode.vehicle.web;

import io.crowdcode.vehicle.controller.spi.DBFixture;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitDatabase {
	
	private static Logger log = LoggerFactory.getLogger(InitDatabase.class);
	
	@Autowired
	private DBFixture dbFixture;
	
	@PostConstruct
	public void postConstruct() {
		log.info("Starting to initialize the database...");
//		dbFixture.createDefaultDataInDatabase();
		log.info("Finished initializing the database.");
	}

}
