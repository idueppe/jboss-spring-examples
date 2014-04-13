package io.crowdcode.mockup;

import io.crowdcode.vehicle.dao.VehicleDao;
import io.crowdcode.vehicle.domain.EngineType;
import io.crowdcode.vehicle.domain.Vehicle;

import java.util.List;

public class VehicleDaoMock implements VehicleDao {

    @Override
    public List<Vehicle> findAll() {
        return null;
    }

    @Override
    public Vehicle find(Long id) {
        return null;
    }

    @Override
    public void create(Vehicle entity) {
        
    }

    @Override
    public void delete(Vehicle entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Vehicle update(Vehicle entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Vehicle> findVehicleByManufacturer(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vehicle findCheapestVehicle() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Vehicle> findVehiclesByEngineType(EngineType engineType) {
        // TODO Auto-generated method stub
        return null;
    }

}
