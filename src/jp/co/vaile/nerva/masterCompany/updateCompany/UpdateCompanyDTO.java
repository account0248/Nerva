package jp.co.vaile.nerva.masterCompany.updateCompany;

public class UpdateCompanyDTO {

	private String updateUserId;
	private String[] companyId;
	private String[] companyName;

	public UpdateCompanyDTO(String updateUserId, String[] companyId, String[] companyName) {
		super();
		this.updateUserId = updateUserId;
		this.companyId = companyId;
		this.companyName = companyName;
	
	}

		public String getUpdateUserId() {
			return updateUserId;
		}

		public void setUpdateUserId(String updateUserId) {
			this.updateUserId = updateUserId;
		}

		public String[] getCompanyId() {
			return companyId;
		}

		public void setCompanyId(String[] companyId) {
			this.companyId = companyId;
		}

		public String[] getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String[] companyName) {
			this.companyName = companyName;
		}

	
	}