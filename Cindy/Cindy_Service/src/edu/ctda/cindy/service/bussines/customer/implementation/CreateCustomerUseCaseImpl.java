package edu.ctda.cindy.service.bussines.customer.implementation;

import edu.ctda.cindy.crosscutting.exception.service.ServiceCustomException;
import edu.ctda.cindy.crosscutting.helper.UUIDHelper;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.data.dao.factory.DAOFactory;
import edu.ctda.cindy.domain.CustomerDTO;
import edu.ctda.cindy.service.bussines.customer.CreateCustomerUseCase;
import edu.ctda.cindy.service.bussines.customer.FindCustomerByEmailUseCase;

public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {
	
	private final DAOFactory factory;
	private final FindCustomerByEmailUseCase findCustomerByEmail;
	
	public CreateCustomerUseCaseImpl(DAOFactory factory) {
		this.factory = factory;
		this.findCustomerByEmail = new FindCustomerByEmailUseCaseImpl(factory);
	}

	@Override
	public void execute(CustomerDTO customer) {
		
		validateUserExits(customer);
		
		customer.setId(UUIDHelper.getNewUUID());
		
		factory.getCustomerDAO().create(customer);
		
	}
	
	private void validateUserExits(CustomerDTO customer) {

		boolean exist = findCustomerByEmail.execute(customer.getEmail());
		if (exist) {
			throw ServiceCustomException.createUserException(Messages.CreateCustomerUseCaseImpl.BUSSINES_CUSTOMER_EXISTS_MAIL);
		}
	}
	
}
