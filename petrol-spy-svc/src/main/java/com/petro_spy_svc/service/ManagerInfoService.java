package com.petro_spy_svc.service;

import com.petro_spy_svc.domain.ManagerInfoRequest;
import com.petro_spy_svc.domain.ManagerInfoResponse;

public interface ManagerInfoService {

	public ManagerInfoResponse createManager(ManagerInfoRequest managerInfoRequest);

}
