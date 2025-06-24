package com.user108.CitizenInteractionService.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.user108.CitizenInteractionService.configs.KafkaConsumerConfig;
import com.user108.CitizenInteractionService.dto.ComplaintDto;
import com.user108.CitizenInteractionService.dto.FeedbackDto;

@Service
public class ConsumerService {

	private static Logger logger = Logger.getLogger("ConsumerService");
	
	@Autowired
	private CitizenFeedbackService citizenFeedbackService;
	
	private static final String GROUP = "COMPLAINT_GOUP"; 
	
	@KafkaListener(topics = {ProducerService.COMPLAINT_TOPIC}, groupId = KafkaConsumerConfig.complaint_GROUP_ID)
	public FeedbackDto recieveComplaintsAndCreateFeedbacks(ComplaintDto complaintDto) {
		
		logger.info("Recieved Complaint:"+complaintDto.toString());
		
		FeedbackDto dto = new FeedbackDto();
		dto.setCitizenId(complaintDto.getCitizenId());
		dto.setComments("Resolved:"+complaintDto.getId()+";"+complaintDto.getDescription());
		dto.setComplaintId(complaintDto.getId().toString());
		dto.setRating(3);
		dto.setSubmittedAt(complaintDto.getRaisedAt().plusDays(1));
		
		return citizenFeedbackService.saveFeedback(dto);
		
	}
}
