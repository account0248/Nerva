package jp.co.vaile.nerva.registEmployee;

import java.util.ArrayList;

import jp.co.vaile.nerva.commonprocess.LengthCheck;

public class RegistSkillInfoPageDTO {
	private ArrayList<String> skillType;
	private ArrayList<String> skillDetail;
	private ArrayList<String> experienceYears;
	private ArrayList<String> acquisitionYearMonth;

	public RegistSkillInfoPageDTO(String[] skillType, String[] skillDetail, String[] experienceYears,
			String[] acquisitionYearMonth) {
		ArrayList<String> skillTypeList = new ArrayList<String>();
		ArrayList<String> skillDetailList = new ArrayList<String>();
		ArrayList<String> experienceYearsList = new ArrayList<String>();
		ArrayList<String> acquisitionYearMonthList = new ArrayList<String>();
		LengthCheck lengthCheck = new LengthCheck();
		//スキル情報が登録されていない場合は何もしない。
		if (skillType != null) {
			for (int i = 0; i < skillType.length; i++) {
				skillTypeList.add(skillType[i]);
				skillDetailList.add(skillDetail[i]);
				//経験年数の有無を確認
				//空文字の場合はnullを格納。値が入っている場合はそのまま格納。
				if (lengthCheck.isNomNullOrNomEmpty(experienceYears[i]) == false) {
					experienceYearsList.add(null);
				} else {
					experienceYearsList.add(experienceYears[i]);
				}
				//取得年月の有無を確認
				//空文字の場合はnullを格納。値が入っている場合はそのまま格納。
				if (lengthCheck.isNomNullOrNomEmpty(acquisitionYearMonth[i]) == false) {
					acquisitionYearMonthList.add(null);
				} else {
					acquisitionYearMonthList.add(acquisitionYearMonth[i]);
				}
			}
			this.skillType = skillTypeList;
			this.skillDetail = skillDetailList;
			this.experienceYears = experienceYearsList;
			this.acquisitionYearMonth = acquisitionYearMonthList;
		}
	}

	public ArrayList<String> getSkillType() {
		return skillType;
	}

	public ArrayList<String> getSkillDetail() {
		return skillDetail;
	}

	public ArrayList<String> getExperienceYears() {
		return experienceYears;
	}

	public ArrayList<String> getAcquisitionYearMonth() {
		return acquisitionYearMonth;
	}

}
