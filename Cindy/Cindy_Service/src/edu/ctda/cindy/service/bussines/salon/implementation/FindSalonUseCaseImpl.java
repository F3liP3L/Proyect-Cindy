package edu.ctda.cindy.service.bussines.salon.implementation;

import java.util.List;

import edu.ctda.cindy.data.dao.factory.DAOFactory;
import edu.ctda.cindy.domain.SalonDTO;
import edu.ctda.cindy.service.bussines.salon.FindSalonUseCase;

public class FindSalonUseCaseImpl implements FindSalonUseCase {
	
	private final DAOFactory factory;
	
	public FindSalonUseCaseImpl(DAOFactory factory) {
		this.factory = factory;
	}

	@Override
	public List<SalonDTO> execute() {
		return factory.getSalonDAO().find();
	}

}
