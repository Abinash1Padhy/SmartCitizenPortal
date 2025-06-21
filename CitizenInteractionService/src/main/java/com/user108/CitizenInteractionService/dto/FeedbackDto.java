package com.user108.CitizenInteractionService.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.user108.CitizenInteractionService.entity.CitizenFeedback;

public class FeedbackDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String citizenId;
	private String complaintId; // optional
	private String comments;
	private int rating; // 1 to 5
	private LocalDateTime submittedAt;

	@Override
	public String toString() {
		return "FeedbackDto [id=" + id + ", citizenId=" + citizenId + ", complaintId=" + complaintId + ", comments="
				+ comments + ", rating=" + rating + ", submittedAt=" + submittedAt + "]";
	}

	public FeedbackDto() {
		super();
	}

	public FeedbackDto(CitizenFeedback feedback) {
		this.id = feedback.getId();
		this.citizenId = feedback.getCitizenId();
		this.complaintId = feedback.getComplaintId();
		this.comments = feedback.getComments();
		this.rating = feedback.getRating();
		this.submittedAt = feedback.getSubmittedAt();
	}

	public FeedbackDto(String citizenId, String complaintId, String comments, int rating, LocalDateTime submittedAt) {
		super();
		this.citizenId = citizenId;
		this.complaintId = complaintId;
		this.comments = comments;
		this.rating = rating;
		this.submittedAt = submittedAt;
	}

	private FeedbackDto(String id, String citizenId, String complaintId, String comments, int rating,
			LocalDateTime submittedAt) {
		super();
		this.id = id;
		this.citizenId = citizenId;
		this.complaintId = complaintId;
		this.comments = comments;
		this.rating = rating;
		this.submittedAt = submittedAt;
	}

	public String getId() {
		return id;
	}
	private void setId(String id) {
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
