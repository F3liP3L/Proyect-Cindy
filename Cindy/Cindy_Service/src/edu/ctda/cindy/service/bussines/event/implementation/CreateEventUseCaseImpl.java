package edu.ctda.cindy.service.bussines.event.implementation;

import static edu.ctda.cindy.crosscutting.helper.DateHelper.getDateALocalDate;
import static edu.ctda.cindy.crosscutting.helper.DateHelper.getLocalDateADate;
import static edu.ctda.cindy.crosscutting.helper.DateHelper.rangesOverlap;

import java.util.List;

import edu.ctda.cindy.crosscutting.exception.service.ServiceCustomException;
import edu.ctda.cindy.crosscutting.helper.UUIDHelper;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.data.dao.factory.DAOFactory;
import edu.ctda.cindy.domain.EventDTO;
import edu.ctda.cindy.service.bussines.event.CreateEventUseCase;
import edu.ctda.cindy.service.bussines.event.FindIfSalonAvailableUseCase;

public class CreateEventUseCaseImpl implements CreateEventUseCase{

	private final DAOFactory factory;
	private final FindIfSalonAvailableUseCase findIfSalonAvailableUseCase;
	
	public CreateEventUseCaseImpl(DAOFactory factory) {
		this.factory = factory;
		this.findIfSalonAvailableUseCase = new FindIfSalonAvailableUseCaseImpl(factory);
	}


	@Override
	public void execute(EventDTO event) {
		
		List<EventDTO> results = findIfSalonAvailableUseCase.execute(event.getSalon());
		
		event.setReservationDate(getLocalDateADate(getDateALocalDate(event.getReservationDate()).plusDays(1)));
		event.setDeliveryDate(getLocalDateADate(getDateALocalDate(event.getDeliveryDate()).plusDays(1)));
		
		if(!checkAvailabilityDates(results, event)) {
			throw ServiceCustomException.createUserException(Messages.CreateEventUseCaseImpl.BUSSINES_EVENT_EXIST_IN_DATE);
		}
		
		event.setId(UUIDHelper.getNewUUID());
		
		
		factory.getEventDAO().create(event);
	}
	
	private static boolean checkAvailabilityDates (List<EventDTO> events, EventDTO event) {
		boolean exist = false;
		List<EventDTO> results = events.stream().filter(evt -> rangesOverlap(getDateALocalDate(evt.getReservationDate()),getDateALocalDate(evt.getDeliveryDate()), getDateALocalDate(event.getReservationDate()), getDateALocalDate(event.getDeliveryDate())) && evt.isState()).toList();
		if(results.isEmpty()) {
			exist = true;
		}
		return exist;
	}

}
