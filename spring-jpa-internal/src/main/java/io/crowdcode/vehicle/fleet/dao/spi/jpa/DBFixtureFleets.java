package io.crowdcode.vehicle.fleet.dao.spi.jpa;

import io.crowdcode.vehicle.domain.Vehicle;
import io.crowdcode.vehicle.fleet.domain.Fleet;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class DBFixtureFleets {
	
	private static final Logger LOG = Logger.getLogger(DBFixtureFleets.class.getName());
	
	public static final String COMPANY_NAME = "crowdcode";

    @PersistenceUnit(unitName="vehicle-foundation")
    private EntityManagerFactory emf;
    
    private EntityManager em;
    
    private List<Fleet> fleets = new ArrayList<>();

    private Fleet currentFleet;
    
    public void createDefaultDataInDatabase() {
        em = emf.createEntityManager();
    	LOG.info("Creating dummy data...");
        this.createFleet(COMPANY_NAME)
            .addVehicleToFleet("Veyron")
            .addVehicleToFleet("Veyron Diesel")
            .addVehicleToFleet("A4")
            .persistAll();
    }

    public DBFixtureFleets persistAll() {
        beginTx();
        persistAll(fleets);
        commitTx();
        return this;
    }
    
    private DBFixtureFleets clear() {
        fleets.clear();
        currentFleet = null;
        return null;
    }
    
    protected void beginTx() {
        if (em == null) {
            em = emf.createEntityManager();
        }
        em.getTransaction().begin();
    }
    
    protected void commitTx() {
        em.getTransaction().commit();
        em.close();
        em = null;
    }
    
    public DBFixtureFleets unlinkFleets() {
        for (Fleet fleet: fleets) {
            em.merge(fleet);
            fleet.getVehicles().clear();
        }
        return this;
    }

    public DBFixtureFleets removeAll() {
        beginTx();
        TypedQuery<Fleet> query = em.createQuery("SELECT f FROM Fleet f", Fleet.class);
        for (Fleet fleet : query.getResultList()) {
            em.remove(fleet);
        }
        commitTx();
        clear();
        return this;
    }
    
    private void persistAll(List<?> entities) {
        for (Object entity : entities) {
            System.out.println("--- persisting : "+entity);
            em.persist(entity);
        }
    }
    
    public DBFixtureFleets createFleet(String companyName) {
        currentFleet = new Fleet();
        currentFleet.setCompanyName(companyName);
        fleets.add(currentFleet);
        return this;
    }
    
    public DBFixtureFleets addVehicleToFleet(Long vehicleId) {
        Vehicle vehicle = em.find(Vehicle.class, vehicleId);
        currentFleet.getVehicles().add(vehicle);
        return this;
    }
    
    public DBFixtureFleets addVehicleToFleet(String model) {
        TypedQuery<Vehicle> query = em.createQuery("SELECT v FROM Vehicle v WHERE v.model = :model", Vehicle.class);
        query.setParameter("model", model);
        Vehicle vehicle = query.getSingleResult();
        currentFleet.getVehicles().add(vehicle);
        return this;
    }

    public void terminateAllActiveSessionInDB() {
        Query nativeQuery = em.createNativeQuery("SELECT pg_terminate_backend(procpid) FROM pg_stat_activity WHERE datname = 'vehicle-tmp'");
        nativeQuery.executeUpdate();
    }
    
}
