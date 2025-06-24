package com.user108.UserService.controller;

import java.util.List;
import java.util.Map;

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

import com.user108.UserService.dto.FilterUserDto;
import com.user108.UserService.dto.UserDto;
import com.user108.UserService.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService; 

	@GetMapping("/helloUser")
	public ResponseEntity<String> getHi() {
		return ResponseEntity.ok("Hi from User Service");
	}
	@PostMapping("/getUsers")
	public ResponseEntity<List<UserDto>> getAllUsers(@RequestBody FilterUserDto filterDto) {

		if(filterDto==null)
			return ResponseEntity.badRequest().build();
		
		List<UserDto> users = userService.getUsers(filterDto);
		return ResponseEntity.ofNullable(users);

		//		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<UserDto> createUsers(@RequestBody UserDto key){

		if(key!=null)
		{
			UserDto newUser = userService.saveUser(key);
			return ResponseEntity.ofNullable(newUser);
		}
		else
			return ResponseEntity.internalServerError().build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateFeedBack(
			@PathVariable("id") String citizenId,
			@RequestBody UserDto userDto)
	{
		UserDto key = userService.findById(citizenId.toString());
		return ResponseEntity.ofNullable(userService.saveUser(userDto));
		//		return ResponseEntity.notFound().build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UserDto> updateFeedbackDetail(
			@PathVariable("id") String id,
			@RequestBody Map<String,Object> userUpdatesKV)
	{

		UserDto user = userService.findById(id.toString());
		if(user!=null) {
			userUpdatesKV.forEach((key, value) -> {
				switch (key) {
				case "name" -> user.setName((String) value);
				case "email" -> user.setEmail((String) value);
				case "phone" -> user.setPhone((String) value);
				case "role" -> user.setRole((String) value);
				case "password" -> user.setPassword((String) value);
				}
			});
			return ResponseEntity.ofNullable(userService.saveUser(user));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserDto> deleteComplaint(@PathVariable("id") String citizenId){
		return ResponseEntity.ofNullable(userService.deleteById(citizenId.toString()));

	}
}
