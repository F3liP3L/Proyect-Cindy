package edu.ctda.cindy.service.command;

import edu.ctda.cindy.domain.EventDTO;

public interface CreateEventCommand {
	
	void execute (EventDTO event);

}
