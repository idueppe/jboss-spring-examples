package io.crowdcode.vehicle.service.spi;

import io.crowdcode.vehicle.converter.LogEntryConverter;
import io.crowdcode.vehicle.dao.ApplicationLogDao;
import io.crowdcode.vehicle.domain.ApplicationLog;
import io.crowdcode.vehicle.dto.LogEntry;
import io.crowdcode.vehicle.service.ApplicationLogService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("applicationLog")
@Transactional(propagation=Propagation.SUPPORTS)
public class ApplicationLogServiceBean implements ApplicationLogService {

    @Autowired
    private ApplicationLogDao dao;
    
    @Autowired
    private LogEntryConverter logEntryConverter;
    
    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void log(String message) {
        dao.log(new ApplicationLog(message));
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public List<LogEntry> logEntries() {
        return logEntryConverter.convert(dao.findAll());
    }
    
}
