package io.crowdcode.vehicle.security.converter;

import io.crowdcode.vehicle.converter.AbstractDefaultConverter;
import io.crowdcode.vehicle.security.domain.FleetGroup;
import io.crowdcode.vehicle.security.dto.FleetGroupDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FleetGroupConverter extends AbstractDefaultConverter<FleetGroup, FleetGroupDto>{
    
    @Autowired
    private UserConverter userConverter;

    @Override
    protected FleetGroupDto newTargetInstance() {
        return new FleetGroupDto();
    }

    @Override
    protected void copyProperties(FleetGroup source, FleetGroupDto target) {
        target.setCompanyName(source.getFleet().getCompanyName());
        target.setUsers(userConverter.convert(source.getUsers()));
    }
    
}
