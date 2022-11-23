package edu.ctda.cindy.service.command;

import edu.ctda.cindy.domain.CustomerDTO;

public interface CreateCustomerCommand {

	void execute (CustomerDTO customer);

}
