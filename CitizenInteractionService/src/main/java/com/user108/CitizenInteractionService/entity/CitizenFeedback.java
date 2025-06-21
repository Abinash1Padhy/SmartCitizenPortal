package com.user108.CitizenInteractionService.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import com.user108.CitizenInteractionService.dto.FeedbackDto;

import jakarta.persistence.Id;

@Document
public class CitizenFeedback{

	@Id
	private String id= UUID.randomUUID().toString();

    private String citizenId;
    private String complaintId; // optional
    private String comments;
    private int rating; // 1 to 5
    private LocalDateTime submittedAt;
	
    @Override
	public String toString() {
		return "CitizenFeedback [id=" + id + ", citizenId=" + citizenId + ", complaintId=" + complaintId + ", comments="
				+ comments + ", rating=" + rating + ", submittedAt=" + submittedAt + "]";
	}
	
	public CitizenFeedback(FeedbackDto dto) {
		super();
		this.citizenId = dto.getCitizenId();
		this.complaintId = dto.getComplaintId();
		this.comments = dto.getComments();
		this.rating = dto.getRating();
		this.submittedAt = dto.getSubmittedAt();
	}

	private CitizenFeedback(String id, String citizenId, String complaintId, String comments, int rating,
			LocalDateTime submittedAt) {
		super();
		this.id = id;
		this.citizenId = citizenId;
		this.complaintId = complaintId;
		this.comments = comments;
		this.rating = rating;
		this.submittedAt = submittedAt;
	}

	public CitizenFeedback(String citizenId, String complaintId, String comments, int rating,
			LocalDateTime submittedAt) {
		super();
		this.citizenId = citizenId;
		this.complaintId = complaintId;
		this.comments = comments;
		this.rating = rating;
		this.submittedAt = submittedAt;
	}

	public CitizenFeedback() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public String getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public LocalDateTime getSubmittedAt() {
		return submittedAt;
	}

	public void setSubmittedAt(LocalDateTime submittedAt) {
		this.submittedAt = submittedAt;
	}
    
}
