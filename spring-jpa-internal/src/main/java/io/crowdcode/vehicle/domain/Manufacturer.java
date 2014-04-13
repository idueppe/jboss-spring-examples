package io.crowdcode.vehicle.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = Manufacturer.FIND_BY_NAME, query = "SELECT m FROM Manufacturer m WHERE m.name = :name"),
        @NamedQuery(name = Manufacturer.FIND_BY_ENGINE_TYPE, query = "SELECT m FROM Manufacturer AS m JOIN m.vehicles AS v WHERE v.engine.type IN (:engineType)") })
public class Manufacturer extends AbstractEntity {

    public static final String FIND_BY_NAME = "Manufacturer.findByName";
    public static final String FIND_BY_ENGINE_TYPE = "Manufacturer.findByENgineType";

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "manufacturer", cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
            CascadeType.DETACH, CascadeType.MERGE })
    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToMany(mappedBy = "manufacturer", cascade = { CascadeType.ALL })
    private List<Engine> ownedEngines = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle != null) {
            vehicle.setManufacturer(this);
            vehicles.add(vehicle);
        }
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Manufacturer other = (Manufacturer) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public List<Engine> getOwnedEngines() {
        return ownedEngines;
    }

    public void setOwnedEngines(List<Engine> engines) {
        this.ownedEngines = engines;
    }

    @Override
    public String toString() {
        return "Manufacturer [name=" + name + "]";
    }

}
