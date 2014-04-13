package io.crowdcode.vehicle.security.controller;

import io.crowdcode.vehicle.security.dto.FleetGroupDto;
import io.crowdcode.vehicle.security.dto.UserDto;
import io.crowdcode.vehicle.security.filter.UserFilterParameters;

import java.util.List;

public interface SecurityServiceController {
    
    public void registerUser(UserDto userDto);
    
    public List<UserDto> findAllUsers();
    
    public List<UserDto> findAllCustomer();
    
    public List<UserDto> findAllCustomersNotMemberOf(String companyName);
    
    public List<UserDto> findByFilter(String username, String email, String firstname, String surename, String role);
    
    public List<UserDto> findByFilter(UserFilterParameters filter);

    public FleetGroupDto getGroupByCompanyName(String companyName);
    
    public void addUserToGroup(String companyName, String username);

    
}
