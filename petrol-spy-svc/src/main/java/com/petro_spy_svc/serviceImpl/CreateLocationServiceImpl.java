package com.petro_spy_svc.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petro_spy_svc.domain.BusinessError;
import com.petro_spy_svc.domain.FetchLocationRequest;
import com.petro_spy_svc.domain.FetchLocationResponse;
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
				return locationAlreadyExist(locationResponse, businessError);
			}

			if (null != location && null != location.getId() && null != locationRequest
					&& null != locationRequest.getLocationId()
					&& location.getId().equals(locationRequest.getLocationId())) {
				return idAlreadyExist(locationResponse, businessError);
			}

			if (null != locationRequest && null == locationRequest.getLocationId()) {
				return provideLocationId(locationResponse, businessError);
			}

			if (null != locationRequest && null == locationRequest.getLocation()) {
				return provideLocation(locationResponse, businessError);
			}

			if (null != locationRequest && null == locationRequest.getState()) {
				return provideState(locationResponse, businessError);
			}
			if (null != locationRequest && null == locationRequest.getCountry()) {
				return provideCountry(locationResponse, businessError);
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

	private LocationResponse provideCountry(LocationResponse locationResponse, BusinessError businessError) {
		businessError.setErrCode("ERR04");
		businessError.setErrMsg("Please Provide the Country");
		locationResponse.setBusinessError(businessError);
		return locationResponse;
	}

	private LocationResponse provideState(LocationResponse locationResponse, BusinessError businessError) {
		businessError.setErrCode("ERR03");
		businessError.setErrMsg("Please Provide the State");
		locationResponse.setBusinessError(businessError);
		return locationResponse;
	}

	private LocationResponse provideLocation(LocationResponse locationResponse, BusinessError businessError) {
		businessError.setErrCode("ERR02");
		businessError.setErrMsg("Please Provide the Location");
		locationResponse.setBusinessError(businessError);
		return locationResponse;
	}

	private LocationResponse provideLocationId(LocationResponse locationResponse, BusinessError businessError) {
		businessError.setErrCode("ERR01");
		businessError.setErrMsg("Please Provide the Location Id");
		locationResponse.setBusinessError(businessError);
		return locationResponse;
	}

	private LocationResponse idAlreadyExist(LocationResponse locationResponse, BusinessError businessError) {
		businessError.setErrCode("ERR05");
		businessError.setErrMsg("Please Provide Different Id as the ID already Exists");
		locationResponse.setBusinessError(businessError);
		return locationResponse;
	}

	private LocationResponse locationAlreadyExist(LocationResponse locationResponse, BusinessError businessError) {
		businessError.setErrCode("ERR06");
		businessError.setErrMsg("Please Provide Different Location as the Location already Exists");
		locationResponse.setBusinessError(businessError);
		return locationResponse;
	}

	@Override
	public FetchLocationResponse fetchLocationName(FetchLocationRequest fetchlocationRequest) {
		
		FetchLocationResponse fetchLocationResponse=new FetchLocationResponse();
		BusinessError businessError = new BusinessError();
		List<String>location=new ArrayList<String>();
		try {
			if (null != fetchlocationRequest && null == fetchlocationRequest.getState()) {
				return provideState(fetchLocationResponse, businessError);
			}
			if (null != fetchlocationRequest && null == fetchlocationRequest.getCountry()) {
				return provideCountry(fetchLocationResponse, businessError);
			}
			List<LocationEntity> locationEntity=locationRepository.getLocationNameBasedOnState(fetchlocationRequest.getState());
			List<String>locationName=locationEntity.stream().map(LocationEntity::getLocation).toList();
			for(String loc:locationName) {
				location.add(loc);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		fetchLocationResponse.setLocation(location);
		return fetchLocationResponse;
	}

	private FetchLocationResponse provideCountry(FetchLocationResponse fetchLocationResponse,
			BusinessError businessError) {
		businessError.setErrCode("ERR04");
		businessError.setErrMsg("Please Provide the Country");
		fetchLocationResponse.setBusinessError(businessError);
		return fetchLocationResponse;
	}

	private FetchLocationResponse provideState(FetchLocationResponse fetchLocationResponse,
			BusinessError businessError) {
		businessError.setErrCode("ERR03");
		businessError.setErrMsg("Please Provide the State");
		fetchLocationResponse.setBusinessError(businessError);
		return fetchLocationResponse;
	}

}
