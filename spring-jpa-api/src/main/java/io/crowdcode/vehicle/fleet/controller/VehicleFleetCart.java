package io.crowdcode.vehicle.fleet.controller;

import io.crowdcode.vehicle.dto.FleetVehicleDto;

import java.util.List;

public interface VehicleFleetCart {

    List<FleetVehicleDto> listCart();

    void remove(FleetVehicleDto vehicle);

    void close();

    void add(FleetVehicleDto fleetVehicle);

    void order(String companyName);

}
