package jp.co.vaile.nerva.detailEmployee;

public class EmpSkillInfoDTO {
	private int skillInfoId;
	private String skillTypeId;
	private String skillType;
	private String skillDetail;
	private String experienceYears;
	private String acquisitionYearMonth;
	private boolean yearsDateOfAcquisitionFlg;

	public int getSkillInfoId() {
		return skillInfoId;
	}

	public void setSkillInfoId(int skillInfoId) {
		this.skillInfoId = skillInfoId;
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

	public String getAcquisitionYearMonth() {
		return acquisitionYearMonth;
	}

	public void setAcquisitionYearMonth(String acquisitionYearMonth) {
		this.acquisitionYearMonth = acquisitionYearMonth;
	}

	public String getSkillType() {
		return skillType;
	}

	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}

	public boolean isYearsDateOfAcquisitionFlg() {
		return yearsDateOfAcquisitionFlg;
	}

	public void setYearsDateOfAcquisitionFlg(boolean yearsDateOfAcquisitionFlg) {
		this.yearsDateOfAcquisitionFlg = yearsDateOfAcquisitionFlg;
	}
}