package com.user108.UserService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.user108.UserService.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

}
