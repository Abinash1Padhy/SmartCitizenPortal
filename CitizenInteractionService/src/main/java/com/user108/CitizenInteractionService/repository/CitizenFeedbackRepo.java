package com.user108.CitizenInteractionService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.user108.CitizenInteractionService.entity.CitizenFeedback;

@Repository
public interface CitizenFeedbackRepo extends MongoRepository<CitizenFeedback, String> {

}
