package jp.co.vaile.nerva.searchTeam;

public class SearchTeamPageDTO {
	private String teamId;
	private String teamName;
	private String teamLeaderName;
	private String projectId;
	private String projectName;
	private String orderSourceName;

	public SearchTeamPageDTO(String teamId, String teamName, String teamLeaderName, String projectId,
			String projectName,
			String orderSourceName) {
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamLeaderName = teamLeaderName;
		this.projectId = projectId;
		this.projectName = projectName;
		this.orderSourceName = orderSourceName;
	}

	public String getTeamId() {
		return teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getTeamLeaderName() {
		return teamLeaderName;
	}

	public String getProjectId() {
		return projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getOrderSourceName() {
		return orderSourceName;
	}

}
