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
import edu.ctda.cindy.crosscutting.helper.ObjectHelper;
import edu.ctda.cindy.crosscutting.helper.UUIDHelper;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.data.dao.EventDAO;
import edu.ctda.cindy.data.dao.relational.DAORelational;
import edu.ctda.cindy.domain.CustomerDTO;
import edu.ctda.cindy.domain.EventDTO;
import edu.ctda.cindy.domain.SalonDTO;

public class EventPostgresqlDAO extends DAORelational implements EventDAO {
	
	public EventPostgresqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(EventDTO event) {
		final var sqlInsert = "INSERT INTO evento(codigo, nombre, cliente_codigo, salon_codigo, fecha_reserva, fecha_entrega, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (final var preparedStatement = getConnection().prepareStatement(sqlInsert)) {

			preparedStatement.setString(1, event.getIdAsString());
			preparedStatement.setString(2, event.getName());
			preparedStatement.setString(3, event.getCustomer().getIdAsString());
			preparedStatement.setString(4, event.getSalon().getIdAsString());
			preparedStatement.setDate(5, event.getReservationDate());
			preparedStatement.setDate(6, event.getDeliveryDate());
			preparedStatement.setBoolean(7, event.isState());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			throw DataCustomException.createTechnicalException(Messages.CustomerPostgresqlDAO.TECHNICAL_PROBLEM_CREATE_CUSTOMER, exception);
		} catch (final Exception exception) {
			throw DataCustomException.createTechnicalException(
					Messages.CustomerPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_CUSTOMER, exception);
		}
		
	}

	@Override
	public void update(EventDTO event) {
		final var sqlUpdate = "UPDATE evento SET nombre=?, salon_codigo = ?, fecha_reserva = ?, fecha_entrega = ?, estado = ? WHERE codigo = ?";
		try (final var preparedStatement = getConnection().prepareStatement(sqlUpdate)) {
			preparedStatement.setString(1, event.getName());
			preparedStatement.setString(2, event.getSalon().getIdAsString());
			preparedStatement.setDate(3, event.getReservationDate());
			preparedStatement.setDate(4, event.getDeliveryDate());
			preparedStatement.setBoolean(5, event.isState());
			preparedStatement.setString(6, event.getIdAsString());
			
			preparedStatement.executeUpdate();
			
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
		final var sqlDelete = "DELETE FROM evento WHERE codigo = ?";
		final var idAsString = getUUIDAsString(id);

		try (final var preparedStatement = getConnection().prepareStatement(sqlDelete)) {
			preparedStatement.setString(1, idAsString);
			
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			throw DataCustomException
					.createTechnicalException(Messages.EventPostgresqlDAO.TECHNICAL_PROBLEM_DELETE_EVENT_DTO, exception);
		} catch (Exception exception) {
			throw DataCustomException.createTechnicalException(
					Messages.EventPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_EVENT_DTO, exception);
		}
	}

	@Override
	public List<EventDTO> find(EventDTO event) {
		
		var parameters = new ArrayList<Object>();

		final var sqlBuilder = new StringBuilder();

		createSelectFrom(sqlBuilder);

		createWhere(sqlBuilder, event, parameters);

		createOrderBy(sqlBuilder);

		return prepareAndExecuteQuery(sqlBuilder, parameters);
	}

	private final List<EventDTO> prepareAndExecuteQuery(StringBuilder sqlBuilder, List<Object> parameters) {
			try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {

				setParameterValues(preparedStatement, parameters);

				return executeQuery(preparedStatement);

			} catch (final DataCustomException exception) {
				throw exception;
			} catch (final SQLException exception) {
				throw DataCustomException.createTechnicalException(
						Messages.EventPostgresqlDAO.TECHNICAL_PROBLEM_PREPARED_STATEMENT, exception);
			} catch (final Exception exception) {
				throw DataCustomException.createTechnicalException(
						Messages.EventPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STATEMENT, exception);
			}
		}

		private final List<EventDTO> executeQuery(PreparedStatement preparedStatement) {
			try (final var resultSet = preparedStatement.executeQuery()) {

				return fillResults(resultSet);

			} catch (DataCustomException exception) {
				throw exception;
			} catch (SQLException exception) {
				throw DataCustomException
						.createTechnicalException(Messages.EventPostgresqlDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
			} catch (Exception exception) {
				throw DataCustomException.createTechnicalException(
						Messages.EventPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY, exception);
			}
		}

		private final void createOrderBy(final StringBuilder stringBuilder) {
			stringBuilder.append("Order By E.nombre ASC ");
		}

		private final void setParameterValues(final PreparedStatement preparedStatement, final List<Object> parameters) {
			try {
				for (int index = 0; index < parameters.size(); index++) {
					preparedStatement.setObject(index + 1, parameters.get(index));
				}
			} catch (final SQLException exception) {
				throw DataCustomException.createTechnicalException(
						Messages.EventPostgresqlDAO.TECHNICAL_PROBLEM_SET_PARAMETERS_VALUES_QUERY, exception);
			} catch (final Exception exception) {
				throw DataCustomException.createTechnicalException(
						Messages.EventPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETERS_QUERY, exception);
			}
		}

		private final List<EventDTO> fillResults(final ResultSet resultset) {
			try {
				var results = new ArrayList<EventDTO>();
				while (resultset.next()) {
					results.add(fillEventDTO(resultset));
				}
				return results;
			} catch (final DataCustomException exception) {
				throw exception;
			} catch (final SQLException exception) {
				throw DataCustomException.createTechnicalException(
						Messages.EventPostgresqlDAO.TECHNICAL_PROBLEM_CREATE_EVENT, exception);
			} catch (final Exception exception) {
				throw DataCustomException.createTechnicalException(
						Messages.EventPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_EVENT,
						exception);
			}
		}

		private final EventDTO fillEventDTO(final ResultSet resultset) {
			try {
				return EventDTO.create(resultset.getString("idEvent"), resultset.getString("eventName"), fillCustomerDTO(resultset),
						resultset.getDate("reservationDate"), resultset.getDate("deliveryDate"), resultset.getBoolean("state"), fillSalonDTO(resultset));
			} catch (final DataCustomException exception) {
				throw exception;
			} catch (final SQLException exception) {
				throw DataCustomException
						.createTechnicalException(Messages.EventPostgresqlDAO.TECHNICAL_PROBLEM_FILL_EVENT_DTO, exception);
			} catch (final Exception exception) {
				throw DataCustomException.createTechnicalException(
						Messages.EventPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_EVENT_DTO, exception);
			}
		}
		
		private final CustomerDTO fillCustomerDTO(final ResultSet resultset) {
			try {
				return CustomerDTO.create(resultset.getString("idCustomer"), resultset.getString("customerName"),
						resultset.getString("customerSurname"), resultset.getString("phone"), resultset.getString("cedula"), resultset.getString("email"), resultset.getString("password"));
			} catch (final DataCustomException exception) {
				throw exception;
			} catch (final SQLException exception) {
				throw DataCustomException
						.createTechnicalException(Messages.EventPostgresqlDAO.TECHNICAL_PROBLEM_FILL_EVENT_DTO, exception);
			} catch (final Exception exception) {
				throw DataCustomException.createTechnicalException(
						Messages.EventPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_EVENT_DTO, exception);
			}
		}
		
		private final SalonDTO fillSalonDTO (final ResultSet resultset) {
			try {
				return SalonDTO.create(resultset.getString("idSalon"), resultset.getString("salonName"), resultset.getString("salonAddress"));
			} catch (final DataCustomException exception) {
				throw exception;
			} catch (final SQLException exception) {
				throw DataCustomException.createTechnicalException(Messages.SalonPostgresqlDAO.TECHNICAL_PROBLEM_FILL_SALON, exception);
			} catch (final Exception exception) {
				throw DataCustomException.createTechnicalException(Messages.SalonPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_SALON, exception);
			}
		}


		private final void createSelectFrom(final StringBuilder sqlBuilder) {
			sqlBuilder.append("SELECT E.codigo AS idEvent, ");
			sqlBuilder.append("		  E.nombre AS eventName,  ");
			sqlBuilder.append("		  cliente_codigo AS idCustomer,  ");
			sqlBuilder.append("		  salon_codigo AS idSalon,  ");
			sqlBuilder.append("		  fecha_reserva AS reservationDate,  ");
			sqlBuilder.append("	      fecha_entrega AS deliveryDate,   ");
			sqlBuilder.append("		  estado AS state, ");
			sqlBuilder.append("       C.nombre AS customerName, ");
			sqlBuilder.append("       C.apellido AS customerSurname, ");
			sqlBuilder.append("       C.dni AS cedula, ");
			sqlBuilder.append("       C.correo AS email, ");
			sqlBuilder.append("       C.clave AS password, ");
			sqlBuilder.append("       C.telefono AS phone, ");
			sqlBuilder.append("       S.nombre AS salonName, ");
			sqlBuilder.append("       S.direccion AS salonAddress ");
			sqlBuilder.append("FROM evento E ");
			sqlBuilder.append("INNER JOIN cliente C ");
			sqlBuilder.append("ON E.cliente_codigo = C.codigo ");
			sqlBuilder.append("INNER JOIN salon S ");
			sqlBuilder.append("ON S.codigo = E.salon_codigo ");
		}

		private final void createWhere(final StringBuilder sqlBuilder, final EventDTO event, final List<Object> parameters) {

			var setWhere = true;

			if (!ObjectHelper.isNull(event)) {

				if (!UUIDHelper.isDefaultUUID(event.getId())) {
					sqlBuilder.append("WHERE E.codigo = ? ");
					setWhere = false;
					parameters.add(event.getIdAsString());
				}

				if (!UUIDHelper.isDefaultUUID(event.getCustomer().getId())) {
					sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("E.cliente_codigo = ? ");
					setWhere = false;
					parameters.add(event.getCustomer().getIdAsString());
				}
				
				if (!UUIDHelper.isDefaultUUID(event.getSalon().getId())) {
					sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("E.salon_codigo = ? ");
					parameters.add(event.getSalon().getIdAsString());
				}
			}
		}
}
