package jp.co.vaile.nerva.registEmployee;

import java.util.Date;

public class RegistSkillInfoDTO {
	private String employeeId;
	private String skillTypeId;
	private String skillDetail;
	private String experienceYears;
	private Date acquisitionYearMonth;
	private String registUser;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getSkillTypeId() {
		return skillTypeId;
	}

	public void setSkillTypeId(String skillTypeId) {
		this.skillTypeId = skillTypeId;
	}

	public String getSkillDetail() {
		return skillDetail;
	}

	public void setSkillDetail(String skillDetail) {
		this.skillDetail = skillDetail;
	}

	public String getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(String experienceYears) {
		this.experienceYears = experienceYears;
	}

	public Date getAcquisitionYearMonth() {
		return acquisitionYearMonth;
	}

	public void setAcquisitionYearMonth(Date acquisitionYearMonth) {
		this.acquisitionYearMonth = acquisitionYearMonth;
	}

	public String getRegistUser() {
		return registUser;
	}

	public void setRegistUser(String registUser) {
		this.registUser = registUser;
	}

}
