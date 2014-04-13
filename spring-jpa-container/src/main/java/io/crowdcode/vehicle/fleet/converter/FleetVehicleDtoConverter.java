package io.crowdcode.vehicle.fleet.converter;

import io.crowdcode.vehicle.converter.AbstractDefaultConverter;
import io.crowdcode.vehicle.domain.Vehicle;
import io.crowdcode.vehicle.dto.FleetVehicleDto;

import org.springframework.stereotype.Service;

@Service
public class FleetVehicleDtoConverter extends AbstractDefaultConverter<Vehicle, FleetVehicleDto> {

	@Override
	protected FleetVehicleDto newTargetInstance() {
		return new FleetVehicleDto();
	}

	@Override
	protected void copyProperties(Vehicle source, FleetVehicleDto target) {
		target.setConstructionDate(source.getConstructionDate());
		target.setManufacturerName(source.getManufacturer().getName());
		// TODO OrderDate
		target.setVehicleModel(source.getModel());
	}

}
