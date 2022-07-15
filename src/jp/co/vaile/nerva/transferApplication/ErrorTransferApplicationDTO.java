package jp.co.vaile.nerva.transferApplication;

public class ErrorTransferApplicationDTO {

	private String errorMsg;

	private TransferApplicationToStringDTO transferApplicationErrorDTO;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public TransferApplicationToStringDTO getTransferApplicationErrorDTO() {
		return transferApplicationErrorDTO;
	}

	public void setTransferApplicationErrorDTO(TransferApplicationToStringDTO transferApplicationErrorDTO) {
		this.transferApplicationErrorDTO = transferApplicationErrorDTO;
	}



}
