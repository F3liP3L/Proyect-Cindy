package edu.ctda.cindy.crosscutting.helper;

import java.util.UUID;

import edu.ctda.cindy.crosscutting.exception.CrosscuttingCustomException;
import edu.ctda.cindy.crosscutting.messages.Messages;

import static edu.ctda.cindy.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public final class UUIDHelper {

	private static final String DEFAULT_UUID_AS_STRING = "00000000-0000-0000-0000-000000000000";
	private static final UUID DEFAULT_UUID = UUID.fromString(DEFAULT_UUID_AS_STRING);

	private UUIDHelper() {
		super();
	}

	public static final UUID getDefaultUUID(final UUID value) {
		return getDefaultIfNull(value, DEFAULT_UUID);
	}

	public static final UUID getNewUUID() {
		UUID uuid;
		do {
			uuid = UUID.randomUUID();
		} while (isDefaultUUID(uuid));

		return uuid;
	}

	public static final String getUUIDAsString(final UUID value) {
		return getDefaultUUID(value).toString();
	}

	public static final UUID getUUIDFromString (final String value) {
		try {
		return UUID.fromString(StringHelper.getDefaultString(value, DEFAULT_UUID_AS_STRING));
		} catch (IllegalArgumentException exception) {
			throw CrosscuttingCustomException.createTechnicalException(Messages.UUIDHelper.TECHNICAL_UUID_FROM_STRING_INVALID, exception);
		} catch (Exception exception) {
			throw CrosscuttingCustomException.createTechnicalException(Messages.UUIDHelper.TECHNICAL_UUID_FROM_STRING_UNEXPECTED_ERROR, exception);
		}
	}

	public static final boolean isDefaultUUID(final UUID value) {
		return DEFAULT_UUID.equals(value);
	}

}
