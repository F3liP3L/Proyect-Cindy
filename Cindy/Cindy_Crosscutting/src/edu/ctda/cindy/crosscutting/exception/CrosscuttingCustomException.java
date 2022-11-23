package edu.ctda.cindy.crosscutting.exception;

import static edu.ctda.cindy.crosscutting.helper.StringHelper.EMPTY;

import edu.ctda.cindy.crosscutting.exception.enumeration.LayerException;

public class CrosscuttingCustomException extends CindyCustomException {

	private static final long serialVersionUID = 1560390210752726252L;


	private CrosscuttingCustomException(final String userMessage, final String technicalMessage,
			final Exception rootException) {
			super(userMessage, technicalMessage, rootException, LayerException.CROSSCUTTING);
	}

	public static final CindyCustomException createUserException(final String userMessage) {
		return new CrosscuttingCustomException(userMessage, userMessage, new Exception());
	}
	
	public static final CindyCustomException createTechnicalException (final String technicalMessage) {
		return new CrosscuttingCustomException(EMPTY, technicalMessage, new Exception());
	}
	
	public static final CindyCustomException createTechnicalException (final String technicalMessage,
			final Exception rootException) {
		return new CrosscuttingCustomException(EMPTY, technicalMessage, rootException);
	}
	
	public static final CindyCustomException create(final String userMessage, final String technicalMessage) {
		return new CrosscuttingCustomException(userMessage, technicalMessage, new Exception());
	}
	
	public static final CindyCustomException create (final String userMessage, final String technicalMessage,
			final Exception rootException) {
		return new CrosscuttingCustomException(userMessage, userMessage, rootException);
	}
}
