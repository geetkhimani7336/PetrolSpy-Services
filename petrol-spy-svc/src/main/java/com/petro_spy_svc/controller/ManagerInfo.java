package com.petro_spy_svc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petro_spy_svc.domain.FetchManagerDetailRequest;
import com.petro_spy_svc.domain.FetchManagerDetailResponse;
import com.petro_spy_svc.domain.ManagerInfoRequest;
import com.petro_spy_svc.domain.ManagerInfoResponse;
import com.petro_spy_svc.service.ManagerInfoService;


@RequestMapping("/rest/petro")
@RestController
public class ManagerInfo {
	
	@Autowired
	ManagerInfoService managerInfoService;

	@PostMapping(value = "/managerInfo",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManagerInfoResponse> createLocation(@RequestBody ManagerInfoRequest managerInfoRequest) {
	  return new ResponseEntity<>(managerInfoService.createManager(managerInfoRequest),HttpStatus.OK);
	  
	  
}
	
	@PostMapping(value = "/fetchManagerDetail",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FetchManagerDetailResponse> fetchManagerDetail(@RequestBody FetchManagerDetailRequest fetchManagerDetailRequest) {
	  return new ResponseEntity<>(managerInfoService.fetchManagerDetail(fetchManagerDetailRequest),HttpStatus.OK);
}
	
}
