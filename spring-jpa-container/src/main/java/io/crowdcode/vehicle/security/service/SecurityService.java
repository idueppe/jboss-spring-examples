package io.crowdcode.vehicle.security.service;

import io.crowdcode.vehicle.security.domain.FleetGroup;
import io.crowdcode.vehicle.security.domain.Role;
import io.crowdcode.vehicle.security.domain.User;
import io.crowdcode.vehicle.security.filter.UserFilterParameters;

import java.util.List;

public interface SecurityService {
    
    public void registerUser(User user);
    
    public List<User> findAllUser();
    
    public List<User> findAllCustomer();
    
    public List<User> findAllCustomerNotMemberOf(String companyName);
    
    public List<User> findByFilter(String username, String email, String firstname, String surename, Role role);
    
    public FleetGroup getGroupByCompanyName(String companyName);
    
    public void addUserToGroup(String companyName, String username);

    public List<User> findByFilter(UserFilterParameters filter);
    
}
