package jp.co.vaile.nerva.masterIndustry.updateIndustry;



public class UpdateIndustryDTO {
	//業種マスタメンテナンス画面の更新時に入力される値を格納
	private String updateUserId;
	private String[] industryId;
	private String[] industryName;
	
	//コンストラクタ
	UpdateIndustryDTO(String updateUserId, String[] industryId, String[] industryName){
		this.updateUserId = updateUserId;
		this.industryId = industryId;
		this.industryName = industryName;
	}
	
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String[] getIndustryId() {
		return industryId;
	}
	public void setIndustryId(String[] industryId) {
		this.industryId = industryId;
	}
	public String[] getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String[] industryName) {
		this.industryName = industryName;
	}
}
