package edu.ctda.cindy.domain;

import static edu.ctda.cindy.crosscutting.helper.StringHelper.EMPTY;
import static edu.ctda.cindy.crosscutting.helper.StringHelper.applyTrim;
import static edu.ctda.cindy.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.ctda.cindy.crosscutting.helper.UUIDHelper.getUUIDAsString;
import static edu.ctda.cindy.crosscutting.helper.UUIDHelper.getUUIDFromString;

import java.util.UUID;

public class SalonDTO {
	
	private UUID id;
	private String name;
	private String address;
	
	public SalonDTO() {
		setId(getDefaultUUID(id));
		setAddress(EMPTY);
		setName(EMPTY);
	}
	
	public SalonDTO(UUID id, String name, String address) {
		setId(id);
		setName(name);
		setAddress(address);
	}

	public static SalonDTO create(final UUID id, final String name, final String address) {
		return new SalonDTO(id, name, address);
	}
	
	public static SalonDTO create(final String id, final String name, final String address) {
		return new SalonDTO(getUUIDFromString(id), name, address);
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = getDefaultUUID(id);
	}

	public String getname() {
		return name;
	}

	public void setName(String name) {
		this.name = applyTrim(name);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = applyTrim(address);
	}

	public String getIdAsString() {
		return getUUIDAsString(getId());
	}
}