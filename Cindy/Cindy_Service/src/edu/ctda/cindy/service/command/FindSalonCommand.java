package edu.ctda.cindy.service.command;

import java.util.List;

import edu.ctda.cindy.domain.SalonDTO;

public interface FindSalonCommand {
	
	List<SalonDTO> execute();

}
