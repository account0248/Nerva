package jp.co.vaile.nerva.masterContract.insertContract;


public class InsertContractDTO {

	private String registUserId;
	private String contractId;
	private String contractName;
	
	InsertContractDTO(String registUserId, String contractId, String contractName){
		this.registUserId = registUserId;
		this.contractId = contractId;
		this.contractName = contractName;
	}
		
	public String getRegistUserId() {
		return registUserId;
	}
	public void setLoginUserId(String registUserId) {
		this.registUserId = registUserId;
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
