package com.user108.CitizenInteractionService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.user108.CitizenInteractionService.dto.ComplaintDto;

@Service
public class ComplaintProducerService {

	@Autowired
	private KafkaTemplate<String, ComplaintDto> kafkaTemplate;
	
	private final String COMPLAINT_TOPIC = "complaintTopic";

	public ComplaintProducerService(KafkaTemplate<String, ComplaintDto> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public String sendMessage(ComplaintDto complaintDto) {
		kafkaTemplate.send(COMPLAINT_TOPIC, complaintDto);
		return complaintDto.toString()+"Submitted";
	}
	
}
