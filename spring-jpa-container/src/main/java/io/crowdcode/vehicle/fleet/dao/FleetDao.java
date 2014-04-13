package io.crowdcode.vehicle.fleet.dao;

import io.crowdcode.vehicle.dao.EntityDao;
import io.crowdcode.vehicle.fleet.domain.EngineInfo;
import io.crowdcode.vehicle.fleet.domain.Fleet;

import java.util.List;

public interface FleetDao extends EntityDao<Fleet> {

    public Fleet findByCompanyName(String company);

    public List<String> findAllCompanyNames();

    public List<EngineInfo> getEngineReport(String companyName);

}
