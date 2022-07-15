package jp.co.vaile.nerva.transferApplication;

import java.sql.Date;

public class SearchTransferApplicationDTO {

	private String transferEmpId;

	private String roleId;

	private String contractTypeId;

	private int monthUnitPrice;

	private String transferApplicationTeam;

	private Date transferPreferredDate;

	private Date teamFinishDate;

	private boolean deleteFlg;

	private String projectId;

	private boolean unapprovalFlg;

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

	public Date getTransferPreferredDate() {
		return transferPreferredDate;
	}

	public void setTransferPreferredDate(Date transferPreferredDate) {
		this.transferPreferredDate = transferPreferredDate;
	}

	public Date getTeamFinishDate() {
		return teamFinishDate;
	}

	public void setTeamFinishDate(Date teamFinishDate) {
		this.teamFinishDate = teamFinishDate;
	}

	public boolean isDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}


	public void setUnapprovalFlg(boolean unapprovalFlg) {
		this.unapprovalFlg = unapprovalFlg;
	}

}
