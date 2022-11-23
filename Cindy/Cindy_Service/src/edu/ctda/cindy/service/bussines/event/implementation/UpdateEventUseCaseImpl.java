package edu.ctda.cindy.service.bussines.event.implementation;

import static edu.ctda.cindy.crosscutting.helper.DateHelper.getDateALocalDate;
import static edu.ctda.cindy.crosscutting.helper.DateHelper.isBetweenDate;

import java.util.List;

import edu.ctda.cindy.crosscutting.exception.service.ServiceCustomException;
import edu.ctda.cindy.crosscutting.helper.DateHelper;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.data.dao.factory.DAOFactory;
import edu.ctda.cindy.domain.EventDTO;
import edu.ctda.cindy.service.bussines.event.FindIfSalonAvailableUseCase;
import edu.ctda.cindy.service.bussines.event.UpdateEventUseCase;

public class UpdateEventUseCaseImpl implements UpdateEventUseCase {

	private final DAOFactory factory;
	private final FindIfSalonAvailableUseCase findIfSalonAvailableUseCase;
	
	public UpdateEventUseCaseImpl(DAOFactory factory) {
		this.factory = factory;
		this.findIfSalonAvailableUseCase = new FindIfSalonAvailableUseCaseImpl(factory);
	}

	@Override
	public void execute(EventDTO event) {
		
		List<EventDTO> results = findIfSalonAvailableUseCase.execute(event.getSalon());
		
		if(!checkAvailabilityDates(results, event)) {
			throw ServiceCustomException.createUserException(Messages.UpdateEventUseCaseImpl.BUSSINES_EVENT_EXIST_IN_DATE);
		}
		/*
		event.setName(event.getName());
		event.setReservationDate(event.getReservationDate());
		*/
		
		factory.getEventDAO().update(event);
		
	}
	
	private static boolean checkAvailabilityDates (List<EventDTO> events, EventDTO eventCreate) {
		boolean exist = false;
		List<EventDTO> results = events.stream().filter(event -> isBetweenDate(getDateALocalDate(event.getReservationDate()), getDateALocalDate(event.getDeliveryDate()), getDateALocalDate(eventCreate.getReservationDate())) || DateHelper.isBefore(getDateALocalDate(eventCreate.getDeliveryDate()), getDateALocalDate(event.getReservationDate()))).toList();
		if(results.isEmpty()) {
			exist = true;
		}
		return exist;
	}
	
}
