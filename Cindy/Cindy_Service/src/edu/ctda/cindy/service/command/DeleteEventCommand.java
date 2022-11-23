package edu.ctda.cindy.service.command;

import java.util.UUID;

public interface DeleteEventCommand {
	
	void execute (UUID id);

}
