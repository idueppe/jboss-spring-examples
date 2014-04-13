package io.crowdcode.vehicle.dao;

import io.crowdcode.vehicle.domain.Engine;

import java.util.List;

public interface EngineDao extends EntityDao<Engine>{

	List<Engine> findByManufacturer(String manufacturerName);

}
