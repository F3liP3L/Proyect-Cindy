package edu.ctda.cindy.service.bussines.event.implementation;

import java.util.List;

import edu.ctda.cindy.crosscutting.helper.DateHelper;
import edu.ctda.cindy.data.dao.factory.DAOFactory;
import edu.ctda.cindy.domain.EventDTO;
import edu.ctda.cindy.service.bussines.event.FindEventByMonthUseCase;
import edu.ctda.cindy.service.bussines.event.FindEventUseCase;

public class FindEventByMonthUseCaseImpl implements FindEventByMonthUseCase{

	private FindEventUseCase findEventUseCase;
	
	public FindEventByMonthUseCaseImpl(DAOFactory factory) {
		this.findEventUseCase = new FindEventUseCaseImpl(factory);
	}
	
	@Override
	public List<EventDTO> execute(String month) {
		
		return findEventUseCase.execute(EventDTO.create(null)).stream().filter(event -> DateHelper.getDateALocalDate(event.getDeliveryDate()).getMonth().toString().equalsIgnoreCase(month)).toList();
	}

}
