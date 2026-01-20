package com.petro_spy_svc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petro_spy_svc.domain.BusinessError;
import com.petro_spy_svc.domain.LocationRequest;
import com.petro_spy_svc.domain.LocationResponse;
import com.petro_spy_svc.entity.LocationEntity;
import com.petro_spy_svc.repository.LocationRepository;
import com.petro_spy_svc.service.CreateLocationService;

@Service
public class CreateLocationServiceImpl implements CreateLocationService {

	@Autowired
	LocationRepository locationRepository;

	@Override
	public LocationResponse creteLocation(LocationRequest locationRequest) {

		LocationResponse locationResponse = new LocationResponse();

		LocationEntity locationEntity = new LocationEntity();

		BusinessError businessError = new BusinessError();

		try {

			LocationEntity location = locationRepository.getLocationId(locationRequest.getLocationId());

			LocationEntity locationName = locationRepository.getLocationName(locationRequest.getLocation());

			if (null != locationName && null != locationName.getLocation() && null != locationRequest
					&& null != locationRequest.getLocation()
					&& locationName.getLocation().equals(locationRequest.getLocation())) {
				businessError.setErrCode("ERR06");
				businessError.setErrMsg("Please Provide Different Location as the Location already Exists");
				locationResponse.setBusinessError(businessError);
				return locationResponse;
			}

			if (null != location && null != location.getId() && null != locationRequest
					&& null != locationRequest.getLocationId()
					&& location.getId().equals(locationRequest.getLocationId())) {
				businessError.setErrCode("ERR05");
				businessError.setErrMsg("Please Provide Different Id as the ID already Exists");
				locationResponse.setBusinessError(businessError);
				return locationResponse;
			}

			if (null != locationRequest && null == locationRequest.getLocationId()) {
				businessError.setErrCode("ERR01");
				businessError.setErrMsg("Please Provide the Location Id");
				locationResponse.setBusinessError(businessError);
				return locationResponse;
			}

			if (null != locationRequest && null == locationRequest.getLocation()) {
				businessError.setErrCode("ERR02");
				businessError.setErrMsg("Please Provide the Location");
				locationResponse.setBusinessError(businessError);
				return locationResponse;
			}

			if (null != locationRequest && null == locationRequest.getState()) {
				businessError.setErrCode("ERR03");
				businessError.setErrMsg("Please Provide the State");
				locationResponse.setBusinessError(businessError);
				return locationResponse;
			}
			if (null != locationRequest && null == locationRequest.getCountry()) {
				businessError.setErrCode("ERR04");
				businessError.setErrMsg("Please Provide the Country");
				locationResponse.setBusinessError(businessError);
				return locationResponse;
			}
			if (null != locationRequest && null != locationRequest.getLocationId()) {
				locationEntity.setId(locationRequest.getLocationId());
			}

			if (null != locationRequest && null != locationRequest.getLocation()) {
				locationEntity.setLocation(locationRequest.getLocation());
			}

			if (null != locationRequest && null != locationRequest.getState()) {
				locationEntity.setState(locationRequest.getState());
			}

			if (null != locationRequest && null != locationRequest.getCountry()) {
				locationEntity.setCountry(locationRequest.getCountry());
			}

			locationRepository.save(locationEntity);

		} catch (Exception e) {

			e.printStackTrace();
		}

		locationResponse.setMessage("Data Saved Successfully");

		return locationResponse;
	}

}
