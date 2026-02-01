package com.petro_spy_svc.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FetchLocationResponse {

	private List<String>location;
	
	private BusinessError businessError;

	public List<String> getLocation() {
		return location;
	}

	public void setLocation(List<String> location) {
		this.location = location;
	}

	public BusinessError getBusinessError() {
		return businessError;
	}

	public void setBusinessError(BusinessError businessError) {
		this.businessError = businessError;
	}

	
}
