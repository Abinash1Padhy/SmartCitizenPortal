package com.user108.CitizenInteractionService.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.user108.CitizenInteractionService.dto.ComplaintDto;
import com.user108.CitizenInteractionService.entity.CitizenComplaint;
import com.user108.CitizenInteractionService.repository.CitizenComplaintRepo;

import jakarta.annotation.PostConstruct;

@Service
public class CitizenComplaintServiceImpl implements CitizenComplaintService {

	private final static Logger logger = Logger.getLogger("CitizenComplaintServiceImpl");
	
	@Autowired
	private CitizenComplaintRepo complaintRepo;
	
	@Autowired
	private ProducerService complaintProducerService;
	
	@PostConstruct
	private void createComplaints() {
		LocalDateTime dateTime = LocalDateTime.now();
		CitizenComplaint c1 = new CitizenComplaint("1","Water","Water Cloged on road drain","11O Street Lane 1",dateTime,"Open"),
				c2 = new CitizenComplaint("2","Hand Pump repair","","Lane 1 Basil Street",dateTime,"In-Progress"),
				c3 = new CitizenComplaint("4","Electricity","Street Light not working","11O Street Lane 1 front of 3rd house",dateTime,"Closed");
		
		List<CitizenComplaint> complaints = new ArrayList<>();
		complaints.add(c1);complaints.add(c2);complaints.add(c3);
		
		complaintRepo.saveAll(complaints);
	}
	
	@Override
	public List<ComplaintDto> getComplaints(Integer pageSize, Integer pageNo){
		
		Pageable paging  = PageRequest.of(pageNo, pageSize);
		Page<CitizenComplaint> complaintPage = complaintRepo.findAll(paging);
		
		
		List<ComplaintDto> complaintDtos = new ArrayList<ComplaintDto>();
		
		if(complaintPage.hasContent())
			complaintPage.getContent().parallelStream().forEach(c -> complaintDtos.add(new ComplaintDto(c)));
		
		return complaintDtos;
		
	}

	@Override
	public ComplaintDto saveComplaint(ComplaintDto complaintDto) {
		
		CitizenComplaint complaint = new CitizenComplaint(complaintDto);
		CitizenComplaint newComplaint = complaintRepo.save(complaint);
		ComplaintDto dto = new ComplaintDto(newComplaint);
		String message = complaintProducerService.sendMessage(dto);
		logger.info("Message from Kafka:"+message);
		return dto;
	}

	@Override
	public ComplaintDto findById(UUID id) {
		
		Optional<CitizenComplaint> key = complaintRepo.findById(id);
		if(key.isPresent())
			return new ComplaintDto(key.get());
		return null;
	}

	@Override
	public ComplaintDto deleteById(UUID id) {
		
		if(id==null)
			return null;
		Optional<CitizenComplaint> c = complaintRepo.findById(id);
		
		if(c.isPresent())
		{	
			complaintRepo.deleteById(id);
			return new ComplaintDto(c.get());
		}
		else 
			return null;
	}
	
}
