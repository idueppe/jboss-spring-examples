package io.crowdcode.vehicle.fleet.domain;

import io.crowdcode.vehicle.domain.EngineType;

public class EngineInfo {

    private EngineType engineType;
    private long quantity;
    
    public EngineInfo(EngineType engineType, long quantity) {
        this.engineType = engineType;
        this.quantity = quantity;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public long getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "EngineInfo [engineType=" + engineType + ", quantity=" + quantity + "]";
    }

}
