package com.user108.CitizenInteractionService.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user108.CitizenInteractionService.dto.ComplaintDto;
import com.user108.CitizenInteractionService.dto.FeedbackDto;
import com.user108.CitizenInteractionService.entity.CitizenComplaint;
import com.user108.CitizenInteractionService.entity.CitizenFeedback;
import com.user108.CitizenInteractionService.repository.CitizenFeedbackRepo;

import jakarta.annotation.PostConstruct;

@Service
public class CitizenFeedbackServiceImpl implements CitizenFeedbackService {
	
	@Autowired
	private CitizenFeedbackRepo feedbackRepo;
	
	@PostConstruct
	private void createFeedbacks() {
		LocalDateTime dateTime = LocalDateTime.now();
		List<CitizenFeedback> feedbacks = new ArrayList<CitizenFeedback>();
		CitizenFeedback f1 = new CitizenFeedback("1","1","Comment1 Water blockage good",4, dateTime),
				f2 = new CitizenFeedback("2","2","Comment2 Road Repaired",3, dateTime),
				f3 = new CitizenFeedback("3","3","Comment3 Voltage up now",3, dateTime),
				f4 = new CitizenFeedback("4","4","Comment1 Sewage cleared",5, dateTime);
		feedbacks.add(f1); feedbacks.add(f2);feedbacks.add(f3);feedbacks.add(f4);
		
		feedbackRepo.saveAll(feedbacks);
	}
	
	@Override
	public List<CitizenFeedback> getFeedbacks(){
		
		return feedbackRepo.findAll();
	}

	@Override
	public FeedbackDto saveFeedback(FeedbackDto feedbackDto) {
		
		CitizenFeedback feedback = new CitizenFeedback(feedbackDto);
		feedbackRepo.save(feedback);
		return new FeedbackDto(feedback);
	}
	
	@Override
	public FeedbackDto findById(String id) {
		
		Optional<CitizenFeedback> key = feedbackRepo.findById(id);
		if(key.isPresent())
			return new FeedbackDto(key.get());
		return null;
	}

	@Override
	public FeedbackDto deleteById(String id) {
		
		if(id==null)
			return null;
		Optional<CitizenFeedback> f = feedbackRepo.findById(id);
		
		if(f.isPresent())
		{	
			feedbackRepo.deleteById(id);
			return new FeedbackDto(f.get());
		}
		else 
			return null;
	}
}
