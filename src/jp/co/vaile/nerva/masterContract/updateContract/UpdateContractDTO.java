package jp.co.vaile.nerva.masterContract.updateContract;


public class UpdateContractDTO {
	
	private String updateUserId;
	private String[] contractId;
	private String[] contractName;
	
	UpdateContractDTO(String updateUserId, String[] contractId, String[] contractName) {
		this.updateUserId = updateUserId;
		this.contractId = contractId;
		this.setContractName(contractName);
	}
	
	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String[] getContractId() {
		return contractId;
	}

	public void setContractId(String[] contractId) {
		this.contractId = contractId;
	}
	public String[] getContractName() {
		return contractName;
	}
	public void setContractName(String[] contractName) {
		this.contractName = contractName;
	}

	
	
	
}
