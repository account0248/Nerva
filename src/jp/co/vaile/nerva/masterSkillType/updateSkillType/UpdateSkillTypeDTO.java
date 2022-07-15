package jp.co.vaile.nerva.masterSkillType.updateSkillType;

public class UpdateSkillTypeDTO {
	//スキル種別マスタメンテナンス画面の更新時に入力される値を格納
	private String[] skillTypeId;
	private String[] skillTypeName;
	private String[] yearsDateOfAcquisition;
	//ログイン者のユーザーIDを格納
	private String updateUserId;
	
	//コンストラクタ
	UpdateSkillTypeDTO(){
	}
		
	UpdateSkillTypeDTO(String[] skillTypeId, String[] skillTypeName, String[] yearsDateOfAcquisition, String updateUserId){
		this.skillTypeId = skillTypeId;
		this.skillTypeName = skillTypeName;
		this.yearsDateOfAcquisition = yearsDateOfAcquisition;
		this.updateUserId = updateUserId;
	}

	public String[] getSkillTypeId() {
		return skillTypeId;
	}

	public void setSkillTypeId(String[] skillTypeId) {
		this.skillTypeId = skillTypeId;
	}

	public String[] getSkillTypeName() {
		return skillTypeName;
	}

	public void setSkillTypeName(String[] skillTypeName) {
		this.skillTypeName = skillTypeName;
	}

	public String[] getYearsDateOfAcquisition() {
		return yearsDateOfAcquisition;
	}

	public void setYearsDateOfAcquisition(String[] yearsDateOfAcquisition) {
		this.yearsDateOfAcquisition = yearsDateOfAcquisition;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	
}
