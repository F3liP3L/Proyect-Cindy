package edu.ctda.cindy.service.bussines.event.implementation;

import java.util.UUID;

import edu.ctda.cindy.data.dao.factory.DAOFactory;
import edu.ctda.cindy.service.bussines.event.DeleteEventUseCase;

public class DeleteEventUseCaseImpl implements DeleteEventUseCase {

	private final DAOFactory factory;
	
	public DeleteEventUseCaseImpl(DAOFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void execute(UUID id) {
		factory.getEventDAO().delete(id);
	}
	

}
