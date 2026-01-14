package com.petro_spy_svc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petro_spy_svc.domain.LocationRequest;
import com.petro_spy_svc.domain.LocationResponse;
import com.petro_spy_svc.service.CreateLocationService;


@RequestMapping("/rest/petro")
@RestController
public class Location {
	
	@Autowired
	CreateLocationService createLocationService;

	@PostMapping(value = "/createLocation",consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<LocationResponse> createLocation(@RequestBody LocationRequest locationRequest) {
		  return new ResponseEntity<>(createLocationService.creteLocation(locationRequest),HttpStatus.OK);
	}
	
	                
}
