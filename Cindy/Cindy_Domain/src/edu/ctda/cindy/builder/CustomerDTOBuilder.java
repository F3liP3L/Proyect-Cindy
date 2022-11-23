package edu.ctda.cindy.builder;

import static edu.ctda.cindy.crosscutting.helper.UUIDHelper.getDefaultUUID;

import java.util.UUID;

import edu.ctda.cindy.domain.CustomerDTO;

public class CustomerDTOBuilder {
	
	private UUID id;
	private String name;
	private String surname;
	private String phone;
	private String cedula;
	private String email;
	private String password;	
	
	protected CustomerDTOBuilder() {
		super();
	}
	
	public static final CustomerDTOBuilder getCustomerDTOBuilder() {
		return new CustomerDTOBuilder();
	}

	public void setId(UUID id) {
		this.id = getDefaultUUID(id);
	}

	public final CustomerDTOBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public final CustomerDTOBuilder setSurname(String surname) {
		this.surname = surname;
		return this;
	}

	public final CustomerDTOBuilder setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public final CustomerDTOBuilder setCedula(String cedula) {
		this.cedula = cedula;
		return this;
	}

	public final CustomerDTOBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	public final CustomerDTOBuilder setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public final CustomerDTO build() {
		return CustomerDTO.create(id, name, surname, phone, cedula, email, password);
	}
}
