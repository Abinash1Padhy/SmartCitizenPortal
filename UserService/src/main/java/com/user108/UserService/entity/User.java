package com.user108.UserService.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.user108.UserService.dto.UserDto;

@Document(collection = "users")
public class User {

	@Id
    private String citizenId = UUID.randomUUID().toString();
    private String name;
    private String email;
    private String phone;
    private String role; // e.g., ADMIN, CITIZEN

    private String password; // encrypted later with Keycloak or Spring Security

    private LocalDateTime createdAt = LocalDateTime.now();
    
    private Address address;
    
	public User() {
		super();
	}
	
	@Override
	public String toString() {
		return "User [citizenId=" + citizenId + ", name=" + name + ", email=" + email + ", phone=" + phone + ", role="
				+ role + ", password=" + password + ", createdAt=" + createdAt + ", address=" + address.toString() + "]";
	}

	public User(UserDto udto) {
		super();
		this.citizenId = udto.getCitizenId();
		this.name = udto.getName();
		this.email = udto.getEmail();
		this.phone = udto.getPhone();
		this.role = udto.getRole();
		this.password = udto.getPassword();
		this.createdAt = udto.getCreatedAt();
		this.address = udto.getAddress();
	}

	public User(String name, String email, String phone, String role, String password,
			LocalDateTime createdAt, Address addr) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.password = password;
		this.createdAt = createdAt;
		this.address = addr;
	}

	public String getCitizenId() {
		return citizenId;
	}

	private void setCitizenId(String citizenId) {
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
