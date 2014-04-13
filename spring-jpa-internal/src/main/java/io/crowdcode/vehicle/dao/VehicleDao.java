package io.crowdcode.vehicle.dao;

import io.crowdcode.vehicle.domain.EngineType;
import io.crowdcode.vehicle.domain.Vehicle;

import java.util.List;

public interface VehicleDao extends EntityDao<Vehicle> {

    public List<Vehicle> findVehicleByManufacturer(String name);

    public Vehicle findCheapestVehicle();
    
    public List<Vehicle> findVehiclesByEngineType(EngineType engineType);

}
