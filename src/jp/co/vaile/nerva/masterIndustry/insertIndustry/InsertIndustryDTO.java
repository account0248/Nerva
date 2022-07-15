package jp.co.vaile.nerva.masterIndustry.insertIndustry;


public class InsertIndustryDTO {

	private String registUserId;
	private String industryId;
	private String industryName;
	
	InsertIndustryDTO(String registUserId, String industryId, String industryName){
		this.registUserId = registUserId;
		this.industryId = industryId;
		this.industryName = industryName;
	}
		
	public String getRegistUserId() {
		return registUserId;
	}
	public void setRegistUserId(String registUserId) {
		this.registUserId = registUserId;
	}
	public String getIndustryId() {
		return industryId;
	}
	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	
}
