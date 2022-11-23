package edu.ctda.cindy.service.bussines.event;

import java.util.List;

import edu.ctda.cindy.domain.EventDTO;
import edu.ctda.cindy.domain.SalonDTO;

public interface FindIfSalonAvailableUseCase {
	
	List<EventDTO> execute (SalonDTO salon);

}
