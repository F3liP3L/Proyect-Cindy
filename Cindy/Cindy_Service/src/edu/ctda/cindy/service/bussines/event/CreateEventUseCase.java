package edu.ctda.cindy.service.bussines.event;

import edu.ctda.cindy.domain.EventDTO;

public interface CreateEventUseCase {
	
	void execute(EventDTO event);

}
