package jp.co.vaile.nerva.updateEmployee;

import jp.co.vaile.nerva.commonprocess.LengthCheck;

public class UpdateSkillInfoDTO extends UpdateEmpRelationDTO {
	private int skillInfoId;
	private String skillTypeId;
	private String skillDetail;
	private String experienceYears;
	private String acquisitionYearMonth;

	public UpdateSkillInfoDTO(int skillInfoId, String skillTypeId, String skillDetail, String experienceYears,
			String acquisitionYearMonth) {
		LengthCheck lengthCheck = new LengthCheck();
		this.skillInfoId = skillInfoId;
		this.skillTypeId = skillTypeId;
		this.skillDetail = skillDetail;
		//経験年数の有無を確認
		//空文字の場合はnullに変換
		if (lengthCheck.isNomNullOrNomEmpty(experienceYears) == false || experienceYears.equals("-")) {
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

	public int getSkillInfoId() {
		return skillInfoId;
	}

	public String getSkillTypeId() {
		return skillTypeId;
	}

	public String getSkillDetail() {
		return skillDetail;
	}

	public String getExperienceYears() {
		return experienceYears;
	}

	public String getAcquisitionYearMonth() {
		return acquisitionYearMonth;
	}

}
