package io.crowdcode.vehicle.service;

import io.crowdcode.vehicle.domain.EngineType;
import io.crowdcode.vehicle.domain.Vehicle;

import java.util.List;

public interface VehicleService {

	public Vehicle getCheapestVehicle();
	
	public List<Vehicle> getVehicleByManufacture(String name);
	
	public List<Vehicle> getVehiclesByEngineType(EngineType... engineType);
	
	public Vehicle getVehicleById(Long vehicleId);
	
	public Vehicle registerVehicle(Vehicle vehicle);

    public Vehicle addVehicleToManufacturer(String manufacturerName, Vehicle vehicle);

    public void delete(Long vehicleId);
    
    public void registerObserver(VehicleObserver observer);
    
}
