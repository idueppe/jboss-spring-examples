package io.crowdcode.vehicle.security.service.spi;

import io.crowdcode.vehicle.fleet.dao.FleetDao;
import io.crowdcode.vehicle.fleet.domain.Fleet;
import io.crowdcode.vehicle.security.dao.FleetGroupDao;
import io.crowdcode.vehicle.security.dao.UserDao;
import io.crowdcode.vehicle.security.domain.FleetGroup;
import io.crowdcode.vehicle.security.domain.Role;
import io.crowdcode.vehicle.security.domain.User;
import io.crowdcode.vehicle.security.filter.UserFilterParameters;
import io.crowdcode.vehicle.security.service.SecurityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SecurityServiceBean implements SecurityService {

    @Autowired
    private FleetGroupDao groupDao;
    
    @Autowired
    private FleetDao fleetDao;
    
    @Autowired
    private UserDao userDao;

    @Override
    public void registerUser(User user) {
        userDao.create(user);
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAll();
    }

    @Override
    public List<User> findAllCustomer() {
        return userDao.findAllOfRole(Role.CUSTOMER);
    }

    @Override
    public FleetGroup getGroupByCompanyName(String companyName) {
        FleetGroup group = groupDao.findGroupByCompanyName(companyName);
        
        if (group == null) {
            group = createFleetGroupForCompany(companyName);
        }
        
        return group;
    }

    private FleetGroup createFleetGroupForCompany(String companyName) {
        FleetGroup group = new FleetGroup();
        Fleet fleet = fleetDao.findByCompanyName(companyName);
        group.setFleet(fleet);
        groupDao.create(group);
        return group;
    }

    @Override
    public void addUserToGroup(String companyName, String username) {
        FleetGroup group = getGroupByCompanyName(companyName);
        User user = userDao.findByUsername(username);
        group.getUsers().add(user);
        groupDao.update(group);
    }
    
    @Override
    public List<User> findAllCustomerNotMemberOf(String companyName) {
        return userDao.findAllCustomersNotMemberOfCompany(companyName);
    }

    @Override
    public List<User> findByFilter(String username, String email, String firstname,
                    String surename, Role role) {
        return userDao.findByFilter(username, email, firstname, surename, role);
    }
    
    @Override
    public List<User> findByFilter(UserFilterParameters filter) {
        return userDao.findByFilter(filter);
    }

    public void setUserDao(UserDao dao) {
        userDao = dao;
    }
    
    
    

}
