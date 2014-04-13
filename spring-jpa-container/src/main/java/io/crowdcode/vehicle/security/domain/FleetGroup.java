package io.crowdcode.vehicle.security.domain;

import io.crowdcode.vehicle.fleet.domain.Fleet;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
        @NamedQuery(name = FleetGroup.FIND_BY_FLEET, query = "SELECT g FROM FleetGroup g WHERE g.fleet = :fleet"),
        @NamedQuery(name = FleetGroup.FIND_BY_COMPANYNAME, query = "SELECT g FROM FleetGroup g WHERE g.fleet.companyName = :companyName") })
public class FleetGroup {

    public static final String FIND_BY_FLEET = "FleetGroup.findByName";
    public static final String FIND_BY_COMPANYNAME = "FleetGroup.findByCompanyName";

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH })
    private List<User> users;

    @OneToOne
    private Fleet fleet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        if (users == null) {
            users = new ArrayList<User>();
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Fleet getFleet() {
        return fleet;
    }

    public void setFleet(Fleet fleet) {
        this.fleet = fleet;
    }

}