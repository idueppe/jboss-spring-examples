package io.crowdcode.vehicle.converter;

import io.crowdcode.vehicle.domain.Engine;
import io.crowdcode.vehicle.dto.EngineDto;

import org.springframework.stereotype.Service;

@Service("engineDtoConverter")
public class EngineDtoConverter extends AbstractDefaultConverter<EngineDto, Engine>{

	@Override
	protected Engine newTargetInstance() {
		return new Engine();
	}

	@Override
	protected void copyProperties(EngineDto source, Engine target) {
		target.setId(source.getEngineId());
		target.setModel(source.getModel());
		target.setType(source.getEngineType());
	}
	 
}
