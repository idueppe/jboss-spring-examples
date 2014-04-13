package io.crowdcode.vehicle.dao.spi.jpa;

import io.crowdcode.vehicle.dao.VehicleDao;
import io.crowdcode.vehicle.domain.EngineType;
import io.crowdcode.vehicle.domain.Vehicle;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class VehicleJpaDao implements VehicleDao {
    
    @PersistenceContext(unitName="vehicle-foundation")
    private EntityManager em;
    
    public VehicleJpaDao() {}
    
    public VehicleJpaDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Vehicle> findAll() {
        TypedQuery<Vehicle> query = em.createNamedQuery("vehicleFindAll", Vehicle.class);
        return query.getResultList();
    }

    @Override
    public Vehicle find(Long id) {
        return em.find(Vehicle.class, id);
    }

    @Override
    public void create(Vehicle vehicle) {
        em.persist(vehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        em.remove(vehicle);
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
        return em.merge(vehicle);
    }

    @Override
    public List<Vehicle> findVehicleByManufacturer(String name) {
        TypedQuery<Vehicle> query = em.createNamedQuery("vehicleFindByManufacturerName", Vehicle.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public Vehicle findCheapestVehicle() {
        TypedQuery<Vehicle> query = em.createNamedQuery("vehicleFindCheapest", Vehicle.class);
        return query.getSingleResult();
    }

    @Override
    public List<Vehicle> findVehiclesByEngineType(EngineType engineType) {
        TypedQuery<Vehicle> query = em.createNamedQuery("vehicleByEngineType", Vehicle.class);
        query.setParameter("engineType", engineType);
        return query.getResultList();
    }
    
}
