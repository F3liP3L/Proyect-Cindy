package edu.ctda.cindy.data.dao.relational;

import java.sql.Connection;

import edu.ctda.cindy.crosscutting.exception.data.DataCustomException;
import edu.ctda.cindy.crosscutting.helper.SqlConnectionHelper;
import edu.ctda.cindy.crosscutting.messages.Messages;

public class DAORelational {

	private Connection connection;

	protected DAORelational(final Connection connection) {
		if(!SqlConnectionHelper.connectionIsOpen(connection)) {
			throw DataCustomException.createTechnicalException(Messages.SqlConnectionHelper.TECHNICAL_CONNECTION_IS_CLOSED);
		}
		this.connection = connection;
	}

	protected final Connection getConnection() {
		return connection;
	}

}
