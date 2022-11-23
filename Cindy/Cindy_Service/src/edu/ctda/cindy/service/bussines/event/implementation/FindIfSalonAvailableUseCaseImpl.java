package edu.ctda.cindy.service.bussines.event.implementation;

import java.util.List;

import edu.ctda.cindy.data.dao.factory.DAOFactory;
import edu.ctda.cindy.data.enumeration.DAOFactoryType;
import edu.ctda.cindy.domain.EventDTO;
import edu.ctda.cindy.domain.SalonDTO;
import edu.ctda.cindy.service.bussines.event.FindEventUseCase;
import edu.ctda.cindy.service.bussines.event.FindIfSalonAvailableUseCase;


public class FindIfSalonAvailableUseCaseImpl implements FindIfSalonAvailableUseCase {

	private FindEventUseCase findEventUseCase;
	
	
	public FindIfSalonAvailableUseCaseImpl(DAOFactory factory) {
		this.findEventUseCase = new FindEventUseCaseImpl(factory);
	}

	@Override
	public List<EventDTO> execute(SalonDTO salon) {
		
		final EventDTO eventFind = EventDTO.create(salon);
	
		final List<EventDTO> results = findEventUseCase.execute(eventFind);
		
		return results;
	}
	
	private static boolean SalonIsAvailability (List<EventDTO> events) {
		boolean exist = false;
		if(events.stream().filter(EventDTO::isState).toList().isEmpty()) {
			exist = true;
		}
		return exist;
	}
	
	
	
	public static void main(String[] args) {
		FindIfSalonAvailableUseCaseImpl findIfSalonAv = new FindIfSalonAvailableUseCaseImpl(DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL));
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
		SalonDTO salon = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL).getSalonDAO().find().get(0);
		System.out.println("el salon es: " + salon.getname());
		/*factory.getEventDAO().find(null).forEach(elem -> System.out.println(elem.getName()));*/
		findIfSalonAv.execute(salon).forEach(elem -> System.out.println(elem.getName()));
	}

}
