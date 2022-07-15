package jp.co.vaile.nerva.updateEmployee;

import jp.co.vaile.nerva.commonprocess.LengthCheck;

public class RegistEmpWorkExpDTO extends UpdateEmpRelationDTO {
	private String belongTeamId;
	private String contractTypeId;
	private String roleId;
	private String monthlyUnitPrice;
	private String teamBelongStartDate;
	private String teamBelongCompleteDate;
	private boolean applicationFlag;

	public RegistEmpWorkExpDTO(String belongTeamId, String contractTypeId, String roleId,
			String monthlyUnitPrice, String teamBelongStartDate, String teamBelongCompleteDate) {
		LengthCheck lengthCheck = new LengthCheck();
		this.belongTeamId = belongTeamId;
		this.contractTypeId = contractTypeId;
		this.roleId = roleId;
		//月単価が空文字の場合、nullをセット
		if (monthlyUnitPrice.equals("")) {
			this.monthlyUnitPrice = null;
			//先頭の文字が￥マークの場合取り除く
		} else if (monthlyUnitPrice.trim().startsWith("\u00A5") || monthlyUnitPrice.trim().startsWith("\\")) {
			this.monthlyUnitPrice = monthlyUnitPrice.trim().substring(1);
		} else {
			this.monthlyUnitPrice = monthlyUnitPrice;
		}
		this.teamBelongStartDate = teamBelongStartDate;
		//所属開始日の有無を確認
		//空文字の場合はnullに変換
		if (lengthCheck.isNomNullOrNomEmpty(teamBelongCompleteDate) == false) {
			this.teamBelongCompleteDate = null;
		} else {
			this.teamBelongCompleteDate = teamBelongCompleteDate;
		}
	}

	public String getBelongTeamId() {
		return belongTeamId;
	}

	public String getContractTypeId() {
		return contractTypeId;
	}

	public String getRoleId() {
		return roleId;
	}

	public String getMonthlyUnitPrice() {
		return monthlyUnitPrice;
	}

	public String getTeamBelongStartDate() {
		return teamBelongStartDate;
	}

	public String getTeamBelongCompleteDate() {
		return teamBelongCompleteDate;
	}

	public boolean isApplicationFlag() {
		return applicationFlag;
	}

	public void setApplicationFlag(boolean applicationFlag) {
		this.applicationFlag = applicationFlag;
	}

}
