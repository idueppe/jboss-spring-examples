package io.crowdcode.vehicle.fleet.controller.spi;

import io.crowdcode.vehicle.dto.FleetVehicleDto;
import io.crowdcode.vehicle.fleet.controller.VehicleFleetCart;
import io.crowdcode.vehicle.fleet.controller.VehicleFleetController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class VehicleFleetCartBean implements VehicleFleetCart, Serializable {
    
    private static final Logger LOG = Logger.getLogger(VehicleFleetCartBean.class.getName());
    
	private static final long serialVersionUID = 1L;

	private List<FleetVehicleDto> vehicleList = new ArrayList<FleetVehicleDto>();
	
	@Autowired
	private VehicleFleetController fleetController;

	@Override
	public void add(FleetVehicleDto vehicleDto) {
		vehicleList.add(vehicleDto);
	}

	@Override
	public void remove(FleetVehicleDto vehicleDto) {
		vehicleList.remove(vehicleDto);
	}

	@Override
	public List<FleetVehicleDto> listCart() {
		return vehicleList;
	}

	@Override
	public void order(String companyName) {
		fleetController.addVehicles(companyName, vehicleList);
		vehicleList.clear();
	}

    @Override
    public void close() {
        LOG.info("Closing vehicle fleet cart. Nothing to do here.");
        vehicleList.clear();
    }
}
