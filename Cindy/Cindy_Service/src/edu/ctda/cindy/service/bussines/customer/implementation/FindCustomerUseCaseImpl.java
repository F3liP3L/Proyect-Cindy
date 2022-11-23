package edu.ctda.cindy.service.bussines.customer.implementation;

import java.util.List;

import edu.ctda.cindy.data.dao.factory.DAOFactory;
import edu.ctda.cindy.domain.CustomerDTO;
import edu.ctda.cindy.service.bussines.customer.FindCustomerUseCase;

public class FindCustomerUseCaseImpl implements FindCustomerUseCase {

	private final DAOFactory factory;
	
	public FindCustomerUseCaseImpl(DAOFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public List<CustomerDTO> execute(CustomerDTO customer) {
		return factory.getCustomerDAO().find(customer);
	}

}
