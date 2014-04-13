package io.crowdcode.vehicle.fleet.controller;

import io.crowdcode.vehicle.dto.FleetVehicleDto;

import java.util.List;

public interface VehicleFleetController {
	
	public List<FleetVehicleDto> getVehicleFleetByName(String companyName);

	public void addVehicles(String companyName, List<FleetVehicleDto> vehicleList);
	
	public List<String> allCompanyNames();

}
