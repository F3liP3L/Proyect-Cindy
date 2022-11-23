package edu.ctda.cindy.service.bussines.event;

import java.util.List;

import edu.ctda.cindy.domain.EventDTO;

public interface FindEventUseCase {
	
	List<EventDTO> execute(EventDTO event);

}
