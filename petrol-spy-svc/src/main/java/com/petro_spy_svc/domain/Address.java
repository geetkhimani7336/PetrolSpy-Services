package com.petro_spy_svc.domain;

public class Address {

	private PersonalInfo personalInfo;
	
	private WorkInfo workInfo;

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public WorkInfo getWorkInfo() {
		return workInfo;
	}

	public void setWorkInfo(WorkInfo workInfo) {
		this.workInfo = workInfo;
	}
	
	
}
