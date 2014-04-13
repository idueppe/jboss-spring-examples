package io.crowdcode.vehicle.controller.spi;

import io.crowdcode.vehicle.controller.ManufacturerController;
import io.crowdcode.vehicle.converter.ManufacturerConverter;
import io.crowdcode.vehicle.domain.Manufacturer;
import io.crowdcode.vehicle.dto.ManufacturerDto;
import io.crowdcode.vehicle.service.ManufacturerAlreadyExistsException;
import io.crowdcode.vehicle.service.ManufacturerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManufacturerControllerBean implements ManufacturerController {
	
    @Autowired
    private ManufacturerService manufacturerService;
    
    @Autowired
    private ManufacturerConverter manufacturerConverter;

    @Override
    public ManufacturerDto byName(String manufacturerName) {
        Manufacturer manufacturer = manufacturerService.byName(manufacturerName);
        return manufacturerConverter.convert(manufacturer);
    }

    @Override
    public List<ManufacturerDto> allManufactures() {
    	List<Manufacturer> allManufacturers = manufacturerService.findAll();
        return manufacturerConverter.convert(allManufacturers);
    }

    @Override
    public void addManufacturer(String manufacturerName) throws ManufacturerAlreadyExistsException {
		manufacturerService.addManufacturer(manufacturerName);
    }

    @Override
    public void deleteManufacturer(String manufacturerName) {
    	Manufacturer manufacturer = manufacturerService.byName(manufacturerName);
    	manufacturerService.delete(manufacturer);
    }

    @Override
    public void updateManufacturerName(ManufacturerDto manufacturer) {
        manufacturerService.updateName(manufacturer.getId(), manufacturer.getName());
    }

}
