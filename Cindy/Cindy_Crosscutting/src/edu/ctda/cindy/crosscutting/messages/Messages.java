package edu.ctda.cindy.crosscutting.messages;

public class Messages {
	
	private Messages() {
		super();
	}

	public static class DAOFactory {
		
		private DAOFactory() {
			super();
		}
		
		public static final String TECHNICAL_SQLSERVER_NOT_IMPLEMENTED = "DAOFactory for Postgresql is not implemented yet";
		public static final String TECHNICAL_MONGODB_NOT_IMPLEMENTED = "DAOFactory for MongoDB is not implemented yet";
		public static final String TECHNICAL_CASSANDRA_NOT_IMPLEMENTED = "DAOFactory for Cassandra is not implemented yet";
		public static final String TECHNICAL_MARIADB_NOT_IMPLEMENTED = "DAOFactory for MariaDB is not implemented yet";
		public static final String TECHNICAL_MYSQL_NOT_IMPLEMENTED = "DAOFactory for Mysql is not implemented yet";
		public static final String TECHNICAL_ORACLE_NOT_IMPLEMENTED = "DAOFactory for Oracle is not implemented yet";
		public static final String TECHNICAL_POSTGRESQL_NOT_IMPLEMENTED = "DAOFactory for Postgresql is not implemented yet";
		public static final String TECHNICAL_UNEXPECTED_DAOFACTORY = "Unexpected DAOFactory";
		
	}
	
	public static class SqlConnectionHelper {
		
		private SqlConnectionHelper() {
			super();
		}
		
		public static final String TECHNICAL_CONNECTION_IS_NULL = "Connection is null";
		public static final String TECHNICAL_CONNECTION_IS_CLOSED = "Connection is closed";
		public static final String TECHNICAL_CONNECTION_ALREADY_IS_CLOSED = "Connection already is closed";
		public static final String TECHNICAL_PROBLEM_CLOSING_CONNECTION = "There was a problem trying to close connection. Please verify the technicals details";
		public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_INIT_TRANSACTION = "Connection is closed to start a new transaction";
		public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_COMMIT_TRANSACTION = "Connection is closed to commit the current transaction";
		public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_ROLLBACK_TRANSACTION = "Connection is closed to rollback the current transaction";
		public static final String TECHNICAL_PROBLEM_TRY_INIT_TRANSACTION = "There was a problem trying to start the transaction. Please verify the technicals details";
		public static final String TECHNICAL_PROBLEM_TRY_COMMIT_TRANSACTION = "There was a problem trying to commit the current transaction. Please verify the technicals details";
		public static final String TECHNICAL_PROBLEM_TRY_ROLLBACK_TRANSACTION = "There was a problem trying to rollback the current transaction. Please verify the technicals details";
	}
	
	
	public static class PostgresqlDAOFactory {
		
		private PostgresqlDAOFactory() {
			super();
		}		
		
		public static final String TECHNICAL_PROBLEM_INIT_TRANSACTION = "There was a problem trying to init transaction with the current connection in PostgresqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_OPEN_CONNECTION = "There was a problem trying to open connection in PostgresqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_CONFIRM_TRANSACTION = "There was a problem trying to confirm transaction in PostgresqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_CANCEL_TRANSACTION = "There was a problem trying to cancel transaction in PostgresqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_CLOSE_CONNECTION = "There was a problem trying to confirm close connection in PostgresqlDAOFactory";
	}
	
	public static class CustomerPostgresqlDAO {
		
		private CustomerPostgresqlDAO() {
				super();
		}
		
		public static final String TECHNICAL_PROBLEM_CREATE_CUSTOMER = "There was a problem trying to create the desired customer in PostgresqlDAOFactory";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_CUSTOMER = "There was an unexpected problem trying to create the qualification in PostgresqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_UPDATE_CUSTOMER = "There was a problem trying to update the desired customer in PostgresqlDAOFactory";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_CUSTOMER = "There was an unexpected problem trying to create the customer in PostgresqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_FILL_CUSTOMER_DTO = "There was a problem filling customerDTO from the resultSet";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_CUSTOMER_DTO = "There was an unexpected problem trying to filling customerDTO from the resultSet";
		public static final String TECHNICAL_PROBLEM_DELETE_CUSTOMER_DTO = "There was a problem filling customerDTO from the resultSet";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_CUSTOMER_DTO = "There was an unexpected problem trying to filling customerDTO from the resultSet";
		public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to execute query to find the specific customer";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY = "There was an unexpected problem trying to execute query to find the specific customer";
		public static final String TECHNICAL_PROBLEM_SET_PARAMETERS_VALUES_QUERY = "There was a problem trying to execute query to find the specific customer";
		public static final String TECHNICAL_PROBLEM_PREPARED_STATEMENT = "There was a problem trying to prepare the sql statement";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STATEMENT = "There was an unexpected problem trying to prepare the sql statement";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMATERS_VALUES_QUERY = "There was an unexpected problem trying to execute query to find the specific customer";
	}
	
	public static class IdentificationDocumentPostgresqlDAO {
		
		private IdentificationDocumentPostgresqlDAO() {
				super();
		}
		
		public static final String TECHNICAL_PROBLEM_CREATE_IDENTIFICATION_DOCUMENT = "There was a problem trying to create the desired identification document in PostgresqlDAOFactory";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_IDENTIFICATION_DOCUMENT = "There was an unexpected problem trying to create the identification document in PostgresqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_UPDATE_IDENTIFICATION_DOCUMENT = "There was a problem trying to update the desired identification document in PostgresqlDAOFactory";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_IDENTIFICATION_UPDATE_DOCUMENT = "There was an unexpected problem trying to update the identification document in PostgresqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_FILL_IDENTIFICATION_DOCUMENT_DTO = "There was a problem filling identification document DTO from the resultSet";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_IDENTIFICATION_DOCUMENT_DTO = "There was an unexpected problem trying to filling identification document DTO from the resultSet";
		public static final String TECHNICAL_PROBLEM_DELETE_IDENTIFICATION_DOCUMENT_DTO = "There was a problem filling identification document DTO from the resultSet";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_IDENTIFICATION_DOCUMENT_DTO = "There was an unexpected problem trying to filling identification document DTO from the resultSet";
		public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to execute query to find the specific identification document";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY = "There was an unexpected problem trying to execute query to find the specific identification document";
		public static final String TECHNICAL_PROBLEM_SET_PARAMETERS_VALUES_QUERY = "There was a problem trying to execute query to find the specific identification document";
		public static final String TECHNICAL_PROBLEM_PREPARED_STATEMENT = "There was a problem trying to prepare the sql statement";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STATEMENT = "There was an unexpected problem trying to prepare the sql statement";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMATERS_VALUES_QUERY = "There was an unexpected problem trying to execute query to find the specific identification document";
	}
	
	
	public static class SalonPostgresqlDAO {
		
		private SalonPostgresqlDAO() {
				super();
		}
		
		public static final String TECHNICAL_PROBLEM_FILL_SALON = "There was a problem trying to fill a desired salon in PostgresqlDAOFactory ";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_SALON = "There was an unexpected problem trying to fill a salon in PostgresqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to execute query to find the specific salon";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY = "There was an unexpected problem trying to execute query to find the specific serviceType";
		public static final String TECHNICAL_PROBLEM_SET_PARAMETERS_VALUES_QUERY = "There was a problem trying to execute query to find the specific salon";
		public static final String TECHNICAL_PROBLEM_PREPARED_STATEMENT = "There was a problem trying to prepare the sql statement";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMATERS_VALUES_QUERY = "There was an unexpected problem trying to execute query to find the specific serviceType";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STATEMENT = "There was an unexpected problem trying to prepare the sql statement";
		
	}
	
	
	public static class SubserviceTypePostgresqlDAO {
		
		private SubserviceTypePostgresqlDAO() {
				super();
		}
		
		public static final String TECHNICAL_PROBLEM_FILL_SUB_SALON = "There was a problem trying to fill a desired SubserviceType in PostgresqlDAOFactory ";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_SUB_SALON = "There was an unexpected problem trying to fill a sub SubserviceType in PostgresqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to execute query to find the specific SuberviceType";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY = "There was an unexpected problem trying to execute query to find the specific SubserviceType";
		public static final String TECHNICAL_PROBLEM_SET_PARAMETERS_VALUES_QUERY = "There was a problem trying to execute query to find the specific Subsalon";
		public static final String TECHNICAL_PROBLEM_PREPARED_STATEMENT = "There was a problem trying to prepare the sql statement";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMATERS_VALUES_QUERY = "There was an unexpected problem trying to execute query to find the specific serviceType";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STATEMENT = "There was an unexpected problem trying to prepare the sql statement";
	}
	
	
		public static class EventPostgresqlDAO {
	
		private EventPostgresqlDAO() {
				super();
		}
		
		public static final String TECHNICAL_PROBLEM_CREATE_EVENT = "There was a problem trying to create the desired event in PostgresqlDAOFactory ";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_EVENT = "There was an unexpected problem trying to create the event in PostgresqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_UPDATE_EVENT = "There was a problem trying to update the desired event in PostgresqlDAOFactory ";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_EVENT = "There was an unexpected problem trying to create the event in PostgresqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to execute query to find the specific event";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY = "There was an unexpected problem trying to execute query to find the specific event";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETERS_QUERY = "There was an unexpected problem trying to execute query to find the specific event";
		public static final String TECHNICAL_PROBLEM_SET_PARAMETERS_VALUES_QUERY = "There was a problem trying to execute query to find the specific event";
		public static final String TECHNICAL_PROBLEM_PREPARED_STATEMENT = "There was a problem trying to prepare the sql statement";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STATEMENT = "There was an unexpected problem trying to prepare the sql statement";
		public static final String TECHNICAL_PROBLEM_FILL_EVENT_DTO = "There was a problem filling identification event from the resultSet";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_EVENT_DTO = "There was an unexpected problem trying to filling identification event from the resultSet";
		public static final String TECHNICAL_PROBLEM_DELETE_EVENT_DTO = "There was a problem filling identification event from the resultSet";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_EVENT_DTO = "There was an unexpected problem trying to filling identification event from the resultSet";
	}
		
	
	public static class CityPostgresqlDAO {
		
		private CityPostgresqlDAO() {
			super();
		}
		
		public static final String TECHNICAL_PROBLEM_FILL_CITY_DTO = "There was a problem filling cityDTO from the resultSet";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_CITY_DTO = "There was an unexpected problem trying to filling cityDTO from the resultSet";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS_CITY = "There was a unexpected problem trying to recovering results from the resultSet";
		public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to execute query to find the specific city";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY = "There was an unexpected problem trying to execute query to find the specific city";
		public static final String TECHNICAL_PROBLEM_SET_PARAMETERS_VALUES_QUERY = "There was a problem trying to execute query to find the specific city";
		public static final String TECHNICAL_PROBLEM_PREPARED_STATEMENT = "There was a problem trying to prepare the sql statement";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STATEMENT = "There was an unexpected problem trying to prepare the sql statement";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMATERS_VALUES_QUERY = "There was an unexpected problem trying to execute query to find the specific city";
	
	}
	
	
	public static class UUIDHelper {
		
		private UUIDHelper() {
			super();
		}
		
		public static final String TECHNICAL_UUID_FROM_STRING_INVALID = " The UUID to convert doesnot have a valid format";
		public static final String TECHNICAL_UUID_FROM_STRING_UNEXPECTED_ERROR = " There was  convert doesnot have a valid format";
	}

	
	public static class CreateCustomerUseCaseImpl {
		
		private CreateCustomerUseCaseImpl() {
			super();
		}
		
		public static final String BUSSINES_CUSTOMER_EXISTS = " The customer is already registered ";
		public static final String BUSSINES_CUSTOMER_UNEXPECTED = " There was an unexpected trying to register a customer problem";
		
	}
	
	public static class CreateEventUseCaseImpl {
		
		private CreateEventUseCaseImpl() {
			super();
		}
		
		public static final String BUSSINES_EVENT_EXISTS = " The service is already registered ";
		public static final String BUSSINES_EVENT_UNEXPECTED = " There was an unexpected trying to register a service problem";
		
	}
	
	public static class LoginUserUseCaseImpl {
		
		private LoginUserUseCaseImpl() {
			super();
		}
		
		public static final String BUSSINES_CUSTOMER_DOESNT_EXISTS = "The customer trying to log in does not exist ";
		public static final String BUSSINES_CUSTOMER_INCORRECT_PASSWORD = "the password you have entered is incorrect, please enter a valid password ";
		public static final String BUSSINES_CUSTOMER_LOGIN_UNEXPECTED = " There was an unexpected trying to login a customer problem";
		
	}
	
	public static class FindDocumentIdentificationUseCaseImpl {
		
		private FindDocumentIdentificationUseCaseImpl() {
			super();
		}
		
		public static final String BUSSINES_DOCUMENT_IDENTIFICATION_EXISTS = " The identification document already exists, please choose a valid one";
		public static final String BUSSINES_DOCUMENT_IDENTIFICATION_UNEXPECTED = " unexpected problem when trying to register the identification document";
		
	}
	
	public static class FindServiceTypeUseCaseImpl {
		
		private FindServiceTypeUseCaseImpl() {
			super();
		}
		
		public static final String BUSSINES_SALON_UNEXPECTED = " There was an unexpected problem in the FindServiceTypeCommand";
		
	}
	
	
	public static class ResponseUserController {
		
		private ResponseUserController (){
			super();
		}
		
		public static final String CUSTOMER_CREATED_SUCCESSFULLY = "The customer has been created successfully";
		public static final String CUSTOMER_CREATED_ERROR = "There was an error trying created the customer";
		public static final String CUSTOMER_CREATED_UNEXPECTED_ERROR = "There was a unexpected problem trying to created the customer";
		
		public static final String CUSTOMER_LOGIN_SUCCESSFULLY = "The customer has been login successfully";
		public static final String CUSTOMER_LOGIN_ERROR = "There was an error trying login the customer";
		public static final String CUSTOMER_LOGIN_UNEXPECTED_ERROR = "There was a unexpected problem trying to login the customer";
	}
	
	public static class ResponseServiceController {
		
		private ResponseServiceController (){
			super();
		}
		
		public static final String EVENT_CREATED_SUCCESSFULLY = "The service has been created successfully";
		public static final String EVENT_CREATED_ERROR = "There was an error trying created the service";
		public static final String EVENT_CREATED_UNEXPECTED_ERROR = "There was a unexpected problem trying to created the service";

	}
	
	public static class CreateUserValidator {
		
		private CreateUserValidator() {
			super();
		}
		
		public static final String MAIL_IS_DEFAULT_ERROR = "The email you are trying to enter is the default";
		public static final String MAIL_IS_INVALID_FORMAT_ERROR = "The email you are trying to enter has an invalid format";
		public static final String NAME_IS_INVALID_ERROR = "The name does not meet the required number of characters";
		public static final String SURNAME_IS_INVALID_ERROR = "The surname does not meet the required number of characters";
		public static final String PASSWORD_IS_INVALID_ERROR = "the password does not meet the proper length";
	}
	
	public static class CreateServiceValidator {
		
		private CreateServiceValidator() {
			super();
		}
		
		public static final String NAME_IS_INVALID_ERROR = "Name cannot be empty";
		public static final String DESCRIPTION_IS_INVALID_ERROR = "Description cannot be empty";
	}
	
}
