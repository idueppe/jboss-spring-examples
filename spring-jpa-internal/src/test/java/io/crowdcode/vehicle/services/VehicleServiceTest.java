package io.crowdcode.vehicle.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import io.crowdcode.vehicle.controller.spi.DBFixture;
import io.crowdcode.vehicle.domain.EngineType;
import io.crowdcode.vehicle.domain.Vehicle;
import io.crowdcode.vehicle.service.VehicleService;

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
public class VehicleServiceTest {
     
    @Autowired
    private VehicleService vehicleService;
    
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
    public void testConfiguration() {
        assertNotNull(vehicleService);
    }
    
    @Test
    public void testThatResultIsNotNullIfLookingForUnknownManufacturer() {
        List<Vehicle> vehicleByManufacture = vehicleService.getVehicleByManufacture("NO MANUFACTURER WITH THIS NAME");
        
        assertNotNull("Result list should never be null.", vehicleByManufacture);
    }

    @Test
    public void testThatResultsIsNotNullIfLookingWithNullValueForVehicleByManufacturer() {
        List<Vehicle> vehicleByManufacture = vehicleService.getVehicleByManufacture(null);
        
        assertNotNull("Result list should never be null.", vehicleByManufacture);
    }
    
    @Test
    public void testThatResultsIsNotNullIfLookingWithNullValueForVehiclesByEngineType() {
        List<Vehicle> vehicles = vehicleService.getVehiclesByEngineType();
        
        assertNotNull("Result list should never be null.", vehicles);
    }
    
    @Test
    public void testThatAllVehiclesAreFoundByEngineTypes() {
        List<Vehicle> vehicles = vehicleService.getVehiclesByEngineType(EngineType.DIESEL, EngineType.PETROL);
        assertEquals("There should be two vehicles found.", 4, vehicles.size());
    }
    
    @Test
    public void testThatResultsIsNotNullIfRegisterAVehicle() {
        Vehicle vehicle = vehicleService.registerVehicle(new Vehicle());
        
        assertNotNull("Results should never be null.", vehicle);
        assertNotNull("Vehicle should have an valid id.", vehicle.getId());
    }
    

    @Test
    public void testThatAddingExistingVehicleToManufacturerWorks() {
        Vehicle vehicle = vehicleService.registerVehicle(new Vehicle());
        assertNotNull(vehicle.getId());
        
        assertNull("Vehicle shouldn't have a manufacturer.", vehicle.getManufacturer());
        
        vehicle = vehicleService.addVehicleToManufacturer("Buggati", vehicle);
        assertNotNull("Vehicle should have a manufacturer.", vehicle.getManufacturer());
        
    }
    
}
