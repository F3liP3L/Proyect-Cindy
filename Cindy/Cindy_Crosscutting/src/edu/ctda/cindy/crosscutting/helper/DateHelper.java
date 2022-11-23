package edu.ctda.cindy.crosscutting.helper;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {
	
	private DateHelper() {
		super();
	}
	
	public static final String DEFAULT_DATE = "01/01/0001";
	
	public static final LocalDate getDefaultDate() {
		return LocalDate.parse(DEFAULT_DATE, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public static final LocalDate currentDate() {
		return LocalDate.now();
	}
	
	public static final short getNextYear() {
		return (short) LocalDate.now().plusYears(1).getYear();
	}
	
	public static final Date getLocalDateADate(LocalDate date) {
		return Date.valueOf(date);
	}
	
	public static final LocalDate getDateALocalDate(Date date) {
		return date.toLocalDate();
	}
	
	public static final boolean isBetweenDate(LocalDate initDate, LocalDate endDate, LocalDate compareDate) {
		boolean isBetween = false;
		if (initDate.isBefore(compareDate) && endDate.isAfter(compareDate)) {
			isBetween = true;
		}
		return isBetween;
	}
	
	public static final boolean isBefore(LocalDate initDate, LocalDate endDate) {
		boolean isBefore = false;
		if(initDate.isBefore(endDate)) {
			isBefore = true;
		}
		return isBefore;
	}
	
	public static final boolean isBeforeToday(LocalDate date) {
		return date.isBefore(LocalDate.now());
	}
}
