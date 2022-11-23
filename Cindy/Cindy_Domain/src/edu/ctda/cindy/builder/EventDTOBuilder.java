package edu.ctda.cindy.builder;

import java.sql.Date;
import java.util.UUID;

import edu.ctda.cindy.domain.CustomerDTO;
import edu.ctda.cindy.domain.EventDTO;
import edu.ctda.cindy.domain.SalonDTO;
import static edu.ctda.cindy.crosscutting.helper.UUIDHelper.getDefaultUUID;

public class EventDTOBuilder {

	private UUID id;
	private String name;
	private CustomerDTO customer;
	private Date reservationDate;
	private Date deliveryDate;
	private boolean state;
	private SalonDTO salon;
	
	public EventDTOBuilder() {
		super();
	}
	
	public static final EventDTOBuilder getEventDTOBuilder() {
		return new EventDTOBuilder();
	}

	public final void setId(UUID id) {
		this.id = getDefaultUUID(id);
	}

	public final EventDTOBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public final EventDTOBuilder setCustomer(CustomerDTO customer) {
		this.customer = customer;
		return this;
	}

	public final EventDTOBuilder setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
		return this;
	}

	public final EventDTOBuilder setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
		return this;
	}

	public final EventDTOBuilder setState(boolean state) {
		this.state = state;
		return this;
	}

	public final EventDTOBuilder setSalon(SalonDTO salon) {
		this.salon = salon;
		return this;
	}
	
	public final EventDTO build() {
		return EventDTO.create(id, name, customer, reservationDate, deliveryDate, state, salon);
	}
	
}
