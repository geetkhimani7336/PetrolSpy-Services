package com.petro_spy_svc.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManagerInfoResponse {

	private String message;
	
	private BusinessError businessError;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BusinessError getBusinessError() {
		return businessError;
	}

	public void setBusinessError(BusinessError businessError) {
		this.businessError = businessError;
	}
	
	
	
}
