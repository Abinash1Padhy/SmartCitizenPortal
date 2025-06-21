package com.user108.CitizenInteractionService.controller;

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

import com.user108.CitizenInteractionService.dto.ComplaintDto;
import com.user108.CitizenInteractionService.entity.CitizenComplaint;
import com.user108.CitizenInteractionService.service.CitizenComplaintService;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

	@Autowired
	private CitizenComplaintService complaintService;
	
	@GetMapping
	public ResponseEntity<List<CitizenComplaint>> getComplaints(){

		List<CitizenComplaint> complaints = complaintService.getComplaints(100, 1);

		if(complaints!=null)
			return ResponseEntity.ok(complaints);
		else
			return ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity<ComplaintDto> registerComplaints(@RequestBody ComplaintDto complaintDto){

		if(complaintDto!=null)
		{
			ComplaintDto newComplaint = complaintService.saveComplaint(complaintDto);
			return ResponseEntity.ok(newComplaint);
		}
		return ResponseEntity.ok(complaintDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ComplaintDto> updateComplaint(
			@PathVariable("id") UUID id,
			@RequestBody ComplaintDto complaintDto)
	{
		ComplaintDto key = complaintService.findById(id);
		if(key!=null)
			return ResponseEntity.ok(complaintService.saveComplaint(complaintDto));

		return ResponseEntity.notFound().build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ComplaintDto> updateComplaintDetail(
			@PathVariable("id") UUID id,
			@RequestBody Map<String,Object> complaintUpdatesKV)
	{

		ComplaintDto complaint = complaintService.findById(id);
		if(complaint!=null) {
			complaintUpdatesKV.forEach((key, value) -> {
				switch (key) {
				case "status" -> complaint.setStatus((String) value);
				case "description" -> complaint.setDescription((String) value);
				case "location" -> complaint.setLocation((String) value);
				}
			});
			return ResponseEntity.ofNullable(complaintService.saveComplaint(complaint));
		}

		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ComplaintDto> deleteComplaint(@PathVariable("id") UUID id){
		return ResponseEntity.ofNullable(complaintService.deleteById(id));
	
	}
}
