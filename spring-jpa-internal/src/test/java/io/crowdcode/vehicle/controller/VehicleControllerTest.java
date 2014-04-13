package io.crowdcode.vehicle.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import io.crowdcode.vehicle.controller.VehicleController;
import io.crowdcode.vehicle.controller.spi.DBFixture;
import io.crowdcode.vehicle.converter.VehicleConverter;
import io.crowdcode.vehicle.domain.EngineType;
import io.crowdcode.vehicle.domain.Vehicle;
import io.crowdcode.vehicle.dto.EngineDto;
import io.crowdcode.vehicle.dto.VehicleDto;

import java.util.Date;
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
public class VehicleControllerTest {
     
    @Autowired
    private VehicleController vehicleController;
    
    @Autowired
    private DBFixture dbFixture;
    
    @Autowired
    private VehicleConverter vehicleConverter;
    
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
        assertNotNull(vehicleController);
    }
    
    @Test
    public void testVehicleByManufacturer() {
        List<VehicleDto> vehicles = vehicleController.findVehicleByManufacturer("Buggati");
        assertNotNull("Result list should never be null.", vehicles);
        assertFalse("Vehicle should not be empty", vehicles.isEmpty());
    }
    
    @Test
    public void testUpdateVehicle() {
        Vehicle vehicle = dbFixture.getVehicles().get(0);
        vehicle.setModel("TEST");
        vehicleController.saveOrUpdateVehicle(vehicleConverter.convert(vehicle));
    }
    
    @Test
    public void testCreateNewVehicle() {
        VehicleDto vehicle = new VehicleDto();
        vehicle.setManufacturerName("Buggati");
        vehicle.setConstructionDate(new Date());
        vehicle.setModelName("VEYRON UNIT TEST");
        EngineDto engine = new EngineDto();
        engine.setEngineType(EngineType.DIESEL);
        vehicle.setEngine(engine);
        vehicleController.saveOrUpdateVehicle(vehicle);
        
    }
    
}
