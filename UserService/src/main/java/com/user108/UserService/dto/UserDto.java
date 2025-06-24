package com.user108.UserService.dto;

import java.time.LocalDateTime;

import com.user108.UserService.entity.Address;
import com.user108.UserService.entity.User;

public class UserDto {

	private String citizenId;
	private String name;
	private String email;
	private String phone;
	private String role; // e.g., ADMIN, CITIZEN
	private String password; // encrypted later with Keycloak or Spring Security
	private LocalDateTime createdAt = LocalDateTime.now();
	private Address address;
	
	public UserDto(User u) {
		super();
		this.citizenId = u.getCitizenId();
		this.name = u.getName();
		this.email = u.getEmail();
		this.phone = u.getPhone();
		this.role = u.getRole();
		this.password = u.getPassword();
		this.createdAt = u.getCreatedAt();
		this.address = u.getAddress();
	}
	public UserDto() {
		super();
	}
	
	public UserDto(String citizenId, String name, String email, String phone, String role, String password,
			LocalDateTime createdAt, Address addr) {
		super();
		this.citizenId = citizenId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.password = password;
		this.createdAt = createdAt;
		this.address = addr;
	}
	
	@Override
	public String toString() {
		return "UserDto [citizenId=" + citizenId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", role=" + role + ", password=" + password + ", createdAt=" + createdAt + ", address=" + address.toString()
				+ "]";
	}
	public String getCitizenId() {
		return citizenId;
	}
	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
