package io.crowdcode.vehicle.service;

import io.crowdcode.vehicle.domain.Vehicle;

public interface VehicleObserver {
    
    public void onVehicleDelete(Vehicle vehicle);

}
