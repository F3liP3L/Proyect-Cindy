package edu.ctda.cindy.service.bussines.event.implementation;

import static edu.ctda.cindy.crosscutting.helper.DateHelper.getDateALocalDate;
import static edu.ctda.cindy.crosscutting.helper.DateHelper.isBetweenDate;

import java.util.List;

import edu.ctda.cindy.crosscutting.exception.service.ServiceCustomException;
import edu.ctda.cindy.crosscutting.helper.DateHelper;
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
		
		// verificar si el salon no se encuentra reservado para la fecha dispuesta.
		
		List<EventDTO> results = findIfSalonAvailableUseCase.execute(event.getSalon());
		
		if(!checkAvailabilityDates(results, event)) {
			throw ServiceCustomException.createUserException(Messages.CreateEventUseCaseImpl.BUSSINES_EVENT_EXIST_IN_DATE);
		}
		
		event.setId(UUIDHelper.getNewUUID());
		
		factory.getEventDAO().create(event);
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
