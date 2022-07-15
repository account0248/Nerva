package jp.co.vaile.nerva.updateEmployee;

public class TeamInfoDTO {
	private String projectName;
	private String responsibleIndustry;
	private String resposibleProjectCompany;
	private String teamManagerName;
	private String teamName;
	private String teamId;

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getResponsibleIndustry() {
		return responsibleIndustry;
	}

	public void setResponsibleIndustry(String responsibleIndustry) {
		this.responsibleIndustry = responsibleIndustry;
	}

	public String getResposibleProjectCompany() {
		return resposibleProjectCompany;
	}

	public void setResposibleProjectCompany(String resposibleProjectCompany) {
		this.resposibleProjectCompany = resposibleProjectCompany;
	}

	public String getTeamManagerName() {
		return teamManagerName;
	}

	public void setTeamManagerName(String teamManagerName) {
		this.teamManagerName = teamManagerName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
