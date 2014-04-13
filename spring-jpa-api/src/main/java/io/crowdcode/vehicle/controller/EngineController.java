package io.crowdcode.vehicle.controller;

import io.crowdcode.vehicle.dto.EngineDto;
import io.crowdcode.vehicle.service.EngineDoesNotExistException;

import java.util.List;

public interface EngineController {

    public List<EngineDto> byManufacturerName(String manufacturerName);
    
    public void addEngine(EngineDto engine);
    
    public void deleteEngine(EngineDto engine) throws EngineDoesNotExistException;
    
}
