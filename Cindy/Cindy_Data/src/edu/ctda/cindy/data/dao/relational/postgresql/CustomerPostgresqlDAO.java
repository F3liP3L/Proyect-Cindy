package edu.ctda.cindy.data.dao.relational.postgresql;

import static edu.ctda.cindy.crosscutting.helper.UUIDHelper.getUUIDAsString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.ctda.cindy.crosscutting.exception.data.DataCustomException;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.data.dao.CustomerDAO;
import edu.ctda.cindy.data.dao.relational.DAORelational;
import edu.ctda.cindy.domain.CustomerDTO;

public class CustomerPostgresqlDAO extends DAORelational implements CustomerDAO {

	public CustomerPostgresqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(CustomerDTO customer) {
		final var sqlInsert = "INSERT INTO cliente(codigo, dni, apellido, nombre, correo, clave, telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (final var preparedStatement = getConnection().prepareStatement(sqlInsert)) {

			preparedStatement.setString(1, customer.getIdAsString());
			preparedStatement.setString(2, customer.getCedula());
			preparedStatement.setString(3, customer.getSurname());
			preparedStatement.setString(4, customer.getName());
			preparedStatement.setString(5, customer.getEmail());
			preparedStatement.setString(6, customer.getPassword());
			preparedStatement.setString(7, customer.getPhone());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			throw DataCustomException.createTechnicalException(Messages.CustomerPostgresqlDAO.TECHNICAL_PROBLEM_CREATE_CUSTOMER, exception);
		} catch (final Exception exception) {
			throw DataCustomException.createTechnicalException(
					Messages.CustomerPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_CUSTOMER, exception);
		}
	}

	@Override
	public void update(CustomerDTO customer) {
		final var sqlUpdate = "UPDATE cliente SET dni=?, apellido=?, nombre=?, correo=?, clave=?, telefono=? WHERE codigo = ?";
		try (final var preparedStatement = getConnection().prepareStatement(sqlUpdate)) {
			preparedStatement.setString(1, customer.getCedula());
			preparedStatement.setString(2, customer.getSurname());
			preparedStatement.setString(3, customer.getName());
			preparedStatement.setString(4, customer.getEmail());
			preparedStatement.setString(5, customer.getPassword());
			preparedStatement.setString(6, customer.getPhone());
			preparedStatement.setString(7, customer.getIdAsString());
			
		} catch (SQLException exception) {
			throw DataCustomException
					.createTechnicalException(Messages.CustomerPostgresqlDAO.TECHNICAL_PROBLEM_UPDATE_CUSTOMER, exception);
		} catch (Exception exception) {
			throw DataCustomException.createTechnicalException(
					Messages.CustomerPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_CUSTOMER, exception);
		}
		
	}

	@Override
	public void delete(UUID id) {
		final var sqlDelete = "DELETE FROM cliente WHERE codigo = ?";
		final var idAsString = getUUIDAsString(id);

		try (final var preparedStatement = getConnection().prepareStatement(sqlDelete)) {
			preparedStatement.setString(1, idAsString);
		} catch (SQLException exception) {
			throw DataCustomException
					.createTechnicalException(Messages.CustomerPostgresqlDAO.TECHNICAL_PROBLEM_DELETE_CUSTOMER_DTO, exception);
		} catch (Exception exception) {
			throw DataCustomException.createTechnicalException(
					Messages.CustomerPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_CUSTOMER_DTO, exception);
		}
		
	}

	@Override
	public List<CustomerDTO> find(CustomerDTO customer) {

		final var sqlBuilder = new StringBuilder();

		createSelectFrom(sqlBuilder);

		createOrderBy(sqlBuilder);

		return prepareAndExecuteQuery(sqlBuilder);
	}
	
	private final void createSelectFrom(final StringBuilder sqlBuilder) {
		sqlBuilder.append("SELECT codigo AS idCustomer, ");
		sqlBuilder.append("        dni AS cedula,   ");
		sqlBuilder.append("        nombre AS name,  ");
		sqlBuilder.append("        apellido AS surname,  ");
		sqlBuilder.append("        correo AS email, ");
		sqlBuilder.append("        clave AS password, ");
		sqlBuilder.append("        telefono AS phone ");
		sqlBuilder.append("        FROM cliente ");
	}
	

	private final List<CustomerDTO> prepareAndExecuteQuery(StringBuilder sqlBuilder) {
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
		stringBuilder.append("Order By name ASC ");
	}

	private final List<CustomerDTO> fillResults (final ResultSet resultset) {
		try {
			var results = new ArrayList<CustomerDTO>();
			while(resultset.next()){
				results.add(fillCustomerDTO(resultset));
			}
			return results;
		} catch (final DataCustomException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataCustomException.createTechnicalException(Messages.CustomerPostgresqlDAO.TECHNICAL_PROBLEM_FILL_CUSTOMER_DTO, exception);
		} catch (final Exception exception) {
			throw DataCustomException.createTechnicalException(Messages.CustomerPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_CUSTOMER_DTO, exception);
		}
	}

	private final List<CustomerDTO> executeQuery(PreparedStatement preparedStatement){
		try (final var resultSet = preparedStatement.executeQuery()) {

			return fillResults(resultSet);

		} catch (DataCustomException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw DataCustomException.createTechnicalException(Messages.CustomerPostgresqlDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY ,exception);
		} catch (Exception exception) {
			throw DataCustomException.createTechnicalException(Messages.CustomerPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY,exception);
		}
	}	
	
	private final CustomerDTO fillCustomerDTO(final ResultSet resultset) {
		try {
			return CustomerDTO.create(resultset.getString("idCustomer"), resultset.getString("name"),
					resultset.getString("surname"), resultset.getString("phone"), resultset.getString("cedula"), resultset.getString("email"), resultset.getString("password"));
		} catch (final DataCustomException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataCustomException
					.createTechnicalException(Messages.CustomerPostgresqlDAO.TECHNICAL_PROBLEM_FILL_CUSTOMER_DTO, exception);
		} catch (final Exception exception) {
			throw DataCustomException.createTechnicalException(
					Messages.CustomerPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_CUSTOMER_DTO, exception);
		}
	}

}
