package com.user108.CitizenInteractionService.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user108.CitizenInteractionService.entity.CitizenComplaint;

@Repository
public interface CitizenComplaintRepo extends JpaRepository<CitizenComplaint, UUID> {

}
