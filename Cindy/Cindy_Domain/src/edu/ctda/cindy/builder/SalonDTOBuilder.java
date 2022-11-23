package edu.ctda.cindy.builder;

import java.util.UUID;

import edu.ctda.cindy.domain.SalonDTO;

import static edu.ctda.cindy.crosscutting.helper.UUIDHelper.getDefaultUUID;

public class SalonDTOBuilder {
	
	private UUID id;
	private String name;
	private String address;
	
	protected SalonDTOBuilder() {
		super();
	}
	
	public static final SalonDTOBuilder getSalonDTOBuilder() {
		return new SalonDTOBuilder();
	}

	public final void setId(UUID id) {
		this.id = getDefaultUUID(id);
	}

	public final SalonDTOBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public final SalonDTOBuilder setAddress(String address) {
		this.address = address;
		return this;
	}
	
	public final SalonDTO build() {
		return SalonDTO.create(id, name, address);
	}
}
