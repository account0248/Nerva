package jp.co.vaile.nerva.searchTeam;

public class SearchTeamDTO {
	private String teamId;
	private String teamName;
	private String belongCompanyName;
	private String teamManagerName;
	private String teamLeaderName;
	private String projectName;
	private String totalBelongMember;

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getBelongCompanyName() {
		return belongCompanyName;
	}

	public void setBelongCompanyName(String belongCompanyName) {
		this.belongCompanyName = belongCompanyName;
	}

	public String getTeamManagerName() {
		return teamManagerName;
	}

	public void setTeamManagerName(String teamManagerName) {
		this.teamManagerName = teamManagerName;
	}

	public String getTeamLeaderName() {
		return teamLeaderName;
	}

	public void setTeamLeaderName(String teamLeaderName) {
		this.teamLeaderName = teamLeaderName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTotalBelongMember() {
		return totalBelongMember;
	}

	public void setTotalBelongMember(String totalBelongMember) {
		this.totalBelongMember = totalBelongMember;
	}

}
