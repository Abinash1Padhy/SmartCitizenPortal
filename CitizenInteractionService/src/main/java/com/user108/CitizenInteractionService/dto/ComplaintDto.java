package com.user108.CitizenInteractionService.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.user108.CitizenInteractionService.entity.CitizenComplaint;

public class ComplaintDto implements Serializable{

	private UUID id;
	private String citizenId;
	private String serviceType;
	private String description;
	private String location;
	private LocalDateTime raisedAt;
	private String status;
		
	@Override
	public String toString() {
		return "ComplaintDto [id=" + id + ", citizenId=" + citizenId + ", serviceType=" + serviceType + ", description="
				+ description + ", location=" + location + ", raisedAt=" + raisedAt + ", status=" + status + "]";
	}
	
	public ComplaintDto(CitizenComplaint c) {
		
		this.id = c.getId();
		this.citizenId = c.getCitizenId();
		this.serviceType = c.getServiceType();
		this.description = c.getDescription();
		this.location = c.getLocation();
		this.raisedAt = c.getRaisedAt();
		this.status = c.getStatus();
		
	}

	public ComplaintDto() {
		super();
	}

	public ComplaintDto(UUID id, String citizenId, String serviceType, String description, String location,
			LocalDateTime raisedAt, String status) {
		super();
		this.id = id;
		this.citizenId = citizenId;
		this.serviceType = serviceType;
		this.description = description;
		this.location = location;
		this.raisedAt = raisedAt;
		this.status = status;
	}

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getCitizenId() {
		return citizenId;
	}
	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalDateTime getRaisedAt() {
		return raisedAt;
	}
	public void setRaisedAt(LocalDateTime raisedAt) {
		this.raisedAt = raisedAt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
