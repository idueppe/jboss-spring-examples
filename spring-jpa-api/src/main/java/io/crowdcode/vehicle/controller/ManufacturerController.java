package io.crowdcode.vehicle.controller;

import io.crowdcode.vehicle.dto.ManufacturerDto;
import io.crowdcode.vehicle.service.ManufacturerAlreadyExistsException;

import java.util.List;

public interface ManufacturerController {

    public ManufacturerDto byName(String manufacturerName);
    
    public List<ManufacturerDto> allManufactures();
    
    public void addManufacturer(String manufacturerName) throws ManufacturerAlreadyExistsException;
    
    public void deleteManufacturer(String manufacturerName);

    public void updateManufacturerName(ManufacturerDto selectedManufacturer);
    
}
