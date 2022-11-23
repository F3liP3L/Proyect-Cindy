package edu.ctda.cindy.service.command.implementation;

import edu.ctda.cindy.crosscutting.exception.CindyCustomException;
import edu.ctda.cindy.crosscutting.exception.service.ServiceCustomException;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.data.dao.factory.DAOFactory;
import edu.ctda.cindy.data.enumeration.DAOFactoryType;
import edu.ctda.cindy.domain.CustomerDTO;
import edu.ctda.cindy.service.bussines.customer.CreateCustomerUseCase;
import edu.ctda.cindy.service.bussines.customer.implementation.CreateCustomerUseCaseImpl;
import edu.ctda.cindy.service.command.CreateCustomerCommand;

public class CreateCustomerCommandImpl implements CreateCustomerCommand {
	
	private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
	private final CreateCustomerUseCase useCase = new CreateCustomerUseCaseImpl(factory);

	@Override
	public void execute(CustomerDTO customer) {
		try {
			factory.openConnection();
			factory.initTransaction();
			useCase.execute(customer);
			factory.confirmTransaction();
		} catch (ServiceCustomException exception) {
			factory.cancelTransaction();
			throw exception;
		} catch (CindyCustomException exception) {
			factory.cancelTransaction();
			if(exception.isTechnicalException()) {
			throw ServiceCustomException.wrapException(Messages.CreateCustomerUseCaseImpl.BUSSINES_CUSTOMER_EXISTS, exception);
			}
		} catch (Exception exception) {
			factory.cancelTransaction();
			throw ServiceCustomException.createBussinesException(Messages.CreateCustomerUseCaseImpl.BUSSINES_CUSTOMER_EXISTS, exception);
		} finally {
			factory.closeConnection();
		}
	}
}
