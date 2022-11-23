package edu.ctda.cindy.data.dao;

import java.util.List;
import java.util.UUID;

import edu.ctda.cindy.domain.EventDTO;

public interface EventDAO {
	
	void create (EventDTO event);
	void update (EventDTO event);
	void delete (UUID id);
	List<EventDTO> find (EventDTO event);

}
