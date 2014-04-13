package io.crowdcode.vehicle.dao.spi.jpa;

import io.crowdcode.vehicle.domain.ApplicationLog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class ApplicationLogDao implements io.crowdcode.vehicle.dao.ApplicationLogDao {

    @PersistenceContext(unitName="vehicle-foundation")
    private EntityManager em;

    @Override
    public void log(ApplicationLog logEntry) {
        em.persist(logEntry);
    }

    @Override
    public List<ApplicationLog> findAll() {
        TypedQuery<ApplicationLog> query = em.createNamedQuery("ApplicationLog.LOAD_ALL", ApplicationLog.class);
        return query.getResultList();
    }
    
}