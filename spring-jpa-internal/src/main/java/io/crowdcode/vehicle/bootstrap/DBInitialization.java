package io.crowdcode.vehicle.bootstrap;

import io.crowdcode.vehicle.controller.spi.DBFixture;
import io.crowdcode.vehicle.fleet.dao.spi.jpa.DBFixtureFleets;
import io.crowdcode.vehicle.security.dao.spi.DBFixtureUser;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class DBInitialization {
	
	private static final Logger LOG = Logger.getLogger(DBInitialization.class.getName());
	
	@Autowired
	private DBFixture dbFixture;
	
	@Autowired
	private DBFixtureFleets dbFixtureFleets;
	
	@Autowired
	private DBFixtureUser dbFixtureUser;
	
	public void initializeDatabase() {
	    LOG.info("-------------- Initializing Database with Dummy Data ----------------");
	    
//	    dbFixture.createDefaultDataInDatabase();
//	    dbFixtureFleets.createDefaultDataInDatabase();
//	    dbFixtureUser.createDefaultDataInDatabase();
	}

    
}
