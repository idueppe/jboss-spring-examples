package io.crowdcode.vehicle.converter;

import io.crowdcode.vehicle.domain.Engine;
import io.crowdcode.vehicle.dto.EngineDto;

import org.springframework.stereotype.Service;

/**
 * @author idueppe
 */
@Service("engineConverter")
public class EngineConverter extends AbstractDefaultConverter<Engine, EngineDto>{

    @Override
    protected EngineDto newTargetInstance() {
        return new EngineDto();
    }

    @Override
    protected void copyProperties(Engine source, EngineDto target) {
        target.setEngineId(source.getId());
        target.setEngineType(source.getType());
    }

}
