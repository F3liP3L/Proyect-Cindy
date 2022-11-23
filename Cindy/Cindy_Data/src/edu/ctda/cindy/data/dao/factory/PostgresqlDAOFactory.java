package edu.ctda.cindy.data.dao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.ctda.cindy.crosscutting.exception.CindyCustomException;
import edu.ctda.cindy.crosscutting.exception.CrosscuttingCustomException;
import edu.ctda.cindy.crosscutting.exception.data.DataCustomException;
import edu.ctda.cindy.crosscutting.helper.SqlConnectionHelper;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.data.dao.CustomerDAO;
import edu.ctda.cindy.data.dao.EventDAO;
import edu.ctda.cindy.data.dao.SalonDAO;
import edu.ctda.cindy.data.dao.relational.postgresql.CustomerPostgresqlDAO;
import edu.ctda.cindy.data.dao.relational.postgresql.EventPostgresqlDAO;
import edu.ctda.cindy.data.dao.relational.postgresql.SalonPostgresqlDAO;

public class PostgresqlDAOFactory extends DAOFactory {
	
private Connection connection;

	PostgresqlDAOFactory(){
		super();
	}

	@Override
	public void openConnection() {
		String url = "jdbc:postgresql://localhost/cindy";
		String user = "postgres";
		String password = "Nomic230s";
		  try {
	          connection = DriverManager.getConnection(url, user, password);
	        } catch (CindyCustomException exception) {
	            throw exception;
	        } catch (SQLException exception) {
	        	throw DataCustomException.createTechnicalException(Messages.PostgresqlDAOFactory.TECHNICAL_PROBLEM_OPEN_CONNECTION, exception);
	        }
	}

	@Override
	public void initTransaction() {
		try {
			SqlConnectionHelper.initTransaction(connection);
		} catch(CrosscuttingCustomException exception) {
			throw DataCustomException.createTechnicalException(Messages.PostgresqlDAOFactory.TECHNICAL_PROBLEM_INIT_TRANSACTION, exception);
		}
	}

	@Override
	public void confirmTransaction() {
		try {
			SqlConnectionHelper.commitTransaction(connection);
		} catch (CrosscuttingCustomException exception) {
			throw DataCustomException.createTechnicalException(Messages.PostgresqlDAOFactory.TECHNICAL_PROBLEM_CONFIRM_TRANSACTION, exception);
		}
	}

	@Override
	public void cancelTransaction() {
		try {
			SqlConnectionHelper.rollbackTransaction(connection);
		} catch (CrosscuttingCustomException exception) {
			throw DataCustomException.createTechnicalException(Messages.PostgresqlDAOFactory.TECHNICAL_PROBLEM_CANCEL_TRANSACTION, exception);
		}
	}

	@Override
	public void closeConnection() {
		try {
			SqlConnectionHelper.closeConnection(connection);	
		} catch (CrosscuttingCustomException exception) {
			throw DataCustomException.createTechnicalException(Messages.PostgresqlDAOFactory.TECHNICAL_PROBLEM_CLOSE_CONNECTION,exception);
		}
	}

	@Override
	public CustomerDAO getCustomerDAO() {
		return new CustomerPostgresqlDAO(connection);
	}

	@Override
	public EventDAO getEventDAO() {
		return new EventPostgresqlDAO(connection);
	}

	@Override
	public SalonDAO getSalonDAO() {
		return new SalonPostgresqlDAO(connection);
	}
}
