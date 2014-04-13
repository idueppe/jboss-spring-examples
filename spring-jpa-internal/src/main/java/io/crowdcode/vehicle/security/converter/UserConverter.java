package io.crowdcode.vehicle.security.converter;

import io.crowdcode.vehicle.converter.AbstractDefaultConverter;
import io.crowdcode.vehicle.security.domain.User;
import io.crowdcode.vehicle.security.dto.UserDto;

import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractDefaultConverter<User, UserDto>{

    @Override
    protected UserDto newTargetInstance() {
        return new UserDto();
    }

    @Override
    protected void copyProperties(User source, UserDto target) {
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        target.setFirstname(source.getFirstname());
        target.setSurename(source.getSurename());
        target.setRole(source.getRole().toString());
    }
    
}
