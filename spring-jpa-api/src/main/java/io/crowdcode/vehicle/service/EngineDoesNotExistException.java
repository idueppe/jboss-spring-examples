package io.crowdcode.vehicle.service;

public class EngineDoesNotExistException extends ApplicationException {

	private static final long serialVersionUID = 1L;
	
	private Long engineId;
	
	public EngineDoesNotExistException() {}
	
	public EngineDoesNotExistException(Long engineId) {
		super("Engine with id "+engineId+" does not exist.");
		this.engineId = engineId;
	}
	
	public Long getEngineId() {
		return engineId;
	}

}
