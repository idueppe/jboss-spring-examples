package io.crowdcode.vehicle.fleet.service;

import io.crowdcode.vehicle.domain.Vehicle;
import io.crowdcode.vehicle.fleet.domain.Fleet;

import java.util.List;

public interface FleetService {

	void addVehicles(String companyName, List<Vehicle> vehicleList);

	/**
	 * 
	 * @param companyName
	 * @return Fleet instance of Fleet or NULL if company not exist.
	 */
	Fleet findFleetByName(String companyName);

	/**
	 * Never null.
	 * @return List<String> list of company names
	 */
    List<String> allCompanyNames();

}
