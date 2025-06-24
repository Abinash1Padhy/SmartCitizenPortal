package com.user108.CitizenInteractionService.service;

import java.util.List;
import java.util.UUID;

import com.user108.CitizenInteractionService.dto.ComplaintDto;
import com.user108.CitizenInteractionService.dto.FeedbackDto;
import com.user108.CitizenInteractionService.entity.CitizenFeedback;

public interface CitizenFeedbackService {

	List<CitizenFeedback> getFeedbacks();

	FeedbackDto saveFeedback(FeedbackDto key);

	FeedbackDto findById(String id);

	FeedbackDto deleteById(String string);

	void sendEmailNotification(FeedbackDto feedback);

	void sendSmsNotification(FeedbackDto feedback);
	
}
