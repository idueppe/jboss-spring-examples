package io.crowdcode.vehicle.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import io.crowdcode.vehicle.controller.VehicleController;
import io.crowdcode.vehicle.controller.spi.DBFixture;
import io.crowdcode.vehicle.dao.ApplicationLogDao;
import io.crowdcode.vehicle.dto.VehicleDto;

import java.util.Date;

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
public class VehicleControllerLoggingTest {

    @Autowired
    private VehicleController vehicleController;

    @Autowired
    private ApplicationLogDao dao;

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
        assertNotNull(vehicleController);
    }

    @Test
    public void testLogExistAfterException() {
        try {
            vehicleController.saveOrUpdateVehicle(buildVehicleErrorData());
        } catch (NullPointerException npe) {
            // expected
        }
        assertEquals("No log entry found.", 1, dao.findAll().size());
    }

    private VehicleDto buildVehicleErrorData() {
        VehicleDto vehicle = new VehicleDto();
        vehicle.setManufacturerName("Buggati");
        vehicle.setConstructionDate(new Date());
        vehicle.setModelName("VEYRON UNIT TEST");
        return vehicle;
    }

}
