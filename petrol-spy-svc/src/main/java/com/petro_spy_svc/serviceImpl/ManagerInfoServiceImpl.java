package com.petro_spy_svc.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petro_spy_svc.domain.Address;
import com.petro_spy_svc.domain.AddressInfo;
import com.petro_spy_svc.domain.BusinessError;
import com.petro_spy_svc.domain.FetchManagerDetailRequest;
import com.petro_spy_svc.domain.FetchManagerDetailResponse;
import com.petro_spy_svc.domain.FetchMangerInfoDetail;
import com.petro_spy_svc.domain.ManagerInfoRequest;
import com.petro_spy_svc.domain.ManagerInfoResponse;
import com.petro_spy_svc.domain.ManagerPersonalInfo;
import com.petro_spy_svc.domain.ManagerWorkInfo;
import com.petro_spy_svc.domain.PersonalInfo;
import com.petro_spy_svc.domain.WorkInfo;
import com.petro_spy_svc.entity.MangerEntity;
import com.petro_spy_svc.repository.ManagerInfoRepository;
import com.petro_spy_svc.service.ManagerInfoService;
import com.ptero_spy_svc.util.LocationErrorConstants;


@Service
public class ManagerInfoServiceImpl implements ManagerInfoService {

	@Autowired
	ManagerInfoRepository managerInfoRepository;
	public static final String MALE = "Male";
	public static final String FEMALE = "Female";

	@Override
	public ManagerInfoResponse createManager(ManagerInfoRequest managerInfoRequest) {
		ManagerInfoResponse managerInfoResponse = new ManagerInfoResponse();
		MangerEntity mangerEntity = new MangerEntity();
		BusinessError businessError = new BusinessError();

		try {

			if (null != managerInfoRequest && null == managerInfoRequest.getName()
					|| managerInfoRequest.getName().trim().isEmpty()) {
				businessError.setErrCode(LocationErrorConstants.ERR07);
				businessError.setErrMsg(LocationErrorConstants.ERR07_MSG);
				managerInfoResponse.setBusinessError(businessError);
				return managerInfoResponse;
			}
			if (null != managerInfoRequest && null == managerInfoRequest.getId()) {
				businessError.setErrCode(LocationErrorConstants.ERR08);
				businessError.setErrMsg(LocationErrorConstants.ERR08_MSG);
				managerInfoResponse.setBusinessError(businessError);
				return managerInfoResponse;
			}
			if (null != managerInfoRequest && null == managerInfoRequest.getAge()) {
				businessError.setErrCode(LocationErrorConstants.ERR09);
				businessError.setErrMsg(LocationErrorConstants.ERR09_MSG);
				managerInfoResponse.setBusinessError(businessError);
				return managerInfoResponse;
			}
			if (null != managerInfoRequest && null != managerInfoRequest.getAge()
					&& managerInfoRequest.getAge() >= 60) {
				businessError.setErrCode(LocationErrorConstants.ERR10);
				businessError.setErrMsg(LocationErrorConstants.ERR10_MSG);
				managerInfoResponse.setBusinessError(businessError);
				return managerInfoResponse;
			}
			if (null != managerInfoRequest && null == managerInfoRequest.getGender()) {
				businessError.setErrCode(LocationErrorConstants.ERR11);
				businessError.setErrMsg(LocationErrorConstants.ERR11_MSG);
				managerInfoResponse.setBusinessError(businessError);
				return managerInfoResponse;

			}
			if (null != managerInfoRequest && null != managerInfoRequest.getGender()
					&& !managerInfoRequest.getGender().equalsIgnoreCase(MALE)
					&& !managerInfoRequest.getGender().equalsIgnoreCase(FEMALE)) {
				businessError.setErrCode(LocationErrorConstants.ERR12);
				businessError.setErrMsg(LocationErrorConstants.ERR12_MSG);
				managerInfoResponse.setBusinessError(businessError);
				return managerInfoResponse;
			}
			if (null != managerInfoRequest && null == managerInfoRequest.getDesignation()) {
				businessError.setErrCode(LocationErrorConstants.ERR13);
				businessError.setErrMsg(LocationErrorConstants.ERR13_MSG);
				managerInfoResponse.setBusinessError(businessError);
				return managerInfoResponse;
			}

			if (null != managerInfoRequest && null == managerInfoRequest.getEmail()
					|| managerInfoRequest.getEmail().trim().isEmpty()) {
				businessError.setErrCode(LocationErrorConstants.ERR14);
				businessError.setErrMsg(LocationErrorConstants.ERR14_MSG);
				managerInfoResponse.setBusinessError(businessError);
			}
			if (!managerInfoRequest.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
				businessError.setErrCode(LocationErrorConstants.ERR15);
				businessError.setErrMsg(LocationErrorConstants.ERR15_MSG);
				managerInfoResponse.setBusinessError(businessError);
				return managerInfoResponse;
			}
			if (null != managerInfoRequest
					&& (null == managerInfoRequest.getPhoneNo() || managerInfoRequest.getPhoneNo().trim().isEmpty())) {

				businessError.setErrCode(LocationErrorConstants.ERR16);
				businessError.setErrMsg(LocationErrorConstants.ERR16_MSG);
				managerInfoResponse.setBusinessError(businessError);
				return managerInfoResponse;
			}

			if (!managerInfoRequest.getPhoneNo().matches("^[6-9][0-9]{9}$")) {

				businessError.setErrCode(LocationErrorConstants.ERR17);
				businessError.setErrMsg(LocationErrorConstants.ERR17_MSG);
				managerInfoResponse.setBusinessError(businessError);
				return managerInfoResponse;
			}

			if (managerInfoRequest == null || mangerEntity == null)
				return managerInfoResponse;
			Optional.ofNullable(managerInfoRequest).map(ManagerInfoRequest::getName).ifPresent(mangerEntity::setName);
			Optional.ofNullable(managerInfoRequest).map(ManagerInfoRequest::getId).ifPresent(mangerEntity::setId);
			Optional.ofNullable(managerInfoRequest).map(ManagerInfoRequest::getEmail).ifPresent(mangerEntity::setEmail);
			Optional.ofNullable(managerInfoRequest).map(ManagerInfoRequest::getGender)
					.ifPresent(mangerEntity::setGender);
			Optional.ofNullable(managerInfoRequest).map(ManagerInfoRequest::getAge).ifPresent(mangerEntity::setAge);
			Optional.ofNullable(managerInfoRequest).map(ManagerInfoRequest::getDesignation)
					.ifPresent(mangerEntity::setDesignation);
			Optional.ofNullable(managerInfoRequest).map(ManagerInfoRequest::getPhoneNo)
					.ifPresent(mangerEntity::setPhoneNo);

			AddressInfo addressInfo = managerInfoRequest.getAddressInfo();
			if (addressInfo != null) {

				// ✅ Ensure Address
				Address address = mangerEntity.getAddress();
				if (address == null) {
					address = new Address();
					mangerEntity.setAddress(address);
				}

				// ✅ Ensure PersonalInfo
				PersonalInfo personalInfo = address.getPersonalInfo();
				if (personalInfo == null) {
					personalInfo = new PersonalInfo();
					address.setPersonalInfo(personalInfo);
				}

				// ✅ Ensure WorkInfo
				WorkInfo workInfo = address.getWorkInfo();
				if (workInfo == null) {
					workInfo = new WorkInfo();
					address.setWorkInfo(workInfo);
				}

				// 🔥 Personal Info
				if (addressInfo.getManagerPersonalInfo() != null) {
					var p = addressInfo.getManagerPersonalInfo();

					if (p.getHouseNo() != null) {
						personalInfo.setHouseNo(p.getHouseNo());
					}
					if (p.getLane() != null) {
						personalInfo.setLane(p.getLane());
					}
					if (p.getState() != null) {
						personalInfo.setState(p.getState());
					}
				}

				// 🔥 Work Info
				if (addressInfo.getManagerWorkInfo() != null) {
					var w = addressInfo.getManagerWorkInfo();

					if (w.getCountry() != null) {
						workInfo.setCountry(w.getCountry());
					}
					if (w.getExperience() != 0) {
						workInfo.setExperience(w.getExperience());
					}
					if (w.getLocation() != null) {
						workInfo.setLocation(w.getLocation());
					}
					if (w.getState() != null) {
						workInfo.setState(w.getState());
					}
				}
			}

			managerInfoRepository.save(mangerEntity);

		} catch (Exception e) {
			e.printStackTrace();
		}
		managerInfoResponse.setMessage("Data Saved Successfully");
		return managerInfoResponse;
	}

	@Override
	public FetchManagerDetailResponse fetchManagerDetail(FetchManagerDetailRequest fetchManagerDetailRequest) {
		
		FetchManagerDetailResponse fetchManagerDetailResponse=new FetchManagerDetailResponse();
		List<FetchMangerInfoDetail>fetchMangerInfoDetail=new ArrayList<>();
		List<MangerEntity>mangerEntity=managerInfoRepository.findManager(fetchManagerDetailRequest.getState());
		for(MangerEntity manager:mangerEntity) {
			FetchMangerInfoDetail fetchManager=new FetchMangerInfoDetail();
			fetchManager.setAge(manager.getAge());
			fetchManager.setName(manager.getName());
			fetchManager.setDesignation(manager.getDesignation());
			fetchManager.setEmail(manager.getEmail());
			fetchManager.setGender(manager.getGender());
			fetchManager.setId(manager.getId());
			fetchManager.setPhoneNo(manager.getPhoneNo());
			fetchManager.setAddressInfo(getAddressInfo(manager));
			fetchMangerInfoDetail.add(fetchManager);
		}
		fetchManagerDetailResponse.setFetchMangerInfoDetail(fetchMangerInfoDetail);		
		return fetchManagerDetailResponse;
	}

	private AddressInfo getAddressInfo(MangerEntity manager) {
		AddressInfo addressInfo=new AddressInfo();
		ManagerWorkInfo managerWorkInfo=new ManagerWorkInfo();
		ManagerPersonalInfo managerPersonalInfo=new ManagerPersonalInfo();
		managerPersonalInfo.setHouseNo(manager.getAddress().getPersonalInfo().getHouseNo());
		managerPersonalInfo.setLane(manager.getAddress().getPersonalInfo().getLane());
		managerPersonalInfo.setState(manager.getAddress().getPersonalInfo().getState());
		managerWorkInfo.setCountry(manager.getAddress().getWorkInfo().getCountry());
		managerWorkInfo.setExperience(manager.getAddress().getWorkInfo().getExperience());
		managerWorkInfo.setLocation(manager.getAddress().getWorkInfo().getLocation());
		managerWorkInfo.setState(manager.getAddress().getWorkInfo().getState());
		addressInfo.setManagerPersonalInfo(managerPersonalInfo);
		addressInfo.setManagerWorkInfo(managerWorkInfo);
		return addressInfo;
	}

}
