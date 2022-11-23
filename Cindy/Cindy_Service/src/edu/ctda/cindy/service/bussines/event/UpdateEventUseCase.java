package edu.ctda.cindy.service.bussines.event;

import edu.ctda.cindy.domain.EventDTO;

public interface UpdateEventUseCase {
	
	void execute(EventDTO event);

}
