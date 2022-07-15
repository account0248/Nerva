package jp.co.vaile.nerva.masterSkillType.searchSkillType;

public class SearchSkillTypeDTO {
	//スキル種別マスタメンテナンス画面の検索時に入力される値を格納
	private String skillTypeId;
	private String skillTypeName;
	private String yearsDateOfAcquisition;
	
	//コンストラクタ
	public SearchSkillTypeDTO(){
	}
	
	public SearchSkillTypeDTO(String skillTypeId, String skillTypeName, String yearsDateOfAcquisition){
		this.skillTypeId = skillTypeId;
		this.skillTypeName = skillTypeName;
		this.yearsDateOfAcquisition = yearsDateOfAcquisition;
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
