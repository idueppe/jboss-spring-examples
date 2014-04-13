package io.crowdcode.vehicle.service.spi;

import io.crowdcode.vehicle.dao.ManufacturerDao;
import io.crowdcode.vehicle.dao.VehicleDao;
import io.crowdcode.vehicle.domain.EngineType;
import io.crowdcode.vehicle.domain.Manufacturer;
import io.crowdcode.vehicle.domain.Vehicle;
import io.crowdcode.vehicle.service.VehicleObserver;
import io.crowdcode.vehicle.service.VehicleService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleServiceBean implements VehicleService {
    
    @Autowired
    private VehicleDao vehicleDao;
    
    @Autowired
    private ManufacturerDao manufacturerDao;
    
    private List<VehicleObserver> observers = new ArrayList<>();

    @Override
    public Vehicle getCheapestVehicle() {
        return vehicleDao.findCheapestVehicle();
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public List<Vehicle> getVehicleByManufacture(String name) {
        return vehicleDao.findVehicleByManufacturer(name);
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly = true)
    public List<Vehicle> getVehiclesByEngineType(EngineType... engineType) {
        List<Vehicle> vehicles = new LinkedList<>();
        for (EngineType type : engineType) {
            vehicles.addAll(vehicleDao.findVehiclesByEngineType(type));
        }
        
        return vehicles;
    }

    @Override
    @Transactional
    public Vehicle registerVehicle(Vehicle vehicle) {
        vehicleDao.create(vehicle);
        return vehicle;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public Vehicle addVehicleToManufacturer(String manufacturerName, Vehicle vehicle) {
        Manufacturer manufacturer = manufacturerDao.findManufacturerByName(manufacturerName);
        if (vehicle.getId() != null) {
            vehicle = vehicleDao.update(vehicle);
        }
        manufacturer.addVehicle(vehicle);
        return vehicle;
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public Vehicle getVehicleById(Long vehicleId) {
        return vehicleDao.find(vehicleId);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void delete(Long vehicleId) {
        Vehicle vehicle = vehicleDao.find(vehicleId);
        if (vehicle != null) {
            notifyObserversAboutDeletingVehicle(vehicle);
            vehicleDao.delete(vehicle);
        } 
    }
    
    private void notifyObserversAboutDeletingVehicle(Vehicle vehicle) {
        for(VehicleObserver observer : observers) {
            observer.onVehicleDelete(vehicle);
        }
    }
    
    public void registerObserver(VehicleObserver observer) {
        if (observer !=  null) {
            observers.add(observer);
        }
    }
    
}
