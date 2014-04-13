package io.crowdcode.vehicle.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import io.crowdcode.vehicle.controller.spi.DBFixture;
import io.crowdcode.vehicle.converter.EngineConverter;
import io.crowdcode.vehicle.dto.EngineDto;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class EngineControllerTest {
     
    @Autowired
    private EngineController engineController;
    
    @Autowired
    private DBFixture dbFixture;
    
    @Autowired
    private EngineConverter engineConverter;
    
    @Before
    public void setUp() {
        dbFixture.createDefaultDataInDatabase();
    }
    
    @After
    public void tearDown() {
        dbFixture.removeAll();
    }
    
    @Test
    public void testFindByManufacturer() {
    	List<EngineDto> found = engineController.byManufacturerName(DBFixture.MANUFACTURER_BUGGATI);
    	assertNotNull("EngineController must not return null.", found);
    	assertEquals("EngineController returns the wrong number of engines", 2, found.size());
    }
    
}
