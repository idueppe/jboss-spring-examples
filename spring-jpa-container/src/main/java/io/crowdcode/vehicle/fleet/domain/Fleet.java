package io.crowdcode.vehicle.fleet.domain;

import io.crowdcode.vehicle.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.persistence.Version;


@Entity
@NamedQueries(
	{
	     @NamedQuery(name=Fleet.FIND_BY_COMPANY_NAME, query="SELECT f FROM Fleet f WHERE f.companyName = :companyName"),
	     @NamedQuery(name=Fleet.FIND_ALL, query="SELECT f FROM Fleet f"),
	     @NamedQuery(name=Fleet.FIND_ALL_COMPANY_NAMES, query="SELECT f.companyName FROM Fleet f"),
	     @NamedQuery(name=Fleet.ENGINE_REPORT, query=
	            " SELECT "+
	     		" new io.crowdcode.vehicle.fleet.domain.EngineInfo(v.engine.type, COUNT(v)) " +
	     		" FROM Fleet f INNER JOIN f.vehicles AS v" +
	     		" WHERE f.companyName = :companyName" +
	     		" GROUP BY v.engine.type" 
	     		)
	     
	}
)
public class Fleet {
    
    public static final String FIND_BY_COMPANY_NAME = "Fleet.findByCompanyName";
    public static final String FIND_ALL = "Fleet.findAll";
    public static final String FIND_ALL_COMPANY_NAMES = "Fleet.findAllCompanyNames";
    public static final String ENGINE_REPORT = "Fleet.EngineReport";
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=true)
	private String companyName;
	
	@Transient
	private int nameLength;

	
	@ManyToMany(cascade={CascadeType.MERGE})
	private List<Vehicle> vehicles;
	
	@Version
	private long version;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<Vehicle> getVehicles() {
		if (vehicles == null) {
			vehicles = new ArrayList<Vehicle>();
		}
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

    public void delete(Vehicle vehicle) {
        getVehicles().remove(vehicle);
    }


}
