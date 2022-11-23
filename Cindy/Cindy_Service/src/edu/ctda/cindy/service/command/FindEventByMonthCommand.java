package edu.ctda.cindy.service.command;

import java.util.List;

import edu.ctda.cindy.domain.EventDTO;

public interface FindEventByMonthCommand {
	
	List<EventDTO> execute (String month);

}
