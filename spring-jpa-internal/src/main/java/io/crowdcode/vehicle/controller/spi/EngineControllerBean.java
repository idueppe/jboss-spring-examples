package io.crowdcode.vehicle.controller.spi;

import io.crowdcode.vehicle.controller.EngineController;
import io.crowdcode.vehicle.converter.EngineConverter;
import io.crowdcode.vehicle.converter.EngineDtoConverter;
import io.crowdcode.vehicle.domain.Engine;
import io.crowdcode.vehicle.dto.EngineDto;
import io.crowdcode.vehicle.service.EngineDoesNotExistException;
import io.crowdcode.vehicle.service.EngineService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EngineControllerBean implements EngineController {

	@Autowired
	private EngineService engineService;
	
	@Autowired
	private EngineConverter engineConverter;
	
	@Autowired
	private EngineDtoConverter engineDtoConverter;
	
	
	@Override
	public List<EngineDto> byManufacturerName(String manufacturerName) {
		List<Engine> engines = engineService.findByManufacturer(manufacturerName);
		return engineConverter.convert(engines);
	}
	
	@Override
	public void addEngine(EngineDto engine) {
		engineService.createEngine(engineDtoConverter.convert(engine));
	}

	@Override
	public void deleteEngine(EngineDto engine) throws EngineDoesNotExistException {
		engineService.delete(engine.getEngineId());
	}

}
