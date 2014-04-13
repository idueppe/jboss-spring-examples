package io.crowdcode.vehicle.security.dao.spi;

import io.crowdcode.vehicle.fleet.domain.Fleet;
import io.crowdcode.vehicle.security.dao.FleetGroupDao;
import io.crowdcode.vehicle.security.domain.FleetGroup;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class FleetGroupDaoBean implements FleetGroupDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public FleetGroup findGroup(Long id) {
        return em.find(FleetGroup.class, id);
    }

    @Override
    public void create(FleetGroup group) {
        em.persist(group);
    }

    @Override
    public void update(FleetGroup group) {
        em.merge(group);
    }

    @Override
    public void remove(FleetGroup group) {
        em.remove(group);
    }

    @Override
    public FleetGroup findGroupByFleet(Fleet fleet) {
        TypedQuery<FleetGroup> query = em.createNamedQuery(FleetGroup.FIND_BY_FLEET, FleetGroup.class);
        query.setParameter("fleet", fleet);
        return query.getSingleResult();
    }

    @Override
    public FleetGroup findGroupByCompanyName(String companyName) {
        TypedQuery<FleetGroup> query = em.createNamedQuery(FleetGroup.FIND_BY_COMPANYNAME, FleetGroup.class);
        query.setParameter("companyName", companyName);
        return query.getSingleResult();
    }

}
