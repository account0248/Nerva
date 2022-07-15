package jp.co.vaile.nerva.updateProject;

import java.util.Date;

public class StubEmpExpDTO {
	private int employeeExperienceId;
	private String employeeId;
	private String teamId;
	private Date teamBelongStartDate;
	private Date teamBelongCompleteDate;
	private String roleId;
	private String contractTypeId;
	private Integer monthlyUnitPrice;

	public int getEmployeeExperienceId() {
		return employeeExperienceId;
	}
	public void setEmployeeExperienceId(int employeeExperienceId) {
		this.employeeExperienceId = employeeExperienceId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public Date getTeamBelongStartDate() {
		return teamBelongStartDate;
	}
	public void setTeamBelongStartDate(Date teamBelongStartDate) {
		this.teamBelongStartDate = teamBelongStartDate;
	}
	public Date getTeamBelongCompleteDate() {
		return teamBelongCompleteDate;
	}
	public void setTeamBelongCompleteDate(Date teamBelongCompleteDate) {
		this.teamBelongCompleteDate = teamBelongCompleteDate;
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
	public Integer getMonthlyUnitPrice() {
		return monthlyUnitPrice;
	}
	public void setMonthlyUnitPrice(Integer monthlyUnitPrice) {
		this.monthlyUnitPrice = monthlyUnitPrice;
	}
}
