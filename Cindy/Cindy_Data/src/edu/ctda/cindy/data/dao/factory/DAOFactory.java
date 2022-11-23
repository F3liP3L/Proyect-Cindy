package edu.ctda.cindy.data.dao.factory;

import edu.ctda.cindy.crosscutting.exception.data.DataCustomException;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.data.dao.CustomerDAO;
import edu.ctda.cindy.data.dao.EventDAO;
import edu.ctda.cindy.data.dao.SalonDAO;
import edu.ctda.cindy.data.enumeration.DAOFactoryType;

public abstract class DAOFactory {
	
	public static final DAOFactory getDAOFactory(final DAOFactoryType factory) {

		DAOFactory daoFactory;

		switch (factory) {
		case SQLSERVER:
			throw DataCustomException.createTechnicalException(Messages.DAOFactory.TECHNICAL_SQLSERVER_NOT_IMPLEMENTED);
		case CASSANDRA:
			throw DataCustomException.createTechnicalException(Messages.DAOFactory.TECHNICAL_CASSANDRA_NOT_IMPLEMENTED);
		case MARIADB:
			throw DataCustomException.createTechnicalException(Messages.DAOFactory.TECHNICAL_MARIADB_NOT_IMPLEMENTED);
		case MONGODB:
			throw DataCustomException.createTechnicalException(Messages.DAOFactory.TECHNICAL_MONGODB_NOT_IMPLEMENTED);
		case MYSQL:
			throw DataCustomException.createTechnicalException(Messages.DAOFactory.TECHNICAL_MYSQL_NOT_IMPLEMENTED);
		case ORACLE:
			throw DataCustomException.createTechnicalException(Messages.DAOFactory.TECHNICAL_ORACLE_NOT_IMPLEMENTED);
		case POSTGRESQL:
			daoFactory = new PostgresqlDAOFactory();
			break;
		default:
			throw DataCustomException.createTechnicalException(Messages.DAOFactory.TECHNICAL_UNEXPECTED_DAOFACTORY);
		}
		return daoFactory;
	}

	public abstract void openConnection();

	public abstract void initTransaction();

	public abstract void confirmTransaction();

	public abstract void cancelTransaction();

	public abstract void closeConnection();
	
	public abstract CustomerDAO getCustomerDAO();
	
	public abstract EventDAO getEventDAO();
	
	public abstract SalonDAO getSalonDAO();
	
}
