package io.crowdcode.vehicle.converter;

import io.crowdcode.vehicle.domain.ApplicationLog;
import io.crowdcode.vehicle.dto.LogEntry;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("logEntryConverter")
public class LogEntryConverter extends AbstractDefaultConverter<ApplicationLog, LogEntry>{

    @Autowired
    private EngineConverter engineConverter;
    
    @Override
    protected LogEntry newTargetInstance() {
        return new LogEntry();
    }

    @Override
    protected void copyProperties(ApplicationLog source, LogEntry target) {
        target.setMessage(source.getMessage());
        target.setTimeStamp(source.getTimestamp());
    }

	@Override
	protected void sort(List<LogEntry> targetList) {
		Collections.sort(targetList, new Comparator<LogEntry>(){

			@Override
			public int compare(LogEntry o1, LogEntry o2) {
				return o2.getTimeStamp().compareTo(o1.getTimeStamp());
			}});
	}

    
    
}
