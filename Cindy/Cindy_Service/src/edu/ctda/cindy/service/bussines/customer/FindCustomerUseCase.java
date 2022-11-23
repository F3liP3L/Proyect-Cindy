package edu.ctda.cindy.service.bussines.customer;

import java.util.List;

import edu.ctda.cindy.domain.CustomerDTO;

public interface FindCustomerUseCase {

	List<CustomerDTO> execute(CustomerDTO customer);
	
}
