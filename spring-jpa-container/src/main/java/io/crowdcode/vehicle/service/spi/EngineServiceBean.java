package io.crowdcode.vehicle.service.spi;

import io.crowdcode.vehicle.dao.EngineDao;
import io.crowdcode.vehicle.domain.Engine;
import io.crowdcode.vehicle.service.EngineDoesNotExistException;
import io.crowdcode.vehicle.service.EngineService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("engineService")
public class EngineServiceBean implements EngineService {

	@Autowired
	private EngineDao engineDao;
	
	@Override
	public List<Engine> getAllEngines() {
		return engineDao.findAll();
	}

	@Override
	public List<Engine> findByManufacturer(String manufacturerName) {
		return engineDao.findByManufacturer(manufacturerName);
	}

	@Override
	@Transactional
	public void delete(Long engineId) throws EngineDoesNotExistException {
		Engine engine = engineDao.find(engineId);
		if (engine == null) {
			throw new EngineDoesNotExistException(engineId);
		}
		engineDao.delete(engine);
	}
	
	@Override
	public void createEngine(Engine engine) {
		engineDao.create(engine);
	}

}
