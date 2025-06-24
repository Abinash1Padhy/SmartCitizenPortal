package com.user108.UserService.entity;

import com.user108.UserService.dto.AddressDto;

public class Address {
	
	private String street;
	private String area;
	private String city;
	private String state;
	private String country;
	
	@Override
	public String toString() {
		return "Address [street=" + street + ", area=" + area + ", city=" + city + ", state=" + state + ", country="
				+ country + "]";
	}
	public Address(String street, String area, String city, String state, String country) {
		super();
		this.street = street;
		this.area = area;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	public Address(AddressDto dto) {
		super();
		this.street = dto.getStreet();
		this.area = dto.getArea();
		this.city = dto.getCity();
		this.state = dto.getState();
		this.country = dto.getCountry();
	}
	public Address() {
		super();
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
