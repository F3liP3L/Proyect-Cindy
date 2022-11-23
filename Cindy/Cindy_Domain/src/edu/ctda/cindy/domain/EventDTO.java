package edu.ctda.cindy.domain;

import static edu.ctda.cindy.builder.CustomerDTOBuilder.getCustomerDTOBuilder;
import static edu.ctda.cindy.builder.SalonDTOBuilder.getSalonDTOBuilder;
import static edu.ctda.cindy.crosscutting.helper.DateHelper.getDefaultDate;
import static edu.ctda.cindy.crosscutting.helper.DateHelper.getLocalDateADate;
import static edu.ctda.cindy.crosscutting.helper.ObjectHelper.getDefaultIfNull;
import static edu.ctda.cindy.crosscutting.helper.StringHelper.EMPTY;
import static edu.ctda.cindy.crosscutting.helper.StringHelper.applyTrim;
import static edu.ctda.cindy.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.ctda.cindy.crosscutting.helper.UUIDHelper.getUUIDAsString;
import static edu.ctda.cindy.crosscutting.helper.UUIDHelper.getUUIDFromString;

import java.sql.Date;
import java.util.UUID;

public class EventDTO {

	private UUID id;
	private String name;
	private CustomerDTO customer;
	private Date reservationDate;
	private Date deliveryDate;
	private boolean state;
	private SalonDTO salon;
	
	public EventDTO() {
		setId(getDefaultUUID(id));
		setName(EMPTY);
		setCustomer(getCustomerDTOBuilder().build());
		setReservationDate(getLocalDateADate(getDefaultDate()));
		setDeliveryDate(getLocalDateADate(getDefaultDate()));
		setState(true);
		setSalon(getSalonDTOBuilder().build());
	}

	public EventDTO(final UUID id, final String name, final CustomerDTO customer, final Date reservationDate, final Date deliveryDate, final boolean state,
			final SalonDTO salon) {
		setId(id);
		setName(name);
		setCustomer(customer);
		setReservationDate(reservationDate);
		setDeliveryDate(deliveryDate);
		setState(state);
		setSalon(salon);
	}
	
	public static final EventDTO create(final UUID id, final String name, final CustomerDTO customer, final Date reservationDate, final Date deliveryDate, final boolean state,
			final SalonDTO salon) {
		return new EventDTO(id, name, customer, reservationDate, deliveryDate, state, salon);
	}

	public static final EventDTO create(final String id, final String name, final CustomerDTO customer, final Date reservationDate, final Date deliveryDate, final boolean state,
			final SalonDTO salon) {
		return new EventDTO(getUUIDFromString(id), name, customer, reservationDate, deliveryDate, state, salon);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = getDefaultUUID(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = applyTrim(name);
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = getDefaultIfNull(customer, getCustomerDTOBuilder().build());
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = getDefaultIfNull(reservationDate, getLocalDateADate(getDefaultDate()));
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = getDefaultIfNull(deliveryDate, getLocalDateADate(getDefaultDate()));
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public SalonDTO getSalon() {
		return salon;
	}

	public void setSalon(SalonDTO salon) {
		this.salon = getDefaultIfNull(salon, getSalonDTOBuilder().build());
	}
	
	public String getIdAsString() {
		return getUUIDAsString(getId());
	}

	public static EventDTO create(SalonDTO salon) {
		return new EventDTO(getDefaultUUID(null), EMPTY, getCustomerDTOBuilder().build(), getLocalDateADate(getDefaultDate()), getLocalDateADate(getDefaultDate()), true, salon);
	}

}
