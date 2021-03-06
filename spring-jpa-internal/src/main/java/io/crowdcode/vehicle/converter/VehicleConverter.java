package io.crowdcode.vehicle.converter;

import io.crowdcode.vehicle.domain.Vehicle;
import io.crowdcode.vehicle.dto.VehicleDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("vehicleConverter")
public class VehicleConverter extends AbstractDefaultConverter<Vehicle, VehicleDto>{

    @Autowired
    private EngineConverter engineConverter;
    
    @Override
    protected VehicleDto newTargetInstance() {
        return new VehicleDto();
    }

    @Override
    protected void copyProperties(Vehicle source, VehicleDto target) throws ConversionException {
        target.setId(source.getId());
        target.setModelName(source.getModel());
        target.setConstructionDate(source.getConstructionDate());
        target.setManufacturerName(source.getManufacturer().getName());
        target.setEngine(engineConverter.convert(source.getEngine()));
        
    }


}
