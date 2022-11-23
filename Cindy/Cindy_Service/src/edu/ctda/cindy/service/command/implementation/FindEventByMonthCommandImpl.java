package edu.ctda.cindy.service.command.implementation;

import java.util.ArrayList;
import java.util.List;

import edu.ctda.cindy.crosscutting.exception.CindyCustomException;
import edu.ctda.cindy.crosscutting.exception.service.ServiceCustomException;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.data.dao.factory.DAOFactory;
import edu.ctda.cindy.data.enumeration.DAOFactoryType;
import edu.ctda.cindy.domain.EventDTO;
import edu.ctda.cindy.service.bussines.event.FindEventByMonthUseCase;
import edu.ctda.cindy.service.bussines.event.implementation.FindEventByMonthUseCaseImpl;
import edu.ctda.cindy.service.command.FindEventByMonthCommand;

public class FindEventByMonthCommandImpl implements FindEventByMonthCommand {
	
	private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
	private final FindEventByMonthUseCase useCase = new FindEventByMonthUseCaseImpl(factory);
	
	@Override
	public List<EventDTO> execute(String month) {
		List<EventDTO> result = new ArrayList<>();
		try {
			factory.openConnection();
			result.addAll(useCase.execute(month));
		} catch (ServiceCustomException exception) {
			factory.cancelTransaction();
			throw exception;
		} catch (CindyCustomException exception) {
			factory.cancelTransaction();
			if(exception.isTechnicalException()) {
			throw ServiceCustomException.wrapException(Messages.FindEventByMonthUseCaseImpl.BUSSINES_EVENT_MONTH_UNEXPECTED, exception);
			}
		} catch (Exception exception) {
			factory.cancelTransaction();
			throw ServiceCustomException.createBussinesException(Messages.FindEventByMonthUseCaseImpl.BUSSINES_EVENT_MONTH_UNEXPECTED, exception);
		} finally {
			factory.closeConnection();
		}
		return result;
	}

}
