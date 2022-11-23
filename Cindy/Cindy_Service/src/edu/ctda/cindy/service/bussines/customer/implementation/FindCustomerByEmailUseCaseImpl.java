package edu.ctda.cindy.service.bussines.customer.implementation;

import edu.ctda.cindy.data.dao.factory.DAOFactory;
import edu.ctda.cindy.domain.CustomerDTO;
import edu.ctda.cindy.service.bussines.customer.FindCustomerByEmailUseCase;
import edu.ctda.cindy.service.bussines.customer.FindCustomerUseCase;

public class FindCustomerByEmailUseCaseImpl implements FindCustomerByEmailUseCase {

	private final FindCustomerUseCase findCustomerUseCase;

	public FindCustomerByEmailUseCaseImpl(DAOFactory factory) {
		this.findCustomerUseCase = new FindCustomerUseCaseImpl(factory);
	}

	@Override
	public boolean execute(String email) {

		final CustomerDTO customer = CustomerDTO.create(email);

		return !findCustomerUseCase.execute(customer).isEmpty();
	}

}
