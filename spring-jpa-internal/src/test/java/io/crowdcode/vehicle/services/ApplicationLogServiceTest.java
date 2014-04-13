package io.crowdcode.vehicle.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import io.crowdcode.vehicle.controller.spi.DBFixture;
import io.crowdcode.vehicle.service.ApplicationLogService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
@Transactional
public class ApplicationLogServiceTest {

    @Autowired
    private ApplicationLogService service;
    
    @Autowired
    private DBFixture dbFixture;
    
    @Before
    public void setUp() {
        dbFixture.createDefaultDataInDatabase();
    }
    
    @After
    public void tearDown() {
        dbFixture.removeAll();
    }
    
    @Test
    public void testDependencyInjectionConfiguration() {
        assertNotNull(service);
    }

    @Test
    public void testAddLog() {
    	service.log("JUNIT TEST ADD LOG");
    	assertEquals("JUNIT TEST ADD LOG", service.logEntries().get(0).getMessage());
    }
    
}
