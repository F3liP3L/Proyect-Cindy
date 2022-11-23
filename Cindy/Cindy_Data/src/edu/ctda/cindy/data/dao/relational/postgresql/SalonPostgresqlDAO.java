package edu.ctda.cindy.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ctda.cindy.crosscutting.exception.data.DataCustomException;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.data.dao.SalonDAO;
import edu.ctda.cindy.data.dao.relational.DAORelational;
import edu.ctda.cindy.domain.SalonDTO;

public class SalonPostgresqlDAO extends DAORelational implements SalonDAO {

	public SalonPostgresqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public List<SalonDTO> find() {
		
		final var sqlBuilder = new StringBuilder();

		createSelectFrom(sqlBuilder);

		createOrderBy(sqlBuilder);

		return prepareAndExecuteQuery(sqlBuilder);
	}
	
	private final void createSelectFrom(final StringBuilder sqlBuilder) {
		sqlBuilder.append("SELECT  codigo AS idSalon, ");
		sqlBuilder.append("        nombre AS salonName,  ");
		sqlBuilder.append("        direccion AS address ");
		sqlBuilder.append("        FROM salon ");
	}
	

	private final List<SalonDTO> prepareAndExecuteQuery(StringBuilder sqlBuilder) {
		try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())){

			return executeQuery(preparedStatement);

		}  catch (final DataCustomException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataCustomException.createTechnicalException(Messages.SalonPostgresqlDAO.TECHNICAL_PROBLEM_FILL_SALON, exception);
		} catch (final Exception exception) {
			throw DataCustomException.createTechnicalException(Messages.SalonPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_SALON, exception);
		}
	}

	private final void createOrderBy(final StringBuilder stringBuilder) {
		stringBuilder.append("Order By salonName ASC ");
	}

	private final List<SalonDTO> fillResults (final ResultSet resultset) {
		try {
			var results = new ArrayList<SalonDTO>();
			while(resultset.next()){
				results.add(fillSalonDTO(resultset));
		}
			return results;
		} catch (final DataCustomException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataCustomException.createTechnicalException(Messages.SalonPostgresqlDAO.TECHNICAL_PROBLEM_FILL_SALON, exception);
		} catch (final Exception exception) {
			throw DataCustomException.createTechnicalException(Messages.SalonPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_SALON, exception);
		}
	}

	private final List<SalonDTO> executeQuery(PreparedStatement preparedStatement){
		try (final var resultSet = preparedStatement.executeQuery()) {

			return fillResults(resultSet);

		} catch (DataCustomException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw DataCustomException.createTechnicalException(Messages.SalonPostgresqlDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY ,exception);
		} catch (Exception exception) {
			throw DataCustomException.createTechnicalException(Messages.SalonPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY,exception);
		}
	}

	private final SalonDTO fillSalonDTO (final ResultSet resultset) {
		try {
			return SalonDTO.create(resultset.getString("idSalon"), resultset.getString("salonName"), resultset.getString("address"));
		} catch (final DataCustomException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataCustomException.createTechnicalException(Messages.SalonPostgresqlDAO.TECHNICAL_PROBLEM_FILL_SALON, exception);
		} catch (final Exception exception) {
			throw DataCustomException.createTechnicalException(Messages.SalonPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_SALON, exception);
		}
	}


	
}
