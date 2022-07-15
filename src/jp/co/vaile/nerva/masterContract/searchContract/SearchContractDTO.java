package jp.co.vaile.nerva.masterContract.searchContract;



public class SearchContractDTO {
	private String contractId;
	private String contractName;
	
	
	public SearchContractDTO() {
		super();
	}

	public SearchContractDTO(String contractId, String contractName) {
		super();
		this.contractId = contractId;
		this.contractName = contractName;
	}

	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	

}
