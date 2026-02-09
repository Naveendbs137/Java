package com.dhatvibs.dto;

import java.time.LocalDate;

public class AdminSlotCreateRequest {

    private String adminCode; // must be AD123

    private LocalDate dateFrom;
    private LocalDate dateTo;

    private String city;
    private String zone;

    private String startTime; // 06:00
    private String endTime;   // 08:00

    private Integer maxRiders;

	public String getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getMaxRiders() {
		return maxRiders;
	}

	public void setMaxRiders(Integer maxRiders) {
		this.maxRiders = maxRiders;
	}

    // getters & setters
}
