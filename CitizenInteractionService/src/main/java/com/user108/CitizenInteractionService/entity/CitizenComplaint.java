package com.user108.CitizenInteractionService.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.user108.CitizenInteractionService.dto.ComplaintDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CitizenComplaint{

	@Id
	private UUID id= UUID.randomUUID();
	
	private String citizenId;
    private String serviceType; // e.g., "Electricity", "Water"
    private String description;
    private String location;
    private LocalDateTime raisedAt;
    private String status;
 // e.g., "Open", "In Progress", "Resolved"
	
    @Override
	public String toString() {
		return "CitizenComplaint [id=" + id + ", citizenId=" + citizenId + ", serviceType=" + serviceType
				+ ", description=" + description + ", location=" + location + ", raisedAt=" + raisedAt + ", status="
				+ status + "]";
	}
    
    public CitizenComplaint() {
		super();
	}

    
	public CitizenComplaint(ComplaintDto dto) {
		super();
		this.id = UUID.randomUUID();
		this.citizenId = dto.getCitizenId();
		this.serviceType = dto.getServiceType();
		this.description = dto.getDescription();
		this.location = dto.getLocation();
		this.raisedAt = dto.getRaisedAt();
		this.status = dto.getStatus();
	}

	private CitizenComplaint(UUID id, String citizenId, String serviceType, String description, String location,
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

	public CitizenComplaint(String citizenId, String serviceType, String description, String location,
			LocalDateTime raisedAt, String status) {
		super();
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

	private void setId(UUID id) {
		if(id==null)
			this.id = UUID.randomUUID();
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
