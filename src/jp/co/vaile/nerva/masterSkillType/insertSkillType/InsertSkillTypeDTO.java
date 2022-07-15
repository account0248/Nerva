package jp.co.vaile.nerva.masterSkillType.insertSkillType;

public class InsertSkillTypeDTO {
	//スキル種別マスタメンテナンス画面の登録時に入力される値を格納
	private String skillTypeId;
	private String skillTypeName;
	private String yearsDateOfAcquisition;
	//ログイン者のユーザーIDを格納
	private String registUserId;
	
	//コンストラクタ
	InsertSkillTypeDTO(){
	}
		
	InsertSkillTypeDTO(String skillTypeId, String skillTypeName, String yearsDateOfAcquisition, String registUserId){
		this.skillTypeId = skillTypeId;
		this.skillTypeName = skillTypeName;
		this.yearsDateOfAcquisition = yearsDateOfAcquisition;
		this.registUserId = registUserId;
	}

	public String getRegistUserId() {
		return registUserId;
	}

	public void setRegistUserId(String registUserId) {
		this.registUserId = registUserId;
	}

	public String getSkillTypeId() {
		return skillTypeId;
	}

	public void setSkillTypeId(String skillTypeId) {
		this.skillTypeId = skillTypeId;
	}

	public String getSkillTypeName() {
		return skillTypeName;
	}

	public void setSkillTypeName(String skillTypeName) {
		this.skillTypeName = skillTypeName;
	}

	public String getYearsDateOfAcquisition() {
		return yearsDateOfAcquisition;
	}

	public void setYearsDateOfAcquisition(String yearsDateOfAcquisition) {
		this.yearsDateOfAcquisition = yearsDateOfAcquisition;
	}
	
}
