package io.crowdcode.vehicle.controller.spi;

import io.crowdcode.vehicle.controller.VehicleController;
import io.crowdcode.vehicle.converter.VehicleConverter;
import io.crowdcode.vehicle.domain.Engine;
import io.crowdcode.vehicle.domain.Vehicle;
import io.crowdcode.vehicle.dto.VehicleDto;
import io.crowdcode.vehicle.service.ApplicationLogService;
import io.crowdcode.vehicle.service.VehicleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleControllerBean implements VehicleController {

    @Autowired
    private VehicleService vehicleService;
    
    @Autowired
    private VehicleConverter vehicleConverter;
    
    @Autowired
    private ApplicationLogService log;
    
    @Override
    @Transactional
    public List<VehicleDto> findVehicleByManufacturer(String manufacturerName) {
        List<Vehicle> vehicles = vehicleService.getVehicleByManufacture(manufacturerName);
        return vehicleConverter.convert(vehicles);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void saveOrUpdateVehicle(VehicleDto vehicleDto) {
        log.log("saving or updating a vehicle...");
        if (vehicleDto.getId() != null) {
            updateExistingVehicle(vehicleDto);
        } else {
            createNewVehicle(vehicleDto);
        }
    }

    private void createNewVehicle(VehicleDto vehicleDto) {
        // DTO -> DOMAIN
        Vehicle vehicle;
        vehicle = new Vehicle();
        vehicle.setConstructionDate(vehicleDto.getConstructionDate());
        vehicle.setModel(vehicleDto.getModelName());
        
        Engine engine = new Engine();
        engine.setType(vehicleDto.getEngine().getEngineType());
        vehicle.setEngine(engine);
        String manufacturerName = vehicleDto.getManufacturerName();
        // CALL SERVICE
        vehicleService.registerVehicle(vehicle);
        vehicleService.addVehicleToManufacturer(manufacturerName, vehicle);
        // DOMAIN -> DTO
    }

    private void updateExistingVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleDto.getId());
        vehicle.setConstructionDate(vehicleDto.getConstructionDate());
        vehicle.setModel(vehicleDto.getModelName());
    }

    @Override
    public void deleteVehicle(VehicleDto vehicle) {
        vehicleService.delete(vehicle.getId());
    }

    @Override
    public VehicleDto getVehicle(Long id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        return vehicleConverter.convert(vehicle);
    }

}
