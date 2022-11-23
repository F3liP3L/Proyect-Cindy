package edu.ctda.cindy.service.command.implementation;

import edu.ctda.cindy.crosscutting.exception.CindyCustomException;
import edu.ctda.cindy.crosscutting.exception.service.ServiceCustomException;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.data.dao.factory.DAOFactory;
import edu.ctda.cindy.data.enumeration.DAOFactoryType;
import edu.ctda.cindy.domain.EventDTO;
import edu.ctda.cindy.service.bussines.event.CreateEventUseCase;
import edu.ctda.cindy.service.bussines.event.implementation.CreateEventUseCaseImpl;
import edu.ctda.cindy.service.command.CreateEventCommand;

public class CreateEventCommandImpl implements CreateEventCommand{

	private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
	private final CreateEventUseCase useCase = new CreateEventUseCaseImpl(factory);
	
	@Override
	public void execute(EventDTO event) {
		try {
			factory.openConnection();
			factory.initTransaction();
			useCase.execute(event);
			factory.confirmTransaction();
		} catch (ServiceCustomException exception) {
			factory.cancelTransaction();
			throw exception;
		} catch (CindyCustomException exception) {
			factory.cancelTransaction();
			if(exception.isTechnicalException()) {
			throw ServiceCustomException.wrapException(Messages.CreateEventUseCaseImpl.BUSSINES_EVENT_EXIST_IN_DATE, exception);
			}
		} catch (Exception exception) {
			factory.cancelTransaction();
			throw ServiceCustomException.createBussinesException(Messages.CreateEventUseCaseImpl.BUSSINES_EVENT_UNEXPECTED, exception);
		} finally {
			factory.closeConnection();
		}
		
	}

}
