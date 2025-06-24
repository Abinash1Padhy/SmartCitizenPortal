package com.user108.UserService.dto;

import java.time.LocalDateTime;
import java.util.List;

public class FilterUserDto {

	private List<String> citizenIds;
	private List<String> emails;
	private List<String> phone;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private Integer pageSize;
	private Integer pageNumber;
	
	@Override
	public String toString() {
		return "FilterUserDto [citizenIds=" + citizenIds + ", emails=" + emails + ", phone=" + phone + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", pageSize=" + pageSize + ", pageNumber=" + pageNumber + "]";
	}
	public FilterUserDto(List<String> citizenIds, List<String> emails, List<String> phone, LocalDateTime fromDate,
			LocalDateTime toDate, Integer pageSize, Integer pageNumber) {
		super();
		this.citizenIds = citizenIds;
		this.emails = emails;
		this.phone = phone;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
	}
	public FilterUserDto() {
		super();
	}
	public List<String> getCitizenIds() {
		return citizenIds;
	}
	public void setCitizenIds(List<String> citizenIds) {
		this.citizenIds = citizenIds;
	}
	public List<String> getEmails() {
		return emails;
	}
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}
	public List<String> getPhone() {
		return phone;
	}
	public void setPhone(List<String> phone) {
		this.phone = phone;
	}
	public LocalDateTime getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDateTime getToDate() {
		return toDate;
	}
	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	
	
 }
