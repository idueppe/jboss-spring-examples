package io.crowdcode.vehicle.dao;

import io.crowdcode.vehicle.domain.EngineType;
import io.crowdcode.vehicle.domain.Manufacturer;

import java.util.List;

public interface ManufacturerDao extends EntityDao<Manufacturer> {
    
    public Manufacturer findManufacturerByName(String name);
    
    public List<Manufacturer> findManufacturerWithEngineTypes(EngineType... engineType);

}
