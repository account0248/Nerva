package jp.co.vaile.nerva.transferApplication;

public class SearchTransferApplicationToStringDTO {

	private String transferEmpId;

	private String roleId;

	private String contractTypeId;

	private int monthUnitPrice;

	private String transferApplicationTeam;

	private String transferPreferredDate;

	private String teamFinishDate;


	public String getTransferEmpId() {
		return transferEmpId;
	}

	public void setTransferEmpId(String transferEmpId) {
		this.transferEmpId = transferEmpId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getContractTypeId() {
		return contractTypeId;
	}

	public void setContractTypeId(String contractTypeId) {
		this.contractTypeId = contractTypeId;
	}

	public int getMonthUnitPrice() {
		return monthUnitPrice;
	}

	public void setMonthUnitPrice(int monthUnitPrice) {
		this.monthUnitPrice = monthUnitPrice;
	}

	public String getTransferApplicationTeam() {
		return transferApplicationTeam;
	}

	public void setTransferApplicationTeam(String transferApplicationTeam) {
		this.transferApplicationTeam = transferApplicationTeam;
	}

	public String getTransferPreferredDate() {
		return transferPreferredDate;
	}

	public void setTransferPreferredDate(String transferPreferredDate) {
		this.transferPreferredDate = transferPreferredDate;
	}

	public String getTeamFinishDate() {
		return teamFinishDate;
	}

	public void setTeamFinishDate(String teamFinishDate) {
		this.teamFinishDate = teamFinishDate;
	}

}
