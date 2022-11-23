package edu.ctda.cindy.service.command.implementation;

import java.util.ArrayList;
import java.util.List;

import edu.ctda.cindy.crosscutting.exception.CindyCustomException;
import edu.ctda.cindy.crosscutting.exception.service.ServiceCustomException;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.data.dao.factory.DAOFactory;
import edu.ctda.cindy.data.enumeration.DAOFactoryType;
import edu.ctda.cindy.domain.SalonDTO;
import edu.ctda.cindy.service.bussines.salon.FindSalonUseCase;
import edu.ctda.cindy.service.bussines.salon.implementation.FindSalonUseCaseImpl;
import edu.ctda.cindy.service.command.FindSalonCommand;

public class FindSalonCommandImpl implements FindSalonCommand{
	
	private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
	private final FindSalonUseCase useCase = new FindSalonUseCaseImpl(factory);

	@Override
	public List<SalonDTO> execute() {
		List<SalonDTO> result = new ArrayList<>();
		try {
			factory.openConnection();
			result.addAll(useCase.execute());
		} catch (ServiceCustomException exception) {
			factory.cancelTransaction();
			throw exception;
		} catch (CindyCustomException exception) {
			factory.cancelTransaction();
			if(exception.isTechnicalException()) {
			throw ServiceCustomException.wrapException(Messages.FindSalonUseCaseImpl.BUSSINES_SALON_UNEXPECTED, exception);
			}
		} catch (Exception exception) {
			factory.cancelTransaction();
			throw ServiceCustomException.createBussinesException(Messages.FindSalonUseCaseImpl.BUSSINES_SALON_UNEXPECTED, exception);
		} finally {
			factory.closeConnection();
		}
		return result;
	}

}
