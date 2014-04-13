package io.crowdcode.vehicle.security.service;

import static org.junit.Assert.*;
import io.crowdcode.vehicle.security.dao.UserDao;
import io.crowdcode.vehicle.security.domain.Role;
import io.crowdcode.vehicle.security.domain.User;
import io.crowdcode.vehicle.security.filter.UserFilterParameters;
import io.crowdcode.vehicle.security.service.spi.SecurityServiceBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class SecurityServiceBeanMockTest {
    
    @Test
    public void testSecurityService() {
        SecurityServiceBean service = new SecurityServiceBean();
        UserDao dao = new UserDao() {

            @Override
            public User find(Long id) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public void create(User user) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void update(User user) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void delete(User user) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public User findByUsername(String username) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public List<User> findAll() {
                return Collections.emptyList();
            }

            @Override
            public List<User> findAllOfRole(Role customer) {
                List<User> users = new ArrayList<>();
                users.add(new User());
                return users;
            }

            @Override
            public List<User> findAllCustomersNotMemberOfCompany(String companyName) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public List<User> findByFilter(String username, String email, String firstName,
                            String surename, Role role) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public List<User> findByFilter(UserFilterParameters userFilter) {
                // TODO Auto-generated method stub
                return null;
            }
            
        };
        service.setUserDao(dao);
        assertNotNull(service.findAllCustomer());
        assertEquals(1, service.findAllCustomer().size());
    }

}
