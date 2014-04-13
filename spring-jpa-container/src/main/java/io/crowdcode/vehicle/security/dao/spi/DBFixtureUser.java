package io.crowdcode.vehicle.security.dao.spi;

import io.crowdcode.vehicle.fleet.domain.Fleet;
import io.crowdcode.vehicle.security.domain.FleetGroup;
import io.crowdcode.vehicle.security.domain.Role;
import io.crowdcode.vehicle.security.domain.User;

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
public class DBFixtureUser {
	
	public static final String COMPANY_NAME = "crowdcode";

    private static final Logger LOG = Logger.getLogger(DBFixtureUser.class.getName());

    @PersistenceUnit(unitName="vehicle-foundation")
    private EntityManagerFactory emf;
    
    private EntityManager em;
    
    private List<User> users = new ArrayList<User>();
    private List<FleetGroup> groups = new ArrayList<FleetGroup>();

    public User currentUser;
    private FleetGroup currentGroup;
    
    public void createDefaultDataInDatabase() {
    	LOG.info("Creating user data...");
        if (em == null)
            em = emf.createEntityManager();
        this.createUser("admin", "admin@junit")
            .setRole(Role.ADMIN)
            .createUser("agent", "agent@junit")
            .setRole(Role.AGENT)
            .createUser("customer", "customer@junit")
            .setRole(Role.CUSTOMER)
            .createFleetGroup()
            .addFleet(COMPANY_NAME)
            .addUser(0)
            .addUser(1)
            .addUser(2)
            .persistAll();
    }

    public DBFixtureUser persistAll() {
        beginTx();
        persistAll(users);
        persistAll(groups);
        commitTx();
        return this;
    }
    
    private DBFixtureUser clear() {
        users.clear();
        groups.clear();
        return this;
    }

    public DBFixtureUser removeAll() {
        beginTx();
        List<FleetGroup> groups = em.createQuery("SELECT g FROM FleetGroup g", FleetGroup.class).getResultList();
        for (FleetGroup group : groups) {
            group.getUsers().clear();
            group.setFleet(null);
            em.flush();
        }
        em.createQuery("DELETE FROM FleetGroup").executeUpdate();
        em.createQuery("DELETE FROM User").executeUpdate();
        clear();
        commitTx();
        return this;
    }
    
    private void persistAll(List<?> entities) {
        for (Object entity : entities) {
            System.out.println("--- persisting : "+entity);
            em.persist(entity);
        }
    }
    
    protected void beginTx() {
        if (em == null)
            em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    protected void commitTx() {
        em.getTransaction().commit();
        em.close();
        em = null;
    }
    
    
    public DBFixtureUser createUser(String username, String email) {
        currentUser = new User();
        currentUser.setUsername(username);
        currentUser.setEmail(email);
        users.add(currentUser);
        return this;
    }
    
    public DBFixtureUser setRole(Role role)  {
        currentUser.setRole(role);
        return this;
    }
    
    public DBFixtureUser createFleetGroup() {
        currentGroup = new FleetGroup();
        groups.add(currentGroup);
        return this;
    }
    
    public DBFixtureUser addFleet(String companyName) {
        TypedQuery<Fleet> query = em.createNamedQuery(Fleet.FIND_BY_COMPANY_NAME, Fleet.class);
        query.setParameter("companyName", companyName);
        currentGroup.setFleet(query.getSingleResult());
        return this;
    }
    
    public DBFixtureUser addUser(int index) {
        currentGroup.getUsers().add(users.get(index));
        return this;
    }
    
    public void terminateAllActiveSessionInDB() {
        Query nativeQuery = em.createNativeQuery("SELECT pg_terminate_backend(procpid) FROM pg_stat_activity WHERE datname = 'vehicle-tmp'");
        nativeQuery.executeUpdate();
    }
    

}
