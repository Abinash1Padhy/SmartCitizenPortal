package com.user108.UserService.service;

import java.util.List;

import com.user108.UserService.dto.FilterUserDto;
import com.user108.UserService.dto.UserDto;

public interface UserService {

	UserDto saveUser(UserDto complaintDto);

	UserDto findById(String citizenId);

	UserDto deleteById(String id);

	List<UserDto> getUsers(FilterUserDto filterDto);
	
	
}
