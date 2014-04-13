package io.crowdcode.vehicle.service;

import io.crowdcode.vehicle.dto.LogEntry;

import java.util.List;

public interface ApplicationLogService {
    
    public void log(String message);
    
    public List<LogEntry> logEntries();

}
