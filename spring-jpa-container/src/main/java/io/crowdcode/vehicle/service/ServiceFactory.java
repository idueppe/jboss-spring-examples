package io.crowdcode.vehicle.service;

import io.crowdcode.vehicle.service.spi.VehicleServiceBean;

public class ServiceFactory {
	
	public static VehicleService getVehicleService() {
		return new VehicleServiceBean();
	}
	
}
