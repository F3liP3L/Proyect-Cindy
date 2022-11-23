package edu.ctda.cindy.service.command;

import edu.ctda.cindy.domain.EventDTO;

public interface UpdateEventCommand {
	
	void execute (EventDTO event);

}
