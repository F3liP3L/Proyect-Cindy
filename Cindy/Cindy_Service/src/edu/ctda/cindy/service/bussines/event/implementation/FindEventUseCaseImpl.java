package edu.ctda.cindy.service.bussines.event.implementation;

import java.util.List;

import edu.ctda.cindy.data.dao.factory.DAOFactory;
import edu.ctda.cindy.domain.EventDTO;
import edu.ctda.cindy.service.bussines.event.FindEventUseCase;

public class FindEventUseCaseImpl implements FindEventUseCase {
	
	private final DAOFactory factory;
	
	public FindEventUseCaseImpl(DAOFactory factory) {
		this.factory = factory;
	}

	@Override
	public List<EventDTO> execute(EventDTO event) {
		return factory.getEventDAO().find(event);
	}

}
