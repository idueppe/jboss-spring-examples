package io.crowdcode.vehicle.dao;

import io.crowdcode.vehicle.domain.ApplicationLog;

import java.util.List;

public interface ApplicationLogDao {

    public void log(ApplicationLog logEntry);
    
    public List<ApplicationLog> findAll();
}
