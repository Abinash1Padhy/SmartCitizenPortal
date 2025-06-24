package com.user108.UserService.dto;

import com.user108.UserService.entity.Address;

public class AddressDto {

	private String street;
	private String area;
	private String city;
	private String state;
	private String country;
	
	public AddressDto() {
		super();
	}
	public AddressDto(String street, String area, String city, String state, String country) {
		super();
		this.street = street;
		this.area = area;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	public AddressDto(Address addr) {
		super();
		this.street = addr.getStreet();
		this.area = addr.getArea();
		this.city = addr.getCity();
		this.state = addr.getState();
		this.country = addr.getCountry();
	}
	@Override
	public String toString() {
		return "AddressDto [street=" + street + ", area=" + area + ", city=" + city + ", state=" + state + ", country="
				+ country + "]";
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
