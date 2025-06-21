package com.user108.CitizenInteractionService.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user108.CitizenInteractionService.dto.FeedbackDto;
import com.user108.CitizenInteractionService.entity.CitizenFeedback;
import com.user108.CitizenInteractionService.service.CitizenFeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private CitizenFeedbackService feedbackService;
	
	@GetMapping
	public ResponseEntity<Object> getAllFeedbacks() {
		
		List<CitizenFeedback> feedbacks = feedbackService.getFeedbacks();
		if(feedbacks!=null)
			return ResponseEntity.ok(feedbacks);
		else if(feedbacks==null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.noContent().build();
		
	}

	@PostMapping
	public ResponseEntity<FeedbackDto> createfeedbacks(@RequestBody FeedbackDto key){
		
		if(key!=null)
		{
			FeedbackDto newFeedback = feedbackService.saveFeedback(key);
			return ResponseEntity.ofNullable(newFeedback);
		}
		else
			return ResponseEntity.internalServerError().build();
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FeedbackDto> updateFeedBack(
			@PathVariable("id") UUID id,
			@RequestBody FeedbackDto feedbackDto)
	{
		FeedbackDto key = feedbackService.findById(id.toString());
		if(key!=null)
			return ResponseEntity.ofNullable(feedbackService.saveFeedback(feedbackDto));

		return ResponseEntity.notFound().build();
	}

	
	@PatchMapping("/{id}")
	public ResponseEntity<FeedbackDto> updateFeedbackDetail(
			@PathVariable("id") UUID id,
			@RequestBody Map<String,Object> feedbackUpdatesKV)
	{

		FeedbackDto feedback = feedbackService.findById(id.toString());
		if(feedback!=null) {
			feedbackUpdatesKV.forEach((key, value) -> {
				switch (key) {
	            case "citizenId" -> feedback.setCitizenId((String) value);
	            case "complaintId" -> feedback.setComplaintId((String) value);
	            case "comments" -> feedback.setComments((String) value);
	            case "rating" -> feedback.setRating((int) value);
	            case "submittedAt" -> feedback.setSubmittedAt(LocalDateTime.parse((String) value));
	        }
			});
			return ResponseEntity.ofNullable(feedbackService.saveFeedback(feedback));
		}

		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<FeedbackDto> deleteComplaint(@PathVariable("id") UUID id){
		return ResponseEntity.ofNullable(feedbackService.deleteById(id.toString()));
	
	}
}
