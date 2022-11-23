package edu.ctda.cindy.crosscutting.exception.service;

import static edu.ctda.cindy.crosscutting.helper.StringHelper.EMPTY;

import edu.ctda.cindy.crosscutting.exception.CindyCustomException;
import edu.ctda.cindy.crosscutting.exception.enumeration.LayerException;

public class ServiceCustomException extends CindyCustomException {
	
	private static final long serialVersionUID = 2004123920866285366L;

	private ServiceCustomException(String userMessage, String technicalMessage, Throwable rootException) {
			super(userMessage, technicalMessage, rootException, LayerException.SERVICE);
	}
	
	public static final CindyCustomException createUserException(final String userMessage) {
		return new ServiceCustomException(userMessage, userMessage, new Exception());
	}
	
	public static final CindyCustomException createTechnicalException (final String technicalMessage) {
		return new ServiceCustomException(EMPTY, technicalMessage, new Exception());
	}
	
	public static final CindyCustomException createBussinesException (final String bussineslMessage,
			final Exception rootException) {
		return new ServiceCustomException(EMPTY, bussineslMessage, rootException);
	}
	
	public static final CindyCustomException wrapException(final String message, final CindyCustomException exception) {
		if(exception.isTechnicalException()) {
			return ServiceCustomException.createBussinesException(message, exception);
		}
		return exception;
	}
	
	
	public static final CindyCustomException create(final String userMessage, final String technicalMessage) {
		return new ServiceCustomException(userMessage, technicalMessage, new Exception());
	}
	
	public static final CindyCustomException create (final String userMessage, final String technicalMessage,
			final Exception rootException) {
		return new ServiceCustomException(userMessage, userMessage, rootException);
	}
	
}
