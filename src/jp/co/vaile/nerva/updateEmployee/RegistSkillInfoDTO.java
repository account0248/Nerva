package jp.co.vaile.nerva.updateEmployee;

import jp.co.vaile.nerva.commonprocess.LengthCheck;

public class RegistSkillInfoDTO extends UpdateEmpRelationDTO {
	private String skillTypeId;
	private String skillDetail;
	private String experienceYears;
	private String acquisitionYearMonth;

	public RegistSkillInfoDTO(String skillTypeId, String skillDetail, String experienceYears,
			String acquisitionYearMonth) {
		this.skillTypeId = skillTypeId;
		this.skillDetail = skillDetail;
		LengthCheck lengthCheck = new LengthCheck();
		//経験年数の有無を確認
		//空文字の場合はnullに変換
		if (lengthCheck.isNomNullOrNomEmpty(experienceYears) == false) {
			this.experienceYears = null;
		} else {
			this.experienceYears = experienceYears;
		}
		//取得年月の有無を確認
		//空文字の場合はnullに変換
		if (lengthCheck.isNomNullOrNomEmpty(acquisitionYearMonth) == false) {
			this.acquisitionYearMonth = null;
		} else {
			this.acquisitionYearMonth = acquisitionYearMonth;
		}
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

}
