package jp.co.vaile.nerva.updateEmployee;

import jp.co.vaile.nerva.commonprocess.LengthCheck;

public class UpdateEmpWorkExpDTO extends UpdateEmpRelationDTO {
	private int employeeExperienceId;
	private String teamBelongStartDate;
	private String teamBelongCompleteDate;

	public UpdateEmpWorkExpDTO(int employeeExperienceId, String teamBelongStartDate, String teamBelongCompleteDate) {
		LengthCheck lengthCheck = new LengthCheck();
		this.employeeExperienceId = employeeExperienceId;
		this.teamBelongStartDate = teamBelongStartDate;
		//所属完了日の空文字チェック
		if (!lengthCheck.isNomNullOrNomEmpty(teamBelongCompleteDate)) {
			this.teamBelongCompleteDate = null;
		} else {
			this.teamBelongCompleteDate = teamBelongCompleteDate;
		}
	}

	public int getEmployeeExperienceId() {
		return employeeExperienceId;
	}

	public void setEmployeeExperienceId(int employeeExperienceId) {
		this.employeeExperienceId = employeeExperienceId;
	}

	public String getTeamBelongStartDate() {
		return teamBelongStartDate;
	}

	public void setTeamBelongStartDate(String teamBelongStartDate) {
		this.teamBelongStartDate = teamBelongStartDate;
	}

	public String getTeamBelongCompleteDate() {
		return teamBelongCompleteDate;
	}

	public void setTeamBelongCompleteDate(String teamBelongCompleteDate) {
		this.teamBelongCompleteDate = teamBelongCompleteDate;
	}

}
