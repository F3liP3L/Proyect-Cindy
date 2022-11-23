package edu.ctda.cindy.domain;

import java.util.UUID;

import static edu.ctda.cindy.crosscutting.helper.StringHelper.EMPTY;
import static edu.ctda.cindy.crosscutting.helper.StringHelper.applyTrim;
import static edu.ctda.cindy.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.ctda.cindy.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.ctda.cindy.crosscutting.helper.UUIDHelper.getUUIDAsString;
import static edu.ctda.cindy.crosscutting.helper.UUIDHelper.getUUIDFromString;

public class CustomerDTO {
	
	private UUID id;
	private String name;
	private String surname;
	private String phone;
	private String cedula;
	private String email;
	private String password;	
	
	public CustomerDTO() {
		setId(getNewUUID());
		setName(EMPTY);
		setSurname(EMPTY);
		setPhone(EMPTY);
		setCedula(EMPTY);
		setEmail(EMPTY);
		setPassword(EMPTY);
	}
	
	public CustomerDTO(final UUID id, final String name, final String surname, final String phone, final String cedula, final String email,
			final String password) {
		setId(id);
		setName(name);
		setSurname(surname);
		setPhone(phone);
		setCedula(cedula);
		setEmail(email);
		setPassword(password);
	}
	
	public static CustomerDTO create(final UUID id, final String name, final String surname, final String phone, final String cedula, final String email,
			final String password) {
		return new CustomerDTO(id, name, surname, phone, cedula, email, password);
	}
	
	public static CustomerDTO create(final String id, final String name, final String surname, final String phone, final String cedula, final String email,
			final String password) {
		return new CustomerDTO(getUUIDFromString(id), name, surname, phone, cedula, email, password);
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = applyTrim(surname);
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = applyTrim(cedula);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = applyTrim(email);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = applyTrim(password);
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = applyTrim(phone);
	}
	
	public String getIdAsString() {
		return getUUIDAsString(getId());
	}
}
