package com.user108.CitizenInteractionService.service;

import java.util.List;
import java.util.UUID;

import com.user108.CitizenInteractionService.dto.ComplaintDto;
import com.user108.CitizenInteractionService.entity.CitizenComplaint;

public interface CitizenComplaintService {

	List<ComplaintDto> getComplaints(Integer pageSize, Integer pageNo);

	ComplaintDto saveComplaint(ComplaintDto complaintDto);
	
	ComplaintDto findById(UUID id);
	
	ComplaintDto deleteById(UUID id);

}
