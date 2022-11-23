package edu.ctda.cindy.service.command.implementation;

import java.util.UUID;

import edu.ctda.cindy.crosscutting.exception.CindyCustomException;
import edu.ctda.cindy.crosscutting.exception.service.ServiceCustomException;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.data.dao.factory.DAOFactory;
import edu.ctda.cindy.data.enumeration.DAOFactoryType;
import edu.ctda.cindy.service.bussines.event.DeleteEventUseCase;
import edu.ctda.cindy.service.bussines.event.implementation.DeleteEventUseCaseImpl;
import edu.ctda.cindy.service.command.DeleteEventCommand;

public class DeleteEventCommandImpl implements DeleteEventCommand {

	private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
	private final DeleteEventUseCase useCase = new DeleteEventUseCaseImpl(factory);
	
	@Override
	public void execute(UUID id) {
		try {
			factory.openConnection();
			factory.initTransaction();
			useCase.execute(id);
			factory.confirmTransaction();
		} catch (ServiceCustomException exception) {
			factory.cancelTransaction();
			throw exception;
		} catch (CindyCustomException exception) {
			factory.cancelTransaction();
			if(exception.isTechnicalException()) {
			throw ServiceCustomException.wrapException(Messages.DeleteEventUseCaseImpl.BUSSINES_EVENT_DELETE_EVENT, exception);
			}
		} catch (Exception exception) {
			factory.cancelTransaction();
			throw ServiceCustomException.createBussinesException(Messages.DeleteEventUseCaseImpl.BUSSINES_EVENT_DELETE_UNEXPECTED, exception);
		} finally {
			factory.closeConnection();
		}
	}
		
}
