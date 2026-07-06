package com.petro_spy_svc.service;

import com.petro_spy_svc.domain.FetchManagerDetailRequest;
import com.petro_spy_svc.domain.FetchManagerDetailResponse;
import com.petro_spy_svc.domain.ManagerInfoRequest;
import com.petro_spy_svc.domain.ManagerInfoResponse;

public interface ManagerInfoService {

	public ManagerInfoResponse createManager(ManagerInfoRequest managerInfoRequest);

	public FetchManagerDetailResponse fetchManagerDetail(FetchManagerDetailRequest fetchManagerDetailRequest);

}
