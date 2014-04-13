package io.crowdcode.vehicle.service;

import io.crowdcode.vehicle.domain.Manufacturer;
import io.crowdcode.vehicle.service.ManufacturerAlreadyExistsException;

import java.util.List;

public interface ManufacturerService {
    
    public List<Manufacturer> findAll();
    
    public void addManufacturer(String manufacturerName) throws ManufacturerAlreadyExistsException;
    
    public Manufacturer byName(String manufacturerName);

    public boolean isExisting(String manufacturerName);
    
    public void delete(Manufacturer manufacturer);

    public void updateName(Long manufacturerId, String newManufacturerName);
    
}
