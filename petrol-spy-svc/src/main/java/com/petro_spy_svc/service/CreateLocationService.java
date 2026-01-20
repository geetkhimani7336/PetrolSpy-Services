package com.petro_spy_svc.service;

import com.petro_spy_svc.domain.LocationRequest;
import com.petro_spy_svc.domain.LocationResponse;


public interface CreateLocationService {

	public LocationResponse creteLocation(LocationRequest locationRequest);

	
}
