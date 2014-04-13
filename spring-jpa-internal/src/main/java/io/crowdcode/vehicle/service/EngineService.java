package io.crowdcode.vehicle.service;

import io.crowdcode.vehicle.domain.Engine;
import io.crowdcode.vehicle.service.EngineDoesNotExistException;

import java.util.List;

public interface EngineService {
	
	public List<Engine> getAllEngines();

	public List<Engine> findByManufacturer(String manufacturerName);

	public void delete(Long engineId) throws EngineDoesNotExistException;

	public void createEngine(Engine engine);
	
}
