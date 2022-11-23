package edu.ctda.cindy.data.dao;

import java.util.List;
import java.util.UUID;

import edu.ctda.cindy.domain.CustomerDTO;

public interface CustomerDAO {
	
	void create (CustomerDTO customer);
	void update (CustomerDTO customer);
	void delete (UUID id);
	List<CustomerDTO> find(CustomerDTO customer);

}
