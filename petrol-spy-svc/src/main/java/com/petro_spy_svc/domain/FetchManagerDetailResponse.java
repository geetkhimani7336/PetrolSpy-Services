package com.petro_spy_svc.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FetchManagerDetailResponse {

	private List<FetchMangerInfoDetail>fetchMangerInfoDetail;
	
	private BusinessError businessError;

	public List<FetchMangerInfoDetail> getFetchMangerInfoDetail() {
		return fetchMangerInfoDetail;
	}

	public void setFetchMangerInfoDetail(List<FetchMangerInfoDetail> fetchMangerInfoDetail) {
		this.fetchMangerInfoDetail = fetchMangerInfoDetail;
	}

	public BusinessError getBusinessError() {
		return businessError;
	}

	public void setBusinessError(BusinessError businessError) {
		this.businessError = businessError;
	}
	
	
}
