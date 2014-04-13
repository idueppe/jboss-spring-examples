package io.crowdcode.vehicle.dao.spi.jpa;

import io.crowdcode.vehicle.dao.EngineDao;
import io.crowdcode.vehicle.domain.Engine;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class EngineJpaDao implements EngineDao {

    @PersistenceContext(unitName="vehicle-foundation")
    private EntityManager em;

    public EngineJpaDao() {
    }
    
    @Override
    public List<Engine> findAll() {
        TypedQuery<Engine> query = em.createQuery("SELECT e FROM Engine e", Engine.class);
        return query.getResultList();
    }
    
	@Override
	public List<Engine> findByManufacturer(String manufacturerName) {
		TypedQuery<Engine> query = em.createQuery("SELECT e FROM Engine e WHERE e.manufacturer.name = :name", Engine.class);
		query.setParameter("name", manufacturerName);
		return query.getResultList();
	}


    @Override
    public Engine find(Long id) {
        return em.find(Engine.class, id);
    }

    @Override
    public void create(Engine entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Engine entity) {
        em.remove(entity);
    }

    @Override
    public Engine update(Engine entity) {
        return em.merge(entity);
    }


}
