package jp.co.vaile.nerva.masterCompany.insertCompany;

public class InsertCompanyDTO {

	private String resistUserId;
	private String companyId;
	private String companyName;
	private String companyGroup;
	private String companyCode;
	
	public InsertCompanyDTO(String resistUserId, String companyId, String companyName, String companyGroup,
			String companyCode) {
		super();
		this.resistUserId = resistUserId;
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyGroup = companyGroup;
		this.companyCode = companyCode;
	}
	public String getResistUserId() {
		return resistUserId;
	}
	public void setResistUserId(String resistUserId) {
		this.resistUserId = resistUserId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyGroup() {
		return companyGroup;
	}
	public void setCompanyGroup(String companyGroup) {
		this.companyGroup = companyGroup;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	

}
