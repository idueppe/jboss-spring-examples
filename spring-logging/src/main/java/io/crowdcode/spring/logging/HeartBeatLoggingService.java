package io.crowdcode.spring.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

@Service
public class HeartBeatLoggingService {
	
	private static final Logger log = Logger.getLogger(HeartBeatLoggingService.class.getName());
	
	private volatile boolean shutdown = false;
	
	@PostConstruct
	public void setup()
	{
		new Thread() {
			@Override
			public void run() {
				while (!shutdown)
				{
					System.out.println("\b\bB sysout Message");
					log.log(Level.FINE, " A fine Message");
					log.log(Level.FINEST, "A finest Message");
					log.log(Level.INFO, " An info Message");
					log.log(Level.WARNING, "A warning Message");
					log.log(Level.SEVERE, "A severe Message");
					
					org.slf4j.LoggerFactory.getLogger("HeartBeat").warn("A warning by slf4j api");
					
					try {
						Thread.yield();
						Thread.sleep(15000);
					} catch (InterruptedException e) {
						log.log(Level.SEVERE, "A servere Message" + e.getMessage());
					}
				}
			}
			
		}.start();
	}
	
	@PreDestroy
	public void shutdown()
	{
		shutdown = true;
	}
	

}
