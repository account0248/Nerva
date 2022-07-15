package jp.co.vaile.nerva.updateEmployee;

import java.util.List;

public class CheckDateEmpWorkExpDTO {
	private List<String> teamBelongStartDateList;
	private List<String> teamBelongCompleteDateList;

	public CheckDateEmpWorkExpDTO(List<String> teamBelongStartDateList, List<String> teamBelongCompleteDateList) {
		this.teamBelongStartDateList = teamBelongStartDateList;
		this.teamBelongCompleteDateList = teamBelongCompleteDateList;
	}

	public List<String> getTeamBelongStartDateList() {
		return teamBelongStartDateList;
	}

	public List<String> getTeamBelongCompleteDateList() {
		return teamBelongCompleteDateList;
	}

}
